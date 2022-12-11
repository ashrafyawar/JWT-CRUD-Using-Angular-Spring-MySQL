package com.teanetworks.springjwt.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.teanetworks.springjwt.models.User;
import com.teanetworks.springjwt.models.WeatherData;
import com.teanetworks.springjwt.models.WeatherDataFilter;
import com.teanetworks.springjwt.payload.response.MessageResponse;
import com.teanetworks.springjwt.repository.UserRepository;
import com.teanetworks.springjwt.repository.WeatherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/test")
public class TestController {

  @Autowired
  WeatherRepository weatherRepository;
  @Autowired
  UserRepository userRepository;

  @GetMapping("/all")
  public ResponseEntity<?> allAccess() throws JsonProcessingException {
    return ResponseEntity.ok(new MessageResponse("Public Content"));
  }

  @GetMapping("/user")
  @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
  public ResponseEntity<?> userAccess() {
    return ResponseEntity.ok(new MessageResponse("End-User Content"));
  }

  @GetMapping("/admin")
  @PreAuthorize("hasRole('ADMIN')")
  public ResponseEntity<?> adminAccess() {
    return ResponseEntity.ok(new MessageResponse("Admin Content"));
  }

  @GetMapping("/end-user-list")
  @PreAuthorize("hasRole('ADMIN')")
  public ResponseEntity<List<User>> getUserList() {
    return ResponseEntity.ok(userRepository.findAll());
  }

  @PutMapping("/user")
  @PreAuthorize("hasRole('ADMIN')")
  public ResponseEntity<?> updateUser(@RequestBody User updatedUser,@RequestParam("id") Long userId) {

    Optional<User> user = userRepository.findById(userId);
    user.get().setUsername(updatedUser.getUsername());
    user.get().setEmail(updatedUser.getEmail());
    userRepository.save(user.get());
    return ResponseEntity.ok(new MessageResponse("User With ID: " + userId + " Has Successfully Been Updated !"));
  }

  @DeleteMapping("/user")
  @PreAuthorize("hasRole('ADMIN')")
  public ResponseEntity<?> deleteUser(@RequestParam("id") Long userId) {

    userRepository.deleteById(userId);
    return ResponseEntity.ok(new MessageResponse("User With ID: " + userId + " Has Successfully Been Deleted !"));
  }

  @PostMapping("/weather")
  @PreAuthorize("hasRole('ADMIN')")
  public ResponseEntity<List<WeatherData>> getWeatherData(@RequestBody WeatherDataFilter weatherDataFilter) {

    if (weatherDataFilter.getIsAll()){
      return ResponseEntity.ok(weatherRepository.findAll());
    }else {
      switch (weatherDataFilter.getFilterType()) {
        case "condition": {
          List<WeatherData> weatherDataList = weatherRepository.findByCondition(weatherDataFilter.getFilterVal());
          return ResponseEntity.ok(weatherDataList);
        }
        case "location": {
          List<WeatherData> weatherDataList = weatherRepository.findByLocation(weatherDataFilter.getFilterVal());
          return ResponseEntity.ok(weatherDataList);
        }
        case "average": {
          List<WeatherData> weatherDataList = weatherRepository.findByAverage(Integer.parseInt(weatherDataFilter.getFilterVal()));
          return ResponseEntity.ok(weatherDataList);
        }
        default:
          return ResponseEntity.ok(weatherRepository.findAll());
      }
    }
  }

  @PutMapping("/weather")
  @PreAuthorize("hasRole('ADMIN')")
  public ResponseEntity<?> updateWeatherData(@RequestBody WeatherData updatedWeather,@RequestParam("id") Long weatherId) {

    Optional<WeatherData> weatherData = weatherRepository.findById(weatherId);
    weatherData.get().setAverage(updatedWeather.getAverage());
    weatherData.get().setCondition(updatedWeather.getCondition());
    weatherData.get().setLocation(updatedWeather.getLocation());
    weatherData.get().setTimeRange(updatedWeather.getTimeRange());
    weatherRepository.save(weatherData.get());
    return ResponseEntity.ok(new MessageResponse("WeatherData With ID: " + weatherId + " Has Successfully Been Updated !"));
  }

  @DeleteMapping("/weather")
  @PreAuthorize("hasRole('ADMIN')")
  public ResponseEntity<?> deleteWeatherData(@RequestParam("id") Long weatherId) {

    weatherRepository.deleteById(weatherId);
    return ResponseEntity.ok(new MessageResponse("WeatherData With ID: " + weatherId + " Has Successfully Been Deleted !"));
  }

}
