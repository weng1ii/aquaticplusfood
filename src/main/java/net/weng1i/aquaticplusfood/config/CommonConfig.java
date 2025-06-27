package net.weng1i.aquaticplusfood.config;

import net.minecraftforge.common.ForgeConfigSpec;

public class CommonConfig {
    public final ForgeConfigSpec.IntValue mutantcucumberSpawnWeight;
    public final ForgeConfigSpec.IntValue mutantcucumberSpawnRolls;
    public ForgeConfigSpec.IntValue pathfindingThreads;

    public CommonConfig(final ForgeConfigSpec.Builder builder) {
        builder.push("spawning");
        mutantcucumberSpawnWeight = buildInt(builder, "mutantcucumberSpawnWeight", "spawns", SpawnConfig.mutantcucumberSpawnWeight, 0, 1000, "Spawn Weight, added to a pool of other mobs for each biome. Higher number = higher chance of spawning. 0 = disable spawn");
        mutantcucumberSpawnRolls = buildInt(builder, "mutantcucumberSpawnRolls", "spawns", SpawnConfig.mutantcucumberSpawnRolls, 0, Integer.MAX_VALUE, "Random roll chance to enable mob spawning. Higher number = lower chance of spawning");
        builder.pop();
        builder.push("dangerZone");
        pathfindingThreads = buildInt(builder, "pathfindingThreads", "dangerZone", SpawnConfig.pathfindingThreads, 1, 100,"How many cpu cores some mobs(elephants, leafcutter ants, bison etc) should utilize when pathing. Bigger number = less impact on TPS");
        builder.pop();
    }

    private static ForgeConfigSpec.IntValue buildInt(ForgeConfigSpec.Builder builder, String name, String catagory, int defaultValue, int min, int max, String comment) {
        return builder.comment(comment).translation(name).defineInRange(name, defaultValue, min, max);
    }
}
