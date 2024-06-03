package monad;

import java.util.*;
import java.util.function.*;

final class Lazy<T> {
    private T value = null;
    private final Supplier<? extends T> supplier;

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