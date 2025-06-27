package net.weng1i.aquaticplusfood.item;

import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;
import net.weng1i.aquaticplusfood.Aquaticplusfood;

public class APFCreativeModTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, Aquaticplusfood.MOD_ID);

    public static final RegistryObject<CreativeModeTab> AQUATICPLUSFOOD_TAB = CREATIVE_MODE_TABS.register("aquaticplusfood_tab",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(APFItems.PINEAPPLE.get()))
                    .title(Component.translatable("creativetab.aquaticplusfood_tab"))
                    .displayItems((pParameters, pOutput) -> {
                        pOutput.accept(APFItems.PINEAPPLE.get());
                        pOutput.accept(APFItems.STRAWBERRY.get());
                        pOutput.accept(APFItems.KIWI.get());
                        pOutput.accept(APFItems.ORANGE.get());
                        pOutput.accept(APFItems.POMEGRANATE.get());
                        pOutput.accept(APFItems.MUTANTCUCUMBER_SPAWN_EGG.get());
                        pOutput.accept(APFItems.CUCUMBER_SEED.get());
                        pOutput.accept(APFItems.CUCUMBER.get());

                    })
                    .build());


    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TABS.register(eventBus);
    }
}