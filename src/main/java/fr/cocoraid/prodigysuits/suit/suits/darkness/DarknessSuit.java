package fr.cocoraid.prodigysuits.suit.suits.darkness;

import fr.cocoraid.prodigysuits.item.FlyingItem;
import fr.cocoraid.prodigysuits.particle.ParticleEffect;
import fr.cocoraid.prodigysuits.particle.data.color.RegularColor;
import fr.cocoraid.prodigysuits.suit.Suit;
import fr.cocoraid.prodigysuits.utils.UtilMath;
import fr.cocoraid.prodigysuits.utils.VectorUtils;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.util.Vector;

import java.util.*;

public class DarknessSuit extends Suit {

    public DarknessSuit() {
        super("darkness",new DarknessBoot(), new DarknessLegging(), new DarknessHelmet(), new DarknessChestplate());
    }


    private Vector vector = new Vector(0.3 * Math.cos(2 * Math.PI), 0, 0.3 * Math.sin(2 * Math.PI));
    @Override
    public void asyncGlobal() {
        super.asyncGlobal();
        VectorUtils.rotateAroundAxisY(vector, Math.PI / 12);

        demons.values().removeIf(d -> {
            if(d.removed) {
                d.items.clear();
                return true;
            } else {
                d.update();
                return  false;
            }
        });

    }

    private Map<UUID, DemonAura> demons = new HashMap<>();

    @Override
    public void action(PlayerInteractEvent e) {
        super.action(e);
        if(e.getAction() == Action.LEFT_CLICK_AIR || e.getAction() == Action.LEFT_CLICK_BLOCK) {
            Player p = e.getPlayer();
            if(!demons.containsKey(p.getUniqueId())) {
                Set<Material> nullSet = null;
                Block target = p.getTargetBlock(nullSet, 15);
                if(target.getType() != Material.AIR) {
                    demons.put(p.getUniqueId(), new DemonAura().spawn(target.getLocation()));
                }

            }
        }
    }

    private static java.awt.Color black = new java.awt.Color(0,0,0);
    private static java.awt.Color purple = new java.awt.Color(181,0,255);

    private class DemonAura {

        private List<FlyingItem> items = new ArrayList<>();
        private double speed = 0.5F;
        private Location target;
        private Location location;
        private Vector direction;
        private boolean removed = false;
        public DemonAura() {

        }


        public DemonAura spawn(Location start) {
            this.target = start;
            this.location = start.clone().add(UtilMath.randInt(-10,10), 20,UtilMath.randInt(-10,10));
            this.direction = target.toVector().subtract(location.toVector()).normalize().multiply(0.4);
            return this;
        }

        public void update() {
            if(removed) return;

            location.add(direction);
            ParticleEffect.SMOKE_LARGE.display(location);

            double stepAngle = 2 * Math.PI / 4;
            for(int i = 0; i < 4; i++) {
                Vector v = VectorUtils.rotateAroundAxisY(vector, i * stepAngle);
                location.add(v);
                ParticleEffect.REDSTONE.display(location,new RegularColor(i % 2 == 0 ? black : purple));
                location.subtract(v);
            }

            if(location.distanceSquared(target) <= 2) {
                ParticleEffect.SMOKE_LARGE.display(location,1F,1F,1F,0.1F,100,null);
                ParticleEffect.SPELL_WITCH.display(location,1F,1F,1F,0.1F,50,null);

                removed = true;
            }
        }

    }


}
