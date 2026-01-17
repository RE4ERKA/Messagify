package me.re4erka.messagify.variable;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import me.re4erka.messagify.result.VariableResult;
import me.re4erka.messagify.variable.configuration.ConfigurationVariables;
import me.re4erka.messagify.variable.impl.CommonVariable;
import org.apache.commons.lang.StringUtils;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.annotations.UnmodifiableView;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;

public class Variables {

    private final Set<Variable> variables;

    private static final Variables EMPTY = new Variables(Collections.emptySet());

    public Variables(@NotNull Set<Variable> variables) {
        this.variables = variables;
    }

    @NotNull
    public String process(@NotNull String content) {
        return process(null, content);
    }

    @NotNull
    public String process(@Nullable CommandSender sender, @NotNull String content) {
        if (EMPTY == this) {
            return content;
        }

        String process = content;
        for (Variable variable : variables) {
            process = StringUtils.replaceOnce(process,
                    variable.search, variable.apply(sender));
        }

        return process;
    }

    @NotNull
    @UnmodifiableView
    public List<String> process(@NotNull List<String> contents) {
        return process(null, contents);
    }

    @NotNull
    @UnmodifiableView
    public List<String> process(@Nullable CommandSender sender, @NotNull List<String> contents) {
        final List<String> process = Lists.newArrayListWithCapacity(contents.size());
        for (String content : contents) {
            final String processed = process(sender, content);
            process.add(processed);
        }

        return Collections.unmodifiableList(process);
    }

    @NotNull
    @UnmodifiableView
    public Map<String, String> toMap() {
        return toMap(false);
    }

    @NotNull
    @UnmodifiableView
    public Map<String, String> toMap(boolean skipUnsupported) {
        if (this == EMPTY) {
            return Collections.emptyMap();
        }

        final Map<String, String> map = Maps.newHashMapWithExpectedSize(variables.size());
        for (Variable variable : variables) {
            if (variable instanceof CommonVariable) {
                final CommonVariable commonVariable = (CommonVariable) variable;
                map.put(variable.search(), commonVariable.replacement());
            } else if (!skipUnsupported) {
                throw new IllegalArgumentException("Converting to map support only CommonVariable");
            }
        }

        return Collections.unmodifiableMap(map);
    }

    @NotNull
    public Builder toBuilder() {
        return new Builder(
                Sets.newHashSet(variables));
    }

    @Override
    public String toString() {
        return "Variables{" +
                "variables=" + variables +
                '}';
    }

    @NotNull
    public static Variables of(@NotNull String search, @NotNull String replacement) {
        return new Variables(Sets.newHashSet(
                Variable.common(search, replacement)));
    }

    @NotNull
    public static Variables of(@NotNull String search1, @NotNull String replacement1,
                               @NotNull String search2, @NotNull String replacement2) {
        return new Variables(Sets.newHashSet(
                Variable.common(search1, replacement1), Variable.common(search2, replacement2)));
    }

    @NotNull
    public static Variables of(@NotNull String search1, @NotNull String replacement1,
                               @NotNull String search2, @NotNull String replacement2,
                               @NotNull String search3, @NotNull String replacement3) {
        return new Variables(Sets.newHashSet(
                Variable.common(search1, replacement1), Variable.common(search2, replacement2),
                Variable.common(search3, replacement3)));
    }

    @NotNull
    public static Variables of(@NotNull String search1, @NotNull String replacement1,
                               @NotNull String search2, @NotNull String replacement2,
                               @NotNull String search3, @NotNull String replacement3,
                               @NotNull String search4, @NotNull String replacement4) {
        return new Variables(Sets.newHashSet(
                Variable.common(search1, replacement1), Variable.common(search2, replacement2),
                Variable.common(search3, replacement3), Variable.common(search4, replacement4)));
    }

    @NotNull
    public static Variables of(@NotNull String search1, @NotNull String replacement1,
                               @NotNull String search2, @NotNull String replacement2,
                               @NotNull String search3, @NotNull String replacement3,
                               @NotNull String search4, @NotNull String replacement4,
                               @NotNull String search5, @NotNull String replacement5) {
        return new Variables(Sets.newHashSet(
                Variable.common(search1, replacement1), Variable.common(search2, replacement2),
                Variable.common(search3, replacement3), Variable.common(search4, replacement4),
                Variable.common(search5, replacement5)));
    }

    @NotNull
    public static Variables of(@NotNull String search1, @NotNull String replacement1,
                               @NotNull String search2, @NotNull String replacement2,
                               @NotNull String search3, @NotNull String replacement3,
                               @NotNull String search4, @NotNull String replacement4,
                               @NotNull String search5, @NotNull String replacement5,
                               @NotNull String search6, @NotNull String replacement6) {
        return new Variables(Sets.newHashSet(
                Variable.common(search1, replacement1), Variable.common(search2, replacement2),
                Variable.common(search3, replacement3), Variable.common(search4, replacement4),
                Variable.common(search5, replacement5), Variable.common(search6, replacement6)));
    }

