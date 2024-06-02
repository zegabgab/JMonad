package monad;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

class LoopTest {

    @Test
    void loopWhile() {
        assertAll(
                () -> assertEquals(
                        10,
                        Loop.of((Integer n) -> n + 1).loopWhile(n -> n < 10).on(3)
                ),
                () -> assertEquals(
                        69,
                        Loop.of((Integer n) -> n + 1).loopWhile(n -> n < 10).on(69)
                )
        );
    }

    @Test
    void loopFor() {
        assertEquals(
                420,
                Loop.of((Integer n) -> n + 10).loopFor(42).on(0)
        );
    }
}