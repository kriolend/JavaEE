package org.demo.practic2.model;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;

/**
 * Created by u0100 on 06.06.2016.
 */
@Entity
public class Album {
    private int id;
    private String title;
    private Integer audiosId;

    @Basic
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
    @Column(name = "AUDIOS_ID")
    public Integer getAudiosId() {
        return audiosId;
    }

    public void setAudiosId(Integer audiosId) {
        this.audiosId = audiosId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Album album = (Album) o;

        if (id != album.id) return false;
        if (title != null ? !title.equals(album.title) : album.title != null) return false;
        if (audiosId != null ? !audiosId.equals(album.audiosId) : album.audiosId != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + (audiosId != null ? audiosId.hashCode() : 0);
        return result;
    }
}
