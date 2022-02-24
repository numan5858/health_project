package com.example.health.dto;

import com.example.health.model.Patient;


public class PatientDTO {

	private long pid;
	private String pName;
	private String pEmail;
	private String pAdd;
	private long pMobileNo;


	public PatientDTO() {

	}

	public PatientDTO(Patient patient) {
		this.pid = patient.getPid();
		this.pName =patient.getpName();
		this.pEmail = patient.getEmail();
		this.pAdd = patient.getpAdd();
		this.pMobileNo =patient.getpMobileNo(); 
	
	}

	public long getPid() {
		return pid;
	}

	public void setPid(long pid) {
		this.pid = pid;
	}

	public String getpName() {
		return pName;
	}

	public void setpName(String pName) {
		this.pName = pName;
	}

	public String getpEmail() {
		return pEmail;
	}

	public void setpEmail(String pEmail) {
		this.pEmail = pEmail;
	}

	public String getpAdd() {
		return pAdd;
	}

	public void setpAdd(String pAdd) {
		this.pAdd = pAdd;
	}

	public long getpMobileNo() {
		return pMobileNo;
	}

	public void setpMobileNo(long pMobileNo) {
		this.pMobileNo = pMobileNo;
	}



	
}
