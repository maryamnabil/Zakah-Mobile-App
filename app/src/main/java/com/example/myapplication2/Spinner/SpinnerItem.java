package com.example.myapplication2.Spinner;

public class SpinnerItem {
    String title;
    Boolean checked;

    SpinnerItem(String title , Boolean checked){
        this.title=title;
        this.checked=checked;
    }

    public String getTitle() {
        return title;
    }

    public Boolean getChecked() {
        return checked;
    }

}
