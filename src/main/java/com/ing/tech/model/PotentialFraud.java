package com.ing.tech.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

@Slf4j
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class PotentialFraud {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToOne(cascade = CascadeType.ALL)
    private LoginEntry loginEntry;

    @OneToOne
    private Location firstLocation;

    @OneToOne
    private Location secondLocation;

    private Long estimatedArrivalTime = 0L;
    private Long actualArrivalTime;

    private boolean fraudSuspect = false;

    public PotentialFraud(LoginEntry loginEntry, Location firstLocation, Location secondLocation, Long estimatedArrivalTime, Long actualArrivalTime, boolean fraudSuspect) {
        this.loginEntry = loginEntry;
        this.firstLocation = firstLocation;
        this.secondLocation = secondLocation;
        this.estimatedArrivalTime = estimatedArrivalTime;
        this.actualArrivalTime = actualArrivalTime;
        this.fraudSuspect = fraudSuspect;
    }

    public PotentialFraud(Location firstLocation, Location secondLocation, long l) {
        this.firstLocation = firstLocation;
        this.secondLocation = secondLocation;
        this.actualArrivalTime = l;
    }

    public Long durationToLong(String apiResponse) {
        Long timeInSeconds = 0L;
        String[] splitResponse = apiResponse.split("\"");
        String[] splitDuration = splitResponse[3].split(" ");

        if(splitDuration[1].compareTo("days") == 0)
            timeInSeconds += 86400 * Long.parseLong(splitDuration[0]) + 3600 * Long.parseLong(splitDuration[2]);
        else if (splitDuration[1].compareTo("hours") == 0)
            timeInSeconds += 3600 * Long.parseLong(splitDuration[0]) + 60 * Long.parseLong(splitDuration[2]);

        return timeInSeconds;
    }

    public void checkIfSuspectOfFraud () throws IOException {

        if (firstLocation.toString().compareTo(secondLocation.toString()) != 0) {

            URL url = new URL("https://maps.googleapis.com/maps/api/distancematrix/json?units=imperial&origins=" +
                    this.firstLocation.getLatCoordinate() +
                    "," +
                    this.firstLocation.getLongCoordinate() +
                    "&destinations=" +
                    this.secondLocation.getLatCoordinate() +
                    "," +
                    this.secondLocation.getLongCoordinate() +
                    "&key=AIzaSyCGk43cBZwgGDXm5-vPlNMFoRGmn7XoT2o");

            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setConnectTimeout(2000);
            int status = connection.getResponseCode();

            BufferedReader in = new BufferedReader(
                    new InputStreamReader(connection.getInputStream())
            );

            String inputLine;
            StringBuffer content = new StringBuffer();
            while ((inputLine = in.readLine()) != null) {
                content.append(inputLine);
                if (inputLine.contains("duration"))
                    this.estimatedArrivalTime = durationToLong(in.readLine());
            }

            in.close();
            connection.disconnect();


            log.info(String.valueOf(this.actualArrivalTime));
            log.info(String.valueOf(this.estimatedArrivalTime));

            if (this.actualArrivalTime < this.estimatedArrivalTime)
                this.fraudSuspect = true;
        }
    }
}
