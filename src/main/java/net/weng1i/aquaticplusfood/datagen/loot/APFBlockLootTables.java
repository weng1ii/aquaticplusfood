package net.weng1i.aquaticplusfood.datagen.loot;

import net.minecraft.advancements.critereon.StatePropertiesPredicate;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.storage.loot.predicates.LootItemBlockStatePropertyCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraftforge.registries.RegistryObject;
import net.weng1i.aquaticplusfood.block.APFBlocks;
import net.weng1i.aquaticplusfood.block.custom.CucumberCropBlock;
import net.weng1i.aquaticplusfood.item.APFItems;

import java.util.Set;

public class APFBlockLootTables extends BlockLootSubProvider {

    public APFBlockLootTables() {
        super(Set.of(), FeatureFlags.REGISTRY.allFlags());
    }

    @Override
    protected void generate() {
        LootItemCondition.Builder lootitemcondition$builder = LootItemBlockStatePropertyCondition
                .hasBlockStateProperties(APFBlocks.CUCUMBER_CROP.get())
                .setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(CucumberCropBlock.AGE, 7));

        this.add(APFBlocks.CUCUMBER_CROP.get(), createCropDrops(APFBlocks.CUCUMBER_CROP.get(), APFItems.CUCUMBER.get(),
                APFItems.CUCUMBER_SEED.get(), lootitemcondition$builder));
    }

    @Override
    protected Iterable<Block> getKnownBlocks() {
        return APFBlocks.BLOCKS.getEntries().stream().map(RegistryObject::get)::iterator;
    }
}
