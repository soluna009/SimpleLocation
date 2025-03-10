package jp.outlook.soluna009.simplelocationmod.enums;

import java.awt.Color;
import java.util.Arrays;
import java.util.List;

public enum HeadLookupStatus
{
    NONE(0, 30, new Color(255, 255, 255, (60 * 255) / 100).getRGB()),
    NORMAL(NONE.high, 45, new Color(255, 255, 255, (60 * 255) / 100).getRGB()),
    WARNING(NORMAL.high, 85, new Color(255, 255, 0, (80 * 255) / 100).getRGB()),
    DANGER(WARNING.high, 90, Color.RED.getRGB()) {
        @Override
        public boolean isIn(final double value)
        {
            return (WARNING.high <= value) && (value <= DANGER.high);
        }
    };

    private final int min;
    private final int high;
    private final int color;
    public static final List<HeadLookupStatus> LIST = Arrays.asList(values());

    HeadLookupStatus(final int range_min, final int range_high, final int draw_color)
    {
        min = range_min;
        high = range_high;
        color = draw_color;
    }

    public int getColor()
    {
        return color;
    }

    public boolean isIn(final double value)
    {
        return (min <= value) && (value < high);
    }
}