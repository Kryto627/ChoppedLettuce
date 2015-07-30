package calemi.fusionwarfare.gui;

import net.minecraft.block.Block;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.inventory.Container;
import net.minecraft.util.ResourceLocation;
import calemi.fusionwarfare.FusionWarfare;
import calemi.fusionwarfare.Reference;
import calemi.fusionwarfare.gui.button.GuiFusionButton;
import calemi.fusionwarfare.inventory.ContainerEnergyTank;
import calemi.fusionwarfare.packet.ServerPacketHandler;
import calemi.fusionwarfare.tileentity.TileEntityBase;
import calemi.fusionwarfare.tileentity.TileEntityEnergyTransmitter;
import calemi.fusionwarfare.tileentity.machine.TileEntityEXPFabricator;

public class GuiEXPFabricator extends GuiContainerBase {

	private GuiFusionButton button1;
	private GuiFusionButton button2;
	private GuiFusionButton button3;
	
	private GuiFusionButton[] buttons = new GuiFusionButton[]{button1, button2, button3};
	
	public GuiEXPFabricator(EntityPlayer player, TileEntityBase tileEntity) {
		super(new ContainerEnergyTank(player, tileEntity), player, tileEntity);
	}
	
	@Override
	public void initGui() {		
		super.initGui();
		
		for (int i = 0; i < buttons.length; i++) {
			buttons[i] = new GuiFusionButton(i, getScreenX() + 20, (getScreenY() + 21) + (i * 21), 64, "Add " + (30 + (i * 30)) + " EXP");
			buttonList.add(buttons[i]);
		}
	}
	
	@Override
	protected void actionPerformed(GuiButton button) {		
		super.actionPerformed(button);
		
		for (int i = 0; i < buttons.length; i++) {
			
			if (button.id == i) {
				FusionWarfare.network.sendToServer(new ServerPacketHandler("addEXP%" + (30 + (i * 30)) + "%" + tileEntity.xCoord + "%" + tileEntity.yCoord + "%" + tileEntity.zCoord));
			}			
		}		
	}
	
	@Override
	public int getScreenYOffset() {
		return 15;
	}
	
	@Override
	public String getGuiTextures() {
		return "exp_fabricator";
	}

	@Override
	public String getGuiTitle() {
		return "EXP Fabricator";
	}

	@Override
	public void drawGuiBackground(int mouseX, int mouseY) {
		drawLargeFuelBar(99, 80);
	}

	@Override
	public void drawGuiForeground(int mouseX, int mouseY) {
		drawLargeFuelBarTextBox(99, 80, mouseX, mouseY);
	}
}