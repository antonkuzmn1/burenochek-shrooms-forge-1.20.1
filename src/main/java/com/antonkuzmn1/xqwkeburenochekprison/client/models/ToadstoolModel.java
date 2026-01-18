package com.antonkuzmn1.xqwkeburenochekprison.client.models;

import com.antonkuzmn1.xqwkeburenochekprison.XqwkeBurenochekShroomsMod;
import com.antonkuzmn1.xqwkeburenochekprison.entities.ToadstoolEntity;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class ToadstoolModel extends GeoModel<ToadstoolEntity> {
    @Override
    public ResourceLocation getModelResource(ToadstoolEntity animatable) {
        return ResourceLocation.fromNamespaceAndPath(XqwkeBurenochekShroomsMod.MODID, "geo/toadstool.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(ToadstoolEntity animatable) {
        return ResourceLocation.fromNamespaceAndPath(XqwkeBurenochekShroomsMod.MODID, "textures/entity/toadstool.png");
    }

    @Override
    public ResourceLocation getAnimationResource(ToadstoolEntity animatable) {
        return ResourceLocation.fromNamespaceAndPath(XqwkeBurenochekShroomsMod.MODID, "animations/toadstool.animation.json");
    }
}
