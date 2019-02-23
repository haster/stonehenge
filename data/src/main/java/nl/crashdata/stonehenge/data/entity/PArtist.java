package nl.crashdata.stonehenge.data.entity;

import javax.persistence.*;

@Entity
@Table(name="Artist")
@Access(AccessType.PROPERTY)
public class PArtist {
    @Id
    @Column(name="id")
    @Access(AccessType.FIELD)
    private Long id;

    @Version
    @Column(name="version")
    private Long version;

    @Column(name="name")
    @Basic
    private String name;

    protected PArtist() {

    }

    public Long getId() {
        return id;
    }

    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
