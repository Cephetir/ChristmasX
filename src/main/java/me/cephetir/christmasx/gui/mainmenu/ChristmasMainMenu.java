package me.cephetir.christmasx.gui.mainmenu;

import me.cephetir.christmasx.utils.TextureUtils;
import net.minecraft.client.gui.GuiMultiplayer;
import net.minecraft.client.gui.GuiOptions;
import net.minecraft.client.gui.GuiSelectWorld;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.GL11;
import me.cephetir.christmasx.ChristmasX;
import me.cephetir.christmasx.config.ChristmasConfig;
import me.cephetir.christmasx.gui.mainmenu.comp.CustomButton;
import me.cephetir.christmasx.gui.mainmenu.comp.GuiScreen;
import me.cephetir.christmasx.gui.mainmenu.comp.particles.ParticleGenerator;

import java.awt.*;
import java.io.File;

public class ChristmasMainMenu extends GuiScreen {
    private int right = 0;
    private File customBg;
    private ResourceLocation bg;
    public ParticleGenerator particleGenerator;

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        GlStateManager.pushMatrix();
        ScaledResolution scaledResolution = new ScaledResolution(mc);
        GlStateManager.matrixMode(GL11.GL_PROJECTION);
        GlStateManager.loadIdentity();
        GlStateManager.ortho(0.0D, scaledResolution.getScaledWidth(), scaledResolution.getScaledHeight(), 0.0D, 1000.0D, 3000.D);
        GlStateManager.matrixMode(GL11.GL_MODELVIEW);
        GlStateManager.loadIdentity();
        GlStateManager.translate(0.0F, 0.0F, -2000.0F);
        GlStateManager.disableLighting();
        GlStateManager.disableFog();
        GlStateManager.disableDepth();
        GlStateManager.enableTexture2D();

        if (customBg == null || !customBg.isFile() || !customBg.exists()) mc.getTextureManager().bindTexture(bg);
        else TextureUtils.bindTexture(customBg);

        GlStateManager.resetColor();
        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
        drawModalRectWithCustomSizedTexture(-25 + (Mouse.getX() / 90), ((Mouse.getY() * -1 / 90)), 0, 0, width + 25, height + 25, width + 25, height + 25);

        drawRect(-1, -1, -1, -1, 1);
        drawRect(0, 0, right, this.height, new Color(1, 1, 1, 155).getRGB());

        GlStateManager.enableAlpha();
        GlStateManager.alphaFunc(516, 0.1F);
        GlStateManager.popMatrix();

        if (ChristmasConfig.toggleSnow) {
            particleGenerator.breite = width;
            particleGenerator.höhe = height;
            particleGenerator.drawParticles();
        }

        super.drawScreen(mouseX, mouseY, partialTicks);
    }

    private void anim() {
        new Thread(() -> {
            int cooldown = 50;
            while (right != 225) {
                right++;
                if (cooldown > 10) cooldown -= 10;
                else if (cooldown > 1) cooldown -= 1;
                try {
                    Thread.sleep(cooldown);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    @Override
    public void initGui() {
        this.customButtonList.clear();
        this.customButtonList.add(new CustomButton("sp", 10, height / 2 - 60, 200, 20, "Singleplayer", new Color(255, 255, 255, 255), new Color(255, 255, 255, 94), new Color(243, 100, 100, 255), new Color(208, 59, 59, 255)));
        this.customButtonList.add(new CustomButton("mp", 10, height / 2 - 30, 200, 20, "Multiplayer", new Color(255, 255, 255, 255), new Color(255, 255, 255, 94), new Color(243, 100, 100, 255), new Color(208, 59, 59, 255)));
        this.customButtonList.add(new CustomButton("st", 10, height / 2 - 0, 200, 20, "Settings", new Color(255, 255, 255, 255), new Color(255, 255, 255, 94), new Color(243, 100, 100, 255), new Color(208, 59, 59, 255)));
        this.customButtonList.add(new CustomButton("q", 10, height / 2 + 30, 200, 20, "Quit", new Color(255, 255, 255, 255), new Color(255, 255, 255, 94), new Color(243, 100, 100, 255), new Color(208, 59, 59, 255)));
        anim();

        //ffmpeg -i "C:\Program Files (x86)\Steam\steamapps\workshop\content\431960\2351726596\TopoDesktop.mp4" -pix_fmt rgba "D:\mod\1VoidClient\src\main\resources\assets\minecraft\christmasx\bg\%01d.png"
        customBg = null;
        File file = new File(ChristmasConfig.customBgPath);
        if (ChristmasConfig.customBg && file.isFile() && file.exists()) customBg = file;

        if (ChristmasConfig.bg > 8 || ChristmasConfig.bg == 4) bg = new ResourceLocation("christmasx/bg/" + ChristmasConfig.bg + ".png");
        else bg = new ResourceLocation("christmasx/bg/" + ChristmasConfig.bg + ".jpg");

        ChristmasX.INSTANCE.particleGenerator = particleGenerator = new ParticleGenerator(ChristmasConfig.pAmount, width, height);
        super.initGui();
    }

    @Override
    protected void actionPerformed(CustomButton button) {
        switch (button.function) {
            case "sp":
                mc.displayGuiScreen(new GuiSelectWorld(this));
                break;
            case "mp":
                mc.displayGuiScreen(new GuiMultiplayer(this));
                break;
            case "st":
                mc.displayGuiScreen(new GuiOptions(this, mc.gameSettings));
                break;
            case "q":
                mc.shutdown();
                break;
        }
        super.actionPerformed(button);
    }
}
