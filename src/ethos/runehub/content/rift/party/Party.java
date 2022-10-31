package ethos.runehub.content.rift.party;

import ethos.model.players.Player;

import java.util.Arrays;
import java.util.Objects;

public class Party {

    public boolean addMember(Player player) {
        if (player.getAttributes().getParty() == null) {
            for (int i = 0; i < members.length; i++) {
                if (members[i] == null) {
                    player.getAttributes().setParty(this);
                    members[i] = player;
                    return true;
                }
            }
        }
        return false;
    }

    public boolean removeMember(Player player) {
        if (player.getAttributes().getParty() == null) {
            for (int i = 0; i < members.length; i++) {
                if (members[i] == player) {
                    player.getAttributes().setParty(null);
                    members[i] = null;
                    this.shiftMembers();
                    return true;
                }
            }
        }
        return false;
    }

    public void sendMessage(String message) {
        Arrays.stream(members).filter(Objects::nonNull).forEach(player -> player.sendMessage(message));
    }

    private void shiftMembers() {
        for (int i = 0; i < members.length; i++) {
            if (members[i] == null) {
                int slot = this.getFirstNonNullSlotAfterSlot(i);
                if (slot > i) {
                    members[i] = members[slot];
                    members[slot] = null;
                }
            }
        }
    }

    private boolean contains(Player player) {
        for (int i = 0; i < members.length; i++) {
            if (members[i] != null) {
                if (Objects.equals(members[i].getContext().getId(), player.getContext().getId())) {
                    return true;
                }
            }
        }
        return false;
    }

    private int getFirstNonNullSlotAfterSlot(int slot) {
        for (int i = 0; i < members.length; i++) {
            if (members[i] != null && i > slot) {
                return i;
            }
        }
        return -1;
    }

    private Player getMember(int slot) {
        return members[slot];
    }

    private String getMemberName(int slot) {
        return members[slot] == null ? "null" : members[slot].playerName;
    }

    public Player[] getMembers() {
        return members;
    }

    @Override
    public String toString() {
        return "[" + this.getMemberName(0) + ", " + this.getMemberName(1) + ", "+ this.getMemberName(2) +"]";
    }

    public Party(Player player) {
        this.members = new Player[3];
        members[0] = player;
    }

    private final Player[] members;
}
