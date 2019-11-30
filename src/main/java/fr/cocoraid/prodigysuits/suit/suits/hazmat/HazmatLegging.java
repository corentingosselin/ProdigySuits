package fr.cocoraid.prodigysuits.suit.suits.hazmat;

import fr.cocoraid.prodigysuits.particle.ParticleEffect;
import fr.cocoraid.prodigysuits.particle.data.color.RegularColor;
import fr.cocoraid.prodigysuits.suit.parts.Legging;
import fr.cocoraid.prodigysuits.utils.ItemBuilder;
import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.Material;

public class HazmatLegging extends Legging {

    public HazmatLegging() {
        super("hazmat","hazmatlegging", new ItemBuilder(Material.LEATHER_LEGGINGS).setGlowing().setColor(Color.YELLOW).setDisplayName("§e§lPantalon Hazmat"));
    }


    private static java.awt.Color black = new java.awt.Color(0,0,0);
    private static java.awt.Color yellow = new java.awt.Color(255, 255, 0);

    @Override
    public void asyncAnimate(Location location) {
        super.asyncAnimate(location);
        ParticleEffect.REDSTONE.display(location.clone().add(vector),new RegularColor(time % 2 == 0 ? black : yellow));
    }
}
