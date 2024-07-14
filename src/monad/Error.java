package monad;

import java.util.*;
import java.util.function.*;

public record Error<V, E>(E value) implements Result<V, E> {
    public Error(E value) {
        this.value = Objects.requireNonNull(value);
    }

    @Override
    public V unwrap() {
        throw new NoSuchElementException("This is an value object");
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
    public <T extends Throwable> V unwrapOrElseThrow(Function<E, T> exception) throws T {
        throw exception.apply(value);
    }

    @Override
    public E unwrapError() {
        return value;
    }

    @Override
    public E unwrapErrorOr(E other) {
        return value;
    }

    @Override
    public E unwrapErrorOrElse(Supplier<? extends E> other) {
        return value;
    }

    @Override
    public Option<V> ok() {
        return Option.none();
    }

    @Override
    public Option<E> error() {
        return Option.of(value);
    }

    @SuppressWarnings("unchecked")
    @Override
    public <U> Result<U, E> map(Function<? super V, U> mapper) {
        return (Result<U, E>) this;
    }

    @SuppressWarnings("unchecked")
    @Override
    public <U> Result<U, ? super E> andThen(Function<? super V, ? extends Result<U, ? super E>> mapper) {
        return (Result<U, E>) this;
    }

    @Override
    public <R> Result<V, R> mapError(Function<? super E, R> mapper) {
        return new Error<>(mapper.apply(value));
    }

    @Override
    public <R> Result<? super V, R> orElse(Function<? super E, ? extends Result<? super V, R>> mapper) {
        return mapper.apply(value);
    }

    @Override
    public Result<V, E> attempt(Consumer<? super V> action) {
        return this;
    }

    @Override
    public Result<V, E> attemptError(Consumer<? super E> action) {
        action.accept(value);
        return this;
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
        return predicate.test(value);
    }
}
