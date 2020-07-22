package top.themeda.AnimalTrade.Ai;

import java.lang.reflect.Field;
import java.util.Set;

import org.bukkit.craftbukkit.v1_13_R2.entity.CraftEntity;
import org.bukkit.entity.Entity;

import net.minecraft.server.v1_13_R2.EntityInsentient;
import net.minecraft.server.v1_13_R2.PathfinderGoalSelector;

public class BeFool_1_13 {
	public static void clear(Entity e) {
		EntityInsentient en = (EntityInsentient)((CraftEntity)e).getHandle();
		PathfinderGoalSelector pfd = en.goalSelector;
		try {
            Class<PathfinderGoalSelector> clazz = PathfinderGoalSelector.class;
            Field field = clazz.getDeclaredField("b");
            field.setAccessible(true);
			Set<?> set = (Set<?>) field.get(pfd);
            set.clear();
        } catch(IllegalAccessException | IllegalArgumentException | NoSuchFieldException | SecurityException ex) {
            
        }
	}
}
