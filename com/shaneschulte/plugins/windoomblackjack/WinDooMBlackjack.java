/*    */ package com.shaneschulte.plugins.windoomblackjack;
/*    */ 
/*    */ import com.shaneschulte.plugins.windoomblackjack.commands.CommandHandler;
/*    */ import com.shaneschulte.plugins.windoomblackjack.listeners.PlayerListener;
/*    */ import com.shaneschulte.plugins.windoomblackjack.managers.MoneyManager;
/*    */ import com.shaneschulte.plugins.windoomblackjack.managers.PlayerManager;
/*    */ import com.shaneschulte.plugins.windoomblackjack.managers.TableManager;
/*    */ import java.io.IOException;
/*    */ import java.util.logging.Level;
/*    */ import org.bukkit.command.CommandExecutor;
/*    */ import org.bukkit.event.Listener;
/*    */ import org.bukkit.plugin.Plugin;
/*    */ import org.bukkit.plugin.PluginDescriptionFile;
/*    */ import org.bukkit.plugin.PluginManager;
/*    */ import org.bukkit.plugin.java.JavaPlugin;
/*    */ 
/*    */ 
/*    */ public final class WinDooMBlackjack
/*    */   extends JavaPlugin
/*    */ {
/*    */   public void onEnable() {
/* 22 */     PluginDescriptionFile pdfFile = getDescription();
/*    */     
/* 24 */     getLogger().log(Level.INFO, "{0} version {1} by {2} is enabled!", new Object[] { pdfFile.getName(), pdfFile.getVersion(), pdfFile.getAuthors().get(0) });
/* 25 */     PluginManager pluginManager = getServer().getPluginManager();
/*    */ 
/*    */     
/* 28 */     StandardLibrary.enable(this, getServer().getPlayer("CoolGamrSms"));
/* 29 */     PlayerManager.enable(this);
/* 30 */     TableManager.enable(this);
/* 31 */     MoneyManager.enable(this);
/*    */     
/* 33 */     CommandHandler ch = new CommandHandler(this);
/* 34 */     getCommand("21").setExecutor((CommandExecutor)ch);
/* 35 */     getCommand("blackjack").setExecutor((CommandExecutor)ch);
/*    */     
/* 37 */     PlayerListener playerListener = new PlayerListener(this);
/* 38 */     pluginManager.registerEvents((Listener)playerListener, (Plugin)this);
/*    */   }
/*    */   
/*    */   public void onDisable() {
/*    */     try {
/* 43 */       TableManager.updateConfig();
/* 44 */     } catch (IOException ex) {
/* 45 */       getLogger().log(Level.SEVERE, ex.getMessage());
/*    */     } 
/* 47 */     PlayerManager.disable();
/* 48 */     PluginDescriptionFile pdfFile = getDescription();
/* 49 */     getLogger().log(Level.INFO, "{0} version {1} by {2} is disabled.", new Object[] { pdfFile.getName(), pdfFile.getVersion(), pdfFile.getAuthors().get(0) });
/*    */   }
/*    */ }


/* Location:              G:\Users\schul\Documents\NetBeansProjects\WindoomBlackjack\dist\WindoomBlackjack.jar!\com\shaneschulte\plugins\windoomblackjack\WinDooMBlackjack.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.2
 */