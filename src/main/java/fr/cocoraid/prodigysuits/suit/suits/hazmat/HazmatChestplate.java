package fr.cocoraid.prodigysuits.suit.suits.hazmat;

import fr.cocoraid.prodigysuits.item.FlyingItem;
import fr.cocoraid.prodigysuits.suit.parts.Chestplate;
import fr.cocoraid.prodigysuits.utils.ItemBuilder;
import fr.cocoraid.prodigysuits.utils.UtilMath;
import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Vector;

import java.util.ArrayList;
import java.util.List;

public class HazmatChestplate extends Chestplate {

    public HazmatChestplate() {
        super("hazmat","hazmatchestplate",new ItemBuilder(Material.LEATHER_CHESTPLATE).setColor(Color.YELLOW).setGlowing().setDisplayName("§e§lCombinaison hazmat"));
    }


    private List<FlyingItem> items = new ArrayList<>();

    public List<FlyingItem> getItems() {
        return items;
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
                    new ItemStack(Material.WOOL,1,(short) 15),
                    UtilMath.randInt(1,2),
                    0,0,0,
                    FlyingItem.Size.EXTRA_SMALL,
                    UtilMath.randFloat(0.5F,2F),
                    0,
                    UtilMath.randFloat(0,(float)(2*Math.PI)),
                    true,
                    true,
                    Math.PI / 60,
                    0.1f,
                    true,
                    40
            );

            FlyingItem item2 =  new FlyingItem(l,
                    new ItemStack(Material.WOOL,1,(short) 4),
                    UtilMath.randInt(1,2),
                    UtilMath.randInt(-180,180),UtilMath.randInt(-180,180),UtilMath.randInt(-180,180),
                    FlyingItem.Size.EXTRA_SMALL,
                    UtilMath.randFloat(0.5F,2F),
                    -1,
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
