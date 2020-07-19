package top.themeda.AnimalTrade.Commands;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import top.themeda.AnimalTrade.AnimalTradePlugin;
import top.themeda.AnimalTrade.ConfigLoad;

public class SelectCommand {
	public void parseCommand(CommandSender sender,String[] args) {
		if(sender instanceof Player) {
			Player p = (Player)sender;
			if(!p.hasPermission("animaltrade.select")&&!p.isOp()) {
				p.sendMessage(ConfigLoad.cfg.getString("Language.HaveNoPremission"));
				return;
			}
			if(args.length!=1) {
				p.sendMessage(ConfigLoad.cfg.getString("Language.WrongCommand").replace("%arg%", "/at select"));
				return;
			}
			AnimalTradePlugin.interact.put(p, "select");
			p.sendMessage(ConfigLoad.cfg.getString("Language.SelectEntity","[§l§c动§l§b物§l§6交§l§d易§r]:§a现在右键你需要选择的实体！(如果你看到这条消息，请在配置文件中的语言部分增加SelectEntity节点并自定义信息(Language.SelectEntity))"));
		}else {
			sender.sendMessage(ConfigLoad.cfg.getString("Language.PlayerOnly"));
			return;
		}
	}
}
