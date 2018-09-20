package pl.coderslab.provider.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "countries")
public class Country
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;
    @JsonIgnoreProperties({"country", "teams"})
    @OneToMany(mappedBy = "country", cascade = CascadeType.ALL)
    private Set<League> leagues = new HashSet<>();

    public Country(String name, Set<League> leagues)
    {
        this.name = name;
        this.leagues = leagues;
    }

    public Country () {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<League> getLeagues() {
        return leagues;
    }

    public void setLeagues(Set<League> leagues) {
        this.leagues = leagues;
    }
}
