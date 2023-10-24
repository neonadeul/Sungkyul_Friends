package com.gmail.sungkyulfriends.MyPage;

public class ReviewData {

    private String score;
    private String content;

    public ReviewData(String score, String content) {
        this.score = score;
        this.content = content;
    }

    public ReviewData() {

    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
