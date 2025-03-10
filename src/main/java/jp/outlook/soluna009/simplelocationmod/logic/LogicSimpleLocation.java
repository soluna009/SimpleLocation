package jp.outlook.soluna009.simplelocationmod.logic;

import jp.outlook.soluna009.simplelocationmod.enums.HeadLookupStatus;
import jp.outlook.soluna009.simplelocationmod.enums.Positions;
import net.minecraft.client.CameraType;
import net.minecraft.client.Minecraft;
import net.minecraft.client.Options;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.world.phys.Vec3;

import java.util.Arrays;
import java.util.List;

import static jp.outlook.soluna009.simplelocationmod.enums.HeadLookupStatus.LIST;
import static jp.outlook.soluna009.simplelocationmod.enums.HeadLookupStatus.NONE;

public class LogicSimpleLocation implements InterfaceSimpleLocation
{
    private record ArrowOffset(int begin_x, int end_x, int end_y) {}

    private static final int MAX_POSITION_MASK = 0x03;
    private static final List<ArrowOffset> ARROW_OFFSET_ARRAY = Arrays.asList(
            new ArrowOffset(-1,1, 10),
            new ArrowOffset(-4,-1, 8),
            new ArrowOffset(1,+4, 8),
            new ArrowOffset(-6,-2, 6),
            new ArrowOffset(1,6, 6)
    );

    private final Minecraft minecraft;
    private final Options options;

    private GuiGraphics drawScreen;

    private boolean showLocation;
    private int locationPos;
    private int frontPos;
    private boolean showHeadInfo;

    private int centerX;
    private int centerY;
    private Vec3 playerPosition;

    private int arrowColor;

    public LogicSimpleLocation(Minecraft game)
    {
        minecraft = game;
        options = minecraft.options;
    }

    @Override
    public GuiGraphics getDrawScreen()
    {
        return drawScreen;
    }

    @Override
    public void setDrawScreen(final GuiGraphics draw_screen)
    {
        drawScreen = draw_screen;
        centerX = drawScreen.guiWidth() / 2 - 1;
        centerY = drawScreen.guiHeight() / 2 - 1;
        playerPosition = minecraft.player.position();
    }

    @Override
    public boolean isActive()
    {
        return minecraft.player != null;
    }

    @Override
    public void filipShowLocation()
    {
        showLocation ^= true;
    }

    @Override
    public boolean hasFrontPosition()
    {
        return frontPos != POSITION_NO_SELECT;
    }

    @Override
    public void cycleLocationPosition()
    {
        locationPos = ++locationPos & MAX_POSITION_MASK;
    }

    @Override
    public void showCenterKeyAction()
    {
        if (frontPos == POSITION_NO_SELECT) {
            frontPos = locationPos;
            locationPos = Positions.CENTER.ordinal();
        } else {
            locationPos = frontPos;
            frontPos = POSITION_NO_SELECT;
        }
    }

    @Override
    public void filipShowHeadInfo()
    {
        showHeadInfo ^= true;
    }

    @Override
    public void drawLocationInfo()
    {
        Positions.values()[locationPos].drawInfo(drawScreen, minecraft, playerPosition, centerX, centerY);
    }

    private void drawUpSide(final ArrowOffset arrow_offset)
    {
        drawScreen.hLine(centerX + arrow_offset.begin_x, centerX + arrow_offset.end_x, centerY - arrow_offset.end_y, arrowColor);
    }

    private void drawDownSide(final ArrowOffset arrow_offset)
    {
        drawScreen.hLine(centerX + arrow_offset.begin_x, centerX + arrow_offset.end_x, centerY + arrow_offset.end_y, arrowColor);
    }

    @Override
    public void drawHeadAngleArrows()
    {
        if (!showHeadInfo || (options.getCameraType() != CameraType.FIRST_PERSON)) return;
        final int x_rot = (int)-minecraft.player.getXRot();
        final int abs_x_rot = Math.abs(x_rot);
        final HeadLookupStatus head_lookup = LIST.stream().filter(h -> h.isIn(abs_x_rot)).findFirst().orElse(NONE);
        arrowColor = minecraft.player.isFallFlying() || minecraft.player.isSwimming() ? head_lookup.getColor(): NONE.getColor();
        // 上下方向角度値表示。
        drawScreen.drawString(minecraft.font, String.format("%d", x_rot), centerX + 32, centerY - minecraft.font.lineHeight / 2, arrowColor, false);
        // 上下方向矢印表示。
        if (head_lookup == NONE) return;
        if (x_rot >= 0.0) ARROW_OFFSET_ARRAY.forEach(this::drawUpSide); // Up Arrow
        else ARROW_OFFSET_ARRAY.forEach(this::drawDownSide); // Down Arrow
    }

    @Override
    public boolean isShowLocation()
    {
        return showLocation;
    }

    @Override
    public void setShowLocation(boolean show_lLocation)
    {
        showLocation = show_lLocation;
    }

    @Override
    public int getLocationPos()
    {
        return locationPos;
    }

    @Override
    public void setLocationPos(int location_pos)
    {
        locationPos = location_pos;
    }

    @Override
    public int getFrontPos()
    {
        return frontPos;
    }

    @Override
    public void setFrontPos(int front_pos)
    {
        frontPos = front_pos;
    }

    @Override
    public boolean isShowHeadInfo()
    {
        return showHeadInfo;
    }

    @Override
    public void setShowHeadInfo(boolean show_head_info)
    {
        showHeadInfo = show_head_info;
    }
}