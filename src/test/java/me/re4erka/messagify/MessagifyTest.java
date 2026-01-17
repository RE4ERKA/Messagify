package me.re4erka.messagify;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("Messagify Test")
public class MessagifyTest {

    @Test
    public void testFormat() {
        final String formatted = Messagify.format("&aGreen &bAqua &cRed &eYellow &lBold &nUnderline " +
                "&mStrikethrough &oItalic &kObfuscated &r&7Reset to Gray");
        Assertions.assertEquals(formatted, "§aGreen §bAqua §cRed §eYellow §lBold §nUnderline " +
                "§mStrikethrough §oItalic §kObfuscated §r§7Reset to Gray");
    }

    @Test
    public void testFormatWithHex() {
        final String formatted = Messagify.format("#FF0000R #FF7F00A #FFFF00I #00FF00N #0000FFB #4B0082O " +
                "#9400D3W #FF69B4P #FF0000I #FF7F00N #FFFF00K!");
        Assertions.assertEquals(formatted, "§x§F§F§0§0§0§0R §x§F§F§7§F§0§0A §x§F§F§F§F§0§0I §x§0§0§F§F§0§0N " +
                "§x§0§0§0§0§F§FB §x§4§B§0§0§8§2O §x§9§4§0§0§D§3W §x§F§F§6§9§B§4P §x§F§F§0§0§0§0I §x§F§F§7§F§0§0N §x§F§F§F§F§0§0K!");
    }

    @Test
    public void testFormatWithAlternativeHex() {
        final String formatted = Messagify.format("&#FF0000R &#FF7F00A &#FFFF00I &#00FF00N &#0000FFB &#4B0082O " +
                "&#9400D3W &#FF69B4P &#FF0000I &#FF7F00N &#FFFF00K!");
        Assertions.assertEquals(formatted, "§x§F§F§0§0§0§0R §x§F§F§7§F§0§0A §x§F§F§F§F§0§0I §x§0§0§F§F§0§0N " +
                "§x§0§0§0§0§F§FB §x§4§B§0§0§8§2O §x§9§4§0§0§D§3W §x§F§F§6§9§B§4P §x§F§F§0§0§0§0I §x§F§F§7§F§0§0N §x§F§F§F§F§0§0K!");
    }

    @Test
    public void testStrip() {
        final String stripped = Messagify.strip("&aGreen &bAqua &cRed &eYellow &lBold &nUnderline " +
                "&mStrikethrough &oItalic &kObfuscated &r&7Reset to Gray");
        Assertions.assertEquals(stripped, "Green Aqua Red Yellow Bold Underline " +
                "Strikethrough Italic Obfuscated Reset to Gray");
    }

    @Test
    public void testStripWithHex() {
        final String stripped = Messagify.strip("#FF0000R #FF7F00A #FFFF00I #00FF00N #0000FFB #4B0082O " +
                "#9400D3W #FF69B4P #FF0000I #FF7F00N #FFFF00K!");
        Assertions.assertEquals(stripped, "R A I N B O W P I N K!");
    }
}
