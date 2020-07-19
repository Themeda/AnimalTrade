package top.themeda.AnimalTrade.Commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class ParseCommand implements CommandExecutor{
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(args.length!=0) {
			switch(args[0]) {
			case"cancel":
				CancelCommand canc = new CancelCommand();
				canc.parseCommand(sender, args);
				break;
			case"type":
				TypeCommand tc = new TypeCommand();
				tc.parseCommand(sender, args);
				break;
			case"reload":
				ReloadCommand rc = new ReloadCommand();
				rc.parseCommand(sender, args);
				break;
			case"delname":
				DelNameCommand dc = new DelNameCommand();
				dc.parseCommand(sender, args);
				break;
			case"select":
				SelectCommand sc = new SelectCommand();
				sc.parseCommand(sender, args);
				break;
			case"tphere":
				TphereCommand thc = new TphereCommand();
				thc.parseCommand(sender, args);
				break;
			case"create":
				CreateCommand cc = new CreateCommand();
				cc.parseCommand(sender, args);
				break;
			case"setname":
				SetNameCommand snc = new SetNameCommand();
				snc.parseCommand(sender, args);
				break;
			case"tp":
				TpCommand tpc = new TpCommand();
				tpc.parseCommand(sender, args);
				break;
				default:
					return false;
			}
		}
		return true;
	}
}