    @NotNull
    public static Variables of(@NotNull String search1, @NotNull String replacement1,
                               @NotNull String search2, @NotNull String replacement2,
                               @NotNull String search3, @NotNull String replacement3,
                               @NotNull String search4, @NotNull String replacement4,
                               @NotNull String search5, @NotNull String replacement5,
                               @NotNull String search6, @NotNull String replacement6,
                               @NotNull String search7, @NotNull String replacement7) {
        return new Variables(Sets.newHashSet(
                Variable.common(search1, replacement1), Variable.common(search2, replacement2),
                Variable.common(search3, replacement3), Variable.common(search4, replacement4),
                Variable.common(search5, replacement5), Variable.common(search6, replacement6),
                Variable.common(search7, replacement7)));
    }

    @NotNull
    public static Variables of(@NotNull String search1, @NotNull String replacement1,
                               @NotNull String search2, @NotNull String replacement2,
                               @NotNull String search3, @NotNull String replacement3,
                               @NotNull String search4, @NotNull String replacement4,
                               @NotNull String search5, @NotNull String replacement5,
                               @NotNull String search6, @NotNull String replacement6,
                               @NotNull String search7, @NotNull String replacement7,
                               @NotNull String search8, @NotNull String replacement8) {
        return new Variables(Sets.newHashSet(
                Variable.common(search1, replacement1), Variable.common(search2, replacement2),
                Variable.common(search3, replacement3), Variable.common(search4, replacement4),
                Variable.common(search5, replacement5), Variable.common(search6, replacement6),
                Variable.common(search7, replacement7), Variable.common(search8, replacement8)));
    }

    @NotNull
    public static Variables of(@NotNull String search1, @NotNull String replacement1,
                               @NotNull String search2, @NotNull String replacement2,
                               @NotNull String search3, @NotNull String replacement3,
                               @NotNull String search4, @NotNull String replacement4,
                               @NotNull String search5, @NotNull String replacement5,
                               @NotNull String search6, @NotNull String replacement6,
                               @NotNull String search7, @NotNull String replacement7,
                               @NotNull String search8, @NotNull String replacement8,
                               @NotNull String search9, @NotNull String replacement9) {
        return new Variables(Sets.newHashSet(
                Variable.common(search1, replacement1), Variable.common(search2, replacement2),
                Variable.common(search3, replacement3), Variable.common(search4, replacement4),
                Variable.common(search5, replacement5), Variable.common(search6, replacement6),
                Variable.common(search7, replacement7), Variable.common(search8, replacement8),
                Variable.common(search9, replacement9)));
    }

    @NotNull
    public static Variables of(@NotNull String search1, @NotNull String replacement1,
                               @NotNull String search2, @NotNull String replacement2,
                               @NotNull String search3, @NotNull String replacement3,
                               @NotNull String search4, @NotNull String replacement4,
                               @NotNull String search5, @NotNull String replacement5,
                               @NotNull String search6, @NotNull String replacement6,
                               @NotNull String search7, @NotNull String replacement7,
                               @NotNull String search8, @NotNull String replacement8,
                               @NotNull String search9, @NotNull String replacement9,
                               @NotNull String search10, @NotNull String replacement10) {
        return new Variables(Sets.newHashSet(
                Variable.common(search1, replacement1), Variable.common(search2, replacement2),
                Variable.common(search3, replacement3), Variable.common(search4, replacement4),
                Variable.common(search5, replacement5), Variable.common(search6, replacement6),
                Variable.common(search7, replacement7), Variable.common(search8, replacement8),
                Variable.common(search9, replacement9), Variable.common(search10, replacement10)));
    }

    @NotNull
    public static Variables ofEntries(@NotNull String... entries) {
        if (entries.length % 2 != 0) {
            throw new IllegalArgumentException("Odd number of arguments passed. Key-value pairs expected");
        }

        final Set<Variable> variables = Sets.newHashSetWithExpectedSize(entries.length / 2);
        for (int i = 0; i < entries.length; i += 2) {
            final String search = entries[i];
            final String replacement = entries[i + 1];

            final Variable variable = Variable.common(search, replacement);
            variables.add(variable);
        }

        return new Variables(variables);
    }

    @NotNull
    public static Variables empty() {
        return EMPTY;
    }

    @NotNull
    public static Builder builder() {
        return new Builder(
                Sets.newHashSet());
    }

    public static class Builder {

        private final Set<Variable> variables;

        public Builder(@NotNull Set<Variable> variables) {
            this.variables = variables;
        }

        @NotNull
        public Builder add(@NotNull String search, @Nullable String replacement) {
            return add(search, replacement, this::notNull);
        }

