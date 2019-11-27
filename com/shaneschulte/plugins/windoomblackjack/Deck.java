/*    */ package com.shaneschulte.plugins.windoomblackjack;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ import java.util.Collections;
/*    */ import java.util.List;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class Deck
/*    */ {
/*    */   private List<Card> deck;
/*    */   
/*    */   public Deck() {
/* 19 */     this.deck = new ArrayList<>();
/* 20 */     reset();
/* 21 */     shuffle();
/*    */   }
/*    */   public void reset() {
/* 24 */     this.deck.clear();
/* 25 */     for (int i = 0; i < 13; i++) {
/* 26 */       for (int j = 0; j < 4; j++)
/* 27 */         this.deck.add(new Card(i, j)); 
/*    */     } 
/*    */   }
/*    */   
/*    */   public void shuffle() {
/* 32 */     reset();
/* 33 */     Collections.shuffle(this.deck);
/*    */   }
/*    */   
/*    */   public Card deal() {
/* 37 */     if (this.deck.isEmpty()) shuffle(); 
/* 38 */     return this.deck.remove(0);
/*    */   }
/*    */   
/* 41 */   public int size() { return this.deck.size(); }
/*    */ }


/* Location:              G:\Users\schul\Documents\NetBeansProjects\WindoomBlackjack\dist\WindoomBlackjack.jar!\com\shaneschulte\plugins\windoomblackjack\Deck.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.2
 */