package wtf.cephetir.christmasx;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraftforge.client.event.GuiScreenEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import org.apache.logging.log4j.Logger;
import wtf.cephetir.christmasx.config.ChristmasConfig;
import wtf.cephetir.christmasx.gui.mainmenu.comp.particles.ParticleGenerator;

@Mod(modid = "christmasx", name = "ChristmasX", version = "1.0")
public class ChristmasX {
    public static final String NAME = "ChristmasX", VERSION = "1.0", AUTHOR = "Cephetir", PREFIX = "[ChristmasX] ";
    private static final ChristmasX INSTANCE = new ChristmasX();
    public Logger logger;
    public ParticleGenerator particleGenerator;

    public static ChristmasX getInstance() {
        return INSTANCE;
    }

    public void print(String msg) {
        logger.info(PREFIX + msg);
    }

    @SubscribeEvent
    public void onRender(GuiScreenEvent.DrawScreenEvent.Pre event) {
        if (event.gui instanceof GuiContainer && ChristmasConfig.toggleSnow) {
            particleGenerator.breite = event.gui.width;
            particleGenerator.höhe = event.gui.height;
            particleGenerator.drawParticles();
        }
    }
}
