package com.example.health.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import com.example.health.config.UserDetailsImpl;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.health.dto.PatientDTO;
import com.example.health.model.Patient;
import com.example.health.model.User;
import com.example.health.repository.UserRepository;
import com.example.health.service.PatientServiceImpl;


@RestController
@RequestMapping(value = "/api")
public class PatientController {

	@Autowired
	private PatientServiceImpl patientServiceImpl;

	@Autowired
	UserRepository userRepository;
	
	@PreAuthorize("hasAnyRole('ROLE_PATIENT')")
	@PostMapping(value="/patient",produces = { MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE }, 
			consumes = { MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<PatientDTO> addPatient(@RequestBody PatientDTO patientDTO) {
// 		Authentication user = SecurityContextHolder.getContext().getAuthentication();
// 		//User user = (User) authentication.getPrincipal();
// 		 Patient patient = new Patient();

// 		patient.setPid(patientDTO.getPid());
// 		patient.setpName(patientDTO.getpName());
// 		patient.setpDob(patientDTO.getpDob());
// 		patient.setpMobileNo(patientDTO.getpMobileNo());
// 		patient
		
// 		.setpAdd(patientDTO.getpAdd());

// 		((User) user).setPatient(patient);
// userRepository.save(user);
		PatientDTO patDTO = patientServiceImpl.save(patientDTO);
			
		return ResponseEntity.ok().body(patDTO);
	}

	@PreAuthorize("hasAnyRole('ROLE_PATIENT')")
	@PutMapping(value="/patient/{id}",produces = { MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE }, 
			consumes = { MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<PatientDTO> updatePatient(@RequestBody PatientDTO patient,
								@PathVariable("id") long patId) throws Exception {
		
		PatientDTO patDTO = patientServiceImpl.update(patient, patId);
		return ResponseEntity.ok().body(patDTO);
	}
	
	@PreAuthorize("hasAnyRole('ROLE_PATIENT')")
	@DeleteMapping(value="/patient/{id}")
	public Map<String, Boolean> deletePatient(@PathVariable long id) throws Exception
	{
		return patientServiceImpl.delete(id);
	}

	@PreAuthorize("hasAnyRole('ROLE_PATIENT')")
	@GetMapping(value="/patient/{id}", produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<PatientDTO> findById(Authentication authentication,@PathVariable long id) throws Exception {
		UserDetailsImpl user = (UserDetailsImpl) authentication.getPrincipal();


        long i=user.getPatient().getPid();

		if(id!=i){
			return new ResponseEntity<PatientDTO>(HttpStatus.NOT_FOUND);
		}

		PatientDTO patDTO =  patientServiceImpl.getById(id);
		return ResponseEntity.ok().body(patDTO);
	}
	
	
	@GetMapping(value="/patient")
	public ResponseEntity<List<PatientDTO>> listAll()
	{
		List<PatientDTO> patDTOList =  patientServiceImpl.getAll();
		return ResponseEntity.ok().body(patDTOList);
	}
	
	// @PreAuthorize("hasAnyRole('ROLE_PATIENT')")
	// @PostMapping(value = "/patient/check")
	// public ResponseEntity<Map<String, Boolean>> isExists(@RequestBody PatientDTO patient){
	// 	Map<String, Boolean> res = new HashMap<>();
	// 	if(patientServiceImpl.existsByNumber(patient)) {
	// 		res.put("available", Boolean.TRUE);
	// 	}else {
	// 		res.put("available", Boolean.FALSE);
	// 	}
		
	// 	return ResponseEntity.ok().body(res);
	// }

	
	
}
