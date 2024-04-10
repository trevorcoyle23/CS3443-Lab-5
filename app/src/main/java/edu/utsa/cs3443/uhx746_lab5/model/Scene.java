package edu.utsa.cs3443.uhx746_lab5.model;

import java.util.ArrayList;

public class Scene {
    // Instance Variable
    private int idNum;
    private String title;
    private ArrayList<Role> roles;

    // Low-key never needed this class. oops
    public Scene(int idNum, String title) {
        this.idNum = idNum;
        this.title = title;
        roles = new ArrayList<>();
    }
}
