package model;

import java.util.Random;

public class Toy {
    private String id = "";
    private String title = "";
    private int quantity = 0;
    private int frequencyOfFalling = 0;

    public Toy(String id, String title, int quantity, int frequencyOfFalling) {
        this.id = id;
        this.title = title;
        this.quantity = quantity;
        this.frequencyOfFalling = frequencyOfFalling;
    }

    public Toy(String title, int quantity, int frequencyOfFalling) {
        this.title = title;
        this.quantity = quantity;
        this.frequencyOfFalling = check(frequencyOfFalling);
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setId(String id) {
        this.id = id;
    }

    private int check(int frequencyOfFalling){
        if (frequencyOfFalling > 0 && frequencyOfFalling < 100){
            return  frequencyOfFalling;
        }
        do{
            frequencyOfFalling /= 10;
        }while(frequencyOfFalling > 100);
        return frequencyOfFalling;
    }

    private static int generateRandomIntIntRange(int min, int max) {
        Random r = new Random();
        return r.nextInt((max - min) + 1) + min;
    }

    public int getFrequencyOfFalling() {
        return frequencyOfFalling;
    }

    public void setFrequencyOfFalling(int frequencyOfFalling) {
        this.frequencyOfFalling = frequencyOfFalling;
    }

    @Override
    public String toString() {
        return  "id ='" + id + '\'' +
                ", title ='" + title + '\'' +
                ", quantity =" + quantity +
                ", frequencyOfFalling =" + frequencyOfFalling;
    }
}
