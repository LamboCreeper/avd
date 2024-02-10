package uk.lambocreeper.avd.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import uk.lambocreeper.avd.services.BlockRenderingService;
import uk.lambocreeper.avd.services.PlayerTrackerService;

public class PlayerMoveListener implements Listener {
    private final PlayerTrackerService playerTrackerService;
    private final BlockRenderingService blockRenderingService;

    public PlayerMoveListener(PlayerTrackerService playerTrackerService, BlockRenderingService blockRenderingService) {
        this.playerTrackerService = playerTrackerService;
        this.blockRenderingService = blockRenderingService;
    }

    @EventHandler
    public void onPlayerMove(PlayerMoveEvent event) {
        Player player = event.getPlayer();

        if (this.playerTrackerService.isPlayerBeingTracked(player)) {
            this.blockRenderingService.showBlocksAtLocationForPlayer(player.getLocation(), player);
        }
    }
}
