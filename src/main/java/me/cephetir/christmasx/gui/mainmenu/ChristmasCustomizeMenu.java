package me.cephetir.christmasx.gui.mainmenu;

import me.cephetir.christmasx.ChristmasX;
import me.cephetir.christmasx.config.ChristmasConfig;
import me.cephetir.christmasx.gui.mainmenu.comp.CustomButton;
import me.cephetir.christmasx.gui.mainmenu.comp.GuiScreen;
import me.cephetir.christmasx.gui.mainmenu.comp.particles.ParticleGenerator;
import me.cephetir.christmasx.utils.RoundedUtils;
import me.cephetir.christmasx.utils.TextureUtils;
import net.minecraft.client.gui.GuiTextField;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.GL11;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class ChristmasCustomizeMenu extends GuiScreen {
    private final List<GuiTextField> textFields = new LinkedList<>();

    private File customBg;
    private ResourceLocation bg;
    public ParticleGenerator particleGenerator;
    private final net.minecraft.client.gui.GuiScreen parent;

    public ChristmasCustomizeMenu(net.minecraft.client.gui.GuiScreen parent) {
        this.parent = parent;
    }

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

        drawRect(0, 0, 0, 0, -1);
        drawRect(0, 0, width, height, new Color(0, 0, 0, 135).getRGB());

        RoundedUtils.drawRoundedRect(width / 2f - fontRendererObj.getStringWidth("Change snow speed X") / 2f - (fontRendererObj.getStringWidth("<-") + 16) - 90 * 2, height / 5f + 20 * 5, (width / 2f - fontRendererObj.getStringWidth("Change snow speed X") / 2f - (fontRendererObj.getStringWidth("<-") + 16) - 50) + 198 + 120 + 90, height / 5f + 20 * 3 + fontRendererObj.FONT_HEIGHT + 8 + 20 + 20 + 20, 6f, new Color(255, 255, 255, 94).getRGB());
        fontRendererObj.drawStringWithShadow("Current Particles Speed X: " + ChristmasConfig.speedX + ", Speed Y: " + ChristmasConfig.speedY + ", Amount: " + ChristmasConfig.pAmount, width / 2f - fontRendererObj.getStringWidth("Current Particles Speed X: " + ChristmasConfig.speedX + ", Speed Y: " + ChristmasConfig.speedY + ", Amount: " + ChristmasConfig.pAmount) / 2f + 8, height / 5f + 20 * 2 + 24 + 40, new Color(243, 100, 100, 255).getRGB());
        fontRendererObj.drawStringWithShadow("Custom Splash Screen: " + (ChristmasConfig.toggleSplash ? "On" : "Off") + ", Custom Buttons: " + (ChristmasConfig.toggleButtons ? "On" : "Off") + ", Snow Particles: " + (ChristmasConfig.toggleSnow ? "On" : "Off") + ", Custom Background: " + (ChristmasConfig.customBg ? "On" : "Off"), width / 2f - fontRendererObj.getStringWidth("Custom Splash Screen: " + (ChristmasConfig.toggleSplash ? "On" : "Off") + ", Custom Buttons: " + (ChristmasConfig.toggleButtons ? "On" : "Off") + ", Snow Particles: " + (ChristmasConfig.toggleSnow ? "On" : "Off") + ", Custom Background: " + (ChristmasConfig.customBg ? "On" : "Off")) / 2f + 8, height / 5f + 20 * 2 + 24 + 40 + 20, new Color(243, 100, 100, 255).getRGB());

        GlStateManager.enableAlpha();
        GlStateManager.alphaFunc(516, 0.1F);
        GlStateManager.popMatrix();

        if (ChristmasConfig.toggleSnow) {
            particleGenerator.breite = width;
            particleGenerator.höhe = height;
            particleGenerator.drawParticles();
        }

        for (GuiTextField textField : textFields)
            textField.drawTextBox();

        super.drawScreen(mouseX, mouseY, partialTicks);
    }

    @Override
    public void initGui() {
        this.customButtonList.clear();
        this.customButtonList.add(new CustomButton("q", 10, height - (fontRendererObj.FONT_HEIGHT + 13), 200, 20, "Return", new Color(255, 255, 255, 255), new Color(255, 255, 255, 94), new Color(243, 100, 100, 255), new Color(208, 59, 59, 255)));
        this.customButtonList.add(new CustomButton("ts", width / 2 - fontRendererObj.getStringWidth("Toggle Custom Splash Screen") / 2 - 17, height / 5 - 40, fontRendererObj.getStringWidth("Toggle Custom Splash Screen") + 16 + 34, fontRendererObj.FONT_HEIGHT + 8, "Toggle Custom Splash Screen", new Color(255, 255, 255, 255), new Color(255, 255, 255, 94), new Color(243, 100, 100, 255), new Color(208, 59, 59, 255)));
        this.customButtonList.add(new CustomButton("tcb", width / 2 - fontRendererObj.getStringWidth("Toggle Custom Splash Screen") / 2 - 17, height / 5 - 20, fontRendererObj.getStringWidth("Toggle Custom Splash Screen") + 16 + 34, fontRendererObj.FONT_HEIGHT + 8, "Toggle Custom Buttons", new Color(255, 255, 255, 255), new Color(255, 255, 255, 94), new Color(243, 100, 100, 255), new Color(208, 59, 59, 255)));
        this.customButtonList.add(new CustomButton("n", width / 2 - fontRendererObj.getStringWidth("Change background X") / 2, height / 5, fontRendererObj.getStringWidth("Change background X") + 16, fontRendererObj.FONT_HEIGHT + 8, "Change Background", new Color(243, 100, 100, 255), new Color(255, 255, 255, 94), new Color(243, 100, 100, 255), new Color(255, 255, 255, 94)));
        this.customButtonList.add(new CustomButton("chbg+", width / 2 + fontRendererObj.getStringWidth("Change snow speed X") / 2 + (fontRendererObj.getStringWidth("->") + 10) + 5, height / 5, fontRendererObj.getStringWidth("->") + 16, fontRendererObj.FONT_HEIGHT + 8, "->", new Color(255, 255, 255, 255), new Color(255, 255, 255, 94), new Color(243, 100, 100, 255), new Color(208, 59, 59, 255)));
        this.customButtonList.add(new CustomButton("chbg-", width / 2 - fontRendererObj.getStringWidth("Change snow speed X") / 2 - (fontRendererObj.getStringWidth("<-") + 16) - 10, height / 5, fontRendererObj.getStringWidth("<-") + 16, fontRendererObj.FONT_HEIGHT + 8, "<-", new Color(255, 255, 255, 255), new Color(255, 255, 255, 94), new Color(243, 100, 100, 255), new Color(208, 59, 59, 255)));
        this.customButtonList.add(new CustomButton("n", width / 2 - fontRendererObj.getStringWidth("Change snow speed X") / 2, height / 5 + 20, fontRendererObj.getStringWidth("Change snow speed X") + 16, fontRendererObj.FONT_HEIGHT + 8, "Change Snow Speed X", new Color(243, 100, 100, 255), new Color(255, 255, 255, 94), new Color(243, 100, 100, 255), new Color(255, 255, 255, 94)));
        this.customButtonList.add(new CustomButton("chspx+", width / 2 + fontRendererObj.getStringWidth("Change snow speed X") / 2 + (fontRendererObj.getStringWidth("->") + 10) + 5, height / 5 + 20, fontRendererObj.getStringWidth("->") + 16, fontRendererObj.FONT_HEIGHT + 8, "->", new Color(255, 255, 255, 255), new Color(255, 255, 255, 94), new Color(243, 100, 100, 255), new Color(208, 59, 59, 255)));
        this.customButtonList.add(new CustomButton("chspx-", width / 2 - fontRendererObj.getStringWidth("Change snow speed X") / 2 - (fontRendererObj.getStringWidth("<-") + 16) - 10, height / 5 + 20, fontRendererObj.getStringWidth("<-") + 16, fontRendererObj.FONT_HEIGHT + 8, "<-", new Color(255, 255, 255, 255), new Color(255, 255, 255, 94), new Color(243, 100, 100, 255), new Color(208, 59, 59, 255)));
        this.customButtonList.add(new CustomButton("n", width / 2 - fontRendererObj.getStringWidth("Change snow speed Y") / 2, height / 5 + 20 * 2, fontRendererObj.getStringWidth("Change snow speed Y") + 16, fontRendererObj.FONT_HEIGHT + 8, "Change Snow Speed Y", new Color(243, 100, 100, 255), new Color(255, 255, 255, 94), new Color(243, 100, 100, 255), new Color(255, 255, 255, 94)));
        this.customButtonList.add(new CustomButton("chspy+", width / 2 + fontRendererObj.getStringWidth("Change snow speed Y") / 2 + (fontRendererObj.getStringWidth("->") + 10) + 5, height / 5 + 20 * 2, fontRendererObj.getStringWidth("->") + 16, fontRendererObj.FONT_HEIGHT + 8, "->", new Color(255, 255, 255, 255), new Color(255, 255, 255, 94), new Color(243, 100, 100, 255), new Color(208, 59, 59, 255)));
        this.customButtonList.add(new CustomButton("chspy-", width / 2 - fontRendererObj.getStringWidth("Change snow speed Y") / 2 - (fontRendererObj.getStringWidth("<-") + 16) - 10, height / 5 + 20 * 2, fontRendererObj.getStringWidth("<-") + 16, fontRendererObj.FONT_HEIGHT + 8, "<-", new Color(255, 255, 255, 255), new Color(255, 255, 255, 94), new Color(243, 100, 100, 255), new Color(208, 59, 59, 255)));
        this.customButtonList.add(new CustomButton("n", width / 2 - fontRendererObj.getStringWidth("Change snow speed Y") / 2, height / 5 + 20 * 3, fontRendererObj.getStringWidth("Change snow speed Y") + 16, fontRendererObj.FONT_HEIGHT + 8, "Change Snow Amount", new Color(243, 100, 100, 255), new Color(255, 255, 255, 94), new Color(243, 100, 100, 255), new Color(255, 255, 255, 94)));
        this.customButtonList.add(new CustomButton("chpa+", width / 2 + fontRendererObj.getStringWidth("Change snow speed Y") / 2 + (fontRendererObj.getStringWidth("->") + 10) + 5, height / 5 + 20 * 3, fontRendererObj.getStringWidth("->") + 16, fontRendererObj.FONT_HEIGHT + 8, "->", new Color(255, 255, 255, 255), new Color(255, 255, 255, 94), new Color(243, 100, 100, 255), new Color(208, 59, 59, 255)));
        this.customButtonList.add(new CustomButton("chpa-", width / 2 - fontRendererObj.getStringWidth("Change snow speed Y") / 2 - (fontRendererObj.getStringWidth("<-") + 16) - 10, height / 5 + 20 * 3, fontRendererObj.getStringWidth("<-") + 16, fontRendererObj.FONT_HEIGHT + 8, "<-", new Color(255, 255, 255, 255), new Color(255, 255, 255, 94), new Color(243, 100, 100, 255), new Color(208, 59, 59, 255)));
        this.customButtonList.add(new CustomButton("snow", width / 2 - fontRendererObj.getStringWidth("Toggle Custom Splash Screen") / 2 - 17, height / 5 + 20 * 4, fontRendererObj.getStringWidth("Toggle Custom Splash Screen") + 16 + 34, fontRendererObj.FONT_HEIGHT + 8, "Toggle Snow Particles", new Color(255, 255, 255, 255), new Color(255, 255, 255, 94), new Color(243, 100, 100, 255), new Color(208, 59, 59, 255)));

        this.customButtonList.add(new CustomButton("customBg", width / 2 - fontRendererObj.getStringWidth("Toggle Custom Splash Screen") / 2 - 17, height / 5 + 20 * 7, fontRendererObj.getStringWidth("Toggle Custom Splash Screen") + 16 + 34, fontRendererObj.FONT_HEIGHT + 8, "Toggle Custom Background", new Color(255, 255, 255, 255), new Color(255, 255, 255, 94), new Color(243, 100, 100, 255), new Color(208, 59, 59, 255)));
        GuiTextField field = new GuiTextField(69420, mc.fontRendererObj, width / 2 - fontRendererObj.getStringWidth("Toggle Custom Splash ScreenToggle Custom Splash Screen") / 2 - 17, height / 5 + 20 * 8, fontRendererObj.getStringWidth("Toggle Custom Splash ScreenToggle Custom Splash Screen") + 16 + 34, fontRendererObj.FONT_HEIGHT + 8);
        field.setMaxStringLength(128);
        field.setText(ChristmasConfig.customBgPath);
        this.textFields.add(field);

        this.customButtonList.add(new CustomButton("buttonOutline", width / 2 - fontRendererObj.getStringWidth("Toggle Custom Splash Screen") / 2 - 17, height / 5 + 20 * 9, fontRendererObj.getStringWidth("Toggle Custom Splash Screen") + 16 + 34, fontRendererObj.FONT_HEIGHT + 8, "Toggle Button Outline", new Color(255, 255, 255, 255), new Color(255, 255, 255, 94), new Color(243, 100, 100, 255), new Color(208, 59, 59, 255)));

        //ffmpeg -i "C:\Program Files (x86)\Steam\steamapps\workshop\content\431960\2351726596\TopoDesktop.mp4" -pix_fmt rgba "D:\mod\1VoidClient\src\main\resources\assets\minecraft\christmasx\bg\%01d.png"
        customBg = null;
        File file = new File(ChristmasConfig.customBgPath);
        if (ChristmasConfig.customBg && file.isFile() && file.exists()) customBg = file;

        if (ChristmasConfig.bg > 8 || ChristmasConfig.bg == 4) bg = new ResourceLocation("christmasx/bg/" + ChristmasConfig.bg + ".png");
        else bg = new ResourceLocation("christmasx/bg/" + ChristmasConfig.bg + ".jpg");

        ChristmasX.INSTANCE.particleGenerator = this.particleGenerator = new ParticleGenerator(ChristmasConfig.pAmount, width, height);
        super.initGui();
    }

    @Override
    protected void actionPerformed(CustomButton button) {
        switch (button.function) {
            case "n":
                break;
            case "ts":
                ChristmasConfig.toggleSplash = !ChristmasConfig.toggleSplash;
                break;
            case "tcb":
                ChristmasConfig.toggleButtons = !ChristmasConfig.toggleButtons;
                break;
            case "snow":
                ChristmasConfig.toggleSnow = !ChristmasConfig.toggleSnow;
                break;
            case "q":
                mc.displayGuiScreen(parent);
                break;
            case "chbg+":
                ChristmasConfig.bg++;
                if (ChristmasConfig.bg > 13) ChristmasConfig.bg = 1;
                if (ChristmasConfig.bg > 8 || ChristmasConfig.bg == 4)
                    bg = new ResourceLocation("christmasx/bg/" + ChristmasConfig.bg + ".png");
                else bg = new ResourceLocation("christmasx/bg/" + ChristmasConfig.bg + ".jpg");
                break;
            case "chbg-":
                ChristmasConfig.bg--;
                if (ChristmasConfig.bg < 1) ChristmasConfig.bg = 13;
                if (ChristmasConfig.bg > 8 || ChristmasConfig.bg == 4)
                    bg = new ResourceLocation("christmasx/bg/" + ChristmasConfig.bg + ".png");
                else bg = new ResourceLocation("christmasx/bg/" + ChristmasConfig.bg + ".jpg");
                break;
            case "chspx+":
                ChristmasConfig.speedX++;
                break;
            case "chspx-":
                ChristmasConfig.speedX--;
                break;
            case "chspy+":
                ChristmasConfig.speedY++;
                break;
            case "chspy-":
                ChristmasConfig.speedY--;
                break;
            case "chpa+":
                ChristmasConfig.pAmount += 10;
                ChristmasX.INSTANCE.particleGenerator = particleGenerator = new ParticleGenerator(ChristmasConfig.pAmount, width, height);
                break;
            case "chpa-":
                ChristmasConfig.pAmount -= 10;
                if (ChristmasConfig.pAmount <= 0) ChristmasConfig.pAmount = 0;
                ChristmasX.INSTANCE.particleGenerator = particleGenerator = new ParticleGenerator(ChristmasConfig.pAmount, width, height);
                break;
            case "customBg":
                ChristmasConfig.customBg = !ChristmasConfig.customBg;
                if (ChristmasConfig.customBg) {
                    File file = new File(ChristmasConfig.customBgPath);
                    if (file.isFile() && file.exists()) customBg = file;
                } else customBg = null;
                break;
            case "buttonOutline":
                ChristmasConfig.buttonOutline = !ChristmasConfig.buttonOutline;
                break;
        }
        super.actionPerformed(button);
    }

    @Override
    protected void mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException {
        for (GuiTextField textField : textFields)
            textField.mouseClicked(mouseX, mouseY, mouseButton);

        super.mouseClicked(mouseX, mouseY, mouseButton);
    }

    @Override
    protected void keyTyped(char typedChar, int keyCode) {
        for (GuiTextField textField : textFields)
            textField.textboxKeyTyped(typedChar, keyCode);
        ChristmasConfig.customBgPath = textFields.get(0).getText();

        if (ChristmasConfig.customBg) {
            File file = new File(ChristmasConfig.customBgPath);
            if (file.isFile() && file.exists()) customBg = file;
        }

        super.keyTyped(typedChar, keyCode);
    }
}
