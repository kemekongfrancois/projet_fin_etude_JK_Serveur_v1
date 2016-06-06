/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entite;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author KEF10
 */
@Entity
@Table(name = "super_admin")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "SuperAdmin.findAll", query = "SELECT s FROM SuperAdmin s"),
    @NamedQuery(name = "SuperAdmin.findByEmailAddress", query = "SELECT s FROM SuperAdmin s WHERE s.emailAddress = :emailAddress"),
    @NamedQuery(name = "SuperAdmin.findByNamePerson", query = "SELECT s FROM SuperAdmin s WHERE s.namePerson = :namePerson"),
    @NamedQuery(name = "SuperAdmin.findBySurnamePerson", query = "SELECT s FROM SuperAdmin s WHERE s.surnamePerson = :surnamePerson"),
    @NamedQuery(name = "SuperAdmin.findByDateOfBirth", query = "SELECT s FROM SuperAdmin s WHERE s.dateOfBirth = :dateOfBirth"),
    @NamedQuery(name = "SuperAdmin.findByPassword", query = "SELECT s FROM SuperAdmin s WHERE s.password = :password"),
    @NamedQuery(name = "SuperAdmin.findByPathPhoto", query = "SELECT s FROM SuperAdmin s WHERE s.pathPhoto = :pathPhoto")})
public class SuperAdmin implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 254)
    @Column(name = "email_address")
    private String emailAddress;
    @Size(max = 254)
    @Column(name = "name_person")
    private String namePerson;
    @Size(max = 254)
    @Column(name = "surname_person")
    private String surnamePerson;
    @Column(name = "date_of_birth")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateOfBirth;
    @Size(max = 254)
    @Column(name = "password")
    private String password;
    @Size(max = 254)
    @Column(name = "path_photo")
    private String pathPhoto;
    @OneToMany(mappedBy = "supEmailAddress")
    private List<Student> studentList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "supEmailAddress")
    private List<Admin> adminList;

    public SuperAdmin() {
    }

    public SuperAdmin(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getNamePerson() {
        return namePerson;
    }

    public void setNamePerson(String namePerson) {
        this.namePerson = namePerson;
    }

    public String getSurnamePerson() {
        return surnamePerson;
    }

    public void setSurnamePerson(String surnamePerson) {
        this.surnamePerson = surnamePerson;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPathPhoto() {
        return pathPhoto;
    }

    public void setPathPhoto(String pathPhoto) {
        this.pathPhoto = pathPhoto;
    }

    @XmlTransient
    public List<Student> getStudentList() {
        return studentList;
    }

    public void setStudentList(List<Student> studentList) {
        this.studentList = studentList;
    }

    @XmlTransient
    public List<Admin> getAdminList() {
        return adminList;
    }

    public void setAdminList(List<Admin> adminList) {
        this.adminList = adminList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (emailAddress != null ? emailAddress.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SuperAdmin)) {
            return false;
        }
        SuperAdmin other = (SuperAdmin) object;
        if ((this.emailAddress == null && other.emailAddress != null) || (this.emailAddress != null && !this.emailAddress.equals(other.emailAddress))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entite.SuperAdmin[ emailAddress=" + emailAddress + " ]";
    }
    
}
