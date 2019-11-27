/*    */ package com.shaneschulte.plugins.windoomblackjack.managers;
/*    */ 
/*    */ import java.util.logging.Level;
/*    */ import net.md_5.bungee.api.ChatColor;
/*    */ import net.milkbowl.vault.economy.Economy;
/*    */ import org.bukkit.entity.Player;
/*    */ import org.bukkit.plugin.Plugin;
/*    */ import org.bukkit.plugin.PluginManager;
/*    */ import org.bukkit.plugin.RegisteredServiceProvider;
/*    */ import org.bukkit.plugin.java.JavaPlugin;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public final class MoneyManager
/*    */ {
/*    */   private static JavaPlugin plugin;
/*    */   public static Economy economy;
/*    */   
/* 26 */   public static void announce(Player p, int amount) { plugin.getServer().broadcastMessage(ChatColor.GOLD + "[Blackjack] High roller alert! " + p.getName() + " just wagered $" + Integer.toString(amount) + "!"); }
/*    */   
/*    */   public static void enable(JavaPlugin pl) {
/* 29 */     plugin = pl;
/* 30 */     if (!setupEconomy()) {
/* 31 */       plugin.getLogger().log(Level.INFO, "Vault not found! Disabling plugin.");
/* 32 */       PluginManager pluginManager = plugin.getServer().getPluginManager();
/* 33 */       pluginManager.disablePlugin((Plugin)plugin);
/*    */       return;
/*    */     } 
/*    */   }
/*    */   
/* 38 */   public static void pay(Player p, int amount) { economy.depositPlayer(p.getName(), amount); }
/*    */ 
/*    */   
/* 41 */   public static void charge(Player p, int amount) { economy.withdrawPlayer(p.getName(), amount); }
/*    */ 
/*    */   
/* 44 */   public static boolean canAfford(Player p, int amount) { return economy.has(p.getName(), amount); }
/*    */   
/*    */   private static boolean setupEconomy() {
/* 47 */     RegisteredServiceProvider<Economy> economyProvider = plugin.getServer().getServicesManager().getRegistration(Economy.class);
/* 48 */     if (economyProvider != null) {
/* 49 */       economy = (Economy)economyProvider.getProvider();
/*    */     }
/*    */     
/* 52 */     return (economy != null);
/*    */   }
/*    */ 
/*    */   
/* 56 */   public static void announceWin(Player player, int wager) { plugin.getServer().broadcastMessage(ChatColor.GREEN + "[Blackjack] " + player.getName() + " just won the hand!"); }
/*    */ 
/*    */ 
/*    */   
/* 60 */   public static void announceLoss(Player player, int wager) { plugin.getServer().broadcastMessage(ChatColor.RED + "[Blackjack] " + player.getName() + " just lost the hand!"); }
/*    */ 
/*    */ 
/*    */   
/* 64 */   public static void announcePush(Player player, int wager) { plugin.getServer().broadcastMessage(ChatColor.GRAY + "[Blackjack] " + player.getName() + " and the dealer pushed."); }
/*    */ 
/*    */ 
/*    */   
/* 68 */   public static void announceBlackjack(Player player, int wager) { plugin.getServer().broadcastMessage(ChatColor.GREEN + "[Blackjack] " + player.getName() + " just won with a blackjack!"); }
/*    */ }


/* Location:              G:\Users\schul\Documents\NetBeansProjects\WindoomBlackjack\dist\WindoomBlackjack.jar!\com\shaneschulte\plugins\windoomblackjack\managers\MoneyManager.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.2
 */