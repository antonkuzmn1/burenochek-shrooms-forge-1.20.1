package com.antonkuzmn1.xqwkeburenochekshrooms.registry;

import com.antonkuzmn1.xqwkeburenochekshrooms.XqwkeBurenochekShroomsMod;
import com.antonkuzmn1.xqwkeburenochekshrooms.entities.ToadstoolEntity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModEntities {
    public static final DeferredRegister<EntityType<?>> ENTITIES = DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, XqwkeBurenochekShroomsMod.MODID);

    public static final RegistryObject<EntityType<ToadstoolEntity>> TOADSTOOL = ENTITIES.register("toadstool", () -> EntityType.Builder
            .of(ToadstoolEntity::new, MobCategory.MISC)
            .sized(1.0f, 1.0f)
            .build("toadstool")
    );
}
