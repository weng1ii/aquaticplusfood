package net.weng1i.aquaticplusfood.datagen;

import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemRandomChanceCondition;
import net.minecraftforge.common.data.GlobalLootModifierProvider;
import net.minecraftforge.common.loot.LootTableIdCondition;
import net.weng1i.aquaticplusfood.Aquaticplusfood;
import net.weng1i.aquaticplusfood.item.APFItems;
import net.weng1i.aquaticplusfood.loot.AddItemModifier;

public class APFGlobalLootModifiersProvider extends GlobalLootModifierProvider {

    public APFGlobalLootModifiersProvider(PackOutput output) {
        super(output, Aquaticplusfood.MOD_ID);
    }

    @Override
    protected void start() {
        /*
        add("pine_cone_from_grass", new AddItemModifier(new LootItemCondition[] {
                LootItemBlockStatePropertyCondition.hasBlockStateProperties(Blocks.GRASS).build(),
                LootItemRandomChanceCondition.randomChance(0.35f).build()}, ModItems.PINE_CONE.get()));
         */
        add("cucumber_from_mutant_cucumber", new AddItemModifier(new LootItemCondition[]{
                new LootTableIdCondition.Builder(new ResourceLocation("aquaticplusfood", "entity/aquaticplusfood/mutantcucumber")).build()
        }, APFItems.CUCUMBER.get()));

                //LootItemRandomChanceCondition.randomChance(0.35f).build()}, APFItems.CUCUMBER.get()));
        /*
        add("metal_detector_from_jungle_temples", new AddItemModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(new ResourceLocation("chests/jungle_temple")).build() }, ModItems.METAL_DETECTOR.get()));
        */
    }
}
