package net.weng1i.aquaticplusfood;

import com.mojang.logging.LogUtils;
import com.mojang.serialization.Codec;
import net.minecraft.client.Minecraft;
import net.minecraft.client.color.block.BlockColors;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.world.BiomeModifier;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.config.ModConfigEvent;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.weng1i.aquaticplusfood.block.APFBlocks;
import net.weng1i.aquaticplusfood.config.BiomeConfig;
import net.weng1i.aquaticplusfood.config.ConfigHolder;
import net.weng1i.aquaticplusfood.config.SpawnConfig;
import net.weng1i.aquaticplusfood.entity.APFEntities;
import net.weng1i.aquaticplusfood.entity.client.MutantCucumberRenderer;
import net.weng1i.aquaticplusfood.item.APFCreativeModTabs;
import net.weng1i.aquaticplusfood.item.APFItems;
import net.weng1i.aquaticplusfood.loot.APFLootModifiers;
import net.weng1i.aquaticplusfood.world.APFSpawnBiomeModifier;
import org.slf4j.Logger;


@Mod(Aquaticplusfood.MOD_ID)
public class Aquaticplusfood
{
    public static final String MOD_ID = "aquaticplusfood";
    public static final CommonProxy PROXY = DistExecutor.runForDist(() -> ClientProxy::new, () -> CommonProxy::new);
    public static final Logger LOGGER = LogUtils.getLogger();


    public Aquaticplusfood()
    {
        IEventBus APFEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        APFEventBus.addListener(this::setup);
        APFEventBus.addListener(this::setupClient);
        APFEventBus.addListener(this::onModConfigEvent);
        final ModLoadingContext modLoadingContext = ModLoadingContext.get();
        APFCreativeModTabs.register(APFEventBus);

        APFItems.register(APFEventBus);
        APFBlocks.register(APFEventBus);

        APFEntities.register(APFEventBus);
        APFLootModifiers.register(APFEventBus);

        final DeferredRegister<Codec<? extends BiomeModifier>> biomeModifiers = DeferredRegister.create(ForgeRegistries.Keys.BIOME_MODIFIER_SERIALIZERS, Aquaticplusfood.MOD_ID);
        biomeModifiers.register(APFEventBus);
        biomeModifiers.register("am_mob_spawns", APFSpawnBiomeModifier::makeCodec);
        modLoadingContext.registerConfig(ModConfig.Type.COMMON, ConfigHolder.COMMON_SPEC, "aquaticplusfood.toml");
        PROXY.init();

        APFEventBus.addListener(this::commonSetup);
        MinecraftForge.EVENT_BUS.register(this);
        APFEventBus.addListener(this::addCreative);

    }

    private void commonSetup(final FMLCommonSetupEvent event)
    {

    }

    @SubscribeEvent
    public void onModConfigEvent(final ModConfigEvent event) {
        final ModConfig config = event.getConfig();
        // Rebake the configs when they change
        if (config.getSpec() == ConfigHolder.COMMON_SPEC) {
            SpawnConfig.bake(config);
        }
        BiomeConfig.init();
    }

    private void setup(final FMLCommonSetupEvent event) {
        PROXY.initPathfinding();
    }

    private void addCreative(BuildCreativeModeTabContentsEvent event)
    {
        if(event.getTabKey() == CreativeModeTabs.FOOD_AND_DRINKS) {
            event.accept(APFItems.STRAWBERRY);
            event.accept(APFItems.PINEAPPLE);
            event.accept(APFItems.KIWI);
            event.accept(APFItems.ORANGE);
            event.accept(APFItems.POMEGRANATE);
            event.accept(APFItems.CUCUMBER);
            event.accept(APFItems.CUCUMBER_SEED);
        }
    }

    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event)
    {

    }

    @Mod.EventBusSubscriber(modid = MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents
    {

        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event)
        {
            EntityRenderers.register(APFEntities.MUTANTCUCUMBER.get(), MutantCucumberRenderer::new);
        }
    }
    private void setupClient(FMLClientSetupEvent event) {
        event.enqueueWork(PROXY::clientInit);
    }
}
