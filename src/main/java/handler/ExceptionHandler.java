package handler;

import main.Application;

/**
 * Если во время исполнения программы случается исключение
 * его возможно вывести пользователю вызвав метод {@link #showToUser(Exception, boolean)}
 */
public class ExceptionHandler {

    /**
     *  Выводит сооющение исключения.
     *  Заданный stackTrace пользователю будете выведен если
     *  параметр printStackTrace - true
     *
     * @param exception массив объектов {@link StackTraceElement}.
     * @param printStackTrace true если нужно вывести stack trace исключения.
     */
    public static void showToUser(Exception exception, boolean printStackTrace){

        StringBuilder exceptionReport = new StringBuilder();
        exceptionReport.append(exception.getMessage()+"\n");
        if(printStackTrace){
            printStackTrace(exception, exceptionReport);
        }
        Application.printText(exceptionReport.toString());

    }

    private static void printStackTrace(Exception exception, StringBuilder exceptionReport){
        for (StackTraceElement element : exception.getStackTrace()){
            exceptionReport.append(element+"\n");
        }
    }
}
