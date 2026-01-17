package me.re4erka.messagify.serializer;

import de.exlll.configlib.Serializer;
import me.re4erka.messagify.variable.Variables;
import org.jetbrains.annotations.NotNull;

import java.util.Map;

public class VariablesSerializer implements Serializer<Variables, Map<String, String>> {

    @NotNull
    @Override
    public Map<String, String> serialize(@NotNull Variables variables) {
        return variables.toMap();
    }

    @NotNull
    @Override
    public Variables deserialize(@NotNull Map<String, String> map) {
        final Variables.Builder builder = Variables.builder();
        for (Map.Entry<String, String> entry : map.entrySet()) {
            builder.add(entry.getKey(), entry.getValue());
        }

        return builder.build();
    }
}
