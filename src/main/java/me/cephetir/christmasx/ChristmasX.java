package me.cephetir.christmasx;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraftforge.client.event.GuiScreenEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import me.cephetir.christmasx.config.ChristmasConfig;
import me.cephetir.christmasx.gui.mainmenu.comp.particles.ParticleGenerator;

@Mod(modid = "christmasx", name = ChristmasX.NAME, version = ChristmasX.VERSION)
public class ChristmasX {
    public static final String NAME = "ChristmasX", VERSION = "1.0", AUTHOR = "Cephetir";
    public static final ChristmasX INSTANCE = new ChristmasX();
    public static final Logger logger = LogManager.getLogger("ChristmasX");
    public ParticleGenerator particleGenerator;

    @SubscribeEvent
    public void onRender(GuiScreenEvent.DrawScreenEvent.Pre event) {
        if (event.gui instanceof GuiContainer && ChristmasConfig.toggleSnow) {
            particleGenerator.breite = event.gui.width;
            particleGenerator.höhe = event.gui.height;
            particleGenerator.drawParticles();
        }
    }
}
