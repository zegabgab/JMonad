package monad;

import java.util.*;
import java.util.function.*;

final class Ok<V, E> implements Result<V, E> {
    private final V value;

    Ok(V value) {
        this.value = Objects.requireNonNull(value);
    }

    @Override
    public V unwrap() {
        return value;
    }

    @Override
    public V unwrapOr(V other) {
        return value;
    }

    @Override
    public V unwrapOrElse(Supplier<? extends V> other) {
        return value;
    }

    @Override
    public E unwrapError() {
        throw new NoSuchElementException("No error occurred");
    }

    @Override
    public E unwrapErrorOr(E other) {
        return other;
    }

    @Override
    public E unwrapErrorOrElse(Supplier<? extends E> other) {
        return other.get();
    }

    @Override
    public <U> Result<U, E> map(Function<? super V, U> mapper) {
        return new Ok<>(mapper.apply(value));
    }

    @Override
    public <U> Result<U, E> andThen(Function<? super V, ? extends Result<U, E>> mapper) {
        return mapper.apply(value);
    }

    @SuppressWarnings("unchecked")
    @Override
    public <R> Result<V, R> mapError(Function<? super E, R> mapper) {
        return (Result<V, R>) this;
    }

    @SuppressWarnings("unchecked")
    @Override
    public <R> Result<V, R> orElse(Function<? super E, ? extends Result<V, R>> mapper) {
        return (Result<V, R>) this;
    }

    @Override
    public boolean isOk() {
        return true;
    }

    @Override
    public boolean isError() {
        return false;
    }

    @Override
    public boolean isOkAnd(Predicate<? super V> predicate) {
        return predicate.test(value);
    }

    @Override
    public boolean isErrorAnd(Predicate<? super E> predicate) {
        return false;
    }
}
