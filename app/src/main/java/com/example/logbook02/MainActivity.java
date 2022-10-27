package com.example.logbook02;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ImageView imageView;
    ArrayList<String> urlList;
    Button aURL;
    Button back_btn;
    Button forward_btn;
    EditText inputImageURl;
    int currentImage = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        urlList = new ArrayList<String>();
        urlList.add("https://anhdephd.vn/wp-content/uploads/2022/07/hinh-anh-phuong-hoang-ve.jpg");
        urlList.add("https://i.pinimg.com/originals/e2/bd/5e/e2bd5ed3af0da59998f6b0d6235cb74b.png");
        urlList.add("https://wallpaperaccess.com/full/3921527.png");
        urlList.add("https://i.pinimg.com/736x/88/16/e1/8816e1366247324ede1fcc3cbc0bb18f.jpg");

//        countImageItem = urlList.size();
//        Log.i("count", String.valueOf(countImageItem));

        inputImageURl = findViewById(R.id.eUrl);
        imageView = findViewById(R.id.image_View);
        back_btn = findViewById(R.id.previous);
        forward_btn = findViewById(R.id.next);

        back_btn.setOnClickListener(this::renderImageWhenOnclick);
        forward_btn.setOnClickListener(this::renderImageWhenOnclick);

        aURL = findViewById(R.id.aURL);

        aURL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String imageURl = inputImageURl.getText().toString();
                // add arrylist
                if (imageURl.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Please enter Image URL !!!", Toast.LENGTH_SHORT).show();
                } else {
//                    Log.i("Data", imageURl);
                    urlList.add(imageURl);
//                    Log.i("list", String.valueOf(urlList));
                    inputImageURl.setText("");
                    Toast.makeText(getApplicationContext(), "Successful", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void renderImageWhenOnclick(View view) {
        if (view == forward_btn) {
            currentImage++;
            if (currentImage == urlList.size()) {
                currentImage = 0;
            }
        } else {
            if (currentImage == 0) {
                currentImage = urlList.size();
            }
            currentImage--;
        }
        loadImage(currentImage);
    }

    private void loadImage(int item) {
        Glide.with(MainActivity.this)
                .load(urlList.get(item))
                .into(imageView);
    }
}