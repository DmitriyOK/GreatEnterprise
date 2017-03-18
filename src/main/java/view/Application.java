package view;

import Validator.Validator;
import datasource.DBConnection;
import model.Employer;
import model.EmployerWorkDay;
import period.Period;
import services.EmployerService;
import services.EmployerWorkDayService;
import services.impl.EmployerServiceImpl;
import services.impl.EmployerWorkDayServiceImpl;
import java.awt.event.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;


import javax.swing.*;

public class Application extends JFrame implements ActionListener {

    private JPanel panel;
    private JPasswordField passwordField;
    private JTextArea textArea;
    private JComboBox reportList;
    private JButton loginButton, logoutButton, startWorkDayButton, endWorkDayButton, findReportButton;
    private JTextField loginTextField, startPeriodTextField, endPeriodTextField, employerLoginTextField;

    private Employer employer;
    private EmployerWorkDay employerWorkDay;
    private EmployerService employerService = new EmployerServiceImpl();
    private EmployerWorkDayService employerWorkDayService = new EmployerWorkDayServiceImpl();

    private String title; 
    private String formatReport;

    public Application() throws IOException {

        createClassFields();
        setClassFields();
        showFieldOnlogoutAction();
        addFieldsActionListener();
        addTextFieldsKeyListener();
        addTextFieldsMouseClickListener();
        printText("Для работы с системой авторизуйтесь.");
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == loginButton) {
            if (Validator.isValidLogin(loginTextField.getText())) {
                authorization();
            } else {
                printText("Логин должен состоять из латинских букв, от 1 до 32 символа.");
            }
        }
        if (e.getSource() == logoutButton) {
            logout();
        }

        if (e.getSource() == startWorkDayButton) {
            startWorkDay();
        }

        if (e.getSource() == endWorkDayButton) {
            finishWorkDay();
        }

        if (e.getSource() == reportList) {
            switch (reportList.getSelectedIndex()) {
                case 0: // reportList: 0 "На заданный день"  1: "За период по сотруднику"
                    //TODO не реализован SQL
                    employerLoginTextField.setEditable(true);
                    break;
                case 1: //
                    employerLoginTextField.setEditable(true);
                    break;
            }
        }

