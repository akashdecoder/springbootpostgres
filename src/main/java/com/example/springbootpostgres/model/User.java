package com.example.springbootpostgres.model;

import javax.persistence.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue (strategy=GenerationType.IDENTITY)
    private long user_id;

    @Column(nullable = false, length = 64)
    private String firstname;

    @Column(nullable = false, length = 64)
    private String lastname;

    @Column(nullable = false, length = 64)
    private String email;

    @Column(nullable = false, unique = true, length = 64)
    private String username;

    @Column(nullable = false, length = 64)
    private String contact;

    @Column(nullable = false, length = 64)
    private String password;

    @Column(length = 64)
    private String resetpasswordtoken;

    @Column(length = 64)
    private String branch;

    @Column(length = 10)
    private String year;

    @Column(length = 10)
    private String semester;

    @Column(nullable = false, length = 45)
    private String role;

    @Column(nullable = false)
    private int enabled;

//    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
//    @JoinTable(
//            name = "user_roles",
//            joinColumns = @JoinColumn(name = "user_id"),
//            inverseJoinColumns = @JoinColumn(name = "role_id")
//    )
//    private Set<Roles> roles = new HashSet<>();

    public User() {
    }

    public User(long user_id, String firstname, String lastname, String email, String username, String contact, String password, String resetpasswordtoken, String branch, String year, String semester, String role, int enabled) {
        this.user_id = user_id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.username = username;
        this.contact = contact;
        this.password = password;
        this.resetpasswordtoken = resetpasswordtoken;
        this.branch = branch;
        this.year = year;
        this.semester = semester;
        this.role = role;
        this.enabled = enabled;
    }

    public long getUser_id() {
        return user_id;
    }

    public void setUser_id(long user_id) {
        this.user_id = user_id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getResetpasswordtoken() {
        return resetpasswordtoken;
    }

    public void setResetpasswordtoken(String resetpasswordtoken) {
        this.resetpasswordtoken = resetpasswordtoken;
    }

    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getSemester() {
        return semester;
    }

    public void setSemester(String semester) {
        this.semester = semester;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public int getEnabled() {
        return enabled;
    }

    public void setEnabled(int enabled) {
        this.enabled = enabled;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    //    public Set<Roles> getRoles() {
//        return roles;
//    }
//
//    public void setRoles(Set<Roles> roles) {
//        this.roles = roles;
//    }
//
//    public void addRole(Roles role) {
//        this.roles.add(role);
//    }

//    public boolean hasRole(String roleName) {
//        Iterator<Roles> iterator = roles.iterator();
//        while(iterator.hasNext()) {
//            Roles roles = iterator.next();
//            if(roles.getRole_name().equals(roleName)) {
//                return true;
//            }
//        }
//        return false;
//    }
}
