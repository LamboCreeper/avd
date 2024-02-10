package uk.lambocreeper.avd.services;

import org.bukkit.entity.Player;
import uk.lambocreeper.avd.repositories.PlayerTrackerRepository;

public class PlayerTrackerService {
    private final PlayerTrackerRepository playerTrackerRepository;

    public PlayerTrackerService(PlayerTrackerRepository playerTrackerRepository) {
        this.playerTrackerRepository = playerTrackerRepository;
    }

    public void addPlayer(Player player) {
        this.playerTrackerRepository.addPlayer(player);
    }

    public void removePlayer(Player player) {
        this.playerTrackerRepository.removePlayer(player);
    }

    public boolean isPlayerBeingTracked(Player player) {
        return this.playerTrackerRepository.isPlayerBeingTracked(player);
    }
}
