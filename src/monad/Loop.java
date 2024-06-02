package monad;

import java.util.function.*;

public interface Loop<T> {
    static <T> Loop<T> of(UnaryOperator<T> operator) {
        return new While<>(operator, value -> true);
    }

    T executeOn(T value);
    Loop<T> doWhile(Predicate<? super T> condition);
    Loop<T> doFor(int iterations);
}

final class While<T> implements Loop<T> {
    private final UnaryOperator<T> operator;
    private final Predicate<? super T> condition;

    While(UnaryOperator<T> operator, Predicate<? super T> condition) {
        this.operator = operator;
        this.condition = condition;
    }

    @Override
    public T executeOn(T value) {
        while (condition.test(value)) {
            value = operator.apply(value);
        }
        return value;
    }

    @Override
    public Loop<T> doWhile(Predicate<? super T> condition) {
        return new While<>(operator, condition);
    }

    @Override
    public Loop<T> doFor(int iterations) {
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

    @Override
    public T executeOn(T value) {
        for (int i = 0; i < iterations; i++) {
            value = operator.apply(value);
        }
        return value;
    }

    @Override
    public Loop<T> doWhile(Predicate<? super T> condition) {
        return new While<>(operator, condition);
    }

    @Override
    public Loop<T> doFor(int iterations) {
        return new For<>(operator, iterations);
    }
}