package dev.project.fight.client;

import io.smallrye.common.constraint.NotNull;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

@Schema(description="The villain fighting against the hero")
public class Villain {

    @NotNull
    public String name;

    @NotNull
    public int level;

    @NotNull
    public String picture;

    public String powers;

}