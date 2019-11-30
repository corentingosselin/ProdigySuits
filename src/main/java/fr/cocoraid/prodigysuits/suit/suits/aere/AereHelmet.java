package fr.cocoraid.prodigysuits.suit.suits.aere;

import fr.cocoraid.prodigysuits.particle.ParticleEffect;
import fr.cocoraid.prodigysuits.suit.parts.Helmet;
import fr.cocoraid.prodigysuits.utils.ItemBuilder;
import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.Material;

public class AereHelmet extends Helmet {

    public AereHelmet() {
        super("aere","aerehelmet", new ItemBuilder(Material.LEATHER_HELMET).setColor(Color.WHITE).setDisplayName("§fCasque Aere"));
    }

    @Override
    public void asyncAnimate(Location location) {
        super.asyncAnimate(location);
        ParticleEffect.SPELL.display(location.clone().add(vector),0 ,0,0,0,1,null);
    }
}
