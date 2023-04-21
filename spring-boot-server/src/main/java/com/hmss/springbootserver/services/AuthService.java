package com.hmss.springbootserver.services;

import com.hmss.springbootserver.DTOs.AppointmentDTO;
import com.hmss.springbootserver.DTOs.PatientDTO;
import com.hmss.springbootserver.DTOs.UserDTO;
import com.hmss.springbootserver.entities.*;
import com.hmss.springbootserver.mappers.AppointmentMapper;
import com.hmss.springbootserver.mappers.PatientMapper;
import com.hmss.springbootserver.mappers.UserMapper;
import com.hmss.springbootserver.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthService {
    private final UserRepository userRepository;
    private final PatientRepository patientRepository;

    private final AppointmentRepository appointmentRepository;

    private final HospitalRepository hospitalRepository;
    private final ProcedureRepository procedureRepository;
    @Autowired
    public AuthService(UserRepository userRepository, PatientRepository patientRepository, AppointmentRepository appointmentRepository, HospitalRepository hospitalRepository, ProcedureRepository procedureRepository) {
        this.userRepository = userRepository;
        this.patientRepository = patientRepository;
        this.appointmentRepository = appointmentRepository;
        this.hospitalRepository = hospitalRepository;
        this.procedureRepository = procedureRepository;
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

    public Hospital getHospitals(){
        var x = this.hospitalRepository.findAll();
        //var z = x.get(0).getSpecialitySet().size();
        return null;
    }

    public Procedure getProcedures(){
        var x = this.procedureRepository.findAll();
        var z = x.get(0).getHospitalSet().size();
        return null;
    }

    public boolean addProcedureToHospital(){
        var x = this.hospitalRepository.getById((long)1);
        x.getLocation();
        var y = new Procedure();
        y.setName("ProdceduraNUme");
        x.addProcedure(y);
        this.hospitalRepository.save(x);
        return true;
    }

    public boolean addHospitalToProcedure(){
        var x = this.procedureRepository.getById((long)4);
        var y = new Hospital();
        y.setLocation("Mamaia");
        x.addHospital(y);
        this.procedureRepository.save(x);
        return true;
    }

    public boolean removeProcedureFromHospital(){
        var x = this.hospitalRepository.getById((long)1);
        var y = this.procedureRepository.getById((long)4);
        x.removeProcedure(y);
        this.hospitalRepository.save(x);
        return true;
    }
}
