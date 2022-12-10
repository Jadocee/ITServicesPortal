package com.spacejaam.itservicesportal.controllers;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.spacejaam.itservicesportal.data.PerformanceDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/API", produces = MediaType.APPLICATION_JSON_VALUE)
public class PerformanceController {

    private final PerformanceDAO performanceDAO;
    private final Gson gson;

    @Autowired
    PerformanceController(PerformanceDAO performanceDAO) {
        this.performanceDAO = performanceDAO;
        this.gson = new Gson();
    }

    @GetMapping(value = "/performance")
    public ResponseEntity<String> getPerformanceStats() {
        final JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("resolved", gson.toJson(performanceDAO.getResolvedCount()));
        jsonObject.addProperty("unresolved", gson.toJson(performanceDAO.getUnresolvedCount()));
        jsonObject.addProperty("stressRate", gson.toJson(performanceDAO.getStressRate()));
        return new ResponseEntity<>(gson.toJson(jsonObject), new HttpHeaders(), HttpStatus.OK);
    }
}
