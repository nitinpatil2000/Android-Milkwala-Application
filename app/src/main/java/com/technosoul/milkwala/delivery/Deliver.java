package com.technosoul.milkwala.delivery;

public class Deliver {

    String deliverTxt;
    String deliverSubText;

    public Deliver(String deliverTxt, String deliverSubText) {
        this.deliverTxt = deliverTxt;
        this.deliverSubText = deliverSubText;
    }

    public String getDeliverTxt() {
        return deliverTxt;
    }

    public void setDeliverTxt(String deliverTxt) {
        this.deliverTxt = deliverTxt;
    }

    public String getDeliverSubText() {
        return deliverSubText;
    }

    public void setDeliverSubText(String deliverSubText) {
        this.deliverSubText = deliverSubText;
    }
}
