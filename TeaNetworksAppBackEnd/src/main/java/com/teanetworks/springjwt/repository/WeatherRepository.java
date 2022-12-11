package com.teanetworks.springjwt.repository;

import com.teanetworks.springjwt.models.User;
import com.teanetworks.springjwt.models.WeatherData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface WeatherRepository extends JpaRepository<WeatherData, Long> {
    @Query("SELECT u FROM WeatherData u WHERE u.condition = :condition")
    List<WeatherData> findByCondition(String condition);
    @Query("SELECT u FROM WeatherData u WHERE u.location = :location")
    List<WeatherData> findByLocation(@Param("location") String location);
    @Query("SELECT u FROM WeatherData u WHERE u.average = :average")
    List<WeatherData> findByAverage(Integer average);
}