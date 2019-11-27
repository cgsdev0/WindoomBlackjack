/*     */ package com.shaneschulte.plugins.windoomblackjack.managers;
/*     */ 
/*     */ import com.shaneschulte.plugins.windoomblackjack.BlackjackPlayer;
/*     */ import com.shaneschulte.plugins.windoomblackjack.StandardLibrary;
/*     */ import com.shaneschulte.plugins.windoomblackjack.Table;
/*     */ import com.shaneschulte.plugins.windoomblackjack.interfaces.TableAddInterface;
/*     */ import java.io.File;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.io.InputStreamReader;
/*     */ import java.io.Reader;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import java.util.Set;
/*     */ import org.bukkit.Location;
/*     */ import org.bukkit.configuration.Configuration;
/*     */ import org.bukkit.configuration.file.FileConfiguration;
/*     */ import org.bukkit.configuration.file.YamlConfiguration;
/*     */ import org.bukkit.entity.Player;
/*     */ import org.bukkit.plugin.java.JavaPlugin;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public final class TableManager
/*     */ {
/*     */   private static List<Table> tables;
/*     */   private static List<TableAddInterface> tableAddInterfaces;
/*     */   private static File tableConfigFile;
/*     */   private static FileConfiguration tableConfig;
/*     */   private static boolean hasTables;
/*     */   
/*     */   public static void enable(JavaPlugin plugin) {
/*  37 */     tables = new ArrayList<>();
/*  38 */     hasTables = true;
/*  39 */     tableAddInterfaces = new ArrayList<>();
/*  40 */     tableConfigFile = new File(plugin.getDataFolder(), "tables.yml");
/*  41 */     tableConfig = (FileConfiguration)YamlConfiguration.loadConfiguration(tableConfigFile);
/*     */     
/*  43 */     InputStream defConfigStream = plugin.getResource("tables.yml");
/*  44 */     if (defConfigStream != null) {
/*  45 */       Reader targetReader = new InputStreamReader(defConfigStream);
/*  46 */       YamlConfiguration defConfig = YamlConfiguration.loadConfiguration(targetReader);
/*  47 */       tableConfig.setDefaults((Configuration)defConfig);
/*     */     } 
/*  49 */     loadConfig();
/*     */   }
/*     */ 
/*     */   
/*  53 */   public static void removeTables() { tables.clear(); }
/*     */ 
/*     */   
/*     */   public static boolean addInterface(Player p) {
/*  57 */     if (findInterface(p) == null) { tableAddInterfaces.add(new TableAddInterface(p)); }
/*  58 */     else { return false; }
/*  59 */      return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public static boolean removeInterface(Player p) {
/*  64 */     TableAddInterface t = findInterface(p);
/*  65 */     if (t == null) return false; 
/*  66 */     tableAddInterfaces.remove(t);
/*  67 */     return true;
/*     */   }
/*     */   
/*     */   public static boolean finishInterface(Player p) {
/*  71 */     TableAddInterface t = findInterface(p);
/*  72 */     if (t == null) return false; 
/*  73 */     if (!t.isComplete()) return false; 
/*  74 */     tables.add(t.finish());
/*  75 */     tableAddInterfaces.remove(t);
/*  76 */     return true;
/*     */   }
/*     */   public static boolean sendRightClick(Player p, Location l) {
/*  79 */     TableAddInterface t = findInterface(p);
/*  80 */     if (t == null) return true; 
/*  81 */     t.sendRightClick(l);
/*  82 */     return false;
/*     */   }
/*     */   public static void checkSeat(Player p, Location l) {
/*  85 */     if (tables.isEmpty())
/*  86 */       return;  for (Table t : tables) {
/*  87 */       if (t.isSeat(l)) {
/*  88 */         PlayerManager.addPlayer(p, l);
/*     */         break;
/*     */       } 
/*     */     } 
/*     */   }
/*     */   private static TableAddInterface findInterface(Player p) {
/*  94 */     for (TableAddInterface t : tableAddInterfaces) {
/*  95 */       if (t.getPlayer().equals(p)) return t; 
/*     */     } 
/*  97 */     return null;
/*     */   }
/*     */   public static void updateConfig() throws IOException {
/* 100 */     int i = 0;
/* 101 */     for (Table t : tables) {
/* 102 */       i++;
/* 103 */       tableConfig.getConfigurationSection("tables").set("t" + i + ".hit", StandardLibrary.serializeLoc(t.getHit()));
/* 104 */       tableConfig.getConfigurationSection("tables").set("t" + i + ".stay", StandardLibrary.serializeLoc(t.getStay()));
/* 105 */       tableConfig.getConfigurationSection("tables").set("t" + i + ".seats", t.getSeats());
/*     */     } 
/* 107 */     tableConfig.save(tableConfigFile);
/*     */   }
/*     */   
/*     */   public static void loadConfig() {
/* 111 */     Set<String> tablestemp = null;
/*     */     try {
/* 113 */       tablestemp = tableConfig.getConfigurationSection("tables").getKeys(false);
/* 114 */     } catch (NullPointerException e) {
/* 115 */       hasTables = false;
/*     */     } 
/* 117 */     if (hasTables) for (String name : tablestemp) {
/* 118 */         Location hit = StandardLibrary.deserializeLoc(tableConfig.getString("tables." + name + ".hit"));
/* 119 */         Location stay = StandardLibrary.deserializeLoc(tableConfig.getString("tables." + name + ".stay"));
/* 120 */         Table newTable = new Table(hit, stay);
/* 121 */         tables.add(newTable);
/* 122 */         List<String> contents = tableConfig.getList("tables." + name + ".seats");
/* 123 */         for (String s : contents) {
/* 124 */           newTable.addSeat(StandardLibrary.deserializeLoc(s));
/*     */         }
/*     */       }  
/*     */   }
/*     */   
/*     */   public static void passClick(BlackjackPlayer bp, Location location) {
/* 130 */     for (Table t : tables) {
/* 131 */       if (t.getStay().equals(location)) { bp.gameStay(); return; }
/* 132 */        if (t.getHit().equals(location)) { bp.gameHit();
/*     */         return; }
/*     */     
/*     */     } 
/*     */   }
/*     */ }


/* Location:              G:\Users\schul\Documents\NetBeansProjects\WindoomBlackjack\dist\WindoomBlackjack.jar!\com\shaneschulte\plugins\windoomblackjack\managers\TableManager.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.2
 */