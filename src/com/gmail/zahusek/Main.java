package com.gmail.zahusek;

import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import com.gmail.zahusek.essentials.BlockPlaceEvt;
import com.gmail.zahusek.essentials.PacketPlayInUpdateSign;
import com.gmail.zahusek.essentials.PlayerInteractEvt;
import com.gmail.zahusek.protocols.TinyProtocol;
import com.sk89q.worldguard.bukkit.WorldGuardPlugin;

public class Main extends JavaPlugin {
	
	protected TinyProtocol tinyprotocol;
	public TinyProtocol getTinyProtocol() { return this.tinyprotocol; }
	
	private static WorldGuardPlugin worldguard;
	public static WorldGuardPlugin getWorldGuard(){return worldguard;}
	
	private static boolean isworldguard = false;
	public static boolean getvalueworldguard() {return isworldguard;}
	
	@Override
	public void onEnable() {
		this.tinyprotocol = new TinyProtocol(this);
		new PacketPlayInUpdateSign();
		Bukkit.getServer().getPluginManager().registerEvents(new PlayerInteractEvt(), this);
		Bukkit.getServer().getPluginManager().registerEvents(new BlockPlaceEvt(), this);
	    isworldguard = isworldguard();
		super.onEnable();
	}
	@Override
	public void onDisable() {
		super.onDisable();
	}
	
	private boolean isworldguard(){
		Plugin plugin = getServer().getPluginManager().getPlugin("WorldGuard");
	    if (plugin == null || !(plugin instanceof WorldGuardPlugin))
	       worldguard = null;
	    else 
	    	worldguard = (WorldGuardPlugin) plugin;
	    return worldguard == null ? false : true;
	}
}
