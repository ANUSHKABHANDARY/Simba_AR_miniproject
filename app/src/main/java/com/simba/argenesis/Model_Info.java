package com.simba.argenesis;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.speech.tts.UtteranceProgressListener;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.bumptech.glide.*;
import java.util.Locale;

public class Model_Info extends AppCompatActivity {

    View viewAR;
    ImageButton backButton, favButton, moreButton;
    Toast mToast = null;
    TextToSpeech TTS;

    TextView modelName, modelDescription;
    ImageView modelImage;

    final boolean[] sound = {false};

    Button SoundButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_model_info);
        modelName = findViewById(R.id.Model_Name);
        modelDescription = findViewById(R.id.Model_Description);
        modelImage = findViewById(R.id.Model_Image);
        Context context = getBaseContext();

        FirebaseFirestore db = FirebaseFirestore.getInstance();
        DocumentReference docRef = db.collection("Model_Information").document("6xfT3Ve9QruwkaqUSrVz");

        docRef.get().addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                DocumentSnapshot document = task.getResult();
                if (document.exists()) {
                    Log.d("TAG", "DocumentSnapshot data: " + document.getData());

                    modelName.setText((CharSequence) document.get("Model_Name"));
                    modelDescription.setText((CharSequence) document.get("Model_Description"));
                    Glide.with(context)
                            .load(document.get("Model_Image"))
                            .into(modelImage);
                } else {
                    Log.d("TAG", "No such document");
                }
            } else {
                Log.d("TAG", "get failed with ", task.getException());
            }
        });

        viewAR = findViewById(R.id.View_AR_Model_Button);
        viewAR.setOnClickListener(view -> {
            Toast.makeText(Model_Info.this, "View Model", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(intent);
        });
        backButton = findViewById(R.id.Back_Button);
        favButton = findViewById(R.id.Favourite_Button);
        moreButton = findViewById(R.id.More_Button);

        backButton.setOnClickListener(view -> {
            showAToast("Back Button");
            Intent intent = new Intent(getApplicationContext(), HomePage.class);
            startActivity(intent);
        });

        favButton.setOnClickListener(view -> {
            showAToast("Favourite Button");
        });

        moreButton.setOnClickListener(view -> {
            showAToast("More Button");
        });

        TTS = new TextToSpeech(this, status -> {
            if (status == TextToSpeech.SUCCESS) {
                int result = TTS.setLanguage(new Locale("en", "IN"));

                if (result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                    showAToast("Language Data Missing or Language Not Supported");
                }
            } else {
                showAToast("Initialization Failed");
            }
            TTS.setOnUtteranceProgressListener(new UtteranceProgressListener() {
                @Override
                public void onStart(String s) {
                }

                @Override
                public void onDone(String s) {
                    runOnUiThread(() -> {
                        SoundButton.setBackgroundResource(R.drawable.ic_stop_audio);
                    });
                }
                @Override
                public void onError(String s) {

                }
            });
        });

        SoundButton = findViewById(R.id.Sound_BT);
        SoundButton.setOnClickListener(view -> {
            if (!sound[0]) {
                SoundButton.setBackgroundResource(R.drawable.ic_play_audio);
                sound[0] = true;
                speak();
            } else {
                SoundButton.setBackgroundResource(R.drawable.ic_stop_audio);
                sound[0] = false;
                TTS.stop();
            }
        });
    }

    private void speak() {
        String text = getString(R.string.earth_description);
        SoundButton = findViewById(R.id.Sound_BT);

        TTS.speak(text, TextToSpeech.QUEUE_FLUSH, null, "");
    }

    public void showAToast(String message) {

        if (mToast != null) {
            mToast.cancel();
        }
        mToast = Toast.makeText(this, message, Toast.LENGTH_SHORT);
        mToast.show();
    }

    @Override
    protected void onDestroy() {
        if (TTS != null) {
            TTS.stop();
            TTS.shutdown();
        }
        super.onDestroy();
    }

}