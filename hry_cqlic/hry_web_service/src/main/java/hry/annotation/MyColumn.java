package hry.annotation;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

/**
 * 拓展下Column
 * @author:      denghf
 * @version:     V1.0 
 * @Date:        2018-02-13 21:56:23
 */
@Target({METHOD, FIELD}) 
@Retention(RUNTIME)
public @interface MyColumn {

    String name() default "";

    boolean unique() default false;

    boolean nullable() default true;

    boolean insertable() default true;

    boolean updatable() default true;

    String columnDefinition() default "";

    String table() default "";

    int length() default 255;

    int precision() default 0;

    int scale() default 0;

    String comment() default "";
    
    String type() default "varchar";
}