        if (e.getSource() == findReportButton) {
            System.out.println("Reports");
            switch (reportList.getSelectedIndex()) {
                case 0: // reportList: 0 "На заданный день"  1: "За период по сотруднику"
                    //TODO не реализован SQL
                    findReportByDay();
                    break;
                case 1: //
                    findEmployerReportByPeriod();
                    break;

            }
        }
    }

    private void findReportByDay() {
    }

    private void findEmployerReportByPeriod() {
        Employer employerForReport = employerService.findByLogin(employerLoginTextField.getText());
        String aStartPeriod = startPeriodTextField.getText();
        String aEndPeriod = endPeriodTextField.getText();
        if(!Validator.isValidFormat(aStartPeriod) && Validator.isValidFormat(aEndPeriod)){
            printText("Некорректный формат даты. Дата должна соответсвовать формату гггг-мм-дд");
            return;
        }
        int startPeriod = Period.createPeriod(aStartPeriod);
        int endPeriod = Period.createPeriod(aEndPeriod);
        List<EmployerWorkDay> results = employerWorkDayService.findEmployerWorkDayByPeriod(employerForReport, startPeriod, endPeriod);
        String reportTitle = (String.format(formatReport, employerForReport.getLogin(), aStartPeriod, aEndPeriod));

        StringBuilder report = new StringBuilder();
        report.append(reportTitle);
        report.append("\n");
        for (EmployerWorkDay workDay : results) {
            report.append(workDay.toString());
            report.append("\n");
        }

        printText(report.toString());

    }


    public static void main(String[] args) {

        try {
            DBConnection.init();
            while (DBConnection.getConnection().isClosed()){}
            new Application();
        } catch (Exception e) {
            e.printStackTrace();
        }
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
        startPeriodTextField.setBounds(220, 400, 150, 30);
        endPeriodTextField.setBounds(220, 445, 150, 30);
        employerLoginTextField.setBounds(20, 445, 150, 30);
        employerLoginTextField.setEditable(false);
        loginButton.setBounds(870, 400, 95, 30);
        logoutButton.setBounds(870, 400, 95, 30);
        findReportButton.setBounds(450, 400, 95, 30);
        startWorkDayButton.setBounds(600, 400, 140, 30);
        endWorkDayButton.setBounds(600, 445, 140, 30);

        reportList.setBounds(20, 400, 170, 30);
    }

    private void createClassFields() {
        panel = new JPanel();
        textArea = new JTextArea();
        title = "Учет рабочего времени.";

        loginButton = new JButton("Войти");
        logoutButton = new JButton("Выйти");
        startWorkDayButton = new JButton("Начать работу");
        endWorkDayButton = new JButton("Закончить работу");
        findReportButton = new JButton("Отчет");

        loginTextField = new JTextField("Ваш логин");
        passwordField = new JPasswordField("Ваш пароль");
        employerLoginTextField = new JTextField("Логин сотрудника");
        startPeriodTextField = new JTextField("От (включительно)");
        endPeriodTextField = new JTextField("До (включительно)");
        formatReport = "Отчет для: %S за период с %S по %S";
        

        reportList = new JComboBox(new String[]{"На заданный день", "За период по сотруднику"});

    }

    private void logout() {
        // 0=yes, 1=no, 2=cancel
        int choise = JOptionPane.showConfirmDialog(null, "Закончить рабочий день?");

        if(choise == 2) {
            return;
        }
        if(choise == 0) {
            finishWorkDay();
        }
        clearPanelBeforeLogout();
        showFieldOnlogoutAction();
    }

    private void finishWorkDay() {
        Period.createFinishTime(employerWorkDay);
        employerWorkDay = employerWorkDayService.save(employerWorkDay);
        printText("Рабочий день закончен: "+employerWorkDay.getFinishTime());
        this.setTitle(title);
    }

    private void startWorkDay() {
        employerWorkDay = employerWorkDayService.findCurrentEmployerWorkDay(employer);
        Period.createStartTime(employerWorkDay);
        employerWorkDay = employerWorkDayService.save(employerWorkDay);
        printText("Рабочий день начат: "+employerWorkDay.getStartTime());
    }

    private void addFieldsActionListener() {
        loginButton.addActionListener(this);
        logoutButton.addActionListener(this);
        startWorkDayButton.addActionListener(this);
        endWorkDayButton.addActionListener(this);
        reportList.addActionListener(this);
        findReportButton.addActionListener(this);
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
                    authorization();
                }
            }
        });

        startPeriodTextField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                if (e.getKeyChar() == '\n' && enterChecker(startPeriodTextField.getText())) {
                    authorization();
                }
            }
        });

        endPeriodTextField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                if (e.getKeyChar() == '\n' && enterChecker(endPeriodTextField.getText())) {
                    authorization();
                }
            }
        });
    }

    private void addTextFieldsMouseClickListener(){
        loginTextField.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                loginTextField.setText("");
            }
        });
        passwordField.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                passwordField.setText("");
            }
        });
        employerLoginTextField.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                employerLoginTextField.setText("");
            }
        });
        startPeriodTextField.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                startPeriodTextField.setText("");
            }
        });

        endPeriodTextField.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                endPeriodTextField.setText("");
            }
        });

    }

    private void authorization() {
        try {
               employer = Validator.authorization(loginTextField.getText(), passwordField.getText());
               if (employer.getId() == -1) {
                   printText("Не правильный пароль.");
               }else {
                   this.setTitle(title + " Пользователь: "+employer.getLogin());
                   showFieldOnloginAction();
               }

           } catch (IndexOutOfBoundsException e) {
               printText("Пользователь с указанным логином не найден. login: " + loginTextField.getText());
           }
    }

    private void showFieldOnloginAction (){
            textArea.setText("Вы авторизованы");
            panel.add(startPeriodTextField);
            panel.add(endPeriodTextField);
            panel.add(employerLoginTextField);
            panel.add(startWorkDayButton);
            panel.add(endWorkDayButton);
            panel.add(findReportButton);
            panel.remove(loginTextField);
            panel.remove(passwordField);
            panel.remove(loginButton);
            panel.add(logoutButton);
            panel.add(reportList);
            panel.updateUI();
        }

    private void showFieldOnlogoutAction (){
            panel.add(textArea);
            panel.add(loginTextField);
            panel.add(passwordField);
            panel.add(loginButton);
            panel.updateUI();
            textArea.setText("Вы вышли, не закончив рабочий день.");
            this.setTitle(title);
    }

    private void clearPanelBeforeLogout(){
            panel.remove(logoutButton);
            panel.remove(reportList);
            panel.remove(startPeriodTextField);
            panel.remove(endPeriodTextField);
            panel.remove(employerLoginTextField);
    }

    private void printText(String text){
        textArea.setText(text);
    }

    private static boolean enterChecker(String t) {
        for (int i = 0; i < t.length(); i++)
            if (t.charAt(i) != '\n' && t.charAt(i) != ' ')
                return true;
        return false;
    }
}