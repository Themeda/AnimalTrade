package top.themeda.AnimalTrade.Commands;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import top.themeda.AnimalTrade.AnimalTradePlugin;
import top.themeda.AnimalTrade.ConfigLoad;

public class SetNameCommand {
	public void parseCommand(CommandSender sender,String[] args) {
		if(sender instanceof Player) {
			Player p = (Player)sender;
			if(!p.hasPermission("animaltrade.setname")&&!p.isOp()) {
				p.sendMessage(ConfigLoad.cfg.getString("Language.HaveNoPremission"));
				return;
			}
			if(args.length!=2) {
				p.sendMessage(ConfigLoad.cfg.getString("Language.WrongCommand").replace("%arg%", "/at setname <name>"));
				return;
			}
			if(AnimalTradePlugin.pmap.containsKey(p)) {
				AnimalTradePlugin.pmap.get(p).setCustomName(args[1].replace("&","¡ì").replace("%20", " "));
				AnimalTradePlugin.pmap.get(p).setCustomNameVisible(true);
				p.sendMessage(ConfigLoad.cfg.getString("Language.SetNameSuccess"));
				return;
			}else {
				p.sendMessage(ConfigLoad.cfg.getString("Language.SetNameFailA"));
				return;
			}
		}else {
			sender.sendMessage(ConfigLoad.cfg.getString("Language.PlayerOnly"));
			return;
		}
	}
}
