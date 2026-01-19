package com.antonkuzmn1.xqwkeburenochekshrooms.client.renderers;

import com.antonkuzmn1.xqwkeburenochekshrooms.client.itemstates.common.ItemTransform;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import software.bernie.geckolib.core.animatable.GeoAnimatable;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.renderer.GeoItemRenderer;

public class ItemRenderer<I extends Item & GeoAnimatable, IM extends GeoModel<I>> extends GeoItemRenderer<I> {
    ItemTransform base;
    ItemTransform gui;
    ItemTransform thirdPerson;
    ItemTransform firstPerson;

    public ItemRenderer(
            IM itemModelInstance,
            ItemTransform base,
            ItemTransform gui,
            ItemTransform thirdPerson,
            ItemTransform firstPerson
    ) {
        super(itemModelInstance);
        this.base = base;
        this.gui = gui;
        this.thirdPerson = thirdPerson;
        this.firstPerson = firstPerson;
    }

    public void renderByItem(
            ItemStack stack,
            ItemDisplayContext transformType,
            PoseStack poseStack,
            MultiBufferSource buffer,
            int packedLight,
            int packedOverlay
    ) {
        poseStack.pushPose();

        poseStack.scale(base.scale, base.scale, base.scale);
        poseStack.translate(base.translate.x, base.translate.y, base.translate.z);

        if (transformType == ItemDisplayContext.GUI) {
            poseStack.scale(gui.scale, gui.scale, gui.scale);
            poseStack.translate(gui.translate.x, gui.translate.y, gui.translate.z);
            poseStack.mulPose(gui.rotate);
        }

        if (transformType == ItemDisplayContext.THIRD_PERSON_LEFT_HAND
                || transformType == ItemDisplayContext.THIRD_PERSON_RIGHT_HAND
        ) {
            poseStack.scale(thirdPerson.scale, thirdPerson.scale, thirdPerson.scale);
            poseStack.translate(thirdPerson.translate.x, thirdPerson.translate.y, thirdPerson.translate.z);
            poseStack.mulPose(thirdPerson.rotate);
        }

        if (transformType == ItemDisplayContext.FIRST_PERSON_LEFT_HAND
                || transformType == ItemDisplayContext.FIRST_PERSON_RIGHT_HAND
        ) {
            poseStack.scale(firstPerson.scale, firstPerson.scale, firstPerson.scale);
            poseStack.translate(firstPerson.translate.x, firstPerson.translate.y, firstPerson.translate.z);
            poseStack.mulPose(firstPerson.rotate);
        }

        super.renderByItem(stack, transformType, poseStack, buffer, packedLight, packedOverlay);

        poseStack.popPose();
    }
}

