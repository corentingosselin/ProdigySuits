package fr.cocoraid.prodigysuits.suit;

import com.comphenix.protocol.wrappers.EnumWrappers;
import fr.cocoraid.prodigysuits.packet.wrapper.entity.WrapperEntityArmorStand;
import fr.cocoraid.prodigysuits.utils.VectorUtils;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Vector;

import java.util.ArrayList;
import java.util.List;

public class FlyingItem {

    private List<ItemListener> listeners = new ArrayList<>();

    public interface ItemListener {
        void onRemove();
        void onUpdate();
    }

    public void addListener(ItemListener listener) {
        listeners.add(listener);
    }

    public enum Size {
        BIG(),
        MEDIUM(),
        SMALL(),
        EXTRA_SMALL();
    }

    private WrapperEntityArmorStand flyingItem;
    protected Vector vector;
    private double speedRotation;
    private Location current;
    private boolean rotate;
    private boolean goesUp;
    private double speedGoesUp;
    private boolean rotationItself;
    private float itselfSpeedRotation;
    private Vector direction;
    private double directionSpeed = 1;

    private int stayTime = -1;
    public FlyingItem(Location start,
                      Material material ,
                      int stayTime,
                      float poseX,
                      float poseY,
                      float poseZ,
                      Size size,
                      double radius,
                      double y,
                      double startAngle,
                      boolean rotate,
                      boolean goesUp,
                      double speedRotation,
                      double speedGoesUp,
                      boolean rotateItself,
                      float itselfRotationSpeed) {


        if(stayTime != -1)
            this.stayTime = Math.abs(stayTime) * 20;
        this.rotationItself = rotateItself;
        this.itselfSpeedRotation = itselfRotationSpeed;

        this.current = start.clone();
        if(size == Size.BIG || size == Size.MEDIUM) {
            current.subtract(0,1.5,0);
        } else {
            current.subtract(0,0.5,0);
        }
        this.vector = new Vector(radius * Math.cos(startAngle), y, radius * Math.sin(startAngle));
        current.add(vector);
        this.speedRotation = speedRotation;
        this.speedGoesUp = speedGoesUp;
        this.goesUp = goesUp;
        this.rotate = rotate;

        this.flyingItem = new WrapperEntityArmorStand(current);
        flyingItem.setArms(true);
        flyingItem.setInvisible(true);
        flyingItem.setMarker(true);
        if(stayTime <= -1) {
            flyingItem.spawnFar();
        } else
            flyingItem.spawn();

        if(size != Size.EXTRA_SMALL && size != Size.SMALL) {
            flyingItem.setSmall(size == Size.BIG ? false : true);
            flyingItem.setHeadPose(poseX,poseY,poseZ);
            flyingItem.equip(EnumWrappers.ItemSlot.HEAD,new ItemStack(material));
        } else {
            flyingItem.setSmall(size == Size.SMALL ? false : true);
            flyingItem.setRightArmPose(poseZ, poseY,322);
            flyingItem.equip(EnumWrappers.ItemSlot.MAINHAND,new ItemStack(material));
        }
        flyingItem.sendUpdatedmetatada();
    }

    private float yaw = 0;
    public void update(Location location) {
        if(flyingItem == null) return;

        listeners.forEach(l -> l.onUpdate());

        if(direction != null) {
            flyingItem.teleport(flyingItem.getLocation().clone().add(direction.clone().multiply(directionSpeed)));
        }

        if(rotate && direction == null) {
            VectorUtils.rotateAroundAxisY(vector, speedRotation);
            flyingItem.teleport(location.clone().add(vector));
        }

        if(goesUp && direction == null) {
            flyingItem.teleport(location.clone().add(0,speedGoesUp,0
            ));
        }

        if(rotationItself) {
            flyingItem.updateRotation(yaw);
        }


        if(stayTime > 0) {
            stayTime--;
            if(stayTime == 0) {
                if (flyingItem != null) {
                    listeners.forEach(l -> l.onRemove());
                    flyingItem.despawn();
                    this.flyingItem = null;
                }
            }
        }
        yaw+=itselfSpeedRotation;
        yaw%=360;
    }

    public void setDirection(Vector direction) {
        this.direction = direction;
    }

    public void setDirectionSpeed(double directionSpeed) {
        this.directionSpeed = directionSpeed;
    }

    public void setStayTime(int stayTime) {
        this.stayTime = stayTime * 20;
    }

    public void remove() {
        if(flyingItem == null) return;
        flyingItem.despawn();
        this.flyingItem = null;
    }

    public Vector getDirection() {
        return direction;
    }

    public int getStayTime() {
        return stayTime;
    }

    public Location getCurrentLocation() {
        return flyingItem.getLocation();
    }

    public boolean isExisting() {
        return flyingItem != null;
    }


}
