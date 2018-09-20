package pl.coderslab.provider.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="teams")
public class Team
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;
    @JsonIgnoreProperties({"country", "teams"})
    @ManyToOne
    private League league;


    private int attackPotential;

    private int defencePotential;

    public Team(String name, League league, int attackPotential, int defencePotential) {
        this.name = name;
        this.league = league;
        this.attackPotential = attackPotential;
        this.defencePotential = defencePotential;
    }

    public Team () {}

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

    public League getLeague() {
        return league;
    }

    public void setLeague(League league) {
        this.league = league;
    }


    public int getAttackPotential() {
        return attackPotential;
    }

    public void setAttackPotential(int attackPotential) {
        this.attackPotential = attackPotential;
    }

    public int getDefencePotential() {
        return defencePotential;
    }

    public void setDefencePotential(int defencePotential) {
        this.defencePotential = defencePotential;
    }
}
