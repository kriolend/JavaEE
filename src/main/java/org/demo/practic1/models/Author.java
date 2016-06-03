package org.demo.practic1.models;

import java.util.Date;

/**
 * Created by u0100 on 02.06.2016.
 */
public class Author {
    private int id;
    private String firstName;
    private String lastName;
    private Date birthDay;
    private Audio audio;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Date getBirthDay() {
        return birthDay;
    }

    public void setBirthDay(Date birthday) {
        this.birthDay = birthday;
    }

    public Audio getAudio() {
        return audio;
    }

    public void setAudio(Audio audio) {
        this.audio = audio;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Author)) return false;

        Author author = (Author) o;

        if (getId() != author.getId()) return false;
        if (!getFirstName().equals(author.getFirstName())) return false;
        if (!getLastName().equals(author.getLastName())) return false;
        if (!getBirthDay().equals(author.getBirthDay())) return false;
        return getAudio().equals(author.getAudio());

    }

    @Override
    public int hashCode() {
        int result = getId();
        result = 31 * result + getFirstName().hashCode();
        result = 31 * result + getLastName().hashCode();
        result = 31 * result + getBirthDay().hashCode();
        result = 31 * result + getAudio().hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Author{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", birthday=" + birthDay +
                ", audio=" + audio +
                '}';
    }
}
