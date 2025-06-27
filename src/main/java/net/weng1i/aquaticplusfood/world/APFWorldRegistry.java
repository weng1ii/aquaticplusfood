package net.weng1i.aquaticplusfood.world;

import com.github.alexthe666.citadel.config.biome.SpawnBiomeData;
import net.minecraft.core.Holder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.MobSpawnSettings;
import net.minecraftforge.common.world.ModifiableBiomeInfo;
import net.minecraftforge.fml.common.Mod;
import net.weng1i.aquaticplusfood.Aquaticplusfood;

import net.weng1i.aquaticplusfood.config.BiomeConfig;
import net.weng1i.aquaticplusfood.config.SpawnConfig;
import net.weng1i.aquaticplusfood.entity.APFEntities;
import org.apache.commons.lang3.tuple.Pair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Mod.EventBusSubscriber(modid = Aquaticplusfood.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class APFWorldRegistry {
    private static final Logger LOGGER = LoggerFactory.getLogger("MobSpawnBiomeModifier");

    private static ResourceLocation getBiomeName(Holder<Biome> biome) {
        return biome.unwrap().map((resourceKey) -> resourceKey.location(), (noKey) -> null);
    }

    public static boolean testBiome(Pair<String, SpawnBiomeData> entry, Holder<Biome> biome) {
        try {
            boolean result = BiomeConfig.test(entry, biome, getBiomeName(biome));
            LOGGER.debug("Biome test for {}: {} -> {}", entry.getLeft(), getBiomeName(biome), result);
            return result;
        } catch (Exception e) {
            LOGGER.warn("Could not test biome config for {}, defaulting to no spawns.", entry.getLeft(), e);
            return false;
        }
    }

    public static void addBiomeSpawns(Holder<Biome> biome, ModifiableBiomeInfo.BiomeInfo.Builder builder) {
        ResourceLocation biomeId = getBiomeName(biome);
        if (biomeId == null) return;

        // Debug: Log weights at runtime
        LOGGER.debug("Biome {}: MutantCucumber weight = {}",
                biomeId, SpawnConfig.mutantcucumberSpawnWeight);

        // Anglerfish spawn
        if (testBiome(BiomeConfig.mutantcucumber, biome) && SpawnConfig.mutantcucumberSpawnWeight > 0) {
            LOGGER.info("Adding MutantCucumber to biome: {}", biomeId);
            builder.getMobSpawnSettings().getSpawner(MobCategory.MONSTER)
                    .add(new MobSpawnSettings.SpawnerData(APFEntities.MUTANTCUCUMBER.get(), SpawnConfig.mutantcucumberSpawnWeight, 0, 1));
        } else {
            LOGGER.info("Biome {} is not valid for MutantCucumber or spawn weight is 0", biomeId);
        }

    }
}
