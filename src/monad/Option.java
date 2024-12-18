package monad;

import java.util.function.*;

public sealed interface Option<V> permits One, None {
    static <T> Option<T> of(T value) {
        return value != null ? new One<>(value) : none();
    }

    static <T> Option<T> none() {
        return None.none();
    }

    static <T> Option<T> conditional(boolean condition, Supplier<? extends T> supplier) {
        return condition ? of(supplier.get()) : none();
    }

    V unwrap();

    V unwrapOr(V other);

    V unwrapOrElse(Supplier<? extends V> other);

    <E> Result<V, E> okOr(E error);

    <E> Result<V, E> okOrElse(Supplier<E> error);

    <T extends Throwable> V unwrapOrThrow(T exception) throws T;

    <T extends Throwable> V unwrapOrElseThrow(Supplier<T> exception) throws T;

    <U> Option<U> map(Function<? super V, U> mapper);

    <U> Option<U> andThen(Function<? super V, ? extends Option<? extends U>> mapper);

    Option<V> or(Option<V> other);

    Option<V> orElse(Supplier<? extends Option<V>> other);

    Option<V> filter(Predicate<? super V> predicate);

    Option<V> attempt(Consumer<? super V> action);

    Option<V> attemptOrElse(Consumer<? super V> action, Runnable other);

    boolean isOne();

    boolean isNone();

    boolean isOneAnd(Predicate<? super V> predicate);
}
