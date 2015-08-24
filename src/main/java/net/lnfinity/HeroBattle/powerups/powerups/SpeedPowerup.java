package net.lnfinity.HeroBattle.powerups.powerups;

import net.lnfinity.HeroBattle.powerups.PositivePowerup;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;


public class SpeedPowerup implements PositivePowerup
{

	@Override
	public void onPickup(final Player player, final ItemStack pickupItem)
	{
		player.sendMessage(ChatColor.GREEN + "De la vitesse pour 10 secondes !");
		player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 20 * 10, 1));
	}

	@Override
	public ItemStack getItem()
	{
		return new ItemStack(Material.SUGAR);
	}

	@Override
	public String getName()
	{
		return ChatColor.DARK_PURPLE + "" + ChatColor.BOLD + "PLUS VITE" + ChatColor.LIGHT_PURPLE + " DIX SECONDES";
	}

	@Override
	public double getWeight()
	{
		return 25;
	}
}
