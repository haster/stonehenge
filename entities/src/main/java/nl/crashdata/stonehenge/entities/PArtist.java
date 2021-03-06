package nl.crashdata.stonehenge.entities;

import javax.persistence.*;
import java.util.function.Supplier;

@Entity
@Table(name="Artist")
@Access(AccessType.PROPERTY)
@SequenceGenerator(name = "sequence", initialValue = 1000, allocationSize = 100)
public class PArtist {
    @Id
    @Column(name="id")
    @Access(AccessType.FIELD)
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "sequence")
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

    public void setId(Long id) {
        this.id = id;
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

    public Supplier<PArtist> unproxy() {
        return () -> this;
    }
}
