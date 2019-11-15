package fr.cocoraid.prodigysuits.suit.suits.glaciem;

import fr.cocoraid.prodigysuits.suit.Suit;
import org.bukkit.entity.Player;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

public class GlaciemSuit extends Suit {

    public GlaciemSuit() {
        super("glaciem",new GlaciemBoots(), new GlaciemLegging(), new GlaciemHelmet(), new GlaciemChestplate());
    }

    @Override
    public void action(PlayerInteractEvent e) {
        super.action(e);
        if(e.getAction() == Action.LEFT_CLICK_AIR || e.getAction() == Action.LEFT_CLICK_BLOCK) {
            Player p = e.getPlayer();
            GlaciemChestplate chestplate = (GlaciemChestplate) getParts().get("glaciemchestplate");
            chestplate.launch(p);

        }
    }




}
