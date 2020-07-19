package top.themeda.AnimalTrade.Commands;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

import top.themeda.AnimalTrade.AnimalTradePlugin;
import top.themeda.AnimalTrade.ConfigLoad;
import top.themeda.AnimalTrade.Here;

public class TpCommand {
	public void parseCommand(CommandSender sender,String[] args) {
		if(sender instanceof Player) {
			Player p = (Player)sender;
			if(!p.hasPermission("animaltrade.tp")&&!p.isOp()) {
				p.sendMessage(ConfigLoad.cfg.getString("Language.HaveNoPremission"));
				return;
			}
			if(args.length==1) {
				//only tp
				if(!AnimalTradePlugin.pmap.containsKey(p)) {
					for(Entity en:p.getNearbyEntities(100d, 100d, 100d)) {
						if(Here.isTrader(en.getUniqueId())) {
							p.teleport(en);
							p.sendMessage(ConfigLoad.cfg.getString("Language.TeleportSuccess"));
							return;
						}
					}
					p.sendMessage(ConfigLoad.cfg.getString("Language.TeleportFailA"));
				}else {
					p.teleport(AnimalTradePlugin.pmap.get(p));
					p.sendMessage(ConfigLoad.cfg.getString("Language.TeleportSuccess"));
					return;
				}
			}else if(args.length==2) {
				//tp name
				for(Entity ents :p.getWorld().getEntities()){
					if(args[1].equals(ChatColor.stripColor(ents.getCustomName()))) {
						p.teleport(ents);
						p.sendMessage(ConfigLoad.cfg.getString("Language.TeleportSuccess"));
						return;
					}
				}
				p.sendMessage(ConfigLoad.cfg.getString("Language.TeleportFail"));
				return;
			}else {
				p.sendMessage(ConfigLoad.cfg.getString("Language.WrongCommand").replace("%arg%", "/at tp [name]"));
				return;
			}
		}else {
			sender.sendMessage(ConfigLoad.cfg.getString("Language.PlayerOnly"));
			return;
		}
	}
}
