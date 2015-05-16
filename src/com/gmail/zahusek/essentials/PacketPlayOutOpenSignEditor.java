package com.gmail.zahusek.essentials;

import org.bukkit.Location;
import org.bukkit.entity.Player;

import com.gmail.zahusek.protocols.Reflection;
import com.gmail.zahusek.protocols.TinyProtocol;
import com.gmail.zahusek.protocols.Reflection.FieldAccessor;

public class PacketPlayOutOpenSignEditor {
	
	private final Object handle;
	public TinyProtocol tinyprotocol;
	
	private Class<?> packet = Reflection.getClass("{nms}.PacketPlayOutOpenSignEditor");
	private FieldAccessor<Integer> x = Reflection.getField(packet, int.class, 0);
	private FieldAccessor<Integer> y = Reflection.getField(packet, int.class, 1);
	private FieldAccessor<Integer> z = Reflection.getField(packet, int.class, 2);
	
	public PacketPlayOutOpenSignEditor() {
		this.tinyprotocol = TinyProtocol.getTinyProtocol();
		this.handle = Reflection.getConstructor("{nms}.PacketPlayOutOpenSignEditor").invoke();
	}
	public PacketPlayOutOpenSignEditor(Location l) {
		this.tinyprotocol = TinyProtocol.getTinyProtocol();
		this.handle = Reflection.getConstructor("{nms}.PacketPlayOutOpenSignEditor").invoke();
		xyz(l);
	}
	public void xyz(Location l){
		x.set(this.handle, l.getBlockX());
		y.set(this.handle, l.getBlockY());
		z.set(this.handle, l.getBlockZ());
	}
	
	public void sendPacket(Player receiver) { tinyprotocol.sendPacket(receiver, this.handle); }
	
}
