package me.ztowne13.deathholograms;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

public class PlayerListener implements Listener
{
	private DeathHolograms dh;
	
	public PlayerListener(DeathHolograms dh)
	{
		this.dh = dh;
	}
	
	@EventHandler
	public void onPlayerDeath(PlayerDeathEvent e)
	{
		if(e.getEntity().getKiller() instanceof Player)
		{
			dh.manageDeath(e.getEntity(), e.getEntity().getKiller());
		}
	}
}
