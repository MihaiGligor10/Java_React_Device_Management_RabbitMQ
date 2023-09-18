package ps2.PS2.config;

import java.sql.Timestamp;
import java.util.UUID;

public class Coada {
    private Timestamp timestamp;
    private UUID device_id;
    private float value;

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }

    public UUID getDevice_id() {
        return device_id;
    }

    @Override
    public String toString() {
        return "Coada{" +
                "timestamp=" + timestamp +
                ", device_id=" + device_id +
                ", value=" + value +
                '}';
    }

    public void setDevice_id(UUID device_id) {
        this.device_id = device_id;
    }

    public float getValue() {
        return value;
    }

    public void setValue(float value) {
        this.value = value;
    }

    public Coada(Timestamp timestamp, UUID device_id, float value){
        this.timestamp = timestamp;
        this.device_id = device_id;
        this.value = value;
    }
}
