package monad;

import java.util.function.*;

public sealed interface Option<V> permits One, None {
    static <T> Option<T> of(T value) {
        return value != null ? new One<>(value) : None.none();
    }

    static <T> Option<T> none() {
        return None.none();
    }

    V unwrap();

    V unwrapOr(V other);

    V unwrapOrElse(Supplier<? extends V> other);

    <T extends Throwable> V unwrapOrThrow(T exception) throws T;

    <T extends Throwable> V unwrapOrElseThrow(Supplier<T> exception) throws T;

    <U> Option<U> map(Function<? super V, U> mapper);

    <U> Option<U> andThen(Function<? super V, ? extends Option<U>> mapper);

    Option<V> filter(Predicate<? super V> predicate);

    Option<V> orElse(Supplier<? extends Option<V>> supplier);

    void attempt(Consumer<? super V> action);

    boolean isOne();

    boolean isNone();

    boolean isOneAnd(Predicate<? super V> predicate);
}
