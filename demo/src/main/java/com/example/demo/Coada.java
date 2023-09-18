package com.example.demo;

import java.sql.Timestamp;
import java.util.UUID;

public class Coada {
    private Timestamp timestamp;
    private int device_id;
    private float value;

    @Override
    public String toString() {
        return "Coada{" +
                "timestamp=" + timestamp +
                ", device_id=" + device_id +
                ", value=" + value +
                '}';
    }

    public Coada(Timestamp timestamp, int device_id, float value){
        this.timestamp = timestamp;
        this.device_id = device_id;
        this.value = value;
    }
}
