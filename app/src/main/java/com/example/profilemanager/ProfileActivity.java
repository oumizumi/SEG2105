package com.example.profilemanager;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class ProfileActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
    }

    // Method called when any flag is clicked
    public void SetTeamIcon(View view) {
        // Creating a return Intent to pass to the Main Activity
        Intent returnIntent = new Intent();

        // Figuring out which image was clicked
        ImageView selectedImage = (ImageView) view;

        // Determine the flag name based on the clicked image ID
        String flagName = "flag_ca"; // default

        int viewId = selectedImage.getId();
        if (viewId == R.id.flagid00) {
            flagName = "flag_ca";
        } else if (viewId == R.id.flagid01) {
            flagName = "flag_eg";
        } else if (viewId == R.id.flagid02) {
            flagName = "flag_fr";
        } else if (viewId == R.id.flagid03) {
            flagName = "flag_jp";
        } else if (viewId == R.id.flagid04) {
            flagName = "flag_kr";
        } else if (viewId == R.id.flagid05) {
            flagName = "flag_sp";
        } else if (viewId == R.id.flagid06) {
            flagName = "flag_tr";
        } else if (viewId == R.id.flagid07) {
            flagName = "flag_uk";
        } else if (viewId == R.id.flagid08) {
            flagName = "flag_us";
        }

        // Adding the flag name to the return intent
        returnIntent.putExtra("flagName", flagName);

        // Setting the result as OK and passing the intent
        setResult(RESULT_OK, returnIntent);

        // Finishing the activity and returning to the main screen
        finish();
    }
}