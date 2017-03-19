package annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**

 * Аннотация используется для сеттеров классов - сущностей.
 * Такие классы должны быть отмечены аннотацией {@link Entity}
 *
 * Аннотация инициализирует сеттер объекта
 * соответствующим значением поля из таблицы базы данных
 *
 */

@Target(value= ElementType.METHOD)
@Retention(value= RetentionPolicy.RUNTIME)
public @interface Column {

    /**
     * Имя аннотации должно соответсвовать имени столбца в таблице
     *
     * @return имя столбца в таблице.
     */
    String name();
}
