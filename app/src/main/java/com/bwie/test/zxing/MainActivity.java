package com.bwie.test.zxing;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.xys.libzxing.zxing.activity.CaptureActivity;
import com.xys.libzxing.zxing.encoding.EncodingUtils;

public class MainActivity extends AppCompatActivity {
    private TextView mTv_Ruslet;
    private EditText mInput;
    private ImageView mImg;
    private CheckBox isLogo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mTv_Ruslet = (TextView) findViewById(R.id.tv_ruselt);
        mInput = (EditText) findViewById(R.id.et_text);
        mImg = (ImageView) findViewById(R.id.img);
        isLogo = (CheckBox) findViewById(R.id.is_logo);
    }
    //生成二维码
    public void make(View view){
        String input=mInput.getText().toString().trim();
        //生成二维码，然后为二维码增加logo
        Bitmap bitmap= EncodingUtils.createQRCode(input,500,500,isLogo.isChecked() ? BitmapFactory.decodeResource(getResources(),  R.mipmap.ic_launcher) : null
        );
        mImg.setImageBitmap(bitmap);
    }
    //扫描二维码
    public void scan(View view){
        startActivityForResult(new Intent(MainActivity.this, CaptureActivity.class), 0);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode==RESULT_OK){
            Bundle bundle=data.getExtras();
            String result=bundle.getString("result");
            mTv_Ruslet.setText(result);
            mInput.setText(result);
        }else if(resultCode==RESULT_CANCELED){
            mTv_Ruslet.setText("扫描出错");
        }
    }
}
