package dev.project.villain.entity;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import io.smallrye.common.constraint.NotNull;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;

import java.util.Random;

@Entity
public class Villain extends PanacheEntity {

    @NotNull
    @Size(min = 3, max = 30)
    public String name;

    public String otherName;

    @NotNull
    @Min(1)
    public int level;

    public String picture;

    @Column(columnDefinition = "TEXT")
    public String powers;

    @Override
    public String toString() {
        return "Villain{" +
                "name='" + name + '\'' +
                ", otherName='" + otherName + '\'' +
                ", level=" + level +
                ", picture='" + picture + '\'' +
                ", powers='" + powers + '\'' +
                ", id=" + id +
                '}';
    }

    public static Villain findRandomVillain(){
        long countVillain = Villain.count();
        Random random = new Random();
        int randomVillain = random.nextInt((int) countVillain);
        return Villain.findAll().page(randomVillain, 1).firstResult();
    }

}