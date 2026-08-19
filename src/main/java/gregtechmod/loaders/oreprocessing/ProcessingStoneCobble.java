package gregtechmod.loaders.oreprocessing;

import java.util.List;

import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;

import gregtechmod.api.enums.Materials;
import gregtechmod.api.enums.OrePrefixes;
import gregtechmod.api.interfaces.IOreRecipeRegistrator;
import gregtechmod.api.util.OreDictEntry;
import gregtechmod.common.recipe.RecipeEntry;
import gregtechmod.common.recipe.RecipeEntry.Match;
import gregtechmod.common.recipe.RecipeMaps;

public class ProcessingStoneCobble implements IOreRecipeRegistrator {

    public ProcessingStoneCobble() {
        OrePrefixes.stoneCobble.add(this);
    }

    @Override
    public void registerOre(OrePrefixes aPrefix, List<OreDictEntry> entries) {
        for (OreDictEntry entry : entries) {
            if (this.isExecutable(aPrefix, this.getMaterial(aPrefix, entry))) RecipeMaps.ASSEMBLING.factory()
                .EUt(1)
                .duration(400)
                .input(OrePrefixes.stick, Materials.Wood)
                .input(RecipeEntry.fromStacks(entry.ores, Match.STRICT))
                .output(new ItemStack(Blocks.lever, 1))
                .buildAndRegister();
        }
    }
}
