package com.zhanglin.filterimageview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private FilterImageView mImageView;
    private boolean isRemoveColor = false;
    private boolean isEnableGray = false;

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.fivClick:
                Toast.makeText(MainActivity.this, "click---", Toast.LENGTH_SHORT).show();
                break;
            case R.id.btnRemoveColor:
                isRemoveColor = !isRemoveColor;
                mImageView.setBlackWhite(isRemoveColor);
                break;
            case R.id.btnEnableGray:
                isEnableGray = !isEnableGray;
                mImageView.enableGrayOnClick(isEnableGray);
                break;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mImageView = (FilterImageView) findViewById(R.id.fivClick);
        mImageView.setOnClickListener(this);
        findViewById(R.id.btnEnableGray).setOnClickListener(this);
        findViewById(R.id.btnRemoveColor).setOnClickListener(this);
    }
}
