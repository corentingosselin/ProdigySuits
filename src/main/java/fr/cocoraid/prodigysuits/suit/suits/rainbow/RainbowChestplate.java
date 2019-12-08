package fr.cocoraid.prodigysuits.suit.suits.rainbow;

import fr.cocoraid.prodigysuits.item.FlyingItem;
import fr.cocoraid.prodigysuits.suit.parts.Chestplate;
import fr.cocoraid.prodigysuits.utils.ItemBuilder;
import fr.cocoraid.prodigysuits.utils.UtilMath;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Vector;

import java.util.ArrayList;
import java.util.List;

public class RainbowChestplate extends Chestplate {

    public RainbowChestplate() {
        super("rainbow","rainbowchestplate",new ItemBuilder(Material.LEATHER_CHESTPLATE).setDisplayName("§dArmure Arc-En-Ciel"));
    }


    private int[] colors = new int[] {14,10,9,4,5,2,1};

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

            int c = colors[UtilMath.randInt(0,colors.length - 1)];
            FlyingItem item2 =  new FlyingItem(l,
                    new ItemStack(Material.WOOL,1,(short) c),
                    UtilMath.randInt(2,4),
                    UtilMath.randInt(-180,180),UtilMath.randInt(-180,180),UtilMath.randInt(-180,180),
                    FlyingItem.Size.EXTRA_SMALL,
                    UtilMath.randFloat(0.5F,2F),
                    -1,
                    UtilMath.randFloat(0,(float)(2*Math.PI)),
                    true,
                    true,
                    Math.PI / 60,
                    0.05f,
                    false,
                    40
            );
            items.add(item2);
        }
    }
}
