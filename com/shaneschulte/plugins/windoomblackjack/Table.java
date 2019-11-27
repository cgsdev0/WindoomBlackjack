/*    */ package com.shaneschulte.plugins.windoomblackjack;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ import java.util.List;
/*    */ import org.bukkit.Location;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class Table
/*    */ {
/*    */   private Location hit;
/*    */   private Location stay;
/*    */   private List<Location> seats;
/*    */   
/*    */   public Table(Location hit, Location stay) {
/* 21 */     this.hit = hit;
/* 22 */     this.stay = stay;
/* 23 */     this.seats = new ArrayList<>();
/*    */   }
/*    */   public Table(Location hit, Location stay, List<Location> seats) {
/* 26 */     this.hit = hit;
/* 27 */     this.stay = stay;
/* 28 */     this.seats = seats;
/*    */   }
/*    */   
/*    */   public boolean isSeat(Location l) {
/* 32 */     if (this.seats.isEmpty()) return false; 
/* 33 */     return this.seats.contains(l);
/*    */   }
/*    */ 
/*    */   
/* 37 */   public void addSeat(Location l) { this.seats.add(l); }
/*    */ 
/*    */   
/* 40 */   public Location getHit() { return this.hit; }
/*    */ 
/*    */   
/* 43 */   public Location getStay() { return this.stay; }
/*    */   
/*    */   public List<String> getSeats() {
/* 46 */     List<String> temp = new ArrayList<>();
/* 47 */     for (Location l : this.seats) {
/* 48 */       temp.add(StandardLibrary.serializeLoc(l));
/*    */     }
/* 50 */     return temp;
/*    */   }
/*    */ }


/* Location:              G:\Users\schul\Documents\NetBeansProjects\WindoomBlackjack\dist\WindoomBlackjack.jar!\com\shaneschulte\plugins\windoomblackjack\Table.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.2
 */