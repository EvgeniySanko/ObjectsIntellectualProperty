package by.ipo.bo.Impl;

import by.ipo.bo.GoodsCodeBo;
import by.ipo.entity.GoodsCode;
import by.ipo.entity.Reestr;
import by.ipo.entity.dao.GoodsCodeDao;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("goodsCodeBo")
public class GoodsCodeBoImpl implements GoodsCodeBo {

    @Autowired
    GoodsCodeDao goodsCodeDao;

    private Logger log = LogManager.getLogger(GoodsCodeBoImpl.class);

    public ArrayList<GoodsCode> findByCodeTnved(String codeTnved) {
        return goodsCodeDao.findByCodeTnved(codeTnved);
    }

    public void deleteAll() {
        goodsCodeDao.deleteAll();
    }

    public void insertAllFromWord(List<Reestr> reestrs) {
        goodsCodeDao.insertAllFromWord(reestrs);
    }
}
