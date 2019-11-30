package fr.cocoraid.prodigysuits.suit.suits.zeus;

import fr.cocoraid.prodigysuits.particle.ParticleEffect;
import fr.cocoraid.prodigysuits.particle.data.color.RegularColor;
import fr.cocoraid.prodigysuits.suit.parts.Helmet;
import fr.cocoraid.prodigysuits.utils.ItemBuilder;
import org.bukkit.Location;
import org.bukkit.util.Vector;

import java.awt.*;

public class ZeusHelmet extends Helmet {

    public ZeusHelmet() {
        super("zeus","zeushelmet", ItemBuilder.Head.ZEUS.setDisplayName("§fTête de Zeus"));
    }

    @Override
    public void asyncAnimate(Location location) {
        super.asyncAnimate(location);
        Vector v = vector.clone().setX(vector.getX() * 2).setZ(vector.getZ() * 2);
        if(time % 2 == 0)
            ParticleEffect.SPELL_INSTANT.display(location.clone().add(v));
        else
            ParticleEffect.REDSTONE.display(location.clone().add(v),new RegularColor(Color.WHITE));
    }
}
