package net.weng1i.aquaticplusfood;

import net.minecraft.client.CameraType;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.ForgeHooksClient;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.weng1i.aquaticplusfood.entity.APFEntities;
import net.weng1i.aquaticplusfood.entity.client.MutantCucumberRenderer;

@OnlyIn(Dist.CLIENT)
@Mod.EventBusSubscriber(modid = Aquaticplusfood.MOD_ID, value = Dist.CLIENT)
public class ClientProxy extends CommonProxy {
    public CameraType prevPOV = CameraType.FIRST_PERSON;

    @SubscribeEvent
    @OnlyIn(Dist.CLIENT)
    public void clientInit() {
        MinecraftForge.EVENT_BUS.register(new ForgeHooksClient.ClientEvents());
        EntityRenderers.register(APFEntities.MUTANTCUCUMBER.get(), MutantCucumberRenderer::new);

    }

    public Player getClientSidePlayer() {
        return Minecraft.getInstance().player;
    }

    public void updateBiomeVisuals(int x, int z) {
        Minecraft.getInstance().levelRenderer.setBlocksDirty(x - 32, 0, x - 32, z + 32, 255, z + 32);
    }

    public void setRenderViewEntity(Entity entity) {
        prevPOV = Minecraft.getInstance().options.getCameraType();
        Minecraft.getInstance().setCameraEntity(entity);
        Minecraft.getInstance().options.setCameraType(CameraType.THIRD_PERSON_BACK);
    }

    public void resetRenderViewEntity() {
        Minecraft.getInstance().setCameraEntity(Minecraft.getInstance().player);
    }

    public int getPreviousPOV() {
        return prevPOV.ordinal();
    }

    public boolean isFarFromCamera(double x, double y, double z) {
        Minecraft lvt_1_1_ = Minecraft.getInstance();
        return lvt_1_1_.gameRenderer.getMainCamera().getPosition().distanceToSqr(x, y, z) >= 256.0D;
    }

    @SubscribeEvent
    @OnlyIn(Dist.CLIENT)
    public void onRegisterEntityRenders(EntityRenderersEvent.RegisterLayerDefinitions event) {
    }
}
