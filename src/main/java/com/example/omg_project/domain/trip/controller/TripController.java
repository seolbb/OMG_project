package com.example.omg_project.domain.trip.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TripController {
    @GetMapping("/createtrip")
    public String createTripPage() {
        return "trip/createtrip";
    }
}