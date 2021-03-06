package net.lnfinity.HeroBattle.powerups.powerups;

import net.lnfinity.HeroBattle.HeroBattle;
import net.lnfinity.HeroBattle.game.HeroBattlePlayer;
import net.lnfinity.HeroBattle.powerups.PositivePowerup;
import net.lnfinity.HeroBattle.utils.ToolsUtils;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

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
public class ResetToolCooldownPowerup implements PositivePowerup
{

	private final HeroBattle p;

	public ResetToolCooldownPowerup(final HeroBattle plugin)
	{
		p = plugin;
	}

	@Override
	public void onPickup(final Player player, final ItemStack pickupItem)
	{
		final HeroBattlePlayer gPlayer = p.getGamePlayer(player);

		// 1: we try to find a tool with a delay higher than 5 seconds.
		final List<Integer> slotsToTest = new ArrayList<>();
		for (int i = 0; i < gPlayer.getPlayerClass().getTools().size(); i++)
		{
			slotsToTest.add(i);
		}

		ItemStack toolToReset = null;
		final Random random = new Random();

		do
		{
			if (slotsToTest.size() == 0) break;

			final int stackSlot = slotsToTest.get(random.nextInt(slotsToTest.size()));
			final ItemStack stack = player.getInventory().getItem(stackSlot);

			slotsToTest.remove((Integer) stackSlot);

			if (!ToolsUtils.isToolAvailable(stack) && stack.getAmount() >= 6)
			{
				toolToReset = stack;
			}
		} while (toolToReset == null);


		if (toolToReset == null)
		{
			player.sendMessage(ChatColor.RED + "" + ChatColor.ITALIC + "Vous n'avez aucune capacité qui vaille le coup d'être réinitialisé...");
			return;
		}


		ToolsUtils.resetTool(toolToReset);
		player.sendMessage(ChatColor.GREEN + "La capacité " + toolToReset.getItemMeta().getDisplayName() + ChatColor.GREEN + " est à nouveau utilisable ! " + ChatColor.DARK_GREEN + "*pouf*");

		player.updateInventory();
	}

	@Override
	public ItemStack getItem()
	{
		return new ItemStack(Material.WATCH);
	}

	@Override
	public String getName()
	{
		return ChatColor.GOLD + "" + ChatColor.BOLD + "RESTAURATION D'UN OUTIL BLOQUÉ";
	}

	@Override
	public double getWeight()
	{
		return 20;
	}
}
