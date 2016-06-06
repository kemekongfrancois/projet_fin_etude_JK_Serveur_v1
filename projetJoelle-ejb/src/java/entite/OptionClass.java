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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "option_class")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "OptionClass.findAll", query = "SELECT o FROM OptionClass o"),
    @NamedQuery(name = "OptionClass.findByNameOption", query = "SELECT o FROM OptionClass o WHERE o.nameOption = :nameOption")})
public class OptionClass implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 254)
    @Column(name = "name_option")
    private String nameOption;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "optionClass")
    private List<OptionAndClasse> optionAndClasseList;
    @JoinColumn(name = "email_address", referencedColumnName = "email_address")
    @ManyToOne(optional = false)
    private Admin emailAddress;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "optionClass")
    private List<Classroom> classroomList;

    public OptionClass() {
    }

    public OptionClass(String nameOption) {
        this.nameOption = nameOption;
    }

    public String getNameOption() {
        return nameOption;
    }

    public void setNameOption(String nameOption) {
        this.nameOption = nameOption;
    }

    @XmlTransient
    public List<OptionAndClasse> getOptionAndClasseList() {
        return optionAndClasseList;
    }

    public void setOptionAndClasseList(List<OptionAndClasse> optionAndClasseList) {
        this.optionAndClasseList = optionAndClasseList;
    }

    public Admin getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(Admin emailAddress) {
        this.emailAddress = emailAddress;
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
        hash += (nameOption != null ? nameOption.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof OptionClass)) {
            return false;
        }
        OptionClass other = (OptionClass) object;
        if ((this.nameOption == null && other.nameOption != null) || (this.nameOption != null && !this.nameOption.equals(other.nameOption))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entite.OptionClass[ nameOption=" + nameOption + " ]";
    }
    
}
