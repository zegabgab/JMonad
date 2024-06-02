package monad;

import java.util.function.*;

public interface Loop<T> {
    static <T> Loop<T> of(UnaryOperator<T> operator) {
        return new While<>(operator, value -> true);
    }

    T on(T value);
    Loop<T> loopWhile(Predicate<? super T> condition);
    Loop<T> loopFor(int iterations);
}

final class While<T> implements Loop<T> {
    private final UnaryOperator<T> operator;
    private final Predicate<? super T> condition;

    While(UnaryOperator<T> operator, Predicate<? super T> condition) {
        this.operator = operator;
        this.condition = condition;
    }

    @Override
    public T on(T value) {
        return condition.test(value) ?
                on(operator.apply(value)) :
                value;
    }

    @Override
    public Loop<T> loopWhile(Predicate<? super T> condition) {
        return new While<>(operator, condition);
    }

    @Override
    public Loop<T> loopFor(int iterations) {
        return new For<>(operator, iterations);
    }
}

final class For<T> implements Loop<T> {
    private final UnaryOperator<T> operator;
    private final int iterations;

    For(UnaryOperator<T> operator, int iterations) {
        this.operator = operator;
        this.iterations = iterations;
    }

    private T iterate(T value, int remaining) {
        return remaining > 0 ?
                iterate(operator.apply(value), remaining - 1) :
                value;
    }

    @Override
    public T on(T value) {
        return iterate(value, iterations);
    }

    @Override
    public Loop<T> loopWhile(Predicate<? super T> condition) {
        return new While<>(operator, condition);
    }

    @Override
    public Loop<T> loopFor(int iterations) {
        return new For<>(operator, iterations);
    }
}