package me.colton.clashmod.screen;

import me.colton.clashmod.Modification;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.screen.ScreenTexts;
import net.minecraft.text.Text;

public class ClashModScreen extends Screen {

    private final Screen parent;

    public ClashModScreen(Screen parent) {
        super(Text.of("ClashEvents Mod"));
        this.parent = parent;
    }

    Text getText(Modification modification) {
        return Text.of(modification.getDisplayName() + " is currently " + (modification.isEnabled() ? "enabled" : "disabled"));
    }

    protected void init() {
        // Hide Players mod
        this.addDrawableChild(ButtonWidget.builder(getText(Modification.HIDE_PLAYERS), (buttonWidget) -> {
            Modification.HIDE_PLAYERS.setEnabled(!Modification.HIDE_PLAYERS.isEnabled());
            buttonWidget.setMessage(getText(Modification.HIDE_PLAYERS));
        }).dimensions(this.width / 2 - 255, this.height / 6 + 90, 250, 20).build());

        // Outline Clashers mod
        this.addDrawableChild(ButtonWidget.builder(getText(Modification.OUTLINE_CLASHERS), (buttonWidget) -> {
            Modification.OUTLINE_CLASHERS.setEnabled(!Modification.OUTLINE_CLASHERS.isEnabled());
            buttonWidget.setMessage(getText(Modification.OUTLINE_CLASHERS));
        }).dimensions(this.width / 2 + 5, this.height / 6 + 90, 250, 20).build());

        // Alive Counter mod
        this.addDrawableChild(ButtonWidget.builder(getText(Modification.ALIVE_COUNT), (buttonWidget) -> {
            Modification.ALIVE_COUNT.setEnabled(!Modification.ALIVE_COUNT.isEnabled());
            buttonWidget.setMessage(getText(Modification.ALIVE_COUNT));
        }).dimensions(this.width / 2 - 255, this.height / 6 + 120, 250, 20).build());

        // Back button
        this.addDrawableChild(ButtonWidget.builder(ScreenTexts.BACK, (buttonWidget) -> {
            assert this.client != null;
            this.client.setScreen(this.parent);
        }).dimensions(this.width / 2 + 5, this.height / 6 + 120, 250, 20).build());
    }

    @Override
    public void render(MatrixStack matrixStack, int mouseX, int mouseY, float partialTicks) {
        renderBackground(matrixStack);
        super.render(matrixStack, mouseX, mouseY, partialTicks);
    }
}
