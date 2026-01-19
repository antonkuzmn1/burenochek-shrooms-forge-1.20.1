package com.antonkuzmn1.xqwkeburenochekshrooms.registry;

import com.antonkuzmn1.xqwkeburenochekshrooms.XqwkeBurenochekShroomsMod;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class ModCreativeTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, XqwkeBurenochekShroomsMod.MODID);

    public static final RegistryObject<CreativeModeTab> XQWKE_BURENOCHEK_SHROOMS = CREATIVE_MODE_TABS.register(
            "xqwkeburenochekshrooms",
            () -> CreativeModeTab.builder()
                    .title(Component.translatable("itemGroup.xqwkeburenochekshrooms"))
                    .icon(() -> ModItems.TOADSTOOL.get().getDefaultInstance())
                    .displayItems((parameters, output) -> {
                        output.accept(ModItems.TOADSTOOL.get());
                    })
                    .build()
    );
}
