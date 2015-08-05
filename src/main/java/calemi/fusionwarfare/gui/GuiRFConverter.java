package calemi.fusionwarfare.gui;

import calemi.fusionwarfare.FusionWarfare;
import calemi.fusionwarfare.Reference;
import calemi.fusionwarfare.gui.button.GuiFusionButton;
import calemi.fusionwarfare.inventory.ContainerEnergyTank;
import calemi.fusionwarfare.inventory.ContainerRFConverter;
import calemi.fusionwarfare.packet.ServerPacketHandler;
import calemi.fusionwarfare.tileentity.TileEntityBase;
import calemi.fusionwarfare.tileentity.machine.TileEntityMissileSiloCore;
import calemi.fusionwarfare.tileentity.machine.TileEntityRFConverter;
import codechicken.nei.recipe.GuiCraftingRecipe;
import cpw.mods.fml.common.Loader;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.util.ResourceLocation;

public class GuiRFConverter extends GuiContainerBase {

	private GuiFusionButton button;
	
	private TileEntityRFConverter tileEntityRF;
	
	public GuiRFConverter(EntityPlayer player, TileEntityBase tileEntity) {
		super(new ContainerRFConverter(player, tileEntity), player, tileEntity);
		
		tileEntityRF = (TileEntityRFConverter)tileEntity;
	}

	@Override
	public void initGui() {
		super.initGui();
		
		button = new GuiFusionButton(0, getScreenX() + 72, getScreenY() + 36, 32, tileEntityRF.outputFusion ? "<--" : "-->");
		buttonList.add(button);		
	}

	@Override
	protected void actionPerformed(GuiButton button) {

		if (button.id == 0) {
			
			if (tileEntityRF.outputFusion) tileEntityRF.outputFusion = false;
			else tileEntityRF.outputFusion = true;
			
			FusionWarfare.network.sendToServer(new ServerPacketHandler("output.mode%" + tileEntity.xCoord + "%" + tileEntity.yCoord + "%" + tileEntity.zCoord));
		}
	}
	
	@Override
	public void updateScreen() {
		
		button.displayString = tileEntityRF.outputFusion ? "<--" : "-->";
	}
	
	@Override
	public String getGuiTextures() {
		return "rf_converter";
	}

	@Override
	public String getGuiTitle() {
		return "RF Converter";
	}

	@Override
	public void drawGuiBackground(int mouseX, int mouseY) {
		
		drawSmallFuelBar(19, 69);
		
		mc.getTextureManager().bindTexture(new ResourceLocation(Reference.MOD_ID + ":textures/gui/rf_converter.png"));
		
		int scaledFuel = tileEntityRF.getCurrentRFScaled(50);
			
		this.drawTexturedModalRect(getScreenX() + 143, (getScreenY() + 69) - scaledFuel, 176, 50 - scaledFuel, 14, scaledFuel + 1);		
	}

	@Override
	public void drawGuiForeground(int mouseX, int mouseY) {
		
		fontRendererObj.drawString("5", getScreenX() + 52, getScreenY() + 40, 4210752);
		
		fontRendererObj.drawString("1000", getScreenX() + 110, getScreenY() + 40, 4210752);
		
		drawSmallFuelBarTextBox(19, 69, mouseX, mouseY);
		
		drawStringOverBox("RF: " + tileEntityRF.storage.getEnergyStored() + "/" + tileEntityRF.storage.getMaxEnergyStored(), 143, 69 - 50, 14, 50, mouseX, mouseY);
	}
}