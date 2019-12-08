package fr.cocoraid.prodigysuits.event;

import fr.cocoraid.prodigysuits.ProdigyPlayer;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class TaskManager extends BukkitRunnable {

    public TaskManager() {

    }

    private int time = 0;
    @Override
    public void run() {

        ProdigyPlayer.getProdigyPlayers().values().stream().filter(pp -> pp.getSuit() != null).forEach(pp -> {
            Player p = pp.getPlayer();
            if(!p.isOnline()) return;

            if(time % 5 == 0)
                pp.checkMove();

            if(time % 20 == 0)
                pp.setOldLocation(p.getLocation());

        });

        time++;
        time%=(20*60);
    }


}
