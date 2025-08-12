package com.hrmsdashboard.controller;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hrmsdashboard.dto.ServerStatusResponse;
import com.hrmsdashboard.service.ServerStatusService;

@RestController
@RequestMapping("/api/server")
@CrossOrigin(origins = "http://localhost:5173")
public class ServerStatusController {

	private final ServerStatusService statusService;

	public ServerStatusController(ServerStatusService statusService) {
		this.statusService = statusService;
	}

	@GetMapping("/status")
	public List<ServerStatusResponse> getStatus() {
		return statusService.checkAllStatuses();
	}

}
