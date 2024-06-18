package monad;

import java.util.function.*;

final class For<T> implements Loop<T> {
    private final UnaryOperator<T> operator;
    private final int iterations;

    For(UnaryOperator<T> operator, int iterations) {
        this.operator = operator;
        this.iterations = iterations;
    }

    private T iterate(T value, int remaining) {
        return remaining > 0 ?
                iterate(operator.apply(value), remaining - 1) :
                value;
    }

    @Override
    public UnaryOperator<T> action() {
        return operator;
    }

    @Override
    public T on(T value) {
        return iterate(value, iterations);
    }
}
