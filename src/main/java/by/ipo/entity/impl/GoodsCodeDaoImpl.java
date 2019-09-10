package by.ipo.entity.impl;

import by.ipo.entity.GoodsCode;
import by.ipo.entity.Reestr;
import by.ipo.entity.dao.GoodsCodeDao;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Transactional
@Repository("goodsCodeDao")
public class GoodsCodeDaoImpl implements GoodsCodeDao {

    private SessionFactory sessionFactory;

    private Logger log = LogManager.getLogger(GoodsCodeDaoImpl.class);

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    @Resource(name = "sessionFactory")
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Transactional(readOnly = true)
    public ArrayList<GoodsCode> findByCodeTnved(String codeTnved) {
        log.info("Find by input codeTNVED = " + codeTnved);
        ArrayList<GoodsCode> list = (ArrayList<GoodsCode>) sessionFactory.getCurrentSession().getNamedQuery("GoodsCode.findByCodeTnved")
                .setParameter("code", codeTnved).list();
        return list;
    }

    @Transactional
    public void deleteAll() {
        sessionFactory.getCurrentSession().getNamedQuery("GoodsCode.deleteAll").executeUpdate();
    }

    public void insertAllFromWord(List<Reestr> reestrs) {
        Session session = sessionFactory.getCurrentSession();
        for (Reestr reestr: reestrs) {
            List<GoodsCode> goodsCodes = reestr.goodsCodes;
            for(GoodsCode goodCode : goodsCodes){
                session.getNamedQuery("GoodsCode.insertAllFromWord")
                        .setParameter("reestrId", reestr.getId()).setParameter("tnvedCode", goodCode.getTnvedCode());
                session.save(goodCode);
            }

        }
    }
}
