package com.example.littlenote;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;

import android.Manifest;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.ContentUris;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.location.Location;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.view.Menu;
import android.view.MenuItem;

import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.baidu.location.BDAbstractLocationListener;
import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class WriteActivity extends AppCompatActivity {

    public static final int TAKE_PHOTO=1;
    public static final int CHOOSE_PHOTO=2;
    private ImageView image_add1;
    private Uri imageUri;
    public LocationClient mLocationClient;
    private TextView text2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mLocationClient=new LocationClient(getApplicationContext());
        mLocationClient.registerLocationListener(new MyLocationListener());

        setContentView(R.layout.activity_write);

        Toolbar toolbar=findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar=getSupportActionBar();
        if(actionBar !=null){
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeAsUpIndicator(R.drawable.back_foreground);
        }

        TextView text1=(TextView)findViewById(R.id.text1);
        TextView text2=(TextView)findViewById(R.id.text2);
        image_add1=(ImageView)findViewById(R.id.image_add1);
        ImageButton imageButton_1=(ImageButton)findViewById(R.id.image_button1);
        ImageButton imageButton_2=(ImageButton)findViewById(R.id.image_button2);
        ImageButton imageButton_3=(ImageButton)findViewById(R.id.image_button3);
//显示当前时间
        imageButton_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //打印当前时间
                TextView text1=(TextView)findViewById(R.id.text1);
                Date data=new Date();
                SimpleDateFormat ft = new SimpleDateFormat ("yyyy-MM-dd hh:mm:ss");
                text1.setText(ft.format(data));
            }
        });

//添加照片
        imageButton_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder dialog=new AlertDialog.Builder(WriteActivity.this);
                dialog.setTitle("添加照片");
                dialog.setCancelable(false);
                dialog.setPositiveButton("拍照", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                dialog.setNegativeButton("拍照", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //创建File对象，用于存储拍照后的照片
                        File outputImage=new File (getExternalCacheDir(),"output_image.jpg");
                        try {
                            if (outputImage.exists()) {
                                outputImage.delete();
                            }
                            outputImage.createNewFile();
                        }catch(IOException e){
                            e.printStackTrace();
                        }
                        if(Build.VERSION.SDK_INT>=24){
                            imageUri= FileProvider.getUriForFile(WriteActivity.this,"com.example.littlenote.fileprovider",outputImage);
                        }else{
                            imageUri=Uri.fromFile(outputImage);
                        }
                        //启动相机程序
                        try {
                            Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
                            intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
                            startActivityForResult(intent, TAKE_PHOTO);
                        }catch(Exception e){
                            e.printStackTrace();
                        }
                    }
                });
                //选择 相册 添加照片
                dialog.setPositiveButton("相册", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                       if(ContextCompat.checkSelfPermission(WriteActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE)!= PackageManager.PERMISSION_GRANTED){
                           ActivityCompat.requestPermissions(WriteActivity.this, new String []{Manifest.permission.WRITE_EXTERNAL_STORAGE},1);
                       }else{
                           openAlbum();
                       }
                    }
                });
                dialog.show();
            }
        });

        //显示位置的点击事件
        imageButton_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<String> permissionList=new ArrayList<>();
                if(ContextCompat.checkSelfPermission(WriteActivity.this,Manifest.permission.ACCESS_FINE_LOCATION)!=PackageManager.PERMISSION_GRANTED){
                    permissionList.add(Manifest.permission.ACCESS_FINE_LOCATION);
                }
                if(ContextCompat.checkSelfPermission(WriteActivity.this,Manifest.permission.READ_PHONE_STATE)!=PackageManager.PERMISSION_GRANTED){
                    permissionList.add(Manifest.permission.READ_PHONE_STATE);
                }
                if(ContextCompat.checkSelfPermission(WriteActivity.this,Manifest.permission.WRITE_EXTERNAL_STORAGE)!=PackageManager.PERMISSION_GRANTED){
                    permissionList.add(Manifest.permission.WRITE_EXTERNAL_STORAGE);
                }
                if(!permissionList.isEmpty()){
                    String []permissions=permissionList.toArray(new String[permissionList.size()]);
                    ActivityCompat.requestPermissions(WriteActivity.this,permissions,1);
                }else {
                    requestLocation();
                }
            }
        });

    }


    //打开相册
    public void openAlbum() {
        try{
        Intent intent = new Intent("android.intent.action.GET_CONTENT");
        intent.setType("image/*");
        startActivityForResult(intent, CHOOSE_PHOTO);//打开相册
    }catch(Exception e){
        e.printStackTrace();
    }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode,  Intent data) {
        switch(requestCode){
            case TAKE_PHOTO:
                try{
                    //将拍摄照片显示出来
                    Bitmap bitmap= BitmapFactory.decodeStream(getContentResolver().openInputStream(imageUri));
                    image_add1.setImageBitmap(bitmap);
                }catch(FileNotFoundException e){
                    e.printStackTrace();
                }
                break;
            case CHOOSE_PHOTO:
                if(resultCode==RESULT_OK){
                    //判断手机系统版本号
                    if(Build.VERSION.SDK_INT>=19){
                        //4.4及以上系统用这个方法处理图片
                        handleImageOnKitKat(data);
                    } else {
                        //4.4以下系统使用这个方法
                        handleImageBeforeKitKat(data);
                    }
                }
                break;
            default:
                break;
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    //4.4以下的版本用这个方法处理
    private void handleImageBeforeKitKat(Intent data) {
        Uri uri=data.getData();
        String imagePath=getImagePath(uri,null);
        displayImage(imagePath);//根据图片路径显示图片
    }

    //4.4及以上系统用这个方法处理图片
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    private void handleImageOnKitKat(Intent data) {
        String imagePath=null;
        Uri uri=data.getData();
        if(DocumentsContract.isDocumentUri(this,uri)){
            //如果是document类型的Uri，则通过document id 处理
            String docId= DocumentsContract.getDocumentId(uri);
            if ("com.android.provider.media.documents".equals(uri.getAuthority())) {
                String id=docId.split(":")[1];//解析出数字格式的id
                String selection=MediaStore.Images.Media._ID+"="+id;
                imagePath=getImagePath(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,selection);
            } else if ("com.android.provider.downloads.documents".equals(uri.getAuthority())) {
                Uri contentUri= ContentUris.withAppendedId(Uri.parse("content://downloads/pulic_downloads"),Long.valueOf(docId));
                imagePath=getImagePath(contentUri,null);
            }else if("content".equalsIgnoreCase(uri.getScheme())){
                //如果是content类型的URI，则用普通方式进行处理
                imagePath=getImagePath(uri,null);
            }else if("file".equalsIgnoreCase(uri.getScheme())){
                //如果是file类型的URI。直接获取图片路径即可
                imagePath=uri.getPath();
            }
            displayImage(imagePath);//根据图片路径显示图片
            }
    }

//显示图片
    private void displayImage(String imagePath) {
        if(imagePath!=null){
            Bitmap bitmap=BitmapFactory.decodeFile(imagePath);
            image_add1.setImageBitmap(bitmap);
        }else{
            Toast.makeText(this,"图片加载失败",Toast.LENGTH_SHORT).show();
        }
    }

    //获取图片
    private String getImagePath(Uri uri, String selection) {
        String path=null;
        //通过URI和selection来获取真实的图片路径
        Cursor cursor=getContentResolver().query(uri,null,selection,null,null);
        if(cursor!=null){
            if(cursor.moveToFirst()){
                path=cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DATA));
            }
            cursor.close();
        }
        return path;
    }


