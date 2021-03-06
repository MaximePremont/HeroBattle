package net.lnfinity.HeroBattle.tools.displayers;

import net.lnfinity.HeroBattle.HeroBattle;
import net.lnfinity.HeroBattle.game.HeroBattlePlayer;
import net.lnfinity.HeroBattle.tools.PlayerTool;
import net.lnfinity.HeroBattle.utils.ItemCooldown;
import net.lnfinity.HeroBattle.utils.ToolsUtils;
import net.lnfinity.HeroBattle.utils.Utils;
import org.bukkit.ChatColor;
import org.bukkit.DyeColor;
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
public class MinerSpecialTool extends PlayerTool
{

	private final int COOLDOWN;
	private final int DURATION;

	public MinerSpecialTool(HeroBattle plugin, int cooldown, int duration)
	{
		super(plugin);

		COOLDOWN = cooldown;
		DURATION = duration;
	}

	@Override
	public String getToolID()
	{
		return "tool.minerspecial";
	}

	@Override
	public String getName()
	{
		return ChatColor.GREEN + "Chant du mineur";
	}

	@Override
	public List<String> getDescription()
	{
		return Utils.getToolDescription(ChatColor.GRAY + "Le mineur chante tel qu'il le fait à la mine, augmentant ainsi son efficacité. Il court plus vite et frappe plus fort pendant " + ChatColor.GOLD + DURATION + " " + ChatColor.GRAY + "secondes. Ne peut être utilisé que toutes les " + ChatColor.GOLD + COOLDOWN + " " + ChatColor.GRAY + "secondes.");
	}

	@Override
	public ItemStack getItem()
	{
		final ItemStack item = new ItemStack(Material.INK_SACK, 1, DyeColor.CYAN.getData());
		ToolsUtils.resetTool(item);

		return item;
	}

	@Override
	public void onRightClick(Player player, ItemStack tool, PlayerInteractEvent event)
	{
		if (ToolsUtils.isToolAvailable(tool))
		{
			new ItemCooldown(p, player, this, COOLDOWN);
			player.playSound(player.getLocation(), Sound.LEVEL_UP, 1F, 1.5F);

			HeroBattlePlayer heroBattlePlayer = p.getGamePlayer(player);
			heroBattlePlayer.addRemainingDoubleDamages(DURATION);
			player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, DURATION * 20, 0));


		}
		else
		{
			player.sendMessage(ChatColor.RED + "Vous êtes trop fatigué pour réutiliser ça maintenant");
		}
	}
}
