package top.themeda.AnimalTrade.Commands;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.EntityType;

public class TypeCommand {
	public void parseCommand(CommandSender sender,String[] args) {
		EntityType[] types = EntityType.values();
		sender.sendMessage("��2��l��m               ��l��c����l��b���l��6����l��d�ס�2��l��m               ");
		sender.sendMessage("��6��l���õĶ������ࣺ");
		String line ="";
		for(EntityType tp:types) {
			line += " | "+tp.name();
		}
		sender.sendMessage(line);
	}
}
