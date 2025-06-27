package net.weng1i.aquaticplusfood.event;

import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.weng1i.aquaticplusfood.Aquaticplusfood;
import net.weng1i.aquaticplusfood.entity.APFEntities;
import net.weng1i.aquaticplusfood.entity.custom.MutantCucumberEntity;

@Mod.EventBusSubscriber(modid = Aquaticplusfood.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class APFEventBusEvents {
    @SubscribeEvent
    public static void registerAttributes(EntityAttributeCreationEvent event) {
        event.put(APFEntities.MUTANTCUCUMBER.get(), MutantCucumberEntity.createAttributes().build());

    }
}
