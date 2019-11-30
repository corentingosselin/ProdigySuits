package fr.cocoraid.prodigysuits.suit.suits.flamma;

import fr.cocoraid.prodigysuits.particle.ParticleEffect;
import fr.cocoraid.prodigysuits.suit.parts.Boots;
import fr.cocoraid.prodigysuits.utils.ItemBuilder;
import fr.cocoraid.prodigysuits.utils.UtilMath;
import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.util.Vector;

public class FlammaBoot extends Boots {

    public FlammaBoot() {
        super("flamma","flammaboots",new ItemBuilder(Material.LEATHER_BOOTS).setColor(Color.RED).setGlowing().setDisplayName("§4Bottes enflammées"));
    }

    @Override
    public void asyncAnimate(Location location) {
        super.asyncAnimate(location);
        Vector vector = new Vector(UtilMath.randFloat(-2f,2F), 0.1 , UtilMath.randFloat(-2f,2F));
        ParticleEffect.SMOKE_LARGE.display(location.clone().add(vector));
        if(UtilMath.randInt(0,100) <= 40)
            ParticleEffect.LAVA.display(location.clone().add(vector));

    }
}
