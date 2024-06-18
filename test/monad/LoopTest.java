package monad;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

class LoopTest {

    @Test
    void whileLoop() {
        assertAll(
                () -> assertEquals(
                        10,
                        Loop.whileLoop(n -> n < 10, (Integer n) -> n + 1).on(3)
                ),
                () -> assertEquals(
                        69,
                        Loop.whileLoop(n -> n < 10, (Integer n) -> n + 1).on(69)
                )
        );
    }

    @Test
    void forLoop() {
        assertEquals(
                420,
                Loop.forLoop(42, (Integer n) -> n + 10).on(0)
        );
    }
}