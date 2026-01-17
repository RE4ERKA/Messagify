package me.re4erka.messagify.provider;

import me.re4erka.messagify.variable.Variables;
import org.jetbrains.annotations.NotNull;

public interface BiVariablesProvider<A, B> {

    @NotNull
    Variables.Builder variablesBuilder(A a, B b);

    @NotNull
    default Variables toVariables(A a, B b) {
        return variablesBuilder(a, b).build();
    }
}
