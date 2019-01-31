package me.ztowne13.deathholograms;

import me.ztowne13.deathholograms.Utils;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class DeathHologramCommand implements CommandExecutor
{
	DeathHolograms dh;
	
	public DeathHologramCommand(DeathHolograms dh)
	{
		this.dh = dh;
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String cmdLabel, String[] args) 
	{
		if(!(sender instanceof Player))
		{
			return false;
		}
		
		Player p = (Player) sender;
		
		if(p.hasPermission("deathholograms.admin"))
		{
			if(args.length >= 1)
			{
				if(args[0].equalsIgnoreCase("reload"))
				{
					dh.setup();
					Utils.msg(p, "&2&lSUCCESS! &aReloaded the config.yml.");
					return true;
				}
				
				Utils.msg(p, "&bUsage: &7/DeathHolograms");
				Utils.msg(p, "  &7- Reload&6: Reload all data from the config.yml.");
				return true;
			}
		}

		if(args.length == 0)
		{
			Utils.msg(p, "&7&l>> &3&m--------------------");
			Utils.msg(p, "&c" + dh.getDescription().getName() + " &fV" + dh.getDescription().getVersion());
			Utils.msg(p, "&6By &e" + dh.getDescription().getAuthors().get(0));
			Utils.msg(p, "&7&l>> &3&m--------------------");
			return true;
		}
		
		Utils.msg(p, "&4&lERROR! &cYou need permission: deathholograms.admin for this!");
		return false;
	}

}
