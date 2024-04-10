package edu.utsa.cs3443.uhx746_lab5.model;

import java.io.Serializable;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Role implements Serializable {
    // Instance Variables
    private String name;
    public Role(String name) {
        this.name = name;

    }

    /**
     * getName():
     *  - Getter method for a `Role` object (polymorphism).
     * @return - `name` of `Role` object.
     */
    public String getName() {
        return name;
    }

    /**
     * toString():
     *  - String representation of a `Role` object.
     * @return - `name` of `Role` object.
     */
    @Override
    public String toString() {
        return name;
    }

    /**
     * getCharacterName():
     *  - Uses `Pattern` and `Matcher` objects to extract the
     *    character name enclosed in parenthesis from the `name`.
     * @return - The String enclosed in parenthesis.
     */
    public String getCharacterName() {
        Pattern pattern = Pattern.compile("\\((.*?)\\)");
        Matcher matcher = pattern.matcher(name);
        if (matcher.find()) {
            return matcher.group(1);
        }
        return "";
    }
}
