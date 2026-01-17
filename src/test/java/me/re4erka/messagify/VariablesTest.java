package me.re4erka.messagify;

import be.seeseemelk.mockbukkit.MockBukkit;
import be.seeseemelk.mockbukkit.ServerMock;
import me.re4erka.messagify.variable.Variables;
import org.apache.commons.lang.StringUtils;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@DisplayName("Variables Tests")
public class VariablesTest {

    private ServerMock server;
    private Player player;

    @BeforeEach
    public void setUp() {
        server = MockBukkit.mock();
        player = server.addPlayer("RE4ERKA");
    }

    @Test
    public void testOf() {
        final String content = "%test_1% %test_2% %test_3% %test_4%";
        final Variables variables = Variables.of("test_1", "result_1",
                "test_2", "result_2", "test_3", "result_3");

        assertEquals(variables.process(server.getConsoleSender(), content),
                "result_1 result_2 result_3 %test_4%");
    }

    @Test
    public void testOfEntries() {
        final String content = "%test_1% %test_2% %test_3% %test_4%";
        final Variables variables = Variables.ofEntries("test_1", "result_1",
                "test_2", "result_2", "test_3", "result_3");

        assertEquals(variables.process(server.getConsoleSender(), content),
                "result_1 result_2 result_3 %test_4%");
    }

    @Test
    public void testBuilder() {
        final String content = "%test_1% %test_2% %test_3% %test_4%";
        final Variables variables = Variables.builder().add("test_1", "result_1")
                .add("test_2", "result_2").add("test_3", "result_3").build();

        assertEquals(variables.process(server.getConsoleSender(), content),
                "result_1 result_2 result_3 %test_4%");
    }

    @Test
    public void testBuilderWithDifferentTypes() {
        final String content = "%integer% %long% %double% %float% %character%";
        final Variables variables = Variables.builder().add("integer", Integer.MAX_VALUE)
                .add("long", Long.MAX_VALUE).add("double", Double.MAX_VALUE)
                .add("float", Float.MAX_VALUE).add("character", 'A')
                .build();

        assertEquals(variables.process(server.getConsoleSender(), content),
                "2147483647 9223372036854775807 1.7976931348623157E308 3.4028235E38 A");
    }

    @Test
    public void testBuilderWithNullableTypes() {
        final String content = "%string%%integer%%double%";
        final Variables variables = Variables.builder()
                .add("string", (String) null)
                .add("integer", (Integer) null)
                .add("double", (Double) null)
                .build();

        assertEquals(variables.process(server.getConsoleSender(), content), StringUtils.EMPTY);
    }

    @Test
    public void testBuilderWithoutPlayer() {
        final String content = "Your player name: %player_name%";
        final Variables variables = Variables.builder()
                .add("player_name", HumanEntity::getName)
                .build();

        assertThrows(IllegalArgumentException.class,
                () -> variables.process(server.getConsoleSender(), content));
    }

    @Test
    public void testBuilderWithPlayer() {
        final String content = "Your player name: %player_name%";
        final Variables variables = Variables.builder()
                .add("player_name", HumanEntity::getName)
                .build();

        assertEquals(variables.process(player, content), "Your player name: RE4ERKA");
    }

    @AfterEach
    public void tearDown() {
        MockBukkit.unmock();
    }
}
