package monad;

import java.util.*;
import java.util.function.*;

public record Ok<V, E>(V value) implements Result<V, E> {
    public Ok(V value) {
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
    public <T extends Throwable> V unwrapOrThrow(T exception) {
        return value;
    }

    @Override
    public <T extends Throwable> V unwrapOrElseThrow(Function<E, T> exception) {
        return value;
    }

    @Override
    public E unwrapError() {
        throw new NoSuchElementException("No value occurred");
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
    public Option<V> ok() {
        return Option.of(value);
    }

    @Override
    public Option<E> error() {
        return Option.none();
    }

    @Override
    public <U> Result<U, E> map(Function<? super V, U> mapper) {
        return new Ok<>(mapper.apply(value));
    }

    @Override
    public <U> Result<U, ? super E> andThen(Function<? super V, ? extends Result<U, ? super E>> mapper) {
        return mapper.apply(value);
    }

    @SuppressWarnings("unchecked")
    @Override
    public <R> Result<V, R> mapError(Function<? super E, R> mapper) {
        return (Result<V, R>) this;
    }

    @SuppressWarnings("unchecked")
    @Override
    public <R> Result<? super V, R> orElse(Function<? super E, ? extends Result<? super V, R>> mapper) {
        return (Result<V, R>) this;
    }

    @Override
    public Result<V, E> attempt(Consumer<? super V> action) {
        action.accept(value);
        return this;
    }

    @Override
    public Result<V, E> attemptError(Consumer<? super E> action) {
        return this;
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
