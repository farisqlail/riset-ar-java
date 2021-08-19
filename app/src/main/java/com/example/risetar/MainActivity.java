package com.example.risetar;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.google.ar.sceneform.rendering.ModelRenderable;
import com.google.ar.sceneform.rendering.ViewRenderable;
import com.google.ar.sceneform.ux.ArFragment;

public class MainActivity extends AppCompatActivity {

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

    }
}