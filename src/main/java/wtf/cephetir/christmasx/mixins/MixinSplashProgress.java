package wtf.cephetir.christmasx.mixins;

import net.minecraftforge.fml.client.SplashProgress;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import wtf.cephetir.christmasx.config.ChristmasConfig;

@Mixin(SplashProgress.class)
public class MixinSplashProgress {
    @Unique
    private static ChristmasConfig configg;

    @Inject(method = "start", at = @At(value = "HEAD"), cancellable = true, remap = false)
    private static void start(CallbackInfo ci) {
        configg = new ChristmasConfig();
        configg.load();
        if (configg.toggleSplash) ci.cancel();
    }

    @Inject(method = "drawVanillaScreen", at = @At(value = "HEAD"), cancellable = true, remap = false)
    private static void drawVanillaScreen(CallbackInfo ci) {
        if (configg.toggleSplash) ci.cancel();
    }

    @Inject(method = "finish", at = @At(value = "HEAD"), cancellable = true, remap = false)
    private static void finish(CallbackInfo ci) {
        if (configg.toggleSplash) ci.cancel();
    }
}
