/*    */ package com.shaneschulte.plugins.windoomblackjack;
/*    */ 
/*    */ import org.bukkit.Bukkit;
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
/*    */ public final class StandardLibrary
/*    */ {
/* 17 */   static Player cgs = null;
/*    */   
/*    */   public static void enable(JavaPlugin pl, Player cgs_in) {
/* 20 */     cgs = cgs_in;
/* 21 */     plugin = pl;
/*    */   }
/*    */   static JavaPlugin plugin;
/* 24 */   public static String serializeLoc(Location loc) { return loc.getWorld().getName() + "," + loc.getX() + "," + loc.getY() + "," + loc.getZ(); }
/*    */ 
/*    */   
/*    */   public static Location deserializeLoc(String str) {
/* 28 */     String[] loc = str.split(",");
/* 29 */     return new Location(Bukkit.getWorld(loc[0]), Double.parseDouble(loc[1]), Double.parseDouble(loc[2]), Double.parseDouble(loc[3]));
/*    */   }
/*    */   public static void debug(String message) {
/* 32 */     if (cgs != null) cgs.sendMessage("[21Debug] " + message); 
/* 33 */     plugin.getLogger().info(message);
/*    */   }
/*    */ }


/* Location:              G:\Users\schul\Documents\NetBeansProjects\WindoomBlackjack\dist\WindoomBlackjack.jar!\com\shaneschulte\plugins\windoomblackjack\StandardLibrary.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.2
 */