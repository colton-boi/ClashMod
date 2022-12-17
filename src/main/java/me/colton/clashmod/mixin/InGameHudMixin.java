package me.colton.clashmod.mixin;

import me.colton.clashmod.client.ClashModClient;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.hud.InGameHud;
import net.minecraft.client.util.math.MatrixStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(InGameHud.class)
public abstract class InGameHudMixin {

    @Shadow public abstract TextRenderer getTextRenderer();

    @Shadow private int scaledWidth;

    @Inject(at = @At("HEAD"), method = "render")
    public void render(MatrixStack matrices, float tickDelta, CallbackInfo ci) {

        int alive = ClashModClient.getAlivePlayers();

        int negativeX = getTextRenderer().getWidth("Players remaining: " + alive);
        int xLocation;
        if (ClashModClient.client.currentScreen != null) {
            xLocation = ClashModClient.client.currentScreen.width - negativeX - 10;
        } else {
            xLocation = this.scaledWidth - negativeX - 10;
        }

        getTextRenderer().drawWithShadow(matrices, "Players remaining: " + alive,
                xLocation, 10, 16777215);
    }
}
