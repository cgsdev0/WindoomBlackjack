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
/*    */ public class Hand
/*    */ {
/*    */   private int value;
/*    */   private boolean soft;
/*    */   
/* 18 */   public int getValue() { return this.value; }
/*    */ 
/*    */   
/* 21 */   public boolean isSoft() { return this.soft; }
/*    */ 
/*    */ 
/*    */   
/* 25 */   public String toString() { return ChatColor.BLUE + " (" + ChatColor.GRAY + (this.soft ? "soft " : "hard ") + getValue() + ChatColor.BLUE + ")"; }
/*    */   
/*    */   public void addCard(Card c) {
/* 28 */     int addition = c.getRank() + 2;
/* 29 */     if (addition > 10) addition = 10; 
/* 30 */     this.value += addition;
/* 31 */     if (c.getRank() == 12) {
/* 32 */       this.value++;
/* 33 */       if (this.value > 21) {
/* 34 */         this.value -= 10;
/*    */       } else {
/* 36 */         this.soft = true;
/*    */       } 
/* 38 */     }  if (this.value > 21 && this.soft) {
/* 39 */       this.value -= 10;
/* 40 */       this.soft = false;
/*    */     } 
/*    */   }
/*    */ }


/* Location:              G:\Users\schul\Documents\NetBeansProjects\WindoomBlackjack\dist\WindoomBlackjack.jar!\com\shaneschulte\plugins\windoomblackjack\Hand.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.2
 */