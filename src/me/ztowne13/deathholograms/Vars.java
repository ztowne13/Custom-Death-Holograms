package me.ztowne13.deathholograms;

import me.ztowne13.itemkills.ItemKills;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public enum Vars 
{
	KILLER("killer"),
	
	PLAYER("player"),
	
	REMAININGHEALTH("remainingHealth"),
	
	ITEMKILLS("killsOnItem");
	
	String variable;
	
	Vars(String variable)
	{
		this.variable = "{" + variable + "}";
	}
	
	public String updateForString(String s, Player p, Player k, ItemStack stack)
	{
		String toUpdate = null;
		if(equals(KILLER))
		{
			toUpdate = k.getName();
		}
		else if(equals(PLAYER))
		{
			toUpdate = p.getName();
		}
		else if(equals(REMAININGHEALTH))
		{
			toUpdate = ((int) (k.getHealth()/2)) + "";
		}
		else if(equals(ITEMKILLS))
		{
			if(!(Bukkit.getPluginManager().getPlugin("ItemKills") == null))
			{
				ItemKills ik = (ItemKills) Bukkit.getPluginManager().getPlugin("ItemKills");
				toUpdate = ik.getCurrentKills(stack) + "";
			}
			else
			{
				toUpdate = ChatColor.DARK_RED + "{ItemKills not installed}";
			}
		}
		
		return s.replace(variable, toUpdate);
	}
}
