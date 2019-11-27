/*    */ package com.shaneschulte.plugins.windoomblackjack.listeners;
/*    */ 
/*    */ import com.shaneschulte.plugins.windoomblackjack.BlackjackPlayer;
/*    */ import com.shaneschulte.plugins.windoomblackjack.WinDooMBlackjack;
/*    */ import com.shaneschulte.plugins.windoomblackjack.managers.PlayerManager;
/*    */ import com.shaneschulte.plugins.windoomblackjack.managers.TableManager;
/*    */ import org.bukkit.entity.Entity;
/*    */ import org.bukkit.entity.EntityType;
/*    */ import org.bukkit.entity.Player;
/*    */ import org.bukkit.event.EventHandler;
/*    */ import org.bukkit.event.EventPriority;
/*    */ import org.bukkit.event.Listener;
/*    */ import org.bukkit.event.block.Action;
/*    */ import org.bukkit.event.player.AsyncPlayerChatEvent;
/*    */ import org.bukkit.event.player.PlayerInteractEntityEvent;
/*    */ import org.bukkit.event.player.PlayerInteractEvent;
/*    */ import org.bukkit.event.player.PlayerQuitEvent;
/*    */ import org.bukkit.inventory.EquipmentSlot;
/*    */ import org.spigotmc.event.entity.EntityDismountEvent;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public final class PlayerListener
/*    */   implements Listener
/*    */ {
/*    */   WinDooMBlackjack plugin;
/*    */   
/* 31 */   public PlayerListener(WinDooMBlackjack plugin) { this.plugin = plugin; }
/*    */   
/*    */   @EventHandler(ignoreCancelled = true)
/*    */   public void onPiggyback(PlayerInteractEntityEvent e) {
/* 35 */     Entity clicked = e.getRightClicked();
/* 36 */     if (clicked instanceof Player) {
/* 37 */       Player p = (Player)clicked;
/* 38 */       BlackjackPlayer bp = PlayerManager.getBlackjackPlayer(p);
/* 39 */       if (bp != null && 
/* 40 */         bp.isStarted())
/* 41 */         e.setCancelled(true); 
/*    */     } 
/*    */   }
/*    */   
/*    */   @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
/*    */   public void onPlayerInteract(PlayerInteractEvent e) {
/* 47 */     if (e.getHand() == EquipmentSlot.OFF_HAND)
/* 48 */       return;  if (!e.getAction().equals(Action.RIGHT_CLICK_BLOCK) && !e.getAction().equals(Action.RIGHT_CLICK_AIR))
/* 49 */       return;  if (!e.hasBlock())
/* 50 */       return;  if (e.getPlayer().hasPermission("blackjack.table") && 
/* 51 */       !TableManager.sendRightClick(e.getPlayer(), e.getClickedBlock().getLocation()))
/*    */       return; 
/* 53 */     BlackjackPlayer bp = PlayerManager.getBlackjackPlayer(e.getPlayer());
/* 54 */     if (bp != null) {
/* 55 */       TableManager.passClick(bp, e.getClickedBlock().getLocation());
/*    */     } else {
/* 57 */       TableManager.checkSeat(e.getPlayer(), e.getClickedBlock().getLocation());
/*    */     } 
/*    */   }
/*    */   @EventHandler(ignoreCancelled = true)
/*    */   public void onPlayerExit(EntityDismountEvent e) {
/* 62 */     if (e.getEntityType() == EntityType.PLAYER) {
/* 63 */       Player p = (Player)e.getEntity();
/* 64 */       PlayerManager.removePlayer(p.getPlayer());
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   @EventHandler
/* 70 */   public void onPlayerLogout(PlayerQuitEvent e) { PlayerManager.removePlayer(e.getPlayer()); }
/*    */   
/*    */   @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
/*    */   public void onSpeak(AsyncPlayerChatEvent e) {
/* 74 */     String test = e.getMessage();
/* 75 */     int wager = 0;
/*    */     
/* 77 */     try { wager = Integer.parseInt(test); }
/* 78 */     catch (NumberFormatException ex) { return; }
/* 79 */      if (wager <= 0)
/* 80 */       return;  BlackjackPlayer bp = PlayerManager.getBlackjackPlayer(e.getPlayer());
/* 81 */     if (bp != null)
/* 82 */       e.setCancelled(bp.startGame(wager)); 
/*    */   }
/*    */ }


/* Location:              G:\Users\schul\Documents\NetBeansProjects\WindoomBlackjack\dist\WindoomBlackjack.jar!\com\shaneschulte\plugins\windoomblackjack\listeners\PlayerListener.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.2
 */