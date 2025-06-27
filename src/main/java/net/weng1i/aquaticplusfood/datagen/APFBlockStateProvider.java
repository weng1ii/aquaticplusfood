package net.weng1i.aquaticplusfood.datagen;

import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.client.model.generators.ConfiguredModel;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.weng1i.aquaticplusfood.Aquaticplusfood;
import net.weng1i.aquaticplusfood.block.APFBlocks;
import net.weng1i.aquaticplusfood.block.custom.CucumberCropBlock;
import net.weng1i.aquaticplusfood.item.APFItems;

import java.util.function.Function;

public class APFBlockStateProvider extends BlockStateProvider {
    public APFBlockStateProvider(PackOutput output, ExistingFileHelper exFileHelper) {
        super(output, Aquaticplusfood.MOD_ID, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        makeCucumberCrop(((CropBlock) APFBlocks.CUCUMBER_CROP.get()), "cucumber_stage_", "cucumber_stage_");
    }


    public void makeCucumberCrop(CropBlock block, String modelName, String textureName) {
        Function<BlockState, ConfiguredModel[]> function = state -> cucumberStates(state, block, modelName, textureName);

        getVariantBuilder(block).forAllStates(function);
    }

    private ConfiguredModel[] cucumberStates(BlockState state, CropBlock block, String modelName, String textureName) {
        ConfiguredModel[] models = new ConfiguredModel[1];
        models[0] = new ConfiguredModel(models().crop(modelName + state.getValue(((CucumberCropBlock) block).getAgeProperty()),
                new ResourceLocation(Aquaticplusfood.MOD_ID, "block/" + textureName + state.getValue(((CucumberCropBlock) block).getAgeProperty()))).renderType("cutout"));
        return models;
    }


    private String name(Block block) {
        return key(block).getPath();
    }

    private ResourceLocation key(Block block) {
        return ForgeRegistries.BLOCKS.getKey(block);
    }

    private void leavesBlock(RegistryObject<Block> blockRegistryObject) {
        simpleBlockWithItem(blockRegistryObject.get(),
                models().singleTexture(ForgeRegistries.BLOCKS.getKey(blockRegistryObject.get()).getPath(), new ResourceLocation("minecraft:block/leaves"),
                        "all", blockTexture(blockRegistryObject.get())).renderType("cutout"));
    }

    private void blockItem(RegistryObject<Block> blockRegistryObject) {
        simpleBlockItem(blockRegistryObject.get(), new ModelFile.UncheckedModelFile(Aquaticplusfood.MOD_ID +
                ":block/" + ForgeRegistries.BLOCKS.getKey(blockRegistryObject.get()).getPath()));
    }

    private void blockWithItem(RegistryObject<Block> blockRegistryObject) {
        simpleBlockWithItem(blockRegistryObject.get(), cubeAll(blockRegistryObject.get()));
    }


}