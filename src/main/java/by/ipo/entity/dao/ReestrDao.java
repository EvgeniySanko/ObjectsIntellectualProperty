package by.ipo.entity.dao;

import by.ipo.entity.GoodsCode;
import by.ipo.entity.Reestr;

import java.util.ArrayList;
import java.util.List;

public interface ReestrDao {
    ArrayList<Reestr> findByGoodsCode(List<GoodsCode> goodsCodes);
    void deleteAll();
    void insertAllFromWord(List<Reestr> map);
}
