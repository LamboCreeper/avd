package uk.lambocreeper.avd.repositories;

import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.UUID;

public class PlayerTrackerRepository {
    private final ArrayList<UUID> players = new ArrayList<UUID>();

    public void addPlayer(Player player) {
        this.players.add(player.getUniqueId());
    }

    public void removePlayer(Player player) {
        this.players.remove(player.getUniqueId());
    }

    public boolean isPlayerBeingTracked(Player player) {
        return this.players.contains(player.getUniqueId());
    }
}
