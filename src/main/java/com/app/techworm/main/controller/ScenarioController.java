package com.app.techworm.main.controller;

import com.app.techworm.main.entity.Scenario;
import com.app.techworm.main.service.ScenarioService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/scenarios")
public class ScenarioController {

    private final ScenarioService scenarioService;

    public ScenarioController(ScenarioService scenarioService) {
        this.scenarioService = scenarioService;
    }

    @GetMapping
    public List<Scenario> getAllScenarios() {
        return scenarioService.getAllScenarios();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Scenario> getScenarioById(@PathVariable Integer id) {
        Scenario scenario = scenarioService.getScenarioById(id);
        if (scenario != null) {
            return ResponseEntity.ok(scenario);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Scenario> createScenario(@RequestBody Scenario scenario) {
        Scenario createdScenario = scenarioService.createScenario(scenario);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdScenario);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Scenario> updateScenario(@PathVariable Integer id, @RequestBody Scenario scenario) {
        Scenario updatedScenario = scenarioService.updateScenario(id, scenario);
        if (updatedScenario != null) {
            return ResponseEntity.ok(updatedScenario);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteScenario(@PathVariable Integer id) {
        boolean isDeleted = scenarioService.deleteScenario(id);
        if (isDeleted) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
