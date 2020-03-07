package ethos.model.players.packets.commands.all;

import java.util.ArrayList;
import java.util.Optional;

import ethos.Server;
import ethos.clip.doors.Location;
import ethos.model.players.Player;
import ethos.model.players.PlayerHandler;
import ethos.model.players.packets.commands.Command;

public class Raids extends Command {

	@Override
	public void execute(Player c, String input) {
		if (Server.getMultiplayerSessionListener().inAnySession(c)) {
			return;
		}
		if (c.inClanWars() || c.inClanWarsSafe()) {
			c.sendMessage("@cr10@You can not teleport from here, speak to the doomsayer to leave.");
			return;
		}
		if (c.inWild()) {
			c.sendMessage("You can't use this command in the wilderness.");
			return;
		}
		for (String username : c.clan.activeMembers) {
			Player p = PlayerHandler.getPlayer(username);
			if (c.clan.isFounder(c.playerName)) {
				c.getRaids().killAllSpawns(c);
			}
		c.getPA().spellTeleport(1233, 3568, 0, false);
        c.getRaids().roomNames = new ArrayList<String>();
        c.getRaids().roomPaths= new ArrayList<Location>();
        c.getRaids().currentRoom = 0;
        c.getRaids().mobAmount = 0;
        c.getRaids().reachedRoom = 0;
        c.getRaids().raidLeader=null;
        c.getRaids().lizards = false;
        c.getRaids().vasa = false;
        c.getRaids().vanguard = false;
        c.getRaids().ice = false;
        c.getRaids().chest = false;
        c.getRaids().mystic = false;
        c.getRaids().tekton = false;
        c.getRaids().mutta = false;
        c.getRaids().archers = false;
        c.getRaids().olm = false;
        c.getRaids().olmDead = false;
        c.getRaids().rightHand = false;
        c.getRaids().leftHand = false;
		}
	}

	@Override
	public Optional<String> getDescription() {
		return Optional.of("Teleports you to raids.");
	}

}
