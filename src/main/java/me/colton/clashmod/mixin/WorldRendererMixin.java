package me.colton.clashmod.mixin;

import me.colton.clashmod.Modification;
import me.colton.clashmod.client.ClashModClient;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.WorldRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(WorldRenderer.class)
public abstract class WorldRendererMixin {

    @Inject(at = @At("HEAD"), method = "renderEntity", cancellable = true)
    public void renderEntity(Entity entity, double cameraX, double cameraY, double cameraZ, float tickDelta, MatrixStack matrices, VertexConsumerProvider vertexConsumers, CallbackInfo ci) {
        if (ClashModClient.client.player == null) {
            return;
        }
        if (Modification.HIDE_PLAYERS.isEnabled()) {
            if (entity instanceof PlayerEntity player) {
                if (!player.getName().equals(Text.literal("MasterClashers")) &&
                        !player.getName().equals(ClashModClient.client.player.getName())) {
                    ci.cancel();
                }
            }
        }
    }
}
