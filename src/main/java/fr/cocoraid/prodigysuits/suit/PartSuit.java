package fr.cocoraid.prodigysuits.suit;

import fr.cocoraid.prodigysuits.utils.ItemBuilder;
import org.bukkit.Location;
import org.bukkit.entity.Player;

public abstract class PartSuit extends Suit {

    protected String partName;
    protected ItemBuilder armor;

    public PartSuit(String suitName, String partName, ItemBuilder armor) {
        super(suitName);
        this.partName = partName;
        this.armor = armor;
    }

    public void asyncAnimate(Location location) {

    }

    protected int time = 0;
    @Override
    public void asyncGlobal() {
        super.asyncGlobal();
        time++;
        time%=99999999;
    }

   public void remove(Player p) {

   }


    public ItemBuilder getArmor() {
        return armor;
    }
}
