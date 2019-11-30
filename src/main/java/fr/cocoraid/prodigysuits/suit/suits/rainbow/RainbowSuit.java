package fr.cocoraid.prodigysuits.suit.suits.rainbow;

import com.google.common.collect.Iterables;
import fr.cocoraid.prodigysuits.item.FlyingItem;
import fr.cocoraid.prodigysuits.particle.ParticleEffect;
import fr.cocoraid.prodigysuits.particle.data.color.RegularColor;
import fr.cocoraid.prodigysuits.suit.Suit;
import fr.cocoraid.prodigysuits.suit.suits.terra.TerraChestplate;
import fr.cocoraid.prodigysuits.utils.ItemBuilder;
import fr.cocoraid.prodigysuits.utils.UtilMath;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.LeatherArmorMeta;

import java.awt.*;
import java.util.*;
import java.util.List;

public class RainbowSuit extends Suit {

    public RainbowSuit() {
        super("rainbow", new RainbowBoots(), new RainbowLegging(), new RainbowHelmet(), new RainbowChestplate());
    }

    private static List<java.awt.Color> colors = new ArrayList<>();
    {
        for (int r = 0; r < 100; r++) colors.add(new java.awt.Color(r * 255 / 100, 255, 0));
        for (int g = 100; g > 0; g--) colors.add(new java.awt.Color(255, g * 255 / 100, 0));
        for (int b = 0; b < 100; b++) colors.add(new java.awt.Color(255, 0, b * 255 / 100));
        for (int r = 100; r > 0; r--) colors.add(new java.awt.Color(r * 255 / 100, 0, 255));
        for (int g = 0; g < 100; g++) colors.add(new java.awt.Color(0, g * 255 / 100, 255));
        for (int b = 100; b > 0; b--) colors.add(new java.awt.Color(0, 255, b * 255 / 100));
        colors.add(new java.awt.Color(0, 255, 0));
    }
    private static Iterator<Color> infinite = Iterables.cycle(colors).iterator();

    @Override
    public void asyncGlobalAnimate(Player p) {
        super.asyncGlobalAnimate(p);

        Color c = infinite.next();

        int slot = 0;
        ItemStack[] armors = p.getInventory().getArmorContents();
        for (ItemStack armor : armors) {
            if(armor.getItemMeta() instanceof LeatherArmorMeta) {
                LeatherArmorMeta meta = (LeatherArmorMeta) armor.getItemMeta();
                meta.setColor(org.bukkit.Color.fromBGR(c.getRed(),c.getGreen(),c.getBlue()));
                armor.setItemMeta(meta);
                armors[slot] = armor;
                slot++;
            }
        }
        p.getInventory().setArmorContents(armors);

    }

    public static Iterator<Color> getInfinite() {
        return infinite;
    }


    private Map<UUID,NyanCat> cats = new HashMap<>();
    @Override
    public void action(PlayerInteractEvent e) {
        super.action(e);
        if(e.getAction() == Action.LEFT_CLICK_AIR || e.getAction() == Action.LEFT_CLICK_BLOCK) {
            Player p = e.getPlayer();
            if(!cats.containsKey(p.getUniqueId())) {
                NyanCat cat = new NyanCat(p.getEyeLocation().add(0,0.5,0));
                cats.put(p.getUniqueId(), cat);
            }
        }
    }

    @Override
    public void asyncGlobal() {
        super.asyncGlobal();
        cats.keySet().removeIf(uuid -> {
            if(cats.get(uuid).nyancat ==null) return true;
            else {
                cats.get(uuid).update();
                return false;
            }
        });
    }

    private Color[] color = new Color[] {
            new Color(181,0, 255),
            Color.CYAN,
            Color.YELLOW,
            Color.RED
    };
    private class NyanCat {
        private FlyingItem nyancat ;

        private Location location;
        public NyanCat(Location location) {
            this.location = location;
            this.nyancat = new FlyingItem(location,
                    ItemBuilder.Head.NYANCAT,
                    3,0,0,0,
                    FlyingItem.Size.MEDIUM,
                    0,0,0,
                    false,false,0,
                    0,false,
                    0);
            nyancat.setDirection(location.getDirection().setY(0));
            nyancat.setDirectionSpeed(0.2);
            nyancat.addListener(new FlyingItem.ItemListener() {
                @Override
                public void onRemove() {
                    Location l = nyancat.getCurrentLocation().clone().add(0,0.5,0);
                    for(int k = 0; k < 40; k++) {
                        for (int i = 0; i < 4; i++) {
                            ParticleEffect.SPELL_MOB.display(l, color[i]);
                        }
                    }
                    ParticleEffect.CLOUD.display(l, 0.5F,0.5F,0.5F,0.05F,20,null);
                    nyancat = null;
                }

                @Override
                public void onUpdate() {

                }
            });
        }

        public void update() {
            if(nyancat != null) nyancat.update(location);
            Location l = nyancat.getCurrentLocation().clone().add(0,0.65,0);
            l.add(nyancat.getDirection().clone().multiply(-1.05));
            for(int i = 0; i < 4; i++) {
                l.add(0,0.1,0);
                ParticleEffect.REDSTONE.display(l,color[i]);
            }
            l = nyancat.getCurrentLocation().clone().add(0,0.65,0);
            l.add(nyancat.getDirection().clone().multiply(-1.03));
            for(int i = 0; i < 4; i++) {
                l.add(0,0.1,0);
                ParticleEffect.REDSTONE.display(l,color[i]);
            }

        }
    }


}
