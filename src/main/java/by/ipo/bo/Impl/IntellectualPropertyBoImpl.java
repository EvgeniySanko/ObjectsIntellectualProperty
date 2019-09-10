package by.ipo.bo.Impl;

import by.ipo.bo.*;
import by.ipo.entity.GoodsCode;
import by.ipo.entity.Reestr;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.apache.logging.log4j.*;

import java.util.ArrayList;

@Service("intellectualPropertyBo")
public class IntellectualPropertyBoImpl implements IntellectualProppertyBo {
    @Autowired
    GoodsCodeBo goodsCodeBo;
    @Autowired
    ReestrBo reestrBo;
    @Autowired
    OutputIntellectualPropertyData iProp;

    private Logger log = LogManager.getLogger(IntellectualPropertyBoImpl.class);

    public void newOutputIntellectualPropertyData() {
        this.iProp = new OutputIntellectualPropertyData();
    }

    public ArrayList<OutputIntellectualPropertyData> getIntellectualProperty(ArrayList<InputIntellectualPropertyData> inputIPropData) {
        ArrayList<OutputIntellectualPropertyData> iPropList = null;
        if (inputIPropData == null || inputIPropData.isEmpty()) {
            log.info("Input data is null ");
            return null;
        } else {
            iPropList = new ArrayList<OutputIntellectualPropertyData>(inputIPropData.size());
            StringBuilder tnved = new StringBuilder();
            for (InputIntellectualPropertyData inputData : inputIPropData) {
                ArrayList<GoodsCode> goodsCodes = goodsCodeBo.findByCodeTnved(inputData.getCodeTnved());
                ArrayList<Reestr> reestrList = reestrBo.findByGoodsCode(goodsCodes);
                for (int i = 0; i < goodsCodes.size(); i++) {
                    this.newOutputIntellectualPropertyData();
                    iProp.setGoodsNumber(inputData.getGoodsNumber());
                    iProp.setCodeTnved(goodsCodes.get(i).getTnvedCode());
                    iProp.setReestr(reestrList.get(i));
                    iPropList.add(iProp);
                    tnved.append("Good Number ").append(inputData.getGoodsNumber()).append("*** Reestr id ").append(iProp.getReestr().getId()).append("***");
                }
            }
            log.info("Return data: " + tnved.toString());
            return iPropList;
        }
    }
}
