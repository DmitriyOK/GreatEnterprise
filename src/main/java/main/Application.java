package main;

import Validator.Validator;
import datasource.DBConnection;
import factory.impl.ReportFactory;
import model.Employer;
import model.EmployerWorkDay;
import model.ReportCurrentDay;
import period.Period;
import services.EmployerService;
import services.EmployerWorkDayService;
import services.impl.EmployerServiceImpl;
import services.impl.EmployerWorkDayServiceImpl;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import java.util.List;


import javax.swing.*;

public class Application extends JFrame implements ActionListener {

    private JPanel panel;
    private JPasswordField passwordField;
    private static JTextArea textArea;
    private JComboBox reportList;
    private JButton loginButton, logoutButton, startWorkDayButton, endWorkDayButton, findReportButton, loadConfigButton;
    private JTextField loginTextField, startPeriodTextField, endPeriodTextField, employerLoginTextField;

    private Employer currentEmployer;
    private EmployerWorkDay employerWorkDay;
    private EmployerService employerService = new EmployerServiceImpl();
    private EmployerWorkDayService employerWorkDayService = new EmployerWorkDayServiceImpl();

    private String title; 
    private String formatReport;
    private ReportFactory reportFactory;

    private int startWorkDayButtonClickCount=0; //Костыль для ограничения обновления поля startTime и unixStartTime

    public Application() throws IOException {

        createClassFields();
        setClassFields();
        addFieldsActionListener();
        addTextFieldsKeyListener();
        addTextFieldsFocusListener();
        showFieldOnlogoutAction();
        printText("Для работы с системой загрузите настройки базы данных авторизуйтесь.");
    }

    // До Main- класса идут методы инициализации представления.

    private void createClassFields() {
        panel = new JPanel();
        textArea = new JTextArea();
        title = "Учет рабочего времени.";

        loginButton = new JButton("Войти");
        logoutButton = new JButton("Выйти");
        startWorkDayButton = new JButton("Начать работу");
        endWorkDayButton = new JButton("Закончить работу");
        findReportButton = new JButton("Отчет");
        loadConfigButton = new JButton("Загрузить файл");

        loginTextField = new JTextField("Ваш логин");
        passwordField = new JPasswordField("Ваш пароль");
        employerLoginTextField = new JTextField("Логин сотрудника");
        startPeriodTextField = new JTextField("гггг-мм-дд (включительно)");
        endPeriodTextField = new JTextField("гггг-мм-дд (включительно)");
        formatReport = "Отчет для: %S за период с %S по %S\n";
        reportFactory = new ReportFactory();

        reportList = new JComboBox(new String[]{"На заданный день", "За период по сотруднику", "За текущий день"});
    }

    private void setClassFields() {
        this.setSize(1000, 550);
        this.setVisible(true);
        this.add(panel);
        this.setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setTitle(title);

        textArea.setBounds(20, 20, 950, 360);
        textArea.setEditable(false);
        loginTextField.setBounds(20, 400, 150, 30);
        passwordField.setBounds(180, 400, 150, 30);
        startPeriodTextField.setBounds(220, 400, 155, 30);
        endPeriodTextField.setBounds(220, 445, 155, 30);
        endPeriodTextField.setEditable(false);
        employerLoginTextField.setBounds(20, 445, 150, 30);
        employerLoginTextField.setEditable(false);
        loginButton.setBounds(870, 400, 95, 30);
        logoutButton.setBounds(870, 400, 95, 30);
        findReportButton.setBounds(450, 400, 95, 30);
        startWorkDayButton.setBounds(600, 400, 140, 30);
        endWorkDayButton.setBounds(600, 445, 140, 30);
        loadConfigButton.setBounds(700, 400, 135, 30);

        reportList.setBounds(20, 400, 170, 30);
        reportList.setSelectedIndex(2);
        updateReportList();

    }

    private void addFieldsActionListener() {
        loginButton.addActionListener(this);
        logoutButton.addActionListener(this);
        startWorkDayButton.addActionListener(this);
        endWorkDayButton.addActionListener(this);
        reportList.addActionListener(this);
        findReportButton.addActionListener(this);
        loadConfigButton.addActionListener(this);
        panel.setLayout(null);

    }

