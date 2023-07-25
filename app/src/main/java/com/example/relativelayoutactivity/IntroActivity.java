package com.example.relativelayoutactivity;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.theme.MaterialComponentsViewInflater;

import io.github.dreierf.materialintroscreen.MaterialIntroActivity;
import io.github.dreierf.materialintroscreen.SlideFragmentBuilder;


public class IntroActivity extends MaterialIntroActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addSlide(new SlideFragmentBuilder()
                .title("Title 1")
                .image(R.drawable.first_illus)
                .buttonsColor(R.color.red2)
                .backgroundColor(R.color.red1)
                .build());
        addSlide(new SlideFragmentBuilder()
                .title("Title 2")
                .image(R.drawable.second_illus)
                .buttonsColor(R.color.red2)
                .backgroundColor(R.color.red1)
                .build());
        addSlide(new SlideFragmentBuilder()
                .title("Title 3")
                .image(R.drawable.third_illus)
                .buttonsColor(R.color.red2)
                .backgroundColor(R.color.red1)
                .build());
    }
}
