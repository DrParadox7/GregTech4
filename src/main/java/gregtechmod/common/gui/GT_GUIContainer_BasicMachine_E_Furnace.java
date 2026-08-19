package gregtechmod.common.gui;

import net.minecraft.entity.player.InventoryPlayer;

import gregtechmod.api.gui.GT_GUIContainer_BasicMachine;
import gregtechmod.api.interfaces.IGregTechTileEntity;

public class GT_GUIContainer_BasicMachine_E_Furnace extends GT_GUIContainer_BasicMachine {

    public GT_GUIContainer_BasicMachine_E_Furnace(InventoryPlayer aInventoryPlayer, IGregTechTileEntity aTileEntity,
        String aName, String aTextureFile) {
        super(aInventoryPlayer, aTileEntity, aName, aTextureFile);
    }
}
