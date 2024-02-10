package uk.lambocreeper.avd;

import org.bukkit.plugin.java.JavaPlugin;
import uk.lambocreeper.avd.commands.TestCommand;
import uk.lambocreeper.avd.listeners.PlayerMoveListener;
import uk.lambocreeper.avd.repositories.PlayerTrackerRepository;
import uk.lambocreeper.avd.services.BlockRenderingService;
import uk.lambocreeper.avd.services.PlayerTrackerService;

import java.util.Objects;

public class Main extends JavaPlugin {

    @Override
    public void onEnable() {
        PlayerTrackerRepository playerTrackerRepository = new PlayerTrackerRepository();
        PlayerTrackerService playerTrackerService = new PlayerTrackerService(playerTrackerRepository);
        BlockRenderingService blockRenderingService = new BlockRenderingService();

        this.getServer().getPluginManager().registerEvents(new PlayerMoveListener(playerTrackerService, blockRenderingService), this);

        Objects.requireNonNull(this.getServer().getPluginCommand("test")).setExecutor(new TestCommand(playerTrackerService, blockRenderingService));
    }
}
