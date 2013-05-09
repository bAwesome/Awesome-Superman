package me.C0nsole.BeSuperman;



import java.awt.List;
import java.util.ArrayList;
import java.util.HashMap;

import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
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
	 
    @Override
    public void onDisable() {
        System.out.println("[BeSuperman] v1.0 has been disabled.");
    }
 
    @Override
    public void onEnable() {
        System.out.println("[BeSuperman] v1.0 has been enabled.");
        PluginManager pm = this.getServer().getPluginManager();
        pm.registerEvents(new BeSupermanListener(this), this);
    }
    
    ArrayList<String> supermen = new ArrayList<String>();
    public boolean onCommand(CommandSender sender, Command cmd, String commandlabel, String [] args){
        Player player = (Player) sender;
        if(commandlabel.equalsIgnoreCase("supermanon")){
            if(player.hasPermission("superman.use")){
            	player.setMaxHealth(100);
            	Inventory inv = player.getInventory();
            	if(inv instanceof PlayerInventory){
            		 ItemStack boots = new ItemStack(Material.LEATHER_BOOTS);
            		 LeatherArmorMeta meta = (LeatherArmorMeta) boots.getItemMeta();
            		 meta.setColor(Color.RED);
            		 boots.setItemMeta(meta);
            		 player.getInventory().setBoots(boots);
            		 ItemStack pants = new ItemStack(Material.LEATHER_LEGGINGS);
            		 LeatherArmorMeta meta1 = (LeatherArmorMeta) pants.getItemMeta();
            		 meta1.setColor(Color.BLUE);
            		 pants.setItemMeta(meta1);
            		 player.getInventory().setLeggings(pants);
            		 ItemStack shirt = new ItemStack(Material.LEATHER_CHESTPLATE);
            		 LeatherArmorMeta meta2 = (LeatherArmorMeta) shirt.getItemMeta();
            		 meta2.setColor(Color.BLUE);
            		 shirt.setItemMeta(meta2);
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
                supermen.add(player.getName());
                //1 = isSupeman
                //2 = isNotSuperman
            }
            else{
            	player.sendMessage(ChatColor.DARK_RED + "No permission!");
        }

    }
        
            if(commandlabel.equalsIgnoreCase("supermanoff")){
                if(player.hasPermission("superman.use")){
            	player.setAllowFlight(false);
            	player.setFlying(false);
            	player.setMaxHealth(20);
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
            	supermen.remove(player.getName());
            	}
                else{
                	player.sendMessage(ChatColor.DARK_RED + "No permission!");
                }
        }
		return false;
    }
}
