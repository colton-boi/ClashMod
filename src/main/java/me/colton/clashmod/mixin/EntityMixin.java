package me.colton.clashmod.mixin;

import me.colton.clashmod.Modification;
import me.colton.clashmod.client.ClashModClient;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Entity.class)
public abstract class EntityMixin {

    @Shadow public abstract Text getName();

    @Shadow public abstract EntityType<?> getType();

    @Inject(at = @At("HEAD"), method = "isGlowing", cancellable = true)
    public void isGlowing(CallbackInfoReturnable<Boolean> cir) {
        if (ClashModClient.client.player == null) {
            return;
        }
        if (Modification.OUTLINE_CLASHERS.isEnabled()) {
            if (getName().equals(Text.literal("MasterClashers"))) {
                cir.setReturnValue(true);
            }
        }
    }

    @Inject(at = @At("HEAD"), method = "isInvisible", cancellable = true)
    public void isInvisible(CallbackInfoReturnable<Boolean> cir) {
        if (ClashModClient.client.player == null) {
            return;
        }
        if (Modification.HIDE_PLAYERS.isEnabled()) {
            if (getType() == EntityType.PLAYER) {
                if (!getName().equals(Text.literal("MasterClashers")) &&
                        !getName().equals(ClashModClient.client.player.getName())) {
                    cir.setReturnValue(true);
                }
            }
        }
    }
}
