package fr.cocoraid.prodigysuits.suit.suits.aqua;

import fr.cocoraid.prodigysuits.particle.ParticleEffect;
import fr.cocoraid.prodigysuits.particle.data.color.RegularColor;
import fr.cocoraid.prodigysuits.suit.parts.Boots;
import fr.cocoraid.prodigysuits.utils.ItemBuilder;
import org.bukkit.Location;
import org.bukkit.Material;

import java.awt.*;

public class AquaBoot extends Boots {


    public AquaBoot() {
        super("aqua","aquaboots",
                new ItemBuilder(Material.LEATHER_BOOTS)
                        .setColor(org.bukkit.Color.AQUA)
                        .setDisplayName("§bChaussures aquatique"));
    }


    @Override
    public void asyncAnimate(Location location) {
        super.asyncAnimate(location);
        ParticleEffect.SPELL_MOB.display(location.clone().add(vectorA),
                new RegularColor(new Color(0, 255, 255)));

        ParticleEffect.CRIT_MAGIC.display(location.clone().add(vectorB));

        ParticleEffect.SPELL_MOB.display(location.clone().add(vectorC),
                new RegularColor(new Color(0, 255, 255)));



    }
}
