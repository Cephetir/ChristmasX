package wtf.cephetir.christmasx;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import wtf.cephetir.christmasx.config.ChristmasConfig;

@Mod(modid = "christmasx", name = "ChristmasX", version = "1.0")
public class ChristmasX {
    public static final String NAME = "ChristmasX", VERSION = "1.0", AUTHOR = "Cephetir", PREFIX = "[ChristmasX] ";
    private static final ChristmasX INSTANCE = new ChristmasX();
    public Logger logger;
    public ChristmasConfig config;

    @Mod.EventHandler
    public void onInit(FMLPreInitializationEvent event) {
        getInstance().config = new ChristmasConfig();
        getInstance().logger = LogManager.getLogger("ChristmasX");
        getInstance().print("Launching...");
        getInstance().print("Current version " + VERSION);
        getInstance().config.load();
    }

    public static ChristmasX getInstance() {
        return INSTANCE;
    }

    public void print(String msg) {
        logger.info(PREFIX + msg);
    }
}
