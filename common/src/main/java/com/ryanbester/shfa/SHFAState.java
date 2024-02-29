package com.ryanbester.shfa;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.block.state.BlockState;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

public class SHFAState {
    public static boolean enabled = true;

    public static LinkedHashSet<String> enabledBlocks = new LinkedHashSet<>();

    public static LinkedHashSet<String> enabledBlocksDefault = new LinkedHashSet<>(List.of("#minecraft:fences", "#minecraft:wool_carpets", "#shfa:glass_panes", "#shfa:torches", "minecraft:chain", "minecraft:iron_bars", "minecraft:end_rod", "minecraft:lantern", "minecraft:soul_lantern"));

//    public static void toggleSHFA() {
//        enabled = !enabled;
//    }

    public static boolean showHitbox(BlockState state) {
        if (!enabled) return false;

        if (enabledBlocks.contains(BuiltInRegistries.BLOCK.getKey(state.getBlock()).toString())) {
            return true;
        }

        AtomicBoolean foundTag = new AtomicBoolean(false);
        state.getTags().forEach(t -> {
            if (enabledBlocks.contains("#" + t.location())) {
                foundTag.set(true);
            }
        });

        return foundTag.get();
    }
}
