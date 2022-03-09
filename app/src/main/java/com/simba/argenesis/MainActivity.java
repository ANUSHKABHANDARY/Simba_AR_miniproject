package com.simba.argenesis;

import androidx.appcompat.app.AppCompatActivity;

import android.content.res.Configuration;
import android.net.Uri;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.Size;
import android.view.Gravity;
import android.widget.Toast;

import com.google.ar.core.Anchor;
import com.google.ar.core.CameraConfig;
import com.google.ar.core.CameraConfigFilter;
import com.google.ar.core.Config;
import com.google.ar.core.Session;
import com.google.ar.core.exceptions.UnavailableApkTooOldException;
import com.google.ar.core.exceptions.UnavailableArcoreNotInstalledException;
import com.google.ar.core.exceptions.UnavailableDeviceNotCompatibleException;
import com.google.ar.core.exceptions.UnavailableSdkTooOldException;
import com.google.ar.sceneform.AnchorNode;
import com.google.ar.sceneform.assets.RenderableSource;
import com.google.ar.sceneform.math.Quaternion;
import com.google.ar.sceneform.math.Vector3;
import com.google.ar.sceneform.rendering.ModelRenderable;
import com.google.ar.sceneform.ux.ArFragment;
import com.google.ar.sceneform.ux.TransformableNode;

import java.util.EnumSet;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ArFragment arFragment;
    private static final String GLTF_ASSET =
            "https://github.com/KhronosGroup/glTF-Sample-Models/raw/master/2.0/Duck/glTF/Duck.gltf";

//    @Override
//    public void onConfigurationChanged(Configuration newConfig) {
//        super.onConfigurationChanged(newConfig);
//
//        // In some cases modifying newConfig leads to unexpected behavior,
//        // so it's better to edit new instance.
//        Configuration configuration = new Configuration(newConfig);
//        SystemUtils.adjustFontScale(getApplicationContext(), configuration);
//    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        Configuration configuration = getResources().getConfiguration();
//        configuration.fontScale = (float) 1; //0.85 small size, 1 normal size, 1,15 big etc
//        DisplayMetrics metrics = new DisplayMetrics();
//        getWindowManager().getDefaultDisplay().getMetrics(metrics);
//        metrics.scaledDensity = configuration.fontScale * metrics.density;
//        configuration.densityDpi = (int) getResources().getDisplayMetrics().xdpi;
//        getBaseContext().getResources().updateConfiguration(configuration, metrics);

        setContentView(R.layout.activity_main);
        arFragment = (ArFragment) getSupportFragmentManager().findFragmentById(R.id.arFragment);

//        Session session = null;
//        try {
//            session = new Session(this);
//        } catch (UnavailableArcoreNotInstalledException | UnavailableApkTooOldException | UnavailableSdkTooOldException | UnavailableDeviceNotCompatibleException e) {
//            e.printStackTrace();
//        }
//
//// ...
//
//        Size selectedSize = new Size(0, 0);
//        CameraConfig selectedCameraConfig = null;
//
//        //assert session != null;
//        int flag = 0;
//        CameraConfigFilter filter = new CameraConfigFilter(session);
//        List<CameraConfig> cameraConfigsList = session.getSupportedCameraConfigs(filter);
//        for (CameraConfig currentCameraConfig : cameraConfigsList) {
//            Size cpuImageSize = currentCameraConfig.getImageSize();
//            Size gpuTextureSize = currentCameraConfig.getTextureSize();
//            Log.d("TAG", "CurrentCameraConfig CPU image size:" + cpuImageSize + " GPU texture size:" + gpuTextureSize);
//
//            // Adapt this check to your needs
//            if (gpuTextureSize.getWidth() > selectedSize.getWidth()) {
//                if (flag == 0){
//                    flag++;
//                    selectedSize = gpuTextureSize;
//                    selectedCameraConfig = currentCameraConfig;
//                }
//
//            }
//        }
//
//        Log.d("TAG", "Selected CameraConfig CPU image size:" + selectedCameraConfig.getImageSize() + " GPU texture size:" + selectedCameraConfig.getTextureSize());
//        session.setCameraConfig(selectedCameraConfig);

// ...

// Don't forget to configure the session afterwards
        //session.configure(config);

//        // Create a camera config filter for the session.
//        Session session = null;
//        try {
//            session = new Session(arFragment.requireActivity());
//        } catch (UnavailableArcoreNotInstalledException | UnavailableApkTooOldException | UnavailableSdkTooOldException | UnavailableDeviceNotCompatibleException e) {
//            e.printStackTrace();
//        }
//        assert session != null;
//        CameraConfigFilter filter = new CameraConfigFilter(session);
//
//// Return only camera configs that target 30 fps camera capture frame rate.
//        filter.setTargetFps(EnumSet.of(CameraConfig.TargetFps.TARGET_FPS_30));
//
//// Return only camera configs that will not use the depth sensor.
//        filter.setDepthSensorUsage(EnumSet.of(CameraConfig.DepthSensorUsage.DO_NOT_USE));
//
//// Get list of configs that match filter settings.
//// In this case, this list is guaranteed to contain at least one element,
//// because both TargetFps.TARGET_FPS_30 and DepthSensorUsage.DO_NOT_USE
//// are supported on all ARCore supported devices.
//        List<CameraConfig> cameraConfigList = session.getSupportedCameraConfigs(filter);
//
//// Use element 0 from the list of returned camera configs. This is because
//// it contains the camera config that best matches the specified filter
//// settings.
//        session.setCameraConfig(cameraConfigList.get(0));

        assert arFragment != null;
        arFragment.setOnTapArPlaneListener((hitResult, plane, motionEvent) -> {
            Anchor anchor  = hitResult.createAnchor();

            ModelRenderable.builder()
                    .setSource(this, RenderableSource.builder().setSource(
                            this,
//                            Uri.parse(GLTF_ASSET),
                            Uri.parse("nasa.glb"),
                            RenderableSource.SourceType.GLB)
                            .setScale(0.001f)  // Scale the original model to 50%.
                            .setRecenterMode(RenderableSource.RecenterMode.ROOT)
                            .build())
//                    .setRegistryId(GLTF_ASSET)
                    .setRegistryId("nasa.glb")
                    .build()
                    .thenAccept(modelRenderable -> addModelToScene(anchor, modelRenderable))
                    .exceptionally(
                            throwable -> {
                                Toast toast =
                                        Toast.makeText(this, "Unable to load renderable " +
                                                GLTF_ASSET, Toast.LENGTH_LONG);
                                toast.setGravity(Gravity.CENTER, 0, 0);
                                toast.show();
                                return null;
                            });

        });

    }

    private void addModelToScene(Anchor anchor, ModelRenderable modelRenderable) {
        AnchorNode anchorNode = new AnchorNode(anchor);
        TransformableNode transformableNode = new TransformableNode(arFragment.getTransformationSystem());
        transformableNode.setLocalRotation(Quaternion.axisAngle(new Vector3(0.0f, 0.0f, 0.0f), 180));
        transformableNode.setParent(anchorNode);
        transformableNode.setRenderable(modelRenderable);
        arFragment.getArSceneView().getScene().addChild(anchorNode);
        transformableNode.select();

    }
}