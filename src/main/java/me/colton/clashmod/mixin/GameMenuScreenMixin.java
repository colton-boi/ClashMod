package me.colton.clashmod.mixin;

import me.colton.clashmod.screen.ClashModScreen;
import net.minecraft.client.gui.screen.GameMenuScreen;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(GameMenuScreen.class)
public abstract class GameMenuScreenMixin extends Screen {
    protected GameMenuScreenMixin(Text title) {
        super(title);
    }

    @Inject(at = @At("HEAD"), method = "initWidgets")
    public void initWidgets(CallbackInfo ci) {
        this.addDrawableChild(ButtonWidget.builder(Text.literal("ClashMod"), (buttonWidget) -> {
            assert this.client != null;
            this.client.setScreen(new ClashModScreen(this));
        }).dimensions(10, 10, 70, 20).build());
    }
}
