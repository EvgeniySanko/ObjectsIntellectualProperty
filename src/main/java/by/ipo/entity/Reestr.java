package by.ipo.entity;

import javax.persistence.*;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@NamedQueries({
        @NamedQuery(name = "Reestr.findById", query="select r from Reestr r where r.id = :id"),
        @NamedQuery(name = "Reestr.deleteAll", query = "delete from Reestr r"),
        @NamedQuery(name = "Reestr.insertAllFromWord", query = "insert into Reestr (id, regNumber, lableText, lablePicture, docNumber, name, fullName, dovFullName, dateOff, note)" +
                " select :id, :regNumber, :lableText, :lablePicture, :docNumber, :name, :fullName, :dovFullName, :dateOff, :note from Reestr")
})

@Entity
@Table(name = "reestr")
public class Reestr {
    private int id;
    private String regNumber;
    private String lableText;
    private byte[] lablePicture;
    private String docNumber;
    private String name;
    private String fullName;
    private String dovFullName;
    private Date dateOff;
    private String note;

    public List<GoodsCode> goodsCodes;

    public Reestr() {
    }

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Column(name = "regNomer")
    public String getRegNumber() {
        return regNumber;
    }

    public void setRegNumber(String regNumber) {
        this.regNumber = regNumber;
    }

    @Column(name = "lableText")
    public String getLableText() {
        return lableText;
    }

    public void setLableText(String lableText) {
        this.lableText = lableText;
    }

    @Column(name = "lablePicture")
    public byte[] getLablePicture() {
        return lablePicture;
    }

    public void setLablePicture(byte[] lablePicture) {
        this.lablePicture = lablePicture;
    }

    @Column(name = "docNumber")
    public String getDocNumber() {
        return docNumber;
    }

    public void setDocNumber(String docNumber) {
        this.docNumber = docNumber;
    }

    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "fullName")
    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    @Column(name = "dovFullName")
    public String getDovFullName() {
        return dovFullName;
    }

    public void setDovFullName(String dovFullName) {
        this.dovFullName = dovFullName;
    }

    @Column(name = "dateOff")
    @Temporal(TemporalType.DATE)
    public Date getDateOff() {
        return dateOff;
    }

    public void setDateOff(Date dateOff) {
        this.dateOff = dateOff;
    }

    @Column(name = "note")
    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    @Override
    public String toString() {
        return "Reestr{" +
                "id=" + id +
                ", regNumber='" + regNumber + '\'' +
                ", lableText='" + lableText + '\'' +
                ", lablePicture=" + Arrays.toString(lablePicture) +
                ", docNumber='" + docNumber + '\'' +
                ", name='" + name + '\'' +
                ", fullName='" + fullName + '\'' +
                ", dovFullName='" + dovFullName + '\'' +
                ", dateOff=" + dateOff +
                ", note='" + note + '\'' +
                '}';
    }
}
