package com.hmss.springbootserver.DTOs;

import java.util.List;

public class PatientDTOTest {
    private Long id;
    private String phone;
    private int age;
    private String bloodType;
    private int height;
    private int weight;
    private UserDTOTest user;
    private List<AppointmentDTOTest> appointments;

    private Long user_id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public UserDTOTest getUser() {
        return user;
    }

    public void setUser(UserDTOTest user) {
        this.user = user;
    }

    public List<AppointmentDTOTest> getAppointments() {
        return appointments;
    }

    public void setAppointments(List<AppointmentDTOTest> appointments) {
        this.appointments = appointments;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getBloodType() {
        return bloodType;
    }

    public void setBloodType(String bloodType) {
        this.bloodType = bloodType;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public Long getUser_id() {
        return user_id;
    }

    public void setUser_id(Long user_id) {
        this.user_id = user_id;
    }
}
