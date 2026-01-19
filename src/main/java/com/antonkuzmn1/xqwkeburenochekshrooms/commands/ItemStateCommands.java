package com.antonkuzmn1.xqwkeburenochekshrooms.commands;

import com.antonkuzmn1.xqwkeburenochekshrooms.client.itemstates.ToadstoolItemState;
import com.antonkuzmn1.xqwkeburenochekshrooms.client.itemstates.common.ItemTransform;
import com.antonkuzmn1.xqwkeburenochekshrooms.client.itemstates.common.ItemVec3;
import com.mojang.brigadier.arguments.FloatArgumentType;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import net.minecraft.commands.CommandSourceStack;
import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import static net.minecraft.commands.Commands.argument;

@Mod.EventBusSubscriber
public class ItemStateCommands {
    @SubscribeEvent
    public static void register(RegisterCommandsEvent event) {
        event.getDispatcher().register(literal("itemState")
                .then(toadstool())
        );
    }

    private static LiteralArgumentBuilder<CommandSourceStack> literal(String string) {
        return LiteralArgumentBuilder.literal(string);
    }

    private static LiteralArgumentBuilder<CommandSourceStack> toadstool() {
        return literal("toadstool")
                .then(set("base", ToadstoolItemState.base))
                .then(set("gui", ToadstoolItemState.gui))
                .then(set("thirdPerson", ToadstoolItemState.thirdPerson))
                .then(set("firstPerson", ToadstoolItemState.firstPerson));
    }

    private static LiteralArgumentBuilder<CommandSourceStack> set(String param, ItemTransform transform) {
        return literal("set").then(literal(param)
                .then(scale(transform))
                .then(translate(transform))
                .then(rotation(transform))
        );
    }

    private static <T extends ItemTransform> LiteralArgumentBuilder<CommandSourceStack> scale(T transform) {
        return literal("scale")
                .then(argument("scale", FloatArgumentType.floatArg(0f))
                        .executes(ctx -> {
                            transform.scale = FloatArgumentType.getFloat(ctx, "scale");
                            return 1;
                        })
                );
    }

    private static <T extends ItemTransform> LiteralArgumentBuilder<CommandSourceStack> translate(T transform) {
        return literal("translate")
                .then(argument("x", FloatArgumentType.floatArg())
                        .then(argument("y", FloatArgumentType.floatArg())
                                .then(argument("z", FloatArgumentType.floatArg())
                                        .executes(ctx -> {
                                            transform.translate = new ItemVec3(
                                                    FloatArgumentType.getFloat(ctx, "x"),
                                                    FloatArgumentType.getFloat(ctx, "y"),
                                                    FloatArgumentType.getFloat(ctx, "z")
                                            );
                                            return 1;
                                        })
                                )
                        )
                );
    }

    private static <T extends ItemTransform> LiteralArgumentBuilder<CommandSourceStack> rotation(T transform) {
        return literal("rotation")
                .then(argument("x", FloatArgumentType.floatArg(0f, 360f))
                        .then(argument("y", FloatArgumentType.floatArg(0f, 360f))
                                .then(argument("z", FloatArgumentType.floatArg(0f, 360f))
                                        .executes(ctx -> {
                                            transform.rotate = ItemTransform.toQuaternionf(
                                                    FloatArgumentType.getFloat(ctx, "x"),
                                                    FloatArgumentType.getFloat(ctx, "y"),
                                                    FloatArgumentType.getFloat(ctx, "z")
                                            );
                                            return 1;
                                        })
                                )
                        )
                );
    }
}
