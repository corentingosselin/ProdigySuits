package fr.cocoraid.prodigysuits.suit.parts;

import fr.cocoraid.prodigysuits.suit.PartSuit;
import fr.cocoraid.prodigysuits.utils.ItemBuilder;
import fr.cocoraid.prodigysuits.utils.MathUtils;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

public class Legging extends PartSuit {


    private int particles = 500;
    private int particlesPerIteration = 10;
    private float size = 1F;
    private float xFactor = 1F, yFactor = 1F, zFactor = 1F;
    private float xOffset, yOffset = 1.5F, zOffset;
    private int step = 0;

    protected Vector vector = new Vector();

    public Legging(String suitName, String partName, ItemBuilder armor) {
        super(suitName,partName, armor);
    }

    @Override
    public void equip(Player p) {
        super.equip(p);
        p.getInventory().setLeggings(armor.getItem());
    }

    @Override
    public void asyncGlobal() {
        super.asyncGlobal();
        for (int i = 0; i < particlesPerIteration; i++) {
            step++;

            float t = (MathUtils.PI / particles) * step;
            float r = MathUtils.sin(t) * size;
            float s = 2 * MathUtils.PI * t;

            vector.setX(xFactor * r * MathUtils.cos(s) + xOffset);
            vector.setZ(zFactor * r * MathUtils.sin(s) + zOffset);
            vector.setY(yFactor * size * MathUtils.cos(t) + yOffset);

        }
        step%=999999;

    }


    @Override
    public void asyncAnimate(Location location) {
        super.asyncAnimate(location);
    }
}
