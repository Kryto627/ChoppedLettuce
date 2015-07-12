package calemi.fusionwarfare.item.tool;

import java.util.List;

import org.lwjgl.input.Keyboard;

import calemi.fusionwarfare.init.InitCreativeTabs;
import calemi.fusionwarfare.item.ItemBase;
import calemi.fusionwarfare.tileentity.TileEntityEnergyReceiver;
import calemi.fusionwarfare.tileentity.TileEntityEnergyTransmitter;
import calemi.fusionwarfare.tileentity.machine.TileEntityMissileLauncher;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.world.World;

public class ItemLocationLinker extends ItemBase {

	public ItemLocationLinker() {
		super("location_linker");
		setCreativeTab(InitCreativeTabs.creativeTabTools);
		setMaxStackSize(1);
	}
	
	public NBTTagCompound getNBT(ItemStack is) {
		
		if (is.getTagCompound() == null) {
			is.setTagCompound(new NBTTagCompound());
		}

		return is.getTagCompound();
	}
	
	@Override
	public void addInformation(ItemStack is, EntityPlayer player, List list, boolean b) {

		if (Keyboard.isKeyDown(Keyboard.KEY_LSHIFT) || Keyboard.isKeyDown(Keyboard.KEY_RSHIFT)) {
			
			list.add(EnumChatFormatting.GOLD + "Block Location");
			list.add(EnumChatFormatting.GOLD + "X: " + EnumChatFormatting.AQUA + getNBT(is).getInteger("X"));
			list.add(EnumChatFormatting.GOLD + "Y: " + EnumChatFormatting.AQUA + getNBT(is).getInteger("Y"));		
			list.add(EnumChatFormatting.GOLD + "Z: " + EnumChatFormatting.AQUA + getNBT(is).getInteger("Z"));
			list.add("");
			list.add("Binds locations to");
			list.add("Missile Launcher and Energy Transmitter.");
			list.add("Sneak Right-click to send coords to machine.");
			list.add("Right-click to link a location.");
			
		} else {
			list.add("Press " + EnumChatFormatting.GOLD + "SHIFT" + EnumChatFormatting.RESET + EnumChatFormatting.GRAY + " for more info");
		}
	}
	
	@Override
	public boolean onItemUse(ItemStack is, EntityPlayer player, World world, int x, int y, int z, int i, float f1, float f2, float f3) {
		
		if (!world.isRemote) {
						
			if (player.isSneaking()) {
				
				if (world.getTileEntity(x, y, z) instanceof TileEntityMissileLauncher) {
					
					((TileEntityMissileLauncher)world.getTileEntity(x, y, z)).targetX = getNBT(is).getInteger("X");
					((TileEntityMissileLauncher)world.getTileEntity(x, y, z)).targetZ = getNBT(is).getInteger("Z");
					((TileEntityMissileLauncher)world.getTileEntity(x, y, z)).update();
					player.worldObj.playSoundAtEntity(player, "random.orb", 1F, 1F);	
					return true;
				}
				
				if (world.getTileEntity(x, y, z) instanceof TileEntityEnergyTransmitter) {
					
					((TileEntityEnergyTransmitter)world.getTileEntity(x, y, z)).targetX = getNBT(is).getInteger("X");
					((TileEntityEnergyTransmitter)world.getTileEntity(x, y, z)).targetY = getNBT(is).getInteger("Y");
					((TileEntityEnergyTransmitter)world.getTileEntity(x, y, z)).targetZ = getNBT(is).getInteger("Z");
					((TileEntityEnergyTransmitter)world.getTileEntity(x, y, z)).update();
					player.worldObj.playSoundAtEntity(player, "random.orb", 1F, 1F);	
					return true;
				}
			}
			
			getNBT(is).setInteger("X", x);	
			getNBT(is).setInteger("Y", y);
			getNBT(is).setInteger("Z", z);
			player.worldObj.playSoundAtEntity(player, "random.orb", 1F, 1F);
			return true;					
		}	
		
		return false;
	}	
}