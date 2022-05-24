package annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface Query{
    
    String value() default "";

    String countQuery() default "";

    String countProjection() default "";

    boolean nativeQuery() default false;

    String name() default "";

    String countName() default "";
}
