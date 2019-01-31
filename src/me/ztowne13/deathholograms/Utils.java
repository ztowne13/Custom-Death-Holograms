package me.ztowne13.deathholograms;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class Utils 
{
	public static String removeColor(String s)
	{
		s = ChatColor.stripColor(s);
		String newString = "";
		for(int i = 0; i < s.length(); i++)
		{
			if(s.substring(i, i + 1).equalsIgnoreCase("&"))
			{
				i++;
			}
			else
			{
				newString = newString + s.substring(i, i + 1);
			}
		}
		return newString;
	}
	
	public static void msg(Player p, String s)
	{
		p.sendMessage(ChatColor.translateAlternateColorCodes('&', s));
	}
}
