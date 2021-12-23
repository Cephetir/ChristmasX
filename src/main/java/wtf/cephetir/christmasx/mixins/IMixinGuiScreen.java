package wtf.cephetir.christmasx.mixins;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

import java.util.List;

@Mixin(GuiScreen.class)
public interface IMixinGuiScreen {
    @Accessor("buttonList")
    List<GuiButton> getbuttonlist();
}
