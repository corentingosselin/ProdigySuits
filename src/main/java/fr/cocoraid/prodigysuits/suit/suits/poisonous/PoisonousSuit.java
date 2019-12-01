package fr.cocoraid.prodigysuits.suit.suits.poisonous;

import fr.cocoraid.prodigysuits.ProdigySuits;
import fr.cocoraid.prodigysuits.particle.ParticleEffect;
import fr.cocoraid.prodigysuits.particle.data.color.RegularColor;
import fr.cocoraid.prodigysuits.suit.Suit;
import fr.cocoraid.prodigysuits.utils.VectorUtils;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class PoisonousSuit extends Suit {

    public PoisonousSuit() {
        super("poisonous",new PoisonousBoot(), new PoisonousLegging(), new PoisonousHelmet(), new PoisonousChestplate());
    }


    private Map<UUID,PoisonousAura> aura = new HashMap<>();

    @Override
    public void action(PlayerInteractEvent e) {
        super.action(e);
        if(e.getAction() == Action.LEFT_CLICK_AIR || e.getAction() == Action.LEFT_CLICK_BLOCK) {
            Player p = e.getPlayer();
            if(!aura.containsKey(p.getUniqueId())) {
                aura.put(p.getUniqueId(), new PoisonousAura());
            }
        }
    }

    @Override
    public void asyncGlobalAnimate(Player p) {
        super.asyncGlobalAnimate(p);

        if(aura.containsKey(p.getUniqueId())) {
            PoisonousAura pa = aura.get(p.getUniqueId());
            if(pa.remove) {
                aura.remove(p.getUniqueId());
                return;
            }
            pa.update(p.getLocation());
        }
    }

    private class PoisonousAura {

        private boolean end = false;
        private boolean remove = false;
        public PoisonousAura()  {
        }

        private int i = 0;
        private double radius = 0;
        public void update(Location location) {
            if(end) return;

            int k = 0;
            for (Vector v : VectorUtils.createCircle(0, 8, radius)) {
                if(i%2==0)
                    ParticleEffect.SPELL_MOB.display(location.clone().add(v),
                            new RegularColor(new java.awt.Color(49, 242, 5)));
                else
                    ParticleEffect.REDSTONE.display(location.clone().add(v),
                            new RegularColor(k % 2 == 0 ? new Color(49,242,5) : new java.awt.Color(0, 0, 0)));
                k++;

            }

            radius+=0.4;
            if(radius >= 5) {
                VectorUtils.createCircle(0,32,radius).forEach(v -> {
                    Location loc = location.clone().add(v);
                    ParticleEffect.CLOUD.display(loc,loc.toVector().subtract(location.toVector()),0.05F,0,null);

                });
                end = true;
                new BukkitRunnable() {
                    @Override
                    public void run() {
                        remove = true;
                    }
                }.runTaskLaterAsynchronously(ProdigySuits.getInstance(),20 * 5);

            }
            i++;
        }
    }
}
