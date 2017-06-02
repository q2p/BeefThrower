package q2p.beefthrower;

import java.util.Random;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Item;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapelessRecipe;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.util.Vector;

public class BeefThrower extends JavaPlugin implements Listener {
	public void onEnable() {
		ItemStack hookStack = new ItemStack(Material.GOLD_HOE);
		ItemMeta hookMeta = hookStack.getItemMeta();
		hookMeta.setDisplayName("Hook");
		hookStack.setItemMeta(hookMeta);
		ShapelessRecipe hookRec = new ShapelessRecipe(hookStack);
		hookRec.addIngredient(Material.GOLD_HOE);
		hookRec.addIngredient(Material.FISHING_ROD);
		//hookRec.setIngredient('C', Material.STICK);
		getServer().addRecipe(hookRec);
		getServer().getPluginManager().registerEvents(this, this);
	}
	
	public void onDisable() {
	}
	@EventHandler
	public void onDamage(EntityDamageByEntityEvent event) {
		DamageCause cause = event.getCause();
		if(cause == DamageCause.ENTITY_ATTACK || cause == DamageCause.BLOCK_EXPLOSION || cause == DamageCause.ENTITY_EXPLOSION || cause == DamageCause.LIGHTNING || cause == DamageCause.PROJECTILE) {
			if(event.getEntityType() == EntityType.PIG) {
				//int damage = (int)event.getDamage();
				int damage = 32;
				Location loc = event.getEntity().getLocation();
				Random rnd = new Random();
				for(int i = 0; i < damage; i++) {
					ItemStack itemStack = new ItemStack(Material.PORK, 1);
					int rot = rnd.nextInt(360);
					double x = Math.cos(Math.toRadians(rot));
					double z = Math.sin(Math.toRadians(rot));
					Location drop = loc.clone();
					drop.setX(drop.getX()+x*1.5);
					drop.setZ(drop.getZ()+z*1.5);
					Item item = event.getEntity().getWorld().dropItem(drop, itemStack);
					item.setVelocity(new Vector(x*0.2,0.6,z*0.2));
				}
			}
		}
	}
}
