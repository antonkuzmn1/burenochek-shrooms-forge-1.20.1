package com.antonkuzmn1.xqwkeburenochekshrooms.data;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.NonNullList;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.saveddata.SavedData;
import net.minecraftforge.registries.ForgeRegistries;
import org.jetbrains.annotations.NotNull;

import java.util.Set;

public class ToadstoolInfectedBlocks extends SavedData {
    private static final String NAME = "toadstool_infected_blocks";
    private static final String LIST_KEY = "Blocks";
    private static final int LIMIT = 255;

    private final NonNullList<BlockPos> infectedBlocks = NonNullList.create();

    public static ToadstoolInfectedBlocks get(ServerLevel level) {
        return level.getDataStorage().computeIfAbsent(ToadstoolInfectedBlocks::load, ToadstoolInfectedBlocks::new, NAME);
    }

    public static ToadstoolInfectedBlocks load(CompoundTag tag) {
        ToadstoolInfectedBlocks data = new ToadstoolInfectedBlocks();

        for (long packed : tag.getLongArray(LIST_KEY)) {
            data.infectedBlocks.add(BlockPos.of(packed));
        }

        return data;
    }

    @Override
    public @NotNull CompoundTag save(@NotNull CompoundTag tag) {
        long[] arr = infectedBlocks.stream().limit(LIMIT).mapToLong(BlockPos::asLong).toArray();

        tag.putLongArray(LIST_KEY, arr);
        return tag;
    }

    public void add(ServerLevel level, BlockPos pos) {
        if (infectedBlocks.size() < LIMIT && !infectedBlocks.contains(pos) && verify(level, pos)) {
            BlockState state = level.getBlockState(pos);

            assert state.hasProperty(BlockStateProperties.HORIZONTAL_FACING);
            Direction facing = state.getValue(BlockStateProperties.HORIZONTAL_FACING);

            for (var property : state.getProperties()) {
                if (property.getName().equals("part")) {
                    String part = state.getValue(property).toString();
                    if (!part.equals("foot") && !part.equals("lower")) {
                        pos = pos.relative(facing.getOpposite());
                    }
                }
            }

            infectedBlocks.add(pos);
            setDirty();
        }
    }

    public void remove(BlockPos pos) {
        if (infectedBlocks.remove(pos)) {
            setDirty();
        }
    }

    public static boolean verify(Level level, BlockPos pos) {
        Set<String> allowedBlockIds = Set.of("minecraft:white_bed");

        BlockState state = level.getBlockState(pos);
        Block block = state.getBlock();
        ResourceLocation targetId = ForgeRegistries.BLOCKS.getKey(block);

        return (targetId != null && allowedBlockIds.contains(targetId.toString()));
    }

    public boolean contains(BlockPos pos) {
        return infectedBlocks.contains(pos);
    }

    public NonNullList<BlockPos> getAll() {
        return infectedBlocks;
    }
}
