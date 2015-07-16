package calemi.fusionwarfare.entity;

import io.netty.buffer.ByteBuf;
import net.minecraft.block.Block;
import net.minecraft.block.BlockLiquid;
import net.minecraft.entity.Entity;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

import java.util.Random;

import calemi.fusionwarfare.block.BlockSupplyCrate;
import calemi.fusionwarfare.init.InitBlocks;
import calemi.fusionwarfare.item.ItemMissile;
import calemi.fusionwarfare.loot.CrateLootRegistry;
import calemi.fusionwarfare.loot.EnumCrateRarity;
import calemi.fusionwarfare.tileentity.TileEntitySupplyCrate;
import cpw.mods.fml.common.network.ByteBufUtils;
import cpw.mods.fml.common.registry.IEntityAdditionalSpawnData;

public class EntityFallingSupplyCrate extends Entity {

	public int meta;
	
	public EntityFallingSupplyCrate(World world) {
		super(world);
	}

	public EntityFallingSupplyCrate(int meta, World world, int x, int z) {
		super(world);
		this.meta = meta;
		this.posX = x - 1;
		this.posY = 256;
		this.posZ = z - 1;
		motionY -= 0.1F;
	}
	
	@Override
	public void onUpdate() {
		super.onUpdate();
		
		posX += motionX;
		posY += motionY;
		posZ += motionZ;
					
		if (!worldObj.isRemote) {
			
			if (posY < 0) {
				setDead();
			}
		
			
			if (!worldObj.isAirBlock((int)posX, (int)posY, (int)posZ)) {
				
				setDead();
				checkForAir((int)posX, (int)posY, (int)posZ);
			}
		}
	}
	
	private void checkForAir(int x, int y, int z) {
		
		if (y >= 256) {
			return;
		}
		
		else if (worldObj.isAirBlock(x, y, z) || worldObj.getBlock(x, y, z).isReplaceable(worldObj, x, y, z)) {		
			
			worldObj.setBlock(x, y, z, InitBlocks.supply_crate);
			worldObj.setBlockMetadataWithNotify(x, y, z, meta, 2);
			
			TileEntitySupplyCrate tileEntity = (TileEntitySupplyCrate)worldObj.getTileEntity(x, y, z);
			
			EnumCrateRarity rarity = EnumCrateRarity.BLUE;
			
			if (meta == 1) rarity = EnumCrateRarity.ORANGE;
			if (meta == 2) rarity = EnumCrateRarity.RED;
			
			ItemStack[] stacks = CrateLootRegistry.genLoot(12, rarity);
			
			for (ItemStack stack : stacks) {
				
				int slot = findRandomSlot(tileEntity);
				
				tileEntity.setInventorySlotContents(slot, stack);
			}
		}
		
		else {
			checkForAir(x, y + 1, z);
		}
	}

	private int findRandomSlot(IInventory inv) {

		int slot = MathHelper.getRandomIntegerInRange(new Random(), 0, inv.getSizeInventory() - 1);	
		
		if (inv.getStackInSlot(slot) != null) {
			
			slot = findRandomSlot(inv);			
		}
		
		return slot;
	}

	@Override
	protected void entityInit() {
		
	}

	@Override
	protected void readEntityFromNBT(NBTTagCompound nbt) {
		
	}

	@Override
	protected void writeEntityToNBT(NBTTagCompound nbt) {
		
	}	
}