    private void addTextFieldsKeyListener() {
        loginTextField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                if (e.getKeyChar() == '\n' && enterChecker(loginTextField.getText())) {
                    authorization();
                }
            }
        });

        passwordField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {

                if (e.getKeyChar() == '\n' && enterChecker(passwordField.getText())) {
                    authorization();
                }
            }
        });

        employerLoginTextField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                if (e.getKeyChar() == '\n' && enterChecker(employerLoginTextField.getText())) {
                    findReport();
                }
            }
        });

        startPeriodTextField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                if (e.getKeyChar() == '\n' && enterChecker(startPeriodTextField.getText())) {
                    findReport();
                }
            }
        });

        endPeriodTextField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                if (e.getKeyChar() == '\n' && enterChecker(endPeriodTextField.getText())) {
                    findReport();
                }
            }
        });
    }

    private void addTextFieldsFocusListener(){
        loginTextField.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                loginTextField.selectAll();
            }
        });
        passwordField.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                passwordField.selectAll();
            }
        });

        employerLoginTextField.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                employerLoginTextField.selectAll();
            }
        });
        startPeriodTextField.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                startPeriodTextField.selectAll();
            }
        });

        endPeriodTextField.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                endPeriodTextField.selectAll();
            }
        });

    }

    private static File loadConfigDbFile() {

        File file=null;

        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Выберите файл");
        int returnValue = fileChooser.showOpenDialog(null);
        if (returnValue == JFileChooser.APPROVE_OPTION) {
            file = fileChooser.getSelectedFile();
        }

        return file;
    }

    public static void main(String[] args) {

        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                try {
                    new Application();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    //Cntroller
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == loginButton) {
            if (Validator.isValidLogin(loginTextField.getText())) {
                authorization();
            } else {
                printText("Логин должен состоять из латинских букв, от 1 до 32 символа.");
            }
        }
        if (e.getSource() == logoutButton) {

            if(employerWorkDay.isOnline()){
                confirmFinishDay();
            }
            employerWorkDay.setOnline(false);
            employerWorkDay = employerWorkDayService.updateStatus(employerWorkDay);

            clearPanelBeforeLogout();
            showFieldOnlogoutAction();
        }

        if (e.getSource() == startWorkDayButton) {
            panel.add(endWorkDayButton);
            panel.updateUI();
            startWorkDay();
        }

        if (e.getSource() == endWorkDayButton) {
            finishWorkDay();
        }

        if (e.getSource() == reportList) {
            updateReportList();
        }

        if (e.getSource() == findReportButton) {
            findReport();
        }

        if(e.getSource() == loadConfigButton){
               if(DBConnection.init(loadConfigDbFile())){
                   printText(textArea.getText()+"\n"+"Соединие установлено. Авторизуйтесь");
               }else {
                   printText(textArea.getText() +"\n"+ "Проверьте файл настроек");
               }
        }
    }

    // Методы изменения состояния представления

    private void updateReportList() {
        switch (reportList.getSelectedIndex()) {
            case 0: // reportList: 0 "На заданный день"  1: "За период по сотруднику" 2: "За текущий день"
                employerLoginTextField.setEditable(false);
                endPeriodTextField.setEditable(false);
                employerLoginTextField.setText("");
                endPeriodTextField.setText("");
                startPeriodTextField.setEditable(true);
                startPeriodTextField.setText("От \"гггг-мм-дд\"");

                break;

            case 1:
                startPeriodTextField.setEditable(true);
                endPeriodTextField.setEditable(true);
                employerLoginTextField.setEditable(true);
                employerLoginTextField.setText("Логин сотрудника");
                startPeriodTextField.setText("От \"гггг-мм-дд\"");
                endPeriodTextField.setText("До \"гггг-мм-дд\"");
                break;

            case 2:
                employerLoginTextField.setEditable(false);
                startPeriodTextField.setEditable(false);
                endPeriodTextField.setEditable(false);
                employerLoginTextField.setText("");
                startPeriodTextField.setText("");
                endPeriodTextField.setText("");
                break;
        }

    }

    private void findReport() {
        switch (reportList.getSelectedIndex()) {
            case 0: // reportList: 0 "На заданный день"  1: "За период по сотруднику" 2: "За текущий день"
                findReportForSelectedDay(startPeriodTextField.getText());
                break;
            case 1: //
                findEmployerReportByPeriod(employerLoginTextField.getText(), startPeriodTextField.getText(), endPeriodTextField.getText());
                break;
            case 2:
                findCurrentDayReport();
                break;

        }
    }

    private void findCurrentDayReport() {
        List<ReportCurrentDay> results = employerWorkDayService.findCurrentDayReport();
        String reportTitle = "ОТЧЕТ ЗА ТЕКУЩИЙ ДЕНЬ\n";
        printText(reportFactory.buildReport(reportTitle, results));
    }

    private void findReportForSelectedDay(String day) {
        List<ReportCurrentDay> results = employerWorkDayService.findReportForSelectedDay(day);
        String reportTitle = (String.format(formatReport, "УКАЗАННЫЙ ПЕРИОД", day, day));
        printText(reportFactory.buildReport(reportTitle, results));
    }

    private void findEmployerReportByPeriod(String login, String startPeriod, String endPeriod) {
        Employer employerForReport = employerService.findByLogin(login);
        List<EmployerWorkDay> results = employerWorkDayService.findEmployerWorkDayByPeriod(employerForReport, startPeriod, endPeriod);
        String reportTitle = (String.format(formatReport, employerForReport.getLogin(), startPeriod, endPeriod));
        printText(reportFactory.buildReport(reportTitle, results));
    }

    private void confirmFinishDay() {
        // 0=yes, 1=no, 2=cancel
        if(JOptionPane.showConfirmDialog(null, "Закончить рабочий день?") == 0) {
            finishWorkDay();
        }
    }

    private void finishWorkDay() {
        Period.createFinishTime(employerWorkDay);
        employerWorkDay = employerWorkDayService.save(employerWorkDay);
        printText("Рабочий день закончен: "+employerWorkDay.getFinishTime());
        this.setTitle(title);
    }

    private void startWorkDay() {

        if(employerWorkDay.getFinishTime()== null) {
            if(startWorkDayButtonClickCount < 1 ) {
                Period.createStartTime(employerWorkDay);
                startWorkDayButtonClickCount++;
                employerWorkDay = employerWorkDayService.save(employerWorkDay);
            }

            printText("Рабочий день начат: " + employerWorkDay.getStartTime());
        } else{
            printText(String.format("Вы отработали сегодняшний день с %s по %s",
                    employerWorkDay.getStartTime(), employerWorkDay.getFinishTime()));
        }

    }

    private void authorization() {
        if(!DBConnection.isInit()){
            printText("Загрузите настройки базы данных и авторизуйтесь.");
            return;
        }
        currentEmployer = Validator.authorization(loginTextField.getText(), passwordField.getText());
        if (currentEmployer.getId() == -1) {
            printText("Не правильный логин или пароль.");
        } else {
            this.setTitle(title + " Пользователь: "+ currentEmployer.getLogin());
            employerWorkDay = employerWorkDayService.findCurrentEmployerWorkDay(currentEmployer);
            employerWorkDay.setOnline(true);
            employerWorkDayService.updateStatus(employerWorkDay);
            showFieldOnloginAction();
               }
    }

    private void showFieldOnloginAction (){

            textArea.setText("Вы авторизованы");
            panel.add(startPeriodTextField);
            panel.add(endPeriodTextField);
            panel.add(employerLoginTextField);
            panel.add(startWorkDayButton);
            panel.add(findReportButton);
            panel.add(logoutButton);
            panel.add(reportList);
            panel.remove(loginTextField);
            panel.remove(passwordField);
            panel.remove(loginButton);
            panel.remove(loadConfigButton);

            panel.updateUI();
        }

    private void showFieldOnlogoutAction (){
            panel.add(loginButton);
            panel.add(textArea);
            panel.add(loginTextField);
            panel.add(passwordField);
            panel.add(loginButton);
            panel.add(loadConfigButton);
            panel.updateUI();
            printText("Вы вышли.");
            this.setTitle(title);
    }

    private void clearPanelBeforeLogout(){
            panel.remove(logoutButton);
            panel.remove(reportList);
            panel.remove(startPeriodTextField);
            panel.remove(endPeriodTextField);
            panel.remove(employerLoginTextField);
            panel.remove(startWorkDayButton);
            panel.remove(endWorkDayButton);
            panel.remove(findReportButton);
    }

    public static void printText(String text){
        textArea.setText(text);
    }

    private static boolean enterChecker(String t) {
        for (int i = 0; i < t.length(); i++)
            if (t.charAt(i) != '\n' && t.charAt(i) != ' ')
                return true;
        return false;
    }
}