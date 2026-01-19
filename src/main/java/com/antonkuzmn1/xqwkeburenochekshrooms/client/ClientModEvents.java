package com.antonkuzmn1.xqwkeburenochekshrooms.client;

import com.antonkuzmn1.xqwkeburenochekshrooms.XqwkeBurenochekShroomsMod;
import com.antonkuzmn1.xqwkeburenochekshrooms.client.renderers.ToadstoolEntityRenderer;
import com.antonkuzmn1.xqwkeburenochekshrooms.registry.ModEntities;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

@Mod.EventBusSubscriber(
        modid = XqwkeBurenochekShroomsMod.MODID,
        bus = Mod.EventBusSubscriber.Bus.MOD,
        value = Dist.CLIENT
)
public class ClientModEvents {
    @SubscribeEvent
    public static void registerBlockEntityRenderers(final EntityRenderersEvent.RegisterRenderers event) {
    }

    @SubscribeEvent
    public static void registerRenderers(final EntityRenderersEvent.RegisterRenderers event) {
        event.registerEntityRenderer(ModEntities.TOADSTOOL.get(), ToadstoolEntityRenderer::new);
    }

    @SubscribeEvent
    public static void onClientSetup(FMLClientSetupEvent event) {
    }
}
