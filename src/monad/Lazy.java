package monad;

import java.util.*;
import java.util.function.*;

/**
 * Abstraction for lazy initialization of a non-null value.
 *
 * @param <T> the type of the value
 */
public final class Lazy<T> implements Supplier<T> {
    private final Supplier<? extends T> supplier;
    private T value = null;

    Lazy(Supplier<? extends T> supplier) {
        this.supplier = Objects.requireNonNull(supplier);
    }

    /**
     * Creates a new lazy value.
     *
     * @param supplier the function used to calculate the value. This should not return null
     * @param <T>      the type of the encapsulated value
     * @return a fresh lazy value
     */
    public static <T> Lazy<T> of(Supplier<? extends T> supplier) {
        return new Lazy<>(supplier);
    }

    /**
     * Grabs the encapsulated value or calculates it if not already initialized.
     *
     * @return the contained value
     */
    public T get() {
        return value != null ? value : (value = supplier.get());
    }
}
