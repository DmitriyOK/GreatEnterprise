import annotation.Table;
import datasource.DBConnection;
import model.Department;
import model.Employer;
import model.EmployerWorkDay;
import sqlquery.SqlQuery;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * Created by Dmitriy on 16.03.2017.
 */
public class Entity {



    HashSet<String> tableNames = new HashSet<String>();

    private ArrayList<Class<?>> entityClasses = new ArrayList<Class<?>>();


    public Entity(Class<?> aClass) {
        if (aClass.isAnnotationPresent(annotation.Entity.class))
            entityClasses.add(aClass);
    }

    public Entity(Class<?>... aClasses) {
        for (Class aClass : aClasses) {
            if (aClass.isAnnotationPresent(annotation.Entity.class)) {
                entityClasses.add(aClass);
            }
        }
    }

    public void showAllDeclaredClassAnnotation() {
        for (Class aClass : entityClasses) {
            System.out.println("*********Annotation class of " + aClass + "***********");
            for (Annotation annotation : aClass.getDeclaredAnnotations())
                System.out.println(annotation);

        }
    }
        public void showAllDeclaredMethods(Class aClass){
            System.out.println("!!!!");
        for(Method method : aClass.getDeclaredMethods()){
            for( Annotation annotation : method.getDeclaredAnnotations()){
                System.out.println(method.getName());
                System.out.println(annotation);
            }
        }
    }

    public List<Employer> findAll(){

    }


    private static ResultSet executeQuery(Class className) throws SQLException {

        String tableName = getTableName(className);
        Connection conn = DBConnection.getConnection();
        PreparedStatement preparedStatement =
                    conn.prepareStatement(SqlQuery.ALL.toString().replaceFirst(":tableName", tableName));
            return preparedStatement.executeQuery();
        }



    public static void main(String[] args) throws SQLException {
        Entity entity = new Entity(EmployerWorkDay.class, Department.class, Employer.class);
        entity.showAllDeclaredClassAnnotation();
        entity.showAllDeclaredMethods(entity.entityClasses.get(0));

            ResultSet resultSet = executeQuery(Employer.class);
//        //showResult(resultSet);
//        //showColumnsName(resultSet);
////        buildEmployerList(resultSet);
    }



    private static String getTableName(Class className) {

        String tableName = null;
        for (Annotation annotation : className.getDeclaredAnnotations()) {
            if (annotation instanceof Table)
                tableName = ((Table) annotation).name();
        }
        if (tableName == null) {
            throw new NoSuchElementException(String.format("%s class is not present %s", className.getName(), Table.class.getName()));
        }
        return tableName;
    }




}

