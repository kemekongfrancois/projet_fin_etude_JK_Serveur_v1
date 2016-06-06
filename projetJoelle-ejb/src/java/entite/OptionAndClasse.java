/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entite;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author KEF10
 */
@Entity
@Table(name = "option_and_classe")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "OptionAndClasse.findAll", query = "SELECT o FROM OptionAndClasse o"),
    @NamedQuery(name = "OptionAndClasse.findByNameOption", query = "SELECT o FROM OptionAndClasse o WHERE o.optionAndClassePK.nameOption = :nameOption"),
    @NamedQuery(name = "OptionAndClasse.findByNameClass", query = "SELECT o FROM OptionAndClasse o WHERE o.optionAndClassePK.nameClass = :nameClass"),
    @NamedQuery(name = "OptionAndClasse.findByTotalStrength", query = "SELECT o FROM OptionAndClasse o WHERE o.totalStrength = :totalStrength")})
public class OptionAndClasse implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected OptionAndClassePK optionAndClassePK;
    @Column(name = "total_strength")
    private Integer totalStrength;
    @JoinColumn(name = "name_option", referencedColumnName = "name_option", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private OptionClass optionClass;
    @JoinColumn(name = "name_class", referencedColumnName = "name_class", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private TheClass theClass;
    @OneToMany(mappedBy = "optionAndClasse")
    private List<Student> studentList;

    public OptionAndClasse() {
    }

    public OptionAndClasse(OptionAndClassePK optionAndClassePK) {
        this.optionAndClassePK = optionAndClassePK;
    }

    public OptionAndClasse(String nameOption, String nameClass) {
        this.optionAndClassePK = new OptionAndClassePK(nameOption, nameClass);
    }

    public OptionAndClassePK getOptionAndClassePK() {
        return optionAndClassePK;
    }

    public void setOptionAndClassePK(OptionAndClassePK optionAndClassePK) {
        this.optionAndClassePK = optionAndClassePK;
    }

    public Integer getTotalStrength() {
        return totalStrength;
    }

    public void setTotalStrength(Integer totalStrength) {
        this.totalStrength = totalStrength;
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

    @XmlTransient
    public List<Student> getStudentList() {
        return studentList;
    }

    public void setStudentList(List<Student> studentList) {
        this.studentList = studentList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (optionAndClassePK != null ? optionAndClassePK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof OptionAndClasse)) {
            return false;
        }
        OptionAndClasse other = (OptionAndClasse) object;
        if ((this.optionAndClassePK == null && other.optionAndClassePK != null) || (this.optionAndClassePK != null && !this.optionAndClassePK.equals(other.optionAndClassePK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entite.OptionAndClasse[ optionAndClassePK=" + optionAndClassePK + " ]";
    }
    
}
