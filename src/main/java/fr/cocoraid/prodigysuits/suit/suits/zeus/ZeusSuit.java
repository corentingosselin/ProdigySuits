package fr.cocoraid.prodigysuits.suit.suits.zeus;

import fr.cocoraid.prodigysuits.particle.ParticleEffect;
import fr.cocoraid.prodigysuits.particle.data.color.RegularColor;
import fr.cocoraid.prodigysuits.suit.Suit;
import fr.cocoraid.prodigysuits.utils.UtilMath;
import fr.cocoraid.prodigysuits.utils.VectorUtils;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.util.Vector;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class ZeusSuit extends Suit {

    public ZeusSuit() {
        super("zeus",new ZeusBoot(), new ZeusLegging(), new ZeusHelmet(), new ZeusChestplate());
    }


    private Map<UUID,CloudLightning> clouds = new HashMap<>();

    @Override
    public void action(PlayerInteractEvent e) {
        super.action(e);
        if(e.getAction() == Action.LEFT_CLICK_AIR || e.getAction() == Action.LEFT_CLICK_BLOCK) {
            Player p = e.getPlayer();


            if(clouds.containsKey(p.getUniqueId())) {
                if(!clouds.get(p.getUniqueId()).end)
                    return;
                else
                    clouds.remove(p.getUniqueId());
            }
            clouds.put(p.getUniqueId() , new CloudLightning(p.getLocation()));
            p.playSound(p.getLocation(), Sound.AMBIENCE_THUNDER,0.5F,1.5f);

        }
    }

    @Override
    public void asyncGlobal() {
        super.asyncGlobal();
        clouds.values().stream().filter(c -> !c.end).forEach(c -> c.update());
    }

    public class CloudLightning {

        private Location location;
        public CloudLightning(Location location) {
            this.location = location;
        }

        private Vector getVector() {
            Vector dir = VectorUtils.getRandomCircleVector();
            dir.setY(-1);
            dir.normalize();
            dir.multiply(0.1F);
            return dir;
        }

        private int number = 10;
        private boolean end = false;
        private int time = 0;
        public void update() {
            if(end) return;
            if(number < 0) {
                time++;
                if(time >= 20 * 5) {
                    end = true;
                }
                return;
            }
            number--;
            spawn();
        }

        public void spawn() {
            Location location = this.location.clone().add(UtilMath.randInt(-5,5),2,UtilMath.randInt(-5,5));
            ParticleEffect.CLOUD.display(location,0.2F,0.2F,0.2F,0F,20,null);
            Location loc = location.clone();
            Vector v = getVector();
            for(int i = 0; i < 10; i++) {
                loc.add(v);
                ParticleEffect.REDSTONE.display(loc,new RegularColor(Color.YELLOW));
            }
            for(int k = 0 ; k < UtilMath.randInt(1,4);k++) {
                v = getVector();
                for (int i = 0; i < 10; i++) {
                    loc.add(v);
                    ParticleEffect.REDSTONE.display(loc, new RegularColor(Color.YELLOW));
                }
            }
        }
    }
}
