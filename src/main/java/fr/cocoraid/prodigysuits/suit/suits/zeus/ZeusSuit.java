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

public class ZeusSuit extends Suit {

    public ZeusSuit() {
        super("zeus",new ZeusBoot(), new ZeusLegging(), new ZeusHelmet(), new ZeusChestplate());
    }


    @Override
    public void action(PlayerInteractEvent e) {
        super.action(e);
        if(e.getAction() == Action.LEFT_CLICK_AIR || e.getAction() == Action.LEFT_CLICK_BLOCK) {
            Player p = e.getPlayer();
            new CloudLightning(p.getLocation().add(UtilMath.randInt(-5,5),2,UtilMath.randInt(-5,5))).spawn();
            p.playSound(p.getLocation(), Sound.AMBIENCE_THUNDER,0.1F,1.5f);
        }
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

        public void spawn() {
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
