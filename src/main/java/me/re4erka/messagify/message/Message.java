package me.re4erka.messagify.message;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import me.re4erka.messagify.Messagify;
import me.re4erka.messagify.variable.Variables;
import org.apache.commons.lang.StringUtils;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.jetbrains.annotations.NotNull;

import java.util.concurrent.TimeUnit;

public class Message implements CharSequence {

    private final String content;

    private static final Cache<CommandSender, String> COOLDOWN = CacheBuilder.newBuilder()
            .expireAfterWrite(1, TimeUnit.SECONDS)
            .build();

    private Message(@NotNull String content) {
        this.content = content;
    }

    public void sendTo(@NotNull CommandSender sender) {
        if (isBlank()) {
            return;
        }

        sendMessage(sender, content);
    }

    public void sendTo(@NotNull CommandSender sender, @NotNull Variables variables) {
        if (isBlank()) {
            return;
        }

        sendMessage(sender, variables.process(sender, content));
    }

    public void sendThrottled(@NotNull CommandSender sender) {
        if (isBlank()) {
            return;
        }

        sendThrottled(sender, content);
    }

    public void sendThrottled(@NotNull CommandSender sender, @NotNull Variables variables) {
        if (isBlank()) {
            return;
        }

        sendThrottled(sender, variables.process(sender, content));
    }

    @Override
    public int length() {
        return content.length();
    }

    @Override
    public char charAt(int index) {
        return content.charAt(index);
    }

    @NotNull
    @Override
    public CharSequence subSequence(int start, int end) {
        return content.subSequence(start, end);
    }

    @NotNull
    @Override
    public String toString() {
        return content;
    }

    public boolean isBlank() {
        return StringUtils.isBlank(content);
    }

    private void sendMessage(@NotNull CommandSender sender, @NotNull String content) {
        sender.sendMessage(isConsoleSender(sender)
                ? Messagify.strip(content) : Messagify.format(content));
    }

    private void sendThrottled(@NotNull CommandSender sender, @NotNull String message) {
        final String lastMessage = COOLDOWN.getIfPresent(sender);
        if (message.equals(lastMessage)) {
            return;
        }

        COOLDOWN.put(sender, message);
        sendMessage(sender, message);
    }

    private boolean isConsoleSender(@NotNull CommandSender sender) {
        return Bukkit.getConsoleSender() == sender
                || sender instanceof ConsoleCommandSender;
    }

    @NotNull
    public static Message of(@NotNull String content) {
        return new Message(content);
    }
}
