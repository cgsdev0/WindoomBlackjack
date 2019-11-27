/*    */ package com.shaneschulte.plugins.windoomblackjack;
/*    */ 
/*    */ import org.bukkit.ChatColor;
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
/*    */ public class Card
/*    */ {
/* 16 */   private String[] suitNames = new String[] { ChatColor.BLACK + "♠", ChatColor.DARK_RED + "♦", ChatColor.DARK_RED + "♥", ChatColor.BLACK + "♣" };
/* 17 */   private String[] rankNames = new String[] { "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K", "A" };
/*    */   private int suit;
/*    */   private int rank;
/*    */   
/*    */   public Card(int rank, int suit) {
/* 22 */     this.suit = suit;
/* 23 */     this.rank = rank;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/* 28 */   public String toString() { return ChatColor.WHITE + this.rankNames[this.rank] + "" + this.suitNames[this.suit]; }
/*    */ 
/*    */ 
/*    */   
/* 32 */   public int getRank() { return this.rank; }
/*    */ 
/*    */   
/* 35 */   public int getSuit() { return this.suit; }
/*    */ }


/* Location:              G:\Users\schul\Documents\NetBeansProjects\WindoomBlackjack\dist\WindoomBlackjack.jar!\com\shaneschulte\plugins\windoomblackjack\Card.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.2
 */