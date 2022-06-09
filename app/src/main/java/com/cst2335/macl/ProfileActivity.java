package com.cst2335.macl;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.widget.EditText;
import android.widget.ImageButton;

public class ProfileActivity extends AppCompatActivity {

    public static final String TAG = "PROFILE_ACTIVITY";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.e(TAG, "In function: onCreate");
        super.onCreate(savedInstanceState);
        EditText emailText = findViewById(R.id.emailEdit);
        setContentView(R.layout.activity_profile);
        Intent fromMain = getIntent();
        emailText.setText(fromMain.getStringExtra("EMAIL"));
        ImageButton imgView = findViewById(R.id.imgButton);
        ActivityResultLauncher<Intent> myPictureTakerLauncher =
                registerForActivityResult( new ActivityResultContracts.StartActivityForResult()
                        ,new ActivityResultCallback<ActivityResult>() {
            @Override
            public void onActivityResult(ActivityResult result) {
                if (result.getResultCode() == Activity.RESULT_OK)
                { Intent data = result.getData();
                    Bitmap imgbitmap = (Bitmap) data.getExtras().get("data");
                    imgView.setImageBitmap(imgbitmap); // the imageBuLon
                }
                else if(result.getResultCode() == Activity.RESULT_CANCELED)
                    Log.i(TAG, "User refused to capture a picture.");
            }
        } );

    }

    protected void onStart() {
        Log.e(TAG, "In function: onStart");
        super.onStart();
    }

    protected void onStop() {
        Log.e(TAG, "In function: onStop");
        super.onStop();
    }

    protected void onPause() {
        Log.e(TAG, "In function: onPause");
        super.onPause();
    }

    protected void onResume() {
        Log.e(TAG, "In function: onResume");
        super.onResume();
    }

    protected void onDestroy() {
        Log.e(TAG, "In function: onDestroy");
        super.onDestroy();
    }

    protected void onActivityResult(){
        Log.e(TAG, "In function: onActivityResult");

    }

    private void dispatchTakePictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            ActivityResultLauncher<Intent> myPictureTakerLauncher = null;
            myPictureTakerLauncher.launch(takePictureIntent);
        }
    }
}