package org.demo.practic2.model;

import javax.persistence.*;
import java.util.Collection;

/**
 * Created by u0100 on 06.06.2016.
 */
@Entity
public class Audios {
    private int id;
    private String title;
    private Integer duration;
    private Integer year;
    private Integer genereId;
    private Integer groupId;
    private Collection<Album> albaById;
    private Genere genereByGenereId;
    private Group groupByGroupId;

    @Id
    @Column(name = "ID")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "TITLE")
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Basic
    @Column(name = "DURATION")
    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    @Basic
    @Column(name = "YEAR")
    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    @Basic
    @Column(name = "GENERE_ID")
    public Integer getGenereId() {
        return genereId;
    }

    public void setGenereId(Integer genereId) {
        this.genereId = genereId;
    }

    @Basic
    @Column(name = "GROUP_ID")
    public Integer getGroupId() {
        return groupId;
    }

    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Audios audios = (Audios) o;

        if (id != audios.id) return false;
        if (title != null ? !title.equals(audios.title) : audios.title != null) return false;
        if (duration != null ? !duration.equals(audios.duration) : audios.duration != null) return false;
        if (year != null ? !year.equals(audios.year) : audios.year != null) return false;
        if (genereId != null ? !genereId.equals(audios.genereId) : audios.genereId != null) return false;
        if (groupId != null ? !groupId.equals(audios.groupId) : audios.groupId != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + (duration != null ? duration.hashCode() : 0);
        result = 31 * result + (year != null ? year.hashCode() : 0);
        result = 31 * result + (genereId != null ? genereId.hashCode() : 0);
        result = 31 * result + (groupId != null ? groupId.hashCode() : 0);
        return result;
    }

    @ManyToMany(mappedBy = "audiosByAudiosId")
    public Collection<Album> getAlbaById() {
        return albaById;
    }

    public void setAlbaById(Collection<Album> albaById) {
        this.albaById = albaById;
    }

    @ManyToOne
    @JoinColumn(name = "GENERE_ID", referencedColumnName = "ID")
    public Genere getGenereByGenereId() {
        return genereByGenereId;
    }

    public void setGenereByGenereId(Genere genereByGenereId) {
        this.genereByGenereId = genereByGenereId;
    }

    @ManyToOne
    @JoinColumn(name = "GROUP_ID", referencedColumnName = "ID")
    public Group getGroupByGroupId() {
        return groupByGroupId;
    }

    public void setGroupByGroupId(Group groupByGroupId) {
        this.groupByGroupId = groupByGroupId;
    }


}
