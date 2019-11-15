package fr.cocoraid.prodigysuits.packet.wrapper.packet;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.events.PacketContainer;
import com.comphenix.protocol.events.PacketEvent;
import fr.cocoraid.prodigysuits.packet.AbstractPacket;
import org.bukkit.World;
import org.bukkit.entity.Entity;

public class WrapperPlayServerRelEntityMove extends AbstractPacket {
	public static final PacketType TYPE =
			PacketType.Play.Server.REL_ENTITY_MOVE;

	public WrapperPlayServerRelEntityMove() {
		super(new PacketContainer(TYPE), TYPE);
		handle.getModifier().writeDefaults();
	}

	public WrapperPlayServerRelEntityMove(PacketContainer packet) {
		super(packet, TYPE);
	}

	/**
	 * Retrieve Entity ID.
	 * <p>
	 * Notes: entity's ID
	 * 
	 * @return The current Entity ID
	 */
	public int getEntityID() {
		return handle.getIntegers().read(0);
	}

	/**
	 * Set Entity ID.
	 * 
	 * @param value - new value.
	 */
	public void setEntityID(int value) {
		handle.getIntegers().write(0, value);
	}

	/**
	 * Retrieve the entity of the painting that will be spawned.
	 * 
	 * @param world - the current world of the entity.
	 * @return The spawned entity.
	 */
	public Entity getEntity(World world) {
		return handle.getEntityModifier(world).read(0);
	}

	/**
	 * Retrieve the entity of the painting that will be spawned.
	 * 
	 * @param event - the packet event.
	 * @return The spawned entity.
	 */
	public Entity getEntity(PacketEvent event) {
		return getEntity(event.getPlayer().getWorld());
	}

	public int getDx() {
		return handle.getIntegers().read(1);
	}

	public void setDx(int value) {
		handle.getIntegers().write(1, value);
	}

	public int getDy() {
		return handle.getIntegers().read(2);
	}

	public void setDy(int value) {
		handle.getIntegers().write(2, value);
	}

	public int getDz() {
		return handle.getIntegers().read(3);
	}

	public void setDz(int value) {
		handle.getIntegers().write(3, value);
	}

	/**
	 * Retrieve On Ground.
	 * 
	 * @return The current On Ground
	 */
	public boolean getOnGround() {
		return handle.getBooleans().read(0);
	}

	/**
	 * Set On Ground.
	 * 
	 * @param value - new value.
	 */
	public void setOnGround(boolean value) {
		handle.getBooleans().write(0, value);
	}
}
