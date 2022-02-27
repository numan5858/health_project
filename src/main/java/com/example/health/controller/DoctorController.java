package com.example.health.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.health.dto.DoctorDTO;
import com.example.health.service.DoctorServiceImpl;


@RestController
@RequestMapping(value = "/api")
public class DoctorController {

	@Autowired
	private DoctorServiceImpl docService;

	@PostMapping(value = "/doctor", produces = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE }, consumes = { MediaType.APPLICATION_JSON_VALUE,
					MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<DoctorDTO> addDoctor(@RequestBody DoctorDTO doctor) {

		DoctorDTO doc = docService.save(doctor);
		return ResponseEntity.ok().body(doc);
	}

	@PreAuthorize("hasAnyRole('ROLE_DOCTOR')")
	@PutMapping(value = "/doctor/{id}", produces = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE }, consumes = { MediaType.APPLICATION_JSON_VALUE,
					MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<DoctorDTO> updateDoctor(@RequestBody DoctorDTO doctor, @PathVariable("id") long docId)
			throws Exception {

		DoctorDTO doc = docService.update(doctor, docId);
		return ResponseEntity.ok().body(doc);
	}

	@PreAuthorize("hasAnyRole('ROLE_DOCTOR')")
	@DeleteMapping(value = "/doctor/{id}")
	public Map<String, Boolean> deleteDoctor(@PathVariable long id) throws Exception {
		return docService.delete(id);
	}

	@PreAuthorize("hasAnyRole('ROLE_DOCTOR')")
	@GetMapping(value = "/doctor/{id}", produces = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<DoctorDTO> findById(@PathVariable long id) throws Exception {

		DoctorDTO doc = docService.getById(id);
		return ResponseEntity.ok().body(doc);
	}

	@PreAuthorize("hasAnyRole('ROLE_DOCTOR')")
	@GetMapping(value = "/doctor")
	public ResponseEntity<List<DoctorDTO>> listAll() {
		List<DoctorDTO> doc = docService.getAll();
		return ResponseEntity.ok().body(doc);
	}
	

}
