package me.ztowne13.deathholograms;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.plugin.java.JavaPlugin;

public class DeathHolograms extends JavaPlugin
{
	private HashMap<String,List<String>> deathMSGS = new HashMap<String,List<String>>();
	private int waitSeconds;
	
	private ArrayList<DynamicHologram> holograms = new ArrayList<DynamicHologram>();
	
	public void onEnable()
	{
		saveDefaultConfig();
		setup();
		
		getCommand("DeathHolograms").setExecutor(new DeathHologramCommand(this));
		
		Bukkit.getPluginManager().registerEvents(new PlayerListener(this), this);
	}
	
	public void onDisable()
	{
		
	}
	
	@SuppressWarnings("unchecked")
	public void setup()
	{
		deathMSGS.clear();
		for(String s: getConfig().getConfigurationSection("Holograms").getValues(false).keySet())
		{
			List<String> values = (List<String>) getConfig().getList("Holograms." + s);
			deathMSGS.put(s, values);
		}
		
		waitSeconds = getConfig().getInt("hologram-length");
	}
	
	public DynamicHologram getLoadedInstance()
	{
		if(Bukkit.getPluginManager().isPluginEnabled("HolographicDisplays"))
		{
			return new HDHologram(this);
		}
		else if(Bukkit.getPluginManager().isPluginEnabled("IndividualHolograms"))
		{
			return new IHHologram(this);
		}
		return null;
	}
	
	public void deleteAll()
	{
		for(DynamicHologram h: holograms)
		{
			h.delete();
		}
	}
	
	public String getHologramToUse(Player p)
	{
		for(String s: deathMSGS.keySet())
		{
			if(p.hasPermission("deathholograms." + s))
			{
				return s;
			}
		}
		return "ERROR_NO_HOLOGRAM";
	}
	
	public String applyVars(String s, Player p, Player k)
	{
		for(Vars vars: Vars.values())
		{
			s = vars.updateForString(s, p, k, k.getItemInHand());
		}
		
		return s;
	}
	
	public void manageDeath(Player p, Player killer)
	{
		String holoToUse = getHologramToUse(p);
		if(!holoToUse.equalsIgnoreCase("ERROR_NO_HOLOGRAM"))
		{
			DynamicHologram h = getLoadedInstance();
			h.create(p.getEyeLocation());
			h.teleport(new Location(p.getWorld(), p.getEyeLocation().getX(), p.getEyeLocation().getY() + 1, p.getEyeLocation().getZ()));
			for(String s: deathMSGS.get(holoToUse))
			{
				ItemStack stack = new ItemStack(Material.SKULL_ITEM, 1, (byte)3);
				SkullMeta sm = (SkullMeta) stack.getItemMeta();
				if(s.equalsIgnoreCase("{playerHead}"))
				{
					sm.setOwner(p.getName());
					stack.setItemMeta(sm);
					h.addItemLine(stack);
				}
				else if(s.equalsIgnoreCase("{killerHead}"))
				{
					sm.setOwner(killer.getName());
					stack.setItemMeta(sm);
					h.addItemLine(stack);
				}
				else if(s.equalsIgnoreCase("{killedItemWith}"))
				{
					if(killer.getItemInHand() == null || killer.getItemInHand().getType().equals(Material.AIR))
					{
						h.addLine("Fist");
					}
					else
					{
						stack.setType(killer.getItemInHand().getType());
						h.addItemLine(stack);
					}
				}
				else
				{
					h.addLine(ChatColor.translateAlternateColorCodes('&', applyVars(s, p, killer)));
				}
			}
			
			holograms.add(h);
			removeTimer(h);
		}
	}
	
	public void removeTimer(final DynamicHologram h)
	{
		Bukkit.getScheduler().scheduleSyncDelayedTask(this, new Runnable()
		{

			@Override
			public void run()
			{
				h.delete();
				holograms.remove(h);
			}
			
		}, waitSeconds*20);
	}
}
