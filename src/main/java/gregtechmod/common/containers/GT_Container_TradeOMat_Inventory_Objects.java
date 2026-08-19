package gregtechmod.common.containers;

import net.minecraft.entity.player.*;
import net.minecraft.inventory.*;

import gregtechmod.api.gui.*;
import gregtechmod.api.interfaces.*;

public class GT_Container_TradeOMat_Inventory_Objects extends GT_ContainerMetaTile_Machine {

    public GT_Container_TradeOMat_Inventory_Objects(final InventoryPlayer aInventoryPlayer,
        final IGregTechTileEntity aTileEntity) {
        super(aInventoryPlayer, aTileEntity);
    }

    @Override
    public void addSlots(final InventoryPlayer aInventoryPlayer) {
        for (int y = 0; y < 3; ++y) {
            for (int x = 0; x < 9; ++x) {
                this.addSlotToContainer(new Slot((IInventory) this.mTileEntity, x + y * 9, 8 + x * 18, 23 + y * 18));
            }
        }
    }

    @Override
    public int getSlotCount() {
        return 27;
    }

    @Override
    public int getShiftClickSlotCount() {
        return 27;
    }
}
