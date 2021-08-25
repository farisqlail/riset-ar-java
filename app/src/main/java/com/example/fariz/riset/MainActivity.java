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
import com.google.ar.sceneform.rendering.Color;
import com.google.ar.sceneform.rendering.ModelRenderable;
import com.google.ar.sceneform.ux.ArFragment;
import com.google.ar.sceneform.ux.TransformableNode;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();
    private ModelRenderable cowRenderable, dogRenderable;

    private ArFragment arFragment;
    private ModelRenderable modelRenderable;
    private ImageView cow, dog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        arFragment = (ArFragment) getSupportFragmentManager().findFragmentById(R.id.ux_fragment);
        dog = (ImageView)findViewById(R.id.dog);
        cow = (ImageView)findViewById(R.id.cow);

        if (cow.hasOnClickListeners()){
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

        } else if (dog.hasOnClickListeners()){
            ModelRenderable.builder()
                    .setSource(this, Uri.parse("dog.sfb"))
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
        }



    }
}
