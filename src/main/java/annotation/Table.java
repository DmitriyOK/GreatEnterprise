package annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Аннотация определяет сопоставление класса с таблицей
 * отмеченной аннотацией {@link Entity}
 *
 */

@Target(value= ElementType.TYPE)
@Retention(value= RetentionPolicy.RUNTIME)
public @interface Table {
    /**
     * Имя аннотации должно соответствовать названию таблицы.
     * @return имя таблицы
     */
    String name();
}
