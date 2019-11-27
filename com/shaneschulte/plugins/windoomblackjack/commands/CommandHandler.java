/*    */ package com.shaneschulte.plugins.windoomblackjack.commands;
/*    */ 
/*    */ import java.util.Arrays;
/*    */ import java.util.LinkedList;
/*    */ import org.bukkit.ChatColor;
/*    */ import org.bukkit.command.Command;
/*    */ import org.bukkit.command.CommandExecutor;
/*    */ import org.bukkit.command.CommandSender;
/*    */ import org.bukkit.entity.Player;
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
/*    */ public class CommandHandler
/*    */   implements CommandExecutor
/*    */ {
/*    */   JavaPlugin plugin;
/*    */   
/* 25 */   public CommandHandler(JavaPlugin pl) { this.plugin = pl; }
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean onCommand(CommandSender sender, Command cmdObj, String label, String[] args) {
/* 30 */     label = label.toLowerCase();
/* 31 */     if (sender instanceof Player) {
/* 32 */       if (args.length == 0) {
/* 33 */         sender.sendMessage(ChatColor.GOLD + " ---- Blackjack Help ---- ");
/* 34 */         sender.sendMessage(ChatColor.GOLD + "/" + label + " - Aliases: /21, /blackjack");
/* 35 */         sender.sendMessage(ChatColor.GOLD + "/" + label + " table - Table commands.");
/* 36 */         sender.sendMessage(ChatColor.GOLD + " ------------------------ ");
/*    */       } else {
/*    */         
/* 39 */         String cmd = args[0];
/* 40 */         if (cmd.equalsIgnoreCase("table")) {
/* 41 */           if (sender.hasPermission("blackjack.table")) {
/* 42 */             if (args.length == 1) {
/* 43 */               sender.sendMessage(ChatColor.GOLD + "/" + label + " table add - Creates a new table.");
/* 44 */               sender.sendMessage(ChatColor.GOLD + "/" + label + " table clear - Removes all tables.");
/* 45 */               sender.sendMessage(ChatColor.GOLD + "/" + label + " table list - Lists all tables.");
/* 46 */               sender.sendMessage(ChatColor.GOLD + "/" + label + " table save - Save all tables to tables.yml.");
/* 47 */               sender.sendMessage(ChatColor.GOLD + "/" + label + " table finish - Finish creating a table.");
/* 48 */               sender.sendMessage(ChatColor.GOLD + "/" + label + " table cancel - Cancels new table creation.");
/*    */             } else {
/*    */               
/* 51 */               LinkedList<String> list = new LinkedList<>(Arrays.asList(args));
/* 52 */               list.pollFirst();
/* 53 */               list.pollFirst();
/* 54 */               return (new TableCommand()).execute((Player)sender, cmdObj, label, args[1], list);
/*    */             } 
/*    */           } else {
/*    */             
/* 58 */             return false;
/*    */           } 
/*    */         }
/*    */       } 
/*    */     } else {
/*    */       
/* 64 */       sender.sendMessage(ChatColor.RED + "You must be a player to do that!");
/*    */     } 
/* 66 */     return true;
/*    */   }
/*    */ }


/* Location:              G:\Users\schul\Documents\NetBeansProjects\WindoomBlackjack\dist\WindoomBlackjack.jar!\com\shaneschulte\plugins\windoomblackjack\commands\CommandHandler.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.2
 */