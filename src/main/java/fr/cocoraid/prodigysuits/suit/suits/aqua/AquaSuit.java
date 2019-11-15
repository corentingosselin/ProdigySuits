package fr.cocoraid.prodigysuits.suit.suits.aqua;

import fr.cocoraid.prodigysuits.packet.wrapper.packet.WrapperPlayServerNamedSoundEffect;
import fr.cocoraid.prodigysuits.suit.Suit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

public class AquaSuit extends Suit {

    public AquaSuit() {
        super("aqua", new AquaBoot(), new AquaLegging(), new AquaHelmet(),new AquaChestplate());
    }

    @Override
    public void asyncGlobal() {
        super.asyncGlobal();
    }

    @Override
    public void action(PlayerInteractEvent e) {
        super.action(e);
        if(e.getAction() == Action.LEFT_CLICK_AIR || e.getAction() == Action.LEFT_CLICK_BLOCK) {
            Location l = e.getPlayer().getLocation();
            l.setPitch(0);
            AquaChestplate chestplate = (AquaChestplate) getParts().get("aquachestplate");
            chestplate.goTo(l.getDirection());
            Player p = e.getPlayer();
            WrapperPlayServerNamedSoundEffect sound = new WrapperPlayServerNamedSoundEffect();
            sound.setEffectPositionX(p.getLocation().getX());
            sound.setEffectPositionY(p.getLocation().getY());
            sound.setEffectPositionZ(p.getLocation().getZ());
            sound.setSoundName("game.neutral.swim");
            sound.setPitch(2);
            sound.setVolume(0.3F);
            sound.sendPacket(p);
        }
    }
}
