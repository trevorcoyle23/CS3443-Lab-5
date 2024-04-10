package edu.utsa.cs3443.uhx746_lab5;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import edu.utsa.cs3443.uhx746_lab5.model.Role;
import edu.utsa.cs3443.uhx746_lab5.model.User;

public class MainActivity extends AppCompatActivity {

    // Instance Variable
    private ArrayList<User> users;

    /**
     * onCreate(Bundle):
     *  - Loads `User` objects from "users.csv" and populates them into the ArrayList<User> `users`.
     *  - Prompts the user for a `username` and `password` in corresponding `EditText` views.
     *  - Validates input using `validate` method in `User` class.
     *      + If validate() -> true > login.
     *      + If validate() -> false > try again.
     *  - Passes `User` (if found) to RoleActivity activity through an `Intent`.
     * @param savedInstanceState - IDK what this is but we need it for something :)
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // EditText Views
        EditText usernameET = findViewById(R.id.usernameID);
        EditText passwordET = findViewById(R.id.passwordID);

        // Initialize `users`
        users = new ArrayList<>();
        loadUsers(MainActivity.this);

        // Button
        Button loginButton = findViewById(R.id.loginButton);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // non-null check
                if ((usernameET.getText().toString().equals("")) || (passwordET.getText().toString().equals(""))) {
                    Toast.makeText(MainActivity.this, "Please enter username and password", Toast.LENGTH_SHORT).show();
                } else {
                    // login success flag:
                    //      0 -> fail
                    //      1 -> success
                    int flag = 0;
                    for (User u : users) {
                        if (u.validate(usernameET.getText().toString(), passwordET.getText().toString())) {
                            Intent intent = new Intent(MainActivity.this, RoleActivity.class);
                            intent.putExtra("user", u);
                            startActivity(intent);

                            // indicate if login success
                            flag++;
                            break;
                        }
                    }
                    // check for login failure
                    if (flag == 0) {
                        Toast.makeText(MainActivity.this, "Username/Password incorrect. Try again.", Toast.LENGTH_SHORT).show();
                    }

                    // clear text for when user returns to screen later
                    usernameET.setText("");
                    passwordET.setText("");
                }
            }
        });
    }

    /**
     * loadUsers(Context):
     *  - Loads `User` objects into ArrayList<User> from "users.csv".
     * @param context - Needed for Assets folder access.
     */
    public void loadUsers(Context context) {
        String filename = "users.csv";
        AssetManager assetManager = context.getAssets();

        try {
            InputStream inputStream = assetManager.open(filename);
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            String line;
            while ((line = reader.readLine()) != null) {
                String[] tokens = line.split(",");
                User user = new User(tokens[0], tokens[1], tokens[2]);
                for (int i = 3; i < tokens.length; i++) {
                    Role r = new Role(tokens[i]);
                    user.getRoles().add(r);
                }
                users.add(user);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}