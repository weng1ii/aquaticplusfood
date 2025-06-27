package net.weng1i.aquaticplusfood.entity.client;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.weng1i.aquaticplusfood.Aquaticplusfood;
import net.weng1i.aquaticplusfood.entity.custom.MutantCucumberEntity;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class MutantCucumberRenderer extends GeoEntityRenderer<MutantCucumberEntity> {
    public MutantCucumberRenderer(EntityRendererProvider.Context renderManager) {
        super(renderManager, new MutantCucumberModel());
    }

    @Override
    public ResourceLocation getTextureLocation(MutantCucumberEntity animatable) {
        return new ResourceLocation(Aquaticplusfood.MOD_ID, "textures/entity/mutantcucumber.png");
    }

    @Override
    public void render(MutantCucumberEntity pEntity, float pEntityYaw, float pPartialTicks, PoseStack pMatrixStack, MultiBufferSource pBuffer, int pPackedLight) {
        if (pEntity.isBaby()) {
            pMatrixStack.scale(0.4f,0.4f,0.4f);
        }
        super.render(pEntity, pEntityYaw, pPartialTicks, pMatrixStack, pBuffer, pPackedLight);
    }
}
