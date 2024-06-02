package monad;

import java.util.*;
import java.util.function.*;

final class None<T> implements Option<T> {
    private final static None<?> NONE = new None<>();

    private None() {
    }

    @SuppressWarnings("unchecked")
    static <T> None<T> none() {
        return (None<T>) NONE;
    }

    @Override
    public T unwrap() {
        throw new NoSuchElementException("No value present");
    }

    @Override
    public T unwrapOr(T other) {
        return other;
    }

    @Override
    public T unwrapOrElse(Supplier<? extends T> other) {
        return other.get();
    }

    @Override
    public <U> Option<U> map(Function<? super T, U> mapper) {
        return none();
    }

    @Override
    public <U> Option<U> andThen(Function<? super T, ? extends Option<U>> mapper) {
        return none();
    }

    @Override
    public Option<T> filter(Predicate<? super T> predicate) {
        return none();
    }

    @Override
    public Option<T> orElse(Supplier<? extends Option<T>> supplier) {
        return supplier.get();
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof None<?>;
    }

    @Override
    public String toString() {
        return "None";
    }
}
