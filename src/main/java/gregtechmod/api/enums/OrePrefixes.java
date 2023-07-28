package gregtechmod.api.enums;

import gregtechmod.api.GregTech_API;
import gregtechmod.api.interfaces.IOreRecipeRegistrator;
import gregtechmod.api.util.GT_ModHandler;
import gregtechmod.api.util.GT_OreDictUnificator;
import gregtechmod.api.util.GT_Utility;
import gregtechmod.api.util.OreDictEntry;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.item.ItemStack;

public enum OrePrefixes {
	@Deprecated pulp	(false, false, false, false, false, -1),
	@Deprecated leaves	(false, false, false, false, false, -1),
	@Deprecated sapling	(false, false, false, false, false, -1),
	@Deprecated itemDust(false, false, false, false, false, -1),
	
	oreNether			(true, true,  false,  false, false, -1), // Prefix of the Nether-Ores Mod. Causes Ores to double. Ore -> Material is a Oneway Operation!
	oreDense			(true, true,  false,  false, false, -1), // Prefix of the Dense-Ores Mod. Causes Ores to double. Ore -> Material is a Oneway Operation!
	oreEnd				(true, true,  false,  false, false, -1), // In case of an End-Ores Mod. Ore -> Material is a Oneway Operation!
	@Deprecated oreGem	(false, false, false, false, false, -1),
	ore					(true, true,  false,  false, false, -1), // Regular Ore Prefix. Ore -> Material is a Oneway Operation! Introduced by Eloraam
	crushedCentrifuged	(true, true,  false,  false, false, -1),
	crushedPurified		(true, true,  false,  false, false, -1),
	crushed				(true, true,  false,  false, false, -1),
	ingotQuintuple		(true, true,  false,  false, false, GregTech_API.MATERIAL_UNIT *		 5), // A quintuple Ingot.
	ingotQuadruple		(true, true,  false,  false, false, GregTech_API.MATERIAL_UNIT *		 4), // A quadruple Ingot.
	@Deprecated ingotQuad(false, false, false, false, false, -1),
	ingotTriple			(true, true,  false,  false, false, GregTech_API.MATERIAL_UNIT *		 3), // A triple Ingot.
	ingotDouble			(true, true,  false,  false, false, GregTech_API.MATERIAL_UNIT *		 2), // A double Ingot. Similar to the double Ingots of TerrafirmaCraft.
	ingotHot			(true, true,  false,  false, false, GregTech_API.MATERIAL_UNIT *		 1), // A hot Ingot, which has to be cooled down by a Vacuum Freezer.
	ingot				(true, true,  false,  false, false, GregTech_API.MATERIAL_UNIT *		 1), // A regular Ingot. Introduced by Eloraam
	gem					(true, true,  false,  false, false, GregTech_API.MATERIAL_UNIT *		 1), // A regular Gem or crystallized Metal worth one Dust. Introduced by Eloraam
	@Deprecated dustDirty(false, false, false, false, false, -1),
	dustTiny			(true, true,  false,  false, false, GregTech_API.MATERIAL_UNIT /		 9), // 1/9th of a Dust.
	dustSmall			(true, true,  false,  false, false, GregTech_API.MATERIAL_UNIT /		 4), // 1/4th of a Dust.
	dustImpure			(true, true,  false,  false, false, GregTech_API.MATERIAL_UNIT *		 1), // Dust with impurities. 1 Unit of Main Material and 1/9 - 1/4 Unit of secondary Material
	dustRefined			(true, true,  false,  false, false, GregTech_API.MATERIAL_UNIT *		 1),
	dustPure			(true, true,  false,  false, false, GregTech_API.MATERIAL_UNIT *		 1), // Dust without impurities.
	dust				(true, true,  false,  false, false, GregTech_API.MATERIAL_UNIT *		 1), // Pure Dust worth of one Ingot or Gem. Introduced by Alblaka.
	nugget				(true, true,  false,  false, false, GregTech_API.MATERIAL_UNIT /		 9), // A Nugget. Introduced by Eloraam
	plateAlloy			(true, false,  false, false, false, -1), // Special Alloys have this prefix.
	plateDense			(true, true,  false,  false, false, GregTech_API.MATERIAL_UNIT *		 9), // 9 Plates combined in one Item.
	plateQuintuple		(true, true,  false,  false, false, GregTech_API.MATERIAL_UNIT *		 5), // Regular Plate made of one Ingot/Dust. Introduced by Calclavia
	plateQuadruple		(true, true,  false,  false, false, GregTech_API.MATERIAL_UNIT *		 4), // Regular Plate made of one Ingot/Dust. Introduced by Calclavia
	@Deprecated plateQuad(false, false, false, false, false, -1),
	plateTriple			(true, true,  false,  false, false, GregTech_API.MATERIAL_UNIT *		 3), // Regular Plate made of one Ingot/Dust. Introduced by Calclavia
	plateDouble			(true, true,  false,  false, false, GregTech_API.MATERIAL_UNIT *		 2), // Regular Plate made of one Ingot/Dust. Introduced by Calclavia
	plate				(true, true,  false,  false, false, GregTech_API.MATERIAL_UNIT *		 1), // Regular Plate made of one Ingot/Dust. Introduced by Calclavia
	block				(true, true,  false,  false, false, GregTech_API.MATERIAL_UNIT *		 9), // Storage Block consisting out of 9 Ingots/Gems/Dusts. Introduced by CovertJaguar
	stick				(true, true,  false,  false, false, GregTech_API.MATERIAL_UNIT /		 2), // Stick made of half an Ingot. Introduced by Eloraam
	lense				(true, true,  false,  false, false, (GregTech_API.MATERIAL_UNIT * 3)   / 4), // 3/4 of a Plate or Gem used to shape a Lense. Normally only used on Transparent Materials.
	round				(true, true,  false,  false, false, GregTech_API.MATERIAL_UNIT /		 9), // consisting out of one Nugget.
	bolt				(true, true,  false,  false, false, GregTech_API.MATERIAL_UNIT /		 8), // consisting out of 1/8 Ingot or 1/4 Stick.
	screw				(true, true,  false,  false, false, GregTech_API.MATERIAL_UNIT /		 8), // consisting out of a Bolt.
	ring				(true, true,  false,  false, false, GregTech_API.MATERIAL_UNIT /		 4), // consisting out of 1/2 Stick.
	cellPlasma			(true, true,  true,  true,  false,  GregTech_API.MATERIAL_UNIT *		 1), // Hot Cell full of Plasma, which can be used in the Plasma Generator.
	cell				(true, true,  true,  true,  false,  GregTech_API.MATERIAL_UNIT *		 1), // Regular Gas/Fluid Cell. Introduced by Calclavia
	bucket				(true, true,  true,  true,  false,  GregTech_API.MATERIAL_UNIT *		 1), // A vanilla Iron Bucket filled with the Material.
	bottle				(true, true,  true,  true,  false,  -1), // Glas Bottle containing a Fluid.
	capsule				(false, true, true,  true,  false,  GregTech_API.MATERIAL_UNIT *		 1),
	crystal				(false, true, false,  false, false, GregTech_API.MATERIAL_UNIT *		 1),
	craftingTool		(false, false, false, false, false, -1), // Special Prefix used mainly for the Crafting Handler.
	crafting			(false, false, false, false, false, -1), // Special Prefix used mainly for the Crafting Handler.
	craft				(false, false, false, false, false, -1), // Special Prefix used mainly for the Crafting Handler.
	log					(false, false, false, false, false, -1), // Prefix used for Logs. Usually as "logWood". Introduced by Eloraam
	slab				(false, false, false, false, false, -1), // Prefix used for Slabs. Usually as "slabWood" or "slabStone". Introduced by SirSengir
	stair				(false, false, false, false, false, -1), // Prefix used for Stairs. Usually as "stairWood" or "stairStone". Introduced by SirSengir
	plank				(false, false, false, false, false, -1), // Prefix for Planks. Usually "plankWood". Introduced by Eloraam
	treeSapling			(false, false, true, false,  false, -1), // Prefix for Saplings.
	treeLeaves			(false, false, true, false,  false, -1), // Prefix for Leaves.
	tree				(false, false, false, false, false, -1), // Prefix for Tree Parts.
	stoneCobble			(false, false, true, false,  false, -1), // Cobblestone Prefix for all Cobblestones.
	stoneSmooth			(false, false, true, false,  false, -1), // Smoothstone Prefix.
	stoneMossyBricks	(false, false, true, false,  false, -1), // Mossy Stone Bricks.
	stoneMossy			(false, false, true, false,  false, -1), // Mossy Cobble.
	@Deprecated stoneBricksMossy(false, false, false, false, false, -1),
	stoneBricks			(false, false, true, false,  false, -1), // Stone Bricks.
	@Deprecated stoneBrick(false, false, false, false, false, -1),
	stoneCracked		(false, false, true, false,  false, -1), // Cracked Bricks.
	stoneChiseled		(false, false, true, false,  false, -1), // Chiseled Stone.
	stone				(false, true, true,  false,  false, -1), // Prefix to determine which kind of Rock this is.
	cobblestone			(false, true, true,  false,  false, -1),
	glass				(false, false, true, false,  false, -1),
	record				(false, false, true, false,  false, -1),
	rubble				(true, true,  true,  false,  false, -1),
	scraps				(true, true,  false,  false, false, -1),
	scrap				(false, false, false, false, false, -1),
	item				(false, false, false, false, false, -1), // Random Item. Introduced by Alblaka
	book				(false, false, false, false, false, -1), // Used for Books of any kind.
	paper				(false, false, false, false, false, -1), // Used for Papers of any kind.
	dye					(false, false, false, false, false, -1), // Used for the 16 dyes. Introduced by Eloraam
	armorHelmet			(false, true, false,  false, false, GregTech_API.MATERIAL_UNIT *		 5), // vanilly Helmet
	armorChestplate		(false, true, false,  false, false, GregTech_API.MATERIAL_UNIT *		 8), // vanilly Chestplate
	armorLeggins		(false, true, false,  false, false, GregTech_API.MATERIAL_UNIT *		 7), // vanilly Pants
	armorBoots			(false, true, false,  false, false, GregTech_API.MATERIAL_UNIT *		 4), // vanilly Boots
	armor				(false, false, false, false, false, -1),
	toolHeadSword		(true, true,  false,  false, false, GregTech_API.MATERIAL_UNIT *		 2), // consisting out of 2 Ingots.
	toolHeadPickaxe		(true, true,  false,  false, false, GregTech_API.MATERIAL_UNIT *		 3), // consisting out of 3 Ingots.
	toolHeadShovel		(true, true,  false,  false, false, GregTech_API.MATERIAL_UNIT *		 1), // consisting out of 1 Ingots.
	toolHeadAxe			(true, true,  false,  false, false, GregTech_API.MATERIAL_UNIT *		 3), // consisting out of 3 Ingots.
	toolHeadHoe			(true, true,  false,  false, false, GregTech_API.MATERIAL_UNIT *		 2), // consisting out of 2 Ingots.
	toolHeadFile		(true, true,  false,  false, false, GregTech_API.MATERIAL_UNIT *		 2), // consisting out of 2 Ingots.
	toolHeadHammer		(true, true,  false,  false, false, GregTech_API.MATERIAL_UNIT *		 6), // consisting out of 6 Ingots.
	toolHeadSaw			(true, true,  false,  false, false, GregTech_API.MATERIAL_UNIT *		 2), // consisting out of 2 Ingots.
	toolHeadScrewdriver	(true, true				,  false,  false, false, GregTech_API.MATERIAL_UNIT *		 1), // consisting out of 1 Ingots.
	toolSword			(false, true, false,  false, false, GregTech_API.MATERIAL_UNIT *		 2), // vanilly Sword
	toolPickaxe			(false, true, false,  false, false, GregTech_API.MATERIAL_UNIT *		 3), // vanilly Pickaxe
	toolShovel			(false, true, false,  false, false, GregTech_API.MATERIAL_UNIT *		 1), // vanilly Shovel
	toolAxe				(false, true, false,  false, false, GregTech_API.MATERIAL_UNIT *		 3), // vanilly Axe
	toolHoe				(false, true, false,  false, false, GregTech_API.MATERIAL_UNIT *		 2), // vanilly Hoe
	toolShears			(false, true, false,  false, false, GregTech_API.MATERIAL_UNIT *		 2), // vanilly Shears
	tool				(false, false, false, false, false, -1), // toolPot, toolSkillet, toolSaucepan, toolBakeware, toolCuttingboard, toolMortarandpestle, toolMixingbowl, toolJuicer
	pipeTiny			(true, true,  false,  false, true, GregTech_API.MATERIAL_UNIT /		 2),
	pipeSmall			(true, true,  false,  false, true, GregTech_API.MATERIAL_UNIT *		 1),
	pipeMedium			(true, true,  false,  false, true, GregTech_API.MATERIAL_UNIT *		 3),
	pipeLarge			(true, true,  false,  false, true, GregTech_API.MATERIAL_UNIT *		 6),
	pipeHuge			(true, true,  false,  false, true, GregTech_API.MATERIAL_UNIT *		12),
	pipe				(false, false, false, false, false, -1),
	gearGt				(true, true,  false,  false, false, GregTech_API.MATERIAL_UNIT *		 4), // Introduced by me because BuildCraft has ruined the gear Prefix...
	
