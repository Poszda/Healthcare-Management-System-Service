package com.hmss.springbootserver.services;

import com.hmss.springbootserver.DTOs.AppointmentDTO;
import com.hmss.springbootserver.DTOs.PatientDTO;
import com.hmss.springbootserver.DTOs.UserDTO;
import com.hmss.springbootserver.entities.Appointment;
import com.hmss.springbootserver.entities.Patient;
import com.hmss.springbootserver.entities.User;
import com.hmss.springbootserver.mappers.AppointmentMapper;
import com.hmss.springbootserver.mappers.PatientMapper;
import com.hmss.springbootserver.mappers.UserMapper;
import com.hmss.springbootserver.repositories.AppointmentRepository;
import com.hmss.springbootserver.repositories.PatientRepository;
import com.hmss.springbootserver.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthService {
    private final UserRepository userRepository;
    private final PatientRepository patientRepository;

    private final AppointmentRepository appointmentRepository;

    @Autowired
    public AuthService(UserRepository userRepository, PatientRepository patientRepository, AppointmentRepository appointmentRepository) {
        this.userRepository = userRepository;
        this.patientRepository = patientRepository;
        this.appointmentRepository = appointmentRepository;
    }

    public List<User> getAllUsers(){
        var x = this.userRepository.findAll();
        return null;
    }

    public List<Patient> getAllPatients(){
        var x = this.patientRepository.findAll();
        return null;
    }

    public List<Appointment> getAllAppointments(){
        var x = this.appointmentRepository.findAll();
        //System.out.println(x.get(0).getPatient().getUser());
        return null;
    }

    public boolean createUserWithPatient(){
        var x = new User();
        x.setFirstName("Nicu");
        x.setLastName("Paleru");
        x.addPatient(new Patient());
        userRepository.save(x);
        return true;
    }

    public boolean createUser(){
        var x = new User();
        x.setFirstName("Andrei");
        x.setLastName("Dalast");
        userRepository.save(x);
        return true;
    }

    public UserDTO getFirstUser(){
        var x = this.userRepository.getById((long)1);
        var z= UserMapper.userToDTO(x);
        return z;
    }

    public UserDTO getFirstUserRestricted(){
        var x = this.userRepository.getById((long)1);
        var z = UserMapper.userToDTORestricted(x);
        return z;

    }

    public UserDTO getCustomUser(){
        var x = this.userRepository.getOne((long)1);
        var z = UserMapper.userToDtoDepth1(x);
        return z;
    }

    public PatientDTO getFirstPatient(){
        var x = this.patientRepository.getById((long)1);
        var z= PatientMapper.patientToDto(x);
        return z;
    }

    public PatientDTO getFirstPatientRestricted(){
        var x = this.patientRepository.getById((long)1);
        var z= PatientMapper.patientToDtoRestricted(x);
        return z;
    }

    public AppointmentDTO getFirstAppointment(){
        var x = this.appointmentRepository.getById((long)1);
        var z= AppointmentMapper.appointmentToDto(x);
        return z;
    }

    public AppointmentDTO getFirstAppointmentRestricted(){
        var x = this.appointmentRepository.getById((long)1);
        var z= AppointmentMapper.appointmentToDtoRestricted(x);
        return z;
    }
}
