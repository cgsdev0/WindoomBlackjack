/*    */ package com.shaneschulte.plugins.windoomblackjack;
/*    */ 
/*    */ import com.shaneschulte.plugins.windoomblackjack.managers.MoneyManager;
/*    */ import org.bukkit.ChatColor;
/*    */ import org.bukkit.Location;
/*    */ import org.bukkit.entity.ArmorStand;
/*    */ import org.bukkit.entity.Entity;
/*    */ import org.bukkit.entity.EntityType;
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
/*    */ 
/*    */ 
/*    */ public class BlackjackPlayer
/*    */ {
/*    */   private Player player;
/*    */   private Entity seatEntity;
/*    */   private Location seat;
/*    */   private Session session;
/*    */   
/*    */   public BlackjackPlayer(Player p, Location seat) {
/* 31 */     this.player = p;
/* 32 */     this.seat = seat;
/* 33 */     freezePlayer(seat);
/* 34 */     this.session = new Session(p);
/*    */   }
/*    */   
/* 37 */   public Player getPlayer() { return this.player; }
/*    */ 
/*    */   
/* 40 */   public Location getSeat() { return this.seat; }
/*    */   
/*    */   public void destroy() {
/* 43 */     unfreezePlayer();
/* 44 */     this.session.gameQuit();
/*    */   }
/*    */   
/* 47 */   public boolean isStarted() { return this.session.isPlaying(); }
/*    */ 
/*    */   
/*    */   public boolean startGame(int wager) {
/* 51 */     if (this.session.isPlaying()) return false; 
/* 52 */     if (!MoneyManager.canAfford(this.player, wager)) {
/* 53 */       this.player.sendMessage(ChatColor.RED + "[Blackjack] You can't afford this.");
/* 54 */       return true;
/*    */     } 
/* 56 */     if (wager < 1000 || wager > 100000) {
/* 57 */       this.player.sendMessage(ChatColor.RED + "[Blackjack] Your wager must be between $1000 and $100000.");
/* 58 */       return true;
/*    */     } 
/* 60 */     this.session.gameStart(wager, (wager >= 75000));
/* 61 */     return true;
/*    */   }
/*    */   
/* 64 */   public void gameHit() { if (this.session.isPlaying()) this.session.gameHit();
/*    */      }
/*    */   
/* 67 */   public void gameStay() { if (this.session.isPlaying()) this.session.gameStay();  }
/*    */   
/*    */   private void freezePlayer(Location seat) {
/* 70 */     double x = seat.getX() + 0.5D;
/* 71 */     double y = seat.getY() - 0.3D;
/* 72 */     double z = seat.getZ() + 0.5D;
/* 73 */     seat = new Location(seat.getWorld(), x, y, z, 270.0F, 90.0F);
/* 74 */     this.seatEntity = this.player.getWorld().spawnEntity(seat, EntityType.ARMOR_STAND);
/* 75 */     this.seatEntity.setPassenger((Entity)this.player);
/* 76 */     ArmorStand as = (ArmorStand)this.seatEntity;
/* 77 */     as.setGravity(true);
/* 78 */     as.setVisible(false);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   private void unfreezePlayer() {
/* 84 */     ArmorStand as = (ArmorStand)this.seatEntity;
/* 85 */     as.teleport((Entity)this.player);
/* 86 */     as.setHealth(0.0D);
/*    */   }
/*    */ }


/* Location:              G:\Users\schul\Documents\NetBeansProjects\WindoomBlackjack\dist\WindoomBlackjack.jar!\com\shaneschulte\plugins\windoomblackjack\BlackjackPlayer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.2
 */