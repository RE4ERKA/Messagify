package me.re4erka.messagify.message;

import me.re4erka.messagify.Messagify;
import me.re4erka.messagify.variable.Variables;
import org.apache.commons.lang.StringUtils;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.jetbrains.annotations.NotNull;

public class Message implements CharSequence {

    private final String content;

    private static final Message EMPTY = of(StringUtils.EMPTY);

    private Message(@NotNull String content) {
        this.content = content;
    }

    public void sendTo(@NotNull CommandSender sender) {
        if (isEmptyOrBlank()) {
            return;
        }

        sendMessage(sender, content);
    }

    public void sendTo(@NotNull CommandSender sender, @NotNull Variables variables) {
        if (isEmptyOrBlank()) {
            return;
        }

        sendMessage(sender, variables.process(sender, content));
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

    public boolean isEmptyOrBlank() {
        return this == EMPTY
                || StringUtils.isBlank(content);
    }

    private void sendMessage(@NotNull CommandSender sender, @NotNull String content) {
        sender.sendMessage(isConsoleSender(sender)
                ? Messagify.strip(content) : Messagify.format(content));
    }

    private boolean isConsoleSender(@NotNull CommandSender sender) {
        return Bukkit.getConsoleSender() == sender
                || sender instanceof ConsoleCommandSender;
    }

    @NotNull
    public static Message of(@NotNull String content) {
        return StringUtils.isBlank(content)
                ? empty() : new Message(content);
    }

    @NotNull
    public static Message empty() {
        return EMPTY;
    }
}
