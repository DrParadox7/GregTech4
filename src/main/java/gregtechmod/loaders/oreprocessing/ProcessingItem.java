package gregtechmod.loaders.oreprocessing;

import java.util.List;

import gregtechmod.api.GregTech_API;
import gregtechmod.api.enums.Materials;
import gregtechmod.api.enums.OrePrefixes;
import gregtechmod.api.interfaces.IOreRecipeRegistrator;
import gregtechmod.api.util.GT_Log;
import gregtechmod.api.util.GT_RecipeRegistrator;
import gregtechmod.api.util.GT_Utility;
import gregtechmod.api.util.OreDictEntry;
import net.minecraft.item.ItemStack;

public class ProcessingItem implements IOreRecipeRegistrator {

    public ProcessingItem() {
        OrePrefixes.item.add(this);
    }

    public void registerOre(OrePrefixes aPrefix, List<OreDictEntry> entries) {
        for (OreDictEntry entry : entries) {
            if (this.isExecutable(aPrefix, this.getMaterial(aPrefix, entry))) {
                for (ItemStack aStack : entry.ores) {

                    switch (entry.oreDictName) {

                        case "itemManganese":
                            GT_RecipeRegistrator.registerBasicReverseMaceratingAndSmelting(GT_Utility.copyAmount(1, aStack), Materials.Manganese, GregTech_API.MATERIAL_UNIT);
                            break;
                        case "itemSalt":
                            GT_RecipeRegistrator.registerBasicReverseMaceratingAndSmelting(GT_Utility.copyAmount(1, aStack), Materials.Salt, GregTech_API.MATERIAL_UNIT);
                            break;
                        case "itemMagnesium":
                            GT_RecipeRegistrator.registerBasicReverseMaceratingAndSmelting(GT_Utility.copyAmount(1, aStack), Materials.Magnesium, GregTech_API.MATERIAL_UNIT);
                            break;
                        case "itemPhosphor":
                        case "itemPhosphorite":
                            GT_RecipeRegistrator.registerBasicReverseMaceratingAndSmelting(GT_Utility.copyAmount(1, aStack), Materials.Phosphorus, GregTech_API.MATERIAL_UNIT);
                            break;
                        case "itemSulfur":
                            GT_RecipeRegistrator.registerBasicReverseMaceratingAndSmelting(GT_Utility.copyAmount(1, aStack), Materials.Sulfur, GregTech_API.MATERIAL_UNIT);
                            break;
                        case "itemSaltpeter":
                            GT_RecipeRegistrator.registerBasicReverseMaceratingAndSmelting(GT_Utility.copyAmount(1, aStack), Materials.Saltpeter, GregTech_API.MATERIAL_UNIT);
                            break;
                        case "itemUranium":
                            GT_RecipeRegistrator.registerBasicReverseMaceratingAndSmelting(GT_Utility.copyAmount(1, aStack), Materials.Uranium, GregTech_API.MATERIAL_UNIT);
                            break;
                        case "itemAluminium":
                        case "itemAluminum":
                            GT_RecipeRegistrator.registerBasicReverseMaceratingAndSmelting(GT_Utility.copyAmount(1, aStack), Materials.Aluminium, GregTech_API.MATERIAL_UNIT);
                            break;
                        case "itemRubber":
                            GT_RecipeRegistrator.registerBasicReverseMaceratingAndSmelting(GT_Utility.copyAmount(1, aStack), Materials.Rubber, GregTech_API.MATERIAL_UNIT);
                            break;

                        default:
                            if(GregTech_API.OREDICT_DEBUG_MODE)
                                GT_Log.log.warn("Item Name: " + entry.oreDictName + " !!!Unknown Item detected!!! Please report to GregTech Intergalactical for additional compatiblity. This is not an Error, it\'s just an Information.");
                    }
                }
            }
        }
    }
}
