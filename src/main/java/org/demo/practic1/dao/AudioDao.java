package org.demo.practic1.dao;

import org.demo.practic1.models.Audio;
import org.demo.practic1.models.Author;

import java.sql.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by u0100 on 02.06.2016.
 */
public class AudioDao {
    private final Connection connection;
    String sql = null;
    List<Audio> audioses;

    public AudioDao(Connection connection) {
        this.connection = connection;
    }

    public Audio save(Audio audio) {
        try {
            PreparedStatement statement = connection.prepareStatement("INSERT INTO AUDIOS (TITLE, YEAR,DURATION) VALUES (?,?,?) ", Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, audio.getTitle());
            statement.setInt(2, audio.getYear());
            statement.setInt(3, audio.getDuration());
            statement.executeUpdate();
            ResultSet resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                audio.setId(resultSet.getInt(1));
            } else return null;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return audio;
    }

    public Audio update(Audio audio) {
        try {
            PreparedStatement statement = connection.prepareStatement("UPDATE AUDIOS set TITLE = ?, YEAR = ?, DURATION = ? WHERE ID = ? ");
            statement.setString(1, audio.getTitle());
            statement.setInt(2, audio.getDuration());
            statement.setInt(3, audio.getYear());
            statement.setInt(4, audio.getId());
            int str = statement.executeUpdate();
            if (str != 1) return null;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return audio;
    }

    public Audio delete(Audio audio) {
        try {
            PreparedStatement statement = connection.prepareStatement("delete from AUDIOS where AUDIOS=?");
            statement.setInt(1, audio.getId());
            int str = statement.executeUpdate();
            if (str != 1) return null;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return audio;
    }

    public Audio getId(int id) {
        Statement statement = null;
        sql = String.format("select ID,TITLE,YEAR,DURATION from AUDIOS where ID =?", id);

        try {
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            Audio audio = null;
            if (resultSet.next()) {
                audio = new Audio();
                audio.setId(resultSet.getInt("ID"));
                audio.setTitle(resultSet.getString("TITLE"));
                audio.setYear(resultSet.getInt("YEAR"));
                audio.setDuration(resultSet.getInt("DURATION"));
            }
            resultSet.close();
            return audio;
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
        return null;
    }

    public List<Audio> getAll() {
        sql = "select ID, TITLE, YEAR, DURATION from AUDIOS";
        Statement statement = null;
        audioses = new ArrayList<>();
        try {
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                Audio audio = new Audio();
                audio.setId(resultSet.getInt("ID"));
                audio.setTitle(resultSet.getString("TITLE"));
                audio.setYear(resultSet.getInt("YEAR"));
                audio.setDuration(resultSet.getInt("DURATION"));
                audioses.add(audio);
            }
            resultSet.close();
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
        return audioses;
    }


    public List<Audio> audioByAuthor(int authorId) {
        sql = "select title from audios where id IN (select audio_id from authors_audios where author_id=" + authorId + ")";
        Statement statement = null;
        audioses = new ArrayList<>();
        try {
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                Audio audio = new Audio();
                audio.setTitle(resultSet.getString(1));
                audioses.add(audio);
            }
            resultSet.close();
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
        return audioses;
    }


    public List<Audio> audioByYearWithInfoAuthors(int year) {
        sql = "SELECT title, firstname, lastname FROM audios JOIN authors_audios ON audios.id=authors_audios.audio_id JOIN authors ON authors_audios.author_id=author_id WHERE audios.year=" + year + ";";
        audioses = new LinkedList<>();
        Statement statement = null;
        try {
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                Audio audio = new Audio();
                audio.setTitle(resultSet.getString(1));
                Author author = new Author();
                author.setFirstName(resultSet.getString(2));
                author.setLastName(resultSet.getString(3));
                audio.setAuthor(author);
                audioses.add(audio);
            }
            resultSet.close();
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
        return audioses;
    }

    public List<Audio> adioByOldAuthor() {
        sql = "SELECT title FROM audios JOIN authors_audios ON audios.id=authors_audios.audio_id JOIN authors ON authors_audios.author_id=author_id WHERE  birthday = (SELECT MAX(birthday) FROM authors);";
        audioses = new LinkedList<>();
        Statement statement = null;
        try {
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                Audio audio = new Audio();
                audio.setTitle(resultSet.getString(1));
                audioses.add(audio);
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
        return audioses;
    }

}
