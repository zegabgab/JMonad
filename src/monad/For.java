package monad;

import java.util.function.*;

public record For<T>(UnaryOperator<T> operator, int iterations) implements Loop<T> {
    private T iterate(T value, int remaining) {
        return remaining > 0 ?
                iterate(operator.apply(value), remaining - 1) :
                value;
    }

    @Override
    public T on(T value) {
        return iterate(value, iterations);
    }
}
