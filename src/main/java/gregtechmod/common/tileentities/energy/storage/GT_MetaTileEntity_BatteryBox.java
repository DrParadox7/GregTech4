package gregtechmod.common.tileentities.energy.storage;

import net.minecraft.entity.player.EntityPlayer;

import gregtechmod.api.interfaces.IGregTechTileEntity;
import gregtechmod.api.metatileentity.MetaTileEntity;

public class GT_MetaTileEntity_BatteryBox extends GT_MetaTileEntity_ChargerBox {

    public GT_MetaTileEntity_BatteryBox(int aID, String mName) {
        super(aID, mName);
    }

    public GT_MetaTileEntity_BatteryBox() {

    }

    @Override
    public int getInvSize() {
        return 4;
    }

    @Override
    public void onRightclick(EntityPlayer aPlayer) {
        getBaseMetaTileEntity().openGUI(aPlayer, 97);
    }

    @Override
    public MetaTileEntity newMetaEntity(IGregTechTileEntity aTileEntity) {
        return new GT_MetaTileEntity_BatteryBox();
    }
}
