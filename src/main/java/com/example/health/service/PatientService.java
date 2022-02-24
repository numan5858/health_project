package com.example.health.service;

import java.util.List;
import java.util.Map;

import com.example.health.dto.PatientDTO;

public interface PatientService {

	public PatientDTO save(PatientDTO patient);
    public PatientDTO update(PatientDTO patient,  long pid) throws Exception;
	public PatientDTO getById(long pid) throws Exception;
	public List<PatientDTO> getAll();
	public Map<String, Boolean> delete(long pid) throws Exception;
	public boolean existsByNumber(PatientDTO patient);
}
