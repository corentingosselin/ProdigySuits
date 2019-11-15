package fr.cocoraid.prodigysuits.suit.suits.glaciem;

import fr.cocoraid.prodigysuits.particle.ParticleEffect;
import fr.cocoraid.prodigysuits.particle.data.color.RegularColor;
import fr.cocoraid.prodigysuits.suit.parts.Boots;
import fr.cocoraid.prodigysuits.utils.ItemBuilder;
import org.bukkit.Location;
import org.bukkit.Material;

import java.awt.*;

public class GlaciemBoots extends Boots {

    public GlaciemBoots() {
        super("glaciem","glaciemboots",
                new ItemBuilder(Material.LEATHER_BOOTS)
                        .setDisplayName("§Bbotte glaciale")
                        .setColor(org.bukkit.Color.WHITE).setGlowing());
    }

    

    @Override
    public void asyncAnimate(Location location) {
        super.asyncAnimate(location);
        ParticleEffect.SPELL_MOB.display(location.clone().add(vectorA),
                new RegularColor(new Color(255, 255, 255)));

        ParticleEffect.REDSTONE.display(location.clone().add(vectorB),
                new RegularColor(new Color(0, 255, 255)));

        ParticleEffect.SPELL_INSTANT.display(location.clone().add(vectorC));


    }


}
