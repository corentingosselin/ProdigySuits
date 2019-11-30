package fr.cocoraid.prodigysuits.suit.suits.hazmat;

import fr.cocoraid.prodigysuits.ProdigyPlayer;
import fr.cocoraid.prodigysuits.particle.ParticleEffect;
import fr.cocoraid.prodigysuits.suit.Suit;
import fr.cocoraid.prodigysuits.suit.suits.SuitManager;
import fr.cocoraid.prodigysuits.suit.suits.aqua.AquaChestplate;
import fr.cocoraid.prodigysuits.suit.suits.rainbow.RainbowSuit;
import fr.cocoraid.prodigysuits.utils.VectorUtils;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.util.Vector;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class HazmatSuit extends Suit {

    public HazmatSuit() {
        super("hazmat",new HazmatBoots(), new HazmatLegging(), new HazmatHelmet(), new HazmatChestplate());
    }

    private Map<UUID,DecontaminationWave> waves = new HashMap<>();

    @Override
    public void asyncGlobalAnimate(SuitManager manager, Location location) {
        if(waves.containsKey(manager.getOwner())) {
            if(waves.get(manager.getOwner()).end)
                waves.remove(manager.getOwner());
            else waves.get(manager.getOwner()).update(location);

            return;
        }
        super.asyncGlobalAnimate(manager, location);
    }


    @Override
    public void action(PlayerInteractEvent e) {
        super.action(e);
        if(e.getAction() == Action.LEFT_CLICK_AIR || e.getAction() == Action.LEFT_CLICK_BLOCK) {
            Player p = e.getPlayer();
            if(!waves.containsKey(p.getUniqueId())) {
                waves.put(p.getUniqueId(), new DecontaminationWave(p.getLocation()));
                ProdigyPlayer pp = ProdigyPlayer.instanceOfPlayer(p);
                HazmatChestplate chestplate = (HazmatChestplate) getParts().get("hazmatchestplate");
                if(pp.getManager().isEquipped(chestplate)) {
                    chestplate.getItems().forEach(i -> i.remove());
                    chestplate.getItems().clear();
                }
            }
        }
    }

    private class DecontaminationWave {

        private double radius = 0;
        private int time = 20 * 5;
        private boolean end = false;

        public DecontaminationWave(Location l) {
            l.getWorld().playSound(l, Sound.FIREWORK_LAUNCH,1F,0F);
        }

        public void update(Location location) {
            if(end) return;
            if(radius < 0) {
                time--;
                if(time <= 0) end = true;
                return;
            }
            for(int k = 0; k < radius * 10; k++) {
                Vector v = VectorUtils.getRandomVector().multiply(radius);
                Location l = location.clone().add(v);
                ParticleEffect.CLOUD.display(l,0.1F,0.1F,0.1F,0.01F,1,null);
            }
            radius+=0.5;
            if(radius >= 5) {
                radius = -1;
            }
        }
    }
}
