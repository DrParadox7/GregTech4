package gregtechmod.common;

import gregtechmod.GT_Mod;
import gregtechmod.api.GregTech_API;
import gregtechmod.api.enums.Dyes;
import gregtechmod.api.enums.GT_ConfigCategories;
import gregtechmod.api.enums.GT_OreDictNames;
import gregtechmod.api.enums.GT_ToolDictNames;
import gregtechmod.api.enums.Materials;
import gregtechmod.api.enums.OrePrefixes;
import gregtechmod.api.util.GT_Log;
import gregtechmod.api.util.GT_ModHandler;
import gregtechmod.api.util.GT_OreDictUnificator;
import gregtechmod.api.util.GT_RecipeRegistrator;
import gregtechmod.api.util.GT_Utility;
import gregtechmod.api.util.OreDictEntry;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.ModContainer;
import cpw.mods.fml.common.ProgressManager;
import cpw.mods.fml.common.ProgressManager.ProgressBar;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;

import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.oredict.OreDictionary.OreRegisterEvent;

@SuppressWarnings("deprecation")
public class GT_OreDictHandler {
	public static final GT_OreDictHandler instance = new GT_OreDictHandler();
	
	private static final Map<OrePrefixes, List<OreDictEntry>> mEvents = new HashMap<>();
    private static Map<String, Map<String, List<ItemStack>>> backlog = new HashMap<>();// Delay registration by caches all ore registration happening preInit (before configs are loaded) until configs are here

    private static Set<String> mIgnoredItems;
    private static Set<String> mIgnoredNames;
    private static Set<String> mInvalidNames;
    private static Set<String> mIgnoredPrefixes;

    private static final String[] defaultOredictModBlacklist = new String[] {
            "tconstruct", "xycraft", "botan", "chisel", "extratic", "extratrees",
            "extrautilities", "forgemicroblock", "hardcoreenderexpansion", "minechem",
            "nuclearcraft", "opencomputers", "projred", "biblio", "warpdrive",
            "storagedrawers", "natura"};

    private static final String[] defaultIgnoredItems = new String[] {
            "itemRawRubber", "itemSilicon", "itemBacon", "itemJetpackAccelerator", "itemLazurite", "itemIridium",
            "itemTear", "itemClaw", "itemFertilizer", "itemTar", "itemSlimeball", "itemCoke", "itemBeeswax", "itemBeeQueen",
            "itemForcicium", "itemForcillium", "itemRoyalJelly", "itemHoneydew", "itemHoney", "itemPollen", "itemReedTypha",
            "itemSulfuricAcid", "itemPotash", "itemCompressedCarbon", "itemBitumen", "itemBioFuel", "itemCokeSugar", "itemCokeCactus",
            "itemCharcoalSugar", "itemCharcoalCactus", "itemSludge", "itemEnrichedAlloy", "itemQuicksilver", "itemMercury",
            "itemOsmium", "itemUltimateCircuit", "itemEnergizedStar", "itemAntimatterMolecule", "itemAntimatterGlob", "itemCoal",
            "itemBoat", "itemHerbalMedicineCake", "itemCakeSponge", "itemFishandPumpkinCakeSponge", "itemSoulCleaver",
            "itemInstantCake", "itemWhippingCream", "itemGlisteningWhippingCream", "itemCleaver", "itemHerbalMedicineWhippingCream",
            "itemStrangeWhippingCream", "itemBlazeCleaver", "itemBakedCakeSponge", "itemMagmaCake", "itemGlisteningCake",
            "itemOgreCleaver", "itemFishandPumpkinCake", "itemMagmaWhippingCream", "itemMultimeter", "itemSuperconductor"
    };
    private static final String[] defaultIgnoredNames = new String[] {
            "whiteStone", "stoneSlab", "clayBowl", "clayPlate", "ceramicBowl", "ceramicPlate",
            "ovenRack", "clayCup", "ceramicCup", "batteryBox", "transmutationStone", "torchRedstoneActive",
            "coal", "charcoal", "cloth", "cobblestoneSlab", "stoneBrickSlab", "cobblestoneWall",
            "stoneBrickWall", "cobblestoneStair", "stoneBrickStair", "blockCloud", "blockDirt", "blockTyrian",
            "blockCarpet", "blockFft", "blockLavastone", "blockHolystone", "blockConcrete", "sunnariumPart",
            "brSmallMachineCyaniteProcessor", "meteoriteCoal", "blockCobble", "pressOreProcessor", "crusherOreProcessor",
            "grinderOreProcessor", "blockRubber", "blockHoney", "blockHoneydew", "blockPeat", "blockRadioactive",
            "blockSlime", "blockCocoa", "blockSugarCane", "blockLeather", "blockClayBrick", "solarPanelHV", "cableRedNet",
            "stoneBowl", "crafterWood", "taintedSoil", "brickXyEngineering", "breederUranium", "wireMill", "chunkLazurite",
            "aluminumNatural", "aluminiumNatural", "naturalAluminum", "naturalAluminium", "antimatterMilligram",
            "antimatterGram", "strangeMatter", "coalGenerator", "electricFurnace", "unfinishedTank", "valvePart", "aquaRegia",
            "leatherSeal", "leatherSlimeSeal", "hambone", "slimeball", "enrichedUranium", "camoPaste", "livingrock",
            "quicksilver", "nuggetQuicksilver", "dirt", "grass", "gravel", "mycelium", "podzol", "netherrack", "ice",
            "chest", "glowstone"
    };
    private static final String[] defaultInvalidNames = new String[] {
            "bloodstoneOre", "universalCable", "bronzeTube", "ironTube", "netherTube",
            "obbyTube", "infiniteBattery", "eliteBattery", "advancedBattery", "10kEUStore",
            "blueDye", "MonazitOre", "quartzCrystal", "whiteLuminiteCrystal", "darkStoneIngot",
            "invisiumIngot", "demoniteOrb", "enderGem", "starconiumGem", "osmoniumIngot",
            "tapaziteGem", "zectiumIngot", "foolsRubyGem", "rubyGem", "meteoriteGem", "adamiteShard",
            "sapphireGem", "copperIngot", "ironStick", "goldStick", "diamondStick", "reinforcedStick",
            "draconicStick", "emeraldStick", "copperStick", "tinStick", "silverStick", "bronzeStick",
            "steelStick", "leadStick", "manyullynStick", "arditeStick", "cobaltStick", "aluminiumStick",
            "alumiteStick", "oilsandsOre", "copperWire", "superconductorWire", "sulfuricAcid", "conveyorBelt",
            "ironWire", "aluminumWire", "aluminiumWire", "silverWire", "tinWire", "dustSiliconSmall", "AluminumOre",
            "plateHeavyT2", "blockWool", "alloyPlateEnergizedHardened", "gasWood", "alloyPlateEnergized",
            "SilverOre", "LeadOre", "TinOre", "CopperOre", "silverOre", "leadOre", "tinOre", "copperOre",
            "bauxiteOre", "HSLivingmetalIngot", "oilMoving", "oilStill", "oilBucket", "petroleumOre", "dieselFuel",
            "diamondNugget", "planks", "wood", "stick", "sticks", "naquadah", "obsidianRod", "stoneRod",
            "thaumiumRod", "steelRod", "netherrackRod", "woodRod", "ironRod", "cactusRod", "flintRod", "copperRod",
            "cobaltRod", "alumiteRod", "blueslimeRod", "arditeRod", "manyullynRod", "bronzeRod", "boneRod", "slimeRod"};

