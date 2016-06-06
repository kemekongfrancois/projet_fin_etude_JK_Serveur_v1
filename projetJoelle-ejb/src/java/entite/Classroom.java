/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entite;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author KEF10
 */
@Entity
@Table(name = "classroom")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Classroom.findAll", query = "SELECT c FROM Classroom c"),
    @NamedQuery(name = "Classroom.findByNameOption", query = "SELECT c FROM Classroom c WHERE c.classroomPK.nameOption = :nameOption"),
    @NamedQuery(name = "Classroom.findByNameClass", query = "SELECT c FROM Classroom c WHERE c.classroomPK.nameClass = :nameClass"),
    @NamedQuery(name = "Classroom.findByNumberClass", query = "SELECT c FROM Classroom c WHERE c.classroomPK.numberClass = :numberClass"),
    @NamedQuery(name = "Classroom.findByEffective", query = "SELECT c FROM Classroom c WHERE c.effective = :effective")})
public class Classroom implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected ClassroomPK classroomPK;
    @Column(name = "effective")
    private Integer effective;
    @JoinColumn(name = "name_option", referencedColumnName = "name_option", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private OptionClass optionClass;
    @JoinColumn(name = "name_class", referencedColumnName = "name_class", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private TheClass theClass;
    @JoinColumn(name = "email_address", referencedColumnName = "email_address")
    @ManyToOne(optional = false)
    private Admin emailAddress;

    public Classroom() {
    }

    public Classroom(ClassroomPK classroomPK) {
        this.classroomPK = classroomPK;
    }

    public Classroom(String nameOption, String nameClass, int numberClass) {
        this.classroomPK = new ClassroomPK(nameOption, nameClass, numberClass);
    }

    public ClassroomPK getClassroomPK() {
        return classroomPK;
    }

    public void setClassroomPK(ClassroomPK classroomPK) {
        this.classroomPK = classroomPK;
    }

    public Integer getEffective() {
        return effective;
    }

    public void setEffective(Integer effective) {
        this.effective = effective;
    }

    public OptionClass getOptionClass() {
        return optionClass;
    }

    public void setOptionClass(OptionClass optionClass) {
        this.optionClass = optionClass;
    }

    public TheClass getTheClass() {
        return theClass;
    }

    public void setTheClass(TheClass theClass) {
        this.theClass = theClass;
    }

    public Admin getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(Admin emailAddress) {
        this.emailAddress = emailAddress;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (classroomPK != null ? classroomPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Classroom)) {
            return false;
        }
        Classroom other = (Classroom) object;
        if ((this.classroomPK == null && other.classroomPK != null) || (this.classroomPK != null && !this.classroomPK.equals(other.classroomPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entite.Classroom[ classroomPK=" + classroomPK + " ]";
    }
    
}
