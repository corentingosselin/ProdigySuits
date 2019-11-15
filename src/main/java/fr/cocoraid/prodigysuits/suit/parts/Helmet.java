package fr.cocoraid.prodigysuits.suit.parts;

import fr.cocoraid.prodigysuits.suit.PartSuit;
import fr.cocoraid.prodigysuits.utils.ItemBuilder;
import fr.cocoraid.prodigysuits.utils.UtilMath;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

public class Helmet extends PartSuit {

    public Helmet(String suitName, String partName, ItemBuilder armor) {
        super(suitName,partName, armor);
    }

    protected Vector vector = new Vector(0,0,0);

    @Override
    public void equip(Player p) {
        super.equip(p);
        p.getInventory().setHelmet(armor.getItem());
    }

    @Override
    public void asyncGlobal() {
        super.asyncGlobal();
        vector = new Vector(UtilMath.randFloat(-1f,1F),UtilMath.randFloat(0f,2F) , UtilMath.randFloat(-1f,1F));
    }
}
