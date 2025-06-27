package net.weng1i.aquaticplusfood.datagen;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraftforge.common.data.BlockTagsProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.weng1i.aquaticplusfood.Aquaticplusfood;

import javax.annotation.Nullable;
import java.util.concurrent.CompletableFuture;

public class APFBlockTagGenerator extends BlockTagsProvider {
    public APFBlockTagGenerator(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, @Nullable ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, Aquaticplusfood.MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider pProvider) {

    }
}