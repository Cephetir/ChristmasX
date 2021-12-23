package wtf.cephetir.christmasx.utils;

import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import org.lwjgl.opengl.GL11;

public class RenderUtils {

    public static void drawCircle(float x, float y, float radius, int color) {
        float alpha = (float) (color >> 24 & 255) / 255.0F;
        float red = (float) (color >> 16 & 255) / 255.0F;
        float green = (float) (color >> 8 & 255) / 255.0F;
        float blue = (float) (color & 255) / 255.0F;
        GlStateManager.color(red, green, blue, alpha);
        GlStateManager.disableTexture2D();
        GlStateManager.enableBlend();
        GlStateManager.blendFunc(770, 771);
        GL11.glEnable(2848);
        GL11.glLineWidth(1.0F);
        Tessellator tessellator = Tessellator.getInstance();
        WorldRenderer wr = tessellator.getWorldRenderer();
        wr.begin(9, DefaultVertexFormats.POSITION);
        int i = 0;

        for (short var12 = 360; i <= var12; ++i) {
            double var10001 = x;
            double var13 = (double) i * 3.141592653589793D / 180.0D;
            var10001 += Math.sin(var13) * (double) radius;
            double var10002 = y;
            var13 = (double) i * 3.141592653589793D / 180.0D;
            wr.pos(var10001, var10002 + Math.cos(var13) * (double) radius, 0.0D).endVertex();
        }

        tessellator.draw();
        GlStateManager.enableTexture2D();
        GL11.glDisable(2848);
        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
    }
}