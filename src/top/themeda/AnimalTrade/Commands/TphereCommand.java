package top.themeda.AnimalTrade.Commands;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import top.themeda.AnimalTrade.AnimalTradePlugin;
import top.themeda.AnimalTrade.ConfigLoad;

public class TphereCommand {
	public void parseCommand(CommandSender sender,String[] args) {
		if(sender instanceof Player) {
			Player p = (Player)sender;
			if(!p.hasPermission("animaltrade.tphere")&&!p.isOp()) {
				p.sendMessage(ConfigLoad.cfg.getString("Language.HaveNoPremission"));
				return;
			}
			if(args.length!=1) {
				p.sendMessage(ConfigLoad.cfg.getString("Language.WrongCommand").replace("%arg%", "/at tphere"));
				return;
			}
			if(!AnimalTradePlugin.pmap.containsKey(p)) {
				p.sendMessage(ConfigLoad.cfg.getString("Language.NotSelect"));
				return;
			}
			AnimalTradePlugin.pmap.get(p).teleport(p);
			p.sendMessage(ConfigLoad.cfg.getString("Language.TeleportSuccess"));
		}else {
			sender.sendMessage(ConfigLoad.cfg.getString("Language.PlayerOnly"));
			return;
		}
	}
}
