package fr.cocoraid.prodigysuits.item;

import com.comphenix.protocol.wrappers.WrappedDataWatcher;
import fr.cocoraid.prodigysuits.ProdigySuits;
import fr.cocoraid.prodigysuits.packet.EIDGen;
import fr.cocoraid.prodigysuits.packet.wrapper.packet.WrapperPlayServerEntityDestroy;
import fr.cocoraid.prodigysuits.packet.wrapper.packet.WrapperPlayServerEntityMetadata;
import fr.cocoraid.prodigysuits.packet.wrapper.packet.WrapperPlayServerSpawnEntity;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

public class FiredItem {

    public FiredItem(Location location, int stayTime) {
        WrapperPlayServerSpawnEntity item = new WrapperPlayServerSpawnEntity();
        item.setEntityID(EIDGen.generateEID());
        item.setX(location.getX());
        item.setY(location.getY());
        item.setZ(location.getZ());

        item.setType(WrapperPlayServerSpawnEntity.ObjectTypes.ITEM_STACK);
        item.sendNearbyPacket(location);

        WrappedDataWatcher dataWatcher = new WrappedDataWatcher();
        dataWatcher.setObject(0,(byte)  (0x01));
        dataWatcher.setObject(10,new ItemStack(Material.STONE_BUTTON));

        WrapperPlayServerEntityMetadata meta = new WrapperPlayServerEntityMetadata();
        meta.setEntityID(item.getEntityID());
        meta.setMetadata(dataWatcher.getWatchableObjects());
        meta.sendNearbyPacket(location);


        new BukkitRunnable() {
            @Override
            public void run() {
                WrapperPlayServerEntityDestroy destroy = new WrapperPlayServerEntityDestroy();
                destroy.setEntityIds(new int[] {item.getEntityID()});
                destroy.sendWorldPacket(location.getWorld());
            }
        }.runTaskLaterAsynchronously(ProdigySuits.getInstance(),stayTime * 20);
    }

}
