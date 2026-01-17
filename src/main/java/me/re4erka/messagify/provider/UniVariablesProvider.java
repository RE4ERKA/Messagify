package me.re4erka.messagify.provider;

import me.re4erka.messagify.variable.Variables;
import org.jetbrains.annotations.NotNull;

public interface UniVariablesProvider<A> {

    @NotNull
    Variables.Builder variablesBuilder(A a);

    @NotNull
    default Variables toVariables(A a) {
        return variablesBuilder(a).build();
    }
}
