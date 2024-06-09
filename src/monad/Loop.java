package monad;

import java.util.function.*;

public interface Loop<T> {
    /**
     * Returns a loop that applies {@code operator} on a supplied value. This loop will not iterate at all -
     * add a terminating condition to make it useful.
     *
     * @param operator the operation to be applied in each iteration
     * @param <T>      the type being iterated over
     * @return the result of the last iteration
     */
    static <T> Loop<T> of(UnaryOperator<T> operator) {
        return new While<>(operator, value -> false);
    }

    T on(T value);

    Loop<T> loopWhile(Predicate<? super T> condition);

    Loop<T> loopFor(int iterations);
}
