package com.shaneschulte.plugins.windoomblackjack.commands;

import java.util.LinkedList;
import org.bukkit.command.Command;
import org.bukkit.entity.Player;

public interface BaseCommand {
  boolean execute(Player paramPlayer, Command paramCommand, String paramString1, String paramString2, LinkedList<String> paramLinkedList);
}


/* Location:              G:\Users\schul\Documents\NetBeansProjects\WindoomBlackjack\dist\WindoomBlackjack.jar!\com\shaneschulte\plugins\windoomblackjack\commands\BaseCommand.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.2
 */