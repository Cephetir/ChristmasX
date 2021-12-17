package wtf.cephetir.christmasx.gui.mainmenu;

import net.minecraft.client.gui.GuiMultiplayer;
import net.minecraft.client.gui.GuiOptions;
import net.minecraft.client.gui.GuiSelectWorld;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.GL11;
import wtf.cephetir.christmasx.ChristmasX;
import wtf.cephetir.christmasx.gui.mainmenu.comp.CustomButton;
import wtf.cephetir.christmasx.gui.mainmenu.comp.GuiScreen;
import wtf.cephetir.christmasx.gui.mainmenu.comp.particles.ParticleGenerator;

import java.awt.*;

public class ChristmasMainMenu extends GuiScreen {
    private int right = 0;
    private ResourceLocation bg;
    private final ResourceLocation minecraftTitleTextures = new ResourceLocation("textures/gui/title/minecraft.png");
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
        mc.getTextureManager().bindTexture(bg);
        GlStateManager.resetColor();
        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
        drawModalRectWithCustomSizedTexture(-25 + (Mouse.getX() / 90), ((Mouse.getY() * -1 / 90)), 0, 0, width + 25, height + 25, width + 25, height + 25);

        drawRect(-1, -1, -1, -1, 1);
        drawRect(0, 0, right, this.height, new Color(1, 1, 1, 155).getRGB());
//        if (height >= 550 && width >= 1100) {
//            //drawImg();
//            GlStateManager.scale(1.5f, 1.5f, 1.5f);
//            this.drawCenteredString(mc.fontRendererObj, ChristmasX.NAME, 72, height / 4 - 95, new Color(255, 50, 50, 255).getRGB());
//            GlStateManager.scale(0.55f, 0.55f, 0.55f);
//            this.drawCenteredString(mc.fontRendererObj, ChristmasX.VERSION, 130, height / 2 - 207 + 25, new Color(255, 80, 80, 255).getRGB());
//        } else if (height >= 400 && width >= 800) {
//            //drawImg2();
//            GlStateManager.scale(1.5f, 1.5f, 1.5f);
//            this.drawCenteredString(mc.fontRendererObj, ChristmasX.NAME, 72, height / 4 - 83, new Color(255, 50, 50, 255).getRGB());
//            GlStateManager.scale(0.55f, 0.55f, 0.55f);
//            this.drawCenteredString(mc.fontRendererObj, ChristmasX.VERSION, 130, height / 2 - 207 + 48, new Color(255, 80, 80, 255).getRGB());
//        }


        GlStateManager.enableAlpha();
        GlStateManager.alphaFunc(516, 0.1F);
        GlStateManager.popMatrix();

        int i = 274;
        int j = this.width / 2 - i / 2;
        int k = 30;
        this.mc.getTextureManager().bindTexture(minecraftTitleTextures);
        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
        GlStateManager.scale(0.7, 0.7, 0.7);
        this.drawTexturedModalRect(j - 480, k + 100, 0, 0, 155, 44);
        this.drawTexturedModalRect(j + 155 - 480, k + 100, 0, 45, 155, 44);
        GlStateManager.scale(1.4285714286, 1.4285714286, 1.4285714286);

        particleGenerator.breite = width;
        particleGenerator.höhe = height;
        particleGenerator.drawParticles();

        super.drawScreen(mouseX, mouseY, partialTicks);
    }

    private void anim() {
        new Thread(() -> {
            int cooldown = 50;
            while (right != 225) {
                right++;
                if (cooldown > 10) {
                    cooldown -= 10;
                } else if (cooldown > 1) {
                    cooldown -= 1;
                }
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
        this.customButtonList.add(new CustomButton("chbg", 10, height - (fontRendererObj.FONT_HEIGHT + 13), 200, 20, "Customize", new Color(255, 255, 255, 255), new Color(255, 255, 255, 94), new Color(243, 100, 100, 255), new Color(208, 59, 59, 255)));
        anim();
        //ffmpeg -i "C:\Program Files (x86)\Steam\steamapps\workshop\content\431960\2351726596\TopoDesktop.mp4" -pix_fmt rgba "D:\mod\1VoidClient\src\main\resources\assets\minecraft\void\bg\%01d.png"
        if (ChristmasX.getInstance().config.bg > 8 || ChristmasX.getInstance().config.bg == 4)
            bg = new ResourceLocation("void/bg/" + ChristmasX.getInstance().config.bg + ".png");
        else bg = new ResourceLocation("void/bg/" + ChristmasX.getInstance().config.bg + ".jpg");
        particleGenerator = new ParticleGenerator(150, width, height);
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
            case "chbg":
                mc.displayGuiScreen(new ChristmasCustomizeMenu(this));
                break;
        }
        super.actionPerformed(button);
    }
}
