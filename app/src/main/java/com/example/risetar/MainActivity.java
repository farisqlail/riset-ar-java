package com.example.risetar;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.TransactionTooLargeException;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.ar.core.Anchor;
import com.google.ar.core.HitResult;
import com.google.ar.core.Plane;
import com.google.ar.sceneform.AnchorNode;
import com.google.ar.sceneform.rendering.ModelRenderable;
import com.google.ar.sceneform.rendering.ViewRenderable;
import com.google.ar.sceneform.ux.ArFragment;
import com.google.ar.sceneform.ux.BaseArFragment;
import com.google.ar.sceneform.ux.TransformableNode;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    ArFragment arFragment;
    private ModelRenderable cowRenderable, dogRenderable;

    ImageView cow, dog;
    View arrayView[];
    ViewRenderable name_animal;

    int selected = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        arFragment = (ArFragment)getSupportFragmentManager().findFragmentById(R.id.sceneform_ux_fragment);

        dog = (ImageView)findViewById(R.id.dog);
        cow = (ImageView)findViewById(R.id.cow);

        setArrayView();
        setClickListener();
        setupModel();


        arFragment.setOnTapArPlaneListener(new BaseArFragment.OnTapArPlaneListener() {
            @Override
            public void onTapPlane(HitResult hitResult, Plane plane, MotionEvent motionEvent) {
               if (selected == 1){
                   Anchor anchor = hitResult.createAnchor();
                   AnchorNode anchorNode = new AnchorNode(anchor);
                   anchorNode.setParent(arFragment.getArSceneView().getScene());

                   createModel(anchorNode, selected);
               }
            }
        });

    }

    private void setupModel() {
        ModelRenderable renderable = null;
        ModelRenderable.builder()
                    .setSource(this, R.raw.cow)
                    .build().thenAccept(randerable -> cowRenderable = renderable)
                    .exceptionally(
                            throwable -> {
                                Toast.makeText(this, "unable to load cow model", Toast.LENGTH_SHORT).show();

                                return  null;
                            }
                    );

        ModelRenderable.builder()
                .setSource(this, R.raw.dog)
                .build().thenAccept(randerable -> dogRenderable = renderable)
                .exceptionally(
                        throwable -> {
                            Toast.makeText(this, "unable to load dog model", Toast.LENGTH_SHORT).show();

                            return  null;
                        }
                );
    }

    private void createModel(AnchorNode anchorNode, int selected) {
        if(selected == 1){
            TransformableNode cow = new TransformableNode(arFragment.getTransformationSystem());
            cow.setParent(anchorNode);
            cow.setRenderable(cowRenderable);
            cow.select();
        }
    }

    private void setClickListener() {
        for(int i=0; i < arrayView.length; i++){
            arrayView[i].setOnClickListener(this);
        }
    }

    private void setArrayView() {
        arrayView = new View[] {
                dog, cow
        };
    }

    @Override
    public void onClick(View v) {

    }
}