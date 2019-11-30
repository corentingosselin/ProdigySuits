package fr.cocoraid.prodigysuits.suit.suits.zeus;

import fr.cocoraid.prodigysuits.particle.ParticleEffect;
import fr.cocoraid.prodigysuits.particle.data.color.RegularColor;
import fr.cocoraid.prodigysuits.suit.parts.Boots;
import fr.cocoraid.prodigysuits.utils.ItemBuilder;
import fr.cocoraid.prodigysuits.utils.UtilMath;
import fr.cocoraid.prodigysuits.utils.VectorUtils;
import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.util.Vector;

public class ZeusBoot extends Boots {

    public ZeusBoot() {
        super("zeus","zeusboots", new ItemBuilder(Material.LEATHER_BOOTS).setDisplayName("§6Bottes éclairs").setColor(Color.YELLOW));
    }


    private Vector vector = new Vector(1.5 * Math.cos(2*Math.PI), 0.3, 1.5 * Math.sin(2 * Math.PI));
    @Override
    public void asyncGlobal() {
        super.asyncGlobal();
        VectorUtils.rotateAroundAxisY(vector,Math.PI / 12);
    }

    @Override
    public void asyncAnimate(Location location) {
        super.asyncAnimate(location);

        Location loc = location.clone().add(vector);
        ParticleEffect.CLOUD.display(loc);
        ParticleEffect.REDSTONE.display(loc.clone().add(UtilMath.randFloat(-0.2f,0.2F),UtilMath.randFloat(-0.2f,0.2F),UtilMath.randFloat(-0.2f,0.2F) ),new RegularColor(new java.awt.Color(230, 255, 47)));

        Vector v = VectorUtils.rotateAroundAxisY(vector.clone(),Math.PI);
        Location loc2 = location.clone().add(v);
        ParticleEffect.CLOUD.display(loc2);
        ParticleEffect.REDSTONE.display(loc2.clone().add(UtilMath.randFloat(-0.2f,0.2F),UtilMath.randFloat(-0.2f,0.2F),UtilMath.randFloat(-0.2f,0.2F) ),new RegularColor(new java.awt.Color(0, 200, 255)));

    }
}
