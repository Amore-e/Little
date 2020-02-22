package com.example.littlenote.uc;

public class Book {
    private String book_name;
    private String writer;
    private int imageId;

    public Book(String book_name,String writer ,int imageId){
        this.book_name=book_name;
        this.writer=writer;
        this.imageId=imageId;
    }
    public String getBook_name(){
        return book_name;
    }
    public String getWriter(){
        return writer;
    }
    public int getImageId(){
        return imageId;
    }


}
