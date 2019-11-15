package fr.cocoraid.prodigysuits.suit.suits.terra;

import fr.cocoraid.prodigysuits.particle.ParticleEffect;
import fr.cocoraid.prodigysuits.particle.data.color.RegularColor;
import fr.cocoraid.prodigysuits.suit.parts.Legging;
import fr.cocoraid.prodigysuits.utils.ItemBuilder;
import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.Material;

public class TerraLegging extends Legging {

    public TerraLegging() {
        super("terra","terralegging", new ItemBuilder(Material.LEATHER_LEGGINGS).setColor(Color.MAROON).setDisplayName("§6Pantalon terra"));
    }

    @Override
    public void asyncAnimate(Location location) {
        super.asyncAnimate(location);
        Location l = location.clone().add(vector);
        ParticleEffect.REDSTONE.display(l,new RegularColor(100,53,0));

    }
}
