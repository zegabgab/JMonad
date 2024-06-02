package monad;

import java.util.*;
import java.util.function.*;

final class One<T> implements Option<T> {
    private final T value;

    One(T value) {
        this.value = Objects.requireNonNull(value);
    }

    @Override
    public T unwrap() {
        return value;
    }

    @Override
    public T unwrapOr(T other) {
        return value;
    }

    @Override
    public T unwrapOrElse(Supplier<? extends T> other) {
        return value;
    }

    @Override
    public <U> Option<U> map(Function<? super T, U> mapper) {
        return new One<>(mapper.apply(value));
    }

    @Override
    public <U> Option<U> andThen(Function<? super T, ? extends Option<U>> mapper) {
        return mapper.apply(value);
    }

    @Override
    public Option<T> filter(Predicate<? super T> predicate) {
        return predicate.test(value) ? this : None.none();
    }

    @Override
    public Option<T> orElse(Supplier<? extends Option<T>> supplier) {
        return this;
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof One<?> other && other.value.equals(value);
    }

    @Override
    public String toString() {
        return "One(%s)".formatted(value);
    }
}
