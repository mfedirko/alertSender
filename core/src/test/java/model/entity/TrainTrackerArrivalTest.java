package model.entity;

import org.junit.Test;

import java.util.Date;

import static org.junit.Assert.assertEquals;

public class TrainTrackerArrivalTest {

    @Test
    public void getDescription() {
        TrainTrackerArrival arrival1 = new TrainTrackerArrival();
        arrival1.setStationName("Fullerton");
        arrival1.setNonLivePrediction(false);
        arrival1.setDestination("95th/Dan Ryan");
        arrival1.setDelayed(true);
        arrival1.setNonLivePrediction(false);
        arrival1.setHasFault(false);
        arrival1.setApproaching(false);
        arrival1.setRunNumber(930);
        arrival1.setArrivalTime(new Date(119,1,9,1,14,27));
        arrival1.setStopId(30234);

        String expected = "Train number 930 toward 95th/Dan Ryan  expected to arrive at " + new Date(119,1,9,1,14,27).toString()
                + " at Fullerton. Train will be delayed.";


        assertEquals(arrival1.getDescription(),expected);
    }
}