package by.ipo.bo;

import org.springframework.stereotype.Component;

@Component("inputIntellectualPropertyData")
public class InputIntellectualPropertyData {
    private int goodsNumber;
    private String codeTnved;

    public InputIntellectualPropertyData() {
    }

    public InputIntellectualPropertyData(int goodsNumber, String codeTnved) {
        this.goodsNumber = goodsNumber;
        this.codeTnved = codeTnved;
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

    @Override
    public String toString() {
        return "InputIntellectualPropertyData{" +
                "goodsNumber=" + goodsNumber +
                ", codeTnved='" + codeTnved + '\'' +
                '}';
    }
}
