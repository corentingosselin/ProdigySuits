package fr.cocoraid.prodigysuits.suit;

import fr.cocoraid.prodigysuits.ProdigyPlayer;
import fr.cocoraid.prodigysuits.ProdigySuits;
import fr.cocoraid.prodigysuits.suit.suits.SuitManager;
import fr.cocoraid.prodigysuits.suit.suits.aqua.AquaSuit;
import fr.cocoraid.prodigysuits.suit.suits.glaciem.GlaciemSuit;
import fr.cocoraid.prodigysuits.suit.suits.terra.TerraSuit;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.PlayerInventory;

import java.util.HashMap;
import java.util.Map;

public abstract class Suit implements Listener {

    private static Map<String, Suit> suits = new HashMap<>();

    private static void register(Suit suit) {
        suits.put(suit.name,suit);
    }

    public static void registerSuits() {
        register(new AquaSuit());
        register(new GlaciemSuit());
        register(new TerraSuit());
    }

    /**
     * Better system with ids maybe
     */
    protected Map<String, PartSuit> parts = new HashMap<>();
    private String name;
    public Suit(String name, PartSuit... part) {
        this.name = name;
        //register parts suit
        for (PartSuit partSuit : part) {
            parts.put(partSuit.partName, partSuit);
        }
        Bukkit.getPluginManager().registerEvents(this, ProdigySuits.getInstance());
    }


    @EventHandler
    public void interact(PlayerInteractEvent e) {
        ProdigyPlayer pp = ProdigyPlayer.instanceOfPlayer(e.getPlayer());
        if(pp.getManager().isAll() && pp.getSuit().getClass().equals(this.getClass()))
            action(e);
    }

    public void action(PlayerInteractEvent e) {

    }

    public void asyncGlobalAnimate(Player p) {
        asyncGlobalAnimate(ProdigyPlayer.instanceOfPlayer(p).getManager(),p.getLocation());
    }

    public void asyncGlobalAnimate(SuitManager manager, Location location) {
        parts.values().stream().filter(part -> manager.isEquipped(part)).forEach(part -> {
            part.asyncAnimate(location);
        });
    }

    public void asyncGlobal() {

    }

    public void equip(Player p) {
        ProdigyPlayer pp = ProdigyPlayer.instanceOfPlayer(p);
        for (PartSuit part : parts.values()) {
            if(pp.getManager().isEquipped(part))
                part.equip(p);
        }
    }



    public void unequip(Player p) {
        PlayerInventory inv = p.getInventory();
        inv.setHelmet(null);
        inv.setChestplate(null);
        inv.setLeggings(null);
        inv.setBoots(null);
    }


    public static Map<String, Suit> getSuits() {
        return suits;
    }

    public String getName() {
        return name;
    }

    public Map<String, PartSuit> getParts() {
        return parts;
    }
}
