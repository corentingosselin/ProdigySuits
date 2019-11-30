package fr.cocoraid.prodigysuits.suit.suits.poisonous;

import fr.cocoraid.prodigysuits.particle.ParticleEffect;
import fr.cocoraid.prodigysuits.particle.data.color.RegularColor;
import fr.cocoraid.prodigysuits.suit.parts.Helmet;
import fr.cocoraid.prodigysuits.utils.ItemBuilder;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.util.Vector;

public class PoisonousHelmet extends Helmet {


    public PoisonousHelmet() {
        super("poisonous","poisonoushelmet",new ItemBuilder(Material.SLIME_BLOCK).setDisplayName("§2Casque empoisonné"));
    }


    @Override
    public void asyncAnimate(Location location) {
        super.asyncAnimate(location);
        Vector v = vector.clone().setX(vector.getX() * 2).setZ(vector.getZ() * 2);
        if(time % 2 == 0)
            ParticleEffect.VILLAGER_HAPPY.display(location.clone().add(v));
        else
            ParticleEffect.REDSTONE.display(location.clone().add(v),new RegularColor(java.awt.Color.BLACK));
    }
}
