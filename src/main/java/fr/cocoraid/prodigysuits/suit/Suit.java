package fr.cocoraid.prodigysuits.suit;

import fr.cocoraid.prodigysuits.ProdigyPlayer;
import fr.cocoraid.prodigysuits.ProdigySuits;
import fr.cocoraid.prodigysuits.suit.parts.Chestplate;
import fr.cocoraid.prodigysuits.suit.parts.Helmet;
import fr.cocoraid.prodigysuits.suit.parts.Legging;
import fr.cocoraid.prodigysuits.suit.suits.Parts;
import fr.cocoraid.prodigysuits.suit.suits.SuitManager;
import fr.cocoraid.prodigysuits.suit.suits.aere.AereSuit;
import fr.cocoraid.prodigysuits.suit.suits.aqua.AquaSuit;
import fr.cocoraid.prodigysuits.suit.suits.darkness.DarknessSuit;
import fr.cocoraid.prodigysuits.suit.suits.flamma.FlammaSuit;
import fr.cocoraid.prodigysuits.suit.suits.glaciem.GlaciemSuit;
import fr.cocoraid.prodigysuits.suit.suits.hazmat.HazmatSuit;
import fr.cocoraid.prodigysuits.suit.suits.poisonous.PoisonousSuit;
import fr.cocoraid.prodigysuits.suit.suits.rainbow.RainbowSuit;
import fr.cocoraid.prodigysuits.suit.suits.terra.TerraSuit;
import fr.cocoraid.prodigysuits.suit.suits.zeus.ZeusSuit;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
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
        register(new AereSuit());
        register(new FlammaSuit());
        register(new DarknessSuit());
        register(new PoisonousSuit());
        register(new ZeusSuit());
        register(new RainbowSuit());
        register(new HazmatSuit());
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
        ProdigyPlayer pp = ProdigyPlayer.instanceOfPlayer(manager.getOwner());
        parts.values().stream().filter(part -> manager.isEquipped(part)).forEach(part -> {
            if(pp.isPlayerMoving() && (part instanceof Helmet || part instanceof Chestplate || part instanceof Legging))  {
                part.remove(pp.getPlayer());
                return;
            }
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
        parts.values().forEach(part -> part.remove(p));
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
