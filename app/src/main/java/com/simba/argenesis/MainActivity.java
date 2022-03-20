package com.simba.argenesis;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.widget.Button;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.ar.core.Anchor;
import com.google.ar.sceneform.AnchorNode;
import com.google.ar.sceneform.assets.RenderableSource;
import com.google.ar.sceneform.math.Quaternion;
import com.google.ar.sceneform.math.Vector3;
import com.google.ar.sceneform.rendering.ModelRenderable;
import com.google.ar.sceneform.ux.ArFragment;
import com.google.ar.sceneform.ux.TransformableNode;
import com.google.firebase.FirebaseApp;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.File;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    private ArFragment arFragment;
    Button clearArView;
    private static final String GLTF_ASSET =
            "https://github.com/KhronosGroup/glTF-Sample-Models/raw/master/2.0/Duck/glTF/Duck.gltf";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Access a Cloud Firestore instance from your Activity

        FirebaseFirestore db = FirebaseFirestore.getInstance();

        arFragment = (ArFragment) getSupportFragmentManager().findFragmentById(R.id.arFragment);
        DocumentReference docRef = db.collection("Model_Information").document("6xfT3Ve9QruwkaqUSrVz");


        FirebaseApp.initializeApp(this);

        FirebaseStorage storage = FirebaseStorage.getInstance();
        StorageReference modelRef = storage.getReference().child("Model_Files/Earth.glb");

        try {
            File file = File.createTempFile("Earth", "glb");
            modelRef.getFile(file).addOnSuccessListener(taskSnapshot -> buildModel(file));
        } catch (IOException e) {
            e.printStackTrace();
        }

        assert arFragment != null;
        arFragment.setOnTapArPlaneListener((hitResult, plane, motionEvent) -> {

            Anchor anchor  = hitResult.createAnchor();
            addModelToScene(anchor, renderable);
//            AnchorNode anchorNode  = new AnchorNode(hitResult.createAnchor());
//            anchorNode.setRenderable(renderable);
//            arFragment.getArSceneView().getScene().addChild(anchorNode);

//            ModelRenderable.builder()
//                    .setSource(this, RenderableSource.builder().setSource(
//                            this,
////                            Uri.parse(GLTF_ASSET),
//                            Uri.parse("nasa.glb"),
//                            RenderableSource.SourceType.GLB)
//                            .setScale(0.001f)  // Scale the original model to 50%.
//                            .setRecenterMode(RenderableSource.RecenterMode.ROOT)
//                            .build())
////                    .setRegistryId(GLTF_ASSET)
//                    .setRegistryId("nasa.glb")
//                    .build()
//                    .thenAccept(modelRenderable -> addModelToScene(anchor, modelRenderable))
//                    .exceptionally(
//                            throwable -> {
//                                Toast toast =
//                                        Toast.makeText(this, "Unable to load renderable " +
//                                                GLTF_ASSET, Toast.LENGTH_LONG);
//                                toast.setGravity(Gravity.CENTER, 0, 0);
//                                toast.show();
//                                return null;
//                            });

        });

        clearArView = findViewById(R.id.clear_ar_viewButton);
        clearArView.setOnClickListener(view -> {
            Intent i = new Intent(this, MainActivity.class);
            i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
            this.startActivity(i);
        });

    }

    private ModelRenderable renderable;


    private void buildModel(File file) {
        RenderableSource renderableSource = RenderableSource
                .builder()
                .setSource(this, Uri.parse(file.getPath()), RenderableSource.SourceType.GLB)
                .setScale(0.001f)  // Scale the original model to 50%.
                .setRecenterMode(RenderableSource.RecenterMode.ROOT)
                .build();

        ModelRenderable
                .builder()
                .setSource(this, renderableSource)
//                        RenderableSource.SourceType.GLB)
//                        .setScale(0.001f)  // Scale the original model to 50%.
//                        .setRecenterMode(RenderableSource.RecenterMode.ROOT)
//                        .build())
////                    .setRegistryId(GLTF_ASSET)
                .setRegistryId(file.getPath())
                .build()
                .thenAccept(modelRenderable -> {
                    Toast.makeText(this, "Model Built", Toast.LENGTH_SHORT).show();
                    renderable = modelRenderable;
//                    addModelToScene(anchor, modelRenderable);
                });
//                .exceptionally(
//                        throwable -> {
//                            Toast toast =
//                                    Toast.makeText(this, "Unable to load renderable " +
//                                            GLTF_ASSET, Toast.LENGTH_LONG);
//                            toast.setGravity(Gravity.CENTER, 0, 0);
//                            toast.show();
//                            return null;
//                        });
    }

    private void addModelToScene(Anchor anchor, ModelRenderable modelRenderable) {
        AnchorNode anchorNode = new AnchorNode(anchor);
        TransformableNode transformableNode = new TransformableNode(arFragment.getTransformationSystem());
        transformableNode.setLocalRotation(Quaternion.axisAngle(new Vector3(0.0f, 270.0f, 0.0f), 180));
        transformableNode.setParent(anchorNode);
        transformableNode.setRenderable(modelRenderable);
        arFragment.getArSceneView().getScene().addChild(anchorNode);
        transformableNode.select();

    }
}