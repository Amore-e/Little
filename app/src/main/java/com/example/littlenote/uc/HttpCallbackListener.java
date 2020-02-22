package com.example.littlenote.uc;

public interface HttpCallbackListener {
    String onFinish(String response);
    String onError(Exception e);
}