//toolbar导航按钮的点击事件
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.write_toobar,menu);
        return true;
    }

    //toolbar菜单栏的点击事件
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()){
            case android.R.id.home:
                finish();
                break;
            case R.id.cun:
                Toast.makeText(this,"保存成功！",Toast.LENGTH_SHORT).show();
                break;
            case R.id.share:
                ProgressDialog progressDialog = new ProgressDialog(WriteActivity.this);
                progressDialog.setMessage("Loading...");
                progressDialog.setTitle("分享");
                progressDialog.show();
                break;
            default:
                break;
        }
        return true;
    }

//显示位置
    private class MyLocationListener  implements BDLocationListener {
        @Override
        public void onReceiveLocation(BDLocation Location) {
            try {
                StringBuilder currentPosition = new StringBuilder();
                currentPosition.append(Location.getCountry()).append(" ").append(Location.getProvince()).append(" ").append(Location.getCity()).append(" ").append(Location.getDistrict()).append(" ").append(Location.getStreet()).append(" ");
                text2.setText(currentPosition);
            }catch(NullPointerException e){
                e.printStackTrace();
            }

        }
    }

//请求位置
    private void requestLocation() {
        //更新当前位置
        initLocation();
        mLocationClient.start();
    }

    private void initLocation() {
        LocationClientOption option =new LocationClientOption();
       option.setLocationMode(LocationClientOption.LocationMode.Device_Sensors);//传感器模式，使用GBS定位
        option.setIsNeedAddress(true);//获取当前位置详细信息
        mLocationClient.setLocOption(option);
    }
//停止定位
    @Override
    protected void onDestroy() {
        super.onDestroy();
        mLocationClient.stop();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case 1:
                if (grantResults.length > 0) {
                    for (int result : grantResults) {
                        if (result != PackageManager.PERMISSION_GRANTED) {
                            Toast.makeText(this, "必须同意所有权限才能使用本程序", Toast.LENGTH_SHORT).show();
                            finish();
                            return;
                        }
                    }
                    requestLocation();
                } else {
                    Toast.makeText(this, "发生位置错误", Toast.LENGTH_SHORT).show();
                    finish();
                }
                break;
            default:
        }
    }


}
