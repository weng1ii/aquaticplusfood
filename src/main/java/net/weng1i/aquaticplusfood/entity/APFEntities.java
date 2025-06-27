package net.weng1i.aquaticplusfood.entity;

import com.google.common.base.Predicates;
import net.minecraft.core.BlockPos;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.NaturalSpawner;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.material.FluidState;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.weng1i.aquaticplusfood.Aquaticplusfood;
import net.weng1i.aquaticplusfood.entity.custom.MutantCucumberEntity;

import java.util.function.Predicate;

public class APFEntities {
    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES =
            DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, Aquaticplusfood.MOD_ID);

    public static final RegistryObject<EntityType<MutantCucumberEntity>> MUTANTCUCUMBER =
            ENTITY_TYPES.register("mutantcucumber", () -> EntityType.Builder.of(MutantCucumberEntity::new, MobCategory.MONSTER)
                    .sized(1f, 1f).build("mutantcucumber"));


    public static void register(IEventBus eventBus) {
        ENTITY_TYPES.register(eventBus);
    }

    @SubscribeEvent
    public static void initializeAttributes(EntityAttributeCreationEvent event) {
        SpawnPlacements.Type spawnsOnLeaves = SpawnPlacements.Type.create("am_leaves", APFEntities::createLeavesSpawnPlacement);
        SpawnPlacements.register(MUTANTCUCUMBER.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING, MutantCucumberEntity::checkMonsterSpawnRules);
        event.put(MUTANTCUCUMBER.get(), MutantCucumberEntity.createAttributes().build());
    }

    public static Predicate<LivingEntity> buildPredicateFromTag(TagKey<EntityType<?>> entityTag){
        if(entityTag == null){
            return Predicates.alwaysFalse();
        }else{
            return (com.google.common.base.Predicate<LivingEntity>) e -> e.isAlive() && e.getType().is(entityTag);
        }
    }

    public static Predicate<LivingEntity> buildPredicateFromTagTameable(TagKey<EntityType<?>> entityTag, LivingEntity owner){
        if(entityTag == null){
            return Predicates.alwaysFalse();
        }else{
            return (com.google.common.base.Predicate<LivingEntity>) e -> e.isAlive() && e.getType().is(entityTag) && !owner.isAlliedTo(e);
        }
    }

    public static boolean rollSpawn(int rolls, RandomSource random, MobSpawnType reason){
        if(reason == MobSpawnType.SPAWNER){
            return true;
        }else{
            return rolls <= 0 || random.nextInt(rolls) == 0;
        }
    }

    public static boolean createLeavesSpawnPlacement(LevelReader level, BlockPos pos, EntityType<?> type){
        BlockPos blockpos = pos.above();
        BlockPos blockpos1 = pos.below();
        FluidState fluidstate = level.getFluidState(pos);
        BlockState blockstate = level.getBlockState(pos);
        BlockState blockstate1 = level.getBlockState(blockpos1);
        if (!blockstate1.isValidSpawn(level, blockpos1, SpawnPlacements.Type.ON_GROUND, type) && !blockstate1.is(BlockTags.LEAVES)) {
            return false;
        } else {
            return NaturalSpawner.isValidEmptySpawnBlock(level, pos, blockstate, fluidstate, type) && NaturalSpawner.isValidEmptySpawnBlock(level, blockpos, level.getBlockState(blockpos), level.getFluidState(blockpos), type);
        }
    }
}
