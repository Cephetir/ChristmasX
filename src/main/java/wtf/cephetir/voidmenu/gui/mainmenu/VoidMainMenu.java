package wtf.cephetir.voidmenu.gui.mainmenu;

import net.minecraft.client.gui.*;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.GL11;
import wtf.cephetir.voidmenu.Void;
import wtf.cephetir.voidmenu.gui.mainmenu.comp.CustomButton;
import wtf.cephetir.voidmenu.gui.mainmenu.comp.GuiScreen;
import wtf.cephetir.voidmenu.utils.AnimatedResourceLocation;

import java.awt.*;

public class VoidMainMenu extends GuiScreen {
    private int right = 0;
    private AnimatedResourceLocation bg;

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        bg.update();
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
        mc.getTextureManager().bindTexture(bg.getTexture());
        GlStateManager.resetColor();
        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
        drawModalRectWithCustomSizedTexture(-25 + (Mouse.getX() / 90), ((Mouse.getY() * -1 / 90)), 0, 0, width + 25, height + 25, width + 25, height + 25);

        drawRect(-1, -1, -1, -1, 1);
        drawRect(0, 0, right, this.height, new Color(1, 1, 1, 155).getRGB());
        if (height >= 550 && width >= 1100) {
            drawImg();
            GlStateManager.scale(1.5f, 1.5f, 1.5f);
            this.drawCenteredString(mc.fontRendererObj, Void.NAME, 72, height / 4 - 75, new Color(140, 34, 239, 255).getRGB());
            GlStateManager.scale(0.55f, 0.55f, 0.55f);
            this.drawCenteredString(mc.fontRendererObj, Void.VERSION, 130, height / 2 - 207 + 55, new Color(179, 103, 255, 255).getRGB());
            this.drawCenteredString(mc.fontRendererObj, "by " + Void.AUTHOR, 35, height + 132, new Color(140, 34, 239, 255).getRGB());
        } else if (height >= 400 && width >= 800) {
            drawImg2();
            GlStateManager.scale(1.5f, 1.5f, 1.5f);
            this.drawCenteredString(mc.fontRendererObj, Void.NAME, 72, height / 4 - 73, new Color(140, 34, 239, 230).getRGB());
            GlStateManager.scale(0.55f, 0.55f, 0.55f);
            this.drawCenteredString(mc.fontRendererObj, Void.VERSION, 130, height / 2 - 207 + 60, new Color(160, 70, 253, 230).getRGB());
        }

        GlStateManager.enableAlpha();
        GlStateManager.alphaFunc(516, 0.1F);
        GlStateManager.popMatrix();

        super.drawScreen(mouseX, mouseY, partialTicks);
    }

    private void drawImg() {
        this.mc.getTextureManager().bindTexture(new ResourceLocation("void/icons/256x256.png"));
        GlStateManager.resetColor();
        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
        drawModalRectWithCustomSizedTexture(0, 0, 0.0f, 0.0f, 1, 1, 1, 1);
        drawModalRectWithCustomSizedTexture(width / 14, height / 7, 0.0f, 0.0f, 32, 32, 32, 32);
    }

    private void drawImg2() {
        this.mc.getTextureManager().bindTexture(new ResourceLocation("void/icons/256x256.png"));
        GlStateManager.resetColor();
        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
        drawModalRectWithCustomSizedTexture(0, 0, 0.0f, 0.0f, 1, 1, 1, 1);
        drawModalRectWithCustomSizedTexture(width / 12 + 10, height / 12, 0.0f, 0.0f, 32, 32, 32, 32);
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
        this.buttonList.clear();
        /*this.buttonList.add(new GuiButton(1, 10, height / 2 - 60, "Singleplayer"));
        this.buttonList.add(new GuiButton(2, 10, height / 2 - 30, "Multiplayer"));
        this.buttonList.add(new GuiButton(3, 10, height / 2 - 0, "Settings"));
        this.buttonList.add(new GuiButton(4, 10, height / 2 + 30, "Alt Manager"));
        this.buttonList.add(new GuiButton(5, 10, height / 2 + 60, "Quit"));*/
        this.customButtonList.add(new CustomButton("sp", 10, height / 2 - 60, 200, 20, "Singleplayer", new Color(149, 50, 243, 255), new Color(173, 93, 238, 94), new Color(100, 23, 241, 255), new Color(162, 86, 236, 126)));
        this.customButtonList.add(new CustomButton("mp", 10, height / 2 - 30, 200, 20, "Multiplayer", new Color(149, 50, 243, 255), new Color(173, 93, 238, 94), new Color(100, 23, 241, 255), new Color(162, 86, 236, 126)));
        this.customButtonList.add(new CustomButton("st", 10, height / 2 - 0, 200, 20, "Settings", new Color(149, 50, 243, 255), new Color(173, 93, 238, 94), new Color(100, 23, 241, 255), new Color(162, 86, 236, 126)));
        //this.customButtonList.add(new CustomButton("am", 10, height / 2 + 30, 200, 20, "Alt Manager", new Color(149, 50, 243, 255), new Color(173, 93, 238, 94), new Color(100, 23, 241, 255), new Color(162, 86, 236, 126)));
        this.customButtonList.add(new CustomButton("q", 10, height / 2 + 30, 200, 20, "Quit", new Color(149, 50, 243, 255), new Color(173, 93, 238, 94), new Color(100, 23, 241, 255), new Color(162, 86, 236, 126)));
        anim();
        //ffmpeg -i "C:\Program Files (x86)\Steam\steamapps\workshop\content\431960\2351726596\TopoDesktop.mp4" -pix_fmt rgba "D:\mod\1VoidClient\src\main\resources\assets\minecraft\void\bg\%01d.png"
        bg = new AnimatedResourceLocation("void/bg", 300, 1, false);
        //Void.INSTANCE.getRPC().update("Main Menu", "Idle");
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
            case "am":
                //mc.displayGuiScreen(new GuiAltManager(this));
                break;
            case "q":
                mc.shutdown();
                break;
        }
        super.actionPerformed(button);
    }
}
