package com.example.fariz.riset;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.ar.core.Anchor;
import com.google.ar.sceneform.AnchorNode;
import com.google.ar.sceneform.assets.RenderableSource;
import com.google.ar.sceneform.rendering.Color;
import com.google.ar.sceneform.rendering.ModelRenderable;
import com.google.ar.sceneform.rendering.Renderable;
import com.google.ar.sceneform.ux.ArFragment;
import com.google.ar.sceneform.ux.TransformableNode;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();

    private ArFragment arFragment;
    private ModelRenderable modelRenderable;
    private String MODEL_URL="https://github.com/farisqlail/assets-ar/blob/master/cow.fbx";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        arFragment = (ArFragment) getSupportFragmentManager().findFragmentById(R.id.ux_fragment);

        //    Form Asset By Directory
        ModelRenderable.builder()
                .setSource(this, Uri.parse("cow.sfb"))
                .build()
                .thenAccept(renderable -> modelRenderable = renderable)
                .exceptionally(
                        throwable -> {
                            Log.e(TAG, "Unable to load Renderable.", throwable);
                            return null;
                        });

        arFragment.setOnTapArPlaneListener((hitResult, plane, motionEvent) -> {

            if (modelRenderable == null) {
                return;
            }

            ModelRenderable renderable = modelRenderable.makeCopy();

            Anchor anchor = hitResult.createAnchor();
            AnchorNode anchorNode = new AnchorNode(anchor);
            anchorNode.setParent(arFragment.getArSceneView().getScene());

            TransformableNode andy = new TransformableNode(arFragment.getTransformationSystem());
            andy.setParent(anchorNode);
            andy.setRenderable(renderable);
            andy.select();

        });
//        setUpModel();
//        setUpPlane();

    }

//    private void setUpModel() {
//        ModelRenderable.builder()
//                .setSource(this, RenderableSource.builder().setSource(
//                        this,
//                        Uri.parse(MODEL_URL),
//                        RenderableSource.SourceType.GLB)
//                        .setScale(0.75f)
//                        .setRecenterMode(RenderableSource.RecenterMode.ROOT)
//                        .build())
//                .setRegistryId(MODEL_URL)
//                .build()
//                .thenAccept(renderable -> modelRenderable = renderable)
//                .exceptionally(throwable -> {
//                    Toast.makeText(MainActivity.this, "Gak bisa reload assets", Toast.LENGTH_SHORT).show();
//                    return null;
//                });
//
//    }
//
//    private void setUpPlane() {
//        arFragment.setOnTapArPlaneListener((((hitResult, plane, motionEvent) -> {
//            Anchor anchor = hitResult.createAnchor();
//            AnchorNode anchorNode = new AnchorNode(anchor);
//            anchorNode.setParent(arFragment.getArSceneView().getScene());
//            createModel(anchorNode);
//        })));
//    }
//
//    private void createModel(AnchorNode anchorNode) {
//        TransformableNode node = new TransformableNode(arFragment.getTransformationSystem());
//        node.setParent(anchorNode);
//        node.setRenderable(modelRenderable);
//        node.select();
//    }



}

