package com.example.profilemanager;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    // ActivityResultLauncher to handle results from ProfileActivity
    ActivityResultLauncher<Intent> profileActivityResultLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if (result.getResultCode() == Activity.RESULT_OK) {
                        Intent data = result.getData();
                        if (data != null) {
                            ImageView avatarImage = findViewById(R.id.avatarImage);

                            // Get the flag name from the returned intent
                            String flagName = data.getStringExtra("flagName");

                            // Use switch statement on String - this IS allowed!
                            String drawableName;
                            switch (flagName) {
                                case "flag_ca":
                                    drawableName = "flag_ca";
                                    break;
                                case "flag_eg":
                                    drawableName = "flag_eg";
                                    break;
                                case "flag_fr":
                                    drawableName = "flag_fr";
                                    break;
                                case "flag_jp":
                                    drawableName = "flag_jp";
                                    break;
                                case "flag_kr":
                                    drawableName = "flag_kr";
                                    break;
                                case "flag_sp":
                                    drawableName = "flag_sp";
                                    break;
                                case "flag_tr":
                                    drawableName = "flag_tr";
                                    break;
                                case "flag_uk":
                                    drawableName = "flag_uk";
                                    break;
                                case "flag_us":
                                    drawableName = "flag_us";
                                    break;
                                default:
                                    drawableName = "flag_ca";
                                    break;
                            }

                            // Get the resource ID for the selected flag and set it to the ImageView
                            int resID = getResources().getIdentifier(drawableName, "drawable", getPackageName());
                            avatarImage.setImageResource(resID);
                        }
                    }
                }
            });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    // Method to open the ProfileActivity when avatar image is clicked
    public void OnSetAvatarButton(View view) {
        Intent intent = new Intent(getApplicationContext(), ProfileActivity.class);
        profileActivityResultLauncher.launch(intent);
    }

    // Method to open Google Maps with the entered postal code
    public void OnOpenInGoogleMaps(View view) {
        EditText teamAddress = findViewById(R.id.teamAddressTextView);

        String address = teamAddress.getText().toString();

        if (!address.isEmpty()) {
            // Create a Uri from the postal code/address entered by the user
            Uri gmmIntentUri = Uri.parse("http://maps.google.co.in/maps?q=" + address);

            // Create an Intent from gmmIntentUri. Set the action to ACTION_VIEW
            Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);

            // Make the Intent explicit by setting the Google Maps package
            mapIntent.setPackage("com.google.android.apps.maps");

            // Attempt to start an activity that can handle the Intent
            startActivity(mapIntent);
        }
    }
}