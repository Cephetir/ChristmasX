package me.cephetir.christmasx.utils;

import me.cephetir.christmasx.ChristmasX;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.texture.TextureUtil;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.HashMap;

public class TextureUtils {
    private static final HashMap<File, Integer> cache = new HashMap<>();


    public static void bindTexture(File image) {
        if (!image.exists()) {
            ChristmasX.logger.error("Couldn't load image!");
            return;
        }

        try {
            if (cache.containsKey(image))
                GlStateManager.bindTexture(cache.get(image));
            else {
                BufferedImage bufferedImage = ImageIO.read(image);
                int textureId = TextureUtil.glGenTextures();
                TextureUtil.uploadTextureImage(textureId, bufferedImage);
                GlStateManager.bindTexture(textureId);
                cache.put(image, textureId);
            }
        } catch (Exception ioexception) {
            ChristmasX.logger.error("Couldn't load texture", ioexception);
        }
    }
}
