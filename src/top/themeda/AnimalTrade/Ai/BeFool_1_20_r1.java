package top.themeda.AnimalTrade.Ai;

import org.bukkit.craftbukkit.v1_20_R1.entity.CraftEntity;
import org.bukkit.entity.Entity;

import net.minecraft.world.entity.EntityInsentient;

public class BeFool_1_20_r1 {
	public static void clear(Entity e) {
		EntityInsentient en = (EntityInsentient)((CraftEntity)e).getHandle();
		en.fw();
	}
}
