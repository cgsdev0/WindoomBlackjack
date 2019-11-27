/*     */ package com.shaneschulte.plugins.windoomblackjack;
/*     */ 
/*     */ import com.shaneschulte.plugins.windoomblackjack.managers.MoneyManager;
/*     */ import org.bukkit.ChatColor;
/*     */ import org.bukkit.entity.Player;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class Session
/*     */ {
/*     */   public Deck deck;
/*     */   private Hand playerHand;
/*     */   private Hand dealerHand;
/*     */   private int wager;
/*     */   private boolean isPlaying;
/*     */   private Player player;
/*     */   private int total;
/*     */   private boolean highRoller;
/*     */   
/*     */   public Session(Player p) {
/*  26 */     this.deck = new Deck();
/*  27 */     this.isPlaying = false;
/*  28 */     this.player = p;
/*  29 */     this.total = 0;
/*     */   }
/*     */   public void gameStart(int w, boolean high) {
/*  32 */     if (this.isPlaying)
/*  33 */       return;  this.highRoller = high;
/*  34 */     if (this.highRoller) MoneyManager.announce(this.player, w); 
/*  35 */     if (this.deck.size() < 20) {
/*  36 */       this.deck.shuffle();
/*  37 */       this.player.sendMessage(ChatColor.GRAY + "[Blackjack] Shuffling the deck...");
/*     */     } 
/*  39 */     this.wager = w;
/*  40 */     MoneyManager.charge(this.player, this.wager);
/*  41 */     this.total -= this.wager;
/*  42 */     this.isPlaying = true;
/*  43 */     this.playerHand = new Hand();
/*  44 */     this.dealerHand = new Hand();
/*  45 */     dealPlayerCard();
/*  46 */     dealPlayerCard();
/*  47 */     if (this.playerHand.getValue() == 21) {
/*     */       
/*  49 */       payout(1.5D);
/*  50 */       if (this.highRoller) { MoneyManager.announceBlackjack(this.player, this.wager); }
/*  51 */       else { this.player.sendMessage(ChatColor.GREEN + "[Blackjack] Blackjack!"); }
/*     */        return;
/*     */     } 
/*  54 */     this.player.sendMessage(ChatColor.GRAY + "[Blackjack] Dealer dealt: " + givePlayerCard(this.dealerHand, this.deck.deal()).toString() + this.dealerHand.toString());
/*     */   }
/*     */   private Card givePlayerCard(Hand hand, Card c) {
/*  57 */     hand.addCard(c);
/*  58 */     return c;
/*     */   }
/*     */   
/*  61 */   private void dealPlayerCard() { this.player.sendMessage(ChatColor.AQUA + "[Blackjack] You were dealt: " + givePlayerCard(this.playerHand, this.deck.deal()).toString() + this.playerHand.toString()); }
/*     */   
/*     */   public void gameQuit() {
/*  64 */     if (this.isPlaying) {
/*  65 */       this.player.sendMessage(ChatColor.RED + "[Blackjack] You quit! Your wager was forfeited.");
/*     */     }
/*  67 */     if (this.total > 0) {
/*  68 */       this.player.sendMessage(ChatColor.GREEN + "[Blackjack] Net profit: $" + this.total);
/*     */     }
/*  70 */     else if (this.total < 0) {
/*  71 */       this.player.sendMessage(ChatColor.RED + "[Blackjack] Net losses: $" + Math.abs(this.total));
/*     */     } 
/*     */   }
/*     */   public void gameHit() {
/*  75 */     if (!this.isPlaying)
/*  76 */       return;  dealPlayerCard();
/*  77 */     if (checkBust(this.playerHand)) {
/*  78 */       this.player.sendMessage(ChatColor.RED + "[Blackjack] You busted! Hand total: " + this.playerHand.getValue());
/*  79 */       if (this.highRoller) MoneyManager.announceLoss(this.player, this.wager); 
/*  80 */       keepPlaying();
/*     */     } 
/*     */   }
/*     */   public void gameStay() {
/*  84 */     if (!this.isPlaying)
/*  85 */       return;  this.player.sendMessage(ChatColor.GRAY + "[Blackjack] Dealer's Hole: " + givePlayerCard(this.dealerHand, this.deck.deal()).toString() + this.dealerHand.toString());
/*  86 */     while (!checkBust(this.dealerHand) && this.dealerHand.getValue() < 17) {
/*  87 */       this.player.sendMessage(ChatColor.GRAY + "[Blackjack] Dealer dealt: " + givePlayerCard(this.dealerHand, this.deck.deal()).toString() + this.dealerHand.toString());
/*     */     }
/*  89 */     if (checkBust(this.dealerHand)) {
/*  90 */       this.player.sendMessage(ChatColor.GREEN + "[Blackjack] Dealer busted! Hand total: " + this.dealerHand.getValue());
/*  91 */       if (this.highRoller) MoneyManager.announceWin(this.player, this.wager); 
/*  92 */       payout(1.0D);
/*     */       return;
/*     */     } 
/*  95 */     if (this.playerHand.getValue() > this.dealerHand.getValue()) {
/*  96 */       this.player.sendMessage(ChatColor.GREEN + "[Blackjack] You won! (" + this.dealerHand.getValue() + " to " + this.playerHand.getValue() + ")");
/*  97 */       if (this.highRoller) MoneyManager.announceWin(this.player, this.wager); 
/*  98 */       payout(1.0D);
/*     */     }
/* 100 */     else if (this.dealerHand.getValue() > this.playerHand.getValue()) {
/* 101 */       this.player.sendMessage(ChatColor.RED + "[Blackjack] You lost! (" + this.dealerHand.getValue() + " to " + this.playerHand.getValue() + ")");
/* 102 */       if (this.highRoller) MoneyManager.announceLoss(this.player, this.wager); 
/* 103 */       keepPlaying();
/*     */     } else {
/*     */       
/* 106 */       int val = this.playerHand.getValue();
/* 107 */       if (this.highRoller) MoneyManager.announcePush(this.player, this.wager); 
/* 108 */       this.player.sendMessage(ChatColor.AQUA + "[Blackjack] Push. (" + val + " to " + val + ")");
/* 109 */       payout(0.0D);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void payout(double ratio) {
/* 116 */     if (!this.isPlaying)
/* 117 */       return;  int addTo = this.wager + (int)(this.wager * ratio);
/* 118 */     this.total += addTo;
/* 119 */     MoneyManager.pay(this.player, addTo);
/* 120 */     if (ratio > 0.0D) {
/* 121 */       this.player.sendMessage(ChatColor.GREEN + "[Blackjack] Paying you " + (int)(this.wager * ratio));
/*     */     }
/* 123 */     keepPlaying();
/*     */   }
/*     */   public void keepPlaying() {
/* 126 */     if (this.total > 0) {
/* 127 */       this.player.sendMessage(ChatColor.GREEN + "[Blackjack] You are up $" + this.total);
/*     */     }
/* 129 */     else if (this.total < 0) {
/* 130 */       this.player.sendMessage(ChatColor.RED + "[Blackjack] You are down $" + Math.abs(this.total));
/*     */     } 
/* 132 */     this.isPlaying = false;
/* 133 */     this.player.sendMessage(ChatColor.AQUA + "[Blackjack] Enter a wager to keep playing, or press shift.");
/*     */   }
/*     */   
/* 136 */   public boolean checkBust(Hand hand) { return (hand.getValue() > 21); }
/*     */ 
/*     */   
/* 139 */   public boolean isPlaying() { return this.isPlaying; }
/*     */ }


/* Location:              G:\Users\schul\Documents\NetBeansProjects\WindoomBlackjack\dist\WindoomBlackjack.jar!\com\shaneschulte\plugins\windoomblackjack\Session.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.2
 */