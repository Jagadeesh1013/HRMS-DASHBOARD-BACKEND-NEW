package com.hrmsdashboard.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.hrmsdashboard.dto.AuthRequest;
import com.hrmsdashboard.dto.AuthResponse;
import com.hrmsdashboard.dto.SignupRequest;
import com.hrmsdashboard.dto.UserDTO;
import com.hrmsdashboard.entity.UserEntity;
import com.hrmsdashboard.repository.UserRepository;
import com.hrmsdashboard.secuirty.JwtService;

@Service
public class AuthService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private JwtService jwtService;

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private PasswordEncoder passwordEncoder;

	public AuthResponse login(AuthRequest request) {
		authenticationManager
				.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));

		UserEntity user = userRepository.findByUsername(request.getUsername())
				.orElseThrow(() -> new RuntimeException("User not found"));

		String token = jwtService.generateToken(user.getUsername(), user.getRole());
		UserDTO userDTO = new UserDTO(user.getUsername(), user.getRole());
		return new AuthResponse(true, userDTO, token);
	}

	public AuthResponse register(SignupRequest request) {
		if (userRepository.existsByUsername(request.getUsername())) {
			throw new RuntimeException("Username already exists");
		}
		if (!request.getPassword().equals(request.getConfirmPassword())) {
			throw new RuntimeException("Passwords do not match");
		}

		UserEntity user = new UserEntity(request.getUsername(), passwordEncoder.encode(request.getPassword()), "USER");
		userRepository.save(user);

		String token = jwtService.generateToken(user.getUsername(), user.getRole());
		UserDTO userDTO = new UserDTO(user.getUsername(), user.getRole());
		return new AuthResponse(true, userDTO, token);
	}
}
