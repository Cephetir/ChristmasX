package wtf.cephetir.christmasx.mixins;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiMainMenu;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.texture.TextureManager;
import org.spongepowered.asm.lib.Opcodes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import wtf.cephetir.christmasx.gui.mainmenu.ChristmasMainMenu;
import wtf.cephetir.christmasx.gui.splash.ChristmasSplashScreen;

@Mixin(Minecraft.class)
public class MixinMinecraft {
    @Shadow public TextureManager renderEngine;

    @ModifyVariable(method = "displayGuiScreen(Lnet/minecraft/client/gui/GuiScreen;)V", at = @At(value = "LOAD"), argsOnly = true)
    public GuiScreen displayGuiScreen(GuiScreen screen) {
        return screen instanceof GuiMainMenu ? new ChristmasMainMenu() : screen;
    }

    @Inject(method = "startGame", at = @At(value = "FIELD", opcode = Opcodes.PUTFIELD, target = "Lnet/minecraft/client/Minecraft;fontRendererObj:Lnet/minecraft/client/gui/FontRenderer;"))
    public void startGame(CallbackInfo ci) {
        ChristmasSplashScreen.drawSplash(this.renderEngine);
    }

    @Inject(method = "startGame", at = @At(value = "FIELD", opcode = Opcodes.PUTFIELD, target = "Lnet/minecraft/client/Minecraft;standardGalacticFontRenderer:Lnet/minecraft/client/gui/FontRenderer;"))
    public void startGame1(CallbackInfo ci) {
        ChristmasSplashScreen.update("Pre startup", 1);
    }

    @Inject(method = "startGame", at = @At(value = "FIELD", opcode = Opcodes.PUTFIELD, target = "Lnet/minecraft/client/Minecraft;textureMapBlocks:Lnet/minecraft/client/renderer/texture/TextureMap;"))
    public void startGame2(CallbackInfo ci) {
        ChristmasSplashScreen.update("Loading Texture Map", 2);
    }

    @Inject(method = "startGame", at = @At(value = "FIELD", opcode = Opcodes.PUTFIELD, target = "Lnet/minecraft/client/Minecraft;modelManager:Lnet/minecraft/client/resources/model/ModelManager;"))
    public void startGame3(CallbackInfo ci) {
        ChristmasSplashScreen.update("Loading Model Manager", 3);
    }

    @Inject(method = "startGame", at = @At(value = "FIELD", opcode = Opcodes.PUTFIELD, target = "Lnet/minecraft/client/Minecraft;renderItem:Lnet/minecraft/client/renderer/entity/RenderItem;"))
    public void startGame6(CallbackInfo ci) {
        ChristmasSplashScreen.update("Loading Item Renderer", 4);
    }

    @Inject(method = "startGame", at = @At(value = "FIELD", opcode = Opcodes.PUTFIELD, target = "Lnet/minecraft/client/Minecraft;entityRenderer:Lnet/minecraft/client/renderer/EntityRenderer;"))
    public void startGame4(CallbackInfo ci) {
        ChristmasSplashScreen.update("Loading Entity Renderer", 5);
    }

    @Inject(method = "startGame", at = @At(value = "FIELD", opcode = Opcodes.PUTFIELD, target = "Lnet/minecraft/client/Minecraft;ingameGUI:Lnet/minecraft/client/gui/GuiIngame;"))
    public void startGame5(CallbackInfo ci) {
        ChristmasSplashScreen.update("Post startup", 6);
    }
}
