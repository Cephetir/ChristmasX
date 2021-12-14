package wtf.cephetir.voidmenu.gui.splash;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.client.shader.Framebuffer;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;
import wtf.cephetir.voidmenu.Void;

import java.awt.*;

public class VoidSplashScreen {
    public static Minecraft mc = Minecraft.getMinecraft();
    private static ResourceLocation splash;

    public static void update() {
        if(mc == null || mc.getLanguageManager() == null || mc.getTextureManager() == null) return;
        drawSplash(mc.getTextureManager());
    }

    public static void drawSplash(TextureManager tm) {
        ScaledResolution scaledResolution = new ScaledResolution(mc);
        int scaleFactor = scaledResolution.getScaleFactor();

        Framebuffer frameBuffer = new Framebuffer(scaledResolution.getScaledWidth() * scaleFactor, scaledResolution.getScaledHeight() * scaleFactor, true);
        frameBuffer.bindFramebuffer(false);

        GlStateManager.matrixMode(GL11.GL_PROJECTION);
        GlStateManager.loadIdentity();
        GlStateManager.ortho(0.0D, scaledResolution.getScaledWidth(), (double)scaledResolution.getScaledHeight(), 0.0D, 1000.0D, 3000.D);
        GlStateManager.matrixMode(GL11.GL_MODELVIEW);
        GlStateManager.loadIdentity();
        GlStateManager.translate(0.0F, 0.0F, -2000.0F);
        GlStateManager.disableLighting();
        GlStateManager.disableFog();
        GlStateManager.disableDepth();
        GlStateManager.enableTexture2D();

        if(splash == null) splash = new ResourceLocation("void/endportal.jpg");

        tm.bindTexture(splash);

        GlStateManager.resetColor();
        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);

        Gui.drawScaledCustomSizeModalRect(0, 0, 0, 0, 1200, 630, scaledResolution.getScaledWidth(), scaledResolution.getScaledHeight(), 1200, 630);
        Gui.drawRect(scaledResolution.getScaledWidth() / 2 - 16 - 10, scaledResolution.getScaledHeight() / 2 - 16 - 10, scaledResolution.getScaledWidth() / 2 - 16 - 10 + 32 + 10 + 10, scaledResolution.getScaledHeight() / 2 - 16 - 10 + 32 + 15 + 10, new Color(1, 1, 1, 155).getRGB());
        if(mc.fontRendererObj != null) mc.fontRendererObj.drawStringWithShadow(Void.NAME, scaledResolution.getScaledWidth() / 2f - 10f, scaledResolution.getScaledHeight() / 2f + 18f, new Color(140, 34, 239, 255).getRGB());
        drawImg(scaledResolution.getScaledWidth(), scaledResolution.getScaledHeight());
        frameBuffer.unbindFramebuffer();
        frameBuffer.framebufferRender(scaledResolution.getScaledWidth() * scaleFactor, scaledResolution.getScaledHeight() * scaleFactor);
        GlStateManager.enableAlpha();
        GlStateManager.alphaFunc(516, 0.1F);

        mc.updateDisplay();
    }

    private static void drawImg(int width, int height) {
        mc.getTextureManager().bindTexture(new ResourceLocation("void/icons/256x256.png"));
        GlStateManager.resetColor();
        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
        Gui.drawModalRectWithCustomSizedTexture(0, 0, 0.0f, 0.0f, 1, 1, 1, 1);
        Gui.drawModalRectWithCustomSizedTexture(width / 2 - 16, height / 2 - 16, 0.0f, 0.0f, 32, 32, 32, 32);
    }
}
