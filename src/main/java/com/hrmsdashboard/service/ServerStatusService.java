package com.hrmsdashboard.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.hrmsdashboard.config.ServerConfigProperties;
import com.hrmsdashboard.dto.ServerStatusResponse;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;

@Service
public class ServerStatusService {

    private final ServerConfigProperties config;

    public ServerStatusService(ServerConfigProperties config) {
        this.config = config;
    }

    public List<ServerStatusResponse> checkAllStatuses() {
        List<ServerStatusResponse> responses = new ArrayList<>();

        for (ServerConfigProperties.SftpServerDetails server : config.getServers()) {
            responses.add(checkSingleServer(server));
        }

        return responses;
    }

    private ServerStatusResponse checkSingleServer(ServerConfigProperties.SftpServerDetails server) {
        JSch jsch = new JSch();
        Session session = null;

        try {
            session = jsch.getSession(server.getUsername(), server.getHost(), server.getPort());
            session.setPassword(server.getPassword());
            session.setConfig("StrictHostKeyChecking", "no");
            session.connect();

            return new ServerStatusResponse(server.getName(), true);
        } catch (Exception e) {
            return new ServerStatusResponse(server.getName(), false);
        } finally {
            if (session != null && session.isConnected()) {
                session.disconnect();
            }
        }
    }
}

