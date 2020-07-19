package top.themeda.AnimalTrade.Commands;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import top.themeda.AnimalTrade.ConfigLoad;

public class ReloadCommand {
	public void parseCommand(CommandSender sender,String[] args) {
		if(sender instanceof Player) {
			Player p = (Player)sender;
			if(!p.hasPermission("animaltrade.reload")&&!p.isOp()) {
				p.sendMessage(ConfigLoad.cfg.getString("Language.HaveNoPremission"));
			}else {
				ConfigLoad.reloadConfigs();
				p.sendMessage(ConfigLoad.cfg.getString("Language.ReloadSuccess"));
			}
		}else {
			ConfigLoad.reloadConfigs();
			sender.sendMessage(ConfigLoad.cfg.getString("Language.ReloadSuccess"));
		}
	}
}
