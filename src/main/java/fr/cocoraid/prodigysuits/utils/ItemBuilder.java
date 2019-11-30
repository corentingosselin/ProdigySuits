package fr.cocoraid.prodigysuits.utils;

import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.Property;
import org.apache.commons.codec.binary.Base64;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;
import org.bukkit.inventory.meta.SkullMeta;

import java.lang.reflect.Field;
import java.util.UUID;

public class ItemBuilder {

    private ItemStack item;
    public ItemBuilder(Material material) {
        this.item = new ItemStack(material);
    }

    public ItemBuilder(String url) {
        this.item = new ItemStack(Material.SKULL_ITEM, 1, (short)3);
        SkullMeta headMeta = (SkullMeta) item.getItemMeta();
        GameProfile profile = new GameProfile(UUID.randomUUID(), null);
        byte[] encodedData = Base64.encodeBase64(String.format("{textures:{SKIN:{url:\"%s\"}}}", "http://textures.minecraft.net/texture/"+ url).getBytes());
        profile.getProperties().put("textures", new Property("textures", new String(encodedData)));
        Field profileField = null;
        try {
            profileField = headMeta.getClass().getDeclaredField("profile");
            profileField.setAccessible(true);
            profileField.set(headMeta, profile);
        } catch (NoSuchFieldException | IllegalArgumentException | IllegalAccessException e1) {
            e1.printStackTrace();
        }
        item.setItemMeta(headMeta);
    }

    public ItemBuilder setColor(Color color) {
        if(!(item.getItemMeta() instanceof LeatherArmorMeta)) return this;
        LeatherArmorMeta meta = (LeatherArmorMeta) item.getItemMeta();
        meta.setColor(color);
        item.setItemMeta(meta);
        return this;
    }

    public ItemBuilder setDisplayName(String name) {
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(name);
        item.setItemMeta(meta);
        return this;
    }

    public ItemBuilder setGlowing() {
        item.addEnchantment(Enchantment.DURABILITY, 1);
        ItemMeta meta = item.getItemMeta();
        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        item.setItemMeta(meta);
        return this;
    }



    public ItemStack getItem() {
        return item;
    }


    public static class Head {
        public static final ItemStack BLAZE = new ItemBuilder("b78ef2e4cf2c41a2d14bfde9caff10219f5b1bf5b35a49eb51c6467882cb5f0").getItem();
        public static final ItemBuilder ZEUS = new ItemBuilder("29ec534213a29e4dc818dbe7f93ac6d573186e456588ba454aa48c651db15d");
        public static final ItemStack NYANCAT = new ItemBuilder("ab9f6b416e100d9a9edc427c026114e458f77830cd12b730e973d958fe092954").getItem();
        public static final ItemBuilder HAZMAT = new ItemBuilder("9674325cc85b8a6c03ba3106ea793781e7bcf5a6c63a59afb19c17be2b502158");

    }



}
