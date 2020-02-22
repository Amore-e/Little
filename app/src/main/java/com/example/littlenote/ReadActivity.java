package com.example.littlenote;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.os.Bundle;

import com.example.littlenote.uc.Book;
import com.example.littlenote.uc.BookAdapter;

import java.util.ArrayList;
import java.util.List;

public class ReadActivity extends AppCompatActivity {

    private List<Book> bookList=new ArrayList<>();
    private SwipeRefreshLayout swipeRefreshLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read);
        initBooks();
        RecyclerView recyclerView=(RecyclerView)findViewById(R.id.recycle_view);
        LinearLayoutManager layoutManager=new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        final BookAdapter adapter=new BookAdapter(bookList);
        recyclerView.setAdapter(adapter);

        swipeRefreshLayout=(SwipeRefreshLayout)findViewById(R.id.swipe_refresh);
        swipeRefreshLayout.setColorSchemeResources(R.color.colorPrimary);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {//网上请求最新数据
                refreshBooks();
            }

            private void refreshBooks() {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try{
                            Thread.sleep(3000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                initBooks();
                                adapter.notifyDataSetChanged();
                                swipeRefreshLayout.setRefreshing(false);
                            }
                        });
                    }
                }).start();
            }
        });
    }

    private void initBooks(){
        for(int i=0;i<2;i++){
            Book ll=new Book("简.爱","夏洛蒂.勃朗特",R.drawable.l);
            bookList.add(ll);
            Book bb=new Book("海底两万里","凡尔纳",R.drawable.b);
            bookList.add(bb);
            Book cc=new Book("假如给我三天光明","海伦.凯勒",R.drawable.c);
            bookList.add(cc);
            Book dd=new Book("镜花缘","李汝珍",R.drawable.d);
            bookList.add(dd);
            Book ee=new Book("聊斋志异","蒲松龄",R.drawable.e);
            bookList.add(ee);
            Book ff=new Book("小王子","圣埃尔克絮佩里",R.drawable.f);
            bookList.add(ff);
            Book gg=new Book("瓦尔登湖","梭罗",R.drawable.g);
            bookList.add(gg);
            Book hh=new Book("泰戈尔诗选","泰戈尔",R.drawable.h);
            bookList.add(hh);
            Book ii=new Book("三国演义","罗贯中",R.drawable.i);
            bookList.add(ii);
            Book jj=new Book("气球上的五星期","儒勒.凡尔纳",R.drawable.j);
            bookList.add(jj);
            Book kk=new Book("绿野仙踪","莱曼.弗兰克.鲍姆",R.drawable.k);
            bookList.add(kk);
            Book mm=new Book("丰饶之海","三岛由纪夫",R.drawable.m);
            bookList.add(mm);
            Book nn=new Book("北雁南飞","张恨水",R.drawable.n);
            bookList.add(nn);
            Book aa=new Book("欧也妮.葛朗台","巴尔扎克",R.drawable.a);
            bookList.add(aa);
        }
    }
}
