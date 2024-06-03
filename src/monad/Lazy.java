package monad;

import java.util.function.*;

public interface Lazy<T> {
    static <T> Lazy<T> of(Supplier<? extends T> supplier) {
        return new VeryLazy<>(supplier);
    }
    T get();
}
