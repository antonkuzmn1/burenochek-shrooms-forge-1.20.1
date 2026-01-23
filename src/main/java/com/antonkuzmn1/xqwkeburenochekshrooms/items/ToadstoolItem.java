package com.antonkuzmn1.xqwkeburenochekshrooms.items;

import com.antonkuzmn1.xqwkeburenochekshrooms.client.renderers.ToadstoolItemRenderer;
import com.antonkuzmn1.xqwkeburenochekshrooms.data.ToadstoolInfectedBlocks;
import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.client.extensions.common.IClientItemExtensions;
import net.minecraftforge.registries.ForgeRegistries;
import org.jetbrains.annotations.NotNull;
import software.bernie.geckolib.animatable.GeoItem;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.core.animation.AnimatableManager;
import software.bernie.geckolib.util.GeckoLibUtil;

import java.util.function.Consumer;

public class ToadstoolItem extends Item implements GeoItem {
    private final AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache(this);

    public ToadstoolItem() {
        super(new Properties());
    }

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllers) {
    }

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return cache;
    }

    @Override
    public void initializeClient(Consumer<IClientItemExtensions> consumer) {
        consumer.accept(new IClientItemExtensions() {
            @Override
            public BlockEntityWithoutLevelRenderer getCustomRenderer() {
                return new ToadstoolItemRenderer();
            }
        });
    }

    @Override
    public @NotNull InteractionResult useOn(@NotNull UseOnContext context) {
        Level level = context.getLevel();
        BlockPos pos = context.getClickedPos();
        BlockState state = level.getBlockState(pos);
        ResourceLocation targetId = ForgeRegistries.BLOCKS.getKey(state.getBlock());

        if (targetId != null && ToadstoolInfectedBlocks.verify(level, pos)) {
            if (!level.isClientSide) {
                ServerLevel serverLevel = (ServerLevel) level;
                ToadstoolInfectedBlocks infectedBlocks = ToadstoolInfectedBlocks.get(serverLevel);
                infectedBlocks.add(serverLevel, pos);
            }
            return InteractionResult.SUCCESS;
        }

        return InteractionResult.PASS;
    }
}