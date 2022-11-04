package com.task.rohan;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ActuatorController {

	@GetMapping("/Demo")
	public String Demo() {
		return "This is the demonstration of actuator from SpringBoot";
	}
}
