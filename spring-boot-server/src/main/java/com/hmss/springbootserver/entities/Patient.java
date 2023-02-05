package com.hmss.springbootserver.entities;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

@Entity
public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    //    @JsonIgnore
    @OneToOne()
    @JoinColumn(name = "user_id", foreignKey = @ForeignKey(name = "FK_USER_PATIENT"))
    private User user;
    private String cnp;
    private String phone;


    public Patient(Long id, User user, String cnp, String phone) {
        this.id = id;
        this.user = user;
        this.cnp = cnp;
        this.phone = phone;
    }

    public Patient() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getCnp() {
        return cnp;
    }

    public void setCnp(String cnp) {
        this.cnp = cnp;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
