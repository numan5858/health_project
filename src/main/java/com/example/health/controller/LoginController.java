package com.example.health.controller;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.health.config.UserDetailsImpl;
import com.example.health.config.jwt.JwtUtils;
import com.example.health.dto.PatientDTO;
import com.example.health.model.Doctor;
import com.example.health.model.ERole;
import com.example.health.model.Patient;
import com.example.health.model.Role;
import com.example.health.model.User;
import com.example.health.payload.request.LoginRequest;
import com.example.health.payload.request.SignupRequest;
import com.example.health.payload.response.JwtResponse;
import com.example.health.payload.response.MessageResponse;
import com.example.health.repository.DoctorRepository;
import com.example.health.repository.PatientRepository;
import com.example.health.repository.RoleRepository;
import com.example.health.repository.UserRepository;
import com.example.health.service.PatientServiceImpl;


@RestController
@RequestMapping(value="/auth")
public class LoginController {
	
	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	UserRepository userRepository;

	@Autowired
	PatientRepository patientRepository;

	@Autowired
	DoctorRepository doctorRepository;

	@Autowired
	RoleRepository roleRepository;

	@Autowired
	PasswordEncoder encoder;

	@Autowired
	JwtUtils jwtUtils;
	
	@PostMapping("/signin")
	public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

		System.out.println(loginRequest.getUsername() + " : " + loginRequest.getPassword());
		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

		SecurityContextHolder.getContext().setAuthentication(authentication);
		String jwt = jwtUtils.generateJwtToken(authentication);
		
		UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();		
		List<String> roles = userDetails.getAuthorities().stream()
				.map(item -> item.getAuthority())
				.collect(Collectors.toList());

		return ResponseEntity.ok(new JwtResponse(jwt, 
												 userDetails.getId(), 
												 userDetails.getUsername(),
												 roles));
	}

	@PostMapping("/signup")
	public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
		if (userRepository.existsByUsername(signUpRequest.getUsername())) {
			return ResponseEntity
					.badRequest()
					.body(new MessageResponse("Error: Username is already taken!"));
		}

		// Create new user's account
		User user = new User(signUpRequest.getUsername(), 
							 encoder.encode(signUpRequest.getPassword()));

// 							 Patient patient= new Patient(signUpRequest.getpName(),signUpRequest.getpDob(),signUpRequest.getpAdd(),signUpRequest.getpMobileNo());
// 							 patientRepository.save(patient);
// // PatientDTO patientDTO= new PatientDTO(patient);
// // patientServiceImpl.save(patientDTO);


// user.setPatient(patient);

		Set<String> strRoles = signUpRequest.getRole();
		Set<Role> roles = new HashSet<>();

		if (strRoles == null) {
			Role userRole = roleRepository.findByName(ERole.ROLE_USER)
					.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
			roles.add(userRole);
		} else {
			strRoles.forEach(role -> {
				switch (role) {
				case "admin":
					Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN)
							.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
					roles.add(adminRole);

					break;

				case "patient":
				Role patientRole = roleRepository.findByName(ERole.ROLE_PATIENT)
				.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
		roles.add(patientRole);
		Patient patient= new Patient(signUpRequest.getpName(),signUpRequest.getpDob(),signUpRequest.getpAdd(),signUpRequest.getpMobileNo());
		patientRepository.save(patient);
// PatientDTO patientDTO= new PatientDTO(patient);
// patientServiceImpl.save(patientDTO);
        user.setPatient(patient);
                    break;

			    case "doctor":
				Role doctorRole = roleRepository.findByName(ERole.ROLE_DOCTOR)
				.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
		roles.add(doctorRole);
		Doctor doctor= new Doctor(signUpRequest.getpName(),signUpRequest.getpDob(),signUpRequest.getpAdd(),signUpRequest.getpMobileNo());
		doctorRepository.save(doctor);
		user.setDoctor(doctor);
                break;

				default:
					Role userRole = roleRepository.findByName(ERole.ROLE_USER)
							.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
					roles.add(userRole);
				}
			});
		}

		user.setRoles(roles);
		userRepository.save(user);

		return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
	}
	
//	@GetMapping(value = "/")
//	public String login() {
//		return "login success";
//	}

}
