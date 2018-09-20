package pl.coderslab.provider.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="leagues")
public class League
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    @ManyToOne
    private Country country;
    @JsonIgnoreProperties("league")
    @OneToMany(mappedBy = "league", cascade = CascadeType.ALL)
    private Set<Team> teams = new HashSet<>();

    public League(String name, Country country, Set<Team> teams)
    {
        this.name = name;
        this.country = country;
        this.teams = teams;
    }

    public League () {}

    public Long getId()
    {
        return id;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public Set<Team> getTeams() {
        return teams;
    }

    public void setTeams(Set<Team> teams) {
        this.teams = teams;
    }
}
