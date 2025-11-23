package top.themeda.AnimalTrade.Ai;

import org.bukkit.craftbukkit.v1_19_R3.entity.CraftEntity;
import org.bukkit.entity.Entity;

import net.minecraft.world.entity.EntityInsentient;

public class BeFool_1_19_r3 {
	public static void clear(Entity e) {
		EntityInsentient en = (EntityInsentient)((CraftEntity)e).getHandle();
		en.fw();
	}
}
