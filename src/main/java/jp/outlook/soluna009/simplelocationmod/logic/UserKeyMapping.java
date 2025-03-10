package jp.outlook.soluna009.simplelocationmod.logic;

import jp.outlook.soluna009.simplelocationmod.SimpleLocationMod;
import net.minecraft.client.KeyMapping;
import net.minecraftforge.client.event.RegisterKeyMappingsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.jarjar.nio.util.Lazy;
import org.lwjgl.glfw.GLFW;

import java.util.stream.Stream;

@Mod.EventBusSubscriber(modid = SimpleLocationMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class UserKeyMapping
{
    public static final Lazy<KeyMapping> SHOW_LOCATION_KEY = Lazy.of(UserKeyMapping::makeShowLocationKeymap);
    public static final Lazy<KeyMapping> LOCATION_POSITION_KEY = Lazy.of(UserKeyMapping::makeLocationPositionKeymap);
    public static final Lazy<KeyMapping> SHOW_CENTER_KEY = Lazy.of(UserKeyMapping::makeShowCenter);
    public static final Lazy<KeyMapping> SHOW_HEAD_INFO = Lazy.of(UserKeyMapping::makeHeadInfo);

    @SubscribeEvent
    public static void registerKeyMappings(RegisterKeyMappingsEvent event)
    {
        Stream.of(SHOW_LOCATION_KEY, LOCATION_POSITION_KEY, SHOW_CENTER_KEY, SHOW_HEAD_INFO).map(Lazy::get).forEach(event::register);
    }

    private static KeyMapping makeShowLocationKeymap()
    {
        return new NumberSortKeyMapping("show_location", GLFW.GLFW_KEY_I, 0);
    }

    private static KeyMapping makeLocationPositionKeymap()
    {
        return new NumberSortKeyMapping("change_location", GLFW.GLFW_KEY_O, 1);
    }

    private static KeyMapping makeShowCenter()
    {
        return new NumberSortKeyMapping("show_center", GLFW.GLFW_KEY_U, 3);
    }

    private static KeyMapping makeHeadInfo()
    {
        return new NumberSortKeyMapping("show_head_info", GLFW.GLFW_KEY_K, 4);
    }
}