package fr.cocoraid.prodigysuits.suit.suits.glaciem;

import fr.cocoraid.prodigysuits.particle.ParticleEffect;
import fr.cocoraid.prodigysuits.particle.data.color.RegularColor;
import fr.cocoraid.prodigysuits.suit.parts.Helmet;
import fr.cocoraid.prodigysuits.utils.ItemBuilder;
import org.bukkit.Location;
import org.bukkit.Material;

import java.awt.*;
import java.util.concurrent.ThreadLocalRandom;

public class GlaciemHelmet extends Helmet {

    public GlaciemHelmet() {
        super("glaciem","glaciemhelmet",
                new ItemBuilder(Material.PACKED_ICE).setDisplayName("§bCasque Glaciale"));
    }

    @Override
    public void asyncAnimate(Location location) {
        super.asyncAnimate(location);
        int r = ThreadLocalRandom.current().nextInt(0, 255 + 1);
        ParticleEffect.REDSTONE.display(location.clone().add(vector), new RegularColor(new Color(r, 255, 255)));

    }
}