	/* Electric Components.
	 * 
	 * usual Materials for this are:
	 * Primitive (Tier 1)
	 * Basic (Tier 2) as used by UE as well : IC2 Circuit and RE-Battery
	 * Good (Tier 3)
	 * Advanced (Tier 4) as used by UE as well : Advanced Circuit, Advanced Battery and Lithium Battery
	 * Data (Tier 5) : Data Storage Circuit
	 * Elite (Tier 6) as used by UE as well : Energy Crystal and Data Control Circuit
	 * Master (Tier 7) : Energy Flow Circuit and Lapotron Crystal
	 * Ultimate (Tier 8) : Data Orb and Lapotronic Energy Orb
	 * Infinite (Cheaty)
	 */
	batterySingleuse	(false, true, false,  false, false, -1),
	battery				(false, true, false,  false, false, -1), // Introduced by Calclavia
	circuitBoard		(true, true,  false,  false, false, -1), // Board needed for creating a Circuit of the same Tier
	circuitPart			(true, true,  false,  false, false, -1), // Part needed for creating a Circuit of the same Tier
	circuit				(true, true,  false,  false, false, -1), // Introduced by Calclavia
	computer			(true, true,  false,  false, true, -1), // A whole Computer. "computerMaster" = ComputerCube
	
	// random known prefixes without special abilities.
	cluster				(false, false, false, false, false, -1),
	grafter				(false, false, false, false, false, -1),
	scoop				(false, false, false, false, false, -1),
	frame				(false, false, false, false, false, -1),
	tome				(false, false, false, false, false, -1),
	junk				(false, false, false, false, false, -1),
	bee					(false, false, false, false, false, -1),
	rod					(false, false, false, false, false, -1),
	dirt				(false, false, false, false, false, -1),
	sand				(false, false, false, false, false, -1),
	grass				(false, false, false, false, false, -1),
	gravel				(false, false, false, false, false, -1),
	mushroom			(false, false, false, false, false, -1),
	wood				(false, false, false, false, false, -1), // Introduced by Eloraam
	drop				(false, false, false, false, false, -1),
	fuel				(false, false, false, false, false, -1),
	panel				(false, false, false, false, false, -1),
	brick				(false, false, false, false, false, -1),
	chunk				(false, false, false, false, false, -1),
	wire				(false, false, false, false, false, -1),
	seed				(false, false, false, false, false, -1),
	reed				(false, false, false, false, false, -1),
	sheet				(false, false, false, false, false, -1),
	crop				(false, false, false, false, false, -1),
	plant				(false, false, false, false, false, -1),
	coin				(false, false, false, false, false, -1),
	lumar				(false, false, false, false, false, -1),
	ground				(false, false, false, false, false, -1),
	cable				(false, false, false, false, false, -1),
	reduced				(false, false, false, false, false, -1),
	component			(false, false, false, false, false, -1),
	crystalline			(false, false, false, false, false, -1),
	cleanGravel			(false, false, false, false, false, -1),
	dirtyGravel			(false, false, false, false, false, -1),
	wax					(false, false, false, false, false, -1),
	wall				(false, false, false, false, false, -1),
	tube				(false, false, false, false, false, -1),
	list				(false, false, false, false, false, -1),
	food				(false, false, false, false, false, -1),
	gear				(false, false, false, false, false, -1), // Introduced by SirSengir
	coral				(false, false, false, false, false, -1),
	shard				(false, false, false, false, false, -1),
	clump				(false, false, false, false, false, -1),
	flower				(false, false, false, false, false, -1),
	storage				(false, false, false, false, false, -1),
	material			(false, false, false, false, false, -1),
	plasma				(false, false, false, false, false, -1),
	element				(false, false, false, false, false, -1),
	molecule			(false, false, false, false, false, -1),
	wafer				(false, false, false, false, false, -1),
	compressed			(false, false, false, false, false, -1),
	fertilizer			(false, false, false, false, false, -1),
	chest				(false, false, false, false, false, -1),
	raw					(false, false, false, false, false, -1),
	pane				(false, false, false, false, false, -1), // New forge prefix for panes
	chipset				(false, false, false, false, false, -1), // BuildCraft chipsets
	slimeball			(false, false, false, false, false, -1); // Introduced by MFR
	
