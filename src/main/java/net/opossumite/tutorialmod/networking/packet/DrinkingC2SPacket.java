package net.opossumite.tutorialmod.networking.packet;

import net.fabricmc.fabric.api.networking.v1.PacketSender;
import net.minecraft.block.Blocks;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnReason;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayNetworkHandler;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Style;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.math.BlockPos;

public class DrinkingC2SPacket {
    private static final String MESSAGE_DRINKING_WATER = "message.tutorialmod.drank_water";
    private static final String MESSAGE_NO_WATER_NEARBY = "message.tutorialmod.no_water";

    public static void recieve(MinecraftServer server, ServerPlayerEntity player, ServerPlayNetworkHandler handler,
                               PacketByteBuf buf, PacketSender responseSender) {
        // everything here only happens server-side
        ServerWorld world = player.getWorld();
        if(isWaterAroundThem(player, world, 2)) {
            // notify player
            player.sendMessage(Text.translatable(MESSAGE_DRINKING_WATER)
                    .fillStyle(Style.EMPTY.withColor(Formatting.DARK_AQUA)), false);
            // play drinking sound
            world.playSound(null, player.getBlockPos(), SoundEvents.ENTITY_GENERIC_DRINK, SoundCategory.PLAYERS,
                    0.5F, world.random.nextFloat() * 0.1F + 0.9F);
            // output how much thirst player has
            // actually add water level to player
        } else {
            // notify player
            player.sendMessage(Text.translatable(MESSAGE_NO_WATER_NEARBY)
                    .fillStyle(Style.EMPTY.withColor(Formatting.RED)), false);
        }


    }

    private static boolean isWaterAroundThem(ServerPlayerEntity player, ServerWorld world, int size) {
        return BlockPos.stream(player.getBoundingBox().expand(2))
                .map(world::getBlockState).filter(state -> state.isOf(Blocks.WATER)).toArray().length > 0;
    }
}
