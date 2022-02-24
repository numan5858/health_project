package com.example.health.dto;


import com.example.health.model.Doctor;


public class DoctorDTO {
	private long doctorId;
	private String doctorName;
	private String doctorEmail;
	private String doctorAddress;
	private long doctorPhoneNO;

	
	public long getDoctorId() {
		return doctorId;
	}
	public void setDoctorId(long doctorId) {
		this.doctorId = doctorId;
	}
	public String getDoctorName() {
		return doctorName;
	}
	public void setDoctorName(String doctorName) {
		this.doctorName = doctorName;
	}
	public String getDoctorAddress() {
		return doctorAddress;
	}
	public void setDoctorAddress(String doctorAddress) {
		this.doctorAddress = doctorAddress;
	}
	public long getDoctorPhoneNO() {
		return doctorPhoneNO;
	}
	public void setDoctorPhoneNO(long doctorPhoneNO) {
		this.doctorPhoneNO = doctorPhoneNO;
	}

	public String getDoctorEmail() {
		return doctorEmail;
	}
	public void setDoctorEmail(String doctorEmail) {
		this.doctorEmail = doctorEmail;
	}
	

	public DoctorDTO(Doctor doctor) {
		
		this.doctorId = doctor.getDoctorId();
		this.doctorName = doctor.getDoctorName();
		this.doctorEmail=doctor.getDoctorEmail();
		this.doctorAddress = doctor.getDoctorAddress();
		this.doctorPhoneNO = doctor.getDoctorPhoneNO();
	
	}
	// public DoctorDTO() {
		
	// 	
	// }
	

}
