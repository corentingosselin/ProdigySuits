package fr.cocoraid.prodigysuits.suit.suits.zeus;

import fr.cocoraid.prodigysuits.particle.ParticleEffect;
import fr.cocoraid.prodigysuits.particle.data.color.RegularColor;
import fr.cocoraid.prodigysuits.suit.parts.Legging;
import fr.cocoraid.prodigysuits.utils.ItemBuilder;
import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.Material;

public class ZeusLegging extends Legging {

    public ZeusLegging() {
        super("zeus","zeuslegging", new ItemBuilder(Material.LEATHER_LEGGINGS).setColor(Color.WHITE).setDisplayName("§fPantalon de §6Zeus"));
    }

    private static java.awt.Color yellow = new java.awt.Color(230, 255, 47);
    private static java.awt.Color blue = new java.awt.Color(0, 247, 255);

    @Override
    public void asyncAnimate(Location location) {
        super.asyncAnimate(location);
        ParticleEffect.CLOUD.display(location.clone().add(vector));
    }
}
