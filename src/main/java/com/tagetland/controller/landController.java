package com.tagetland.controller;

import java.util.List;
import com.tagetland.service.*;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/Targetland")

public class landController {
     @Autowired
    private landService landService;

    @PostMapping("/getArea")
    public List<Integer> getCalcArea(@RequestBody List<String> rectangles) {
        return landService.calcArea(rectangles);
    } 
}
