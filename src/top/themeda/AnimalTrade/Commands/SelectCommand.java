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
			p.sendMessage(ConfigLoad.cfg.getString("Language.SelectEntity","[��l��c����l��b���l��6����l��d�ס�r]:��a�����Ҽ�����Ҫѡ���ʵ�壡(����㿴��������Ϣ�����������ļ��е����Բ�������SelectEntity�ڵ㲢�Զ�����Ϣ(Language.SelectEntity))"));
		}else {
			sender.sendMessage(ConfigLoad.cfg.getString("Language.PlayerOnly"));
			return;
		}
	}
}
