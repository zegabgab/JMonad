package monad;

import java.util.function.*;

/**
 * No idea how loop monads work yet, we'll have to research.
 */
public interface Loop<T> {
    static <T> Loop<T> on(T value) {
        return new WhileLoop<>(value);
    }
    T unwrap();
    Loop<T> loop(UnaryOperator<T> operator, Predicate<? super T> during);
}

final class WhileLoop<T> implements Loop<T> {
    final T value;

    WhileLoop(T value) {
        this.value = value;
    }

    @Override
    public T unwrap() {
        return value;
    }

    @Override
    public Loop<T> loop(UnaryOperator<T> operator, Predicate<? super T> during) {
        T looped = value;
        while (during.test(looped)) {
            looped = operator.apply(looped);
        }
        return Loop.on(looped);
    }
}