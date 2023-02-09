package net.dirtcraft.plugin.dirtcraft;

import org.spongepowered.api.Sponge;
import org.spongepowered.api.event.Listener;
import org.spongepowered.api.event.game.state.GameInitializationEvent;
import org.spongepowered.api.plugin.Plugin;

@Plugin(id = "dirtcraft", name = "Dirt-Craft", version = "1.0.0", description = "Adds a workbench command cause Nucleus didn't implement it until API7")
public class DirtCraft {
    @Listener
    public void onGameInit(GameInitializationEvent event) {
        Sponge.getCommandManager().register(this, CraftCommand.getSpec(), "craft", "workbench", "wb");
    }
}
