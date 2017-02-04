package com.thinkternet.uc2k17admin;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;

import org.w3c.dom.Text;

import jp.wasabeef.blurry.Blurry;

/**
 * Created by diksha on 4/2/17.
 */

public class RandomActivity extends AppCompatActivity {

    private TextView textView;
    private FrameLayout fLayout;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.custom_toast);

        textView = (TextView)findViewById(R.id.sin);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "HI", Toast.LENGTH_SHORT).show();
            }
        });

        fLayout = (FrameLayout)findViewById(R.id.f_layout);
        Glide.with(this).load(R.drawable.bckgnd).asBitmap().into(new SimpleTarget<Bitmap>() {
            @Override
            public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
                Drawable drawable = new BitmapDrawable(resource);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                    fLayout.setBackground(drawable);
                }
            }
        });
        Blurry.with(RandomActivity.this)
                .radius(10)
                .sampling(8)
                .color(Color.argb(66, 255, 255, 0))
                .async()
                .animate(500)
                .onto(fLayout);

    }


}
