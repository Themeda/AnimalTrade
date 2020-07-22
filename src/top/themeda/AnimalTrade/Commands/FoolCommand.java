package top.themeda.AnimalTrade.Commands;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import top.themeda.AnimalTrade.AnimalTradePlugin;
import top.themeda.AnimalTrade.ConfigLoad;
import top.themeda.AnimalTrade.Ai.BeFool_1_12;
import top.themeda.AnimalTrade.Ai.BeFool_1_13;
import top.themeda.AnimalTrade.Ai.BeFool_1_14;
import top.themeda.AnimalTrade.Ai.BeFool_1_15;
import top.themeda.AnimalTrade.Ai.BeFool_1_16;

public class FoolCommand {
	public void parseCommand(CommandSender sender,String[] args) {
		if(sender instanceof Player) {
			Player p = (Player)sender;
			if(!p.hasPermission("animaltrade.fool")&&!p.isOp()) {
				p.sendMessage(ConfigLoad.cfg.getString("Language.HaveNoPremission"));
				return;
			}
			if(args.length!=1) {
				p.sendMessage(ConfigLoad.cfg.getString("Language.WrongCommand").replace("%arg%", "/at fool"));
				return;
			}
			if(!AnimalTradePlugin.pmap.containsKey(p)) {
				p.sendMessage(ConfigLoad.cfg.getString("Language.SetNameFailA"));
				return;
			}
			double version = AnimalTradePlugin.version;
			if(version==1.12d) {
				BeFool_1_12.clear(AnimalTradePlugin.pmap.get(p));
			}else if(version == 1.13d) {
				BeFool_1_13.clear(AnimalTradePlugin.pmap.get(p));
			}else if(version == 1.14d) {
				BeFool_1_14.clear(AnimalTradePlugin.pmap.get(p));
			}else if(version == 1.15d) {
				BeFool_1_15.clear(AnimalTradePlugin.pmap.get(p));
			}else if(version == 1.16d) {
				BeFool_1_16.clear(AnimalTradePlugin.pmap.get(p));
			}
			p.sendMessage(ConfigLoad.cfg.getString("Language.EditSuccess"));
			return;
		}else {
			sender.sendMessage(ConfigLoad.cfg.getString("Language.PlayerOnly"));
			return;
		}
	}
}
