package fr.cocoraid.prodigysuits.suit.suits.aqua;

import com.comphenix.protocol.wrappers.EnumWrappers;
import fr.cocoraid.prodigysuits.packet.wrapper.entity.WrapperEntityArmorStand;
import fr.cocoraid.prodigysuits.particle.ParticleEffect;
import fr.cocoraid.prodigysuits.suit.parts.Chestplate;
import fr.cocoraid.prodigysuits.utils.ItemBuilder;
import fr.cocoraid.prodigysuits.utils.UtilMath;
import fr.cocoraid.prodigysuits.utils.VectorUtils;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Vector;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


public class AquaChestplate extends Chestplate {

    public AquaChestplate() {
        super("aqua","aquachestplate",
                new ItemBuilder(Material.LEATHER_CHESTPLATE)
                        .setColor(org.bukkit.Color.AQUA)
                        .setDisplayName("§bArmure aquatique")
        );

    }

    private List<Fish> fishes = new ArrayList<>();

    @Override
    public void remove(Player p) {
        super.remove(p);
        fishes.stream().filter(f -> f.owner.equals(p.getUniqueId()));
    }

    @Override
    public void asyncGlobalAnimate(Player p) {
        super.asyncGlobalAnimate(p);
        if(time % (10) == 0) {
            Fish fish = new Fish(p.getLocation(),p.getUniqueId());
            fishes.add(fish);
        }
    }


    @Override
    public void asyncGlobal() {
        super.asyncGlobal();
        fishes.removeIf(fish -> {
            if(fish == null) return true;
            else {
                fish.update();
                return false;
            }
        });
    }

    public void goTo(Vector direction) {
        fishes.forEach(f -> {
            f.goTo(direction);
        });
    }


    public class Fish {
        private UUID owner;
        private float maxHeight = 0.1F;
        private WrapperEntityArmorStand fish;
        private Vector direction = VectorUtils.getRandomCircleVector().normalize();
        public Fish(Location location, UUID owner) {
            this.owner = owner;
            Location loc = location.clone().add(VectorUtils.getRandomCircleVector().normalize());

            loc.setDirection(direction);
            this.fish = new WrapperEntityArmorStand(loc);
            fish.setInvisible(true);
            fish.setRightArmPose(180, 0,90);
            fish.setMarker(true);
            fish.setArms(true);
            fish.setSmall(true);
            fish.spawn();
            fish.equip(EnumWrappers.ItemSlot.MAINHAND,new ItemStack(Material.RAW_FISH));
            fish.sendUpdatedmetatada();
        }

        private boolean goTo = false;
        private int distance = 0;
        public void goTo(Vector direction) {
            if(fish == null) return;
            this.direction = direction;
            this.goTo = true;
            Location dir = fish.getLocation().setDirection(direction);
            dir.setPitch(0);
            fish.updateYaw(dir);
            y = 0;

        }

        private float y = 0;
        private boolean loop = false;
        public void update() {
            if(fish == null) return;

            ParticleEffect.WATER_SPLASH.display(fish.getLocation().clone().add(0,1,0),0.1F,0.1F,0.1F,0.1F,2, null);
            fish.teleport(fish.getLocation().add(direction.clone().multiply(0.2F)).add(0,y,0));

            if(goTo) {
                if(time % 5 == 0) {
                    Location l = fish.getLocation();
                    l.setYaw(l.getYaw() + (loop ? 60 : -60));
                    direction = l.getDirection();
                    loop = !loop;
                    fish.updateYaw(l);
                }

                distance++;
                if(distance >= (20 * 3)) {
                    ParticleEffect.CLOUD.display(fish.getLocation().clone().add(0,1,0),0.1F,0.1F,0.1F,0.1F,10, null);
                    fish.despawn();
                    fish = null;
                }

            } else {

                if (!loop) {
                    fish.setRightArmPose(UtilMath.randInt(160, 190), UtilMath.randInt(-20, 20), 90);
                    y += 0.01f;
                    if (y >= maxHeight) {
                        loop = true;
                        y = 0;
                    }
                } else {
                    y -= 0.02F;
                    fish.setRightArmPose(UtilMath.randInt(160, 190), 330 + UtilMath.randInt(-20, 20), 90);
                    if (y <= -(maxHeight + 0.2)) {
                        fish.despawn();
                        fish = null;
                        return;
                    }
                }
                fish.sendUpdatedmetatada();
            }

        }

    }

}
