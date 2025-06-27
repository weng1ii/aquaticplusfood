package net.weng1i.aquaticplusfood.entity.custom;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.game.ClientboundAnimatePacket;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerChunkCache;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.RandomSource;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.ai.navigation.GroundPathNavigation;
import net.minecraft.world.entity.ai.navigation.PathNavigation;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.phys.Vec3;
import net.weng1i.aquaticplusfood.config.SpawnConfig;
import net.weng1i.aquaticplusfood.entity.APFEntities;
import software.bernie.geckolib.animatable.GeoEntity;
import software.bernie.geckolib.constant.DefaultAnimations;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.core.animation.AnimatableManager;
import software.bernie.geckolib.util.GeckoLibUtil;

import javax.annotation.Nullable;
import java.util.EnumSet;
import java.util.UUID;

public class MutantCucumberEntity extends Monster implements GeoEntity {
    private final AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache(this);
    private static final EntityDataAccessor<Boolean> IS_BABY = SynchedEntityData.defineId(MutantCucumberEntity.class, EntityDataSerializers.BOOLEAN);

    public MutantCucumberEntity(EntityType<? extends Monster> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }

    public void aiStep() {
        this.updateSwingTime();
        this.updateNoActionTime();
        super.aiStep();
    }

    protected void updateNoActionTime() {
        float f = this.getLightLevelDependentMagicValue();
        if (f > 0.5F) {
            this.noActionTime += 2;
        }

    }

    @Override
    protected void updateSwingTime() {
        int i = this.getCurrentSwingDuration();
        if (this.swinging) {
            ++this.swingTime;
            if (this.swingTime >= i) {
                this.swingTime = 0;
                this.swinging = false;
            }
        } else {
            this.swingTime = 0;
        }

        this.attackAnim = (float)this.swingTime / (float)i;
    }

    @Override
    public void swing(InteractionHand pHand) {
        this.swing(pHand, false);
    }

    @Override
    public void swing(InteractionHand pHand, boolean pUpdateSelf) {
        ItemStack stack = this.getItemInHand(pHand);
        if (!stack.isEmpty() && stack.onEntitySwing(this)) return;
        if (!this.swinging || this.swingTime >= this.getCurrentSwingDuration() / 2 || this.swingTime < 0) {
            this.swingTime = -1;
            this.swinging = true;
            this.swingingArm = pHand;
            if (this.level() instanceof ServerLevel) {
                ClientboundAnimatePacket clientboundanimatepacket = new ClientboundAnimatePacket(this, pHand == InteractionHand.MAIN_HAND ? 0 : 3);
                ServerChunkCache serverchunkcache = ((ServerLevel)this.level()).getChunkSource();
                if (pUpdateSelf) {
                    serverchunkcache.broadcastAndSend(this, clientboundanimatepacket);
                } else {
                    serverchunkcache.broadcast(this, clientboundanimatepacket);
                }
            }
        }

    }

    private int getCurrentSwingDuration() {
        return 20;
    }

    public boolean checkSpawnRules(LevelAccessor worldIn, MobSpawnType spawnReasonIn) {
        return APFEntities.rollSpawn(SpawnConfig.mutantcucumberSpawnRolls, this.getRandom(), spawnReasonIn);
    }

    @javax.annotation.Nullable
    public SpawnGroupData finalizeSpawn(ServerLevelAccessor worldIn, DifficultyInstance difficultyIn, MobSpawnType reason, @javax.annotation.Nullable SpawnGroupData spawnDataIn, @Nullable CompoundTag dataTag) {
        SpawnGroupData finalspawn = super.finalizeSpawn(worldIn, difficultyIn, reason, spawnDataIn, dataTag);
        if (this.random.nextFloat() < 0.2F) {
            this.setBaby(true);
        }
        return finalspawn;
    }

        protected PathNavigation createNavigation(Level worldIn) {
        return new GroundPathNavigation(this, worldIn);
    }


    public static AttributeSupplier.Builder createAttributes() {
        return Monster.createLivingAttributes()
                .add(Attributes.MAX_HEALTH, 1.0D)
                .add(Attributes.FOLLOW_RANGE, 32D)
                .add(Attributes.ATTACK_DAMAGE, 1.0D)
                .add(Attributes.MOVEMENT_SPEED, 0.23F)
                .add(Attributes.ATTACK_KNOCKBACK, 1D)
                .add(Attributes.ATTACK_SPEED, 1.6D);
    }

