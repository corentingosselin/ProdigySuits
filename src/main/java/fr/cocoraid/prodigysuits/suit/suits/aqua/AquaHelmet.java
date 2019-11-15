package fr.cocoraid.prodigysuits.suit.suits.aqua;

import fr.cocoraid.prodigysuits.particle.ParticleEffect;
import fr.cocoraid.prodigysuits.particle.data.color.RegularColor;
import fr.cocoraid.prodigysuits.suit.parts.Helmet;
import fr.cocoraid.prodigysuits.utils.ItemBuilder;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;

import java.awt.*;
import java.util.concurrent.ThreadLocalRandom;

public class AquaHelmet extends Helmet {

    public AquaHelmet() {
        super("aqua","aquahelmet",
                new ItemBuilder(Material.LEATHER_HELMET)
                        .setColor(org.bukkit.Color.AQUA)
                        .setDisplayName("§bCasque aquatique"));
    }

    @Override
    public void equip(Player p) {
        super.equip(p);
        p.getInventory().setHelmet(armor.getItem());
    }


    @Override
    public void asyncAnimate(Location location) {
        super.asyncAnimate(location);
        int randomG = ThreadLocalRandom.current().nextInt(0, 255 + 1);
        ParticleEffect.REDSTONE.display(location.clone().add(vector), new RegularColor(new Color(0, randomG, 255)));
    }
}
