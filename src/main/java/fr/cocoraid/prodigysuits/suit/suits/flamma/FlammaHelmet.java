package fr.cocoraid.prodigysuits.suit.suits.flamma;

import fr.cocoraid.prodigysuits.particle.ParticleEffect;
import fr.cocoraid.prodigysuits.particle.data.color.RegularColor;
import fr.cocoraid.prodigysuits.suit.parts.Helmet;
import fr.cocoraid.prodigysuits.utils.ItemBuilder;
import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Item;

import java.util.concurrent.ThreadLocalRandom;

public class FlammaHelmet extends Helmet {

    public FlammaHelmet() {
        super("flamma","flammahelmet", new ItemBuilder(Material.LEATHER_HELMET).setGlowing().setColor(Color.RED));
    }

    @Override
    public void asyncAnimate(Location location) {
        super.asyncAnimate(location);
        ParticleEffect.CRIT.display(location.clone().add(vector));
    }
}
