package fr.cocoraid.prodigysuits.suit.suits.hazmat;

import com.google.common.collect.Iterables;
import fr.cocoraid.prodigysuits.particle.ParticleEffect;
import fr.cocoraid.prodigysuits.particle.data.color.RegularColor;
import fr.cocoraid.prodigysuits.suit.parts.Boots;
import fr.cocoraid.prodigysuits.suit.suits.rainbow.RainbowSuit;
import fr.cocoraid.prodigysuits.utils.ItemBuilder;
import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.Material;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class HazmatBoots extends Boots {

    public HazmatBoots() {
        super("hazmat","hazmatboots", new ItemBuilder(Material.LEATHER_BOOTS).setGlowing().setColor(Color.YELLOW).setDisplayName("§e§lBottes hazmat"));
    }

    private static List<java.awt.Color> colors = new ArrayList<>();
    {
        for (int rg = 255; rg < 0; rg--) colors.add(new java.awt.Color(rg,rg,0));
        for (int rg = 0; rg < 255; rg++) colors.add(new java.awt.Color(rg,rg,0));


    }
    private static Iterator<java.awt.Color> infinite = Iterables.cycle(colors).iterator();

    @Override
    public void asyncAnimate(Location location) {
        super.asyncAnimate(location);
        ParticleEffect.REDSTONE.display(location.clone().add(vectorA),
                new RegularColor(infinite.next()));

        ParticleEffect.SPELL_MOB.display(location.clone().add(vectorB),
                new RegularColor(infinite.next()));


        ParticleEffect.REDSTONE.display(location.clone().add(vectorC),
                new RegularColor(infinite.next()));



    }


}
