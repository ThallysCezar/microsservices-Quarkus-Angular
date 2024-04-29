package dev.project.heroes.service;

import dev.project.heroes.entity.Hero;
import jakarta.enterprise.context.ApplicationScoped;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

import java.util.List;

import static jakarta.transaction.Transactional.TxType.REQUIRED;
import static jakarta.transaction.Transactional.TxType.SUPPORTS;

@ApplicationScoped
@Transactional(REQUIRED)
public class HeroService {

    @ConfigProperty(name = "level.multiplier", defaultValue = "1")
    int levelMultiplier;

    @Transactional(SUPPORTS)
    public List<Hero> findAllHeroes(){
        return Hero.listAll();
    }

    @Transactional(SUPPORTS)
    public Hero findHeroById(Long id){
        return Hero.findById(id);
    }

    @Transactional(SUPPORTS)
    public Hero findRandomHero(){
        Hero randomHero = null;
        while (randomHero == null){
            randomHero = Hero.findRandomHero();
        }
        return randomHero;
    }

    public Hero persistHero(@Valid Hero hero){
        hero.level = hero.level * levelMultiplier;
        Hero.persist(hero);
        return hero;
    }

    public Hero updateHero(@Valid Hero hero){
        Hero entity = Hero.findById(hero.id);
        entity.name = hero.name;
        entity.otherName = hero.otherName;
        entity.level = hero.level;
        entity.picture = hero.picture;
        entity.powers = hero.powers;
        return entity;
    }

    public void deleteHero(Long id){
        Hero hero = Hero.findById(id);
        hero.delete();
    }
}