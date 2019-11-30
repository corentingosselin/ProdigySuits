package fr.cocoraid.prodigysuits.suit.suits.rainbow;

import fr.cocoraid.prodigysuits.particle.ParticleEffect;
import fr.cocoraid.prodigysuits.particle.data.color.RegularColor;
import fr.cocoraid.prodigysuits.suit.parts.Helmet;
import fr.cocoraid.prodigysuits.utils.ItemBuilder;
import org.bukkit.Location;
import org.bukkit.Material;

public class RainbowHelmet extends Helmet {

    public RainbowHelmet() {
        super("rainbow","rainbowhelmet", new ItemBuilder(Material.LEATHER_HELMET).setDisplayName("§dCasque Arc-En-Ciel"));
    }

    @Override
    public void asyncAnimate(Location location) {
        super.asyncAnimate(location);
        ParticleEffect.SPELL_MOB.display(location.clone().add(vector), new RegularColor(RainbowSuit.getInfinite().next()));
    }
}
