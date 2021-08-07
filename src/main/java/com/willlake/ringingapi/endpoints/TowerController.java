package com.willlake.ringingapi.endpoints;

import com.willlake.ringingapi.databaseObj.Tower;
import com.willlake.ringingapi.endpoints.handlers.TowerHandler;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/towers")
public class TowerController {
    private final TowerHandler towerHandler;

    public TowerController(TowerHandler towerHandler) {
        this.towerHandler = towerHandler;
    }

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public ResponseEntity<Iterable<Tower>> allTowers() {
        return new ResponseEntity<>(towerHandler.allTowers(), HttpStatus.OK);
    }

    @RequestMapping(value = "/{towerId}", method =  RequestMethod.GET)
    public ResponseEntity<Tower> getTower(@PathVariable String towerId) {
        return new ResponseEntity<>(towerHandler.getTower(towerId), HttpStatus.OK);
    }
}
