package monad;

import java.util.*;
import java.util.function.*;

public record One<T>(T value) implements Option<T> {
    @Override
    public Option<T> or(Option<T> other) {
        return this;
    }

    public One(T value) {
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
    public <E> Result<T, E> okOr(E error) {
        return Result.ok(value);
    }

    @Override
    public <E> Result<T, E> okOrElse(Supplier<E> error) {
        return Result.ok(value);
    }

    @Override
    public <E extends Throwable> T unwrapOrThrow(E exception) {
        return value;
    }

    @Override
    public <E extends Throwable> T unwrapOrElseThrow(Supplier<E> exception) {
        return value;
    }

    @Override
    public <U> Option<U> map(Function<? super T, U> mapper) {
        return new One<>(mapper.apply(value));
    }

    @SuppressWarnings("unchecked")
    @Override
    public <U> Option<U> andThen(Function<? super T, ? extends Option<? extends U>> mapper) {
        return (Option<U>) mapper.apply(value);
    }

    @Override
    public Option<T> filter(Predicate<? super T> predicate) {
        return predicate.test(value) ? this : None.none();
    }

    @Override
    public Option<T> orElse(Supplier<? extends Option<T>> other) {
        return this;
    }

    @Override
    public Option<T> attempt(Consumer<? super T> action) {
        action.accept(value);
        return this;
    }

    @Override
    public Option<T> attemptOrElse(Consumer<? super T> action, Runnable other) {
        action.accept(value);
        return this;
    }

    @Override
    public boolean isOne() {
        return true;
    }

    @Override
    public boolean isNone() {
        return false;
    }

    @Override
    public boolean isOneAnd(Predicate<? super T> predicate) {
        return predicate.test(value);
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