    private static final String[] defaultIgnoredPrefixes = new String[] {
            "reactor", "mffs", "projred", "ganys", "mystic", "arcane", "petal", "rune",
            "stained"};

    private static final List<String> requiredMods = Arrays.asList("minecraft", "gregtech_addon");


    public static Set<String> modBlacklist;
    public static Set<String> modWhitelist;

    private static boolean mActivated = false;
    private static boolean configLoaded = false;

    @SubscribeEvent
    public void registerOre(OreRegisterEvent aEvent) {
        if (GT_Mod.mDoNotInit || aEvent == null || aEvent.Ore == null || aEvent.Ore.getItem() == null || aEvent.Name == null || aEvent.Name.equals(""))
            return;

        ModContainer tContainer = Loader.instance().activeModContainer();
        String modName = tContainer != null ? tContainer.getModId() : "minecraft";

        if (configLoaded)
            registerOre(modName, aEvent.Name, aEvent.Ore);
         else {
            backlog.computeIfAbsent(modName, c -> new HashMap<>())
                    .computeIfAbsent(aEvent.Name, c -> new ArrayList<>())
                    .add(aEvent.Ore);
         }

    }

    public static void loadingOredictConfigs() {
        mIgnoredItems = new HashSet<>(Arrays.asList(GregTech_API.sUnification.mConfig.getStringList(
                "IgnoredItems",
                GT_ConfigCategories.oredictParameters.toString(),
                defaultIgnoredItems,
                "Items to ignore")));

        mIgnoredNames = new HashSet<>(Arrays.asList(GregTech_API.sUnification.mConfig.getStringList(
                "IgnoredNames",
                GT_ConfigCategories.oredictParameters.toString(),
                defaultIgnoredNames,
                "Item names to ignore")));
        mInvalidNames = new HashSet<>(Arrays.asList(GregTech_API.sUnification.mConfig.getStringList(
                "InvalidNames",
                GT_ConfigCategories.oredictParameters.toString(),
                defaultInvalidNames,
                "Invalid names")));

        mIgnoredPrefixes = new HashSet<>(Arrays.asList(GregTech_API.sUnification.mConfig.getStringList(
                "IgnoredPrefixes",
                GT_ConfigCategories.oredictParameters.toString(),
                defaultIgnoredPrefixes,
                "Ignored Prefixes")));

        modWhitelist = new HashSet<>(Arrays.asList(GregTech_API.sUnification.mConfig.getStringList(
                "modWhitelist",
                GT_ConfigCategories.oredictMod.toString(),
                new String[]{},
                "Prevent mods not in this list from being integrated into GT4 oredict registration. Ignored if empty")));

        modBlacklist = new HashSet<>(Arrays.asList(GregTech_API.sUnification.mConfig.getStringList(
                "modBlacklist",
                GT_ConfigCategories.oredictMod.toString(),
                defaultOredictModBlacklist,
                "Prevent mods in this list from being integrated into GT4 oredict registration. Requires [oredictModWhitelist] to be empty")));
        configLoaded = true;
        processBacklog();
    }

