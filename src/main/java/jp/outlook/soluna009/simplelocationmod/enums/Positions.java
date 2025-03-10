package jp.outlook.soluna009.simplelocationmod.enums;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.world.phys.Vec3;

import java.awt.Color;

/**
 * 表示場所列挙体。
 */
public enum Positions
{
    /** 左上に表示。 */
    HIGH_LEFT {
        /**
         * {@inheritDoc}
         */
        @Override
        public void drawInfo(final GuiGraphics gui, final Minecraft minecraft, final Vec3 player_pos, final int center_x, final int center_y)
        {
            gui.drawString(minecraft.font, Positions.standardInfoText(player_pos), 0, 0, DRAW_COLOR, false);
        }
    },
    /** 右上に表示。*/
    HIGH_RIGHT {
        /**
         * {@inheritDoc}
         */
        @Override
        public void drawInfo(final GuiGraphics gui, final Minecraft minecraft, final Vec3 player_pos, final int center_x, final int center_y)
        {
            final String view_text = Positions.standardInfoText(player_pos);
            gui.drawString(minecraft.font, view_text, gui.guiWidth() - minecraft.font.width(view_text) - 1, 0, DRAW_COLOR, false);
        }
    },
    /** 右下に表示。 */
    LOW_RIGHT {
        /**
         * {@inheritDoc}
         */
        @Override
        public void drawInfo(final GuiGraphics gui, final Minecraft minecraft, final Vec3 player_pos, final int center_x, final int center_y)
        {
            final String view_text = Positions.standardInfoText(player_pos);
            gui.drawString(minecraft.font, view_text, gui.guiWidth() - minecraft.font.width(view_text) - 1, gui.guiHeight() - minecraft.font.lineHeight, DRAW_COLOR, false);
        }
    },
    /** 左下に表示。 */
    LOW_LEFT {
        /**
         * {@inheritDoc}
         */
        @Override
        public void drawInfo(final GuiGraphics gui, final Minecraft minecraft, final Vec3 player_pos, final int center_x, final int center_y)
        {
            final String view_text = Positions.standardInfoText(player_pos);
            gui.drawString(minecraft.font, view_text, 0, gui.guiHeight() - minecraft.font.lineHeight, DRAW_COLOR, false);
        }
    },
    /** 中央に表示。 */
    CENTER {
        /**
         * {@inheritDoc}
         */
        @Override
        public void drawInfo(final GuiGraphics gui, final Minecraft minecraft, final Vec3 player_pos, final int center_x, final int center_y)
        {
            final String x_text = String.format("X:%.3f", player_pos.x);
            gui.drawString(minecraft.font, x_text, center_x - minecraft.font.width(x_text) - 8 , center_y - VERTICAL_BLANK_OFFSET - minecraft.font.lineHeight, DRAW_COLOR, false);

            final String z_text = String.format("Z:%.3f", player_pos.z);
            gui.drawString(minecraft.font, z_text, center_x, center_y - VERTICAL_BLANK_OFFSET - minecraft.font.lineHeight, DRAW_COLOR, false);

            final String y_text = String.format("Y:%.3f", player_pos.y);
            gui.drawString(minecraft.font, y_text, center_x - minecraft.font.width(y_text) / 2, center_y + VERTICAL_BLANK_OFFSET, DRAW_COLOR, false);
        }
    };

    /**
     * 標準座標表示文字列作成。
     *
     * @param player_position プレイヤー3次元ベクター。
     * @return 表示文字列。
     */
    private static String standardInfoText(Vec3 player_position)
    {
        return String.format("X:%.3f Y:%.3f Z:%.3f", player_position.x, player_position.y, player_position.z);
    }

    // 表示文字カラー。
    private static final int DRAW_COLOR = Color.WHITE.getRGB();
    // センター表示時上下空きオフセット。
    private static final int VERTICAL_BLANK_OFFSET = 10;

    /**
     * 座標表示処理。
     *
     * @param gui        書き込み先画面。
     * @param minecraft  Minecraftインスタンス。
     * @param player_pos プレイヤーの三次元位置。
     * @param center_x   中心座標X。
     * @param center_y   中心座標Y。
     */
    public abstract void drawInfo(final GuiGraphics gui, final Minecraft minecraft, final Vec3 player_pos, final int center_x, final int center_y);
}