package me.re4erka.messagify.variable.impl;

import me.re4erka.messagify.variable.Variable;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.function.Function;

public class PlayerVariable extends Variable {

    private final Function<Player, String> function;

    public PlayerVariable(@NotNull String search, @NotNull Function<Player, String> function) {
        super(search);
        this.function = function;
    }

    @Override
    public String apply(@Nullable CommandSender who) {
        if (who instanceof Player) {
            final Player player = (Player) who;
            return function.apply(player);
        }

        throw new IllegalArgumentException("CommandSender is not a player");
    }

    @Override
    public String toString() {
        return "PlayerVariable{" +
                "search='" + search + '\'' +
                '}';
    }
}
