package fr.cocoraid.prodigysuits.suit.suits;

import fr.cocoraid.prodigysuits.suit.PartSuit;
import fr.cocoraid.prodigysuits.suit.parts.Boots;
import fr.cocoraid.prodigysuits.suit.parts.Chestplate;
import fr.cocoraid.prodigysuits.suit.parts.Helmet;
import fr.cocoraid.prodigysuits.suit.parts.Legging;

public class SuitManager {


    private boolean boots,leggings,chestplate,helmet;

    public SuitManager( ) {
    }

    public void setAll(boolean all) {
        this.helmet = all;
        this.chestplate = all;
        this.leggings = all;
        this.boots = all;
    }

    public boolean isAll() {
        return helmet && chestplate && leggings && boots;
    }

    public void setEquipment(PartSuit part, boolean equip) {
        if(part instanceof Boots) this.boots = equip;
        else if(part instanceof Helmet) this.helmet = equip;
        else if(part instanceof Chestplate) this.chestplate = equip;
        else if(part instanceof Legging) this.leggings = equip;
    }



    public boolean isEquipped(PartSuit part) {
        if(part == null) return false;
        if(part instanceof Boots && boots) return true;
        else if(part instanceof Helmet && helmet) return true;
        else if(part instanceof Chestplate && chestplate) return true;
        else if(part instanceof Legging && leggings) return true;
        else return false;

    }
}
