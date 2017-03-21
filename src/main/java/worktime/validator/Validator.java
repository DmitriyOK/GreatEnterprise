package worktime.validator;

import worktime.handler.ExceptionHandler;
import worktime.model.Employer;
import worktime.services.EmployerService;
import worktime.services.impl.EmployerServiceImpl;
import worktime.main.Application;

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
     * @return {@link Employer}
     */
    public static Employer authorization(String login, String password) {

        Employer mockEmployer = new Employer();
        mockEmployer.setLogin(login);
        mockEmployer.setPassword(password);
        mockEmployer.setId(-1);

        try {
            Employer employer = employerService.findByLogin(login);
            if (chekPassword(employer, login, password)){
                return employer;
            }
        } catch (IndexOutOfBoundsException e) {
            Application.printText("Пользователь с указанным логином не найден. login: " + login);
        }
        catch (Exception e){
            ExceptionHandler.showToUser(e, true);
        }
        return mockEmployer;
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
        } catch (Exception e) {
            ExceptionHandler.showToUser(e, true);
           return date != null;
        }
        return date != null;
    }

    /**
     * Если логин состоит из латинских букв, длинной
     * от 1 до 32 символов возвращается true.
     *
     * @param login Логин, который нужно проверить.
     * @return <p>true</p> если логин отвечает требованию.
     */
    public static boolean isValidLogin(String login) {

        if (login == null){
            return false;
        }

        Pattern pattern = Pattern.compile("[a-zA-Z]{1,32}");
        Matcher mathcer = pattern.matcher(login);
        return mathcer.matches();
    }


    /**
     * Проверяет является ли переданный файл *.properties - файлом
     *
     * @param fileName загружаемый файл.
     *
     * @return результат проверки
     */
    public static boolean isPropertiesFile(String fileName){

        String pattern = ".properties";
        return fileName.substring(fileName.lastIndexOf(".")).equals(pattern);
    }

    private static boolean chekPassword(Employer employer, String login, String password) {
        return (employer.getPassword().equals(password) && employer.getLogin().equals(login));
    }

}
