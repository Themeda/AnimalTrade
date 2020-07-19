package top.themeda.AnimalTrade;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.inventory.MerchantRecipe;

public class ConfigLoad {
	//ª˘¥°≈‰÷√
	public static File Databasefile = new File(Bukkit.getPluginManager().getPlugin("AnimalTrade").getDataFolder(),"Database.yml");
	public static FileConfiguration db = YamlConfiguration.loadConfiguration(Databasefile);
	public static File filecfg = new File(Bukkit.getPluginManager().getPlugin("AnimalTrade").getDataFolder(),"config.yml");
	public static FileConfiguration cfg = YamlConfiguration.loadConfiguration(filecfg);
	public static void outputConfig() {
	//	if(!Databasefile.exists())Bukkit.getPluginManager().getPlugin("AnimalTrade").saveResource("Database.yml", false);
		if(!filecfg.exists())Bukkit.getPluginManager().getPlugin("AnimalTrade").saveResource("config.yml", false);
	}
	public static void reloadConfigs() {
		db = YamlConfiguration.loadConfiguration(Databasefile);
		cfg = YamlConfiguration.loadConfiguration(filecfg);
		AnimalTradePlugin.traders.clear();
		for(String key:db.getKeys(false)) {
			List<MerchantRecipe> value = Here.getMenu(key);
			Trader t = new Trader();
			t.setUUID(UUID.fromString(key));
			t.setRecipe(value);
			t.setEditInv(Here.editShop(t.getUUID().toString()));
			t.setEnable(db.getBoolean(key+".Enable"));
			AnimalTradePlugin.traders.add(t);
		}
		
	}
	public static void saveDB() {
		try {
			db.save(Databasefile);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
