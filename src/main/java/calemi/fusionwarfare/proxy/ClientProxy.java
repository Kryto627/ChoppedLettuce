package calemi.fusionwarfare.proxy;

import calemi.fusionwarfare.FusionWarfare;
import calemi.fusionwarfare.entity.EntityBlock;
import calemi.fusionwarfare.entity.EntityFallingSupplyCrate;
import calemi.fusionwarfare.entity.EntityFusionBullet;
import calemi.fusionwarfare.entity.EntityMissile;
import calemi.fusionwarfare.event.FOVEvent;
import calemi.fusionwarfare.event.FusionGunRenderEvent;
import calemi.fusionwarfare.init.InitBlocks;
import calemi.fusionwarfare.init.InitItems;
import calemi.fusionwarfare.key.KeyBindings;
import calemi.fusionwarfare.renderer.RenderBeacon;
import calemi.fusionwarfare.renderer.RenderBlock;
import calemi.fusionwarfare.renderer.RenderFallingSupplyCrate;
import calemi.fusionwarfare.renderer.RenderFloatingItem;
import calemi.fusionwarfare.renderer.RenderFusionBullet;
import calemi.fusionwarfare.renderer.RenderMissile;
import calemi.fusionwarfare.renderer.RenderMissileLauncher;
import calemi.fusionwarfare.renderer.RenderNetworkCable;
import calemi.fusionwarfare.renderer.RenderTurbine;
import calemi.fusionwarfare.renderer.item.ItemRenderFusionAutoPistol;
import calemi.fusionwarfare.renderer.item.ItemRenderFusionGatlingGun;
import calemi.fusionwarfare.renderer.item.ItemRenderFusionMatterDeconstructor;
import calemi.fusionwarfare.renderer.item.ItemRenderFusionPistol;
import calemi.fusionwarfare.renderer.item.ItemRenderFusionSMG;
import calemi.fusionwarfare.renderer.item.ItemRenderFusionShotgun;
import calemi.fusionwarfare.renderer.item.ItemRenderFusionSniperRifle;
import calemi.fusionwarfare.renderer.item.ItemRenderMissileLauncher;
import calemi.fusionwarfare.renderer.item.ItemRenderNetworkCable;
import calemi.fusionwarfare.tileentity.gen.TileEntityWindTurbine;
import calemi.fusionwarfare.tileentity.machine.TileEntityAntiMobBeacon;
import calemi.fusionwarfare.tileentity.machine.TileEntityEXPFabricator;
import calemi.fusionwarfare.tileentity.machine.TileEntityFusionMatterReinforcer;
import calemi.fusionwarfare.tileentity.machine.TileEntityMissileLauncher;
import calemi.fusionwarfare.tileentity.machine.TileEntityPlayerHealingBeacon;
import calemi.fusionwarfare.tileentity.machine.TileEntityTwoInputs;
import calemi.fusionwarfare.tileentity.network.TileEntityNetworkCable;
import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.client.registry.RenderingRegistry;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.MinecraftForgeClient;
import net.minecraftforge.common.MinecraftForge;

public class ClientProxy extends CommonProxy {

	@Override
	public void registerRenders() {
		
		ClientRegistry.registerKeyBinding(KeyBindings.recipeButton);
		
		MinecraftForge.EVENT_BUS.register(new FusionGunRenderEvent());
		MinecraftForge.EVENT_BUS.register(new FOVEvent());
		
		RenderingRegistry.registerEntityRenderingHandler(EntityFusionBullet.class, new RenderFusionBullet());			
		RenderingRegistry.registerEntityRenderingHandler(EntityMissile.class, new RenderMissile());	
		RenderingRegistry.registerEntityRenderingHandler(EntityBlock.class, new RenderBlock());	
		RenderingRegistry.registerEntityRenderingHandler(EntityFallingSupplyCrate.class, new RenderFallingSupplyCrate());
		
		MinecraftForgeClient.registerItemRenderer(InitItems.fusion_pistol, new ItemRenderFusionPistol());
		MinecraftForgeClient.registerItemRenderer(InitItems.fusion_auto_pistol, new ItemRenderFusionAutoPistol());
		MinecraftForgeClient.registerItemRenderer(InitItems.fusion_shotgun, new ItemRenderFusionShotgun());
		MinecraftForgeClient.registerItemRenderer(InitItems.fusion_smg, new ItemRenderFusionSMG());
		MinecraftForgeClient.registerItemRenderer(InitItems.fusion_sniper_rifle, new ItemRenderFusionSniperRifle());		
		MinecraftForgeClient.registerItemRenderer(InitItems.fusion_gatling_gun, new ItemRenderFusionGatlingGun());
		
		MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(InitBlocks.network_cable), new ItemRenderNetworkCable(new RenderNetworkCable(), new TileEntityNetworkCable()));
		MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(InitBlocks.missile_launcher), new ItemRenderMissileLauncher());
		MinecraftForgeClient.registerItemRenderer(InitItems.fusion_matter_deconstructor, new ItemRenderFusionMatterDeconstructor());
		
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityNetworkCable.class, new RenderNetworkCable());
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityMissileLauncher.class, new RenderMissileLauncher());
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityFusionMatterReinforcer.class, new RenderBeacon("fusion_matter_reinforcer"));
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityAntiMobBeacon.class, new RenderBeacon("anti_mob_beacon"));
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityPlayerHealingBeacon.class, new RenderBeacon("player_healing_beacon"));
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityWindTurbine.class, new RenderTurbine());
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityEXPFabricator.class, new RenderFloatingItem(new ItemStack(Items.experience_bottle), 0.5F, 1.1F, 0.5F, 1F));
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityTwoInputs.class, new RenderFloatingItem(new ItemStack(InitItems.velocity_missile_T1), 0.5F, 1.1F, 0.5F, 0.7F));
		
		FusionWarfare.armorIDSteel = addArmor("steel");
		FusionWarfare.armorIDInfusedSteel = addArmor("infused_steel");
		FusionWarfare.armorIDInfusedSteelRed = addArmor("infused_steel_red");
	}
	
	public int addArmor(String armor) {
		return RenderingRegistry.addNewArmourRendererPrefix(armor);				
	}
}