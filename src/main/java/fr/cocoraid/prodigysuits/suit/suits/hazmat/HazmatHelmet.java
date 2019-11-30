package fr.cocoraid.prodigysuits.suit.suits.hazmat;

import fr.cocoraid.prodigysuits.particle.ParticleEffect;
import fr.cocoraid.prodigysuits.suit.parts.Helmet;
import fr.cocoraid.prodigysuits.utils.ItemBuilder;
import fr.cocoraid.prodigysuits.utils.UtilMath;
import fr.cocoraid.prodigysuits.utils.VectorUtils;
import org.bukkit.Location;
import org.bukkit.util.Vector;

public class HazmatHelmet extends Helmet {

    public HazmatHelmet() {
        super("hazmat","hazmathelmet", ItemBuilder.Head.HAZMAT.setDisplayName("§e§lCasque hazmat"));
    }

    @Override
    public void asyncAnimate(Location location) {
        super.asyncAnimate(location);
        Vector v = VectorUtils.getRandomCircleVector().multiply(UtilMath.randFloat(0,4));
        ParticleEffect.VILLAGER_HAPPY.display(location.clone().add(v));
    }
}
