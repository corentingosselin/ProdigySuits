package fr.cocoraid.prodigysuits.suit.suits.flamma;

import fr.cocoraid.prodigysuits.particle.ParticleEffect;
import fr.cocoraid.prodigysuits.suit.parts.Legging;
import fr.cocoraid.prodigysuits.utils.ItemBuilder;
import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.Material;

public class FlammaLegging extends Legging {

    public FlammaLegging() {
        super("flamma","flammalegging", new ItemBuilder(Material.LEATHER_LEGGINGS).setColor(Color.RED).setGlowing().setDisplayName("§4Pantalon enflammé"));
    }

    @Override
    public void asyncAnimate(Location location) {
        super.asyncAnimate(location);
        ParticleEffect.SPELL_MOB.display(location.clone().add(vector));
    }
}
