package me.C0nsole.BeSuperman;



import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;

public class BeSupermanListener implements Listener{
	public static BeSuperman plugin;

	public BeSupermanListener(BeSuperman instance) {
		plugin = instance;
	}
	@EventHandler
	public void onEntityDamage(EntityDamageEvent event) {
		Entity entity = event.getEntity();
		if(supermen.contains(player.getName())){
			
		}
		
	}
}