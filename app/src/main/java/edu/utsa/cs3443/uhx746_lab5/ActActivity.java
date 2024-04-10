package edu.utsa.cs3443.uhx746_lab5;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import edu.utsa.cs3443.uhx746_lab5.model.Role;
import edu.utsa.cs3443.uhx746_lab5.model.User;

public class ActActivity extends AppCompatActivity {

    /**
     * onCreate(Bundle):
     *  - Finds the name of the `Act` passed by the `Intent`.
     *  - Opens the .txt of the chosen `Act` and searches for
     *    the current `User` in a `Scene` of the `Act`.
     *      + If the `User` is found in a `Scene`, we display it
     *        in the `ScrollView` fragment.
     * @param savedInstanceState - Again... For the last time... No idea.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_act);

        // Intent
        Intent intent = getIntent();
        String act = intent.getStringExtra("name");

        // Get User
        User user = (User)intent.getSerializableExtra("user");

        // TextView
        TextView actText = findViewById(R.id.actText);
        switch (act) {
            case "act1":
                actText.setText("Act 1");
                break;
            case "act2":
                actText.setText("Act 2");
                break;
        }

        // Try opening the file
        try {
            openFile(ActActivity.this, act, user);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * openFile(Context, String, User):
     *  - Tries to open file based on the `String` parameter passed.
     *  - Uses `AssetManager` to access the Assets folder.
     *  - Checks each line in the file for the `User`'s `Role` in the `Act`.
     *      + If found -> Add the `Scene` the `User` has a `Role` in to the `ScrollView`.
     * @param context - Needed for `AssetManager`.
     * @param filename - The file we are trying to open.
     * @param user - The `User` we are searching for in the file.
     * @throws IOException - Thrown if file not found.
     */
    public void openFile(Context context, String filename, User user) throws IOException {
        filename = filename + ".txt";
        LinearLayout linearLayout = findViewById(R.id.linearLayout);
        LayoutInflater inflater = LayoutInflater.from(ActActivity.this);
        AssetManager assetManager = context.getAssets();

        try {
            InputStream inputStream = assetManager.open(filename);
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            String line;

            while ((line = reader.readLine()) != null) {
                String[] data = line.split(": ");
                String[] tokens = data[1].split(",");
                for (String t : tokens) {
                    for (Role r : user.getRoles()) {
                        if (t.trim().equals(r.getCharacterName())) {
                            View itemView = inflater.inflate(R.layout.item_layout, linearLayout, false);
                            TextView textView = itemView.findViewById(R.id.scene);
                            textView.setText(line);
                            linearLayout.addView(itemView);
                        }
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}