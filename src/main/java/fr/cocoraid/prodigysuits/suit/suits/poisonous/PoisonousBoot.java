package fr.cocoraid.prodigysuits.suit.suits.poisonous;

import fr.cocoraid.prodigysuits.particle.ParticleEffect;
import fr.cocoraid.prodigysuits.particle.data.color.RegularColor;
import fr.cocoraid.prodigysuits.suit.parts.Boots;
import fr.cocoraid.prodigysuits.utils.ItemBuilder;
import fr.cocoraid.prodigysuits.utils.UtilMath;
import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.util.Vector;

public class PoisonousBoot extends Boots {

    public PoisonousBoot() {
        super("poisnous","poisonousboots", new ItemBuilder(Material.LEATHER_BOOTS).setColor(Color.OLIVE).setDisplayName("§2Bottes empoisonnées"));
    }


    @Override
    public void asyncAnimate(Location location) {
        super.asyncAnimate(location);

        ParticleEffect.SPELL_MOB.display(location.clone().add(vectorA),
                new RegularColor(new java.awt.Color(33, 158, 4)));

        ParticleEffect.REDSTONE.display(location.clone().add(vectorB),
                new RegularColor(new java.awt.Color(49, 242, 5)));

        ParticleEffect.SPELL_MOB.display(location.clone().add(vectorC),
                new RegularColor(new java.awt.Color(49, 242, 5)));

    }
}
