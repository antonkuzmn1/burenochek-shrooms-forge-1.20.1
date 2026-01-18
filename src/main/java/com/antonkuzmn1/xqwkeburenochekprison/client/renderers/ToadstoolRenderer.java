package com.antonkuzmn1.xqwkeburenochekprison.client.renderers;

import com.antonkuzmn1.xqwkeburenochekprison.client.models.ToadstoolModel;
import com.antonkuzmn1.xqwkeburenochekprison.entities.ToadstoolEntity;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class ToadstoolRenderer extends GeoEntityRenderer<ToadstoolEntity> {
    public ToadstoolRenderer(EntityRendererProvider.Context ctx) {
        super(ctx, new ToadstoolModel());
    }
}
