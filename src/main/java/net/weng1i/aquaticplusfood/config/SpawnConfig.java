package net.weng1i.aquaticplusfood.config;

import net.minecraftforge.fml.config.ModConfig;
import net.weng1i.aquaticplusfood.Aquaticplusfood;

public class SpawnConfig {
    public static int mutantcucumberSpawnWeight = 0;
    public static int mutantcucumberSpawnRolls = 0;

    public static int pathfindingThreads = 5;

    public static void bake(ModConfig config) {
        try {
            mutantcucumberSpawnWeight = ConfigHolder.COMMON.mutantcucumberSpawnWeight.get();
            mutantcucumberSpawnRolls = ConfigHolder.COMMON.mutantcucumberSpawnRolls.get();
            pathfindingThreads = ConfigHolder.COMMON.pathfindingThreads.get();
        } catch (Exception e) {
            Aquaticplusfood.LOGGER.warn("An exception was caused trying to load the config for Aquatic Plus Food");
            e.printStackTrace();
        }
    }
}

