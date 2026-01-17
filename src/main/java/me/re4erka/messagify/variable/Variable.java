package me.re4erka.messagify.variable;

import me.re4erka.messagify.variable.impl.CommonVariable;
import me.re4erka.messagify.variable.impl.PlayerVariable;
import org.apache.commons.lang.StringUtils;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.function.Function;

public abstract class Variable {

    protected final String search;

    protected Variable(@NotNull String search) {
        this.search = convert(search);
    }

    public abstract String apply(@Nullable CommandSender who);

    @NotNull
    public String search() {
        return StringUtils.substring(search, 1, -1);
    }

    @NotNull
    private String convert(@NotNull String raw) {
        return '%' + raw + '%';
    }

    public static CommonVariable common(@NotNull String search, @NotNull String replacement) {
        return new CommonVariable(search, replacement);
    }

    public static PlayerVariable player(@NotNull String search, @NotNull Function<Player, String> function) {
        return new PlayerVariable(search, function);
    }
}
