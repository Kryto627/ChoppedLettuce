package calemi.fusionwarfare.init;

import net.minecraft.item.Item;
import calemi.fusionwarfare.FusionWarfare;
import calemi.fusionwarfare.item.ItemBase;
import calemi.fusionwarfare.item.ItemBattery;
import calemi.fusionwarfare.item.ItemChargedSeeds;
import calemi.fusionwarfare.item.ItemCreativeBattery;
import calemi.fusionwarfare.item.ItemFusionGatlingGun;
import calemi.fusionwarfare.item.ItemFusionGun;
import calemi.fusionwarfare.item.ItemMissile;
import calemi.fusionwarfare.item.ItemMissileModule;
import calemi.fusionwarfare.item.ItemTest;
import calemi.fusionwarfare.item.tool.ItemDebugger;
import calemi.fusionwarfare.item.tool.ItemFusionMatterDeconstructor;
import calemi.fusionwarfare.item.tool.ItemLocationLinker;
import calemi.fusionwarfare.item.tool.ItemSprayer;
import calemi.fusionwarfare.util.ToolSet;
import calemi.fusionwarfare.util.missile.MissileTypeRegistry;

public class InitItems {

	//#-#-#-#-#-RESOURCES-#-#-#-#-#\\
	
	public static Item infused_crystal;	
	public static Item infused_catalyst;
	public static Item advanced_infused_catalyst;			
	public static Item infused_azurite;
	public static Item fusion_fish;	
	public static Item charged_seeds;
	
	//#-#-#-#-#-INGOTS-#-#-#-#-#\\
	
	public static Item steel_ingot;
	public static Item infused_steel_ingot;
	public static Item infused_redstone;
	
	//#-#-#-#-#-INGREDIENTS-#-#-#-#-#\\
	
	public static Item steel_mixture;
	public static Item evaporation_cell;	
	public static Item steel_plate;	
	public static Item lightning_rod;
	public static Item sturdy_handle;
	public static Item solar_panel;
	
	public static Item missile_module_1;
	public static Item missile_module_2;
	public static Item missile_module_3;
	
	public static Item basic_chip;
	public static Item advanced_chip;
	public static Item hyper_chip;
	
	public static Item gun_core;
	public static Item gun_barrel;
	public static Item gun_handle;	
	
	//#-#-#-#-#-BATTERIES-#-#-#-#-#\\
	
	public static Item battery;
	public static Item creative_battery;
	
	//#-#-#-#-#-GUNS-#-#-#-#-#\\
	
	public static Item fusion_ammo;

	public static Item fusion_pistol;
	public static Item fusion_auto_pistol;
	public static Item fusion_shotgun;
	public static Item fusion_smg;
	public static Item fusion_sniper_rifle;
	public static Item fusion_grenade_launcher;
	public static Item fusion_gatling_gun;
	
	//#-#-#-#-#-MISSILES-#-#-#-#-#\\
	
	public static Item velocity_missile_T1;
	public static Item velocity_missile_T2;
	public static Item velocity_missile_T3;
	
	public static Item breaching_missile_T1;
	public static Item breaching_missile_T2;
	public static Item breaching_missile_T3;
	
	public static Item emp_missile_T1;
	public static Item emp_missile_T2;
	public static Item emp_missile_T3;
	
	public static Item pyroblast_missile;
	public static Item dudley_missile;
	
	//#-#-#-#-#-TOOLS-#-#-#-#-#\\
	
	public static ToolSet steel, infused_steel, infused_steel_red;
	
	public static Item fusion_matter_deconstructor;
	
	public static Item debugger;
	public static Item location_linker;
	public static Item sprayer;
		
	//#-#-#-#-#-TEAMS-#-#-#-#-#\\
	
	public static Item team_card;	
	
	//#-#-#-#-#-MISC-#-#-#-#-#\\
	
	public static Item overclocking_chip;
	public static Item test;
	
