package org.demo.practic1.dao;

import org.demo.practic1.models.Author;

import java.sql.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by u0100 on 02.06.2016.
 */
public class AuthorDao {
    private final Connection connection;
    private List<Author> authorses;
    private String sql = null;
    private Statement statement = null;
    private ResultSet resultSet = null;

    public AuthorDao(Connection connection) {
        this.connection = connection;
    }

    //Сохранить испонителя
    public Author save(Author author){
        try {
            PreparedStatement statement = connection.prepareStatement("insert INTO authors(FIRST_NAME, LAST_NAME, BIRTHDAY) VALUES (?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, author.getFirstName());
            statement.setString(2, author.getLastName());
            statement.setDate(3, (Date) author.getBirthDay());
            statement.executeUpdate();
            ResultSet resultSet = statement.getGeneratedKeys();
            if (resultSet.next()){
                author.setId(resultSet.getInt(1));
            } else return null;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return author;
    }

    //Обновить испонителя
    public Author update(Author author){
        try {
            PreparedStatement statement = connection.prepareStatement("update authors set FIRST_NAME=?, LAST_NAME=?, BIRTHDAY=?, ID=?");
            statement.setString(1, author.getFirstName());
            statement.setString(2, author.getLastName());
            statement.setDate(3, (Date) author.getBirthDay());
            statement.setInt(4, author.getId());
            int str = statement.executeUpdate();
            if (str != 1) return null;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return author;
    }

    //Удалить испонителя
    public Author delete(Author author){
        try {
            PreparedStatement statement = connection.prepareStatement("delete from AUTHORS where FIRST_NAME=?");
            statement.setInt(1, author.getId());
            int str = statement.executeUpdate();
            if (str != 1) return null;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return author;
    }

    //Вернуть испонителя по ID
    public Author getId(int id){
        sql = String.format("select ID,FIRST_NAME,LAST_NAME,BirthDay from authors where ID=%d", id);
        Statement statement = null;
        Author author = null;
        try {
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            
            if (resultSet.next()){
                author = new Author();
                author.setId(resultSet.getInt("ID"));
                author.setLastName(resultSet.getString("LAST_NAME"));
                author.setFirstName(resultSet.getString("FIRST_NAME"));
                author.setBirthDay(resultSet.getDate("BIRTHDAY"));
            }
            resultSet.close();
            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return author;
    }

    //Вернуть всех испонителей
    public List<Author> getAll(){
        sql = "select ID, FIRST_NAME, LAST_NAME, BIRTHDAY from authors";
        authorses = new ArrayList<Author>();
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                Author authors = new Author();
                authors.setId(resultSet.getInt("ID"));
                authors.setFirstName(resultSet.getString("FIRST_NAME"));
                authors.setLastName(resultSet.getString("LAST_NAME"));
                authors.setBirthDay(resultSet.getDate("BIRTHDAY"));
                authorses.add(authors);
            }
            resultSet.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return authorses;
    }

    //Получить список испонителей родившихся в указаном диапазоне
    public List<Author> getAuthorsBetweenYears(String firstDate, String lastDate) {
        sql = "SELECT authors.ID, FIRST_NAME,LAST_NAME FROM authors WHERE authors.BIRTHDAY BETWEEN " + firstDate + "AND" + lastDate;
        authorses = new LinkedList<>();
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                Author author = new Author();
                author.setId(resultSet.getInt(1));
                author.setFirstName(resultSet.getString(2));
                author.setLastName(resultSet.getString(3));
                authorses.add(author);
            }
            resultSet.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (statement != null) {
                try {
                    statement.close();
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return authorses;
    }

    //"Получить список исполнителей родившихся в указаных датах.
    public List<Author> getAuthorsInYears(List<String> listDate) {
        String dates = "";
        for (int i = 0; i < listDate.size(); i++) {
            if (i != listDate.size() - 1) {
                dates = dates + "\"" + listDate.get(i) + "\", ";
            } else {
                dates = dates + "\"" + listDate.get(i) + "\"";
            }
        }
        System.out.println(dates);
        sql = "SELECT authors.id, FIRST_NAME, LAST_NAME FROM authors WHERE authors.BIRTHDAY IN (" + dates + ");";
        authorses = new LinkedList<>();
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                Author author = new Author();
                author.setId(resultSet.getInt(1));
                author.setFirstName(resultSet.getString(2));
                author.setLastName(resultSet.getString(3));
                authorses.add(author);
            }
            resultSet.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (statement != null) {
                try {
                    statement.close();
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return authorses;
    }

}
