package monad;

import java.util.function.*;

public interface Loop<T> {
    /**
     * Returns a loop that applies {@code body} on a supplied value. This loop will iterate until {@code condition}
     * evaluates to false on the value being iterated over.
     *
     * @param body the operation to be applied in each iteration
     * @param <T>  the type being iterated over
     * @return a while loop
     */
    static <T> Loop<T> whileLoop(Predicate<? super T> condition, UnaryOperator<T> body) {
        return new While<>(body, condition);
    }

    /**
     * Returns a loop that applies {@code body} on a supplied value. This loop will iterate {@code iterations}
     * many times.
     *
     * @param body the operation to be applied in each iteration
     * @param <T>  the type being iterated over
     * @return a for loop
     */
    static <T> Loop<T> forLoop(int iterations, UnaryOperator<T> body) {
        return new For<>(body, iterations);
    }

    /**
     * Applies this loop on {@code value}.
     *
     * @param value the initial value of the iteration
     * @return the result of the last iteration
     */
    T on(T value);
}
