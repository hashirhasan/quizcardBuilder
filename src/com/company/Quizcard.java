package com.company;

class Quizcard{
    private String question;
    private String answer;
    public Quizcard(String ques,String ans)
    {
        this.question=ques;
        this.answer=ans;
    }

    public String getAnswer() {
        return answer;
    }

    public String getQuestion() {
        return question;
    }
}