    public boolean isBaby() {
        return this.entityData.get(IS_BABY);
    }

    public void setBaby(boolean b) {
        this.entityData.set(IS_BABY, b);
    }

    @Override
    public int getExperienceReward() {
        if (this.isBaby()) {
            this.xpReward = (int)((double)this.xpReward * 2.5D);
        }

        return super.getExperienceReward();
    }

    @Override
    protected void registerGoals() {
        //this.goalSelector.addGoal(1, new OccasionalIdleGoal(this));
        this.goalSelector.addGoal(1, new FloatGoal(this));
        this.goalSelector.addGoal(2, new MeleeAttackGoal(this, 1.1, false));
        this.goalSelector.addGoal(3, new WaterAvoidingRandomStrollGoal(this, 1.0D));
        this.goalSelector.addGoal(3, new RandomStrollGoal(this, 1.0D));
        this.goalSelector.addGoal(4, new LookAtPlayerGoal(this, Player.class, 8.0F));
        this.goalSelector.addGoal(4, new RandomLookAroundGoal(this));
        this.targetSelector.addGoal(1, new HurtByTargetGoal(this));
        this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, Player.class, false));
    }

    @Override
    public void tick() {
        super.tick();
    }

    /*
    @Override
    public void travel(Vec3 travelVector) {
        boolean isIdling = this.goalSelector.getRunningGoals()
                .anyMatch(g -> g.getGoal() instanceof OccasionalIdleGoal);

        if (isIdling) {
            super.travel(Vec3.ZERO); // freeze movement
        } else {
            super.travel(travelVector);
        }
    }

     */

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllers) {
        controllers.add(DefaultAnimations.genericIdleController(this));
        controllers.add(DefaultAnimations.genericWalkController(this));
        controllers.add(DefaultAnimations.genericAttackAnimation(this, DefaultAnimations.ATTACK_BITE));
    }

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return this.cache;
    }

    protected boolean shouldDespawnInPeaceful() {
        return true;
    }

    @Override
    protected SoundEvent getAmbientSound(){
        return SoundEvents.ZOMBIE_STEP;
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(IS_BABY, false);

    }

    public void addAdditionalSaveData(CompoundTag pCompound) {
        super.addAdditionalSaveData(pCompound);
        pCompound.putBoolean("Baby", this.isBaby());
    }

    public void readAdditionalSaveData(CompoundTag pCompound) {
        super.readAdditionalSaveData(pCompound);
        this.setBaby(pCompound.getBoolean("IsBaby"));
    }

    @Override
    public float getStandingEyeHeight(Pose pose, EntityDimensions size) {
        return this.isBaby() ? 0.6F : super.getStandingEyeHeight(pose, size);
    }

    @Override
    public EntityDimensions getDimensions(Pose pose) {
        return this.isBaby() ? super.getDimensions(pose).scale(0.5F) : super.getDimensions(pose);
    }

    public class OccasionalIdleGoal extends Goal {
        private final MutantCucumberEntity entity;
        private int idleTicks;

        public OccasionalIdleGoal(MutantCucumberEntity entity) {
            this.entity = entity;
            this.setFlags(EnumSet.of(Goal.Flag.MOVE, Goal.Flag.LOOK));
        }

        @Override
        public boolean canUse() {
            // 1 in 200 chance every tick (~once every 10s)
            return entity.getRandom().nextInt(200) == 0;
        }

        @Override
        public boolean canContinueToUse() {
            return idleTicks > 0;
        }

        @Override
        public void start() {
            this.idleTicks = 40 + entity.getRandom().nextInt(40); // 2-4 seconds
            entity.getNavigation().stop();
        }

        @Override
        public void stop() {
            this.idleTicks = 0;
        }

        @Override
        public void tick() {
            this.idleTicks--;
            entity.setDeltaMovement(0, entity.getDeltaMovement().y, 0); // no X/Z movement
            entity.getLookControl().tick(); // still look around randomly
        }
    }


}
