package top.themeda.AnimalTrade;

import java.util.Map.Entry;

import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;

public class SelectedTip implements Runnable{
	@Override
	public void run() {
		for(Entry<Player, Entity> is:AnimalTradePlugin.pmap.entrySet()) {
			String name = is.getValue().getCustomName();
			String msg = ConfigLoad.cfg.getString("Language.SelectTip");
			if(name==null) {
				msg = msg.replace("%arg%", is.getValue().getName());
			}else {
				msg = msg.replace("%arg%", is.getValue().getCustomName());
			}
			if(msg!=null&&!msg.equals("")) {
				is.getKey().spigot().sendMessage(ChatMessageType.ACTION_BAR,TextComponent.fromLegacyText(msg));
			}
		}
	}
}
