package fr.cocoraid.prodigysuits.suit.suits.aere;

import fr.cocoraid.prodigysuits.particle.ParticleEffect;
import fr.cocoraid.prodigysuits.suit.Suit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.util.Vector;

import java.util.*;

public class AereSuit extends Suit  {




    public AereSuit() {
        super("aere", new AereBoots(), new AereLegging(), new AereHelmet(), new AereChestplate());
    }

    private Map<UUID, Tornado> tornadoes = new HashMap<>();

    @Override
    public void action(PlayerInteractEvent e) {
        super.action(e);
        if(e.getAction() == Action.LEFT_CLICK_AIR || e.getAction() == Action.LEFT_CLICK_BLOCK) {
            Player p = e.getPlayer();
            if(!tornadoes.containsKey(p.getUniqueId())) {
                tornadoes.put(p.getUniqueId(), new Tornado(p.getEyeLocation()));
            }

        }
    }

    @Override
    public void asyncGlobal() {
        super.asyncGlobal();
        tornadoes.keySet().removeIf(uuid -> {
            if(tornadoes.get(uuid).getDistance() <= 0) {
                return true;
            } else {
                tornadoes.get(uuid).update();
                return false;
            }
        });
    }

    private class Tornado {

        private Location location;
        private double speed = 0.4D;
        public Tornado(Location start) {
            this.location = start;
        }


        private int i = 0;
        private double distance = 15;
        public void update() {
            distance-=speed;
            location.add(location.getDirection().multiply(speed));
            ParticleEffect.CLOUD.display(location);
            Vector v =  new Vector(1 *  Math.cos(i * Math.PI / 6), 0 , 1 *  Math.sin(i * Math.PI / 6));
            ParticleEffect.FIREWORKS_SPARK.display(location.clone().add(v));
            i++;
        }

        public double getDistance() {
            return distance;
        }
    }
}
