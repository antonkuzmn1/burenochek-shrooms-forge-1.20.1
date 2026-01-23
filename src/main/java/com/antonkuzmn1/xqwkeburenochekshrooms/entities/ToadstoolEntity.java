package com.antonkuzmn1.xqwkeburenochekshrooms.entities;

import com.antonkuzmn1.xqwkeburenochekshrooms.events.ToadstoolTickHandler;
import com.antonkuzmn1.xqwkeburenochekshrooms.registry.ModItems;
import com.mojang.logging.LogUtils;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import software.bernie.geckolib.animatable.GeoEntity;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.core.animation.AnimatableManager;
import software.bernie.geckolib.core.animation.Animation;
import software.bernie.geckolib.core.animation.AnimationController;
import software.bernie.geckolib.core.animation.RawAnimation;
import software.bernie.geckolib.core.object.PlayState;
import software.bernie.geckolib.util.GeckoLibUtil;

import java.util.Set;

public class ToadstoolEntity extends Entity implements GeoEntity {
    private static final Logger LOGGER = LogUtils.getLogger();
    private final AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache(this);

    private int presetIndex = -1;
    private BlockPos sourcePos;

    public ToadstoolEntity(EntityType<? extends ToadstoolEntity> type, Level level) {
        super(type, level);
    }

    public void setPresetIndex(int index) {
        this.presetIndex = index;
    }

    public int getPresetIndex() {
        return this.presetIndex;
    }

    public void setSourcePos(BlockPos pos) {
        this.sourcePos = pos.immutable();
    }

    public BlockPos getSourcePos() {
        return sourcePos;
    }

    @Override
    protected void defineSynchedData() {
    }

    @Override
    protected void readAdditionalSaveData(@NotNull CompoundTag tag) {
        if (tag.contains("SourcePos")) {
            sourcePos = BlockPos.of(tag.getLong("SourcePos"));
        }
        presetIndex = tag.getInt("PresetIndex");
    }

    @Override
    protected void addAdditionalSaveData(@NotNull CompoundTag tag) {
        if (sourcePos != null) {
            tag.putLong("SourcePos", sourcePos.asLong());
        }
        tag.putInt("PresetIndex", presetIndex);
    }

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllers) {
        controllers.add(
                new AnimationController<>(
                        this,
                        "controller",
                        0,
                        state -> {
                            state.setAnimation(RawAnimation.begin().then(
                                    "animation.toadstool.idle",
                                    Animation.LoopType.LOOP
                            ));
                            return PlayState.CONTINUE;
                        }
                )
        );
    }

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return cache;
    }

    @Override
    public boolean isPickable() {
        return true;
    }

    @Override
    public @NotNull InteractionResult interact(@NotNull Player player, @NotNull InteractionHand hand) {
        //noinspection resource
        Level world = level();
        if (!world.isClientSide) {
            if (presetIndex >= 0) destroySourcePos();
            if (20 <= tickCount && tickCount < 30) {
                this.spawnAtLocation(ModItems.TOADSTOOL.get());
            }
            this.remove(RemovalReason.DISCARDED);
        }
        return InteractionResult.sidedSuccess(world.isClientSide());
    }

    @Override
    public void tick() {
        super.tick();

        //noinspection resource
        Level world = level();

        if (!world.isClientSide && tickCount > 50) {
            if (presetIndex >= 0) destroySourcePos();

            if (world instanceof ServerLevel serverLevel) {
                serverLevel.sendParticles(
                        ParticleTypes.SMOKE,
                        getX(), getY() + 0.1, getZ(),
                        1,
                        0.0, 0.0, 0.0,
                        0.0
                );
            }

            discard();
        }
    }

    private void destroySourcePos() {
        if (sourcePos != null) {
            Set<Integer> set = ToadstoolTickHandler.USED_PRESET_INDEXES.get(sourcePos);
            if (set != null) {
                set.remove(presetIndex);
                if (set.isEmpty()) {
                    ToadstoolTickHandler.USED_PRESET_INDEXES.remove(sourcePos);
                }
            }
        }
    }
}
