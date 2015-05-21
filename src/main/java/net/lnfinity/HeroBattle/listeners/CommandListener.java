package net.lnfinity.HeroBattle.listeners;

import net.lnfinity.HeroBattle.HeroBattle;
import net.md_5.bungee.api.ChatColor;
import net.samagames.gameapi.json.Status;
import net.zyuiop.MasterBundle.MasterBundle;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

import java.util.UUID;

public class CommandListener implements CommandExecutor {

	private HeroBattle p;

	public CommandListener(HeroBattle plugin) {
		p = plugin;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String alias, String[] args) {

		if(sender instanceof Player) {
			UUID id = ((Player) sender).getUniqueId();

			if (!id.equals(UUID.fromString("da04cd54-c6c7-4672-97c5-85663f5bccf6"))
					&& !id.equals(UUID.fromString("9cc7b403-3ce8-47d7-9d95-eb2a03dd78b4"))
					&& !sender.isOp()) {
				return false;
			}
		}


		if (cmd.getName().equalsIgnoreCase("start")) {

			if (p.getPlayerCount() >= 2 || !MasterBundle.isDbEnabled) {

				p.getServer().broadcastMessage(HeroBattle.GAME_TAG + ChatColor.GREEN + "Le jeu a été démarré manuellement.");
				p.getTimer().cancelTimer();
				p.getGame().start();

			} else {

				sender.sendMessage(ChatColor.RED + "Vous devez être au moins deux joueurs !");
			}

		} else if (cmd.getName().equalsIgnoreCase("forcestop")) {

			p.getServer().broadcastMessage(HeroBattle.GAME_TAG + ChatColor.RED + "Le jeu a été interrompu de force.");

			p.getGame().onPlayerWin(null);

		} else if (cmd.getName().equalsIgnoreCase("powerup")) {

			if(p.getGame().getStatus() == Status.InGame) {
				p.getPowerupManager().spawnRandomPowerup();
			}
			else {
				sender.sendMessage(ChatColor.RED + "Impossible de faire apparaître un powerup si le jeu n'est pas démarré.");
			}

		} else return false;



		return true;
	}
}
