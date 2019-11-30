package fr.cocoraid.prodigysuits.suit.suits.rainbow;

import fr.cocoraid.prodigysuits.particle.ParticleEffect;
import fr.cocoraid.prodigysuits.particle.data.color.RegularColor;
import fr.cocoraid.prodigysuits.suit.parts.Boots;
import fr.cocoraid.prodigysuits.utils.ItemBuilder;
import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.Material;

public class RainbowBoots extends Boots {

    public RainbowBoots() {
        super("rainbow","rainbowboots", new ItemBuilder(Material.LEATHER_BOOTS).setDisplayName("§dBootes Arc-En-Ciel"));
    }

    @Override
    public void asyncAnimate(Location location) {
        super.asyncAnimate(location);
        ParticleEffect.REDSTONE.display(location.clone().add(vectorA),
             new RegularColor(RainbowSuit.getInfinite().next()));

        ParticleEffect.REDSTONE.display(location.clone().add(vectorB),
                new RegularColor(RainbowSuit.getInfinite().next()));


        ParticleEffect.REDSTONE.display(location.clone().add(vectorC),
                new RegularColor(RainbowSuit.getInfinite().next()));



    }

}
