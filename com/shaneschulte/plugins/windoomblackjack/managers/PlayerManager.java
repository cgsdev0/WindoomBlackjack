/*    */ package com.shaneschulte.plugins.windoomblackjack.managers;
/*    */ 
/*    */ import com.shaneschulte.plugins.windoomblackjack.BlackjackPlayer;
/*    */ import java.util.ArrayList;
/*    */ import java.util.List;
/*    */ import org.bukkit.ChatColor;
/*    */ import org.bukkit.Location;
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
/*    */ public final class PlayerManager
/*    */ {
/*    */   private static List<BlackjackPlayer> players;
/*    */   
/* 23 */   public static void enable(JavaPlugin plugin) { players = new ArrayList<>(); }
/*    */ 
/*    */   
/*    */   public static void disable() {
/* 27 */     for (BlackjackPlayer bp : players) bp.destroy(); 
/* 28 */     players.clear();
/*    */   }
/*    */   
/*    */   public static BlackjackPlayer addPlayer(Player p, Location seat) {
/* 32 */     if (isSeatOccupied(seat)) return null; 
/* 33 */     BlackjackPlayer bp = new BlackjackPlayer(p, seat);
/* 34 */     p.sendMessage(ChatColor.AQUA + "[Blackjack] Hello! Enter a wager to get started, or press shift to get up.");
/* 35 */     players.add(bp);
/* 36 */     return bp;
/*    */   }
/*    */ 
/*    */   
/*    */   public static void removePlayer(Player p) {
/* 41 */     BlackjackPlayer bp = null;
/* 42 */     for (BlackjackPlayer temp : players) {
/* 43 */       if (temp.getPlayer().equals(p)) {
/* 44 */         bp = temp;
/*    */         break;
/*    */       } 
/*    */     } 
/* 48 */     if (bp != null) {
/* 49 */       bp.destroy();
/* 50 */       players.remove(bp);
/*    */     } 
/*    */   }
/*    */   
/*    */   public static boolean startGame(Player p, int wager) {
/* 55 */     BlackjackPlayer bp = getBlackjackPlayer(p);
/* 56 */     if (bp == null) return false; 
/* 57 */     return bp.startGame(wager);
/*    */   }
/*    */   
/*    */   public static BlackjackPlayer getBlackjackPlayer(Player p) {
/* 61 */     for (BlackjackPlayer temp : players) {
/* 62 */       if (temp.getPlayer().equals(p)) return temp; 
/*    */     } 
/* 64 */     return null;
/*    */   }
/*    */   
/*    */   public static boolean isSeatOccupied(Location seat) {
/* 68 */     for (BlackjackPlayer temp : players) {
/* 69 */       if (temp.getSeat().equals(seat)) return true; 
/*    */     } 
/* 71 */     return false;
/*    */   }
/*    */   
/*    */   public static boolean isBlackjackPlayer(Player p) {
/* 75 */     for (BlackjackPlayer temp : players) {
/* 76 */       if (temp.getPlayer().equals(p)) return true; 
/*    */     } 
/* 78 */     return false;
/*    */   }
/*    */ }


/* Location:              G:\Users\schul\Documents\NetBeansProjects\WindoomBlackjack\dist\WindoomBlackjack.jar!\com\shaneschulte\plugins\windoomblackjack\managers\PlayerManager.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.2
 */