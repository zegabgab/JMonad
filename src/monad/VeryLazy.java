package monad;

import java.util.function.*;

final class VeryLazy<T> implements Lazy<T> {
    private T value = null;
    private final Supplier<? extends T> supplier;

    VeryLazy(Supplier<? extends T> supplier) {
        this.supplier = supplier;
    }

    @Override
    public T get() {
        return value != null ? value : (value = supplier.get());
    }
}