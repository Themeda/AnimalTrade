package top.themeda.AnimalTrade.Commands;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.EntityType;

public class TypeCommand {
	public void parseCommand(CommandSender sender,String[] args) {
		EntityType[] types = EntityType.values();
		sender.sendMessage("§2§l§m               §l§c动§l§b物§l§6交§l§d易§2§l§m               ");
		sender.sendMessage("§6§l可用的动物种类：");
		String line ="";
		for(EntityType tp:types) {
			line += " | "+tp.name();
		}
		sender.sendMessage(line);
	}
}
