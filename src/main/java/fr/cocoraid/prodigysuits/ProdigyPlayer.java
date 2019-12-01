package fr.cocoraid.prodigysuits;

import fr.cocoraid.prodigysuits.suit.PartSuit;
import fr.cocoraid.prodigysuits.suit.Suit;
import fr.cocoraid.prodigysuits.suit.suits.SuitManager;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class ProdigyPlayer {

    private static Map<UUID,ProdigyPlayer> prodigyPlayers = new HashMap<>();

    private Suit suit;
    private SuitManager manager;

    public void equip(String suitName) {
        if(suit != null) {
            unEquip();
            if(suit.getName().equalsIgnoreCase(suitName)) {
                return;
            } else {
                this.suit = Suit.getSuits().get(suitName);
                manager.setAll(true);
                suit.equip(player);
            }
        } else {
            manager.setAll(true);
            this.suit = Suit.getSuits().get(suitName);
            suit.equip(player);
        }
    }

    public void equip(String suitName, String partName) {
        if(suit != null) {
            if (!suitName.equalsIgnoreCase(suit.getName())) {
                suit.unequip(player);
                manager.setAll(false);
            }
        }
        this.suit = Suit.getSuits().get(suitName);
        if(!suit.getParts().containsKey(partName)) {
            //invalid part name, does not exists for this suit
            return;
        }
        PartSuit part = suit.getParts().get(partName);
        if(manager.isEquipped(part)) {
            part.unequip(player);
            manager.setEquipment(part,false);
            return;
        }
        manager.setEquipment(part, true);
        part.equip(player);
    }

    public void unEquip() {
        if(suit == null) return;
        suit.unequip(player);
        manager.setAll(false);
        this.suit = null;
    }

    private Player player;
    public ProdigyPlayer(Player player) {
        this.player = player;
        manager = new SuitManager(player.getUniqueId());
    }

    public static ProdigyPlayer instanceOfPlayer(Player p) {
        if(!prodigyPlayers.containsKey(p.getUniqueId()))
            prodigyPlayers.put(p.getUniqueId(), new ProdigyPlayer(p));
        return prodigyPlayers.get(p.getUniqueId());
    }

    public static Map<UUID, ProdigyPlayer> getProdigyPlayers() {
        return prodigyPlayers;
    }

    public SuitManager getManager() {
        return manager;
    }

    public Suit getSuit() {
        return suit;
    }

    public Player getPlayer() {
        return player;
    }
}
