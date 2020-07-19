package top.themeda.AnimalTrade;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.MerchantRecipe;
import org.bukkit.inventory.meta.ItemMeta;

public class Here {
	public static void handleNewAnimal(Entity e,Player p) {
		e.setCustomNameVisible(true);
		String name = ConfigLoad.cfg.getString("Language.DefaultName","§aDefaultName");
		e.setCustomName(name);
		ConfigLoad.db.set(e.getUniqueId().toString()+".Enable", true);
		ConfigLoad.db.set(e.getUniqueId().toString()+".World", e.getWorld().getName());
		ConfigLoad.saveDB();
		Trader t = new Trader();
		t.setUUID(e.getUniqueId());
		t.setEnable(true);
		List<MerchantRecipe> value = Here.getMenu(e.getUniqueId().toString());
		t.setRecipe(value);
		t.setEditInv(Here.editShop(t.getUUID().toString()));
		AnimalTradePlugin.traders.add(t);
	}
	public static boolean isTrader(UUID uid) {
		for(Trader tr : AnimalTradePlugin.traders) {
			if(tr.getUUID().equals(uid)) {
				return true;
			}
		}
		return false;
	}
	public static Inventory optionSet(String uid) {
		String title = ConfigLoad.cfg.getString("Language.OptionTitle","设置属性");
		Inventory inv = Bukkit.getServer().createInventory(null,27,title);
		inv.setItem(11, getCustomItem("Item.speedButton",uid));
		inv.setItem(12, getCustomItem("Item.flyButton",uid));
		inv.setItem(13, getCustomItem("Item.godButton",uid));
		inv.setItem(14, getCustomItem("Item.enableButton",uid));
		return inv;
	}
	public static Inventory editShop(String uid) {
		String title = ConfigLoad.cfg.getString("Language.GuiTitle","交易设置");
		Inventory inv = Bukkit.getServer().createInventory(null,54,title);
		//  8  26   44
		//保存 关闭 删除NPC
		inv.setItem(17, getCustomItem("Item.optionButton",uid));
		inv.setItem(44, getCustomItem("Item.removeButton",uid));
	
		inv.setItem(0, ConfigLoad.db.getItemStack(uid+".r1.cost1"));
		inv.setItem(9, ConfigLoad.db.getItemStack(uid+".r1.cost2"));
		inv.setItem(18, ConfigLoad.db.getItemStack(uid+".r1.result"));
		
		inv.setItem(1, ConfigLoad.db.getItemStack(uid+".r2.cost1"));
		inv.setItem(10, ConfigLoad.db.getItemStack(uid+".r2.cost2"));
		inv.setItem(19, ConfigLoad.db.getItemStack(uid+".r2.result"));
		
		inv.setItem(2, ConfigLoad.db.getItemStack(uid+".r3.cost1"));
		inv.setItem(11, ConfigLoad.db.getItemStack(uid+".r3.cost2"));
		inv.setItem(20, ConfigLoad.db.getItemStack(uid+".r3.result"));
		
		inv.setItem(3, ConfigLoad.db.getItemStack(uid+".r4.cost1"));
		inv.setItem(12, ConfigLoad.db.getItemStack(uid+".r4.cost2"));
		inv.setItem(21, ConfigLoad.db.getItemStack(uid+".r4.result"));
		
		inv.setItem(4, ConfigLoad.db.getItemStack(uid+".r5.cost1"));
		inv.setItem(13, ConfigLoad.db.getItemStack(uid+".r5.cost2"));
		inv.setItem(22, ConfigLoad.db.getItemStack(uid+".r5.result"));
		
		inv.setItem(5, ConfigLoad.db.getItemStack(uid+".r6.cost1"));
		inv.setItem(14, ConfigLoad.db.getItemStack(uid+".r6.cost2"));
		inv.setItem(23, ConfigLoad.db.getItemStack(uid+".r6.result"));

		inv.setItem(6, ConfigLoad.db.getItemStack(uid+".r7.cost1"));
		inv.setItem(15, ConfigLoad.db.getItemStack(uid+".r7.cost2"));
		inv.setItem(24, ConfigLoad.db.getItemStack(uid+".r7.result"));
		
		inv.setItem(7, ConfigLoad.db.getItemStack(uid+".r8.cost1"));
		inv.setItem(16, ConfigLoad.db.getItemStack(uid+".r8.cost2"));
		inv.setItem(25, ConfigLoad.db.getItemStack(uid+".r8.result"));
		
		inv.setItem(27, ConfigLoad.db.getItemStack(uid+".r9.cost1"));
		inv.setItem(36, ConfigLoad.db.getItemStack(uid+".r9.cost2"));
		inv.setItem(45, ConfigLoad.db.getItemStack(uid+".r9.result"));
		
		inv.setItem(28, ConfigLoad.db.getItemStack(uid+".r10.cost1"));
		inv.setItem(37, ConfigLoad.db.getItemStack(uid+".r10.cost2"));
		inv.setItem(46, ConfigLoad.db.getItemStack(uid+".r10.result"));
		
		inv.setItem(29, ConfigLoad.db.getItemStack(uid+".r11.cost1"));
		inv.setItem(38, ConfigLoad.db.getItemStack(uid+".r11.cost2"));
		inv.setItem(47, ConfigLoad.db.getItemStack(uid+".r11.result"));
		
		inv.setItem(30, ConfigLoad.db.getItemStack(uid+".r12.cost1"));
		inv.setItem(39, ConfigLoad.db.getItemStack(uid+".r12.cost2"));
		inv.setItem(48, ConfigLoad.db.getItemStack(uid+".r12.result"));
		
		inv.setItem(31, ConfigLoad.db.getItemStack(uid+".r13.cost1"));
		inv.setItem(40, ConfigLoad.db.getItemStack(uid+".r13.cost2"));
		inv.setItem(49, ConfigLoad.db.getItemStack(uid+".r13.result"));
		
		inv.setItem(32, ConfigLoad.db.getItemStack(uid+".r14.cost1"));
		inv.setItem(41, ConfigLoad.db.getItemStack(uid+".r14.cost2"));
		inv.setItem(50, ConfigLoad.db.getItemStack(uid+".r14.result"));
		
		inv.setItem(33, ConfigLoad.db.getItemStack(uid+".r15.cost1"));
		inv.setItem(42, ConfigLoad.db.getItemStack(uid+".r15.cost2"));
		inv.setItem(51, ConfigLoad.db.getItemStack(uid+".r15.result"));
		
		inv.setItem(34, ConfigLoad.db.getItemStack(uid+".r16.cost1"));
		inv.setItem(43, ConfigLoad.db.getItemStack(uid+".r16.cost2"));
		inv.setItem(52, ConfigLoad.db.getItemStack(uid+".r16.result"));
		return inv;
	}
	
