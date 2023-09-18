package ps2.PS2.service;

import org.springframework.stereotype.Component;
import ps2.PS2.dto.ChartInfoDTO;
import ps2.PS2.dto.ChartInit;

import java.sql.Timestamp;
import java.util.List;

@Component
public interface MeasurementService {
    void generateMeasurements(int device);
    boolean isSameDay(Timestamp t1 , Timestamp t2);
    List<ChartInfoDTO> generateDataForChart(ChartInit dto);
}