        @NotNull
        public Builder add(@NotNull String search, @Nullable VariableResult wrapper) {
            return add(search, wrapper, this::notNull);
        }

        @NotNull
        public Builder add(@NotNull String search, @Nullable Byte replacement) {
            return add(search, replacement, this::notNullToString);
        }

        @NotNull
        public Builder add(@NotNull String search, @Nullable Integer replacement) {
            return add(search, replacement, this::notNullToString);
        }

        @NotNull
        public Builder add(@NotNull String search, @Nullable Double replacement) {
            return add(search, replacement, this::notNullToString);
        }

        @NotNull
        public Builder add(@NotNull String search, @Nullable Float replacement) {
            return add(search, replacement, this::notNullToString);
        }

        @NotNull
        public Builder add(@NotNull String search, @Nullable Character replacement) {
            return add(search, replacement, this::notNullToString);
        }

        @NotNull
        public Builder add(@NotNull String search, byte replacement) {
            return add(search, replacement, v -> Byte.toString(v));
        }

        @NotNull
        public Builder add(@NotNull String search, int replacement) {
            return add(search, replacement, v -> Integer.toString(v));
        }

        @NotNull
        public Builder add(@NotNull String search, long replacement) {
            return add(search, replacement, v -> Long.toString(v));
        }

        @NotNull
        public Builder add(@NotNull String search, double replacement) {
            return add(search, replacement, v -> Double.toString(v));
        }

        @NotNull
        public Builder add(@NotNull String search, float replacement) {
            return add(search, replacement, v -> Float.toString(v));
        }

        @NotNull
        public Builder add(@NotNull String search, char replacement) {
            return add(search, replacement, v -> Character.toString(v));
        }

        @NotNull
        public Builder add(@NotNull String search, @NotNull Function<Player, String> function) {
            final Variable variable = Variable.player(search, function);
            return add(variable);
        }

        @NotNull
        public Builder addAll(@NotNull Variables variables) {
            variables.variables.forEach(this::add);
            return this;
        }

        @NotNull
        public Builder addAll(@NotNull ConfigurationVariables variables) {
            variables.variables().forEach((key, value) -> {
                final Variable variable = Variable.common(key, value);
                add(variable);
            });
            return this;
        }

        @NotNull
        public Builder replaceAll(@NotNull ConfigurationVariables variables) {
            variables.variables().forEach((key, value) -> {
                final Variable variable = Variable.common(key, value);
                this.variables.removeIf(v -> v.search.equals(variable.search));
                this.variables.add(variable);
            });
            return this;
        }

        @NotNull
        public Builder remove(@NotNull String search) {
            variables.removeIf(variable -> search.equals(variable.search));
            return this;
        }

        @NotNull
        public Variables build() {
            if (variables.isEmpty()) {
                return empty();
            }

            return new Variables(
                    Collections.unmodifiableSet(variables));
        }

        private <V> Builder add(@NotNull String search, V value, @NotNull Function<V, String> toStringer) {
            throwIfSearchAlreadyContained(search);
            final Variable variable = Variable.common(
                    search, toStringer.apply(value));

            variables.add(variable);
            return this;
        }

        private Builder add(@NotNull Variable variable) {
            throwIfSearchAlreadyContained(variable.search);
            variables.add(variable);
            return this;
        }

        @NotNull
        private String notNull(@Nullable String value) {
            return toStringOrEmpty(value, v -> v);
        }

        @NotNull
        private String notNull(@Nullable VariableResult value) {
            return toStringOrEmpty(value, VariableResult::result);
        }

        @NotNull
        private String notNullToString(@Nullable Character value) {
            return toStringOrEmpty(value, v -> Character.toString(v));
        }

        @NotNull
        private String notNullToString(@Nullable Byte value) {
            return toStringOrEmpty(value, v -> Byte.toString(v));
        }

        @NotNull
        private String notNullToString(@Nullable Integer value) {
            return toStringOrEmpty(value, v -> Integer.toString(v));
        }

        @NotNull
        private String notNullToString(@Nullable Double value) {
            return toStringOrEmpty(value, v -> Double.toString(v));
        }

        @NotNull
        private String notNullToString(@Nullable Long value) {
            return toStringOrEmpty(value, v -> Long.toString(v));
        }

        @NotNull
        private String notNullToString(@Nullable Float value) {
            return toStringOrEmpty(value, v -> Float.toString(v));
        }

        private <V> String toStringOrEmpty(@Nullable V value, @NotNull Function<V, String> toStringer) {
            return value == null ? StringUtils.EMPTY : toStringer.apply(value);
        }

        private void throwIfSearchAlreadyContained(@NotNull String other) {
            for (Variable variable : variables) {
                final String current = variable.search;
                if (current.equals(other)) {
                    throw new IllegalArgumentException("Duplicate search key detected: \"" + current+ "\"");
                }
            }
        }
    }
}
