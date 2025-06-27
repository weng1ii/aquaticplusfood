package net.weng1i.aquaticplusfood.datagen;

import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.world.item.crafting.AbstractCookingRecipe;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.level.ItemLike;
import net.minecraftforge.common.crafting.conditions.IConditionBuilder;
import net.weng1i.aquaticplusfood.Aquaticplusfood;
import net.weng1i.aquaticplusfood.block.APFBlocks;
import net.weng1i.aquaticplusfood.item.APFItems;

import java.util.List;
import java.util.function.Consumer;

public class APFRecipeProvider extends RecipeProvider implements IConditionBuilder {

    //private static final List<ItemLike> BLUEFINFISH_SMELTABLES = List.of(APFItems.RAW_BLUEFINFISH.get());

    public APFRecipeProvider(PackOutput pOutput) {
        super(pOutput);
    }

    @Override
    protected void buildRecipes(Consumer<FinishedRecipe> pWriter) {
        //oreCooking(pWriter, RecipeSerializer.SMELTING_RECIPE, BLUEFINFISH_SMELTABLES, RecipeCategory.FOOD, ModItems.COOKED_BLUEFINFISH.get(), 0.25f, 200, "bluefinfish", "_from_smelting");
        //oreCooking(pWriter, RecipeSerializer.SMOKING_RECIPE, BLUEFINFISH_SMELTABLES, RecipeCategory.FOOD, ModItems.COOKED_BLUEFINFISH.get(), 0.35f, 100, "bluefinfish", "_from_smoking");
        //oreCooking(pWriter, RecipeSerializer.CAMPFIRE_COOKING_RECIPE, BLUEFINFISH_SMELTABLES, RecipeCategory.FOOD, ModItems.COOKED_BLUEFINFISH.get(), 0.35f, 600, "bluefinfish", "_from_campfire");

/*
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, APFBlocks.SAPPHIRE_BLOCK.get())
                .pattern("SSS")
                .pattern("SSS")
                .pattern("SSS")
                .define('S', ModItems.SAPPHIRE.get())
                .unlockedBy(getHasName(ModItems.SAPPHIRE.get()), has(ModItems.SAPPHIRE.get()))
                .save(pWriter);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.SAPPHIRE.get(), 9)
                .requires(ModBlocks.SAPPHIRE_BLOCK.get())
                .unlockedBy(getHasName(ModBlocks.SAPPHIRE_BLOCK.get()), has(ModBlocks.SAPPHIRE_BLOCK.get()))
                .save(pWriter);
                */
    }

    protected static void oreSmelting(Consumer<FinishedRecipe> pFinishedRecipeConsumer, List<ItemLike> pIngredients, RecipeCategory pCategory, ItemLike pResult, float pExperience, int pCookingTIme, String pGroup) {
        oreCooking(pFinishedRecipeConsumer, RecipeSerializer.SMELTING_RECIPE, pIngredients, pCategory, pResult, pExperience, pCookingTIme, pGroup, "_from_smelting");
    }

    protected static void oreBlasting(Consumer<FinishedRecipe> pFinishedRecipeConsumer, List<ItemLike> pIngredients, RecipeCategory pCategory, ItemLike pResult, float pExperience, int pCookingTime, String pGroup) {
        oreCooking(pFinishedRecipeConsumer, RecipeSerializer.BLASTING_RECIPE, pIngredients, pCategory, pResult, pExperience, pCookingTime, pGroup, "_from_blasting");
    }

    protected static void oreCooking(Consumer<FinishedRecipe> pFinishedRecipeConsumer, RecipeSerializer<? extends AbstractCookingRecipe> pCookingSerializer, List<ItemLike> pIngredients, RecipeCategory pCategory, ItemLike pResult, float pExperience, int pCookingTime, String pGroup, String pRecipeName) {
        for(ItemLike itemlike : pIngredients) {
            SimpleCookingRecipeBuilder.generic(Ingredient.of(itemlike), pCategory, pResult,
                            pExperience, pCookingTime, pCookingSerializer)
                    .group(pGroup).unlockedBy(getHasName(itemlike), has(itemlike))
                    .save(pFinishedRecipeConsumer,  Aquaticplusfood.MOD_ID + ":" + getItemName(pResult) + pRecipeName + "_" + getItemName(itemlike));
        }
    }
}