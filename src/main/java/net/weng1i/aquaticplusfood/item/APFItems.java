package net.weng1i.aquaticplusfood.item;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemNameBlockItem;
import net.minecraftforge.common.ForgeSpawnEggItem;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.weng1i.aquaticplusfood.Aquaticplusfood;
import net.weng1i.aquaticplusfood.block.APFBlocks;
import net.weng1i.aquaticplusfood.entity.APFEntities;

public class APFItems {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, Aquaticplusfood.MOD_ID);

    public static final RegistryObject<Item> STRAWBERRY = ITEMS.register("strawberry",
            () -> new Item(new Item.Properties().food(APFFoods.STRAWBERRY)));
    public static final RegistryObject<Item> PINEAPPLE = ITEMS.register("pineapple",
            () -> new Item(new Item.Properties().food(APFFoods.PINEAPPLE)));
    public static final RegistryObject<Item> KIWI = ITEMS.register("kiwi",
            () -> new Item(new Item.Properties().food(APFFoods.KIWI)));
    public static final RegistryObject<Item> ORANGE = ITEMS.register("orange",
            () -> new Item(new Item.Properties().food(APFFoods.ORANGE)));
    public static final RegistryObject<Item> POMEGRANATE = ITEMS.register("pomegranate",
            () -> new Item(new Item.Properties().food(APFFoods.POMEGRANATE)));
    public static final RegistryObject<Item> CUCUMBER = ITEMS.register("cucumber",
            () -> new Item(new Item.Properties().food(APFFoods.CUCUMBER)));

    public static final RegistryObject<Item> CUCUMBER_SEED = ITEMS.register("cucumber_seed",
            () -> new ItemNameBlockItem(APFBlocks.CUCUMBER_CROP.get(), new Item.Properties()));

    public static final RegistryObject<Item> MUTANTCUCUMBER_SPAWN_EGG = ITEMS.register("mutantcucumber_spawn_egg",
            () -> new ForgeSpawnEggItem(APFEntities.MUTANTCUCUMBER, 0x003300, 0x003399, new Item.Properties()));


    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }

}
