package net.weng1i.aquaticplusfood.world;

import com.mojang.serialization.Codec;
import net.minecraft.core.Holder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.biome.Biome;
import net.minecraftforge.common.world.BiomeModifier;
import net.minecraftforge.common.world.ModifiableBiomeInfo;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.weng1i.aquaticplusfood.Aquaticplusfood;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class APFSpawnBiomeModifier implements BiomeModifier {
    private static final RegistryObject<Codec<? extends BiomeModifier>> SERIALIZER = RegistryObject.create(new ResourceLocation(Aquaticplusfood.MOD_ID, "am_mob_spawns"), ForgeRegistries.Keys.BIOME_MODIFIER_SERIALIZERS, Aquaticplusfood.MOD_ID);
    private static final Logger LOGGER = LoggerFactory.getLogger(APFSpawnBiomeModifier.class);

    public APFSpawnBiomeModifier() {

    }

    public void modify(Holder<Biome> biome, Phase phase, ModifiableBiomeInfo.BiomeInfo.Builder builder) {
        if (phase == Phase.ADD) {
            ResourceLocation biomeId = biome.unwrapKey().map(k -> k.location()).orElse(ResourceLocation.tryParse("unknown"));
            LOGGER.info("[APFSpawnBiomeModifier] Modifying biome: {}", biomeId);

            APFWorldRegistry.addBiomeSpawns(biome, builder);
        }
    }

    public Codec<? extends BiomeModifier> codec() {
        return (Codec)SERIALIZER.get();
    }

    public static Codec<APFSpawnBiomeModifier> makeCodec() {
        return Codec.unit(APFSpawnBiomeModifier::new);
    }
}
