package fr.cocoraid.prodigysuits.suit.suits.darkness;

import fr.cocoraid.prodigysuits.particle.ParticleEffect;
import fr.cocoraid.prodigysuits.suit.parts.Helmet;
import fr.cocoraid.prodigysuits.utils.ItemBuilder;
import fr.cocoraid.prodigysuits.utils.UtilMath;
import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.util.Vector;

public class DarknessHelmet extends Helmet {

    public DarknessHelmet() {
        super("darkness","darknesshelmet", new ItemBuilder(Material.LEATHER_HELMET).setColor(Color.BLACK).setDisplayName("§7Casque sombre"));
    }



    private static java.awt.Color black = new java.awt.Color(0,0,0);
    private static java.awt.Color purple = new java.awt.Color(181,0,255);
    private static java.awt.Color purple_light = new java.awt.Color(255,0, 229);


    @Override
    public void asyncAnimate(Location location) {
        super.asyncAnimate(location);
        Vector vector = new Vector(UtilMath.randFloat(-2f,2F),UtilMath.randFloat(0f,2F) , UtilMath.randFloat(-2f,2F));
        if(UtilMath.randInt(0,1) == 0) {
            ParticleEffect.REDSTONE.display(location.clone().add(vector), UtilMath.randInt(0,1) == 0 ? purple : purple_light);
        } else {
            ParticleEffect.REDSTONE.display(location.clone().add(vector), black);
        }

    }

}
