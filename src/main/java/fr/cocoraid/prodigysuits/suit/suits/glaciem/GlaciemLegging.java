package fr.cocoraid.prodigysuits.suit.suits.glaciem;

import fr.cocoraid.prodigysuits.particle.ParticleEffect;
import fr.cocoraid.prodigysuits.suit.parts.Legging;
import fr.cocoraid.prodigysuits.utils.ItemBuilder;
import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.Material;

public class GlaciemLegging extends Legging {

    public GlaciemLegging() {
        super("glaciem","glaciemlegging", new ItemBuilder(Material.LEATHER_LEGGINGS)
                .setColor(Color.WHITE)
                .setGlowing()
                .setDisplayName("Pantalon glaciale"));
    }

    @Override
    public void asyncAnimate(Location location) {
        super.asyncAnimate(location);
        ParticleEffect.SNOW_SHOVEL.display(location.clone().add(vector)
                ,0.01F,0.01F,0.01F,0.05F,5,null);

    }
}
