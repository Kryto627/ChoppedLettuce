package calemi.fusionwarfare.key;

import calemi.fusionwarfare.gui.GuiTeam;
import calemi.fusionwarfare.gui.recipe.GuiTwoInputsRecipe;
import calemi.fusionwarfare.recipe.TwoInputRecipeRegistry;
import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.InputEvent.KeyInputEvent;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;

public class KeyInputHandler {

	@SubscribeEvent	
	public void onKeyInput(KeyInputEvent event) {
				
		if (KeyBindings.teamGuiButton.isPressed()) {		
			
			EntityPlayer player = Minecraft.getMinecraft().thePlayer;
			
			FMLClientHandler.instance().displayGuiScreen(player, new GuiTeam(player));
		}
	}
}