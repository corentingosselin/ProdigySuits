package fr.cocoraid.prodigysuits.suit.suits.aere;

import fr.cocoraid.prodigysuits.particle.ParticleEffect;
import fr.cocoraid.prodigysuits.particle.data.color.RegularColor;
import fr.cocoraid.prodigysuits.suit.parts.Legging;
import fr.cocoraid.prodigysuits.utils.ItemBuilder;
import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.Material;

public class AereLegging extends Legging {

    public AereLegging() {
        super("aere","aerelegging",new ItemBuilder(Material.LEATHER_LEGGINGS).setColor(Color.WHITE).setDisplayName("§fPantalon Aere"));
    }

    @Override
    public void asyncAnimate(Location location) {
        super.asyncAnimate(location);
        ParticleEffect.REDSTONE.display(location.clone().add(vector), new RegularColor(192,192,192));
    }
}
