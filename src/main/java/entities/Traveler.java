
package entities;

import java.io.Serializable;

public class Traveler implements Serializable {

    private String name;

    public Traveler() {
    }

    public Traveler(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}