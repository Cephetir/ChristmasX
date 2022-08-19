package me.cephetir.christmasx.mixins;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiOptions;
import net.minecraft.client.gui.GuiScreen;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import me.cephetir.christmasx.gui.mainmenu.ChristmasCustomizeMenu;

@Mixin(GuiOptions.class)
public class MixinGuiOptions {
    @Inject(method = "initGui", at = @At("TAIL"))
    public void initGui(CallbackInfo ci) {
        IMixinGuiScreen IMixinGuiScreen = (IMixinGuiScreen) (Object) this;
        GuiScreen GuiScreen = (GuiScreen) (Object) this;
        IMixinGuiScreen.getbuttonlist().add(new GuiButton(999, GuiScreen.width / 2 - 155, GuiScreen.height / 6 + 48 - 6 - 24, 150, 20, "ChristmasX..."));
    }

    @Inject(method = "actionPerformed", at = @At("HEAD"))
    public void actionPerformed(GuiButton istream, CallbackInfo ci) {
        if(istream.id == 999) Minecraft.getMinecraft().displayGuiScreen(new ChristmasCustomizeMenu((GuiScreen) (Object) this));
    }
}
