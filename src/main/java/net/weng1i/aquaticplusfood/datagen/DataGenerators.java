package net.weng1i.aquaticplusfood.datagen;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.weng1i.aquaticplusfood.Aquaticplusfood;

import java.util.concurrent.CompletableFuture;

@Mod.EventBusSubscriber(modid = Aquaticplusfood.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class DataGenerators {
    @SubscribeEvent
    public static void gatherData(GatherDataEvent event) {
        DataGenerator generator = event.getGenerator();
        PackOutput packOutput = generator.getPackOutput();
        ExistingFileHelper existingFileHelper = event.getExistingFileHelper();
        CompletableFuture<HolderLookup.Provider> lookupProvider = event.getLookupProvider();

        generator.addProvider(event.includeServer(), new APFRecipeProvider(packOutput));
        generator.addProvider(event.includeServer(), APFLootTableProvider.create(packOutput));

        generator.addProvider(event.includeClient(), new APFBlockStateProvider(packOutput, existingFileHelper));
        generator.addProvider(event.includeClient(), new APFItemModelProvider(packOutput, existingFileHelper));

        APFBlockTagGenerator blockTagGenerator = generator.addProvider(event.includeServer(),
                new APFBlockTagGenerator(packOutput, lookupProvider, existingFileHelper));
        generator.addProvider(event.includeServer(), new APFItemTagGenerator(packOutput, lookupProvider, blockTagGenerator.contentsGetter(), existingFileHelper));

        generator.addProvider(event.includeServer(), new APFGlobalLootModifiersProvider(packOutput));
    }
}