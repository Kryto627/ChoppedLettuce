package calemi.fusionwarfare.tileentity.gen;

import calemi.fusionwarfare.tileentity.EnumIO;
import calemi.fusionwarfare.tileentity.TileEntityBase;
import calemi.fusionwarfare.util.EnergyUtil;
import net.minecraft.item.ItemStack;
import net.minecraft.world.EnumSkyBlock;
import net.minecraft.world.biome.BiomeGenOcean;

public class TileEntityWindTurbine extends TileEntityBase {

	public float speed, rotation;

	public TileEntityWindTurbine() {
		maxEnergy = 500;
	}

	@Override
	public void updateEntity() {
		super.updateEntity();

		rotation += speed;

		if (yCoord >= 75 && worldObj.canBlockSeeTheSky(xCoord, yCoord, zCoord)) {

			speed = 5F;

			if (worldObj.getTotalWorldTime() % 40L == 0L) {

				if (!worldObj.isRemote)
					EnergyUtil.addEnergy(this, 15);

				if (worldObj.getBiomeGenForCoords(xCoord, zCoord) instanceof BiomeGenOcean) {
					speed = 8F;
					if (!worldObj.isRemote)
					EnergyUtil.addEnergy(this, 5);
				}
			}
		}

		else {
			speed = 0;

		}
	}

	@Override
	public int[] getAccessibleSlotsFromSide(int p_94128_1_) {
		return null;
	}

	@Override
	public boolean canInsertItem(int p_102007_1_, ItemStack p_102007_2_, int p_102007_3_) {
		return false;
	}

	@Override
	public boolean canExtractItem(int p_102008_1_, ItemStack p_102008_2_, int p_102008_3_) {
		return false;
	}

	@Override
	public int getSizeInventory() {
		return 0;
	}

	@Override
	public boolean isItemValidForSlot(int p_94041_1_, ItemStack p_94041_2_) {
		return false;
	}

	@Override
	public EnumIO getIOType() {
		return EnumIO.OUTPUT;
	}
}
