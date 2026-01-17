package me.re4erka.messagify.serializer;

import de.exlll.configlib.Serializer;
import me.re4erka.messagify.message.Message;
import org.jetbrains.annotations.NotNull;

public final class MessageSerializer implements Serializer<Message, String> {

    @NotNull
    @Override
    public String serialize(@NotNull Message message) {
        return message.toString();
    }

    @NotNull
    @Override
    public Message deserialize(@NotNull String content) {
        return Message.of(content);
    }
}
