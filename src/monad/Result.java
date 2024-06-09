package monad;

import java.util.function.*;

public sealed interface Result<V, E> permits Ok, Error {
    static <V, E> Result<V, E> ok(V value) {
        return new Ok<>(value);
    }

    static <V, E> Result<V, E> error(E error) {
        return new Error<>(error);
    }

    V unwrap();

    V unwrapOr(V other);

    V unwrapOrElse(Supplier<? extends V> other);

    E unwrapError();

    E unwrapErrorOr(E other);

    E unwrapErrorOrElse(Supplier<? extends E> other);

    <U> Result<U, E> map(Function<? super V, U> mapper);

    <U> Result<U, E> andThen(Function<? super V, ? extends Result<U, E>> mapper);

    <R> Result<V, R> mapError(Function<? super E, R> mapper);

    <R> Result<V, R> orElse(Function<? super E, ? extends Result<V, R>> mapper);
}
