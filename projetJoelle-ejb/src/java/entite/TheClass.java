/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entite;

import java.io.Serializable;
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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author KEF10
 */
@Entity
@Table(name = "the_class")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TheClass.findAll", query = "SELECT t FROM TheClass t"),
    @NamedQuery(name = "TheClass.findByNameClass", query = "SELECT t FROM TheClass t WHERE t.nameClass = :nameClass"),
    @NamedQuery(name = "TheClass.findBySchoolFees", query = "SELECT t FROM TheClass t WHERE t.schoolFees = :schoolFees")})
public class TheClass implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 254)
    @Column(name = "name_class")
    private String nameClass;
    @Column(name = "school_fees")
    private Integer schoolFees;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "theClass")
    private List<OptionAndClasse> optionAndClasseList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "theClass")
    private List<Classroom> classroomList;

    public TheClass() {
    }

    public TheClass(String nameClass) {
        this.nameClass = nameClass;
    }

    public String getNameClass() {
        return nameClass;
    }

    public void setNameClass(String nameClass) {
        this.nameClass = nameClass;
    }

    public Integer getSchoolFees() {
        return schoolFees;
    }

    public void setSchoolFees(Integer schoolFees) {
        this.schoolFees = schoolFees;
    }

    @XmlTransient
    public List<OptionAndClasse> getOptionAndClasseList() {
        return optionAndClasseList;
    }

    public void setOptionAndClasseList(List<OptionAndClasse> optionAndClasseList) {
        this.optionAndClasseList = optionAndClasseList;
    }

    @XmlTransient
    public List<Classroom> getClassroomList() {
        return classroomList;
    }

    public void setClassroomList(List<Classroom> classroomList) {
        this.classroomList = classroomList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (nameClass != null ? nameClass.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TheClass)) {
            return false;
        }
        TheClass other = (TheClass) object;
        if ((this.nameClass == null && other.nameClass != null) || (this.nameClass != null && !this.nameClass.equals(other.nameClass))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entite.TheClass[ nameClass=" + nameClass + " ]";
    }
    
}
