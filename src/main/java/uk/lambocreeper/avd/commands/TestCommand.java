package uk.lambocreeper.avd.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import uk.lambocreeper.avd.repositories.PlayerTrackerRepository;
import uk.lambocreeper.avd.services.BlockRenderingService;
import uk.lambocreeper.avd.services.PlayerTrackerService;

public class TestCommand implements CommandExecutor {

    private final PlayerTrackerService playerTrackerService;
    private final BlockRenderingService blockRenderingService;

    public TestCommand(PlayerTrackerService playerTrackerService, BlockRenderingService blockRenderingService) {
        this.playerTrackerService = playerTrackerService;
        this.blockRenderingService = blockRenderingService;
    }

    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, String[] args) {
        if (!(sender instanceof Player player)) return false;

        if (this.playerTrackerService.isPlayerBeingTracked(player)) {
            this.playerTrackerService.removePlayer(player);

            return true;
        }

        this.playerTrackerService.addPlayer(player);
        this.blockRenderingService.hideBlocksAtLocationForPlayer(player.getLocation(), player);

        return true;
    }
}
