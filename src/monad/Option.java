package monad;

import java.util.function.*;

public sealed interface Option<T> permits One, None {
    static <T> Option<T> of(T value) {
        return value != null ? new One<>(value) : None.none();
    }

    static <T> Option<T> none() {
        return None.none();
    }

    T unwrap();

    T unwrapOr(T other);

    T unwrapOrElse(Supplier<? extends T> other);

    <U> Option<U> map(Function<? super T, U> mapper);

    <U> Option<U> andThen(Function<? super T, ? extends Option<U>> mapper);

    Option<T> filter(Predicate<? super T> predicate);

    Option<T> orElse(Supplier<? extends Option<T>> supplier);

    boolean isOne();

    boolean isNone();

    boolean isOneAnd(Predicate<? super T> predicate);
}
