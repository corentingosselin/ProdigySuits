package fr.cocoraid.prodigysuits.suit.suits.terra;

import fr.cocoraid.prodigysuits.suit.Suit;
import fr.cocoraid.prodigysuits.suit.suits.glaciem.GlaciemChestplate;
import org.bukkit.entity.Player;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

public class TerraSuit extends Suit {

    public TerraSuit() {
        super("terra",new TerraBoots(), new TerraLegging(), new TerraHelmet(), new TerraChestplate());
    }

    @Override
    public void action(PlayerInteractEvent e) {
        super.action(e);
        if(e.getAction() == Action.LEFT_CLICK_AIR || e.getAction() == Action.LEFT_CLICK_BLOCK) {
            Player p = e.getPlayer();
            TerraChestplate chestplate = (TerraChestplate) getParts().get("terrachestplate");
            chestplate.extractRetract(p);

        }
    }
}
