package com.system.billing.backend.client;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table (name = "clients")
public class Client {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;

    @Column (nullable = false, length = 150)
    private String name;

    @Column (nullable = false, unique = true, length = 30)
    private String nit;

    @Column (nullable = false, length = 150)
    private String email;

    @Column (length = 30)
    private String tel;

    @Column (length = 255)
    private String address;

    @Column (nullable = false)
    private Boolean active;

    @Column (name = "creation_date")
    private LocalDateTime creationDate;

    @Column (name = "update_date")
    private LocalDateTime updateDate;
    

    // Getters and setters

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getNit() { return nit; }
    public void setNit(String nit) { this.nit = nit; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getTel() { return tel; }
    public void setTel(String tel) { this.tel = tel; }
    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }
    public Boolean getActive() { return active; }
    public void setActive(Boolean active) { this.active = active; }
    public LocalDateTime getCreationDate() { return creationDate; }
    public void setCreationDate(LocalDateTime creationDate) { this.creationDate = creationDate; }
    public LocalDateTime getUpdateDate() { return updateDate; }
    public void setUpdateDate(LocalDateTime f) { this.updateDate = f; }
}
