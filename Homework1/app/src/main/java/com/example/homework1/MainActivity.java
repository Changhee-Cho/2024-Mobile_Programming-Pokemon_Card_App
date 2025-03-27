package com.example.homework1;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    int number = 1;

    ImageView mainImageView;
    EditText editText;
    ImageView leftImageView;
    ImageView rightImageView;
    Button ltButton;
    Button gtButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mainImageView = findViewById(R.id.imageView);
        editText = findViewById(R.id.editTextText);
        leftImageView = findViewById(R.id.imageView2);
        rightImageView = findViewById(R.id.imageView3);

        ltButton = findViewById(R.id.button);
        ltButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                number-=1;
                setView();
            }
        });

        gtButton = findViewById(R.id.button2);
        gtButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                number+=1;
                setView();
            }
        });

        editText.setOnEditorActionListener(new TextView.OnEditorActionListener(){
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                float temp = Float.parseFloat(editText.getText().toString());
                number = (int) Math.round(temp);
                if(number < 1 || number > 20){
                    Toast.makeText(getApplicationContext(), "사진은 1~20까지만 있습니다.",Toast.LENGTH_SHORT).show();
                    if(number < 1){
                        number=1;
                    }
                    else if(number > 20){
                        number=20;
                    }
                }
                setView();
                return true;
            }
        });

        if(savedInstanceState != null){
            number = Integer.parseInt(savedInstanceState.getString("number"));
        }

        setView();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState){
        super.onSaveInstanceState(outState);
        outState.putString("number", String.valueOf(number));
    }

    public void setView(){
        // editText에 number 표시
        editText.setText(String.valueOf(number));
        
        // mainImageView 설정
        int drawableId = getResources().getIdentifier("image"+number, "drawable", getPackageName());
        mainImageView.setImageResource(drawableId);

        // leftImageView와 rightImageView 설정 및 버튼 표시 설정
        if(number==1){
            leftImageView.setVisibility(View.INVISIBLE);
            rightImageView.setVisibility(View.VISIBLE);
            ltButton.setVisibility(View.INVISIBLE);
            gtButton.setVisibility(View.VISIBLE);
            int leftDrawableId = getResources().getIdentifier("image"+(number), "drawable", getPackageName());
            int rightDrawableId = getResources().getIdentifier("image"+(number+1), "drawable", getPackageName());
            leftImageView.setImageResource(leftDrawableId);
            rightImageView.setImageResource(rightDrawableId);
        }
        else if(number==20){
            leftImageView.setVisibility(View.VISIBLE);
            rightImageView.setVisibility(View.INVISIBLE);
            ltButton.setVisibility(View.VISIBLE);
            gtButton.setVisibility(View.INVISIBLE);
            int leftDrawableId = getResources().getIdentifier("image"+(number-1), "drawable", getPackageName());
            int rightDrawableId = getResources().getIdentifier("image"+(number), "drawable", getPackageName());
            leftImageView.setImageResource(leftDrawableId);
            rightImageView.setImageResource(rightDrawableId);
        }
        else{
            leftImageView.setVisibility(View.VISIBLE);
            rightImageView.setVisibility(View.VISIBLE);
            ltButton.setVisibility(View.VISIBLE);
            gtButton.setVisibility(View.VISIBLE);
            int leftDrawableId = getResources().getIdentifier("image"+(number-1), "drawable", getPackageName());
            int rightDrawableId = getResources().getIdentifier("image"+(number+1), "drawable", getPackageName());
            leftImageView.setImageResource(leftDrawableId);
            rightImageView.setImageResource(rightDrawableId);
        }
    }
}