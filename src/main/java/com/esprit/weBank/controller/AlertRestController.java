package com.esprit.weBank.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.esprit.weBank.entities.Alert;
import com.esprit.weBank.services.AlertService;

@RestController
public class AlertRestController {
	@Autowired
	private AlertService alertService;
	
	
	@PostMapping(value = "/createAlert/{rib}")
    public Alert createAlert(@PathVariable(value = "rib") int rib,@RequestBody Alert alert) {
		return alertService.saveAlert(alert,rib);
	}

	@PutMapping(value ="/updateAlert/{id}")
	public Alert updateAlert(@PathVariable(value = "id") int id, @RequestBody Alert alert) {
		return alertService.updateAlert(alert, id);
	}

	@DeleteMapping(value ="/deleteAlert/{id}")
	public void deleteAlert(@PathVariable(value = "id") int id) {
		alertService.deleteAlertById(id);	}

	@GetMapping(value ="/getAlertById/{id}")
	public Alert getAlertById(@PathVariable(value = "id") int id) {
		return alertService.findAlertById(id);
	}

	@GetMapping(value ="/getAllAlerts")
	public List<Alert> getAllAlerts() {
		return alertService.findAllAlert();
	}
}
