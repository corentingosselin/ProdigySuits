package fr.cocoraid.prodigysuits.suit.parts;

import fr.cocoraid.prodigysuits.suit.PartSuit;
import fr.cocoraid.prodigysuits.utils.ItemBuilder;
import fr.cocoraid.prodigysuits.utils.VectorUtils;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

import java.util.ArrayList;
import java.util.List;

public class Chestplate extends PartSuit {

    public Chestplate(String suitName, String partName, ItemBuilder armor) {
        super(suitName,partName, armor);
    }


    protected Vector vectorA = new Vector(0.5 * Math.cos(Math.PI / 2), 0.1, 0.5 * Math.sin(Math.PI / 2));
    protected Vector vectorB = new Vector(-Math.cos(Math.PI / 40), 0.4, -Math.sin(Math.PI / 40));
    protected Vector vectorC = new Vector(1.5 * Math.cos(Math.PI / 130), 0.6, 1.5 * Math.sin(Math.PI / 100));

    private List<Vector> torotate = new ArrayList<>();
    {
        torotate.add(vectorA);
        torotate.add(vectorB);
        torotate.add(vectorC);
    }

    @Override
    public void asyncGlobal() {
        super.asyncGlobal();
        VectorUtils.rotateAroundAxisY(vectorA,Math.PI / 80);
            VectorUtils.rotateAroundAxisY(vectorB,Math.PI / 120);
        VectorUtils.rotateAroundAxisY(vectorC,Math.PI / 100);

    }

    @Override
    public void equip(Player p) {
        super.equip(p);
        p.getInventory().setChestplate(armor.getItem());
    }



}
