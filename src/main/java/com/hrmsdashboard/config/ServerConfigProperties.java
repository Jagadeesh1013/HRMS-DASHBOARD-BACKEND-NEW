package com.hrmsdashboard.config;

import java.util.List;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "sftp")
public class ServerConfigProperties {

	private List<SftpServerDetails> servers;

	public List<SftpServerDetails> getServers() {
		return servers;
	}

	public void setServers(List<SftpServerDetails> servers) {
		this.servers = servers;
	}

	public static class SftpServerDetails {

		private String name;
		private String host;
		private int port;
		private String username;
		private String password;

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getHost() {
			return host;
		}

		public void setHost(String host) {
			this.host = host;
		}

		public int getPort() {
			return port;
		}

		public void setPort(int port) {
			this.port = port;
		}

		public String getUsername() {
			return username;
		}

		public void setUsername(String username) {
			this.username = username;
		}

		public String getPassword() {
			return password;
		}

		public void setPassword(String password) {
			this.password = password;
		}
	}
}
