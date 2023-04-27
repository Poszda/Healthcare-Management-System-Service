package com.hmss.springbootserver.services;

import com.hmss.springbootserver.DTOs.AppointmentDTOTest;
import com.hmss.springbootserver.DTOs.PatientDTOTest;
import com.hmss.springbootserver.DTOs.UserDTOTest;
import com.hmss.springbootserver.entities.*;
import com.hmss.springbootserver.mappers.AppointmentMapperTest;
import com.hmss.springbootserver.mappers.PatientMapper;
import com.hmss.springbootserver.mappers.PatientMapperTest;
import com.hmss.springbootserver.mappers.UserMapperTest;
import com.hmss.springbootserver.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TestService {
    private final UserRepository userRepository;
    private final PatientRepository patientRepository;

    private final AppointmentRepository appointmentRepository;

    private final HospitalRepository hospitalRepository;
    private final ProcedureRepository procedureRepository;

    private final SpecialityRepository specialityRepository;

    private final DiagnosticRepository diagnosticRepository;

    private final MedicationRepository medicationRepository;

    private final AdminRepository adminRepository;
    private final DoctorRepository doctorRepository;
    @Autowired
    public TestService(UserRepository userRepository, PatientRepository patientRepository, AppointmentRepository appointmentRepository, HospitalRepository hospitalRepository, ProcedureRepository procedureRepository, SpecialityRepository specialityRepository, DiagnosticRepository diagnosticRepository, MedicationRepository medicationRepository, AdminRepository adminRepository, DoctorRepository doctorRepository) {
        this.userRepository = userRepository;
        this.patientRepository = patientRepository;
        this.appointmentRepository = appointmentRepository;
        this.hospitalRepository = hospitalRepository;
        this.procedureRepository = procedureRepository;
        this.specialityRepository = specialityRepository;
        this.diagnosticRepository = diagnosticRepository;
        this.medicationRepository = medicationRepository;
        this.adminRepository = adminRepository;
        this.doctorRepository = doctorRepository;
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
        //System.out.println(x.get(1).getDiagnostic()); show null if doesn t exist , nice
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

    public UserDTOTest getFirstUser(){
        var x = this.userRepository.getById((long)1);
        var z= UserMapperTest.userToDTO(x);
        return z;
    }

    public UserDTOTest getFirstUserRestricted(){
        var x = this.userRepository.getById((long)1);
        var z = UserMapperTest.userToDTORestricted(x);
        return z;

    }

    public UserDTOTest getCustomUser(){
        var x = this.userRepository.getOne((long)1);
        var z = UserMapperTest.userToDtoDepth1(x);
        return z;
    }

    public Object getFirstPatient(){
        var x = this.patientRepository.getById((long)1);
        //var z= PatientMapperTest.patientToDto(x);
        var z = PatientMapper.INSTANCE.patientToPatientDTO(x);
        return z;
    }

    public PatientDTOTest getFirstPatientRestricted(){
        var x = this.patientRepository.getById((long)1);
        var z= PatientMapperTest.patientToDtoRestricted(x);
        return z;
    }

    public AppointmentDTOTest getFirstAppointment(){
        var x = this.appointmentRepository.getById((long)1);
        var z= AppointmentMapperTest.appointmentToDto(x);
        return z;
    }

    public AppointmentDTOTest getFirstAppointmentRestricted(){
        var x = this.appointmentRepository.getById((long)1);
        var z= AppointmentMapperTest.appointmentToDtoRestricted(x);
        return z;
    }

    public Hospital getHospitals(){
        var x = this.hospitalRepository.findAll();
        //var z = x.get(0).getSpecialitySet().size();
        return null;
    }

    public Procedure getProcedures(){
        var x = this.procedureRepository.findAll();
        //var z = x.get(0).getHospitalSet().size();
        //var h = x.get(0).getSpeciality().getName();
        return null;
    }

    public Speciality getSpecialities(){
        var x = specialityRepository.findAll();
        //var z = x.get(0).getProcedures().size();
        return null;
    }

    public Diagnostic getDiagnostics(){
        var x = diagnosticRepository.findAll();
        //var z = x.get(0).getAppointment().getPatient();
        return null;
    }

    public Medication getMedications(){
        var x = medicationRepository.findAll();
        //var z = x.get(0).getDiagnostic().getName();
        return null;
    }
    public Admin getAdmins() {
        var x = adminRepository.findAll();
        //var z = x.get(0).getHospital().getLocation();
        return null;
    }

    public Doctor getDoctors(){
        var x = doctorRepository.findAll();
        //var z = x.get(0).getUser().getFirstName();
        return null;
    }

    public void customQuery(){
        var x = this.specialityRepository.smthCustom();
        System.out.println(x);
    }

    public boolean addProcedureToHospital(){
        var x = this.hospitalRepository.getById((long)1);
        x.getLocation();
        var y = new Procedure();
        y.setName("ProdceduraNUme");
        y.setPrice(100);
        x.addProcedure(y);
        this.hospitalRepository.save(x);
        return true;
    }

    public boolean addHospitalToProcedure(){
        var x = this.procedureRepository.getById((long)4);
        System.out.println(x);
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

    public void conversion1() {
        var x = new UserDTOTest();
        var y = new PatientDTOTest();
        x.setFirstName("Mihai");
        y.setPhone("07722229871");
        x.setPatient(y);
        var z = UserMapperTest.userDTOtoEntity(x);
        var hei = "gei";

    }

//    public void cascadeTypeTestPersist(){
//        //PERSIST
//        var x = new Diagnostic();
//        var y = new Medication();
//        var z = new Medication();
//        var list = new ArrayList<Medication>();
//
//        y.setName("Nurofen");
//        y.setDiagnostic(x);
//
//        z.setName("Paracetamol");
//        z.setDiagnostic(x);
//
//        list.add(y);
//        list.add(z);
//
//        x.setName("Diagnostic1");
//        x.setDescription("Description1");
//        x.setMedications(list);
//        diagnosticRepository.save(x);
//    }
//
//    public void cascadeTypeTestRemove(){
//        //REMOVE
//        var x = diagnosticRepository.getOne((long)1);
//        x.removeAllMedications();
//        diagnosticRepository.delete(x);
//    }

}
