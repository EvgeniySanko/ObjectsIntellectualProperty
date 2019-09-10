package by.ipo.bo;

import by.ipo.bo.Impl.GoodsCodeBoImpl;
import by.ipo.bo.Impl.ReestrBoImpl;
import by.ipo.entity.Reestr;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service("bdDataRewriter")
public class BDDataRewriter {

    private Logger log = LogManager.getLogger(WordParser.class);

    @Autowired
    ReestrBoImpl reestrBo;
    @Autowired
    GoodsCodeBoImpl goodsCodeBo;

    public BDDataRewriter() {
    }

    public void rewrite(MultipartFile multipartFile){
        List<Reestr> reestrs = WordParser.parse(multipartFile);
        if(reestrs != null){
            goodsCodeBo.deleteAll();
            reestrBo.deleteAll();
            reestrBo.insertAllFromWord(reestrs);
            goodsCodeBo.insertAllFromWord(reestrs);
        } else {
            //TODO return model wrong data
            log.error("Wrong data");
        }
    }
}
