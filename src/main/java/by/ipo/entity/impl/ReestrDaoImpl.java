package by.ipo.entity.impl;

import by.ipo.entity.GoodsCode;
import by.ipo.entity.Reestr;
import by.ipo.entity.dao.ReestrDao;
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
@Repository("reestrDao")
public class ReestrDaoImpl implements ReestrDao {

    @Resource(name = "sessionFactory")
    private SessionFactory sessionFactory;

    private Logger log = LogManager.getLogger(ReestrDaoImpl.class);

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Transactional(readOnly = true)
    public Reestr findById(int id) {
        return (Reestr) sessionFactory.getCurrentSession().getNamedQuery("Reestr.findById").setParameter("id", id).uniqueResult();
    }

    @Transactional(readOnly = true)
    public List<Reestr> findAll() {
        return sessionFactory.getCurrentSession().createQuery("from Reestr r").list();
    }

    public ArrayList<Reestr> findByGoodsCode(List<GoodsCode> goodsCodes) {
        ArrayList<Reestr> reestr = new ArrayList<Reestr>();
        for (GoodsCode goodsCode : goodsCodes) {
            int reestrId = goodsCode.getReestrId();
            reestr.add((Reestr) sessionFactory.getCurrentSession().getNamedQuery("Reestr.findById").setParameter("id", reestrId).uniqueResult());
        }
        return reestr;
    }
    @Transactional
    public void deleteAll() {
        sessionFactory.getCurrentSession().getNamedQuery("Reestr.deleteAll").executeUpdate();
    }

    public void insertAllFromWord(List<Reestr> reestrs) {
        Session session = sessionFactory.getCurrentSession();
        for (Reestr reestr : reestrs) {
            session.getNamedQuery("Reestr.insertAllFromWord")
                    .setParameter("id", reestr.getId()).setParameter("regNumber", reestr.getRegNumber())
                    .setParameter("lableText", reestr.getLableText()).setParameter("lablePicture", reestr.getLablePicture())
                    .setParameter("docNumber", reestr.getDocNumber()).setParameter("name", reestr.getName())
                    .setParameter("fullName", reestr.getFullName()).setParameter("dovFullName", reestr.getDovFullName())
                    .setParameter("dateOff", reestr.getDateOff()).setParameter("note", reestr.getNote());
            session.save(reestr);
        }
    }
}
