package monad;

import java.util.*;
import java.util.function.*;

public record None<T>() implements Option<T> {
    private final static None<?> NONE = new None<>();

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
    public <E> Result<T, E> okOr(E error) {
        return Result.error(error);
    }

    @Override
    public <E> Result<T, E> okOrElse(Supplier<E> error) {
        return Result.error(error.get());
    }

    @Override
    public <E extends Throwable> T unwrapOrThrow(E exception) throws E {
        throw exception;
    }

    @Override
    public <E extends Throwable> T unwrapOrElseThrow(Supplier<E> exception) throws E {
        throw exception.get();
    }

    @Override
    public <U> Option<U> map(Function<? super T, U> mapper) {
        return none();
    }

    @Override
    public <U> Option<U> andThen(Function<? super T, ? extends Option<? extends U>> mapper) {
        return none();
    }

    @Override
    public Option<T> or(Option<T> other) {
        return other;
    }

    @Override
    public Option<T> filter(Predicate<? super T> predicate) {
        return none();
    }

    @Override
    public Option<T> orElse(Supplier<? extends Option<T>> other) {
        return other.get();
    }

    @Override
    public Option<T> attempt(Consumer<? super T> action) {
        return this;
    }

    @Override
    public Option<T> attemptOrElse(Consumer<? super T> action, Runnable other) {
        other.run();
        return this;
    }

    @Override
    public boolean isOne() {
        return false;
    }

    @Override
    public boolean isNone() {
        return true;
    }

    @Override
    public boolean isOneAnd(Predicate<? super T> predicate) {
        return false;
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