	static {
		pulp.mPrefixInto = dust;
		oreGem.mPrefixInto = ore;
		leaves.mPrefixInto = treeLeaves;
		sapling.mPrefixInto = treeSapling;
		itemDust.mPrefixInto = dust;
		dustDirty.mPrefixInto = dustImpure;
		ingotQuad.mPrefixInto = ingotQuadruple;
		plateQuad.mPrefixInto = plateQuadruple;
		stoneBrick.mPrefixInto = stoneBricks;
		stoneBricksMossy.mPrefixInto = stoneMossyBricks;
		
		block.ignoreMaterials(Materials.Glowstone, Materials.DarkIron);
		
		// These are only the important ones.
		gem.mNotGeneratedItems.add(Materials.Coal);
		gem.mNotGeneratedItems.add(Materials.Charcoal);
		gem.mNotGeneratedItems.add(Materials.NetherStar);
		gem.mNotGeneratedItems.add(Materials.Diamond);
		gem.mNotGeneratedItems.add(Materials.Emerald);
		gem.mNotGeneratedItems.add(Materials.Lapis);
		gem.mNotGeneratedItems.add(Materials.NetherQuartz);
		gem.mNotGeneratedItems.add(Materials.EnderPearl);
		gem.mNotGeneratedItems.add(Materials.EnderEye);
		gem.mNotGeneratedItems.add(Materials.Flint);
		dust.mNotGeneratedItems.add(Materials.Bone);
		dust.mNotGeneratedItems.add(Materials.Redstone);
		dust.mNotGeneratedItems.add(Materials.Glowstone);
		dust.mNotGeneratedItems.add(Materials.Gunpowder);
		dust.mNotGeneratedItems.add(Materials.Sugar);
		dust.mNotGeneratedItems.add(Materials.Blaze);
		stick.mNotGeneratedItems.add(Materials.Wood);
		stick.mNotGeneratedItems.add(Materials.Bone);
		stick.mNotGeneratedItems.add(Materials.Blaze);
		ingot.mNotGeneratedItems.add(Materials.Iron);
		ingot.mNotGeneratedItems.add(Materials.Gold);
		nugget.mNotGeneratedItems.add(Materials.Gold);
		plate.mNotGeneratedItems.add(Materials.Paper);
		cell.mNotGeneratedItems.add(Materials.Empty);
		cell.mNotGeneratedItems.add(Materials.Water);
		cell.mNotGeneratedItems.add(Materials.Lava);
		cell.mNotGeneratedItems.add(Materials.Oxygen);
		cell.mNotGeneratedItems.add(Materials.ConstructionFoam);
		cell.mNotGeneratedItems.add(Materials.UUMatter);
		cell.mNotGeneratedItems.add(Materials.BioFuel);
		cellPlasma.mNotGeneratedItems.add(Materials.Empty);
		bucket.mNotGeneratedItems.add(Materials.Empty);
		bucket.mNotGeneratedItems.add(Materials.Lava);
		bucket.mNotGeneratedItems.add(Materials.Milk);
		bucket.mNotGeneratedItems.add(Materials.Water);
		bottle.mNotGeneratedItems.add(Materials.Empty);
		bottle.mNotGeneratedItems.add(Materials.Water);
		bottle.mNotGeneratedItems.add(Materials.Milk);
		block.mNotGeneratedItems.add(Materials.Iron);
		block.mNotGeneratedItems.add(Materials.Gold);
		block.mNotGeneratedItems.add(Materials.Lapis);
		block.mNotGeneratedItems.add(Materials.Emerald);
		block.mNotGeneratedItems.add(Materials.Redstone);
		block.mNotGeneratedItems.add(Materials.Diamond);
		block.mNotGeneratedItems.add(Materials.Coal);
	}
	
