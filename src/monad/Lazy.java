package monad;

import java.util.*;
import java.util.function.*;

public final class Lazy<T> implements Supplier<T> {
    private final Supplier<? extends T> supplier;
    private T value = null;

    Lazy(Supplier<? extends T> supplier) {
        this.supplier = Objects.requireNonNull(supplier);
    }

    public static <T> Lazy<T> of(Supplier<? extends T> supplier) {
        return new Lazy<>(supplier);
    }

    public T get() {
        return value != null ? value : (value = supplier.get());
    }
}
