package edu.utsa.cs3443.uhx746_lab5;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import edu.utsa.cs3443.uhx746_lab5.model.Role;
import edu.utsa.cs3443.uhx746_lab5.model.User;

public class RoleActivity extends AppCompatActivity {

    /**
     * onCreate(Bundle):
     *  - Initializes `User` object from `Intent`.
     *  - Displays the `User`'s real name and their `Role`(s) in
     *    the production (if any).
     *  - Displays three buttons:
     *      + Act 1
     *      + Act 2
     *      + Log Out
     *  - Based on which button the user pushes, `Act 1` and `Act 2`
     *    open ActActivity activity passing the `name` of the act pushed.
     *    `Log Out` finishes the activity with finish() and returns to
     *    MainActivity activity.
     * @param savedInstanceState - Again... No idea what this is.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_role);

        // Intent
        Intent intent = getIntent();
        User user = (User)intent.getSerializableExtra("user");

        // TextView
        TextView nameText = findViewById(R.id.nameText);
        nameText.setText("");
        TextView roleText = findViewById(R.id.roleText);
        roleText.setText("");

        // Find all Roles
        nameText.setText(user.getName());
        for (Role r : user.getRoles()) {
            roleText.append(r.getCharacterName() + "\n");
        }

        // Buttons
        Button act1, act2, logOut;
        act1 = findViewById(R.id.act1);
        act2 = findViewById(R.id.act2);
        logOut = findViewById(R.id.logOut);

        // act1
        act1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RoleActivity.this, ActActivity.class);
                intent.putExtra("name", "act1");
                intent.putExtra("user", user);
                startActivity(intent);
            }
        });

        // act2
        act2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RoleActivity.this, ActActivity.class);
                intent.putExtra("name", "act2");
                intent.putExtra("user", user);
                startActivity(intent);
            }
        });

        // logOut
        logOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}