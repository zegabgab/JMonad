package monad;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

class OptionTest {

    @Test
    void map() {
        var expected = new Option[]{
                Option.of(23),
                Option.none()
        };
        var given = new Option[]{
                Option.of(69).map(x -> x / 3),
                Option.none().map(x -> (Integer) x / 3)
        };

        assertArrayEquals(expected, given);
    }

    @Test
    void andThen() {
        var expected = new Option[]{
                Option.of(69),
                Option.none(),
                Option.none(),
                Option.none()
        };
        var given = new Option[]{
                Option.of(23).andThen(n -> Option.of(n * 3)),
                Option.of(420).andThen(n -> Option.none()),
                Option.none().andThen(n -> Option.of(69)),
                Option.none().andThen(n -> Option.none())
        };

        assertArrayEquals(expected, given);
    }

    @Test
    void filter() {
        var expected = new Option[]{
                Option.of(69),
                Option.none()
        };
        var given = new Option[]{
                Option.of(69).filter(n -> n % 2 != 0),
                Option.of(69).filter(n -> n % 2 == 0)
        };

        assertArrayEquals(expected, given);
    }

    @Test
    void orElse() {
        var expected = new Option[]{
                Option.of(69),
                Option.of(420)
        };
        var given = new Option[]{
                Option.of(69).orElse(() -> Option.of(420)),
                Option.none().orElse(() -> Option.of(420))
        };

        assertArrayEquals(expected, given);
    }

    @Test
    void string() {
        var expected = new String[]{
                Option.of(69).toString(),
                Option.none().toString()
        };
        var given = new String[]{
                "One(69)",
                "None"
        };

        assertArrayEquals(expected, given);
    }
}
