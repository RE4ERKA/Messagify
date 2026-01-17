package me.re4erka.messagify.variable.configuration;

import me.re4erka.messagify.variable.Variable;
import me.re4erka.messagify.variable.Variables;
import me.re4erka.messagify.variable.impl.CommonVariable;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Collections;
import java.util.Locale;
import java.util.Map;
import java.util.Optional;

public interface ConfigurationVariables {

    @Nullable
    Map<String, String> variablesIfPresent();

    @NotNull
    default Map<String, String> variables() {
        final Map<String, String> variables = variablesIfPresent();
        return variables == null ? Collections.emptyMap() : variables;
    }

    @Nullable
    default CommonVariable variableIfPresent(@NotNull String key) {
        final String search = key.toLowerCase(Locale.ROOT);
        final String replacement = variables().get(search);
        if (replacement == null) {
            return null;
        }

        return Variable.common(search, replacement);
    }

    @NotNull
    default Optional<CommonVariable> variable(@NotNull String key) {
        return Optional.ofNullable(
                variableIfPresent(key));
    }
}
