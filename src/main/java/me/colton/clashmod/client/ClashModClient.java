package me.colton.clashmod.client;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.MinecraftClient;
import net.minecraft.item.Item;

@Environment(EnvType.CLIENT)
public class ClashModClient implements ClientModInitializer {
    public static MinecraftClient client;
    public static ClashModClient instance;
    public static int ticks;
    private static Integer playerCount;

    @Override
    public void onInitializeClient() {
        client = MinecraftClient.getInstance();
        instance = this;
    }

    public void tick() {
        ticks++;
    }

    public static int getAlivePlayers() {
        if (client.world != null) {
            if (playerCount == null || Math.IEEEremainder(ticks, 20) == 0) {
                playerCount = (int) client.world.getPlayers().stream().filter(player ->
                                player.getInventory().getArmorStack(3).getItem().equals(Item.byRawId(786)))
                        .count();
            }
            return playerCount;
        }

        return 0;
    }
}
