package fr.cocoraid.prodigysuits.suit.suits.rainbow;

import fr.cocoraid.prodigysuits.particle.ParticleEffect;
import fr.cocoraid.prodigysuits.particle.data.color.RegularColor;
import fr.cocoraid.prodigysuits.suit.parts.Legging;
import fr.cocoraid.prodigysuits.utils.ItemBuilder;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;

import java.awt.*;

public class RainbowLegging extends Legging {

    public RainbowLegging() {
        super("rainbow","rainbowlegging", new ItemBuilder(Material.LEATHER_LEGGINGS).setDisplayName("§dPantalon Arc-En-Ciel"));
    }

    @Override
    public void asyncAnimate(Location location) {
        super.asyncAnimate(location);
        ParticleEffect.REDSTONE.display(location.clone().add(vector),
                new RegularColor(RainbowSuit.getInfinite().next()), Bukkit.getOnlinePlayers());


    }
}
