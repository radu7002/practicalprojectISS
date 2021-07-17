package sda.practical;

import com.google.gson.annotations.SerializedName;
public class InSpaceResponse {
    private int number;
    private String message;

    @SerializedName("people")
    private People people;

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public People getPeople() {
        return people;
    }

    public void setPeople(People people) {
        this.people = people;
    }
}

