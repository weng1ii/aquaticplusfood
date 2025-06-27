package net.weng1i.aquaticplusfood.datagen;

import net.minecraft.data.PackOutput;
import net.minecraft.data.loot.LootTableProvider;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.weng1i.aquaticplusfood.datagen.loot.APFBlockLootTables;

import java.util.List;
import java.util.Set;

public class APFLootTableProvider {
    public static LootTableProvider create(PackOutput output) {
        return new LootTableProvider(output, Set.of(), List.of(
                new LootTableProvider.SubProviderEntry(APFBlockLootTables::new, LootContextParamSets.BLOCK)
        ));
    }
}
