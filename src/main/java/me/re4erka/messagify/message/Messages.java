package me.re4erka.messagify.message;

import lombok.experimental.UtilityClass;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@UtilityClass
public final class Messages {

    @NotNull
    public List<Message> asList(@NotNull String... contents) {
        final List<Message> messages = new ArrayList<>(contents.length);
        for (String content : contents) {
            final Message message = Message.of(content);
            messages.add(message);
        }

        return Collections.unmodifiableList(messages);
    }
}
