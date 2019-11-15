package fr.cocoraid.prodigysuits.suit.suits.terra;

import fr.cocoraid.prodigysuits.particle.ParticleEffect;
import fr.cocoraid.prodigysuits.particle.data.texture.BlockTexture;
import fr.cocoraid.prodigysuits.suit.parts.Boots;
import fr.cocoraid.prodigysuits.utils.ItemBuilder;
import fr.cocoraid.prodigysuits.utils.UtilMath;
import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.BlockFace;
import org.bukkit.util.Vector;

public class TerraBoots extends Boots {

    public TerraBoots() {
        super("terra","terraboots", new ItemBuilder(Material.LEATHER_BOOTS).setColor(Color.MAROON).setDisplayName("§6Bottes terra"));
    }

    @Override
    public void asyncAnimate(Location location) {
        super.asyncAnimate(location);
        Vector vector = new Vector(UtilMath.randFloat(-2f,2F), 0.1 , UtilMath.randFloat(-2f,2F));
        Location l = location.clone().add(vector);
        Material material = Material.DIRT;
        if(l.getBlock().getRelative(BlockFace.DOWN).getType() != Material.AIR)
            material = l.getBlock().getRelative(BlockFace.DOWN).getType();

        ParticleEffect.BLOCK_CRACK.display(location.clone().add(vector),0.2F,0.2F,0.2F,0.1F,3,new BlockTexture(material));
    }
}
