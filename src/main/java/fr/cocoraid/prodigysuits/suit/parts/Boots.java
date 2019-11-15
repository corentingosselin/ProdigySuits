package fr.cocoraid.prodigysuits.suit.parts;

import fr.cocoraid.prodigysuits.suit.PartSuit;
import fr.cocoraid.prodigysuits.utils.ItemBuilder;
import fr.cocoraid.prodigysuits.utils.VectorUtils;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

import java.util.ArrayList;
import java.util.List;

public class Boots extends PartSuit {

    public Boots(String suitName, String partName, ItemBuilder armor) {
        super(suitName,partName, armor);
    }

    protected Vector vectorA = new Vector(0.5 * Math.cos(Math.PI / 2), 0.1, 0.5 * Math.sin(Math.PI / 2));
    protected Vector vectorB = new Vector(-Math.cos(Math.PI / 40), 0.4, -Math.sin(Math.PI / 40));
    protected Vector vectorC = new Vector(1.5 * Math.cos(Math.PI / 22), 0.6, 1.5 * Math.sin(Math.PI / 22));

    private List<Vector> torotate = new ArrayList<>();
    {
        torotate.add(vectorA);
        torotate.add(vectorB);
        torotate.add(vectorC);
    }

    @Override
    public void asyncGlobal() {
        super.asyncGlobal();
        torotate.forEach(v -> {
            VectorUtils.rotateAroundAxisY(v,Math.PI / 10);
        });
    }

    @Override
    public void equip(Player p) {
        super.equip(p);
        p.getInventory().setBoots(armor.getItem());
    }

}
