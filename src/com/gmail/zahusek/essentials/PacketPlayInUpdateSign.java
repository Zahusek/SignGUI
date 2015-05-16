package com.gmail.zahusek.essentials;

import net.minecraft.util.io.netty.channel.Channel;

import org.bukkit.ChatColor;
import org.bukkit.block.Block;
import org.bukkit.block.Sign;
import org.bukkit.entity.Player;

import com.gmail.zahusek.protocols.Reflection;
import com.gmail.zahusek.protocols.Reflection.FieldAccessor;
import com.gmail.zahusek.protocols.TinyProtocol.PacketListener;

public class PacketPlayInUpdateSign extends PacketListener {
	
	private Class<?> packet = Reflection.getClass("{nms}.PacketPlayInUpdateSign");
	private FieldAccessor<Integer> x = Reflection.getField(packet, int.class, 0);
	private FieldAccessor<Integer> y = Reflection.getField(packet, int.class, 1);
	private FieldAccessor<Integer> z = Reflection.getField(packet, int.class, 2);
	private FieldAccessor<String[]> l = Reflection.getField(packet, String[].class, 0);
	
	@Override
	public Object onPacketInAsync(Player sender, Channel channel, Object packet) {
		if (!this.packet.isInstance(packet)) return packet;
		
		int x = this.x.get(packet);
		int y = this.y.get(packet);
		int z = this.z.get(packet);
		String[] l = this.l.get(packet);
		
		Block b = sender.getWorld().getBlockAt(x, y, z);
		Sign s = (Sign) b.getState();
		
		for(int i = 0; i < l.length; i ++){
			if(sender.hasPermission("essentials.signs.color") || sender.hasPermission("signgui.color"))
				l[i] = ChatColor.translateAlternateColorCodes('&', l[i]);
			else 
				l[i] = ChatColor.stripColor(l[i]);
			s.setLine(i, l[i]);
		}
		s.update();
		return packet;
	}
}
