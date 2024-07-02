package monad;

import java.util.function.*;

public record While<T>(UnaryOperator<T> operator, Predicate<? super T> condition) implements Loop<T> {
    @Override
    public T on(T value) {
        return condition.test(value) ?
                on(operator.apply(value)) :
                value;
    }
}
