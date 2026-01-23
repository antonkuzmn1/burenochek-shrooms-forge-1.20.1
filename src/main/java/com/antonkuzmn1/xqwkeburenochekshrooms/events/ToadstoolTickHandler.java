package com.antonkuzmn1.xqwkeburenochekshrooms.events;

import com.antonkuzmn1.xqwkeburenochekshrooms.data.ToadstoolInfectedBlocks;
import com.antonkuzmn1.xqwkeburenochekshrooms.entities.ToadstoolEntity;
import com.antonkuzmn1.xqwkeburenochekshrooms.entities.ToadstoolPreset;
import com.antonkuzmn1.xqwkeburenochekshrooms.registry.ModEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.*;

@Mod.EventBusSubscriber
public class ToadstoolTickHandler {
    public static final Map<BlockPos, Set<Integer>> USED_PRESET_INDEXES = new HashMap<>();

    private static int tickCounter = 0;

    private static final ToadstoolPreset[] PRESETS = new ToadstoolPreset[]{
            new ToadstoolPreset(new Vec3(0.5, 0.7, -0.5), 0f, 45f),
            new ToadstoolPreset(new Vec3(0.2, 0.7, 0.2), 45f, 30f),
            new ToadstoolPreset(new Vec3(0.8, 0.7, -0.2), 90f, -30f),
            new ToadstoolPreset(new Vec3(0.2, 0.7, -0.8), 180f, -40f),
            new ToadstoolPreset(new Vec3(0.8, 0.7, 0.8), 270f, 15f)
    };

    @SubscribeEvent
    public static void onServerTick(TickEvent.ServerTickEvent event) {
        if (event.phase != TickEvent.Phase.END) return;

        int tickInterval = 20;

        tickCounter++;
        if (tickCounter < tickInterval) return;
        tickCounter = 0;

        for (ServerLevel level : event.getServer().getAllLevels()) {
            ToadstoolInfectedBlocks data = ToadstoolInfectedBlocks.get(level);

            Iterator<BlockPos> it = data.getAll().iterator();
            while (it.hasNext()) {
                BlockPos pos = it.next();

                if (!ToadstoolInfectedBlocks.verify(level, pos)) {
                    it.remove();
                    USED_PRESET_INDEXES.remove(pos);
                    data.setDirty();
                    continue;
                }

                BlockState state = level.getBlockState(pos);

                Direction facing = state.hasProperty(BlockStateProperties.HORIZONTAL_FACING)
                        ? state.getValue(BlockStateProperties.HORIZONTAL_FACING)
                        : Direction.NORTH;

                List<Integer> shuffledIndexes = new ArrayList<>();
                for (int i = 0; i < PRESETS.length; i++) shuffledIndexes.add(i);
                Collections.shuffle(shuffledIndexes, new java.util.Random(level.getRandom().nextLong()));

                if (!USED_PRESET_INDEXES.containsKey(pos)) {
                    USED_PRESET_INDEXES.put(pos, new HashSet<>());
                }
                Set<Integer> presetIndexes = USED_PRESET_INDEXES.get(pos);
                Integer presetIndex = null;
                for (int idx : shuffledIndexes) {
                    if (!presetIndexes.contains(idx)) {
                        presetIndex = idx;
                        break;
                    }
                }

                if (presetIndex == null || presetIndex >= PRESETS.length) break;

                presetIndexes.add(presetIndex);
                ToadstoolPreset preset = PRESETS[presetIndex];

                ToadstoolEntity entity = new ToadstoolEntity(ModEntities.TOADSTOOL.get(), level);
                entity.setPresetIndex(presetIndex);
                entity.setSourcePos(pos);

                Vec3 rotatedOffset = rotateOffset(preset.offset(), facing);
                entity.setPos(
                        pos.getX() + rotatedOffset.x,
                        pos.getY() + rotatedOffset.y,
                        pos.getZ() + rotatedOffset.z
                );

                entity.setYRot(preset.yaw());
                entity.setXRot(preset.pitch());

                level.addFreshEntity(entity);
            }
        }
    }

    private static Vec3 rotateOffset(Vec3 offset, Direction facing) {
        return switch (facing) {
            case SOUTH -> new Vec3(1 - offset.x, offset.y, 1 - offset.z);
            case WEST -> new Vec3(offset.z, offset.y, 1 - offset.x);
            case EAST -> new Vec3(1 - offset.z, offset.y, offset.x);
            default -> offset;
        };
    }
}
