package fr.cocoraid.prodigysuits.suit.suits.terra;

import fr.cocoraid.prodigysuits.particle.ParticleEffect;
import fr.cocoraid.prodigysuits.particle.data.color.RegularColor;
import fr.cocoraid.prodigysuits.suit.parts.Helmet;
import fr.cocoraid.prodigysuits.utils.ItemBuilder;
import fr.cocoraid.prodigysuits.utils.UtilMath;
import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.Material;

import java.util.concurrent.ThreadLocalRandom;

public class TerraHelmet extends Helmet {

    public TerraHelmet() {
        super("terra","terrahelmet",new ItemBuilder(Material.LEATHER_HELMET).setColor(Color.MAROON).setDisplayName("§6Casque Terra"));
    }

    @Override
    public void asyncAnimate(Location location) {
        super.asyncAnimate(location);
        int r = UtilMath.randInt(0,100);
        if(r <= 10)
            ParticleEffect.SPELL_MOB.display(location.clone().add(vector),new RegularColor(UtilMath.randInt(100,145),UtilMath.randInt(53,76),0));
        else if (r <= 50)
            ParticleEffect.VILLAGER_HAPPY.display(location.clone().add(vector));
        else
            ParticleEffect.CRIT.display(location.clone().add(vector));
    }
}
