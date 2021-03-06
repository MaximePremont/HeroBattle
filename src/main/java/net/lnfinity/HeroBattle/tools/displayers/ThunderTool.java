package net.lnfinity.HeroBattle.tools.displayers;

import net.lnfinity.HeroBattle.HeroBattle;
import net.lnfinity.HeroBattle.tools.PlayerTool;
import net.lnfinity.HeroBattle.utils.ItemCooldown;
import net.lnfinity.HeroBattle.utils.ToolsUtils;
import net.lnfinity.HeroBattle.utils.TripleParameters;
import net.lnfinity.HeroBattle.utils.Utils;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

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
public class ThunderTool extends PlayerTool
{
	private final int COOLDOWN; // seconds

	private final int DAMAGES_MIN;
	private final int DAMAGES_MAX;

	public ThunderTool(HeroBattle plugin, int cooldown, int min, int max)
	{
		super(plugin);
		COOLDOWN = cooldown;

		DAMAGES_MIN = min;
		DAMAGES_MAX = max;
	}

	@Override
	public String getToolID()
	{
		return "tool.thunder";
	}

	@Override
	public String getName()
	{
		return ChatColor.DARK_PURPLE + "" + ChatColor.BOLD + "Colère de Zeus";
	}

	@Override
	public List<String> getDescription()
	{
		return Utils.getToolDescription(ChatColor.GRAY + "Invoque un éclair dans la direction visée blessant les joueurs touchés par la foudre de " + ChatColor.RED + DAMAGES_MIN + " " + ChatColor.GRAY + "à " + ChatColor.RED + DAMAGES_MAX + " " + ChatColor.GRAY + "pourcents. Ne peut être utilisé que toutes les " + ChatColor.GOLD + COOLDOWN + " " + ChatColor.GRAY + "secondes.");
	}

	@Override
	public ItemStack getItem()
	{
		ItemStack item = new ItemStack(Material.FIREBALL);
		ToolsUtils.resetTool(item);

		return item;
	}

	@Override
	public void onRightClick(final Player player, ItemStack tool, PlayerInteractEvent event)
	{
		if (ToolsUtils.isToolAvailable(tool))
		{
			Block b = p.getGame().getTargetBlock(player, 160);
			if (b != null)
			{
				p.getGame().addEntityParameters(player.getWorld().strikeLightning(b.getLocation()).getUniqueId(), new TripleParameters(DAMAGES_MIN, DAMAGES_MAX));

				p.getGame().getLastLightningBolts().put(player.getUniqueId(), b.getLocation());
				p.getServer().getScheduler().runTaskLaterAsynchronously(p, () -> p.getGame().getLastLightningBolts().remove(player.getUniqueId()), 20 * 4);

				new ItemCooldown(p, player, this, COOLDOWN);
			}
			else
			{
				player.sendMessage(ChatColor.RED + "Vous ne visez aucun bloc, vous échouez !");
			}
		}
		else
		{
			player.sendMessage(ChatColor.RED + "Vous êtes trop fatigué pour réutiliser ça maintenant");
		}
	}
}