	public final ArrayList<ItemStack> mPrefixedItems = new ArrayList<ItemStack>();
	
	public boolean add(ItemStack aStack) {
		if (aStack == null) return false;
		if (!contains(aStack)) mPrefixedItems.add(aStack);
		while (mPrefixedItems.contains(null)) mPrefixedItems.remove(null);
		return true;
	}
	
	public boolean contains(ItemStack aStack) {
		if (aStack == null) return false;
		for (ItemStack tStack : mPrefixedItems) if (GT_Utility.areStacksEqual(aStack, tStack, !tStack.hasTagCompound())) return true;
		return false;
	}
	
	public boolean dontGenerateItem(Materials aMaterial) {
		return mNotGeneratedItems.contains(aMaterial);
	}
	
	public boolean ignoreMaterials(Materials... aMaterials) {
		for (Materials tMaterial : aMaterials) mIgnoredMaterials.add(tMaterial);
		return true;
	}
	
	public boolean isIgnored(Materials aMaterial) {
		return mIgnoredMaterials.contains(aMaterial);
	}
	
	public boolean add(IOreRecipeRegistrator aRegistrator) {
		if (aRegistrator == null) return false;
		return mOreProcessing.add(aRegistrator);
	}
	
	public void processOre(List<OreDictEntry> entries) {
		for (IOreRecipeRegistrator tRegistrator : mOreProcessing) {
			tRegistrator.registerOre(this, entries);
		}
	}
	
	public final String mUnlocalizedName;
	public final boolean mIsUnificatable, mIsMaterialBased, mIsSelfReferencing, mIsContainer, mDontUnificateActively;
	public OrePrefixes mPrefixInto = this;
	private final ArrayList<Materials> mNotGeneratedItems = new ArrayList<Materials>(), mIgnoredMaterials = new ArrayList<Materials>();
	public final ArrayList<IOreRecipeRegistrator> mOreProcessing = new ArrayList<IOreRecipeRegistrator>();
	
	/**
	 * Used to determine the amount of Material this Prefix contains.
	 * Multiply or Divide GregTech_API.MATERIAL_UNIT to set the Amounts in comparision to one Ingot.
	 * 0 = Null
	 * Negative = Undefined Amount
	 */
	public final long mMaterialAmount;
	
	private OrePrefixes(boolean aIsUnificatable, boolean aIsMaterialBased, boolean aIsSelfReferencing, boolean aIsContainer, boolean aDontUnificateActively, long aMaterialAmount) {
		mIsUnificatable = aIsUnificatable;
		mIsMaterialBased = aIsMaterialBased;
		mIsSelfReferencing = aIsSelfReferencing;
		mIsContainer = aIsContainer;
		mDontUnificateActively = aDontUnificateActively;
		mMaterialAmount = aMaterialAmount;
		mUnlocalizedName = "oreprefixes." + name();
	}
	
	public static OrePrefixes getOrePrefix(String aOre) {
		for (OrePrefixes tPrefix : values()) if (aOre.startsWith(tPrefix.toString())) {
			if (tPrefix == oreNether && aOre.equals("oreNetherQuartz")) return ore;
			return tPrefix;
		}
		return null;
	}
	
	public static String stripPrefix(String aOre) {
		for (OrePrefixes tPrefix : values()) {
			if (aOre.startsWith(tPrefix.toString())) {
				return aOre.replaceFirst(tPrefix.toString(), "");
			}
		}
		return aOre;
	}
	
	public static String replacePrefix(String aOre, OrePrefixes aPrefix) {
		for (OrePrefixes tPrefix : values()) {
			if (aOre.startsWith(tPrefix.toString())) {
				return aOre.replaceFirst(tPrefix.toString(), aPrefix.toString());
			}
		}
		return "";
	}
	
	public static OrePrefixes getPrefix(String aPrefixName) {
		return getPrefix(aPrefixName, null);
	}
	
	public static OrePrefixes getPrefix(String aPrefixName, OrePrefixes aReplacement) {
		try {
			OrePrefixes value = OrePrefixes.valueOf(aPrefixName);
			if (value != null) return value;
		} catch (IllegalArgumentException e) {}
		
		return aReplacement;
	}
	
	public String get(Object aMaterial) {
		return toString() + aMaterial;
	}
	
	public static Materials getMaterial(String aOre) {
		return Materials.get(stripPrefix(aOre));
	}

