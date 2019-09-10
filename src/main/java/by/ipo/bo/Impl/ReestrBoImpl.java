package by.ipo.bo.Impl;

import by.ipo.bo.ReestrBo;
import by.ipo.entity.GoodsCode;
import by.ipo.entity.Reestr;
import by.ipo.entity.dao.ReestrDao;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("reestrBo")
public class ReestrBoImpl implements ReestrBo {

    @Autowired
    ReestrDao reestrDao;

    private Logger log = LogManager.getLogger(ReestrBoImpl.class);

    public ArrayList<Reestr> findByGoodsCode(List<GoodsCode> goodsCodes) {
        return reestrDao.findByGoodsCode(goodsCodes);
    }

    public void deleteAll() {
        reestrDao.deleteAll();
    }

    public void insertAllFromWord(List<Reestr> reestrs) {
        reestrDao.insertAllFromWord(reestrs);
    }

}
