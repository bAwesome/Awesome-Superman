package me.C0nsole.BeSuperman;
 
 
 


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
 
import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.inventory.meta.LeatherArmorMeta;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
 
public class BeSuperman extends JavaPlugin{
 
    List<String> supermen = new ArrayList<String>();
         
    @Override
    public void onDisable() {
        System.out.println("[BeSuperman] v2.4 has been disabled.");
    }
 
    @SuppressWarnings("unused")
	@Override
    public void onEnable() {
        System.out.println("[BeSuperman] v2.4 has been enabled.");
        PluginManager pm = this.getServer().getPluginManager();
        pm.registerEvents(new BeSupermanListener(this), this);
        try {
            Metrics metrics = new Metrics(this);
            metrics.start();
        } catch (IOException e) {
            // Failed to submit the stats :-(
        	System.out.println("Mcstats failed to load the stats of BeSuperman!");
        }
        getConfig().options().copyDefaults(true);
        saveConfig();
		boolean useautoupdater = getConfig().getBoolean("use-autoupdater");
        if(useautoupdater = true){
        	Updater updater = new Updater(this, "besuperman", this.getFile(), Updater.UpdateType.DEFAULT, true);
        }
    }
 
    public List<String> getList() {
        return supermen;
    }
   
    public boolean onCommand(CommandSender sender, Command cmd, String commandlabel, String [] args){
        Player player = (Player) sender;
        if(commandlabel.equalsIgnoreCase("superman")){
            if(player.hasPermission("superman.use")){
            	if(args.length ==0){
            		player.sendMessage(ChatColor.RED + "[BeSuperman]" + ChatColor.GREEN + "This plugin is developed by " + ChatColor.GOLD + ChatColor.ITALIC + "garrett2smart87" + ChatColor.RESET);
            		player.sendMessage(ChatColor.GREEN + "Do " + ChatColor.GOLD + ChatColor.ITALIC + "/superman help " + ChatColor.RESET + ChatColor.GREEN + "for a help!");
            	}
            	else if(args.length == 1){
                if(args[0].equalsIgnoreCase("on")){
                    player.setMaxHealth(100);
                    player.setHealth(100);
                    Inventory inv = player.getInventory();
                    if(inv instanceof PlayerInventory){
                             ItemStack boots = new ItemStack(Material.LEATHER_BOOTS);
                             LeatherArmorMeta meta = (LeatherArmorMeta) boots.getItemMeta();
                             meta.setColor(Color.RED);
                             boots.setItemMeta(meta);
                             boots.addEnchantment(Enchantment.getById(34), 3);
                             player.getInventory().setBoots(boots);
                             ItemStack pants = new ItemStack(Material.LEATHER_LEGGINGS);
                             LeatherArmorMeta meta1 = (LeatherArmorMeta) pants.getItemMeta();
                             meta1.setColor(Color.BLUE);
                             pants.setItemMeta(meta1);
                             pants.addEnchantment(Enchantment.getById(34), 3);
                             player.getInventory().setLeggings(pants);
                             ItemStack shirt = new ItemStack(Material.LEATHER_CHESTPLATE);
                             LeatherArmorMeta meta2 = (LeatherArmorMeta) shirt.getItemMeta();
                             meta2.setColor(Color.BLUE);
                             shirt.setItemMeta(meta2);
                             shirt.addEnchantment(Enchantment.getById(34), 3);
                             player.getInventory().setChestplate(shirt);
                             ItemStack skull = new ItemStack(397, 1, (short) 3);
                             SkullMeta meta3 = (SkullMeta) skull.getItemMeta();
                             meta3.setOwner("garrett2smart87");
                             skull.setItemMeta(meta3);
                             player.getInventory().setHelmet(skull);
                            }
                    player.setAllowFlight(true);
                    player.setFlying(true);
                    player.sendMessage(ChatColor.GREEN + "Transformation to " + ChatColor.AQUA + ChatColor.ITALIC + "Super" + ChatColor.RED + ChatColor.ITALIC + "Man " + ChatColor.RESET + ChatColor.GREEN + "Complete!");
                    player.addPotionEffect(new PotionEffect(PotionEffectType.WATER_BREATHING, Integer.MAX_VALUE, 1, false));
                    player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, Integer.MAX_VALUE, 1, false));
                    supermen.add(player.getName());
                    //1 = isSupeman
                    //2 = isNotSuperman
            }
                else if(args[0].equalsIgnoreCase("off")){
                    player.setAllowFlight(false);
                    player.setFlying(false);
                    player.setMaxHealth(20);
                    player.setHealth(20);
                    player.sendMessage(ChatColor.GREEN + "Transformation to " + ChatColor.RESET + "Human Form" + ChatColor.GREEN + " Complete!");
                    Inventory inv = player.getInventory();
                    if(inv instanceof PlayerInventory){
                            PlayerInventory pinv = (PlayerInventory) inv;
                            pinv.setChestplate(new ItemStack(Material.AIR));
                            pinv.setHelmet(new ItemStack(Material.AIR));
                            pinv.setLeggings(new ItemStack(Material.AIR));
                            pinv.setBoots(new ItemStack(Material.AIR));
                           
                    }
                    player.removePotionEffect(PotionEffectType.WATER_BREATHING);
                    player.removePotionEffect(PotionEffectType.SPEED);
                    supermen.remove(player.getName());
                }
                else if(args[0].equalsIgnoreCase("help")){
                	player.sendMessage(ChatColor.RED + "[BeSuperman]" + ChatColor.GREEN + "These are the commands:" + ChatColor.RESET);
                	player.sendMessage(ChatColor.GOLD + "/superman " + ChatColor.GREEN + " - Display credits for the plugin");
                	player.sendMessage(ChatColor.GOLD + "/superman help " + ChatColor.RESET + ChatColor.GREEN + "- See the commands for the plugin.");
                	player.sendMessage(ChatColor.GOLD + "/superman on " + ChatColor.GREEN + "- Become superman!");
                	player.sendMessage(ChatColor.GOLD + "/superman off" + ChatColor.GREEN + "- Turn back to a human!");
                	
                	
                }
        }
            	else if(args.length >= 2){
            		player.sendMessage(ChatColor.RED + "The correct usage is " + ChatColor.GOLD + ChatColor.ITALIC + "/superman [on/off]" + ChatColor.RESET + ChatColor.RED + " !");
            	}
 
      }
    }
        else{
            player.sendMessage(ChatColor.DARK_RED + "No permission!");  
}
		return false;
    }
}