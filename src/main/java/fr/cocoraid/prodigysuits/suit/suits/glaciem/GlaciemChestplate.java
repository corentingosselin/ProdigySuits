package fr.cocoraid.prodigysuits.suit.suits.glaciem;

import fr.cocoraid.prodigysuits.packet.wrapper.packet.WrapperPlayServerNamedSoundEffect;
import fr.cocoraid.prodigysuits.particle.ParticleEffect;
import fr.cocoraid.prodigysuits.particle.data.texture.BlockTexture;
import fr.cocoraid.prodigysuits.suit.FlyingItem;
import fr.cocoraid.prodigysuits.suit.parts.Chestplate;
import fr.cocoraid.prodigysuits.utils.ItemBuilder;
import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;

import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class GlaciemChestplate extends Chestplate {

    public GlaciemChestplate() {
        super("glaciem","glaciemchestplate",
                new ItemBuilder(Material.LEATHER_CHESTPLATE).setGlowing().setColor(Color.WHITE).setDisplayName("§bArmure glaciale"));
    }


    protected Map<UUID, ArrayDeque<FlyingItem>> flyingItems = new HashMap<>();
    @Override
    public void equip(Player p) {
        super.equip(p);
        refill(p);
    }

    private void refill(Player p) {

        WrapperPlayServerNamedSoundEffect sound = new WrapperPlayServerNamedSoundEffect();
        sound.setEffectPositionX(p.getLocation().getX());
        sound.setEffectPositionY(p.getLocation().getY());
        sound.setEffectPositionZ(p.getLocation().getZ());
        sound.setSoundName("mob.zombie.unfect");
        sound.setPitch(2);
        sound.setVolume(0.5F);
        sound.sendWorldPacket(p.getWorld());

        flyingItems.put(p.getUniqueId(), new ArrayDeque<>());
        int amount = 6;
        double angle = 0;
        double stepAngle = 2 * Math.PI / amount;
        for(int i = 0; i < amount; i++) {
            FlyingItem flyingItem = new FlyingItem(p.getLocation(),
                    i % 2 == 0 ? Material.ICE : Material.PACKED_ICE,
                    -1,
                    0,0,0,
                    FlyingItem.Size.EXTRA_SMALL,
                    2,
                    0.5,
                    angle,
                    true,
                    false,
                    Math.PI / 50,
                    0,
                    true,
                    40
            );
            angle+=stepAngle;

            flyingItems.get(p.getUniqueId()).add(flyingItem);
        }
    }

    protected void launch(Player p) {
        if(flyingItems.get(p.getUniqueId()).isEmpty()) {
            refill(p);
        } else {
            FlyingItem item = flyingItems.get(p.getUniqueId()).getLast();

            if(item.getDirection() != null) {
                return;
            }

                item.setDirection(p.getEyeLocation().getDirection());
                item.setDirectionSpeed(0.5f);
                item.setStayTime(2);
                item.addListener(new FlyingItem.ItemListener() {
                    @Override
                    public void onRemove() {
                        ParticleEffect.BLOCK_CRACK.display(item.getCurrentLocation(), 0.5F, 0.5F, 0.5F, 0.2F, 10, new BlockTexture(Material.SNOW_BLOCK));
                        ParticleEffect.SNOW_SHOVEL.display(item.getCurrentLocation(), 0.5F, 0.5F, 0.5F, 0.2F, 20, null);
                    }

                    @Override
                    public void onUpdate() {
                        WrapperPlayServerNamedSoundEffect sound = new WrapperPlayServerNamedSoundEffect();
                        sound.setEffectPositionX(item.getCurrentLocation().getX());
                        sound.setEffectPositionY(item.getCurrentLocation().getY());
                        sound.setEffectPositionZ(item.getCurrentLocation().getZ());
                        sound.setSoundName("dig.glass");
                        sound.setPitch(2);
                        sound.setVolume(0.1F);
                        sound.sendWorldPacket(item.getCurrentLocation().getWorld());
                        ParticleEffect.BLOCK_CRACK.display(item.getCurrentLocation().clone().add(0, 0.2, 0), 0.1F, 0.1F, 0.1F, 0.1F, 2, new BlockTexture(Material.PACKED_ICE));
                    }
                });

        }
    }


    @Override
    public void unequip(Player p) {
        super.unequip(p);
        flyingItems.get(p.getUniqueId()).forEach(f -> {
            f.remove();
        });
        flyingItems.clear();
    }


    @Override
    public void asyncAnimate(Location location) {
        super.asyncAnimate(location);
        flyingItems.values().forEach(list -> {
            list.forEach(f -> f.update(location));
            list.removeIf(f -> {
                if(f.isExisting()) {
                    f.update(location);
                    return false;
                } else return true;
            });
        });
    }
}
