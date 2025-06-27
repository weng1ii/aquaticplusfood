package net.weng1i.aquaticplusfood.config;

import com.github.alexthe666.citadel.config.biome.BiomeEntryType;
import com.github.alexthe666.citadel.config.biome.SpawnBiomeData;

public class BiomesGroup {
    public static final SpawnBiomeData EMPTY = new SpawnBiomeData();

    public static final SpawnBiomeData MUTANTCUCUMBER = new SpawnBiomeData()
            .addBiomeEntry(BiomeEntryType.BIOME_TAG, false, "forge:is_cold/overworld", 0)
            .addBiomeEntry(BiomeEntryType.BIOME_TAG, false, "forge:is_hot/overworld", 1)
            .addBiomeEntry(BiomeEntryType.BIOME_TAG, false, "forge:is_/overworld", 0);

}
