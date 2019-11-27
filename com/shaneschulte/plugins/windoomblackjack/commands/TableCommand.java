/*    */ package com.shaneschulte.plugins.windoomblackjack.commands;
/*    */ 
/*    */ import com.shaneschulte.plugins.windoomblackjack.managers.TableManager;
/*    */ import java.io.IOException;
/*    */ import java.util.LinkedList;
/*    */ import org.bukkit.ChatColor;
/*    */ import org.bukkit.command.Command;
/*    */ import org.bukkit.entity.Player;
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
/*    */ 
/*    */ public class TableCommand
/*    */   implements BaseCommand
/*    */ {
/*    */   public boolean execute(Player p, Command cmdObj, String label, String cmd, LinkedList<String> args) {
/* 24 */     if (cmd.equalsIgnoreCase("add")) {
/* 25 */       if (TableManager.addInterface(p)) {
/* 26 */         p.sendMessage(ChatColor.GREEN + "[Blackjack] Please right click the 'hit' block.");
/*    */       } else {
/* 28 */         p.sendMessage(ChatColor.RED + "[Blackjack] You are already creating a table!");
/*    */       } 
/* 30 */     } else if (cmd.equalsIgnoreCase("finish")) {
/* 31 */       if (TableManager.finishInterface(p)) {
/* 32 */         p.sendMessage(ChatColor.GREEN + "[Blackjack] Table created successfully.");
/*    */       } else {
/* 34 */         p.sendMessage(ChatColor.RED + "[Blackjack] You are not finished creating a table.");
/*    */       } 
/* 36 */     } else if (cmd.equalsIgnoreCase("save")) {
/*    */       try {
/* 38 */         TableManager.updateConfig();
/* 39 */       } catch (IOException ex) {
/* 40 */         p.sendMessage(ChatColor.RED + "[Blackjack] Error saving to tables.yml!");
/* 41 */         return true;
/*    */       } 
/* 43 */       p.sendMessage(ChatColor.GREEN + "[Blackjack] tables.yml updated successfully.");
/*    */     }
/* 45 */     else if (cmd.equalsIgnoreCase("clear")) {
/* 46 */       TableManager.removeTables();
/* 47 */       p.sendMessage(ChatColor.GREEN + "[Blackjack] Tables cleared.");
/*    */     }
/* 49 */     else if (!cmd.equalsIgnoreCase("list")) {
/*    */ 
/*    */       
/* 52 */       if (cmd.equalsIgnoreCase("cancel"))
/*    */       {
/* 54 */         if (TableManager.removeInterface(p)) {
/* 55 */           p.sendMessage(ChatColor.GREEN + "[Blackjack] Table creation cancelled.");
/*    */         } else {
/* 57 */           p.sendMessage(ChatColor.RED + "[Blackjack] You aren't creating a table.");
/*    */         }  } 
/*    */     } 
/* 60 */     return true;
/*    */   }
/*    */ }


/* Location:              G:\Users\schul\Documents\NetBeansProjects\WindoomBlackjack\dist\WindoomBlackjack.jar!\com\shaneschulte\plugins\windoomblackjack\commands\TableCommand.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.2
 */