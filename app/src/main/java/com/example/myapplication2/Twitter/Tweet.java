package com.example.myapplication2.Twitter;


public class Tweet {
    private String text;
    private String created_on;
    private String number_likes;
    private String number_retweets;

    public Tweet(String text, String created_on, String number_likes, String number_retweets){
        this.text=text;
        this.created_on=created_on;
        this.number_likes= number_likes;
        this.number_retweets=number_retweets;
    }

    public String getCreated_on() {
        return created_on;
    }

    public String getNumber_likes() {
        return number_likes;
    }

    public String getNumber_retweets() {
        return number_retweets;
    }

    public String getText() {
        return text;
    }
}
