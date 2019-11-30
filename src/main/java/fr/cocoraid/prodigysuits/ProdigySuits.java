package fr.cocoraid.prodigysuits;

import fr.cocoraid.prodigysuits.suit.Suit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

public class ProdigySuits extends JavaPlugin {


    private static ProdigySuits instance;

    public static ProdigySuits getInstance() {
        return instance;
    }

    @Override
    public void onEnable() {
        instance = this;

        Suit.registerSuits();

        new BukkitRunnable() {
            @Override
            public void run() {

                Suit.getSuits().values().forEach(s -> {
                    s.asyncGlobal();
                    s.getParts().values().forEach(p -> {
                        p.asyncGlobal();
                    });
                });

                ProdigyPlayer.getProdigyPlayers().values().forEach(pp -> {
                    if(pp.getSuit() != null) {
                        pp.getSuit().asyncGlobalAnimate(pp.getPlayer());
                        pp.getSuit().getParts().values().forEach(part -> part.asyncGlobalAnimate(pp.getPlayer()));
                    }
                });

            }
        }.runTaskTimerAsynchronously(this,0,0);
    }


    @Override
    public void onDisable() {

    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(sender instanceof Player) {
            Player p = (Player) sender;
            if(args.length == 0) {
                ProdigyPlayer pp = ProdigyPlayer.instanceOfPlayer(p);
                pp.equip("hazmat");
            }

            if(args.length == 1) {
                ProdigyPlayer pp = ProdigyPlayer.instanceOfPlayer(p);
                pp.equip("rainbow",args[0]);
            }

        }
        return true;
    }
}
