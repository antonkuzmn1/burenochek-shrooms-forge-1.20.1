package com.antonkuzmn1.xqwkeburenochekshrooms.registry;

import com.antonkuzmn1.xqwkeburenochekshrooms.XqwkeBurenochekShroomsMod;
import com.antonkuzmn1.xqwkeburenochekshrooms.items.ToadstoolItem;
import net.minecraft.world.item.Item;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, XqwkeBurenochekShroomsMod.MODID);

    public static final RegistryObject<ToadstoolItem> TOADSTOOL = ITEMS.register("toadstool", ToadstoolItem::new);
}
