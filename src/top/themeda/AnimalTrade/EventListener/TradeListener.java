package top.themeda.AnimalTrade.EventListener;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.Merchant;
import org.bukkit.inventory.MerchantRecipe;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.Plugin;

import top.themeda.AnimalTrade.AnimalTradePlugin;
import top.themeda.AnimalTrade.Here;
import top.themeda.AnimalTrade.Trader;
import top.themeda.AnimalTrade.ConfigLoad;

public class TradeListener implements Listener {
	HashMap<Player,String> lplay = new HashMap<>();
	@EventHandler
	public void onEntityDamage(EntityDamageEvent e) {
		if(e.getEntity().isInvulnerable())e.setCancelled(true);
		if(ConfigLoad.db.getBoolean(e.getEntity().getUniqueId()+".God",false)) {
			e.getEntity().setInvulnerable(true);
			ConfigLoad.db.set(e.getEntity().getUniqueId()+".God", null);
			ConfigLoad.saveDB();
		}
	}
	@EventHandler
	public void onPlayerInteractive(PlayerInteractEntityEvent e) {
		if(AnimalTradePlugin.version>=1.9d&&e.getHand()==EquipmentSlot.OFF_HAND) {
			return;
		}
		Player p =e.getPlayer();
		UUID uid = e.getRightClicked().getUniqueId();
		Trader t = null;
		for(Trader tr:AnimalTradePlugin.traders) {
			if(tr.getUUID()==null)continue;
			if(tr.getUUID().equals(uid)) {
				t=tr;
				break;
			}
		}
		if(t==null)return;
		e.setCancelled(true);
		if(p.isSneaking()) {
			if(p.isOp()||p.hasPermission("animaltrade.edit")) {
				p.openInventory(t.getEditInv());
				return;
			}
		}else {
			if(!t.getEnable()) {
				p.sendMessage(ConfigLoad.cfg.getString("Language.NotEnableNow"));
				return;
			}
			String title = ConfigLoad.cfg.getString("Language.TradeTitle","§l§c动§l§b物§l§6交§l§d易");
			Merchant inv = Bukkit.createMerchant(title);
			inv.setRecipes(t.getRecipe());
			p.openMerchant(inv, true);
		}
	}
	@EventHandler
	public void onPlayerClickInventory(InventoryClickEvent e) {
		Player p = (Player)e.getWhoClicked();
		if(e.getCurrentItem()==null)return;
		ItemStack item = e.getCurrentItem();
		ItemMeta meta = null;
		if(item.hasItemMeta()) {
			meta = item.getItemMeta();
		}
		int slot = e.getRawSlot();
		String title = ConfigLoad.cfg.getString("Language.GuiTitle","交易设置");
		if(e.getView().getTitle().equalsIgnoreCase(title)) {
			if(slot==17) {
				e.setCancelled(true);
				p.openInventory(Here.optionSet(meta.getLocalizedName()));
			}
			if(slot==44) {
				e.setCancelled(true);
				if(meta.getLore().toString().contains("确认")) {
					Bukkit.getEntity(UUID.fromString(meta.getLocalizedName())).remove();
					ConfigLoad.db.set(meta.getLocalizedName(), null);
					ConfigLoad.saveDB();
					p.closeInventory();
				}else {
					List<String> lore = meta.getLore();
					lore.add("再次点击以确认删除！");
					meta.setLore(lore);
					item.setItemMeta(meta);
					e.getClickedInventory().setItem(44, item);
				}
			}
		}
		
		String optiontitle = ConfigLoad.cfg.getString("Language.OptionTitle","设置属性");
		if(e.getView().getTitle().equalsIgnoreCase(optiontitle)) {
			if(slot==11) {
				e.setCancelled(true);
				lplay.put(p, "walk,"+meta.getLocalizedName());
				p.closeInventory();
				p.sendMessage(ConfigLoad.cfg.getString("Language.InPutNumber"));
				return;
			}
			if(slot==12) {
				e.setCancelled(true);
				lplay.put(p, "fly,"+meta.getLocalizedName());
				p.closeInventory();
				p.sendMessage(ConfigLoad.cfg.getString("Language.InPutNumber"));
				return;
			}
			if(slot==13) {
				e.setCancelled(true);
				Entity en = Bukkit.getServer().getEntity(UUID.fromString(meta.getLocalizedName()));
				if(en.isInvulnerable()) {
					en.setInvulnerable(false);
				}else {
					en.setInvulnerable(true);
				}
				p.sendMessage(ConfigLoad.cfg.getString("Language.EditSuccess"));
				return;
			}
			if(slot==14) {
				e.setCancelled(true);
				if(ConfigLoad.db.getBoolean(meta.getLocalizedName()+".Enable")) {
					ConfigLoad.db.set(meta.getLocalizedName()+".Enable", false);
				}else {
					ConfigLoad.db.set(meta.getLocalizedName()+".Enable", true);
				}
				ConfigLoad.saveDB();
				p.sendMessage(ConfigLoad.cfg.getString("Language.EditSuccess"));
				return;
			}
		}
	}
	Plugin plugin;
	@EventHandler
	public void onPlayerChat(AsyncPlayerChatEvent e) {
		plugin = AnimalTradePlugin.plugin;
		Player p = e.getPlayer();
		if(lplay.containsKey(p)) {
			e.setCancelled(true);
			String[] type = lplay.get(p).split(",");
			try {
				double arg = Double.valueOf(e.getMessage());
				switch(type[0]) {
				case "walk":
					Entity en = Bukkit.getServer().getEntity(UUID.fromString(type[1]));
					if(((LivingEntity)en).getAttribute(Attribute.GENERIC_MOVEMENT_SPEED)!=null) {
						((LivingEntity)en).getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).setBaseValue(arg);
						p.sendMessage(ConfigLoad.cfg.getString("Language.EditSuccess"));
					}else {
						p.sendMessage(ConfigLoad.cfg.getString("Language.EditFail","[§l§c动§l§b物§l§6交§l§d易§r]:§4无法修改 %arg%").replace("%arg%", "Walk"));
					}
					lplay.remove(p);
					Bukkit.getScheduler().runTask(plugin, new Runnable() {
						@Override
						public void run() {
							p.openInventory(Here.optionSet(type[1]));
						}
					});
					break;
				case "fly":
					Entity en2 = Bukkit.getServer().getEntity(UUID.fromString(type[1]));
					if(((LivingEntity)en2).getAttribute(Attribute.GENERIC_FLYING_SPEED)!=null) {
						((LivingEntity)en2).getAttribute(Attribute.GENERIC_FLYING_SPEED).setBaseValue(arg);
						p.sendMessage(ConfigLoad.cfg.getString("Language.EditSuccess"));
					}else {
						p.sendMessage(ConfigLoad.cfg.getString("Language.EditFail","[§l§c动§l§b物§l§6交§l§d易§r]:§4无法修改 %arg%").replace("%arg%", "Fly"));
					}
					lplay.remove(p);
					Bukkit.getScheduler().runTask(plugin, new Runnable() {
						@Override
						public void run() {
							p.openInventory(Here.optionSet(type[1]));
						}
					});
					break;
				}
			}catch(Exception el) {
				if(e.getMessage().equalsIgnoreCase("cancel")) {
					lplay.remove(p);
					Bukkit.getScheduler().runTask(plugin, new Runnable() {
						@Override
						public void run() {
							p.openInventory(Here.optionSet(type[1]));
						}
					});
				}else {
					p.sendMessage(ConfigLoad.cfg.getString("Language.InPutNumber"));
				}
			}
		}
	}
	@EventHandler
	public void onPlayerClose(InventoryCloseEvent e) {
		String title = ConfigLoad.cfg.getString("Language.GuiTitle");
		if(e.getView().getTitle().equalsIgnoreCase(title)) {
			Inventory inv = e.getView().getTopInventory();
			String uid = inv.getItem(44).getItemMeta().getLocalizedName();
			Here.saveShop(inv, uid);
			List<MerchantRecipe> value = Here.getMenu(uid);
			Trader t = new Trader();
			t.setUUID(UUID.fromString(uid));
			t.setRecipe(value);
			t.setEditInv(Here.editShop(t.getUUID().toString()));
			t.setEnable(ConfigLoad.db.getBoolean(uid+".Enable"));
			Iterator<Trader> its = AnimalTradePlugin.traders.iterator();
			while(its.hasNext()) {
				if(its.next().getUUID().equals(t.getUUID())) {
					its.remove();
					break;
				}
			}
			AnimalTradePlugin.traders.add(t);
		}
	}
}
