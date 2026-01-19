package com.antonkuzmn1.xqwkeburenochekshrooms.client.models;

import com.antonkuzmn1.xqwkeburenochekshrooms.XqwkeBurenochekShroomsMod;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.core.animatable.GeoAnimatable;
import software.bernie.geckolib.model.GeoModel;

public abstract class Model<T extends GeoAnimatable> extends GeoModel<T> {
    private final String model;
    private final String texture;
    private final String animation;

    public Model(String model, String texture, String animation) {
        this.model = model;
        this.texture = texture;
        this.animation = animation;
    }

    public Model(String name) {
        this(
                "geo/" + name + ".geo.json",
                "textures/entity/" + name + ".png",
                "animations/" + name + ".animation.json"
        );
    }

    @Override
    public ResourceLocation getModelResource(T animatable) {
        return ResourceLocation.fromNamespaceAndPath(XqwkeBurenochekShroomsMod.MODID, model);
    }

    @Override
    public ResourceLocation getTextureResource(T animatable) {
        return ResourceLocation.fromNamespaceAndPath(XqwkeBurenochekShroomsMod.MODID, texture);
    }

    @Override
    public ResourceLocation getAnimationResource(T animatable) {
        return ResourceLocation.fromNamespaceAndPath(XqwkeBurenochekShroomsMod.MODID, animation);
    }
}
