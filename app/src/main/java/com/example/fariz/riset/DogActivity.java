package com.example.fariz.riset;

import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.google.ar.core.Anchor;
import com.google.ar.sceneform.AnchorNode;
import com.google.ar.sceneform.rendering.ModelRenderable;
import com.google.ar.sceneform.ux.ArFragment;
import com.google.ar.sceneform.ux.TransformableNode;

public class DogActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();
    private ModelRenderable cowRenderable, dogRenderable;

    private ArFragment arFragment;
    private ModelRenderable modelRenderable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dog);

        arFragment = (ArFragment) getSupportFragmentManager().findFragmentById(R.id.ux_fragment);

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
