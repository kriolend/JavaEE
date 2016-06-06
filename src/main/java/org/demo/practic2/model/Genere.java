package org.demo.practic2.model;

import javax.persistence.*;
import java.util.Collection;

/**
 * Created by u0100 on 06.06.2016.
 */
@Entity
public class Genere {
    private int id;
    private String title;
    private Collection<Audios> audiosesById;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Genere genere = (Genere) o;

        if (id != genere.id) return false;
        if (title != null ? !title.equals(genere.title) : genere.title != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (title != null ? title.hashCode() : 0);
        return result;
    }

    @OneToMany(mappedBy = "genereByGenereId")
    public Collection<Audios> getAudiosesById() {
        return audiosesById;
    }

    public void setAudiosesById(Collection<Audios> audiosesById) {
        this.audiosesById = audiosesById;
    }
}
