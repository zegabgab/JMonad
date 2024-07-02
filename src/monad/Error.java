package monad;

import java.util.*;
import java.util.function.*;

public record Error<V, E>(E error) implements Result<V, E> {
    public Error(E error) {
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
    public <T extends Throwable> V unwrapOrThrow(T exception) throws T {
        throw exception;
    }

    @Override
    public <T extends Throwable> V unwrapOrElseThrow(Supplier<T> exception) throws T {
        throw exception.get();
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

    @Override
    public boolean isOk() {
        return false;
    }

    @Override
    public boolean isError() {
        return true;
    }

    @Override
    public boolean isOkAnd(Predicate<? super V> predicate) {
        return false;
    }

    @Override
    public boolean isErrorAnd(Predicate<? super E> predicate) {
        return predicate.test(error);
    }
}
