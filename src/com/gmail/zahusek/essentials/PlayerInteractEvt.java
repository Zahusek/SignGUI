package com.gmail.zahusek.essentials;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

import com.gmail.zahusek.Main;

public class PlayerInteractEvt implements Listener {
	
	Material[] sign = new Material[] {Material.WALL_SIGN, Material.SIGN_POST};

	@EventHandler
	public void onPlayerInteract(PlayerInteractEvent e) {
		final Player p = (Player) e.getPlayer();
		
		if(!e.getAction().equals(Action.RIGHT_CLICK_BLOCK)) return;
		
		final Block b = e.getClickedBlock();
		Material m = b.getType();
		
		if(!m.equals(sign[0]) && !m.equals(sign[1])) return;
		if(Main.getvalueworldguard()) if(!Main.getWorldGuard().canBuild(p, b)) return;
		
		e.setCancelled(true);	
		new PacketPlayOutOpenSignEditor(b.getLocation()).sendPacket(p);
	}
}
