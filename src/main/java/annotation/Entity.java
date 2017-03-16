package annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by Dmitriy on 16.03.2017.
 */


@Target(value= ElementType.TYPE)
@Retention(value= RetentionPolicy.RUNTIME)
public @interface Entity {
}
