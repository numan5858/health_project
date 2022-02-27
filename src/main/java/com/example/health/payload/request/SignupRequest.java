package com.example.health.payload.request;

import java.util.Set;

import javax.persistence.Column;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class SignupRequest {

	@NotBlank
    @Size(min = 3, max = 20)
    private String username;
    
    private Set<String> role;
    
    @NotBlank
    @Size(min = 6, max = 40)
    private String password;

    @Column(name = "pname")
	private String pName;

	@Column(name = "pemail")
	private String pEmail;

	@Column(name = "padd")
	private String pAdd;

	@Column(name = "pmobile")
	private long pMobileNo;
  
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
    
    public Set<String> getRole() {
      return this.role;
    }
    
    public void setRole(Set<String> role) {
      this.role = role;
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

	public void setpDob(String pEmail) {
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
