package wtf.cephetir.voidmenu;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.texture.ITextureObject;
import net.minecraft.client.renderer.texture.SimpleTexture;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import wtf.cephetir.voidmenu.mixins.IMixinTextureManagerAccessor;
import wtf.cephetir.voidmenu.utils.AnimatedResourceLocation;

@Mod(modid = "void", name = "V0ID", version = "1.0")
public class Void {
    public static final String NAME = "V0ID", VERSION = "1.0", AUTHOR = "Cephetir", PREFIX = "[V0ID] ";
    private static final Void INSTANCE = new Void();
    private final Minecraft mc = Minecraft.getMinecraft();
    public static Logger logger;

    @Mod.EventHandler
    public void onInit(FMLPreInitializationEvent event) {
        logger = LogManager.getLogger("V0ID");
        print("Launching...");
        print("Current version " + VERSION);
    }

    @Mod.EventHandler
    public void onInit(FMLInitializationEvent event) {
        AnimatedResourceLocation rl = new AnimatedResourceLocation("void/bg", 300, 1, false);
        for (int i = 0; i < 300; i++) {
            rl.update();
            ResourceLocation resource = rl.getTexture();
            ITextureObject object = ((IMixinTextureManagerAccessor) mc.getTextureManager()).getMapTextureObjects().get(resource);
            if (object == null) {
                object = new SimpleTexture(resource);
                mc.getTextureManager().loadTexture(resource, object);
            }
        }
    }

    public static Void getInstance() {
        return INSTANCE;
    }

    public void print(String msg) {
        logger.info(PREFIX + msg);
    }
}
