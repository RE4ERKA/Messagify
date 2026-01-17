package me.re4erka.messagify.variable.impl;

import me.re4erka.messagify.variable.Variable;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class CommonVariable extends Variable {

    private final String replacement;

    public CommonVariable(@NotNull String search, @NotNull String replacement) {
        super(search);
        this.replacement = replacement;
    }

    @Override
    public String apply(@Nullable CommandSender ignored) {
        return replacement;
    }

    public String replacement() {
        return replacement;
    }

    @Override
    public String toString() {
        return "CommonVariable{" +
                "search='" + search + '\'' +
                ", replacement='" + replacement + '\'' +
                '}';
    }
}
