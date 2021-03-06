package com.example.glow.controller;

import com.example.glow.controller.dto.BaseData;
import com.example.glow.controller.dto.IncomeData;
import com.example.glow.entity.postgreSql.ApplicationsResource;
import com.example.glow.service.IncomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


/**
 * @author Lagus Maksim, Makarev Evgenij
 * @version 1.0
 * Main controller for MongoDB database encapsulating all
 * get methods from multiple CRUD repositories and connecting
 * backend and local host server via mapping
 */


@RestController
public class IncomeController {

    private IncomeService incomeService;

    @Autowired
    public IncomeController(IncomeService incomeService) {
        this.incomeService = incomeService;
    }

    @GetMapping("/income")
    @CrossOrigin
    @ResponseBody
    public ResponseEntity<IncomeData> getIncome(@RequestParam(name = "fio") String fio) {
        String customPredicate = fio.toLowerCase().trim();
        List<IncomeData> res = incomeService.getIncomeData().stream().filter(val -> val.getCustFio().toLowerCase().trim().equals(customPredicate)).collect(Collectors.toList());
        if (res.size() > 0) {
            System.out.println("Income request with " + fio + ", returning " + res.get(0).getCustFio());
            return ResponseEntity.ok(res.get(0));
        }
        System.out.println("Income request with " + fio + ", returning null");
        return ResponseEntity.ok(null);
    }
}
