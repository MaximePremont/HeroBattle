package net.lnfinity.HeroBattle.tools.displayers;

import net.lnfinity.HeroBattle.HeroBattle;
import net.lnfinity.HeroBattle.game.HeroBattlePlayer;
import net.lnfinity.HeroBattle.tools.PlayerTool;
import net.lnfinity.HeroBattle.utils.ItemCooldown;
import net.lnfinity.HeroBattle.utils.ToolsUtils;
import net.lnfinity.HeroBattle.utils.Utils;
import org.bukkit.ChatColor;
import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.List;

/*
 * This file is part of HeroBattle.
 *
 * HeroBattle is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * HeroBattle is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with HeroBattle.  If not, see <http://www.gnu.org/licenses/>.
 */
public class FireTool extends PlayerTool
{

	private final int COOLDOWN;
	private final int DURATION;
	private int taskId;

	public FireTool(HeroBattle plugin, int cooldown, int duration)
	{
		super(plugin);

		COOLDOWN = cooldown;
		DURATION = duration;
	}

	@Override
	public String getToolID()
	{
		return "tool.fire";
	}

	@Override
	public String getName()
	{
		return ChatColor.DARK_RED + "" + ChatColor.BOLD + "Feu follet";
	}

	@Override
	public List<String> getDescription()
	{
		return Utils.getToolDescription(ChatColor.GRAY + "Vous protège d'une couche de magma qui absorbe une bonne partie des dégâts et double votre puissance d'attaque pendant " + ChatColor.GOLD + DURATION + " " + ChatColor.GRAY + "secondes. Ne peut être utilisé que toutes les " + ChatColor.GOLD + COOLDOWN + " " + ChatColor.GRAY + "secondes.");
	}

	@Override
	public ItemStack getItem()
	{
		ItemStack item = new ItemStack(Material.FLINT_AND_STEEL);
		ToolsUtils.resetTool(item);
		return item;
	}

	@Override
	public void onRightClick(final Player player, ItemStack tool, PlayerInteractEvent event)
	{
		if (ToolsUtils.isToolAvailable(tool))
		{
			new ItemCooldown(p, player, this, COOLDOWN);
			player.playSound(player.getLocation(), Sound.BLAZE_BREATH, 1.5F, 0.75F);
			final Location location = player.getLocation();
			taskId = p.getServer().getScheduler().runTaskTimer(p, new Runnable()
			{
				private double i = 0;

				@Override
				public void run()
				{
					player.getWorld().playEffect(new Location(location.getWorld(), location.getX() + Math.sin(i * Math.PI), location.getY() + i, location.getZ() + Math.cos(i * Math.PI)), Effect.LAVADRIP, 0);
					player.getWorld().playEffect(new Location(location.getWorld(), location.getX() + Math.sin(i * Math.PI + Math.PI), location.getY() + i, location.getZ() + Math.cos(i * Math.PI + Math.PI)), Effect.LAVADRIP, 0);
					i += 0.05;
					if (i >= 2.5)
					{
						p.getServer().getScheduler().cancelTask(taskId);
					}
				}
			}, 0L, 1L).getTaskId();

			player.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, DURATION * 20, 0));
			final HeroBattlePlayer heroBattlePlayer = p.getGamePlayer(player);
			heroBattlePlayer.addRemainingDoubleDamages(DURATION);
			heroBattlePlayer.addRemainingReducedIncomingDamages(DURATION - (DURATION / 4));
		}
		else
		{
			player.sendMessage(ChatColor.RED + "Vous êtes trop fatigué pour réutiliser ça maintenant");
		}
	}

	@Override
	public void onLeftClick(Player player, ItemStack tool, PlayerInteractEvent event)
	{
		onRightClick(player, tool, event);
	}

}
