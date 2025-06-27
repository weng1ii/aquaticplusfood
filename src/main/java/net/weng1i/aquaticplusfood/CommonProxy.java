package net.weng1i.aquaticplusfood;

import com.github.alexthe666.citadel.server.entity.pathfinding.raycoms.PathfindingConstants;
import net.minecraftforge.fml.common.Mod;
import net.weng1i.aquaticplusfood.config.SpawnConfig;

import static net.weng1i.aquaticplusfood.Aquaticplusfood.MOD_ID;

@Mod.EventBusSubscriber(modid = MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class CommonProxy {
    public void init() {}

    public void clientInit() {
    }

    public void initPathfinding() {
        //PathfindingConstants.isDebugMode = true;
        PathfindingConstants.pathfindingThreads = Math.max(PathfindingConstants.pathfindingThreads, SpawnConfig.pathfindingThreads);
    }
}
