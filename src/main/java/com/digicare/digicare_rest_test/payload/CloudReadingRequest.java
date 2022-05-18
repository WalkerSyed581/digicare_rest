package com.digicare.digicare_rest_test.payload;
  
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonFormat;


public class CloudReadingRequest {

  public Long id;

  public Map<Long,Double> readings;

//   public double temperature;
//   public double heart_rate;
//   public double spo2;
  
  @JsonFormat(shape=JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss")
  public LocalDateTime timestamp;

  public static SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");



  public CloudReadingRequest() {}   

  public CloudReadingRequest(Map<Long,Double> readings,LocalDateTime timestamp) {
    this.readings = readings;
    this.timestamp = timestamp;
  }

public Long getId() {
	return id;
}

public void setId(Long id) {
	this.id = id;
}

public Map<Long, Double> getReadings() {
	return readings;
}

public void setReadings(Map<Long, Double> readings) {
	this.readings = readings;
}

public LocalDateTime getTimestamp() {
	return timestamp;
}

public void setTimestamp(LocalDateTime timestamp) {
	this.timestamp = timestamp;
}


  
 

  

 
}