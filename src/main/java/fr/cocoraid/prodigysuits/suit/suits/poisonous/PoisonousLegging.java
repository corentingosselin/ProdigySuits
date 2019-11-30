package fr.cocoraid.prodigysuits.suit.suits.poisonous;

import fr.cocoraid.prodigysuits.particle.ParticleEffect;
import fr.cocoraid.prodigysuits.particle.data.color.RegularColor;
import fr.cocoraid.prodigysuits.suit.parts.Legging;
import fr.cocoraid.prodigysuits.utils.ItemBuilder;
import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.Material;

public class PoisonousLegging extends Legging {

    public PoisonousLegging() {
        super("poisonous", "poisonouslegging", new ItemBuilder(Material.LEATHER_LEGGINGS).setColor(Color.OLIVE).setDisplayName("§2Pantalon empoisonné"));
    }

    private static java.awt.Color black = new java.awt.Color(0,0,0);
    private static java.awt.Color green = new java.awt.Color(0, 255, 63);

    @Override
    public void asyncAnimate(Location location) {
        super.asyncAnimate(location);
        ParticleEffect.REDSTONE.display(location.clone().add(vector),new RegularColor(time % 2 == 0 ? black : green));
    }

}
