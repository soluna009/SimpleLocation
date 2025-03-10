package jp.outlook.soluna009.simplelocationmod.entity;

import jp.outlook.soluna009.simplelocationmod.SimpleLocationMod;
import jp.outlook.soluna009.simplelocationmod.enums.Positions;
import jp.outlook.soluna009.simplelocationmod.logic.InterfaceSimpleLocation;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.config.ModConfigEvent;

import static jp.outlook.soluna009.simplelocationmod.logic.InterfaceSimpleLocation.POSITION_NO_SELECT;

@Mod.EventBusSubscriber(modid = SimpleLocationMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class Config
{
    private static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();

    private static final ForgeConfigSpec.BooleanValue SHOW_LOCATION = BUILDER.comment("簡易座標表示ON/OFF状態")
            .define("showLocation", true);

    private static final ForgeConfigSpec.IntValue LOCATION_POS = BUILDER.comment("表示場所")
            .defineInRange("locationPos", 0, 0, Positions.values().length - 1);

    private static final ForgeConfigSpec.IntValue FRONT_POS = BUILDER.comment("中央表示を行う前の表示位置")
            .defineInRange("frontPos", POSITION_NO_SELECT, POSITION_NO_SELECT, Positions.values().length - 1);

    private static final ForgeConfigSpec.BooleanValue SHOW_HEAD_INFO = BUILDER.comment("上下方向情報表示ON/OFF状態")
            .define("showHeadInfo", true);

    public static final ForgeConfigSpec SPEC = BUILDER.build();

    @SubscribeEvent
    public static void onLoad(final ModConfigEvent event)
    {
        SimpleLocationMod.simpleLocation.setShowLocation(SHOW_LOCATION.get());
        SimpleLocationMod.simpleLocation.setLocationPos(LOCATION_POS.get());
        SimpleLocationMod.simpleLocation.setFrontPos(FRONT_POS.get());
        SimpleLocationMod.simpleLocation.setShowHeadInfo(SHOW_HEAD_INFO.get());
    }

    public static void updateConfigValues(final InterfaceSimpleLocation simple_location)
    {
        SHOW_LOCATION.set(simple_location.isShowLocation());
        LOCATION_POS.set(simple_location.getLocationPos());
        FRONT_POS.set(simple_location.getFrontPos());
        SHOW_HEAD_INFO.set(simple_location.isShowHeadInfo());
    }
}