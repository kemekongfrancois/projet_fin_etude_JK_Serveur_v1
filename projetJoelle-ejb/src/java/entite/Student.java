/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entite;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author KEF10
 */
@Entity
@Table(name = "student")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Student.findAll", query = "SELECT s FROM Student s"),
    @NamedQuery(name = "Student.findByEmailAddress", query = "SELECT s FROM Student s WHERE s.emailAddress = :emailAddress"),
    @NamedQuery(name = "Student.findByPathFormerBulletin", query = "SELECT s FROM Student s WHERE s.pathFormerBulletin = :pathFormerBulletin"),
    @NamedQuery(name = "Student.findByIdentificationNumber", query = "SELECT s FROM Student s WHERE s.identificationNumber = :identificationNumber"),
    @NamedQuery(name = "Student.findByNamePerson", query = "SELECT s FROM Student s WHERE s.namePerson = :namePerson"),
    @NamedQuery(name = "Student.findBySurnamePerson", query = "SELECT s FROM Student s WHERE s.surnamePerson = :surnamePerson"),
    @NamedQuery(name = "Student.findByDateOfBirth", query = "SELECT s FROM Student s WHERE s.dateOfBirth = :dateOfBirth"),
    @NamedQuery(name = "Student.findByPassword", query = "SELECT s FROM Student s WHERE s.password = :password"),
    @NamedQuery(name = "Student.findByPathPhoto", query = "SELECT s FROM Student s WHERE s.pathPhoto = :pathPhoto")})
public class Student implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 254)
    @Column(name = "email_address")
    private String emailAddress;
    @Size(max = 254)
    @Column(name = "path_former_bulletin")
    private String pathFormerBulletin;
    @Column(name = "identification_number")
    private Integer identificationNumber;
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
    @JoinColumns({
        @JoinColumn(name = "name_option", referencedColumnName = "name_option"),
        @JoinColumn(name = "name_class", referencedColumnName = "name_class")})
    @ManyToOne
    private OptionAndClasse optionAndClasse;
    @JoinColumn(name = "sup_email_address", referencedColumnName = "email_address")
    @ManyToOne
    private SuperAdmin supEmailAddress;
    @JoinColumn(name = "adm_email_address", referencedColumnName = "email_address")
    @ManyToOne
    private Admin admEmailAddress;

    public Student() {
    }

    public Student(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getPathFormerBulletin() {
        return pathFormerBulletin;
    }

    public void setPathFormerBulletin(String pathFormerBulletin) {
        this.pathFormerBulletin = pathFormerBulletin;
    }

    public Integer getIdentificationNumber() {
        return identificationNumber;
    }

    public void setIdentificationNumber(Integer identificationNumber) {
        this.identificationNumber = identificationNumber;
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

    public OptionAndClasse getOptionAndClasse() {
        return optionAndClasse;
    }

    public void setOptionAndClasse(OptionAndClasse optionAndClasse) {
        this.optionAndClasse = optionAndClasse;
    }

    public SuperAdmin getSupEmailAddress() {
        return supEmailAddress;
    }

    public void setSupEmailAddress(SuperAdmin supEmailAddress) {
        this.supEmailAddress = supEmailAddress;
    }

    public Admin getAdmEmailAddress() {
        return admEmailAddress;
    }

    public void setAdmEmailAddress(Admin admEmailAddress) {
        this.admEmailAddress = admEmailAddress;
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
        if (!(object instanceof Student)) {
            return false;
        }
        Student other = (Student) object;
        if ((this.emailAddress == null && other.emailAddress != null) || (this.emailAddress != null && !this.emailAddress.equals(other.emailAddress))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entite.Student[ emailAddress=" + emailAddress + " ]";
    }
    
}
