package top.themeda.AnimalTrade.EventListener;

import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import top.themeda.AnimalTrade.AnimalTradePlugin;
import top.themeda.AnimalTrade.ConfigLoad;

public class PlayerListener implements Listener{
	@EventHandler
	public void onPlayerInteractEvent(PlayerInteractEntityEvent e) {
		Player p = e.getPlayer();
		if(!AnimalTradePlugin.interact.containsKey(p)) {
			return;
		}
		if(AnimalTradePlugin.interact.get(p).equalsIgnoreCase("select")) {
			Entity ent = e.getRightClicked();
			AnimalTradePlugin.pmap.remove(p);
			AnimalTradePlugin.pmap.put(p, ent);
			AnimalTradePlugin.interact.remove(p);
			p.sendMessage(ConfigLoad.cfg.getString("Language.SelectSuccess"));
			return;
		}
	}
	@EventHandler
	public void onPlayerExit(PlayerQuitEvent e) {
		Player p = e.getPlayer();
		if(AnimalTradePlugin.pmap.containsKey(p)) {
			AnimalTradePlugin.pmap.remove(p);
		}
		if(AnimalTradePlugin.interact.containsKey(p)) {
			AnimalTradePlugin.interact.remove(p);
		}
	}
}
