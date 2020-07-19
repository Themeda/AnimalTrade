package top.themeda.AnimalTrade.Commands;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import top.themeda.AnimalTrade.AnimalTradePlugin;
import top.themeda.AnimalTrade.ConfigLoad;

public class DelNameCommand {
	public void parseCommand(CommandSender sender,String[] args) {
		if(sender instanceof Player) {
			Player p = (Player)sender;
			if(!p.hasPermission("animaltrade.setname")&&!p.isOp()) {
				p.sendMessage(ConfigLoad.cfg.getString("Language.HaveNoPremission"));
				return;
			}
			if(args.length!=1) {
				p.sendMessage(ConfigLoad.cfg.getString("Language.WrongCommand").replace("%arg%", "/at delname"));
				return;
			}
			if(AnimalTradePlugin.pmap.containsKey(p)) {
				AnimalTradePlugin.pmap.get(p).setCustomName(null);
				AnimalTradePlugin.pmap.get(p).setCustomNameVisible(false);
				p.sendMessage(ConfigLoad.cfg.getString("Language.SetNameSuccess"));
				return;
			}else {
				p.sendMessage(ConfigLoad.cfg.getString("Language.SetNameFailA"));
				return;
			}
		}
	}
}
