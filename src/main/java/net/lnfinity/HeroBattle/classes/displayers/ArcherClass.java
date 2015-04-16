package net.lnfinity.HeroBattle.classes.displayers;

import java.util.Arrays;
import java.util.List;

import net.lnfinity.HeroBattle.HeroBattle;
import net.lnfinity.HeroBattle.classes.PlayerClass;
import net.lnfinity.HeroBattle.classes.PlayerClassType;
import net.lnfinity.HeroBattle.tools.displayers.ArrowsTool;
import net.lnfinity.HeroBattle.tools.displayers.SpeedTool;
import net.lnfinity.HeroBattle.tools.displayers.SwordVariant3Tool;
import net.md_5.bungee.api.ChatColor;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public class ArcherClass extends PlayerClass {

	public ArcherClass(HeroBattle plugin) {
		this(plugin, 0, 0, 0);
	}

	public ArcherClass(HeroBattle plugin, int arg1, int arg2, int arg3) {
		super(plugin);

		addTool(new SwordVariant3Tool(p));
		addTool(new ArrowsTool(p, 12 - arg1, 3 + arg2));
		addTool(new SpeedTool(p, 20 - arg1 * 2, 5 + arg2));
	}

	@Override
	public String getName() {
		return "Archer";
	}

	@Override
	public ItemStack getIcon() {
		return new ItemStack(Material.BOW);
	}

	@Override
	public ItemStack getHat() {
		ItemStack item = new ItemStack(Material.STAINED_GLASS);
		item.setDurability((short) 12);
		return item;
	}

	@Override
	public List<String> getDescription() {
		return Arrays.asList("La maitrise d'un arc et de ses flèches", "n'est pas donnée à tout le monde.", "", ChatColor.GRAY + "Classe de type " + ChatColor.GOLD + "Distant", ChatColor.GREEN + "+ " + ChatColor.GRAY + "Dégâts à distance, agilité, précision", ChatColor.RED + "- " + ChatColor.GRAY + "Peu résistant, corps à corps");
	}

	@Override
	public int getMinDamages() {
		return 2;
	}

	@Override
	public int getMaxDamages() {
		return 4;
	}

	@Override
	public int getMaxResistance() {
		return 175;
	}

	@Override
	public int getLives() {
		return 4;
	}

	@Override
	public PlayerClassType getType() {
		return PlayerClassType.ARCHER;
	}

}