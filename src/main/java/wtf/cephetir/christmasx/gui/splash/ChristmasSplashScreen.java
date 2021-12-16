package wtf.cephetir.christmasx.gui.splash;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.client.shader.Framebuffer;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;
import wtf.cephetir.christmasx.Christmas;
import wtf.cephetir.christmasx.utils.RoundedUtils;

import java.awt.*;

public class ChristmasSplashScreen {
    public static Minecraft mc = Minecraft.getMinecraft();

    private static String name = "Starting...";
    private static int step = 0;

    public static void update(String name, int step) {
        if(mc == null || mc.getLanguageManager() == null || mc.getTextureManager() == null) return;
        drawSplash(mc.getTextureManager());
        ChristmasSplashScreen.name = name;
        ChristmasSplashScreen.step = step;
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
        GlStateManager.resetColor();
        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);

        Gui.drawRect(0, 0, scaledResolution.getScaledWidth(), scaledResolution.getScaledHeight(), Color.BLACK.getRGB());
        if(mc.fontRendererObj != null) mc.fontRendererObj.drawStringWithShadow(Christmas.NAME, scaledResolution.getScaledWidth() / 2f - (mc.fontRendererObj.getStringWidth(Christmas.NAME) / 2f), scaledResolution.getScaledHeight() / 2f + 18f, new Color(255, 50, 50, 255).getRGB());
        drawImg(scaledResolution.getScaledWidth(), scaledResolution.getScaledHeight(), tm);
        drawProgress();
        frameBuffer.unbindFramebuffer();
        frameBuffer.framebufferRender(scaledResolution.getScaledWidth() * scaleFactor, scaledResolution.getScaledHeight() * scaleFactor);
        GlStateManager.enableAlpha();
        GlStateManager.alphaFunc(516, 0.1F);

        mc.updateDisplay();
    }

    private static void drawImg(int width, int height, TextureManager textureManager) {
        textureManager.bindTexture(new ResourceLocation("void/icons/256x256.png"));
        GlStateManager.resetColor();
        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
        Gui.drawModalRectWithCustomSizedTexture(0, 0, 0.0f, 0.0f, 1, 1, 1, 1);
        Gui.drawModalRectWithCustomSizedTexture(width / 2 - 16, height / 2 - 16 - 3, 0.0f, 0.0f, 32, 32, 32, 32);
    }

    private static void drawProgress() {
        ScaledResolution sr = new ScaledResolution(mc);
        float calc = (step / 6f) * 100f;

        RoundedUtils.drawSmoothRoundedRect(sr.getScaledWidth() / 2f - 50f, sr.getScaledHeight() / 2f + 18 + 15, sr.getScaledWidth() / 2f + 50f, sr.getScaledHeight() / 2f + 18 + 15 + 5, 5f, new Color(255, 255, 255, 255).getRGB());
        GlStateManager.resetColor();

        if(mc.fontRendererObj != null) mc.fontRendererObj.drawStringWithShadow(name, sr.getScaledWidth() / 2f - 50f, sr.getScaledHeight() / 2f + 18 + 15 + 5 + 9, new Color(255, 80, 80, 255).getRGB());
        GlStateManager.resetColor();

        RoundedUtils.drawSmoothRoundedRect(sr.getScaledWidth() / 2f - 50f, sr.getScaledHeight() / 2f + 18 + 15, sr.getScaledWidth() / 2f - 50f + calc, sr.getScaledHeight() / 2f + 18 + 15 + 5, 5f, new Color(208, 58, 58).getRGB());
    }
}
