package by.ipo.bo;

import by.ipo.entity.GoodsCode;
import by.ipo.entity.Reestr;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public interface GoodsCodeBo {
    ArrayList<GoodsCode> findByCodeTnved(String codeTnved);
    void deleteAll();
    void insertAllFromWord(List<Reestr> reestrs);
}
