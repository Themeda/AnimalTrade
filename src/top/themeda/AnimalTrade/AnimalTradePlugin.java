package top.themeda.AnimalTrade;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import top.themeda.AnimalTrade.Commands.ParseCommand;
import top.themeda.AnimalTrade.EventListener.EntityListener;
import top.themeda.AnimalTrade.EventListener.PlayerListener;
import top.themeda.AnimalTrade.EventListener.TradeListener;

public class AnimalTradePlugin extends JavaPlugin implements Listener{
	public static ConsoleCommandSender log = Bukkit.getConsoleSender();
	public static List<Trader> traders = new ArrayList<>();
	public static String versions = Bukkit.getVersion();
	public static String version ="1.12";
	public static HashMap<Player,String> interact = new HashMap<>();
	public static HashMap<Player,Entity> pmap = new HashMap<>();
	public static Plugin plugin;
	@Override
	public void onEnable() {
		log.sendMessage("§6     _          _                 _ _____              _      " );
		log.sendMessage("§6    / \\   _ __ (_)_ __ ___   __ _| |_   _| __ __ _  __| | ___ ");
		log.sendMessage("§6   / _ \\ | '_ \\| | '_ ` _ \\ / _` | | | || '__/ _` |/ _` |/ _ \\");
		log.sendMessage("§6  / ___ \\| | | | | | | | | | (_| | | | || | | (_| | (_| |  __/");
		log.sendMessage("§6 /_/   \\_\\_| |_|_|_| |_| |_|\\__,_|_| |_||_|  \\__,_|\\__,_|\\___|");
		log.sendMessage("§6                                                              ");
		log.sendMessage("§4                §l§c动§l§b物§l§6交§l§d易§4                   ");
		log.sendMessage("§4                §l§7开始加载§4                   ");
		log.sendMessage("§4    §l§9-----------------------------------§4   ");
		version = versions.substring(versions.indexOf("(MC: ")+5,versions.lastIndexOf(")"));
		log.sendMessage(String.format("§4    §l§9--§7You are Now Running at %-7s",version+""));
		plugin = this;
		log.sendMessage(String.format("§4    §l§9--§7%-31s","正在检查配置文件......"));
		ConfigLoad.outputConfig();
		ConfigLoad.reloadConfigs();
		log.sendMessage(String.format("§4    §l§9--§7%-31s","配置文件检查完毕......"));
		log.sendMessage(String.format("§4    §l§9--§7%-31s","正在注册监听器......"));
		Bukkit.getPluginManager().registerEvents(new TradeListener(), this);
		Bukkit.getPluginManager().registerEvents(new PlayerListener(), this);
		Bukkit.getPluginManager().registerEvents(new EntityListener(), this);
		log.sendMessage(String.format("§4    §l§9--§7%-31s","监听器注册完成......"));
		log.sendMessage(String.format("§4    §l§9--§7%-31s","正在注册异步任务......"));
		Bukkit.getScheduler().runTaskTimerAsynchronously(this, new SelectedTip(), 20L, 20L);
		log.sendMessage(String.format("§4    §l§9--§7%-31s","异步任务注册完毕......"));
		log.sendMessage(String.format("§4    §l§9--§7%-31s","初始化统计系统......"));
		Metrics metrics = new Metrics(this, 8514);
		log.sendMessage(String.format("§4    §l§9--§7%-31s","bStats统计系统："+(metrics.isEnabled()?"Enable":"Unable")));
		log.sendMessage(String.format("§4    §l§9--§7%-31s","正在注册指令......"));
		Bukkit.getServer().getPluginCommand("animaltrade").setExecutor(new ParseCommand());
		log.sendMessage(String.format("§4    §l§9--§7%-31s","指令注册完成......"));
		log.sendMessage("§4    §l§9-----------------------------------§4   ");
		log.sendMessage("§4                §l§7加载完成§4                   ");
		log.sendMessage("§4                §l§c动§l§b物§l§6交§l§d易§4                   ");
		log.sendMessage("§4             §l§6[AnimalTrade]§4             ");
	}
	@Override
	public void onDisable() {
		log.sendMessage("§4             §l§6[AnimalTrade]§4             ");
		log.sendMessage("§4                §l§c动§l§b物§l§6交§l§d易§4                   ");
		log.sendMessage("§4                §l§7开始卸载§4                   ");
		log.sendMessage("§4    §l§9-----------------------------------§4   ");
		log.sendMessage(String.format("§4    §l§9--§7%-31s","正在注销监听器......"));
		HandlerList.unregisterAll();
		log.sendMessage(String.format("§4    §l§9--§7%-31s","监听器注销完成......"));
		log.sendMessage(String.format("§4    §l§9--§7%-31s","正在注销异线程任务......"));
		Bukkit.getScheduler().cancelTasks(this);
		log.sendMessage(String.format("§4    §l§9--§7%-31s","任务注销完成......"));
		log.sendMessage("§4    §l§9-----------------------------------§4   ");
		log.sendMessage("§4                §l§7卸载完成§4                   ");
		log.sendMessage("§4                §l§c动§l§b物§l§6交§l§d易§4                   ");
		log.sendMessage("§4             §l§6[AnimalTrade]§4             ");
	}
}
