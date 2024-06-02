package monad;

import java.util.function.*;

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