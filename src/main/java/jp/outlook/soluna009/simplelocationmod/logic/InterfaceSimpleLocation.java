package jp.outlook.soluna009.simplelocationmod.logic;

import net.minecraft.client.gui.GuiGraphics;

public interface InterfaceSimpleLocation
{
    int POSITION_NO_SELECT = -1;

    GuiGraphics getDrawScreen();

    void setDrawScreen(final GuiGraphics draw_screen);

    boolean isActive();

    void filipShowLocation();

    boolean hasFrontPosition();

    void cycleLocationPosition();

    void showCenterKeyAction();

    void filipShowHeadInfo();

    boolean isShowLocation();

    void drawLocationInfo();

    void drawHeadAngleArrows();

    void setShowLocation(boolean showLocation);

    int getLocationPos();

    void setLocationPos(int locationPos);

    int getFrontPos();

    void setFrontPos(int frontPos);

    boolean isShowHeadInfo();

    void setShowHeadInfo(boolean showHeadInfo);
}