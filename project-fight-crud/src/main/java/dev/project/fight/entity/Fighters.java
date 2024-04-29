package dev.project.fight.entity;

import dev.project.fight.client.Hero;
import dev.project.fight.client.Villain;
import io.smallrye.common.constraint.NotNull;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

@Schema(description = "A fight between one hero and one villain")
public class Fighters {

    @NotNull
    public Hero hero;

    @NotNull
    public Villain villain;
}
