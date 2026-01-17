package me.re4erka.messagify;

import com.google.common.collect.Lists;
import lombok.experimental.UtilityClass;
import org.apache.commons.lang.StringUtils;
import org.bukkit.ChatColor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.annotations.UnmodifiableView;

import java.util.Collections;
import java.util.List;

@UtilityClass
public class Messagify {

    private static final String PREFIX_HEX_COLOR = "§x";

    @NotNull
    public String format(@Nullable String content) {
        if (StringUtils.isBlank(content)) {
            return StringUtils.EMPTY;
        }

        final char[] characters = content.toCharArray();
        final StringBuilder builder = new StringBuilder(content.length());
        for (int i = 0; i < characters.length; i++) {
            final char current = characters[i];

            final int consumedHex = tryParseHexColor(characters, i, builder);
            if (consumedHex > 0) {
                i += consumedHex;
                continue;
            }

            if (current == '&' && i + 1 < characters.length && isColorCode(characters[i + 1])) {
                builder.append('§').append(characters[i + 1]);
                i++;
                continue;
            }

            builder.append(current);
        }

        return builder.toString();
    }

    @NotNull
    @UnmodifiableView
    public List<String> format(@NotNull List<String> contents) {
        final List<String> formatting = Lists.newArrayListWithCapacity(contents.size());
        for (String content : contents) {
            final String formatted = format(content);
            formatting.add(formatted);
        }

        return Collections.unmodifiableList(formatting);
    }

    @NotNull
    public String strip(@NotNull String input) {
        return ChatColor.stripColor(
                format(input));
    }

    private int tryParseHexColor(char[] chars, int i, @NotNull StringBuilder builder) {
        if (chars[i] == '#' && i + 6 < chars.length && isHexColor(chars, i + 1)) {
            appendHex(chars, i + 1, builder);
            return 6;
        }

        if (chars[i] == '&' && i + 7 < chars.length
                && chars[i + 1] == '#' && isHexColor(chars, i + 2)) {
            appendHex(chars, i + 2, builder);
            return 7;
        }

        return 0;
    }

    private void appendHex(char[] chars, int hexStart, @NotNull StringBuilder builder) {
        builder.append(PREFIX_HEX_COLOR);
        for (int j = 0; j < 6; j++) {
            builder.append('§').append(chars[hexStart + j]);
        }
    }

    private boolean isColorCode(char c) {
        return (c >= '0' && c <= '9') || (c >= 'a' && c <= 'f') || (c >= 'A' && c <= 'F')
                || c == 'k' || c == 'l' || c == 'm' || c == 'n' || c == 'o' || c == 'r';
    }

    private boolean isHexColor(char[] chars, int start) {
        if (start + 5 >= chars.length) {
            return false;
        }

        for (int i = start; i < start + 6; i++) {
            if (!isHexChar(chars[i])) {
                return false;
            }
        }

        return true;
    }

    private boolean isHexChar(char c) {
        return (c >= '0' && c <= '9') || (c >= 'a' && c <= 'f') || (c >= 'A' && c <= 'F');
    }
}