package top.themeda.AnimalTrade.EventListener;

import java.util.UUID;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityCombustEvent;
import org.bukkit.event.entity.EntityDeathEvent;

import top.themeda.AnimalTrade.ConfigLoad;
import top.themeda.AnimalTrade.Here;

public class EntityListener implements Listener{
	@EventHandler
	public void onEntityDead(EntityDeathEvent e) {
		UUID uid = e.getEntity().getUniqueId();
		if(Here.isTrader(uid)) {
			ConfigLoad.db.set(uid.toString(), null);
			ConfigLoad.saveDB();
		}
	}
	@EventHandler(ignoreCancelled = true)
	public void onEntityBust(EntityCombustEvent e) {
		UUID uid = e.getEntity().getUniqueId();
		if(Here.isTrader(uid)) {
			e.setCancelled(true);
		}
	}
}
