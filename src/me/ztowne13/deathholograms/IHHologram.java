package me.ztowne13.deathholograms;

import java.util.UUID;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.inventory.ItemStack;

import com.micrlink.holo.HologramManager;

public class IHHologram extends DynamicHologram
{
	UUID uuid;
	double yOffSet = 2;
	
	public IHHologram(DeathHolograms dh)
	{
		super(dh);
	}

	@Override
	public void create(Location l) 
	{
		uuid = UUID.randomUUID();
		l.setY(l.getY() - yOffSet);
		HologramManager.createNewHologram(uuid.toString(), l, "LINE 1 - .");
	}

	@Override
	public void addLine(String line) 
	{
		if(HologramManager.getHologram(uuid.toString()).getLine(0).equalsIgnoreCase("LINE 1 - ."))
		{
			HologramManager.getHologram(uuid.toString()).setLine(0, line);
			return;
		}
		
		HologramManager.addLine(uuid.toString(), line);
	}

	@Override
	public void delete() 
	{
		HologramManager.removeHologram(uuid.toString());
	}

	@Override
	public void teleport(Location l) 
	{
		l.setY(l.getY() - yOffSet);
		HologramManager.moveHologram(uuid.toString(), l);
	}

	@Override
	public void addItemLine(ItemStack stack) 
	{
		addLine(ChatColor.RED + "IndividualHolograms doesn't support Items");
	}
	

}
