
package entities;

import java.io.Serializable;

public class Trip implements Serializable {

    private String name;

    public Trip() {
    }

    public Trip(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}