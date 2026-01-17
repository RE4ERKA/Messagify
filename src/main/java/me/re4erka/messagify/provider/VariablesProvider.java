package me.re4erka.messagify.provider;

import me.re4erka.messagify.variable.Variables;
import org.jetbrains.annotations.NotNull;

public interface VariablesProvider {

    @NotNull
    Variables.Builder variablesBuilder();

    @NotNull
    default Variables toVariables() {
        return variablesBuilder().build();
    }
}
