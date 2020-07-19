package top.themeda.AnimalTrade;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Logger;

import org.bukkit.Bukkit;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import top.themeda.AnimalTrade.Commands.ParseCommand;
import top.themeda.AnimalTrade.EventListener.PlayerListener;
import top.themeda.AnimalTrade.EventListener.TradeListener;

public class AnimalTradePlugin extends JavaPlugin implements Listener{
	static Logger log = Bukkit.getLogger();
	public static List<Trader> traders = new ArrayList<>();
	public static String versions = Bukkit.getVersion();
	public static double version = 0d;
	public static HashMap<Player,String> interact = new HashMap<>();
	public static HashMap<Player,Entity> pmap = new HashMap<>();
	public static Plugin plugin;
	@Override
	public void onEnable() {
		log.info("��4��m             ��l��6[AnimalTrade]��4��m             ");
		log.info("��4��m                ��l��c����l��b���l��6����l��d�ס�4��m                   ");
		log.info("��4��m                ��l��8���ڼ��ء�4��m                   ");
		log.info("��4��m                ��l��7-------��4��m                   ");
		plugin = this;
		Bukkit.getPluginManager().registerEvents(new TradeListener(), this);
		Bukkit.getPluginManager().registerEvents(new PlayerListener(), this);
		try{
			version = Double.valueOf(versions.substring(versions.indexOf("(MC: ")+5,versions.lastIndexOf(".")));
		}catch(Exception e) {
			version = 1.12d;
		}
		ConfigLoad.outputConfig();
		this.reloadConfig();
		Bukkit.getScheduler().runTaskTimerAsynchronously(this, new SelectedTip(), 20L, 20L);
		Bukkit.getServer().getPluginCommand("animaltrade").setExecutor(new ParseCommand());
		log.info("��4��m                ��l��8������ɡ�4��m                   ");
		log.info("��4��m                ��l��c����l��b���l��6����l��d�ס�4��m                   ");
		log.info("��4��m             ��l��6[AnimalTrade]��4��m             ");
	}
	@Override
	public void onDisable() {
		HandlerList.unregisterAll();
		log.info("��4��m             ��l��6[AnimalTrade]��4��m             ");
		log.info("��4��m                ��l��c����l��b���l��6����l��d�ס�4��m                   ");
		log.info("��4��m                ��l��8ж����ɡ�4��m                   ");
		log.info("��4��m                ��l��c����l��b���l��6����l��d�ס�4��m                   ");
		log.info("��4��m             ��l��6[AnimalTrade]��4��m             ");
	}
}
