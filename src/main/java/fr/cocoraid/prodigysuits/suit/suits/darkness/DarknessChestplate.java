package fr.cocoraid.prodigysuits.suit.suits.darkness;

import fr.cocoraid.prodigysuits.item.FlyingItem;
import fr.cocoraid.prodigysuits.suit.parts.Chestplate;
import fr.cocoraid.prodigysuits.utils.ItemBuilder;
import fr.cocoraid.prodigysuits.utils.UtilMath;
import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.util.Vector;

import java.util.ArrayList;
import java.util.List;

public class DarknessChestplate extends Chestplate {

    public DarknessChestplate() {
        super("darkness","darknesschestplate", new ItemBuilder(Material.LEATHER_CHESTPLATE).setDisplayName("§7Armure sombre").setColor(Color.BLACK));
    }

    private List<FlyingItem> items = new ArrayList<>();

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
                    Material.DRAGON_EGG,
                    UtilMath.randInt(1,2),
                    0,0,0,
                    FlyingItem.Size.EXTRA_SMALL,
                    UtilMath.randFloat(0.5F,2F),
                    UtilMath.randFloat(0.3f,1.5f),
                    UtilMath.randFloat(0,(float)(2*Math.PI)),
                    true,
                    false,
                    Math.PI / 60,
                    0.1f,
                    true,
                    40
            );

            FlyingItem item2 =  new FlyingItem(l,
                    Material.EYE_OF_ENDER,
                    UtilMath.randInt(1,2),
                    UtilMath.randInt(-180,180),UtilMath.randInt(-180,180),UtilMath.randInt(-180,180),
                    FlyingItem.Size.EXTRA_SMALL,
                    UtilMath.randFloat(0.5F,2F),
                    -0.5,
                    UtilMath.randFloat(0,(float)(2*Math.PI)),
                    false,
                    true,
                    Math.PI / 60,
                    0.05f,
                    false,
                    40
            );

            items.add(item);
            items.add(item2);
        }
    }
}
