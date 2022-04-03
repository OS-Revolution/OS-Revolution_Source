package ethos.util;

import java.util.Objects;

public class PreconditionUtils {

    public static boolean isNull(Object o) {
        return Objects.isNull(o);
    }

    public static boolean notNull(Object o) {
        return Objects.nonNull(o);
    }

    public static boolean isTrue(boolean condition) {
        return condition;
    }

    public static boolean isFalse(boolean condition) {
        return !condition;
    }
}
