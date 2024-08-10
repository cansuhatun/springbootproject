

package com.app.techworm.main.service;

import com.app.techworm.main.entity.Scenario;
import com.app.techworm.main.repository.ScenarioRepository;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.List;

@Service
public class ScenarioService {

    private final ScenarioRepository scenarioRepository;

    public ScenarioService(ScenarioRepository scenarioRepository) {
        this.scenarioRepository = scenarioRepository;
    }

    public List<Scenario> getAllScenarios() {
        return scenarioRepository.findAll();
    }

    public Scenario getScenarioById(Integer id) {
        return scenarioRepository.findById(id).orElse(null);
    }

    public Scenario createScenario(Scenario scenario) {
        // insertDate alanının null olmamasını sağla
        if (scenario.getInsertDate() == null) {
            scenario.setInsertDate(OffsetDateTime.now(ZoneOffset.UTC));
        }
        return scenarioRepository.save(scenario);
    }

    public Scenario updateScenario(Integer id, Scenario scenario) {
        if (scenarioRepository.existsById(id)) {
            scenario.setId(id);
            // insertDate alanının null olmamasını sağla
            if (scenario.getInsertDate() == null) {
                scenario.setInsertDate(OffsetDateTime.now(ZoneOffset.UTC));
            }
            return scenarioRepository.save(scenario);
        } else {
            return null;
        }
    }

    public boolean deleteScenario(Integer id) {
        if (scenarioRepository.existsById(id)) {
            scenarioRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }
}
