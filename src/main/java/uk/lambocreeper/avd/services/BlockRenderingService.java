package uk.lambocreeper.avd.services;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.block.data.BlockData;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;
import java.util.UUID;

public class BlockRenderingService {
    private static final int HIDE_RADIUS = 25;
    private static final int SHOW_RADIUS = 5;

    private final HashMap<UUID, ArrayList<Location>> locationsSeenByPlayer = new HashMap<>();

    public void hideBlocksAtLocationForPlayer(Location location, Player player) {
        World world = location.getWorld();
        BlockData barrier = Material.BARRIER.createBlockData();

        for (int x = -BlockRenderingService.HIDE_RADIUS; x < BlockRenderingService.HIDE_RADIUS; x++) {
            for (int y = -BlockRenderingService.HIDE_RADIUS; y < BlockRenderingService.HIDE_RADIUS; y++) {
                for (int z = -BlockRenderingService.HIDE_RADIUS; z < BlockRenderingService.HIDE_RADIUS; z++) {
                    Block block = Objects.requireNonNull(world).getBlockAt(
                        location.getBlockX() + x,
                        location.getBlockY() + y,
                        location.getBlockZ() + z
                    );

                    if (block.isEmpty() || block.getBlockData().getMaterial() == Material.BARRIER) continue;

                    player.sendBlockChange(block.getLocation(), barrier);
                }
            }
        }
    }

    private void renderBlockAtLocationForPlayer(Block block, Player player) {
        ArrayList<Location> seenByPlayer = locationsSeenByPlayer.get(player.getUniqueId());

        if (block.isEmpty() || seenByPlayer.contains(block.getLocation())) {
            return;
        }

        player.sendBlockChange(block.getLocation(), block.getBlockData());
        seenByPlayer.add(block.getLocation());

        player.getServer().getConsoleSender().sendMessage("Showing block " + block.getLocation());
    }

    public void showBlocksAtLocationForPlayer(Location location, Player player) {
        World world = location.getWorld();
        UUID uuid = player.getUniqueId();

        if (!locationsSeenByPlayer.containsKey(uuid)) {
            locationsSeenByPlayer.put(uuid, new ArrayList<>());
        }

        for (int x = -BlockRenderingService.SHOW_RADIUS; x < BlockRenderingService.SHOW_RADIUS; x++) {
            for (int y = -BlockRenderingService.SHOW_RADIUS; y < BlockRenderingService.SHOW_RADIUS; y++) {
                for (int z = -BlockRenderingService.SHOW_RADIUS; z < BlockRenderingService.SHOW_RADIUS; z++) {
                    Block block = Objects.requireNonNull(world).getBlockAt(
                        location.getBlockX() + x,
                        location.getBlockY() + y,
                        location.getBlockZ() + z
                    );

                    this.renderBlockAtLocationForPlayer(block, player);
                }
            }
        }
    }
}
