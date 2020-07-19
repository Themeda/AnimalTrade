package top.themeda.AnimalTrade.Commands;

import org.bukkit.World;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;

import top.themeda.AnimalTrade.ConfigLoad;
import top.themeda.AnimalTrade.Here;

public class CreateCommand {
	public void parseCommand(CommandSender sender,String[] args) {
		if(sender instanceof Player) {
			Player p = (Player)sender;
			if(!p.hasPermission("animaltrade.create")&&!p.isOp()) {
				p.sendMessage(ConfigLoad.cfg.getString("Language.HaveNoPremission"));
				return;
			}
			if(args.length!=2) {
				p.sendMessage(ConfigLoad.cfg.getString("Language.WrongCommand").replace("%arg%", "/at create <type>"));
				return;
			}
			try{
				EntityType entitytype = EntityType.valueOf(args[1].toUpperCase());
				World world = p.getWorld();
				Entity entity = world.spawnEntity(p.getLocation(), entitytype);
				Here.handleNewAnimal(entity,p);
				p.sendMessage(ConfigLoad.cfg.getString("Language.CreateSuccess"));
				return;
			}catch(Exception e) {
				p.sendMessage(ConfigLoad.cfg.getString("Language.TypeWrong"));
				return;
			}
		}else {
			sender.sendMessage(ConfigLoad.cfg.getString("Language.PlayerOnly"));
			return;
		}
	}
}
