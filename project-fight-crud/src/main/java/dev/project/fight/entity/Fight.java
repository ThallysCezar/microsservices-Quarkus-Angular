package dev.project.fight.entity;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import io.smallrye.common.constraint.NotNull;
import jakarta.persistence.Entity;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

import java.time.Instant;

@Entity
@Schema(description="Each fight has a winner and a loser")
public class Fight extends PanacheEntity {

    @NotNull
    public Instant fightDate;

    @NotNull
    public String winnerName;

    @NotNull
    public int winnerLevel;

    @NotNull
    public String winnerPicture;

    @NotNull
    public String loserName;

    @NotNull
    public int loserLevel;

    @NotNull
    public String loserPicture;

    @NotNull
    public String winnerTeam;

    @NotNull
    public String loserTeam;

    @Override
    public String toString() {
        return "Fight{" +
                "fightDate=" + fightDate +
                ", winnerName='" + winnerName + '\'' +
                ", winnerLevel=" + winnerLevel +
                ", winnerPicture='" + winnerPicture + '\'' +
                ", loserName='" + loserName + '\'' +
                ", loserLevel=" + loserLevel +
                ", loserPicture='" + loserPicture + '\'' +
                ", winnerTeam='" + winnerTeam + '\'' +
                ", loserTeam='" + loserTeam + '\'' +
                ", id=" + id +
                '}';
    }

}