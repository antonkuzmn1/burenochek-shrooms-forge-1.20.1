package com.antonkuzmn1.xqwkeburenochekshrooms.client.renderers;

import com.antonkuzmn1.xqwkeburenochekshrooms.client.models.ToadstoolEntityModel;
import com.antonkuzmn1.xqwkeburenochekshrooms.entities.ToadstoolEntity;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.util.Mth;
import org.jetbrains.annotations.NotNull;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class ToadstoolEntityRenderer extends GeoEntityRenderer<ToadstoolEntity> {
    private static final float GROW_TICKS = 20f;
    private static final float HOLD_TICKS = 10f;
    private static final float SHRINK_TICKS = 20f;
    private static final float TOTAL_TICKS = GROW_TICKS + HOLD_TICKS + SHRINK_TICKS;

    public ToadstoolEntityRenderer(EntityRendererProvider.Context ctx) {
        super(ctx, new ToadstoolEntityModel());
    }

    @Override
    public void render(
            @NotNull ToadstoolEntity entity,
            float entityYaw,
            float partialTick,
            @NotNull PoseStack poseStack,
            @NotNull MultiBufferSource bufferSource,
            int packedLight
    ) {
        float age = entity.tickCount + partialTick;
        final float maxScale = 0.5f;
        float scale;

        if (age < GROW_TICKS) {
            scale = age / GROW_TICKS;
        } else if (age < GROW_TICKS + HOLD_TICKS) {
            scale = 1f;
        } else if (age < TOTAL_TICKS) {
            float t = (age - GROW_TICKS - HOLD_TICKS) / SHRINK_TICKS;
            scale = 1f - t;
        } else {
            scale = 0f;
        }

        scale = Mth.clamp(scale * maxScale, 0f, maxScale);

        poseStack.scale(scale, scale, scale);

        poseStack.mulPose(Axis.YP.rotationDegrees(entity.getYRot()));
        poseStack.mulPose(Axis.XP.rotationDegrees(entity.getXRot()));

        super.render(entity, entityYaw, partialTick, poseStack, bufferSource, packedLight);
    }
}
