package net.weng1i.aquaticplusfood.entity.client;

import net.minecraft.resources.ResourceLocation;
import net.weng1i.aquaticplusfood.Aquaticplusfood;
import net.weng1i.aquaticplusfood.entity.custom.MutantCucumberEntity;
import software.bernie.geckolib.constant.DataTickets;
import software.bernie.geckolib.core.animatable.model.CoreGeoBone;
import software.bernie.geckolib.core.animation.AnimationState;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.model.data.EntityModelData;

public class MutantCucumberModel extends GeoModel<MutantCucumberEntity> {
    @Override
    public ResourceLocation getModelResource(MutantCucumberEntity animatable) {
        return new ResourceLocation(Aquaticplusfood.MOD_ID, "geo/mutantcucumber.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(MutantCucumberEntity animatable) {
        return new ResourceLocation(Aquaticplusfood.MOD_ID, "textures/entity/mutantcucumber.png");
    }

    @Override
    public ResourceLocation getAnimationResource(MutantCucumberEntity animatable) {
        return new ResourceLocation(Aquaticplusfood.MOD_ID, "animations/mutantcucumber.animation.json");
    }

    @Override
    public void setCustomAnimations(MutantCucumberEntity animatable, long instanceId, AnimationState<MutantCucumberEntity> animationState) {
        CoreGeoBone head = getAnimationProcessor().getBone("head");
/*
        if(head != null) {
            EntityModelData entityData = animationState.getData(DataTickets.ENTITY_MODEL_DATA);
            head.setRotX(entityData.headPitch() * ((float) Math.PI / 180F));
            head.setRotY(entityData.netHeadYaw() * ((float) Math.PI / 180F));
        }

 */
    }
}
