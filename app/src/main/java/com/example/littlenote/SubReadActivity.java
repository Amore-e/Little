package com.example.littlenote;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.littlenote.uc.Book;
import com.example.littlenote.uc.HttpCallbackListener;
import com.example.littlenote.uc.HttpUtil;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.gson.Gson;

public class SubReadActivity extends AppCompatActivity {

    public static final String BOOK_NAME="book_name";

    public static final String BOOK_IMAGE_ID="book_image_id";

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub_read);

        Intent intent=getIntent();

        String bookName=intent.getStringExtra(BOOK_NAME);
        int bookImageId=intent.getIntExtra(BOOK_IMAGE_ID,0);

        Toolbar toolbar=findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        CollapsingToolbarLayout collapsingToolbar=(CollapsingToolbarLayout)findViewById(R.id.CT_layout);
        ImageView bookImageView=(ImageView)findViewById(R.id.book_picture);

        ActionBar actionBar=getSupportActionBar();
        if(actionBar !=null){
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        collapsingToolbar.setTitle(bookName);
        Glide.with(this).load(bookImageId).into(bookImageView);//加载书的图片

        WebView webView=(WebView)findViewById(R.id.web_view);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setWebViewClient(new WebViewClient());
        switch (bookImageId){
            case R.drawable.l: //简爱
                webView.loadUrl("http://www.newxue.com/gkmz/jianai/");
                break;
            case R.drawable.b:
                webView.loadUrl("http://www.newxue.com/mingzhu/haidiliangwanli/");
                break;
            case R.drawable.c:
                webView.loadUrl("http://www.newxue.com/jiarugeiwosantianguangming/");
                break;
            case R.drawable.d:
                webView.loadUrl("http://www.newxue.com/jinghuayuan/");
                break;
            case R.drawable.e:
                webView.loadUrl("http://www.newxue.com/liaozhaizhiyi/");
                break;
            case R.drawable.f:
                webView.loadUrl("http://www.newxue.com/mingzhu/xiaowangzi/");
                break;
            case R.drawable.g:
                webView.loadUrl("http://www.newxue.com/waerdenghu/");
                break;
            case R.drawable.h:
                webView.loadUrl("http://www.newxue.com/gkmz/taiger/");
                break;
            case R.drawable.i:
                webView.loadUrl("http://www.newxue.com/gkmz/sgyy/");
                break;
            case R.drawable.j:
                webView.loadUrl("http://www.newxue.com/mingzhu/qiqiushangdewuxingqi/");
                break;
            case R.drawable.k:
                webView.loadUrl("http://www.newxue.com/lvyexianzong/");
                break;
            case R.drawable.a:
                webView.loadUrl("http://www.newxue.com/gkmz/ouyeni/");
                break;
            case R.drawable.m:
                webView.loadUrl("http://www.newxue.com/mingzhu/fengraozhihai/");
                break;
            case R.drawable.n:
                webView.loadUrl("http://www.newxue.com/beiyannanfei/");
                break;
        }

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()){
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
