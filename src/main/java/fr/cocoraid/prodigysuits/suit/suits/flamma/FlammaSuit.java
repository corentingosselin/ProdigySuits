package fr.cocoraid.prodigysuits.suit.suits.flamma;

import com.comphenix.protocol.wrappers.EnumWrappers;
import com.google.common.collect.Iterables;
import fr.cocoraid.prodigysuits.ProdigyPlayer;
import fr.cocoraid.prodigysuits.item.FiredItem;
import fr.cocoraid.prodigysuits.packet.wrapper.entity.WrapperEntityArmorStand;
import fr.cocoraid.prodigysuits.particle.ParticleEffect;
import fr.cocoraid.prodigysuits.suit.Suit;
import fr.cocoraid.prodigysuits.suit.parts.Chestplate;
import fr.cocoraid.prodigysuits.suit.parts.Legging;
import fr.cocoraid.prodigysuits.suit.suits.SuitManager;
import fr.cocoraid.prodigysuits.utils.ItemBuilder;
import fr.cocoraid.prodigysuits.utils.VectorUtils;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.LeatherArmorMeta;
import org.bukkit.util.Vector;

import java.awt.*;
import java.util.List;
import java.util.*;

public class FlammaSuit extends Suit {

    public FlammaSuit() {
        super("flamma", new FlammaBoot(), new FlammaHelmet(),new FlammaLegging(), new FlammaChestplate());
    }

    private Map<UUID,MiniBlaze> blazes = new HashMap<>();
    @Override
    public void equip(Player p) {
        super.equip(p);
        blazes.put(p.getUniqueId(),new MiniBlaze(p.getLocation()).spawn());

    }

    @Override
    public void unequip(Player p) {
        super.unequip(p);
        if(blazes.containsKey(p.getUniqueId())) {
            blazes.get(p.getUniqueId()).remove();
        }
    }

    private static List<java.awt.Color> colors = new ArrayList<>();
    {
        for (int g = 0; g < 255; g++) colors.add(new java.awt.Color(255, g, 0));
        for (int g = 255; g < 0; g--) colors.add(new java.awt.Color(255, g, 0));

        colors.add(new java.awt.Color(0, 255, 0));
    }
    private static Iterator<Color> infinite = Iterables.cycle(colors).iterator();

    @Override
    public void asyncGlobalAnimate(SuitManager manager, Location location) {
        if(!manager.isAll())
            super.asyncGlobalAnimate(manager, location);
        else {
            parts.values().stream().filter(part -> manager.isEquipped(part) && !(part instanceof Chestplate) && !(part instanceof Legging)).forEach(part -> {
                part.asyncAnimate(location);
            });
        }
    }

    @Override
    public void asyncGlobalAnimate(Player p) {
        super.asyncGlobalAnimate(p);
        if(blazes.containsKey(p.getUniqueId()))
            blazes.get(p.getUniqueId()).update(p.getLocation());

        if(ProdigyPlayer.instanceOfPlayer(p).getManager().isAll()) {
            Color c = infinite.next();
            int slot = 0;
            ItemStack[] armors = p.getInventory().getArmorContents();
            for (ItemStack armor : armors) {
                if (armor.getItemMeta() instanceof LeatherArmorMeta) {
                    LeatherArmorMeta meta = (LeatherArmorMeta) armor.getItemMeta();
                    meta.setColor(org.bukkit.Color.fromBGR(c.getRed(), c.getGreen(), c.getBlue()));
                    armor.setItemMeta(meta);
                    armors[slot] = armor;
                    slot++;
                }
            }
            p.getInventory().setArmorContents(armors);
        }
    }

    @Override
    public void action(PlayerInteractEvent e) {
        super.action(e);
        if(e.getAction() == Action.LEFT_CLICK_AIR || e.getAction() == Action.LEFT_CLICK_BLOCK) {
            Player p = e.getPlayer();
            if(blazes.containsKey(p.getUniqueId())) {
                new FiredItem(blazes.get(p.getUniqueId()).location, 2);
            }
        }
    }


    private Vector vectorAtom = new Vector(2 * Math.cos(2 * Math.PI), 0, 2 * Math.sin(2 * Math.PI));
    private Vector vectorLeg = new Vector(0.5 * Math.cos(2 * Math.PI), 0, 0.5 * Math.sin(2 * Math.PI));

    @Override
    public void asyncGlobal() {
        super.asyncGlobal();

        vectorAtom = VectorUtils.rotateAroundAxisX(vectorAtom,Math.PI / 100);
        VectorUtils.rotateAroundAxisY(vectorAtom,Math.PI / 64);

        VectorUtils.rotateAroundAxisY(vectorLeg,Math.PI / 12);

    }

    private class MiniBlaze {

        private WrapperEntityArmorStand head;
        private List<WrapperEntityArmorStand> legs = new ArrayList<>();
        private Location location;
        public MiniBlaze(Location start) {
            this.location = start;
        }

        public MiniBlaze spawn() {

            this.head = new WrapperEntityArmorStand(location);
            head.setInvisible(true);
            head.setSmall(true);
            head.setMarker(true);
            head.setOnFire(true);
            head.spawnFar();
            head.equip(EnumWrappers.ItemSlot.HEAD, ItemBuilder.Head.BLAZE);
            head.sendUpdatedmetatada();

            double radius = 0.3;
            int amount = 3;
            double step =  2 * Math.PI / amount;
            for(int i = 0; i < amount; i++) {
                double angle = i * step;
                Vector v = new Vector(radius * Math.cos(angle), -0.5,radius * Math.sin(angle));
                Location l = location.clone().add(v);
                l.setDirection(v);
                WrapperEntityArmorStand leg = new WrapperEntityArmorStand(location.clone().add(v));
                leg.setInvisible(true);
                leg.setSmall(true);
                leg.setMarker(true);
                leg.setRightArmPose(180,280,90);
                leg.spawnFar();
                leg.equip(EnumWrappers.ItemSlot.MAINHAND, new ItemStack(Material.BLAZE_ROD));
                l.setDirection(location.toVector().subtract(l.toVector()));
                leg.updateYaw(l);
                leg.sendUpdatedmetatada();
                legs.add(leg);
            }

            return this;
        }






        public void update(Location current) {


            Location loc = current.clone().add(vectorAtom);
            ParticleEffect.SMOKE_LARGE.display(loc.clone().add(0,0.5,0));
            head.teleport(loc);


            legs.forEach(l -> {
                l.teleport(head.getLocation().clone().add(l.getLocation().getDirection()));
            });

            //rotate legs
            int i = 0;
            for (WrapperEntityArmorStand leg : legs) {
                Vector v = VectorUtils.rotateAroundAxisY(vectorLeg.clone(),i * (( 2 * Math.PI ) / 3));
                leg.teleport(head.getLocation().clone().subtract(0,0.5,0).add(v));
                i++;
            }

            this.location = loc;

        }

        public void remove() {
            if(head != null) {
                head.despawn();
                legs.forEach(l -> l.despawn());
            }
        }

    }
}