	public static Materials getMaterial(String aOre, OrePrefixes aPrefix) {
		return Materials.get(aOre.replaceFirst(aPrefix.toString(), ""));
	}
	
	public static Materials getRealMaterial(String aOre, OrePrefixes aPrefix) {
		return Materials.getRealMaterial(aOre.replaceFirst(aPrefix.toString(), ""));
	}
	
	public static boolean isInstanceOf(String aName, OrePrefixes aPrefix) {
		return aName == null ? false : aName.startsWith(aPrefix.toString());
	}

	public static void minimalItems(){
		List<Materials> removals = new ArrayList<>();
		integrateIC2();

		removals.add(Materials.Americium);
		removals.add(Materials.Amethyst);
		removals.add(Materials.Arsenic);
		removals.add(Materials.Barium);
		removals.add(Materials.Boron);
		removals.add(Materials.Caesium);
		removals.add(Materials.Cadmium);
		removals.add(Materials.Cerium);
		removals.add(Materials.Dysprosium);
		removals.add(Materials.Erbium);
		removals.add(Materials.Europium);
		removals.add(Materials.Gadolinium);
		removals.add(Materials.Holmium);
		removals.add(Materials.Indium);
		removals.add(Materials.Lanthanum);
		removals.add(Materials.Lutetium);
		removals.add(Materials.Niobium);
		removals.add(Materials.Molybdenum);
		removals.add(Materials.Neodymium);
		removals.add(Materials.Neutronium);
		removals.add(Materials.Osmium);
		removals.add(Materials.Palladium);
		removals.add(Materials.Plutonium241);
		removals.add(Materials.Praseodymium);
		removals.add(Materials.Promethium);
		removals.add(Materials.Rubidium);
		removals.add(Materials.Samarium);
		removals.add(Materials.Scandium);
		removals.add(Materials.Strontium);
		removals.add(Materials.Tantalum);
		removals.add(Materials.Tellurium);
		removals.add(Materials.Terbium);
		removals.add(Materials.Thulium);
		removals.add(Materials.Vanadium);
		removals.add(Materials.Ytterbium);
		removals.add(Materials.Yttrium);
		removals.add(Materials.Organic);
		removals.add(Materials.Crystal);
		removals.add(Materials.Quartz);
		removals.add(Materials.Cobblestone);
		removals.add(Materials.Adamantium);
		removals.add(Materials.Adamite);
		removals.add(Materials.Adluorite);
		removals.add(Materials.Agate);
		removals.add(Materials.Alduorite);
		removals.add(Materials.Amber);
		removals.add(Materials.Ammonium);
		removals.add(Materials.Amordrine);
		removals.add(Materials.Andesite);
		removals.add(Materials.Angmallen);
		removals.add(Materials.Ardite);
		removals.add(Materials.Aredrite);
		removals.add(Materials.Atlarus);
		removals.add(Materials.Bitumen);
		removals.add(Materials.Black);
		removals.add(Materials.Blizz);
		removals.add(Materials.Blueschist);
		removals.add(Materials.Bluestone);
		removals.add(Materials.Bloodstone);
		removals.add(Materials.Blutonium);
		removals.add(Materials.Carmot);
		removals.add(Materials.Celenegil);
		removals.add(Materials.CertusQuartz);
		removals.add(Materials.Ceruclase);
		removals.add(Materials.Citrine);
		removals.add(Materials.CobaltHexahydrate);
		removals.add(Materials.Chalk);
		removals.add(Materials.Chert);
		removals.add(Materials.Chimerite);
		removals.add(Materials.Coral);
		removals.add(Materials.CrudeOil);
		removals.add(Materials.Chrysocolla);
		removals.add(Materials.CrystalFlux);
		removals.add(Materials.Cyanite);
		removals.add(Materials.Dacite);
		removals.add(Materials.DarkIron);
		removals.add(Materials.DarkStone);
		removals.add(Materials.Demonite);
		removals.add(Materials.Desh);
		removals.add(Materials.Desichalkos);
		removals.add(Materials.Dilithium);
		removals.add(Materials.Draconic);
		removals.add(Materials.Duranium);
		removals.add(Materials.Eclogite);
		removals.add(Materials.ElectrumFlux);
		removals.add(Materials.Emery);
		removals.add(Materials.Enderium);
		removals.add(Materials.Energized);
		removals.add(Materials.Epidote);
		removals.add(Materials.Eximite);
		removals.add(Materials.FieryBlood);
		removals.add(Materials.Firestone);
		removals.add(Materials.Force);
		removals.add(Materials.Forcicium);
		removals.add(Materials.Forcillium);
		removals.add(Materials.Gabbro);
		removals.add(Materials.Gneiss);
		removals.add(Materials.Graphite);
		removals.add(Materials.Greenschist);
		removals.add(Materials.Greenstone);
		removals.add(Materials.Greywacke);
		removals.add(Materials.Haderoth);
		removals.add(Materials.Hematite);
		removals.add(Materials.Hepatizon);
		removals.add(Materials.HSLA);
		removals.add(Materials.Ignatius);
		removals.add(Materials.Infernal);
		removals.add(Materials.Infuscolium);
		removals.add(Materials.InfusedGold);
		removals.add(Materials.InfusedAir);
		removals.add(Materials.InfusedFire);
		removals.add(Materials.InfusedEarth);
		removals.add(Materials.InfusedWater);
		removals.add(Materials.InfusedVis);
		removals.add(Materials.InfusedDull);
		removals.add(Materials.InfusedEntropy);
		removals.add(Materials.InfusedOrder);
		removals.add(Materials.Inolashite);
		removals.add(Materials.Invisium);
		removals.add(Materials.Jade);
		removals.add(Materials.Jasper);
		removals.add(Materials.Kalendrite);
		removals.add(Materials.Komatiite);
		removals.add(Materials.Lemurite);
		removals.add(Materials.Limestone);
		removals.add(Materials.Lodestone);
		removals.add(Materials.Luminite);
		removals.add(Materials.Magma);
		removals.add(Materials.Mawsitsit);
		removals.add(Materials.Mercassium);
		removals.add(Materials.MeteoricIron);
		removals.add(Materials.MeteoricSteel);
		removals.add(Materials.Meteorite);
		removals.add(Materials.Meutoite);
		removals.add(Materials.Migmatite);
		removals.add(Materials.Mimichite);
		removals.add(Materials.Monazite);
		removals.add(Materials.Moonstone);
		removals.add(Materials.Naquadah);
		removals.add(Materials.NaquadahAlloy);
		removals.add(Materials.Nether);
		removals.add(Materials.Nikolite);
		removals.add(Materials.Electrotine);
		removals.add(Materials.ObsidianFlux);
		removals.add(Materials.Oilsands);
		removals.add(Materials.Onyx);
		removals.add(Materials.Orichalcum);
		removals.add(Materials.Osmonium);
		removals.add(Materials.Oureclase);
		removals.add(Materials.Painite);
		removals.add(Materials.Peanutwood);
		removals.add(Materials.Petroleum);
		removals.add(Materials.Pewter);
		removals.add(Materials.Pitchblende);
		removals.add(Materials.Phoenixite);
		removals.add(Materials.Potash);
		removals.add(Materials.Prometheum);
		removals.add(Materials.Quartzite);
		removals.add(Materials.Quicklime);
		removals.add(Materials.Randomite);
		removals.add(Materials.RefinedGlowstone);
		removals.add(Materials.RefinedObsidian);
		removals.add(Materials.Rhyolite);
		removals.add(Materials.Rubracium);
		removals.add(Materials.RyuDragonRyder);
		removals.add(Materials.Sanguinite);
		removals.add(Materials.Siltstone);
		removals.add(Materials.Soapstone);
		removals.add(Materials.Spinel);
		removals.add(Materials.Starconium);
		removals.add(Materials.Sugilite);
		removals.add(Materials.Sunstone);
		removals.add(Materials.Tar);
		removals.add(Materials.Tartarite);
		removals.add(Materials.Tapazite);
		removals.add(Materials.Thyrium);
		removals.add(Materials.Tourmaline);
		removals.add(Materials.Tritanium);
		removals.add(Materials.Turquoise);
		removals.add(Materials.Void);
		removals.add(Materials.Voidstone);
		removals.add(Materials.Vulcanite);
		removals.add(Materials.Vyroxeres);
		removals.add(Materials.Wimalite);
		removals.add(Materials.Yellorite);
		removals.add(Materials.Yellorium);
		removals.add(Materials.Zectium);
		removals.add(Materials.Advanced);
		removals.add(Materials.Basic);
		removals.add(Materials.Antimatter);
		removals.add(Materials.Chocolate);
		removals.add(Materials.Cluster);
		removals.add(Materials.Coffee);
		removals.add(Materials.Data);
		removals.add(Materials.Elite);
		removals.add(Materials.Good);
		removals.add(Materials.Infinite);
		removals.add(Materials.LimePure);
		removals.add(Materials.Master);
		removals.add(Materials.Meat);
		removals.add(Materials.MeatRaw);
		removals.add(Materials.MeatCooked);
		removals.add(Materials.Mud);
		removals.add(Materials.Peat);
		removals.add(Materials.Primitive);
		removals.add(Materials.Quantum);
		removals.add(Materials.Red);
		removals.add(Materials.Reinforced);
		removals.add(Materials.Ultimate);
		removals.add(Materials.Unstable);
		removals.add(Materials.Unstableingot);
		removals.add(Materials.AluminiumBrass);
		removals.add(Materials.Osmiridium);
		removals.add(Materials.Sunnarium);
		removals.add(Materials.Asbestos);
		removals.add(Materials.BandedIron);
		removals.add(Materials.BlueTopaz);
		removals.add(Materials.BrownLimonite);
		removals.add(Materials.CassiteriteSand);
		removals.add(Materials.Celestine);
		removals.add(Materials.Chalcopyrite);
		removals.add(Materials.Chromite);
		removals.add(Materials.Cobaltite);
		removals.add(Materials.DeepIron);
		removals.add(Materials.Garnierite);
		removals.add(Materials.Ilmenite);
		removals.add(Materials.LiveRoot);
		removals.add(Materials.Magnesite);
		removals.add(Materials.Magnetite);
		removals.add(Materials.Molybdenite);
		removals.add(Materials.PigIron);
		removals.add(Materials.Powellite);
		removals.add(Materials.Pumice);
		removals.add(Materials.Pyrolusite);
		removals.add(Materials.RockSalt);
		removals.add(Materials.Scheelite);
		removals.add(Materials.Stibnite);
		removals.add(Materials.Tanzanite);
		removals.add(Materials.Topaz);
		removals.add(Materials.Ultimet);
		removals.add(Materials.Plutonium241);
		removals.add(Materials.Uraninite);
		removals.add(Materials.Wulfenite);
		removals.add(Materials.WroughtIron);
		removals.add(Materials.YellowLimonite);
		removals.add(Materials.Perlite);
		removals.add(Materials.Borax);
		removals.add(Materials.Opal);
		removals.add(Materials.Diatomite);
		removals.add(Materials.VolcanicAsh);
		removals.add(Materials.Niter);
		removals.add(Materials.Pyrotheum);
		removals.add(Materials.Apatite);
		removals.add(Materials.Alumite);
		removals.add(Materials.Manyullyn);
		removals.add(Materials.IronWood);
		removals.add(Materials.ShadowIron);
		removals.add(Materials.ShadowSteel);
		removals.add(Materials.SteelLeaf);
		removals.add(Materials.BlackSteel);
		removals.add(Materials.DamascusSteel);
		removals.add(Materials.AstralSilver);
		removals.add(Materials.Midasium);
		removals.add(Materials.Mithril);
		removals.add(Materials.BlueAlloy);
		removals.add(Materials.ElectrotineAlloy);
		removals.add(Materials.Basalt);
		removals.add(Materials.Marble);
		removals.add(Materials.Thaumium);
		removals.add(Materials.Vinteum);
		removals.add(Materials.Vis);
		removals.add(Materials.Redrock);
		removals.add(Materials.Chrysotile);
		removals.add(Materials.VanadiumMagnetite);
		removals.add(Materials.BasalticMineralSand);
		removals.add(Materials.GraniticMineralSand);
		removals.add(Materials.GarnetSand);
		removals.add(Materials.QuartzSand);
		removals.add(Materials.Bastnasite);
		removals.add(Materials.Pentlandite);
		removals.add(Materials.Spodumene);
		removals.add(Materials.Pollucite);
		removals.add(Materials.Tantalite);
		removals.add(Materials.Lepidolite);
		removals.add(Materials.Glauconite);
		removals.add(Materials.Vermiculite);
		removals.add(Materials.Bentonite);
		removals.add(Materials.FullersEarth);
		removals.add(Materials.Mirabilite);
		removals.add(Materials.Mica);
		removals.add(Materials.Trona);
		removals.add(Materials.Barite);
		removals.add(Materials.Gypsum);
		removals.add(Materials.Alunite);
		removals.add(Materials.Dolomite);
		removals.add(Materials.Wollastonite);
		removals.add(Materials.Zeolite);
		removals.add(Materials.Kyanite);
		removals.add(Materials.Kaolinite);
		removals.add(Materials.Talc);
		removals.add(Materials.Ash);
		removals.add(Materials.Palygorskite);
		removals.add(Materials.Adamantine);
		removals.add(Materials.FzDarkIron);
		removals.add(Materials.FZDarkIron);
		removals.add(Materials.DarkAshes);
		removals.add(Materials.Abyssal);
		removals.add(Materials.Adamant);
		removals.add(Materials.AluminumBrass);
		removals.add(Materials.Aluminum);
		removals.add(Materials.NaturalAluminum);
		removals.add(Materials.NaturalAluminium);
		removals.add(Materials.Americum);
		removals.add(Materials.Beryl);
		removals.add(Materials.BlackGranite);
		removals.add(Materials.CalciumCarbonate);
		removals.add(Materials.Chromium);
		removals.add(Materials.Garnet);
		removals.add(Materials.Kalium);
		removals.add(Materials.Monazit);
		removals.add(Materials.Natrium);
		removals.add(Materials.Obby);
		removals.add(Materials.Peridot);
		removals.add(Materials.Phosphorite);
		removals.add(Materials.Quarried);
		removals.add(Materials.RedRock);
		removals.add(Materials.RefinedIron);
		removals.add(Materials.RedGranite);
		removals.add(Materials.Soulsand);
		removals.add(Materials.SilverLead);
		removals.add(Materials.Titan);
		removals.add(Materials.Uran);
		removals.add(Materials.Wolframite);
		removals.add(Materials.Wolframium);
		removals.add(Materials.Wolfram);
		removals.add(Materials.WrougtIron);

		for (Materials aRemoval : removals) {
			for (OrePrefixes aPrefix : OrePrefixes.values()) {
				aPrefix.mNotGeneratedItems.add(aRemoval);
			}
		}
	}