    private static void processBacklog() {
        backlog.keySet().removeIf(modName -> !isModOredictEnabled(modName));
        backlog.entrySet().removeIf(entryName -> mIgnoredNames.contains(entryName.getKey()));

        backlog.forEach((modName, entryNames) -> {

            entryNames.forEach((entryName, itemStacks) -> {
                itemStacks.forEach(itemstack ->
                        registerOre(modName, entryName, itemstack)
                );
            });
        });

        backlog = null;
    }

    public static void registerOre(String modName, String name, ItemStack stack) {

        try {
            if (stack.stackSize != 1) {
                GT_Log.log.warn("WARNING: '" + name + "' is either being misused by another Mod or has been wrongly registered, as the stackSize of the Event-Stack is not 1!!!");
            }

            stack.stackSize = 1;

            if (!isModOredictEnabled(modName)) return;

            if (GregTech_API.OREDICT_DEBUG_MODE && (mActivated || GregTech_API.sPostloadStarted || GT_Mod.sSortToTheEnd && GregTech_API.sLoadFinished)) {
                GT_Log.log.warn("WARNING: " + modName + " attempted to register " + name + " very late at the OreDictionary! Some Functionality may not work as expected! Sometimes registration in Postload is required, but you should always register OreDictionary Items in the Load Phase whenever possible.");
            }

            String e = modName + " -> " + name;
            String tAssosiation = GT_OreDictUnificator.getAssociation(stack);
            if(GT_Utility.isStringValid(tAssosiation) && tAssosiation.equals(name)) {
                GT_Log.ore.println(e + " is ambiguous, this is an Error.");
                GT_Log.log.warn("WARNING: The OreDict-Registration of " + name + " by " + modName + " is ambiguous. Please check if the Item hasn't already been registered under that Name, before registering it a second time!");
            } else {
                if (name.startsWith("item") && mIgnoredItems.contains(name)) {
                    GT_Log.ore.println(e);
                    if (name.equals("itemCopperWire")) {
                        GT_OreDictUnificator.registerOre(GT_OreDictNames.craftingWireCopper, stack);
                    }
                } else if(mIgnoredNames.contains(name)) {
                    GT_Log.ore.println(e + " is getting ignored via hardcode.");
                } else if(name.equals("stone")) {
                    GT_OreDictUnificator.registerOre("stoneSmooth", stack);
                } else if(name.equals("cobblestone")) {
                    GT_OreDictUnificator.registerOre("stoneCobble", stack);
                } else if(!name.contains("|") && !name.contains("*") && !name.contains(":") && !name.contains(".") && !name.contains("$")) {
                    for (String tName : mIgnoredPrefixes) {
                        if (name.startsWith(tName)) {
                            GT_Log.ore.println(e + " is using an ignored Prefix and is therefor getting ignored via hardcode.");
                            return;
                        }
                    }

                    if(name.equals("copperWire")) {
                        GT_OreDictUnificator.registerOre(GT_OreDictNames.craftingWireCopper, stack);
                    }

//                      if(name.equals("sheetPlastic")) {
//                         GT_OreDictUnificator.registerOre(OrePrefixes.plate, Materials.Plastic, stack);
//                      }

                    if(name.contains(" ")) {
                        GT_Log.ore.println(e + " is getting re-registered because the OreDict Name containing invalid spaces.");
                        GT_Log.log.warn("WARNING: '" + name + "' is an invalid OreDictionary Name, as it contains spaces! Register it without spaces to fix that.");
                        GT_OreDictUnificator.registerOre(name.replaceAll(" ", ""), GT_Utility.copyAmount(1L, stack));
                    } else if(mInvalidNames.contains(name)) {
                        GT_Log.ore.println(e + " is wrongly registered and therefor getting ignored.");
                        GT_Log.log.error("WARNING: '" + name + "' is an invalid OreDictionary Name. The Name doesn't fit to the Type of Item and/or doesn't follow a proper OreDictionary Convention. If you are the Owner of the Mod who adds this Item, please do the following: ");
                        if(name.equals("oilsandsOre")) {
                            GT_Log.log.error("Please change it to 'sandOil'");
                            GT_OreDictUnificator.registerOre("sandOil", stack);
                        } else if(name.equals("10kEUStore")) {
                            GT_Log.log.error("Use 'crafting10kEUStore', you forgot to add the prefix");
                        } else if(name.equals("sulfuricAcid")) {
                            GT_Log.log.error("Please use 'bottleSulfuricAcid' instead, since it is likely a vanilla bottle containing the Material 'SulfuricAcid'");
                        } else if(name.equals("stick")) {
                            GT_Log.log.error("Use 'stickWood' instead, it is already registered in vanilla-forge");
                        } else if(name.equals("wood")) {
                            GT_Log.log.error("Use 'logWood' instead, it is already registered in vanilla-forge");
                        } else if(name.equals("plank")) {
                            GT_Log.log.error("Use 'plankWood' instead, it is already registered in vanilla-forge");
                        } else if(name.endsWith("Tube")) {
                            GT_Log.log.error("Put the 'Tube' in the beginning of the Name to get 'tube" + GT_Utility.capitalizeString(name.replaceFirst("Tube", "")) + "'");
                        } else if(name.endsWith("Cable")) {
                            GT_Log.log.error("Put the 'Cable' in the beginning of the Name to get 'cable" + GT_Utility.capitalizeString(name.replaceFirst("Cable", "")) + "'");
                        } else if(name.endsWith("Battery")) {
                            GT_Log.log.error("Put the 'Battery' in the beginning of the Name to get 'battery" + GT_Utility.capitalizeString(name.replaceFirst("Battery", "")) + "'");
                        } else if(name.endsWith("Ingot")) {
                            GT_Log.log.error("Put the 'Ingot' in the beginning of the Name to get 'ingot" + GT_Utility.capitalizeString(name.replaceFirst("Ingot", "")) + "'");
                        } else if(name.endsWith("Crystal")) {
                            GT_Log.log.error("Put the 'Crystal' in the beginning of the Name to get 'crystal" + GT_Utility.capitalizeString(name.replaceFirst("Crystal", "")) + "'");
                        } else if(name.endsWith("Shard")) {
                            GT_Log.log.error("Put the 'Shard' in the beginning of the Name to get 'shard" + GT_Utility.capitalizeString(name.replaceFirst("Shard", "")) + "'");
                        } else if(name.endsWith("Rod")) {
                            GT_Log.log.error("Put the 'Rod' in the beginning of the Name to get 'rod" + GT_Utility.capitalizeString(name.replaceFirst("Rod", "")) + "'");
                        } else if(name.endsWith("Orb")) {
                            GT_Log.log.error("Put the 'Orb' in the beginning of the Name to get 'orb" + GT_Utility.capitalizeString(name.replaceFirst("Irb", "")) + "'");
                        } else if(name.endsWith("Gem")) {
                            GT_Log.log.error("Put the 'Gem' in the beginning of the Name to get 'gem" + GT_Utility.capitalizeString(name.replaceFirst("Gem", "")) + "'");
                        } else if(name.endsWith("Stick")) {
                            GT_Log.log.error("Put the 'Stick' in the beginning of the Name to get 'stick" + GT_Utility.capitalizeString(name.replaceFirst("Stick", "")) + "'");
                        } else if(name.endsWith("Plate")) {
                            GT_Log.log.error("Put the 'Plate' in the beginning of the Name to get 'plate" + GT_Utility.capitalizeString(name.replaceFirst("Plate", "")) + "'");
                        } else if(name.endsWith("Ore")) {
                            GT_Log.log.error("Put the 'Ore' in the beginning of the Name to get 'ore" + GT_Utility.capitalizeString(name.replaceFirst("Ore", "")) + "'");
                        } else if(name.endsWith("Dye")) {
                            GT_Log.log.error("Put the 'Dye' in the beginning of the Name to get 'dye" + GT_Utility.capitalizeString(name.replaceFirst("Dye", "")) + "'");
                        } else if(name.endsWith("Wire")) {
                            GT_Log.log.error("Put the 'Wire' in the beginning of the Name to get 'wire" + GT_Utility.capitalizeString(name.replaceFirst("Wire", "")) + "'");
                        } else if(name.endsWith("Nugget")) {
                            GT_Log.log.error("Put the 'Nugget' in the beginning of the Name to get 'nugget" + GT_Utility.capitalizeString(name.replaceFirst("Nugget", "")) + "'");
                        } else {
                            GT_Log.log.error("I don't know exactly what to suggest about this Name, please consult me personally at GregTech.");
                        }

                        GT_Log.log.error("Private Prefixes could also be a solution if the first Suggestion doesn't apply. In that case the suggestion for the name is '" + modName + ":" + name + "' don't forget to insert the ':' inbetween the Mod ID and OreDict Name, that is the most important part.");
                        GT_Log.log.error("If you are not the Owner then report it to the Owner of the Mod, which the Item belongs to.");
                    } else {
                        OrePrefixes aPrefix = OrePrefixes.getOrePrefix(name);
                        String tName = "";
                        if(aPrefix == null) {
                            if(name.toLowerCase().equals(name) && !name.equals("glowstone")) {
                                GT_Log.log.error("Improperly registered Ore: " + name + " !!!Improperly registered Ore detected!!! This Object does not follow any OreDictionary Convention, as it is 100% lowercased!!! Please report this to its Modauthor for a fix. If nothing proper is found, a good suggestion for its Name would be '" + modName + ":" + name + "' don't forget to insert the ':' inbetween the Mod ID and OreDict Name, that is the most important part.");
                                GT_Log.ore.println(e + " is invalid due to being solely lowercased.");
                                return;
                            }

                            if(name.toUpperCase().equals(name)) {
                                GT_Log.log.error("Improperly registered Ore: " + name + " !!!Improperly registered Ore detected!!! This Object does not follow any OreDictionary Convention, as it is 100% uppercased!!! Please report this to its Modauthor for a fix. If nothing proper is found, a good suggestion for its Name would be '" + modName + ":" + name + "' don't forget to insert the ':' inbetween the Mod ID and OreDict Name, that is the most important part.");
                                GT_Log.ore.println(e + " is invalid due to being solely uppercased.");
                                return;
                            }

                            if(GT_Utility.sUpperCasedCharacters.contains(name.charAt(0))) {
                                GT_Log.log.error("Improperly registered Ore: " + name + " !!!Improperly registered Ore detected!!! This Object does not follow any OreDictionary Convention, because it starts with an uppercased Letter. Please report this to its Modauthor for a fix. If nothing proper is found, a good suggestion for its Name would be '" + modName + ":" + name + "' don't forget to insert the ':' inbetween the Mod ID and OreDict Name, that is the most important part.");
                                GT_Log.ore.println(e + " is invalid due to the first character being uppercased.");
                                return;
                            }
                        } else {
                            if(aPrefix != aPrefix.mPrefixInto) {
                                tName = name.replaceFirst(aPrefix.toString(), aPrefix.mPrefixInto.toString());
                                if(!GT_OreDictUnificator.isRegisteringOres()) {
                                    GT_Log.ore.println(e + " uses a depricated Prefix, and is getting re-registered as " + tName);
                                }

                                GT_OreDictUnificator.registerOre(tName, stack);
                                return;
                            }

                            tName = name.replaceFirst(aPrefix.toString(), "");
                            if(tName.length() > 0) {
                                if(GT_Utility.sUpperCasedCharacters.contains(tName.charAt(0)) || GT_Utility.sNumberedCharacters.contains(tName.charAt(0)) || tName.charAt(0) == 95) {
                                    if(aPrefix.mDontUnificateActively || Block.getBlockFromItem(stack.getItem()) != null) {
                                        GT_OreDictUnificator.addToBlacklist(stack);
                                    }

                                    if(aPrefix.mIsMaterialBased) {
                                        Materials aMaterial = Materials.get(tName);
                                        if(!aPrefix.isIgnored(aMaterial)) {
                                            aPrefix.add(GT_Utility.copyAmount(1L, stack));
                                        }

                                        if(aMaterial != aMaterial.mMaterialInto) {
                                            GT_OreDictUnificator.registerOre(aPrefix, aMaterial.mMaterialInto, stack);
                                            if(!GT_OreDictUnificator.isRegisteringOres()) {
                                                GT_Log.ore.println(e + " uses a deprecated Material and is getting re-registered as " + aPrefix.get(aMaterial.mMaterialInto));
                                            }

                                            return;
                                        }

                                        if(aPrefix.mMaterialAmount >= 0L && aPrefix.mMaterialAmount < 3628800L || aMaterial == Materials.Stone) {
                                            GT_ModHandler.addToRecyclerBlackList(GT_Utility.copyAmount(1L, stack));
                                        }

                                        if(aMaterial == Materials._NULL) {
                                            GT_Log.log.warn("Material Name: " + name + " !!!Unknown Material detected!!! Please report to GregTech Intergalactical for additional compatiblity. This is not an Error, it's just an Information.");
                                            GT_Log.ore.println(e + " uses an unknown Material. Report this to GregTech.");
                                            return;
                                        }

                                        for (Materials tReRegisteredMaterial : aMaterial.mOreReRegistrations) {
                                            GT_OreDictUnificator.registerOre(aPrefix, tReRegisteredMaterial, stack);
                                        }

                                        aMaterial.add(GT_Utility.copyAmount(1L, stack));

                                        switch(aPrefix) {
                                            case battery:
                                                if(aMaterial == Materials.Basic) {
                                                    GT_OreDictUnificator.registerOre("crafting10kEUStore", stack);
                                                    GT_OreDictUnificator.registerOre("calclavia:BATTERY", stack);
                                                }

                                                if(aMaterial == Materials.Advanced) {
                                                    GT_OreDictUnificator.registerOre("crafting100kEUStore", stack);
                                                    GT_OreDictUnificator.registerOre("calclavia:ADVANCED_BATTERY", stack);
                                                }

                                                if(aMaterial == Materials.Lithium) {
                                                    GT_OreDictUnificator.registerOre("crafting100kEUStore", stack);
                                                    GT_OreDictUnificator.registerOre("craftingLiBattery", stack);
                                                    GT_OreDictUnificator.registerOre("calclavia:ADVANCED_BATTERY", stack);
                                                }

                                                if(aMaterial == Materials.Elite) {
                                                    GT_OreDictUnificator.registerOre("crafting1kkEUStore", stack);
                                                }

                                                if(aMaterial == Materials.Master) {
                                                    GT_OreDictUnificator.registerOre("crafting10kkEUStore", stack);
                                                }

                                                if(aMaterial == Materials.Ultimate) {
                                                    GT_OreDictUnificator.registerOre("crafting100kkEUStore", stack);
                                                }
                                                break;
                                            case circuit:
                                                if(aMaterial == Materials.Basic) {
                                                    GT_OreDictUnificator.registerOre("craftingCircuitTier02", stack);
                                                }

                                                if(aMaterial == Materials.Advanced) {
                                                    GT_OreDictUnificator.registerOre("craftingCircuitTier04", stack);
                                                }

                                                if(aMaterial == Materials.Data) {
                                                    GT_OreDictUnificator.registerOre("craftingCircuitTier05", stack);
                                                }

                                                if(aMaterial == Materials.Elite) {
                                                    GT_OreDictUnificator.registerOre("craftingCircuitTier06", stack);
                                                }

                                                if(aMaterial == Materials.Master) {
                                                    GT_OreDictUnificator.registerOre("craftingCircuitTier07", stack);
                                                }

                                                if(aMaterial == Materials.Ultimate) {
                                                    GT_OreDictUnificator.registerOre("craftingCircuitTier08", stack);
                                                }
                                                break;
                                            case crystal:
                                                if(aMaterial == Materials.CertusQuartz) {
                                                    GT_OreDictUnificator.registerOre(OrePrefixes.gem, Materials.CertusQuartz, stack);
                                                }
                                                break;
                                            case gem:
                                                switch(aMaterial) {
                                                    case Lapis:
                                                    case Sodalite:
                                                        GT_OreDictUnificator.registerOre(Dyes.dyeBlue, stack);
                                                        break;
                                                    case Lazurite:
                                                        GT_OreDictUnificator.registerOre(Dyes.dyeCyan, stack);
                                                        break;
                                                    case Chocolate:
                                                        GT_OreDictUnificator.registerOre(Dyes.dyeBrown, stack);
                                                        break;
                                                    case CertusQuartz:
                                                        GT_OreDictUnificator.registerOre(OrePrefixes.crystal, Materials.CertusQuartz, stack);
                                                    case Quartz:
                                                    case Quartzite:
                                                    case NetherQuartz:
                                                        GT_OreDictUnificator.registerOre(GT_OreDictNames.craftingQuartz, stack);
                                                    default:
                                                        break;
                                                }
                                            case lense:
                                                if(aMaterial.mTransparent && aMaterial.mColor != Dyes._NULL) {
                                                    GT_OreDictUnificator.registerOre("craftingLense" + aMaterial.mColor.toString().replaceFirst("dye", ""), stack);
                                                }
                                                break;
                                            case plate:
                                                if(aMaterial == Materials.Steel || aMaterial == Materials.StainlessSteel) {
                                                    GT_OreDictUnificator.registerOre(GT_OreDictNames.craftingPlateSteel, stack);
                                                }

//                                        if(aMaterial == Materials.Plastic) {
//                                           GT_OreDictUnificator.registerOre("sheetPlastic", stack);
//                                        }

                                                if(aMaterial == Materials.Rubber) {
                                                    GT_OreDictUnificator.registerOre("sheetRubber", stack);
                                                }
                                                break;
                                            case cell:
                                                if(aMaterial == Materials.Empty) {
                                                    GT_OreDictUnificator.addToBlacklist(stack);
                                                }
                                                break;
                                            case gearGt:
                                                if(aMaterial == Materials.Steel || aMaterial == Materials.StainlessSteel) {
                                                    GT_OreDictUnificator.registerOre(GT_OreDictNames.craftingGearGTSteel, stack);
                                                }

                                                GT_OreDictUnificator.registerOre(OrePrefixes.gear, aMaterial, stack);
                                                break;
                                            case stick:
                                                if(!GT_RecipeRegistrator.sRodMaterialList.contains(aMaterial)) {
                                                    GT_RecipeRegistrator.sRodMaterialList.add(aMaterial);
                                                }

                                                if(aMaterial == Materials.Wood) {
                                                    GT_OreDictUnificator.addToBlacklist(stack);
                                                }

                                                if(aMaterial == Materials.Tin || aMaterial == Materials.Lead || aMaterial == Materials.SolderingAlloy) {
                                                    GT_OreDictUnificator.registerOre(GT_ToolDictNames.craftingToolSolderingMetal, stack);
                                                }
                                                break;
                                            case dust:
                                                if(aMaterial == Materials.Wood) {
                                                    GT_OreDictUnificator.registerOre("pulpWood", stack);
                                                }

                                                if(aMaterial == Materials.Lapis) {
                                                    GT_OreDictUnificator.registerOre(Dyes.dyeBlue, stack);
                                                }

                                                if(aMaterial == Materials.Lazurite) {
                                                    GT_OreDictUnificator.registerOre(Dyes.dyeCyan, stack);
                                                }

                                                if(aMaterial == Materials.Sodalite) {
                                                    GT_OreDictUnificator.registerOre(Dyes.dyeBlue, stack);
                                                }

                                                if(aMaterial == Materials.YellowLimonite) {
                                                    GT_OreDictUnificator.registerOre(Dyes.dyeYellow, stack);
                                                }

                                                if(aMaterial == Materials.BrownLimonite) {
                                                    GT_OreDictUnificator.registerOre(Dyes.dyeBrown, stack);
                                                }
                                                break;
                                            case ingot:
                                                if(aMaterial == Materials.Rubber) {
                                                    GT_OreDictUnificator.registerOre("itemRubber", stack);
                                                }

                                                if(aMaterial == Materials.Brass && stack.getItemDamage() == 2 && stack.getUnlocalizedName().equals("item.ingotBrass") && (new ItemStack(stack.getItem(), 1, 0)).getUnlocalizedName().contains("red")) {
                                                    GT_OreDictUnificator.set(OrePrefixes.ingot, Materials.RedAlloy, new ItemStack(stack.getItem(), 1, 0));
                                                    GT_OreDictUnificator.set(OrePrefixes.ingot, Materials.BlueAlloy, new ItemStack(stack.getItem(), 1, 1));
                                                    GT_OreDictUnificator.set(OrePrefixes.ingot, Materials.Brass, new ItemStack(stack.getItem(), 1, 2));
                                                }
                                            default: break;
                                        }

                                        if(aPrefix.mIsUnificatable && !aMaterial.mUnificatable) {
                                            return;
                                        }
                                    } else {
                                        aPrefix.add(GT_Utility.copyAmount(1L, stack));
                                    }
                                }
                            } else {
                                if (name.equals("sand")) {
                                    GT_OreDictUnificator.registerOre(OrePrefixes.block, Materials.Sand, stack);
                                } else if (name.equals("dye")) {
                                    GT_OreDictUnificator.registerOre(Dyes.dyeWhite, stack);
                                } else if (!aPrefix.mIsSelfReferencing) {
                                    GT_Log.log.error("WARNING: '" + name + "' is an OreDictionary Name which may cause Problems, due to being a Prefix, please use another one.");
                                    GT_Log.log.error("Private Prefixes are a solution. Please use '" + modName + ":" + name + "' don't forget to insert the ':' inbetween the Mod ID and OreDict Name, that is the most important part.");
                                    GT_Log.ore.println(e + " uses a Prefix as full OreDict Name, and is therefor invalid.");
                                    return;
                                }

                                aPrefix.add(GT_Utility.copyAmount(1L, stack));
                            }

                            switch(aPrefix) {
                                case stoneSmooth:
                                    GT_OreDictUnificator.registerOre("stone", stack);
                                    break;
                                case stoneCobble:
                                    GT_OreDictUnificator.registerOre("cobblestone", stack);
                                    break;
//                            case sheet:
//                               if(tName.equals("sheetPlastic")) {
//                                  GT_OreDictUnificator.registerOre(OrePrefixes.plate, Materials.Plastic, stack);
//                               }
//                               break;
                                case crafting:
                                    if(tName.equals("ToolSolderingMetal")) {
                                        GregTech_API.registerSolderingMetal(stack);
                                    }

                                    if(tName.equals("IndustrialDiamond")) {
                                        GT_OreDictUnificator.addToBlacklist(stack);
                                    }

                                    if(tName.equals("RawMachineTier01")) {
                                        GT_OreDictUnificator.registerOre(GT_OreDictNames.craftingRawMachineTier00, stack);
                                    }

                                    if(tName.equals("CircuitTier02")) {
                                        GT_OreDictUnificator.registerOre(OrePrefixes.circuit, Materials.Basic, stack);
                                    }

                                    if(tName.equals("CircuitTier04")) {
                                        GT_OreDictUnificator.registerOre(OrePrefixes.circuit, Materials.Advanced, stack);
                                    }

                                    if(tName.equals("CircuitTier05")) {
                                        GT_OreDictUnificator.registerOre(OrePrefixes.circuit, Materials.Data, stack);
                                    }

                                    if(tName.equals("CircuitTier06")) {
                                        GT_OreDictUnificator.registerOre(OrePrefixes.circuit, Materials.Elite, stack);
                                    }

                                    if(tName.equals("CircuitTier07")) {
                                        GT_OreDictUnificator.registerOre(OrePrefixes.circuit, Materials.Master, stack);
                                    }

                                    if(tName.equals("CircuitTier08")) {
                                        GT_OreDictUnificator.registerOre(OrePrefixes.circuit, Materials.Ultimate, stack);
                                    }

                                    if(tName.equals("WireCopper")) {
                                        GT_OreDictUnificator.registerOre(OrePrefixes.wire, Materials.Copper, stack);
                                    }
                                    break;
                                case wood:
                                    if(tName.equals("Rubber")) {
                                        GT_OreDictUnificator.registerOre("logRubber", stack);
                                    }
                                    break;
                                case item:
                                    if(tName.equals("Rubber")) {
                                        GT_OreDictUnificator.registerOre(OrePrefixes.ingot, Materials.Rubber, stack);
                                    }
                                default: break;
                            }
                        }

                        GT_Log.ore.println(e);
                        List<OreDictEntry> list = mEvents.get(aPrefix);
                        OreDictEntry entry = OreDictEntry.create(name);
                        ItemStack copy = stack.copy();
                        if (list != null) {
                            int idx = list.indexOf(entry);
                            if (idx >= 0) {
                                list.get(idx).add(modName, copy);
                            } else {
                                entry.add(modName, copy);
                                list.add(entry);
                            }
                        } else {
                            list = new ArrayList<>();
                            entry.add(modName, copy);
                            list.add(entry);
                        }

                        mEvents.put(aPrefix, list);
                    }
                } else {
                    GT_Log.ore.println(e + " is using a private Prefix and is therefor getting ignored properly.");
                }
            }
        } catch(Throwable e) {
            GT_Log.log.catching(e);
        }
    }

