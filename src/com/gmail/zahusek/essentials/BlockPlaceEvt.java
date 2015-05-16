package com.gmail.zahusek.essentials;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;

import com.gmail.zahusek.Main;

public class BlockPlaceEvt implements Listener {

	@EventHandler
	public void onPlayerInteract(BlockPlaceEvent e) {
		Player p = (Player) e.getPlayer();
		if(!p.getInventory().getItemInHand().getType().equals(Material.SIGN)) return;
		if(e.isCancelled()) return;
		Block b = e.getBlockPlaced();
		if(Main.getvalueworldguard()) if(!Main.getWorldGuard().canBuild(p, b)) return;
		new PacketPlayOutOpenSignEditor(b.getLocation()).sendPacket(p);
	}
}
