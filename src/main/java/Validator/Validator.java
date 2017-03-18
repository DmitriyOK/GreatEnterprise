package Validator;

import model.Employer;
import services.EmployerService;
import services.impl.EmployerServiceImpl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

public abstract class Validator {

    private static EmployerService employerService = new EmployerServiceImpl();

    /**
     * Проверяет введенные пользователем логин и пароль с существующими
     * Если логин или пароль указан не верно, то вернется {@link Employer}
     * с null полями, кроме логина и пароля, которые указал пользователь,
     * переменная <p>Id</p> будет равна -1
     *В противном случае объект будет загружен из базы данных.
     *
     * @param login логин введенный пользователем
     * @param password пароль введенный пользователем
     * @return {@Employer}
     */
    public static Employer authorization(String login, String password) {
        Employer employer = employerService.findByLogin(login);
        if (chekPassword(employer, login, password)) ;
        else {
            employer = new Employer();
            employer.setLogin(login);
            employer.setPassword(password);
            employer.setId(-1);
        }
        return employer;
    }

    /**
     *  Проверяет соответсвует ли дата периода формату "yyyy-MM-dd".
     *
     * @param value введенная дата пользователм {@link String}
     * @return {@link boolean} результат соответствия.
     */
    public static boolean isValidFormat(String value) {
        Date date = null;
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            date = sdf.parse(value);
            if (!value.equals(sdf.format(date))) {
                date = null;
            }
        } catch (ParseException ex) {
            ex.printStackTrace();
        }
        return date != null;
    }

    /**
     * Если логин состоит из латинских букв, длинной
     * от 1 до 32 символов возвращается true.
     *
     * @param value Логин, который нужно проверить.
     * @return <p>true</p> если логин отвечает требованию.
     */

    public static boolean isValidLogin(String value) {
        Pattern pattern = Pattern.compile("[a-zA-Z]{1,32}");
        Matcher mathcer = pattern.matcher(value);
        return mathcer.matches();
    }

    private static boolean chekPassword(Employer employer, String login, String password) {
        return (employer.getPassword().equals(password) && employer.getLogin().equals(login));
    }

}