	public static void saveShop(Inventory inv,String uid) {
		ConfigLoad.db.set(uid+".r1.cost1", inv.getItem(0));
		ConfigLoad.db.set(uid+".r1.cost2", inv.getItem(9));
		ConfigLoad.db.set(uid+".r1.result", inv.getItem(18));

		ConfigLoad.db.set(uid+".r2.cost1", inv.getItem(1));
		ConfigLoad.db.set(uid+".r2.cost2", inv.getItem(10));
		ConfigLoad.db.set(uid+".r2.result", inv.getItem(19));

		ConfigLoad.db.set(uid+".r3.cost1", inv.getItem(2));
		ConfigLoad.db.set(uid+".r3.cost2", inv.getItem(11));
		ConfigLoad.db.set(uid+".r3.result", inv.getItem(20));

		ConfigLoad.db.set(uid+".r4.cost1", inv.getItem(3));
		ConfigLoad.db.set(uid+".r4.cost2", inv.getItem(12));
		ConfigLoad.db.set(uid+".r4.result", inv.getItem(21));

		ConfigLoad.db.set(uid+".r5.cost1", inv.getItem(4));
		ConfigLoad.db.set(uid+".r5.cost2", inv.getItem(13));
		ConfigLoad.db.set(uid+".r5.result", inv.getItem(22));

		ConfigLoad.db.set(uid+".r6.cost1", inv.getItem(5));
		ConfigLoad.db.set(uid+".r6.cost2", inv.getItem(14));
		ConfigLoad.db.set(uid+".r6.result", inv.getItem(23));

		ConfigLoad.db.set(uid+".r7.cost1", inv.getItem(6));
		ConfigLoad.db.set(uid+".r7.cost2", inv.getItem(15));
		ConfigLoad.db.set(uid+".r7.result", inv.getItem(24));

		ConfigLoad.db.set(uid+".r8.cost1", inv.getItem(7));
		ConfigLoad.db.set(uid+".r8.cost2", inv.getItem(16));
		ConfigLoad.db.set(uid+".r8.result", inv.getItem(25));

		ConfigLoad.db.set(uid+".r9.cost1", inv.getItem(27));
		ConfigLoad.db.set(uid+".r9.cost2", inv.getItem(36));
		ConfigLoad.db.set(uid+".r9.result", inv.getItem(45));

		ConfigLoad.db.set(uid+".r10.cost1", inv.getItem(28));
		ConfigLoad.db.set(uid+".r10.cost2", inv.getItem(37));
		ConfigLoad.db.set(uid+".r10.result", inv.getItem(46));

		ConfigLoad.db.set(uid+".r11.cost1", inv.getItem(29));
		ConfigLoad.db.set(uid+".r11.cost2", inv.getItem(38));
		ConfigLoad.db.set(uid+".r11.result", inv.getItem(47));

		ConfigLoad.db.set(uid+".r12.cost1", inv.getItem(30));
		ConfigLoad.db.set(uid+".r12.cost2", inv.getItem(39));
		ConfigLoad.db.set(uid+".r12.result", inv.getItem(48));

		ConfigLoad.db.set(uid+".r13.cost1", inv.getItem(31));
		ConfigLoad.db.set(uid+".r13.cost2", inv.getItem(40));
		ConfigLoad.db.set(uid+".r13.result", inv.getItem(49));

		ConfigLoad.db.set(uid+".r14.cost1", inv.getItem(32));
		ConfigLoad.db.set(uid+".r14.cost2", inv.getItem(41));
		ConfigLoad.db.set(uid+".r14.result", inv.getItem(50));

		ConfigLoad.db.set(uid+".r15.cost1", inv.getItem(33));
		ConfigLoad.db.set(uid+".r15.cost2", inv.getItem(42));
		ConfigLoad.db.set(uid+".r15.result", inv.getItem(51));

		ConfigLoad.db.set(uid+".r16.cost1", inv.getItem(34));
		ConfigLoad.db.set(uid+".r16.cost2", inv.getItem(43));
		ConfigLoad.db.set(uid+".r16.result", inv.getItem(52));
		
		ConfigLoad.saveDB();
	}
	static ItemStack getCustomItem(String path,String arg) {
		Material mate = Material.APPLE;
		short damage = (short)ConfigLoad.cfg.getInt(path+".damage");
		try {
			mate = Material.valueOf(ConfigLoad.cfg.getString(path+".type").toUpperCase());
		}catch(Exception e) { }
		@SuppressWarnings("deprecation")
		ItemStack button = new ItemStack(mate,1,damage);
		ItemMeta meta = button.getItemMeta();
		meta.setDisplayName(ConfigLoad.cfg.getString(path+".displayname"));
		meta.setLocalizedName(arg);
		List<String> lore = ConfigLoad.cfg.getStringList(path+".lore");
		meta.setLore(lore);
		button.setItemMeta(meta);
		return button;	
	}
	public static List<MerchantRecipe> getMenu(String uid){
		List<MerchantRecipe> menu = new ArrayList<MerchantRecipe>();
		MerchantRecipe result = null;
		for(int i=0;i<16;i++) {
			if(ConfigLoad.db.get(uid+".r"+i)!=null) {
				result = new MerchantRecipe(ConfigLoad.db.getItemStack(uid+".r"+i+".result"),9999);
				if(ConfigLoad.db.getItemStack(uid+".r"+i+".result")==null) {
					continue;
				}
				List<ItemStack> ing = new ArrayList<ItemStack>();
				if(ConfigLoad.db.getItemStack(uid+".r"+i+".cost1")!=null) {
					ing.add(ConfigLoad.db.getItemStack(uid+".r"+i+".cost1"));
				}
				if(ConfigLoad.db.getItemStack(uid+".r"+i+".cost2")!=null) {
					ing.add(ConfigLoad.db.getItemStack(uid+".r"+i+".cost2"));
				}
				result.setIngredients(ing);
				menu.add(result);
			}
		}
		return menu;
	}
}
