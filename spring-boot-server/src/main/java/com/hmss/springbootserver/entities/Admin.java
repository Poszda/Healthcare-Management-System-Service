package com.hmss.springbootserver.entities;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

@Entity
public class Admin {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @JsonIgnore
    @OneToOne()
    @JoinColumn(name = "user_id", foreignKey = @ForeignKey(name = "FK_USER_ADMIN"))
    private User user;
    @JsonIgnore
    @OneToOne()
    @JoinColumn(name = "hospital_id", foreignKey = @ForeignKey(name = "FK_HOSPITAL_ADMIN"))
    private Hospital hospital;


    public Admin() {
    }

    public Admin(Long id, Hospital hospital, User user) {
        this.id = id;
        this.hospital = hospital;
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Hospital getHospital() {
        return hospital;
    }

    public void setHospital(Hospital hospital) {
        this.hospital = hospital;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
