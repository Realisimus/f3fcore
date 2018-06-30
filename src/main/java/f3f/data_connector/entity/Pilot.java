package f3f.data_connector.entity;

import com.sun.org.apache.xpath.internal.operations.Bool;
import javafx.beans.DefaultProperty;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "PILOTS")
public class Pilot {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Integer id;

    @NotNull
    @Size(max = 16)
    @Column(name = "LOGIN")
    private String login;

    @Column(name = "FIRST_NAME")
    private String first_name;

    @Column(name = "LAST_NAME")
    private String last_name;

    @Column(name = "LICENSE")
    private String license;

    @Column(name = "EMAIL")
    private String email;

    @Column(name = "PHONE")
    private String phone;

    @Column(name = "CITY")
    private String city;

    @NotNull
    @Column(name = "IS_USER")
    private Boolean is_user;

    @NotNull
    @Column(name = "IS_ACTIVE")
    private Boolean is_active;

    public Pilot() {}

    public Pilot(Integer id, String login, String first_name, String last_name, String license, String email, String phone, String city) {
        this.id = id;
        this.login = login;
        this.first_name = first_name;
        this.last_name = last_name;
        this.license = license;
        this.email = email;
        this.phone = phone;
        this.city = city;
        this.is_user = false;
        this.is_active = true;
    }

    public Pilot(Integer id, String login, String first_name, String last_name, String license, String email, String phone, String city, Boolean is_user, Boolean is_active) {
        this.id = id;
        this.login = login;
        this.first_name = first_name;
        this.last_name = last_name;
        this.license = license;
        this.email = email;
        this.phone = phone;
        this.city = city;
        this.is_user = is_user;
        this.is_active = is_active;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getLicense() {
        return license;
    }

    public void setLicense(String license) {
        this.license = license;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Boolean getIs_user() {
        return is_user;
    }

    public void setIs_user(Boolean is_user) {
        this.is_user = is_user;
    }

    public Boolean getIs_active() {
        return is_active;
    }

    public void setIs_active(Boolean is_active) {
        this.is_active = is_active;
    }
}
