package hellojpa;

import jakarta.persistence.Entity;

@Entity
public class Movie extends Item{
    private String director;
    private String Actor;

    public String getDirector() {
        return director;
    }

    public void setDirector(final String director) {
        this.director = director;
    }

    public String getActor() {
        return Actor;
    }

    public void setActor(final String actor) {
        Actor = actor;
    }
}
