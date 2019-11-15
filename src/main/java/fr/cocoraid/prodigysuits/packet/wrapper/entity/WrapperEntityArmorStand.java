package fr.cocoraid.prodigysuits.packet.wrapper.entity;

import com.comphenix.protocol.wrappers.Vector3F;
import fr.cocoraid.prodigysuits.packet.WrappedEntity;
import fr.cocoraid.prodigysuits.packet.wrapper.packet.WrapperPlayServerEntityLook;
import org.bukkit.Location;

public class WrapperEntityArmorStand extends WrappedEntity {



    private boolean small = false;
    private boolean noBasePlate = true;
    private boolean arms = false;
    private boolean marker = false;
    public WrapperEntityArmorStand(Location location) {
        super(location, 30);
    }



    public void updateRotation(float yaw) {
        WrapperPlayServerEntityLook look = new WrapperPlayServerEntityLook();
        look.setYaw(yaw);
        look.setEntityID(getId());
        look.sendNearbyPacket(location);
    }


    /**
     * Parameters in degree
     * @param x
     * @param y
     * @param z
     */
    public void setHeadPose(float x, float y, float z) {
        getDataWatcher().setObject(11,new Vector3F(x,y,z));
    }


    public void setRightArmPose(float x, float y, float z) {
        getDataWatcher().setObject(14,new Vector3F(x,y,z));
    }

    public void setArms(boolean arms) {
        this.arms = arms;
        setDataWatcherObject(Byte.class, 10,
                (byte) (
                        (arms ? 4 : 0) |
                                (small ? 1 : 0) |
                                (this.noBasePlate ? 8 : 0) |
                                (this.marker ? 22 : 0)

                ));
    }

    public void setSmall(boolean small) {
        this.small = small;
        setDataWatcherObject(Byte.class, 10,
                (byte) (
                        (arms ? 4 : 0) |
                                (small ? 1 : 0) |
                                (this.noBasePlate ? 8 : 0) |
                                (this.marker ? 22 : 0)

                ));    }

    public void setMarker(boolean marker) {
        this.marker = marker;
        setDataWatcherObject(Byte.class, 10,
                (byte) (
                        (arms ? 4 : 0) |
                                (small ? 1 : 0) |
                                (this.noBasePlate ? 8 : 0) |
                                (this.marker ? 22 : 0)

                ));    }

    public void setNoBasePlate(boolean noBasePlate) {
        this.noBasePlate = noBasePlate;
        setDataWatcherObject(Byte.class, 10,
                (byte) (
                        (arms ? 4 : 0) |
                                (small ? 1 : 0) |
                                (this.noBasePlate ? 8 : 0) |
                                (this.marker ? 22 : 0)

                ));    }

}
