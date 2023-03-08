package pro.b2b2b.germ.model;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(uniqueConstraints = { @UniqueConstraint(name = "UniqueInnAndKpp", columnNames = { "inn", "kpp" }) })
public class Organization {

    private @Id @GeneratedValue Long id;
    private String shortName;
    private String fullName;
    private String inn;
    private String kpp;

    Organization() {}

    public Organization(String shortName, String fullName, String inn, String kpp) {
        this.shortName = shortName;
        this.fullName = fullName;
        this.inn = inn;
        this.kpp = kpp;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Organization that = (Organization) o;
        return inn.equals(that.inn) && kpp.equals(that.kpp);
    }

    @Override
    public String toString() {
        return "Organization{" +
                "id=" + id +
                ", shortName='" + shortName + '\'' +
                ", fullName='" + fullName + '\'' +
                ", inn='" + inn + '\'' +
                ", kpp='" + kpp + '\'' +
                '}';
    }

    @Override
    public int hashCode() {
        return Objects.hash(inn, kpp);
    }

    public Long getId() {
        return id;
    }

    public String getShortName() {
        return shortName;
    }

    public String getFullName() {
        return fullName;
    }

    public String getInn() {
        return inn;
    }

    public String getKpp() {
        return kpp;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public void setInn(String inn) {
        this.inn = inn;
    }

    public void setKpp(String kpp) {
        this.kpp = kpp;
    }
}
