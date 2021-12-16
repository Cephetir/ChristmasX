package wtf.cephetir.christmasx.gui.mainmenu;

import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.GL11;
import wtf.cephetir.christmasx.ChristmasX;
import wtf.cephetir.christmasx.gui.mainmenu.comp.CustomButton;
import wtf.cephetir.christmasx.gui.mainmenu.comp.GuiScreen;

import java.awt.*;

public class ChristmasCustomizeMenu extends GuiScreen {
    private ResourceLocation bg;

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
        mc.getTextureManager().bindTexture(bg);
        GlStateManager.resetColor();
        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
        drawModalRectWithCustomSizedTexture(-25 + (Mouse.getX() / 90), ((Mouse.getY() * -1 / 90)), 0, 0, width + 25, height + 25, width + 25, height + 25);

        drawRect(0, 0, width, height, new Color(0, 0, 0, 135).getRGB());

        GlStateManager.enableAlpha();
        GlStateManager.alphaFunc(516, 0.1F);
        GlStateManager.popMatrix();

        ChristmasX.getInstance().particleGenerator.breite = width;
        ChristmasX.getInstance().particleGenerator.höhe = height;
        ChristmasX.getInstance().particleGenerator.drawParticles();

        super.drawScreen(mouseX, mouseY, partialTicks);
    }

    @Override
    public void initGui() {
        this.customButtonList.clear();
        this.customButtonList.add(new CustomButton("q", 10, height - (fontRendererObj.FONT_HEIGHT + 13), 200, 20, "Return", new Color(255, 255, 255, 255), new Color(255, 255, 255, 94), new Color(243, 100, 100, 255), new Color(208, 59, 59, 255)));
        this.customButtonList.add(new CustomButton("n", width / 2 - fontRendererObj.getStringWidth("Change background") / 2, height / 5, fontRendererObj.getStringWidth("Change background") + 16, fontRendererObj.FONT_HEIGHT + 8, "Change background", new Color(243, 100, 100, 255), new Color(255, 255, 255, 94), new Color(243, 100, 100, 255), new Color(255, 255, 255, 94)));
        this.customButtonList.add(new CustomButton("chbg+", width / 2 + fontRendererObj.getStringWidth("Change snow speed X") / 2 + (fontRendererObj.getStringWidth("->") + 10) + 5, height / 5, fontRendererObj.getStringWidth("->") + 16, fontRendererObj.FONT_HEIGHT + 8, "->", new Color(255, 255, 255, 255), new Color(255, 255, 255, 94), new Color(243, 100, 100, 255), new Color(208, 59, 59, 255)));
        this.customButtonList.add(new CustomButton("chbg-", width / 2 - fontRendererObj.getStringWidth("Change snow speed X") / 2 - (fontRendererObj.getStringWidth("<-") + 16) - 10, height / 5, fontRendererObj.getStringWidth("<-") + 16, fontRendererObj.FONT_HEIGHT + 8, "<-", new Color(255, 255, 255, 255), new Color(255, 255, 255, 94), new Color(243, 100, 100, 255), new Color(208, 59, 59, 255)));
        this.customButtonList.add(new CustomButton("n", width / 2 - fontRendererObj.getStringWidth("Change snow speed X") / 2, height / 5 + 20, fontRendererObj.getStringWidth("Change snow speed X") + 16, fontRendererObj.FONT_HEIGHT + 8, "Change snow speed X", new Color(243, 100, 100, 255), new Color(255, 255, 255, 94), new Color(243, 100, 100, 255), new Color(255, 255, 255, 94)));
        this.customButtonList.add(new CustomButton("chspx+", width / 2 + fontRendererObj.getStringWidth("Change snow speed X") / 2 + (fontRendererObj.getStringWidth("->") + 10) + 5, height / 5 + 20, fontRendererObj.getStringWidth("->") + 16, fontRendererObj.FONT_HEIGHT + 8, "->", new Color(255, 255, 255, 255), new Color(255, 255, 255, 94), new Color(243, 100, 100, 255), new Color(208, 59, 59, 255)));
        this.customButtonList.add(new CustomButton("chspx-", width / 2 - fontRendererObj.getStringWidth("Change snow speed X") / 2 - (fontRendererObj.getStringWidth("<-") + 16) - 10, height / 5 + 20, fontRendererObj.getStringWidth("<-") + 16, fontRendererObj.FONT_HEIGHT + 8, "<-", new Color(255, 255, 255, 255), new Color(255, 255, 255, 94), new Color(243, 100, 100, 255), new Color(208, 59, 59, 255)));
        this.customButtonList.add(new CustomButton("n", width / 2 - fontRendererObj.getStringWidth("Change snow speed Y") / 2, height / 5 + 20 * 2, fontRendererObj.getStringWidth("Change snow speed Y") + 16, fontRendererObj.FONT_HEIGHT + 8, "Change snow speed Y", new Color(243, 100, 100, 255), new Color(255, 255, 255, 94), new Color(243, 100, 100, 255), new Color(255, 255, 255, 94)));
        this.customButtonList.add(new CustomButton("chspy+", width / 2 + fontRendererObj.getStringWidth("Change snow speed Y") / 2 + (fontRendererObj.getStringWidth("->") + 10) + 5, height / 5 + 20 * 2, fontRendererObj.getStringWidth("->") + 16, fontRendererObj.FONT_HEIGHT + 8, "->", new Color(255, 255, 255, 255), new Color(255, 255, 255, 94), new Color(243, 100, 100, 255), new Color(208, 59, 59, 255)));
        this.customButtonList.add(new CustomButton("chspy-", width / 2 - fontRendererObj.getStringWidth("Change snow speed Y") / 2 - (fontRendererObj.getStringWidth("<-") + 16) - 10, height / 5 + 20 * 2, fontRendererObj.getStringWidth("<-") + 16, fontRendererObj.FONT_HEIGHT + 8, "<-", new Color(255, 255, 255, 255), new Color(255, 255, 255, 94), new Color(243, 100, 100, 255), new Color(208, 59, 59, 255)));
        //ffmpeg -i "C:\Program Files (x86)\Steam\steamapps\workshop\content\431960\2351726596\TopoDesktop.mp4" -pix_fmt rgba "D:\mod\1VoidClient\src\main\resources\assets\minecraft\void\bg\%01d.png"
        if(ChristmasX.getInstance().config.bg>8) bg = new ResourceLocation("void/bg/" + ChristmasX.getInstance().config.bg + ".png");
        else bg = new ResourceLocation("void/bg/" + ChristmasX.getInstance().config.bg + ".jpg");
        super.initGui();
    }

    @Override
    protected void actionPerformed(CustomButton button) {
        switch (button.function) {
            case "n":
                break;
            case "q":
                mc.displayGuiScreen(new ChristmasMainMenu());
                break;
            case "chbg+":
                ChristmasX.getInstance().config.bg++;
                if(ChristmasX.getInstance().config.bg>13) ChristmasX.getInstance().config.bg = 1;
                if(ChristmasX.getInstance().config.bg>8) bg = new ResourceLocation("void/bg/" + ChristmasX.getInstance().config.bg + ".png");
                else bg = new ResourceLocation("void/bg/" + ChristmasX.getInstance().config.bg + ".jpg");
                break;
            case "chbg-":
                ChristmasX.getInstance().config.bg--;
                if(ChristmasX.getInstance().config.bg<1) ChristmasX.getInstance().config.bg = 13;
                if(ChristmasX.getInstance().config.bg>8) bg = new ResourceLocation("void/bg/" + ChristmasX.getInstance().config.bg + ".png");
                else bg = new ResourceLocation("void/bg/" + ChristmasX.getInstance().config.bg + ".jpg");
                break;
            case "chspx+":
                ChristmasX.getInstance().particleGenerator.changeSpeed(ChristmasX.getInstance().particleGenerator.speedX + 1, ChristmasX.getInstance().particleGenerator.speedY);
                break;
            case "chspx-":
                ChristmasX.getInstance().particleGenerator.changeSpeed(Math.max(ChristmasX.getInstance().particleGenerator.speedX - 1, 1), ChristmasX.getInstance().particleGenerator.speedY);
                break;
            case "chspy+":
                ChristmasX.getInstance().particleGenerator.changeSpeed(ChristmasX.getInstance().particleGenerator.speedX, ChristmasX.getInstance().particleGenerator.speedY + 1);
                break;
            case "chspy-":
                ChristmasX.getInstance().particleGenerator.changeSpeed(ChristmasX.getInstance().particleGenerator.speedX, Math.max(ChristmasX.getInstance().particleGenerator.speedY - 1, 1));
                break;
        }
        super.actionPerformed(button);
    }
}
