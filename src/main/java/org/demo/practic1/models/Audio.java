package org.demo.practic1.models;

/**
 * Created by u0100 on 02.06.2016.
 */
public class Audio {
    private int id;
    private String title;
    private int year;
    private int duration;
    private Author author;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Audio)) return false;

        Audio audio = (Audio) o;

        if (getId() != audio.getId()) return false;
        if (getYear() != audio.getYear()) return false;
        if (getDuration() != audio.getDuration()) return false;
        if (!getTitle().equals(audio.getTitle())) return false;
        return getAuthor().equals(audio.getAuthor());

    }

    @Override
    public int hashCode() {
        int result = getId();
        result = 31 * result + getTitle().hashCode();
        result = 31 * result + getYear();
        result = 31 * result + getDuration();
        result = 31 * result + getAuthor().hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Audio{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", year=" + year +
                ", duration=" + duration +
                ", author=" + author +
                '}';
    }
}
