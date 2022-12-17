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
        this.addDrawableChild(new ButtonWidget(this.width / 2 - 255, this.height/6 + 90, 250, 20,
                getText(Modification.HIDE_PLAYERS), (button) -> {
            Modification.HIDE_PLAYERS.setEnabled(!Modification.HIDE_PLAYERS.isEnabled());
            button.setMessage(getText(Modification.HIDE_PLAYERS));
        }));

        // Outline Clashers mod
        this.addDrawableChild(new ButtonWidget(this.width / 2 + 5, this.height/6 + 90, 250, 20,
                getText(Modification.OUTLINE_CLASHERS), (button) -> {
            Modification.OUTLINE_CLASHERS.setEnabled(!Modification.OUTLINE_CLASHERS.isEnabled());
            button.setMessage(getText(Modification.OUTLINE_CLASHERS));
        }));

        // Alive Counter mod
        this.addDrawableChild(new ButtonWidget(this.width / 2 - 255, this.height/6 + 120, 250, 20,
                getText(Modification.ALIVE_COUNT), (button) -> {
            Modification.ALIVE_COUNT.setEnabled(!Modification.ALIVE_COUNT.isEnabled());
            button.setMessage(getText(Modification.ALIVE_COUNT));
        }));

        // Back button
        this.addDrawableChild(new ButtonWidget(this.width / 2 + 5, this.height/6 + 120, 250, 20,
                ScreenTexts.BACK, (button) -> {
            assert this.client != null;
            this.client.setScreen(this.parent);
        }));
    }

    @Override
    public void render(MatrixStack matrixStack, int mouseX, int mouseY, float partialTicks) {
        renderBackground(matrixStack);
        super.render(matrixStack, mouseX, mouseY, partialTicks);
    }
}
