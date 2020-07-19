package top.themeda.AnimalTrade.Commands;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import top.themeda.AnimalTrade.AnimalTradePlugin;
import top.themeda.AnimalTrade.ConfigLoad;

public class CancelCommand {
	public void parseCommand(CommandSender sender,String[] args) {
		if(sender instanceof Player) {
			Player p =(Player)sender;
			if(args.length!=1) {
				p.sendMessage(ConfigLoad.cfg.getString("Language.WrongCommand").replace("%arg%", "/at cancel"));
				return;
			}
			if(AnimalTradePlugin.pmap.containsKey(p)) {
				AnimalTradePlugin.pmap.remove(p);
				sender.sendMessage(ConfigLoad.cfg.getString("Language.CancelSuccess"));
				return;
			}else {
				sender.sendMessage(ConfigLoad.cfg.getString("Language.CancelFailA"));
				return;
			}
		}else {
			sender.sendMessage(ConfigLoad.cfg.getString("Language.PlayerOnly"));
			return;
		}
	}
}
