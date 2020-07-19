package top.themeda.AnimalTrade;

import java.util.List;
import java.util.UUID;

import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.MerchantRecipe;

public class Trader {
	private UUID uuid;
	private List<MerchantRecipe> recipe;
	private boolean enable;
	private Inventory inv;
	public Trader() {}
	public void setEditInv(Inventory inv) {
		this.inv=inv;
	}
	public Inventory getEditInv() {
		return this.inv;
	}
	public void setUUID(UUID uid) {
		this.uuid=uid;
	}
	public void setRecipe(List<MerchantRecipe> list) {
		this.recipe=list;
	}
	public void setEnable(boolean bol) {
		this.enable=bol;
	}
	public UUID getUUID() {
		return this.uuid;
	}
	public List<MerchantRecipe> getRecipe() {
		return this.recipe;
	}
	public boolean getEnable() {
		return this.enable;
	}
}
