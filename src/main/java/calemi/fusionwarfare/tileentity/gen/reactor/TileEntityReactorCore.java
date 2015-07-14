package calemi.fusionwarfare.tileentity.gen.reactor;

import java.util.Random;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.world.biome.BiomeGenOcean;
import calemi.fusionwarfare.init.InitBlocks;
import calemi.fusionwarfare.init.InitItems;
import calemi.fusionwarfare.tileentity.EnumIO;
import calemi.fusionwarfare.tileentity.TileEntityBase;
import calemi.fusionwarfare.util.EnergyUtil;
import calemi.fusionwarfare.util.Location;
import calemi.fusionwarfare.util.ShapeUtil;

public class TileEntityReactorCore extends TileEntityBase {

	private boolean isAssembled;

	public TileEntityReactorCore() {
		maxEnergy = 25000;
	}

	@Override
	public void updateEntity() {

		Random rand = new Random();

		int r = 2;

		if (!worldObj.isRemote) {

			if (worldObj.getTotalWorldTime() % 20L == 0L) {
				
				isAssembled = isCorrectStructure();

				if (isAssembled && hasEnoughWater()) {

					for (Location l : ShapeUtil.getCube(worldObj, xCoord, yCoord, zCoord, 1, 1, 1)) {

						if (l.getBlock() == Blocks.air) {
							l.setBlock(Blocks.water, 0);
						}
					}
				}

				for (Location l : ShapeUtil.getCube(worldObj, xCoord, yCoord, zCoord, r, r, r)) {

					if (l.getTileEntity() instanceof TileEntitySteelCasing) {

						TileEntitySteelCasing tileEntity = (TileEntitySteelCasing) l.getTileEntity();

						if (isAssembled) {
							tileEntity.x = xCoord;
							tileEntity.y = yCoord;
							tileEntity.z = zCoord;
						}

						else {
							tileEntity.x = 0;
							tileEntity.y = 0;
							tileEntity.z = 0;
						}
					}
				}
			}

			if (isAssembled && worldObj.getBiomeGenForCoords(xCoord, zCoord) instanceof BiomeGenOcean && hasEnoughWater()) {

				if (slots[0] != null && slots[0].getItem() == InitItems.advanced_infused_catalyst) {

					if (slots[0].getItemDamage() >= slots[0].getMaxDamage()) {
						decrStackSize(0, 1);
					}
					
					if (EnergyUtil.canAddEnergy(this, 25)) {

						slots[0].setItemDamage(slots[0].getItemDamage() + 1);
						EnergyUtil.addEnergy(this, 25);
					}
				}
			}
		}
	}

	private boolean hasEnoughWater() {

		for (int y = yCoord + 4; y < (yCoord + 4) + 20; y++) {

			Location loc = new Location(worldObj, xCoord, y, zCoord);

			if (loc.getBlock() != Blocks.water || loc.getBlockMetadata() > 0) {
				return false;
			}
		}

		return true;
	}

	private boolean isCorrectStructure() {

		int r = 2;

		int top = yCoord + r;
		int bottom = yCoord - r;
		int minX = xCoord - r;
		int maxX = xCoord + r;
		int minZ = zCoord - r;
		int maxZ = zCoord + r;

		Location coolingUnit = new Location(worldObj, xCoord, yCoord + 3, zCoord);

		if (!coolingUnit.compareBlocks(InitBlocks.reactor_cooling_unit)) {
			return false;
		}

		for (Location l : ShapeUtil.getCube(worldObj, xCoord, yCoord, zCoord, r, r, r)) {

			if (l.y == top || l.y == bottom) {

				if (l.getBlock() != InitBlocks.steel_casing) {
					return false;
				}
			}

			else if ((l.x == minX || l.x == maxX) && (l.z == minZ || l.z == maxZ)) {

				if (l.getBlock() != InitBlocks.steel_casing) {
					return false;
				}
			}

			else if ((l.x == minX || l.x == maxX) || (l.z == minZ || l.z == maxZ)) {

				if (l.getBlock() != InitBlocks.reinforced_glass) {
					return false;
				}
			}
		}

		return true;
	}

	@Override
	public EnumIO getIOType() {
		return EnumIO.OUTPUT;
	}

	@Override
	public int getSizeInventory() {
		return 1;
	}

	@Override
	public int[] getAccessibleSlotsFromSide(int side) {
		return new int[] {};
	}

	@Override
	public boolean canInsertItem(int slot, ItemStack is, int side) {
		return false;
	}

	@Override
	public boolean canExtractItem(int slot, ItemStack is, int side) {
		return false;
	}

	@Override
	public boolean isItemValidForSlot(int slot, ItemStack is) {
		return false;
	}
}
