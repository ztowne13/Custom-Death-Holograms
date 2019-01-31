package me.ztowne13.deathholograms;

import org.bukkit.Location;
import org.bukkit.inventory.ItemStack;

import com.gmail.filoghost.holographicdisplays.api.Hologram;
import com.gmail.filoghost.holographicdisplays.api.HologramsAPI;

public class HDHologram extends DynamicHologram
{
	Hologram h;
	
	public HDHologram(DeathHolograms dh)
	{
		super(dh);
	}

	@Override
	public void create(Location l) 
	{
		this.h = HologramsAPI.createHologram(dh, l);
	}

	@Override
	public void addLine(String line) {
		h.appendTextLine(line);
		
	}

	@Override
	public void delete() 
	{
		h.delete();	
	}

	@Override
	public void teleport(Location l) 
	{
		h.teleport(l);
	}

	@Override
	public void addItemLine(ItemStack stack) 
	{
		h.appendItemLine(stack);
	}


}
