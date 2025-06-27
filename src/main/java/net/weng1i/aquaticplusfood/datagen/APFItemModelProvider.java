package net.weng1i.aquaticplusfood.datagen;

import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraftforge.client.model.generators.ItemModelBuilder;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.RegistryObject;
import net.weng1i.aquaticplusfood.Aquaticplusfood;
import net.weng1i.aquaticplusfood.item.APFItems;

public class APFItemModelProvider extends ItemModelProvider {

    public APFItemModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, Aquaticplusfood.MOD_ID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        simpleItem(APFItems.STRAWBERRY);
        simpleItem(APFItems.PINEAPPLE);
        simpleItem(APFItems.KIWI);
        simpleItem(APFItems.ORANGE);
        simpleItem(APFItems.POMEGRANATE);
        simpleItem(APFItems.CUCUMBER);
        simpleItem(APFItems.CUCUMBER_SEED);

        withExistingParent(APFItems.MUTANTCUCUMBER_SPAWN_EGG.getId().getPath(), mcLoc("item/template_spawn_egg"));
    }

    private ItemModelBuilder simpleItem(RegistryObject<Item> item) {
        return withExistingParent(item.getId().getPath(),
                new ResourceLocation("item/generated")).texture("layer0",
                new ResourceLocation(Aquaticplusfood.MOD_ID,"item/" + item.getId().getPath()));
    }

}
