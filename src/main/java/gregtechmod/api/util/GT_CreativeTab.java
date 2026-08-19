package gregtechmod.api.util;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import gregtechmod.api.enums.GT_Items;

public class GT_CreativeTab extends CreativeTabs {

    /**
     * Will create tab with translate key itemGroup. + categoryTranslateKey
     * 
     * @param categoryTranslateKey
     */
    public GT_CreativeTab(String categoryTranslateKey) {
        super(categoryTranslateKey);
    }

    @Override
    public ItemStack getIconItemStack() {
        return GT_Items.Tool_Cheat.getUndamaged(1, new ItemStack(Blocks.iron_block, 1));
    }

    @Override
    public Item getTabIconItem() {
        return null;
    }
}