    /**
	 * Gets called during the PreLoad-Phase
	 */
    public void registerHandler() {
    	MinecraftForge.EVENT_BUS.register(this);
        for (String tOreName : OreDictionary.getOreNames())
        	for (ItemStack tOreStack : OreDictionary.getOres(tOreName))
        		registerOre(new OreRegisterEvent(tOreName, tOreStack));
	}
    
	/**
	 * Gets called during the PostLoad-Phase
	 */
    public void activateHandler() {
    	mActivated = true;
    	long time = System.currentTimeMillis();
    	
    	ProgressBar bar = ProgressManager.push("Handling OreDict", mEvents.keySet().size(), false);
    	
    	for (Entry<OrePrefixes, List<OreDictEntry>> e : mEvents.entrySet()) {
    		bar.step(String.valueOf(e.getKey()));

    		if (e.getKey() != null) {
    			e.getKey().processOre(e.getValue());
    		} else if (GregTech_API.OREDICT_DEBUG_MODE) {
    			StringBuilder app = new StringBuilder();
    			app.append("WRONG ORE DICTIONARY NAMES DETECTED FOR PREFIX: ");
    			app.append(e.getKey());
    			app.append("\n");
    			
    			for (OreDictEntry entry : e.getValue()) {
    				app.append('\t');
    				app.append(entry.oreDictName);
    				app.append(":\n");
    				
    				for (Entry<ItemStack, String> en : entry.modMap.entrySet()) {
    					app.append("\t\t");
    					app.append("mod: ");
    					app.append(en.getValue());
    					app.append(", stack: ");
    					app.append(en.getKey());
    					app.append('\n');
    				}
    			}
    			
    			app.append("This Objects seems to probably not follow a valid OreDictionary Convention, or I missed a Convention. ");
    			app.append("Please report to GregTech Intergalactical for additional compatiblity. ");
    			app.append("This is not an Error, it's just an Information.");
    			GT_Log.log.warn(app.toString());
    		}
    	}
    	
    	ProgressManager.pop(bar);
    	mEvents.clear();
		GT_Log.log.warn(String.format("Time spent for oredict iterating: %.3f seconds", (System.currentTimeMillis() - time) / 1000.0D));
    }
    
