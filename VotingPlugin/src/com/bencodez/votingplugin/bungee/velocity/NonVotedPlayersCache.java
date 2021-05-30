package com.bencodez.votingplugin.bungee.velocity;

import java.io.File;

import org.spongepowered.configurate.serialize.SerializationException;

import com.velocitypowered.api.proxy.Player;

public class NonVotedPlayersCache extends VelocityYMLFile {

	private VotingPluginVelocity plugin;

	public NonVotedPlayersCache(File file, VotingPluginVelocity plugin) {
		super(file);
		this.plugin = plugin;
	}

	public void addPlayer(Player player) {
		if (!plugin.getMysql().containsKeyQuery(player.getUniqueId().toString())) {
			addPlayer(player.getUniqueId().toString(), player.getUsername());
		}
	}

	private void addPlayer(String uuid, String playerName) {
		try {
			getNode("NonVotedPlayers", playerName, "UUID").set(uuid);
			getNode("NonVotedPlayers", playerName, "LastTime").set(System.currentTimeMillis());
		} catch (SerializationException e) {
			e.printStackTrace();
		}
		save();
	}

	public void check() {
		for (String player : getKeys(getNode("NonVotedPlayers"))) {
			long time = getNode("NonVotedPlayers", player, "LastTime").getLong(0);
			if ((System.currentTimeMillis() - time) > 1000 * 60 * 60 * 24 * 5) {
				remove(player);
			} else {
				String uuid = getNode("NonVotedPlayers", player, "UUID").getString("");
				if (!uuid.isEmpty()) {
					if (plugin.getMysql().containsKeyQuery(uuid)) {
						remove(player);
					}
				} else {
					remove(player);
				}
			}
		}
		save();
	}

	public String playerExists(String playerName) {
		if (!getNode("NonVotedPlayers", playerName).empty()) {
			return getNode("NonVotedPlayers", playerName, "UUID").getString("");
		}
		return "";
	}

	private void remove(String player) {
		plugin.debug("Removing nonvotedplayer: " + player);
		try {
			getNode("NonVotedPlayers", player).set(null);
		} catch (SerializationException e) {
			e.printStackTrace();
		}
		save();
	}

}