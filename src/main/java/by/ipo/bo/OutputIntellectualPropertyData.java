package by.ipo.bo;

import by.ipo.entity.Reestr;
import org.springframework.stereotype.Component;

@Component("outputIntellectualPropertyData")
public class OutputIntellectualPropertyData {
    private int goodsNumber;
    private String codeTnved;
    private Reestr reestr;

    public OutputIntellectualPropertyData() {
    }

    public OutputIntellectualPropertyData(int goodsNumber, String codeTnved, Reestr reestr) {
        this.goodsNumber = goodsNumber;
        this.codeTnved = codeTnved;
        this.reestr = reestr;
    }

    public int getGoodsNumber() {
        return goodsNumber;
    }

    public void setGoodsNumber(int goodsNumber) {
        this.goodsNumber = goodsNumber;
    }

    public String getCodeTnved() {
        return codeTnved;
    }

    public void setCodeTnved(String codeTnved) {
        this.codeTnved = codeTnved;
    }

    public Reestr getReestr() {
        return reestr;
    }

    public void setReestr(Reestr reestr) {
        this.reestr = reestr;
    }

    @Override
    public String toString() {
        return "OutputIntellectualPropertyData{" +
                "goodsNumber=" + goodsNumber +
                ", codeTnved='" + codeTnved + '\'' +
                ", reestr=" + reestr.toString() +
                '}' + '\'';
    }
}