	public void registerUnificationEntries() {
		GregTech_API.sUnification.mConfig.save();
		GregTech_API.sUnification.mConfig.load();
		
		for (Entry<OrePrefixes, List<OreDictEntry>> e : mEvents.entrySet()) {
			if (e.getKey() == null || !e.getKey().mIsUnificatable) {
				continue;
			}
			
			for (OreDictEntry entry : e.getValue()) {
				for (ItemStack ore : entry.ores) {
					GT_OreDictUnificator.addAssociation(entry.oreDictName, ore);
					String modName = entry.modMap.get(ore);
					
					if (GT_OreDictUnificator.isBlacklisted(ore)) {
						continue;
					}
					if (!modName.equals("minecraft") && GregTech_API.sUnification.get(GT_ConfigCategories.specialunificationtargets + "." + modName, entry.oreDictName, false)) {
						GT_OreDictUnificator.set(entry.oreDictName, ore, true, true);
					} else {
						GT_OreDictUnificator.set(entry.oreDictName, ore, false, true);
					}
				}
			}
		}
		
		GregTech_API.sUnification.mConfig.save();
	}

    public static boolean isModOredictEnabled(String modName) {
        boolean useBlacklist = modWhitelist.isEmpty();

        if (requiredMods.contains(modName))
            return true;

        if (useBlacklist) {
            if (modBlacklist.stream()
                    .anyMatch(entry -> modName.toLowerCase().contains(entry.toLowerCase())))
                return false; // Found in Blacklist. Skipping

        } else if (modWhitelist.stream()
                .noneMatch(entry -> modName.toLowerCase().contains(entry.toLowerCase()))) {
            return false; // Not in found Whitelist. Skipping
        }

       return true;
    }

}