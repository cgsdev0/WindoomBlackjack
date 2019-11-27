/*    */ package com.shaneschulte.plugins.windoomblackjack.interfaces;
/*    */ 
/*    */ import com.shaneschulte.plugins.windoomblackjack.Table;
/*    */ import java.util.ArrayList;
/*    */ import java.util.List;
/*    */ import org.bukkit.ChatColor;
/*    */ import org.bukkit.Location;
/*    */ import org.bukkit.entity.Player;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class TableAddInterface
/*    */ {
/*    */   private int step;
/*    */   private Player p;
/*    */   private Location hit;
/*    */   private Location stay;
/*    */   private List<Location> seats;
/*    */   private static final int STEP_HIT = 0;
/*    */   private static final int STEP_STAY = 1;
/*    */   private static final int STEP_SEAT = 2;
/*    */   private static final int STEP_DONE = 3;
/*    */   
/*    */   public TableAddInterface(Player p) {
/* 30 */     this.p = p;
/* 31 */     this.step = 0;
/* 32 */     this.seats = new ArrayList<>();
/*    */   }
/*    */ 
/*    */   
/* 36 */   public Player getPlayer() { return this.p; }
/*    */ 
/*    */   
/*    */   public void sendRightClick(Location l) {
/* 40 */     if (this.step == 0) {
/* 41 */       this.hit = l;
/* 42 */       this.p.sendMessage(ChatColor.GREEN + "[Blackjack] Hit block set. Right click to set the stay block.");
/*    */     }
/* 44 */     else if (this.step == 1) {
/* 45 */       this.stay = l;
/* 46 */       this.p.sendMessage(ChatColor.GREEN + "[Blackjack] Stay block set. Right click to add a seat.");
/*    */     }
/* 48 */     else if (this.step >= 2) {
/* 49 */       this.seats.add(l);
/* 50 */       this.p.sendMessage(ChatColor.GREEN + "[Blackjack] Seat added! Right click another seat or type " + ChatColor.YELLOW + "/21 table finish");
/*    */     } 
/* 52 */     if (this.step < 3) this.step++;
/*    */   
/*    */   }
/*    */   
/* 56 */   public boolean isComplete() { return (this.step == 3); }
/*    */ 
/*    */   
/* 59 */   public Table finish() { return new Table(this.hit, this.stay, this.seats); }
/*    */ }


/* Location:              G:\Users\schul\Documents\NetBeansProjects\WindoomBlackjack\dist\WindoomBlackjack.jar!\com\shaneschulte\plugins\windoomblackjack\interfaces\TableAddInterface.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.2
 */