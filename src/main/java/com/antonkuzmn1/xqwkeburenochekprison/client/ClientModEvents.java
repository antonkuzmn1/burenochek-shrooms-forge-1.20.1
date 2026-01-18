package com.antonkuzmn1.xqwkeburenochekprison.client;

import com.antonkuzmn1.xqwkeburenochekprison.XqwkeBurenochekShroomsMod;
import com.antonkuzmn1.xqwkeburenochekprison.client.renderers.ToadstoolRenderer;
import com.antonkuzmn1.xqwkeburenochekprison.registry.ModEntities;
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
        event.registerEntityRenderer(ModEntities.TOADSTOOL.get(), ToadstoolRenderer::new);
    }

    @SubscribeEvent
    public static void onClientSetup(FMLClientSetupEvent event) {
    }
}
