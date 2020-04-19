
package entities;

import java.io.Serializable;

public class Guide implements Serializable {

    private String name;

    public Guide() {
    }

    public Guide(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}