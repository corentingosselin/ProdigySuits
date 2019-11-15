package fr.cocoraid.prodigysuits.suit.suits.terra;

import fr.cocoraid.prodigysuits.suit.FlyingItem;
import fr.cocoraid.prodigysuits.suit.parts.Chestplate;
import fr.cocoraid.prodigysuits.utils.ItemBuilder;
import fr.cocoraid.prodigysuits.utils.UtilMath;
import fr.cocoraid.prodigysuits.utils.VectorUtils;
import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

import java.util.*;

public class TerraChestplate extends Chestplate {

    public TerraChestplate() {
        super("terra","terrachestplate",new ItemBuilder(Material.LEATHER_CHESTPLATE).setColor(Color.MAROON).setDisplayName("§6Armure Terra"));
    }

    private Map<UUID,List<FlyingItem>> items = new HashMap<>();


    @Override
    public void asyncAnimate(Location location) {
        super.asyncAnimate(location);
    }

    public void extractRetract(Player p) {
        List<FlyingItem> list = items.get(p.getUniqueId());
        int amount = list.size();

        double angle = 0;
        double stepAngle = 2 * Math.PI / amount;
        for(int i = 0; i < amount; i++) {
            FlyingItem item = list.get(i);
            Vector dir = new Vector(angle, 0, angle);
            item.setDirection(dir);
            item.setStayTime(3);
            item.addListener(new FlyingItem.ItemListener() {
                @Override
                public void onRemove() {

                }

                @Override
                public void onUpdate() {
                    if(item.getStayTime() <= 1 && item.getDirection().equals(dir)) {
                        item.setDirection(dir.multiply(-1));
                        item.setStayTime(2);
                    }
                }
            });
            item.setDirectionSpeed(0.2f);
            angle+=stepAngle;
        }

    }




    @Override
    public void asyncGlobalAnimate(Player p) {
        super.asyncGlobalAnimate(p);
        if(items.containsKey(p.getUniqueId())) {
            items.get(p.getUniqueId()).removeIf(i -> {
                if (i.isExisting()) {
                    i.update(p.getLocation());
                    return false;
                } else {
                    return true;
                }
            });
        }


        if(time % 10 == 0) {

            Vector vector = new Vector(UtilMath.randFloat(-2f,2F), 0.1 , UtilMath.randFloat(-2f,2F));
            Location l = p.getLocation().add(vector);
            Material material = Material.DIRT;
            if(l.getBlock().getRelative(BlockFace.DOWN).getType() != Material.AIR)
                material = l.getBlock().getRelative(BlockFace.DOWN).getType();

            FlyingItem item =  new FlyingItem(l,
                    material,
                    UtilMath.randInt(3,5 ),
                    0,0,0,
                    FlyingItem.Size.EXTRA_SMALL,
                    UtilMath.randFloat(0.5F,2F),
                    0.5,
                    UtilMath.randFloat(0,(float)(2*Math.PI)),
                    true,
                    false,
                    Math.PI / 60,
                    0f,
                    true,
                    40
            );

            items.putIfAbsent(p.getUniqueId(),new ArrayList<>());
            items.get(p.getUniqueId()).add(item);
        }

    }


    @Override
    public void asyncGlobal() {
        super.asyncGlobal();

    }
}
