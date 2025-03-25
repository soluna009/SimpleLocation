package jp.outlook.soluna009.simplelocationmod;

import com.mojang.blaze3d.vertex.PoseStack;
import jp.outlook.soluna009.simplelocationmod.entity.Config;
import jp.outlook.soluna009.simplelocationmod.logic.InterfaceSimpleLocation;
import jp.outlook.soluna009.simplelocationmod.logic.LogicSimpleLocation;
import jp.outlook.soluna009.simplelocationmod.logic.UserKeyMapping;
import net.minecraft.client.Minecraft;
import net.minecraftforge.client.event.CustomizeGuiOverlayEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(SimpleLocationMod.MOD_ID)
@Mod.EventBusSubscriber(modid = SimpleLocationMod.MOD_ID)
public class SimpleLocationMod
{
    public static final String MOD_ID = "simplelocationmod";
    public static final String MY_ID = "jp.outlook.soluna009.simple_location.";
    public static final String CONFIG_FILE = "jp/outlook/soluna009/simpleLocation-client.toml";
    public static final InterfaceSimpleLocation simpleLocation = new LogicSimpleLocation(Minecraft.getInstance());

    public SimpleLocationMod(FMLJavaModLoadingContext context)
    {
        context.registerConfig(ModConfig.Type.CLIENT, Config.SPEC, CONFIG_FILE);
    }

    @SubscribeEvent
    public static void onLogout(PlayerEvent.PlayerLoggedOutEvent event)
    {
        Config.updateConfigValues(simpleLocation);
    }

    @SubscribeEvent
    public static void onOverlay(CustomizeGuiOverlayEvent event)
    {
        if (!simpleLocation.isActive()) return;
        simpleLocation.setDrawScreen(event.getGuiGraphics());
        final PoseStack pose_stack = simpleLocation.getDrawScreen().pose();
        pose_stack.pushPose();
        if (simpleLocation.isShowLocation()) simpleLocation.drawLocationInfo();
        simpleLocation.drawHeadAngleArrows();
        pose_stack.popPose();
    }

    @SubscribeEvent
    public static void onClientTick(TickEvent.ClientTickEvent event) {
        if (event.phase != TickEvent.Phase.END) return;
        boolean disable_location_action = false;
        boolean disable_head_info_action = false;

        while (UserKeyMapping.SHOW_LOCATION_KEY.get().consumeClick()) {
            if (disable_location_action) continue;
            simpleLocation.filipShowLocation();
            disable_location_action = true;
        }

        while (UserKeyMapping.LOCATION_POSITION_KEY.get().consumeClick()) {
            if (disable_location_action || !simpleLocation.isShowLocation() || simpleLocation.hasFrontPosition()) continue;
            simpleLocation.cycleLocationPosition();
            disable_location_action = true;
        }

        while (UserKeyMapping.SHOW_CENTER_KEY.get().consumeClick()) {
            if (disable_location_action || !simpleLocation.isShowLocation()) continue;
            simpleLocation.showCenterKeyAction();
            disable_location_action = true;
        }

        while (UserKeyMapping.SHOW_HEAD_INFO.get().consumeClick()) {
            if (disable_head_info_action) continue;
            simpleLocation.filipShowHeadInfo();
            disable_head_info_action = true;
        }
    }
}