package com.antonkuzmn1.xqwkeburenochekprison.registry;

import com.antonkuzmn1.xqwkeburenochekprison.XqwkeBurenochekShroomsMod;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraftforge.registries.DeferredRegister;

public class ModCreativeTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, XqwkeBurenochekShroomsMod.MODID);

//    public static final RegistryObject<CreativeModeTab> XQWKE_BURENOCHEK_SHROOMS = CREATIVE_MODE_TABS.register(
//            "xqwkeburenochekshrooms",
//            () -> CreativeModeTab.builder()
//                    .title(Component.translatable("itemGroup.xqwkeburenochekshrooms"))
//                    .icon(() -> ModItems.SHROOM.get().getDefaultInstance())
//                    .displayItems((parameters, output) -> {
//                    })
//                    .build()
//    );
}
