package com.antonkuzmn1.xqwkeburenochekshrooms.client.renderers;

import com.antonkuzmn1.xqwkeburenochekshrooms.client.models.ToadstoolEntityModel;
import com.antonkuzmn1.xqwkeburenochekshrooms.entities.ToadstoolEntity;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class ToadstoolEntityRenderer extends GeoEntityRenderer<ToadstoolEntity> {
    public ToadstoolEntityRenderer(EntityRendererProvider.Context ctx) {
        super(ctx, new ToadstoolEntityModel());
    }
}
