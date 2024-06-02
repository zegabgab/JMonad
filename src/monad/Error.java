package monad;

import java.util.*;
import java.util.function.*;

final class Error<V,E> implements Result<V,E> {
    private final E error;

    Error(E error) {
        this.error = Objects.requireNonNull(error);
    }

    @Override
    public V unwrap() {
        throw new NoSuchElementException("This is an error object");
    }

    @Override
    public V unwrapOr(V other) {
        return other;
    }

    @Override
    public V unwrapOrElse(Supplier<? extends V> other) {
        return other.get();
    }

    @Override
    public E unwrapError() {
        return error;
    }

    @Override
    public E unwrapErrorOr(E other) {
        return error;
    }

    @Override
    public E unwrapErrorOrElse(Supplier<? extends E> other) {
        return error;
    }

    @SuppressWarnings("unchecked")
    @Override
    public <U> Result<U, E> map(Function<? super V, U> mapper) {
        return (Result<U, E>) this;
    }

    @SuppressWarnings("unchecked")
    @Override
    public <U> Result<U, E> andThen(Function<? super V, ? extends Result<U, E>> mapper) {
        return (Result<U, E>) this;
    }

    @Override
    public <R> Result<V, R> mapError(Function<? super E, R> mapper) {
        return new Error<>(mapper.apply(error));
    }

    @Override
    public <R> Result<V, R> orElse(Function<? super E, ? extends Result<V, R>> mapper) {
        return mapper.apply(error);
    }
}
