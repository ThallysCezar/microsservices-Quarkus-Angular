package dev.project.fight.service;

import dev.project.fight.client.Hero;
import dev.project.fight.client.Villain;
import dev.project.fight.entity.Fight;
import dev.project.fight.entity.Fighters;
import dev.project.fight.client.HeroService;
import dev.project.fight.client.VillainService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import java.time.Instant;
import java.util.List;
import java.util.Random;

import static jakarta.transaction.Transactional.TxType.SUPPORTS;
import static jakarta.transaction.Transactional.TxType.REQUIRED;

@ApplicationScoped
@Transactional(SUPPORTS)
public class FightService {

//    private static final Logger LOGGER = Logger.getLogger(FightService.class);

    @Inject
    @RestClient
    HeroService heroService;

    @Inject
    @RestClient
    VillainService villainService;

    private final Random random = new Random();

    public List<Fight> findAllFights() {
        return Fight.listAll();
    }

    public Fight findFightById(Long id) {
        return Fight.findById(id);
    }

    @Transactional(REQUIRED)
    public Fight persistFight(Fighters fighters) {
        Fight fight;

        int heroAdjust = random.nextInt(20);
        int villainAdjust = random.nextInt(20);

        if ((fighters.hero.level + heroAdjust)
                > (fighters.villain.level + villainAdjust)) {
            fight = heroWon(fighters);
        } else if (fighters.hero.level < fighters.villain.level) {
            fight = villainWon(fighters);
        } else {
            fight = random.nextBoolean() ? heroWon(fighters) : villainWon(fighters);
        }

        fight.fightDate = Instant.now();
        fight.persist(fight);
        return fight;
    }


    public Fighters findRandomFighters() {
        Hero hero = findRandomHero();
        Villain villain = findRandomVillain();

        Fighters fighters = new Fighters();
        fighters.hero = hero;
        fighters.villain = villain;
        return fighters;
    }

    private Fight heroWon(Fighters fighters) {
//        LOGGER.info("Yes, Hero won :0");
        Fight fight = new Fight();
        fight.winnerName = fighters.hero.name;
        fight.winnerPicture = fighters.hero.picture;
        fight.winnerLevel = fighters.hero.level;
        fight.loserName = fighters.villain.name;
        fight.loserPicture = fighters.villain.picture;
        fight.loserLevel = fighters.villain.level;
        fight.winnerTeam = "heroes";
        fight.loserTeam = "villains";
        return fight;
    }

    private Fight villainWon(Fighters fighters) {
//        LOGGER.info("Gee, Villain won :o(");
        Fight fight = new Fight();
        fight.winnerName = fighters.villain.name;
        fight.winnerPicture = fighters.villain.picture;
        fight.winnerLevel = fighters.villain.level;
        fight.loserName = fighters.hero.name;
        fight.loserPicture = fighters.hero.picture;
        fight.loserLevel = fighters.hero.level;
        fight.winnerTeam = "villains";
        fight.loserTeam = "heroes";
        return fight;
    }

    private Hero findRandomHero() {
        return heroService.findRandomHero();
    }


    private Villain findRandomVillain() {
        return villainService.findRandomVillain();
    }

}