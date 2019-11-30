package fr.cocoraid.prodigysuits.packet;

import com.comphenix.protocol.wrappers.EnumWrappers;
import com.comphenix.protocol.wrappers.WrappedDataWatcher;
import fr.cocoraid.prodigysuits.packet.wrapper.packet.*;
import fr.cocoraid.prodigysuits.utils.UtilMath;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.Map;

public abstract class WrappedEntity {


    private WrapperPlayServerSpawnEntityLiving spawnPacket;
    private WrapperPlayServerEntityDestroy destroyPacket;
    private WrapperPlayServerEntityMetadata metaPacket;
    private WrapperPlayServerEntityTeleport teleportPacket;
    private WrapperPlayServerEntityHeadRotation yawPacket;

    private WrappedDataWatcher dataWatcher;

    private Map<EnumWrappers.ItemSlot, WrapperPlayServerEntityEquipment> equipments = new HashMap<>();

    protected Location location;
    private boolean onFire = false;
    private boolean invisible = false;

    /**
     * @check https://wiki.vg/Entity_metadata#Mobs
     */
    private int typeID;
    private int id;

    public WrappedEntity(Location location, int typeID) {
        this.location = location;
        this.id = EIDGen.generateEID();
        this.typeID = typeID;

        this.spawnPacket = new WrapperPlayServerSpawnEntityLiving();
        spawnPacket.setEntityID(id);
        spawnPacket.setType(typeID);
        spawnPacket.setPitch(location.getPitch());
        spawnPacket.setHeadPitch(location.getPitch());
        spawnPacket.setYaw(location.getYaw());
        spawnPacket.setX(location.getX());
        spawnPacket.setY(location.getY());
        spawnPacket.setZ(location.getZ());

        this.yawPacket = new WrapperPlayServerEntityHeadRotation();
        yawPacket.setEntityID(id);
        yawPacket.setHeadYaw(UtilMath.toPackedByte(location.getYaw()));

        this.destroyPacket = new WrapperPlayServerEntityDestroy();
        destroyPacket.setEntityIds(new int[] {id});

        this.dataWatcher = new WrappedDataWatcher();

        this.metaPacket = new WrapperPlayServerEntityMetadata();
        metaPacket.setEntityID(id);

        this.teleportPacket = new WrapperPlayServerEntityTeleport();

        int slot = 0;
        for (EnumWrappers.ItemSlot itemSlot : EnumWrappers.ItemSlot.values()) {
            WrapperPlayServerEntityEquipment equip = new WrapperPlayServerEntityEquipment();
            equip.setEntityID(id);
            equipments.put(itemSlot,equip);
        }
    }

    public void teleport(Location newLocation) {
        teleportPacket.setEntityID(id);
        teleportPacket.setX(newLocation.getX());
        teleportPacket.setY(newLocation.getY());
        teleportPacket.setZ(newLocation.getZ());
        teleportPacket.setYaw(newLocation.getYaw());
        teleportPacket.setPitch(newLocation.getPitch());
        teleportPacket.sendWorldPacket(newLocation.getWorld());
        this.location = newLocation;

        //update spawn location too
        spawnPacket.setPitch(location.getPitch());
        spawnPacket.setHeadPitch(location.getPitch());
        spawnPacket.setYaw(location.getYaw());
        spawnPacket.setX(location.getX());
        spawnPacket.setY(location.getY());
        spawnPacket.setZ(location.getZ());

        yawPacket.setHeadYaw(UtilMath.toPackedByte(location.getYaw()));

    }

    public void updateYaw(Location location) {
        yawPacket.setHeadYaw(UtilMath.toPackedByte(location.getYaw()));
        yawPacket.sendWorldPacket(location.getWorld());
    }

    public void updateYaw() {
        yawPacket.sendWorldPacket(location.getWorld());
    }

    public void spawnClient(Player client) {
        spawnPacket.sendPacket(client);
        //armorstand does not need it...
        if(id != 0)
            yawPacket.sendPacket(client);
    }

    public void updateForClient(Player client) {

    }


    public void despawnClient(Player client) {
        destroyPacket.sendPacket(client);

    }

    public void spawn() {
        spawnPacket.sendNearbyPacket(location);
        if(id != 0)
            yawPacket.sendNearbyPacket(location);
    }

    public void spawnFar() {
        spawnPacket.sendWorldPacket(location.getWorld());
        if(id != 0)
            yawPacket.sendWorldPacket(location.getWorld());
    }



    public void setCustomNameVisible(boolean visible) {
        setDataWatcherObject(Boolean.class,3,visible);
    }
    public void setInvisible(boolean invisible) {
        this.invisible = invisible;
        setDataWatcherObject(Byte.class,0, (byte) (
                (invisible ? 0x20 : 0) |
                        (onFire ? 0x01 : 0)

        ));
    }

    public void setOnFire(boolean onFire) {
        this.onFire = onFire;

        setDataWatcherObject(Byte.class,0, (byte) (
                (invisible ? 0x20 : 0) |
                        (onFire ? 0x01 : 0)

        ));
    }

    public void despawn() {
        destroyPacket.sendWorldPacket(location.getWorld());
    }

    public void equip(EnumWrappers.ItemSlot slot, ItemStack item) {
        WrapperPlayServerEntityEquipment equipPacket = equipments.get(slot);
        equipPacket.setItem(item);
        if(slot == EnumWrappers.ItemSlot.HEAD)
            equipPacket.setSlot(4);
        else
            equipPacket.setSlot(slot);
        equipPacket.sendWorldPacket(location.getWorld());
    }


    public void setDataWatcherObject(Class<?> type, int objectIndex, Object object) {
        dataWatcher.setObject(objectIndex, object);
    }


    public void sendUpdatedmetatada() {
        metaPacket.setMetadata(dataWatcher.getWatchableObjects());
        metaPacket.sendWorldPacket(location.getWorld());
    }

    public WrappedDataWatcher getDataWatcher() {
        return dataWatcher;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public int getId() {
        return id;
    }

    public Location getLocation() {
        return location;
    }
}
