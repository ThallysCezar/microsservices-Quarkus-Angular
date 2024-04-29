package dev.project.fight.client;

import io.smallrye.common.constraint.NotNull;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

@Schema(description="The hero fighting against the villain")
public class Hero {

    @NotNull
    public String name;

    @NotNull
    public int level;

    @NotNull
    public String picture;

    public String powers;

}