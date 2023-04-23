package com.hmss.springbootserver.entities;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "hospital")
public class Hospital { //owning side //
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    private String location;
    @ManyToMany(cascade = {CascadeType.PERSIST,CascadeType.MERGE})
    @JoinTable(
            name = "hospital_procedure",
            joinColumns = @JoinColumn(name = "hospital_id"),
            inverseJoinColumns = @JoinColumn(name = "procedure_id"))
    Set<Procedure> procedureSet = new HashSet<>();
    @OneToOne(mappedBy="hospital",cascade = CascadeType.ALL)
    private Admin admin;
    @OneToMany(mappedBy="hospital",cascade = CascadeType.ALL)
    private List<Doctor> doctors;

    public void addProcedure(Procedure procedure){
        procedureSet.add(procedure);
        procedure.getHospitalSet().add(this);
    }

    public void removeProcedure(Procedure procedure) {
        procedureSet.remove(procedure);
        procedure.getHospitalSet().remove(this);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Set<Procedure> getProcedureSet() {
        return procedureSet;
    }

    public void setProcedureSet(Set<Procedure> procedureSet) {
        this.procedureSet = procedureSet;
    }

    public Admin getAdmin() {
        return admin;
    }

    public void setAdmin(Admin admin) {
        this.admin = admin;
    }

    public List<Doctor> getDoctors() {
        return doctors;
    }

    public void setDoctors(List<Doctor> doctors) {
        this.doctors = doctors;
    }
}
