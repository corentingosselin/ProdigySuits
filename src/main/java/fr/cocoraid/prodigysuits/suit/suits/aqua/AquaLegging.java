package fr.cocoraid.prodigysuits.suit.suits.aqua;

import fr.cocoraid.prodigysuits.particle.ParticleEffect;
import fr.cocoraid.prodigysuits.particle.data.color.RegularColor;
import fr.cocoraid.prodigysuits.suit.parts.Legging;
import fr.cocoraid.prodigysuits.utils.ItemBuilder;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;

import java.awt.*;

public class AquaLegging extends Legging {

    public AquaLegging() {
        super("aqua","aqualegging",
                new ItemBuilder(Material.LEATHER_LEGGINGS)
                .setColor(org.bukkit.Color.AQUA)
                .setDisplayName("§bPentalon aquatique"));
    }

    @Override
    public void asyncGlobal() {
        super.asyncGlobal();
        if(!loop) {
            g++;
            if(g >= 255) {
                g=255;
                loop = true;
            }
        } else {
            g--;
            if(g <= 0) {
                g=0;
                loop = false;
            }
        }
    }

    private int g = 0;
    private boolean loop = false;
    @Override
    public void asyncAnimate(Location location) {
        super.asyncAnimate(location);
        ParticleEffect.SPELL_MOB.display(location.clone().add(vector),
                new RegularColor(new Color(0, g, 255)), Bukkit.getOnlinePlayers());


    }
}
