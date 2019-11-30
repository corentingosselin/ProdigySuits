package fr.cocoraid.prodigysuits.suit.suits.darkness;

import fr.cocoraid.prodigysuits.particle.ParticleEffect;
import fr.cocoraid.prodigysuits.particle.data.color.RegularColor;
import fr.cocoraid.prodigysuits.suit.parts.Boots;
import fr.cocoraid.prodigysuits.utils.ItemBuilder;
import fr.cocoraid.prodigysuits.utils.UtilMath;
import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.util.Vector;

public class DarknessBoot extends Boots {


    public DarknessBoot() {
        super("darkness","darknessboots", new ItemBuilder(Material.LEATHER_BOOTS).setColor(Color.BLACK).setDisplayName("§7Bottes sombres"));
    }


    @Override
    public void asyncAnimate(Location location) {
        super.asyncAnimate(location);

        ParticleEffect.SMOKE_LARGE.display(location.clone().subtract(0,0.3,0).add(vectorC));


        Vector vector2 = new Vector(UtilMath.randFloat(-2f,2F), 0.1 , UtilMath.randFloat(-2f,2F));
        ParticleEffect.SPELL_MOB.display(location.clone().add(vector2),
                new RegularColor(new java.awt.Color(0, 0, 0)));

        Vector vector3 = new Vector(UtilMath.randFloat(-2f,2F), 0.1 , UtilMath.randFloat(-2f,2F));
        ParticleEffect.SPELL_WITCH.display(location.clone().add(vector3));



    }

}