	public static void init() {

		//#-#-#-#-#-RESOURCES-#-#-#-#-#\\
		
		infused_crystal = new ItemBase("infused_crystal");
		infused_catalyst = new ItemBase("infused_catalyst");
		advanced_infused_catalyst = new ItemBase("advanced_infused_catalyst", true, true).setMaxDamage(1000).setMaxStackSize(1);	
		infused_azurite = new ItemBase("infused_azurite");
		fusion_fish = new ItemBase("fusion_fish");		
		charged_seeds = new ItemChargedSeeds();
		
		//#-#-#-#-#-INGOTS-#-#-#-#-#\\
				
		steel_ingot = new ItemBase("steel_ingot");		
		infused_steel_ingot = new ItemBase("infused_steel_ingot");
		infused_redstone = new ItemBase("infused_redstone");
		
		//#-#-#-#-#-INGREDIANTS-#-#-#-#-#\\
		
		steel_mixture = new ItemBase("steel_mixture");
		evaporation_cell = new ItemBase("evaporation_cell");
		steel_plate = new ItemBase("steel_plate");		
		lightning_rod = new ItemBase("lightning_rod");
		sturdy_handle = new ItemBase("sturdy_handle");
		solar_panel = new ItemBase("solar_panel");
		
		missile_module_1 = new ItemMissileModule(1);
		missile_module_2 = new ItemMissileModule(2);
		missile_module_3 = new ItemMissileModule(3);
		
		basic_chip = new ItemBase("basic_chip");
		advanced_chip = new ItemBase("advanced_chip");
		hyper_chip = new ItemBase("hyper_chip");
		
		gun_core = new ItemBase("gun_core");
		gun_barrel = new ItemBase("gun_barrel");
		gun_handle = new ItemBase("gun_handle");
		
		//#-#-#-#-#-BATTERIES-#-#-#-#-#\\
			
		battery = new ItemBattery();
		creative_battery = new ItemCreativeBattery();	
		
		//#-#-#-#-#-GUNS-#-#-#-#-#\\
		
		fusion_ammo = new ItemBase("fusion_ammo").setCreativeTab(InitCreativeTabs.creativeTabInfantry);

		fusion_pistol = new ItemFusionGun("fusion_pistol", 10, 1, 12, 1, 0.08F, false);
		fusion_auto_pistol = new ItemFusionGun("fusion_auto_pistol", 0, 1, 10, 3, 0.08F, false);
		fusion_shotgun = new ItemFusionGun("fusion_shotgun", 30, 6, 25, 10, 0.1F, false);
		fusion_smg = new ItemFusionGun("fusion_smg", 0, 1, 10, 2, 0.04F, false);
		fusion_sniper_rifle = new ItemFusionGun("fusion_sniper_rifle", 100, 1, 80, 0, 0.002F, true);	
		fusion_gatling_gun = new ItemFusionGatlingGun("fusion_gatling_gun", 1, 2, 2, 0.04F);
		
		//#-#-#-#-#-MISSILES-#-#-#-#-#\\
		
		velocity_missile_T1 = new ItemMissile(MissileTypeRegistry.velocity_1);
		velocity_missile_T2 = new ItemMissile(MissileTypeRegistry.velocity_2);
		velocity_missile_T3 = new ItemMissile(MissileTypeRegistry.velocity_3);
		
		breaching_missile_T1 = new ItemMissile(MissileTypeRegistry.breaching_1);
		breaching_missile_T2 = new ItemMissile(MissileTypeRegistry.breaching_2);
		breaching_missile_T3 = new ItemMissile(MissileTypeRegistry.breaching_3);
		
		emp_missile_T1 = new ItemMissile(MissileTypeRegistry.emp_1);
		emp_missile_T2 = new ItemMissile(MissileTypeRegistry.emp_2);
		emp_missile_T3 = new ItemMissile(MissileTypeRegistry.emp_3);
		
		pyroblast_missile = new ItemMissile(MissileTypeRegistry.pyroblast);
		dudley_missile = new ItemMissile(MissileTypeRegistry.dudley);
		
		//#-#-#-#-#-TOOLS-#-#-#-#-#\\		
		
		steel = new ToolSet("steel", InitToolMaterials.toolMaterialSteel, InitArmorMaterials.toolMaterialSteel, FusionWarfare.armorIDSteel, steel_ingot, true);
		infused_steel = new ToolSet("infused_steel", InitToolMaterials.toolMaterialInfusedSteel, InitArmorMaterials.toolMaterialInfusedSteel, FusionWarfare.armorIDInfusedSteel, infused_steel_ingot, true);
		infused_steel_red = new ToolSet("infused_steel_red", InitToolMaterials.toolMaterialInfusedSteel, InitArmorMaterials.toolMaterialInfusedSteel, FusionWarfare.armorIDInfusedSteel, infused_steel_ingot, false);
		
		fusion_matter_deconstructor = new ItemFusionMatterDeconstructor();
		
		debugger = new ItemDebugger();
		location_linker = new ItemLocationLinker();
		sprayer = new ItemSprayer();
		
		//#-#-#-#-#-TEAMS-#-#-#-#-#\\
		
		//team_card = new ItemTeamCard();
		
		//#-#-#-#-#-MISC-#-#-#-#-#\\
		
		overclocking_chip = new ItemBase("overclocking_chip").setMaxStackSize(15);
		test = new ItemTest();		
	}
}