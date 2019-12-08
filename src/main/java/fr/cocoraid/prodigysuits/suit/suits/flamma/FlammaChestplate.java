package fr.cocoraid.prodigysuits.suit.suits.flamma;

import fr.cocoraid.prodigysuits.item.FiredItem;
import fr.cocoraid.prodigysuits.item.FlyingItem;
import fr.cocoraid.prodigysuits.suit.parts.Chestplate;
import fr.cocoraid.prodigysuits.utils.ItemBuilder;
import fr.cocoraid.prodigysuits.utils.UtilMath;
import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

import java.util.ArrayList;
import java.util.List;

public class FlammaChestplate extends Chestplate {

    public FlammaChestplate() {
        super("flamma","flammachestplate", new ItemBuilder(Material.LEATHER_CHESTPLATE).setGlowing().setColor(Color.RED).setDisplayName("§4Armure enflammée"));
    }

    private List<FlyingItem> items = new ArrayList<>();
    @Override
    public void remove(Player p) {
        super.remove(p);
        items.forEach(i -> i.remove());
        items.clear();
    }

    @Override
    public void asyncAnimate(Location location) {
        super.asyncAnimate(location);

        items.removeIf(i -> {
            if (i.isExisting()) {
                i.update(location);
                return false;
            } else {
                return true;
            }
        });

        if(time % 10 == 0) {

            Vector vector = new Vector(UtilMath.randFloat(-2f,2F), 0.1 , UtilMath.randFloat(-2f,2F));
            Location l = location.clone().add(vector);


            FlyingItem item =  new FlyingItem(l,
                    Material.BLAZE_ROD,
                    UtilMath.randInt(1,2),
                    0,0,0,
                    FlyingItem.Size.EXTRA_SMALL,
                    UtilMath.randFloat(0.5F,2F),
                    -0.5,
                    UtilMath.randFloat(0,(float)(2*Math.PI)),
                    true,
                    true,
                    Math.PI / 60,
                    0.1f,
                    true,
                    40
            );

            new FiredItem(l.clone().add(0,1,0), 2);


            items.add(item);
        }
    }

}
