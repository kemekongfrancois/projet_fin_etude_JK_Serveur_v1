/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entite;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author KEF10
 */
@Embeddable
public class ClassroomPK implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 254)
    @Column(name = "name_option")
    private String nameOption;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 254)
    @Column(name = "name_class")
    private String nameClass;
    @Basic(optional = false)
    @NotNull
    @Column(name = "numberClass")
    private int numberClass;

    public ClassroomPK() {
    }

    public ClassroomPK(String nameOption, String nameClass, int numberClass) {
        this.nameOption = nameOption;
        this.nameClass = nameClass;
        this.numberClass = numberClass;
    }

    public String getNameOption() {
        return nameOption;
    }

    public void setNameOption(String nameOption) {
        this.nameOption = nameOption;
    }

    public String getNameClass() {
        return nameClass;
    }

    public void setNameClass(String nameClass) {
        this.nameClass = nameClass;
    }

    public int getNumberClass() {
        return numberClass;
    }

    public void setNumberClass(int numberClass) {
        this.numberClass = numberClass;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (nameOption != null ? nameOption.hashCode() : 0);
        hash += (nameClass != null ? nameClass.hashCode() : 0);
        hash += (int) numberClass;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ClassroomPK)) {
            return false;
        }
        ClassroomPK other = (ClassroomPK) object;
        if ((this.nameOption == null && other.nameOption != null) || (this.nameOption != null && !this.nameOption.equals(other.nameOption))) {
            return false;
        }
        if ((this.nameClass == null && other.nameClass != null) || (this.nameClass != null && !this.nameClass.equals(other.nameClass))) {
            return false;
        }
        if (this.numberClass != other.numberClass) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entite.ClassroomPK[ nameOption=" + nameOption + ", nameClass=" + nameClass + ", numberClass=" + numberClass + " ]";
    }
    
}
