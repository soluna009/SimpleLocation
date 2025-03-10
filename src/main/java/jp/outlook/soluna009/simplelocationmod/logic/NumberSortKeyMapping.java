package jp.outlook.soluna009.simplelocationmod.logic;

import jp.outlook.soluna009.simplelocationmod.SimpleLocationMod;
import net.minecraft.client.KeyMapping;
import org.jetbrains.annotations.NotNull;

public class NumberSortKeyMapping extends KeyMapping
{
    private static final String KEYMAPPING_HEAD = SimpleLocationMod.MY_ID + "key.";
    private static final String TEXT_ID_LOCATION_CATEGORY = SimpleLocationMod.MY_ID + "categories.misc";

    private final int sortNumber;

    public NumberSortKeyMapping(String key_name, int key_code, final int sort_number)
    {
        super(KEYMAPPING_HEAD + key_name, key_code, TEXT_ID_LOCATION_CATEGORY);
        sortNumber = sort_number;
    }

    @Override
    public int compareTo(@NotNull KeyMapping compare_key)
    {
        if (!(compare_key instanceof NumberSortKeyMapping)) return super.compareTo(compare_key);
        return Integer.compare(sortNumber, ((NumberSortKeyMapping)compare_key).sortNumber);
    }
}