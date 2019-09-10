package by.ipo.entity;

import javax.persistence.*;
@NamedQueries({
        @NamedQuery(name = "GoodsCode.findByCodeTnved", query = "select c from GoodsCode c where c.tnvedCode like concat(:code, '%') \n" +
                "or c.tnvedCode like concat(substring(:code,1,8), '%') \n" +
                "or c.tnvedCode like concat(substring(:code,1,6), '%') \n" +
                "or c.tnvedCode like concat(substring(:code,1,4), '%')"),
        @NamedQuery(name = "GoodsCode.deleteAll", query = "delete from GoodsCode c"),
        @NamedQuery(name = "GoodsCode.insertAllFromWord", query = "insert into GoodsCode (reestrId, tnvedCode) select :reestrId, :tnvedCode from GoodsCode")
})

@Entity
@Table(name = "CodeTov")
public class GoodsCode {
    private int id;
    private int reestrId;
    private String tnvedCode;

    public GoodsCode() {
    }

    public GoodsCode(int id, int reestrId, String tnvedCode) {
        this.id = id;
        this.reestrId = reestrId;
        this.tnvedCode = tnvedCode;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Column(name = "reestr_id")
    public int getReestrId() {
        return reestrId;
    }

    public void setReestrId(int reestrId) {
        this.reestrId = reestrId;
    }

    @Column(name = "tnved_code")
    public String getTnvedCode() {
        return tnvedCode;
    }

    public void setTnvedCode(String tnvedCode) {
        this.tnvedCode = tnvedCode;
    }

    @Override
    public String toString() {
        return "CodeTov{" +
                "id=" + id +
                ", reestrId=" + reestrId +
                ", tnvedCode='" + tnvedCode + '\'' +
                '}';
    }
}
