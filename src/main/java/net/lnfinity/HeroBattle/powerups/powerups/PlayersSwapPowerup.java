package net.lnfinity.HeroBattle.powerups.powerups;

import net.lnfinity.HeroBattle.HeroBattle;
import net.lnfinity.HeroBattle.game.HeroBattlePlayer;
import net.lnfinity.HeroBattle.powerups.NegativePowerup;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

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
public class PlayersSwapPowerup implements NegativePowerup
{

	private final HeroBattle p;
	private final Random random = new Random();

	public PlayersSwapPowerup(final HeroBattle plugin)
	{
		p = plugin;
	}

	@Override
	public void onPickup(final Player player, final ItemStack pickupItem)
	{
		final List<HeroBattlePlayer> otherPlayers = p.getGame().getInGamePlayers().values().stream()
				.filter(gPlayer -> !gPlayer.getUUID().equals(player.getUniqueId()))
				.collect(Collectors.toList());

		if (otherPlayers.size() == 0) return;

		final Player otherPlayer = otherPlayers.get(random.nextInt(otherPlayers.size())).getPlayerIfOnline();
		final Location otherPlayerLocation = otherPlayer.getLocation();

		otherPlayer.teleport(player);
		player.teleport(otherPlayerLocation);

		otherPlayer.sendMessage(ChatColor.RED + "Woosh ! Vous avez échangé votre place avec " + ChatColor.DARK_RED + player.getName() + ChatColor.RED + " car il a pris un bonus de swap !");
		player.sendMessage(ChatColor.RED + "Woosh ! Vous avez échangé votre place avec " + ChatColor.DARK_RED + otherPlayer.getName() + ChatColor.RED + " !");

		otherPlayer.playSound(otherPlayer.getLocation(), Sound.ENDERMAN_TELEPORT, 1, 1);
		player.playSound(player.getLocation(), Sound.ENDERMAN_TELEPORT, 1, 1);
	}

	@Override
	public ItemStack getItem()
	{
		return new ItemStack(Material.COMMAND);
	}

	@Override
	public String getName()
	{
		return ChatColor.DARK_PURPLE + "" + ChatColor.BOLD + "◄►"
				+ ChatColor.LIGHT_PURPLE + "" + ChatColor.BOLD + " INVERSION";
	}

	@Override
	public double getWeight()
	{
		return 15;
	}
}
