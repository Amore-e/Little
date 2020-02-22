package com.example.littlenote.uc;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.littlenote.R;
import com.example.littlenote.SubReadActivity;

import java.util.List;

import androidx.recyclerview.widget.RecyclerView;

public class BookAdapter extends RecyclerView.Adapter<BookAdapter.ViewHolder> {

    private List<Book> eBookList;
    private Context eContext;

    static class ViewHolder extends RecyclerView.ViewHolder {
        View bookView;
        ImageView bookImage;
        TextView bookName, writerName;

        public ViewHolder(View view) {
            super(view);
            bookView = view;
            bookImage = (ImageView) view.findViewById(R.id.book_image);
            bookName = (TextView) view.findViewById(R.id.book_name);
            writerName = (TextView) view.findViewById(R.id.write_name);
        }
    }

    public BookAdapter(List<Book>bookList){
        eBookList=bookList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if(eContext==null){
            eContext=parent.getContext();
        }
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.list_layout,parent,false);

        final  ViewHolder holder=new ViewHolder(view);
        holder.bookView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position=holder.getAdapterPosition();
                Book book=eBookList.get(position);
                Intent intent=new Intent(eContext, SubReadActivity.class);
                intent.putExtra(SubReadActivity.BOOK_NAME,book.getBook_name());
                intent.putExtra(SubReadActivity.BOOK_IMAGE_ID,book.getImageId());
                eContext.startActivity(intent);
            }
        });
        holder.bookImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position=holder.getAdapterPosition();
                Book book=eBookList.get(position);
                Intent intent=new Intent(eContext, SubReadActivity.class);
                intent.putExtra(SubReadActivity.BOOK_NAME,book.getBook_name());
                intent.putExtra(SubReadActivity.BOOK_IMAGE_ID,book.getImageId());
                eContext.startActivity(intent);
            }
        });
        holder.writerName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position=holder.getAdapterPosition();
                Book book=eBookList.get(position);
                Intent intent=new Intent(eContext, SubReadActivity.class);
                intent.putExtra(SubReadActivity.BOOK_NAME,book.getBook_name());
                intent.putExtra(SubReadActivity.BOOK_IMAGE_ID,book.getImageId());
                eContext.startActivity(intent);
            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder( ViewHolder holder, int position) {
        Book book=eBookList.get(position);
        holder.bookName.setText(book.getBook_name());
        holder.bookImage.setImageResource(book.getImageId());
        holder.writerName.setText(book.getWriter());
    }

    @Override
    public int getItemCount() {
        return eBookList.size();
    }



}
