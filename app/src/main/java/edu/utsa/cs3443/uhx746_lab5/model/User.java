package edu.utsa.cs3443.uhx746_lab5.model;

import java.io.Serializable;
import java.util.ArrayList;

public class User implements Serializable {
    // Instance Variables
    private String username;
    private String password;
    private String name;
    private ArrayList<Role> roles;

    public User(String username, String password, String name) {
        this.username = username;
        this.password = password;
        this.name = name;

        roles = new ArrayList<>();
    }

    /**
     * validate(String, String):
     *  - Returns true if the user's input matches the `User` object.
     *  - Returns false otherwise.
     * @param user - User entered username.
     * @param pass - User entered password.
     * @return - true -> credentials match | false -> credentials do not match.
     */
    public boolean validate(String user, String pass) {
        return user.equals(this.username) && pass.equals(this.password);
    }

    /**
     * getRoles():
     *  - Getter method for ArrayList<Role> (polymorphism).
     * @return - `roles` of the `User`.
     */
    public ArrayList<Role> getRoles() {
        return roles;
    }

    /**
     * getName():
     *  - Getter method for `name` (polymorphism).
     * @return - `name` of the `User`.
     */
    public String getName() {
        return name;
    }
}
