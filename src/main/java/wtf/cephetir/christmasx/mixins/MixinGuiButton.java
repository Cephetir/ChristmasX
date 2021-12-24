package wtf.cephetir.christmasx.mixins;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.renderer.GlStateManager;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import wtf.cephetir.christmasx.config.ChristmasConfig;
import wtf.cephetir.christmasx.utils.RoundedUtils;

import java.awt.*;

@Mixin(GuiButton.class)
public abstract class MixinGuiButton {
    @Shadow
    protected boolean hovered;

    @Shadow
    public int xPosition;

    @Shadow
    public int yPosition;

    @Shadow
    public int width;

    @Shadow
    protected abstract void mouseDragged(Minecraft mc, int mouseX, int mouseY);

    @Shadow
    public String displayString;

    @Shadow
    public int height;

    @Shadow public boolean visible;

    @Inject(method = "drawButton", at = @At("HEAD"), cancellable = true)
    public void drawButton(Minecraft mc, int mouseX, int mouseY, CallbackInfo cir) {
        if (ChristmasConfig.toggleButtons && this.visible) {
            cir.cancel();
            FontRenderer fontrenderer = mc.fontRendererObj;
            GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
            this.hovered = mouseX >= this.xPosition && mouseY >= this.yPosition && mouseX < this.xPosition + this.width && mouseY < this.yPosition + this.height;
            GlStateManager.enableBlend();
            GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
            GlStateManager.blendFunc(770, 771);

            int p = new Color(255, 255, 255, 94).getRGB();
            if (this.hovered) p = new Color(208, 59, 59, 255).getRGB();

            RoundedUtils.drawRoundedRect((float) this.xPosition, (float) this.yPosition, (float) this.xPosition + (float) this.width, (float) this.yPosition + (float) this.height, 6.0F, p);
            this.mouseDragged(mc, mouseX, mouseY);
            int j = new Color(243, 100, 100, 255).getRGB();

            if (this.hovered) j = new Color(255, 255, 255, 255).getRGB();

            ((Gui) (Object) this).drawCenteredString(fontrenderer, displayString, (int) (this.xPosition + this.width / 2 + 0.7f), this.yPosition + (this.height - 8) / 2, j);
        }
    }
}
