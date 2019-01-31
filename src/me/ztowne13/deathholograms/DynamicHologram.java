package me.ztowne13.deathholograms;

import org.bukkit.Location;
import org.bukkit.inventory.ItemStack;

public abstract class DynamicHologram
{

	DeathHolograms dh;
	
	public DynamicHologram(DeathHolograms dh)
	{
		this.dh = dh;
	}
	
	public abstract void create(Location l);
	
	public abstract void addLine(String line);
	
	public abstract void addItemLine(ItemStack stack);
	
	public abstract void delete();
	
	public abstract void teleport(Location l);

}
