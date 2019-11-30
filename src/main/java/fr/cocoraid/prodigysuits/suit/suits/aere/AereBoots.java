package fr.cocoraid.prodigysuits.suit.suits.aere;

import fr.cocoraid.prodigysuits.particle.ParticleEffect;
import fr.cocoraid.prodigysuits.suit.parts.Boots;
import fr.cocoraid.prodigysuits.utils.ItemBuilder;
import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.Material;

public class AereBoots extends Boots {

    public AereBoots() {
        super("aere","aereboots", new ItemBuilder(Material.LEATHER_BOOTS).setColor(Color.WHITE).setDisplayName("§fBotte Aere"));
    }


    @Override
    public void asyncAnimate(Location location) {
        super.asyncAnimate(location);
        torotate.forEach(v -> {
            ParticleEffect.CLOUD.display(location.clone().add(v));
        });

    }
}
