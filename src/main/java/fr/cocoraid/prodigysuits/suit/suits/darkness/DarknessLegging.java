package fr.cocoraid.prodigysuits.suit.suits.darkness;

import fr.cocoraid.prodigysuits.particle.ParticleEffect;
import fr.cocoraid.prodigysuits.suit.parts.Legging;
import fr.cocoraid.prodigysuits.utils.ItemBuilder;
import org.bukkit.Location;
import org.bukkit.Material;

public class DarknessLegging extends Legging {

    public DarknessLegging() {
        super("darkness","darknesslegging", new ItemBuilder(Material.LEATHER_LEGGINGS).setColor(org.bukkit.Color.BLACK).setDisplayName("§7Jambières sombres"));
    }

    @Override
    public void asyncAnimate(Location location) {
        super.asyncAnimate(location);
        ParticleEffect.SPELL_WITCH.display(location.clone().add(vector));

    }
}
