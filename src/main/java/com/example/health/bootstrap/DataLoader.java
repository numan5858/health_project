package com.example.health.bootstrap;

import java.util.HashSet;
import java.util.Set;

import com.example.health.model.ERole;
import com.example.health.model.Patient;
import com.example.health.model.Role;
import com.example.health.model.User;
import com.example.health.repository.PatientRepository;
import com.example.health.repository.RoleRepository;
import com.example.health.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements CommandLineRunner {

    private final RoleRepository roleRepository;
    private final PatientRepository patientRepository;
    private final UserRepository userRepository;
    
    @Autowired
    PasswordEncoder encoder; 
    /**
     * @param roleRepository
     * @param patientRepository
     * @param userRepository
     */
    public DataLoader(RoleRepository roleRepository, PatientRepository patientRepository,
            UserRepository userRepository) {
        this.roleRepository = roleRepository;
        this.patientRepository = patientRepository;
        this.userRepository = userRepository;
        
       
        
    }


    private void loadSecurityData() {

        
        
      Role role1=  roleRepository.save(Role.builder().name(ERole.ROLE_USER).build());
       Role role2= roleRepository.save(Role.builder().name(ERole.ROLE_ADMIN).build());
       Role role3=  roleRepository.save(Role.builder().name(ERole.ROLE_PATIENT).build());
       Role role5=  roleRepository.save(Role.builder().name(ERole.ROLE_EMPOLOYEE).build());
       Role role4= roleRepository.save(Role.builder().name(ERole.ROLE_DOCTOR).build());
      

        Patient patient1 = new Patient();
        
                patient1.setpName("gambhir");
                patient1.setpEmail("32232");
                patient1.setpMobileNo(54643);
                patient1.setpAdd("sadaj");
                patientRepository.save(patient1);

                Patient patient2=new Patient();
                
                patient2.setpName("sehwag");
                patient2.setpEmail("32232");
                patient2.setpMobileNo(5464);
                patient2.setpAdd("sadaj");
                patientRepository.save(patient2);



                User gambhir = new User("gambhir", 
                encoder.encode("gambhir"));
              
                gambhir.setPatient(patient1);
                gambhir.setRoles(Set.of(role1));

                userRepository.save(gambhir);

                User sehwag= new User("sehwag", 
                encoder.encode("sehwag"));
                // sehwag.setUsername("sehwag");
                // sehwag.setPassword("password");
                sehwag.setPatient(patient2);
                sehwag.setRoles(Set.of(role1));

                userRepository.save(sehwag);

       


    }
    

    @Override
    public void run(String... args) throws Exception {
       
            loadSecurityData();
        
        
    }

}
