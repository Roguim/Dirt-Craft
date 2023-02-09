package net.dirtcraft.plugin.dirtcraft;

import org.spongepowered.api.command.CommandException;
import org.spongepowered.api.command.CommandResult;
import org.spongepowered.api.command.CommandSource;
import org.spongepowered.api.command.args.CommandContext;
import org.spongepowered.api.command.spec.CommandExecutor;
import org.spongepowered.api.command.spec.CommandSpec;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.event.cause.Cause;
import org.spongepowered.api.event.cause.NamedCause;
import org.spongepowered.api.item.inventory.Inventory;
import org.spongepowered.api.item.inventory.InventoryArchetypes;
import org.spongepowered.api.text.Text;
import org.spongepowered.api.text.channel.MessageChannel;
import org.spongepowered.api.text.format.TextColors;

import java.util.function.Supplier;

public class CraftCommand implements CommandExecutor {
    @Override
    public CommandResult execute(CommandSource src, CommandContext args) throws CommandException {
        if (!(src instanceof Player)) {
            MessageChannel.fixed(src).send(Text.join(Text.of(TextColors.GRAY, "["), Text.of(TextColors.RED, "Dirt-Craft"), Text.of(TextColors.GRAY, "] "), Text.of("This command can only be executed by a player!")));
            return CommandResult.empty();
        }

        Player player = (Player) src;
        if(!player.hasPermission("nucleus.workbench.base") && !player.hasPermission("nucleus.admin")) {
            MessageChannel.fixed(src).send(Text.join(Text.of(TextColors.GRAY, "["), Text.of(TextColors.RED, "Dirt-Craft"), Text.of(TextColors.GRAY, "] "), Text.of("You don't have permission to execute this command!")));
            return CommandResult.empty();
        }

        MessageChannel.fixed(src).send(Text.join(Text.of(TextColors.GRAY, "["), Text.of(TextColors.RED, "Dirt-Craft"), Text.of(TextColors.GRAY, "] "), Text.of("Heads up - Don't close this inventory with items in it! They will be deleted!")));
        player.openInventory(Inventory.builder().of(InventoryArchetypes.WORKBENCH).build(this), Cause.of(NamedCause.of("Craft Command", this)))
        .orElseThrow(() -> {return new CommandException(Text.of("Craft command had an error somewhere."));});
        return CommandResult.success();
    }


    public static CommandSpec getSpec() {
        return CommandSpec.builder().executor(new CraftCommand()).description(Text.of("Open a crafting table")).build();
    }
}