	public static void integrateIC2(){
		//Ingot
		GT_OreDictUnificator.set("ingotRubber", GT_ModHandler.getIC2Item("rubber", 1));
		GT_OreDictUnificator.set("ingotCopper", GT_ModHandler.getIC2Item( "copperIngot",1));
		GT_OreDictUnificator.set("ingotTin", GT_ModHandler.getIC2Item( "tinIngot",1));
		GT_OreDictUnificator.set("ingotBronze", GT_ModHandler.getIC2Item( "bronzeIngot",1));
		GT_OreDictUnificator.set("ingotLead", GT_ModHandler.getIC2Item( "leadIngot",1));
		GT_OreDictUnificator.set("ingotSilver", GT_ModHandler.getIC2Item( "silverIngot",1));
		GT_OreDictUnificator.set("ingotUranium", GT_ModHandler.getIC2Item( "Uran",1));
		GT_OreDictUnificator.set("ingotUranium235", GT_ModHandler.getIC2Item( "Uran235",1));
		GT_OreDictUnificator.set("ingotPlutonium", GT_ModHandler.getIC2Item( "Plutonium",1));
		ingot.mNotGeneratedItems.add(Materials.Rubber);
		ingot.mNotGeneratedItems.add(Materials.Copper);
		ingot.mNotGeneratedItems.add(Materials.Tin);
		ingot.mNotGeneratedItems.add(Materials.Bronze);
		ingot.mNotGeneratedItems.add(Materials.Lead);
		ingot.mNotGeneratedItems.add(Materials.Silver);
		gem.mNotGeneratedItems.add(Materials.Uranium);
		gem.mNotGeneratedItems.add(Materials.Uranium235);
		gem.mNotGeneratedItems.add(Materials.Plutonium);

		//Dust
		GT_OreDictUnificator.set("dustBronze", GT_ModHandler.getIC2Item( "bronzeDust",1));
		GT_OreDictUnificator.set("dustCopper", GT_ModHandler.getIC2Item( "copperDust",1));
		GT_OreDictUnificator.set("dustGold", GT_ModHandler.getIC2Item( "goldDust",1));
		GT_OreDictUnificator.set("dustIron", GT_ModHandler.getIC2Item( "ironDust",1));
		GT_OreDictUnificator.set("dustSilver", GT_ModHandler.getIC2Item( "silverDust",1));
		GT_OreDictUnificator.set("dustTin", GT_ModHandler.getIC2Item( "tinDust",1));
		GT_OreDictUnificator.set("dustLead", GT_ModHandler.getIC2Item( "leadDust",1));
		GT_OreDictUnificator.set("dustLapis", GT_ModHandler.getIC2Item( "lapiDust",1));
		GT_OreDictUnificator.set("dustClay", GT_ModHandler.getIC2Item( "clayDust",1));
		GT_OreDictUnificator.set("dustCoal", GT_ModHandler.getIC2Item( "coalDust",1));
		GT_OreDictUnificator.set("dustStone", GT_ModHandler.getIC2Item( "stoneDust",1));
		GT_OreDictUnificator.set("dustLithium", GT_ModHandler.getIC2Item( "lithiumDust",1));
		GT_OreDictUnificator.set("dustSulfur", GT_ModHandler.getIC2Item( "sulfurDust",1));
		GT_OreDictUnificator.set("dustSiliconDioxide", GT_ModHandler.getIC2Item( "silicondioxideDust",1));
		GT_OreDictUnificator.set("dustDiamond", GT_ModHandler.getIC2Item( "diamondDust",1));
		dust.mNotGeneratedItems.add(Materials.Bronze);
		dust.mNotGeneratedItems.add(Materials.Copper);
		dust.mNotGeneratedItems.add(Materials.Gold);
		dust.mNotGeneratedItems.add(Materials.Iron);
		dust.mNotGeneratedItems.add(Materials.Silver);
		dust.mNotGeneratedItems.add(Materials.Tin);
		dust.mNotGeneratedItems.add(Materials.Lead);
		dust.mNotGeneratedItems.add(Materials.Lapis);
		dust.mNotGeneratedItems.add(Materials.Clay);
		dust.mNotGeneratedItems.add(Materials.Coal);
		dust.mNotGeneratedItems.add(Materials.Stone);
		dust.mNotGeneratedItems.add(Materials.Lithium);
		dust.mNotGeneratedItems.add(Materials.Sulfur);
		dust.mNotGeneratedItems.add(Materials.Diamond);
		dust.mNotGeneratedItems.add(Materials.SiliconDioxide);

		//Crushed
		GT_OreDictUnificator.set("crushedCopper", GT_ModHandler.getIC2Item( "crushedCopperOre",1));
		GT_OreDictUnificator.set("crushedGold", GT_ModHandler.getIC2Item( "crushedGoldOre",1));
		GT_OreDictUnificator.set("crushedIron", GT_ModHandler.getIC2Item( "crushedIronOre",1));
		GT_OreDictUnificator.set("crushedTin", GT_ModHandler.getIC2Item( "crushedTinOre",1));
		GT_OreDictUnificator.set("crushedLead", GT_ModHandler.getIC2Item( "crushedLeadOre",1));
		GT_OreDictUnificator.set("crushedSilver", GT_ModHandler.getIC2Item( "crushedSilverOre",1));
		GT_OreDictUnificator.set("crushedUranium", GT_ModHandler.getIC2Item( "crushedUranOre",1));
		crushed.mNotGeneratedItems.add(Materials.Copper);
		crushed.mNotGeneratedItems.add(Materials.Gold);
		crushed.mNotGeneratedItems.add(Materials.Iron);
		crushed.mNotGeneratedItems.add(Materials.Tin);
		crushed.mNotGeneratedItems.add(Materials.Lead);
		crushed.mNotGeneratedItems.add(Materials.Silver);
		crushed.mNotGeneratedItems.add(Materials.Uranium);

		//CrushedPurified
		GT_OreDictUnificator.set("crushedPurifiedCopper", GT_ModHandler.getIC2Item( "purifiedCrushedCopperOre",1));
		GT_OreDictUnificator.set("crushedPurifiedGold", GT_ModHandler.getIC2Item( "purifiedCrushedGoldOre",1));
		GT_OreDictUnificator.set("crushedPurifiedIron", GT_ModHandler.getIC2Item( "purifiedCrushedIronOre",1));
		GT_OreDictUnificator.set("crushedPurifiedTin", GT_ModHandler.getIC2Item( "purifiedCrushedTinOre",1));
		GT_OreDictUnificator.set("crushedPurifiedLead", GT_ModHandler.getIC2Item( "purifiedCrushedLeadOre",1));
		GT_OreDictUnificator.set("crushedPurifiedSilver", GT_ModHandler.getIC2Item( "purifiedCrushedSilverOre",1));
		GT_OreDictUnificator.set("crushedPurifiedUranium", GT_ModHandler.getIC2Item( "purifiedCrushedUranOre",1));
		crushedPurified.mNotGeneratedItems.add(Materials.Copper);
		crushedPurified.mNotGeneratedItems.add(Materials.Gold);
		crushedPurified.mNotGeneratedItems.add(Materials.Iron);
		crushedPurified.mNotGeneratedItems.add(Materials.Tin);
		crushedPurified.mNotGeneratedItems.add(Materials.Lead);
		crushedPurified.mNotGeneratedItems.add(Materials.Silver);
		crushedPurified.mNotGeneratedItems.add(Materials.Uranium);

		//DustTiny
		GT_OreDictUnificator.set("dustTinyBronze", GT_ModHandler.getIC2Item( "dustBronzeSmall",1));
		GT_OreDictUnificator.set("dustTinyCopper", GT_ModHandler.getIC2Item( "dustCopperSmall",1));
		GT_OreDictUnificator.set("dustTinyGold", GT_ModHandler.getIC2Item( "dustGoldSmall",1));
		GT_OreDictUnificator.set("dustTinyIron", GT_ModHandler.getIC2Item( "dustIronSmall",1));
		GT_OreDictUnificator.set("dustTinyTin", GT_ModHandler.getIC2Item( "dustTinSmall",1));
		GT_OreDictUnificator.set("dustTinyLead", GT_ModHandler.getIC2Item( "dustLeadSmall",1));
		GT_OreDictUnificator.set("dustTinySilver", GT_ModHandler.getIC2Item( "dustSilverSmall",1));
		dustTiny.mNotGeneratedItems.add(Materials.Bronze);
		dustTiny.mNotGeneratedItems.add(Materials.Copper);
		dustTiny.mNotGeneratedItems.add(Materials.Gold);
		dustTiny.mNotGeneratedItems.add(Materials.Iron);
		dustTiny.mNotGeneratedItems.add(Materials.Tin);
		dustTiny.mNotGeneratedItems.add(Materials.Lead);
		dustTiny.mNotGeneratedItems.add(Materials.Silver);

		//Plate
		GT_OreDictUnificator.set("plateBronze", GT_ModHandler.getIC2Item( "bronzePlate",1));
		GT_OreDictUnificator.set("plateCopper", GT_ModHandler.getIC2Item( "copperPlate",1));
		GT_OreDictUnificator.set("plateGold", GT_ModHandler.getIC2Item( "goldPlate",1));
		GT_OreDictUnificator.set("plateIron", GT_ModHandler.getIC2Item( "ironPlate",1));
		GT_OreDictUnificator.set("plateTin", GT_ModHandler.getIC2Item( "tinPlate",1));
		GT_OreDictUnificator.set("plateLead", GT_ModHandler.getIC2Item( "leadPlate",1));
		GT_OreDictUnificator.set("plateSilver", GT_ModHandler.getIC2Item( "silverPlate",1));
		plate.mNotGeneratedItems.add(Materials.Bronze);
		plate.mNotGeneratedItems.add(Materials.Copper);
		plate.mNotGeneratedItems.add(Materials.Gold);
		plate.mNotGeneratedItems.add(Materials.Iron);
		plate.mNotGeneratedItems.add(Materials.Tin);
		plate.mNotGeneratedItems.add(Materials.Lead);
		plate.mNotGeneratedItems.add(Materials.Silver);

		//Nugget
		GT_OreDictUnificator.set("nuggetUranium235", GT_ModHandler.getIC2Item( "Uran235small",1));
		GT_OreDictUnificator.set("nuggetPlutonium", GT_ModHandler.getIC2Item( "PlutoniumSmall",1));
		nugget.mNotGeneratedItems.add(Materials.Uranium235);
		nugget.mNotGeneratedItems.add(Materials.Plutonium);

	}
	
	public static volatile int VERSION = 416;
}