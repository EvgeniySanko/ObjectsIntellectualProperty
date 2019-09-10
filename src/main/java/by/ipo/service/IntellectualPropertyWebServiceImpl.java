package by.ipo.service;

import by.ipo.bo.Impl.IntellectualPropertyBoImpl;
import by.ipo.bo.InputIntellectualPropertyData;
import by.ipo.bo.OutputIntellectualPropertyData;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import javax.jws.WebService;
import java.util.ArrayList;
@WebService(endpointInterface = "by.ipo.service.IntellectualPropertyWebService")
public class IntellectualPropertyWebServiceImpl implements IntellectualPropertyWebService {
    @Autowired
    IntellectualPropertyBoImpl intellectualPropertyBo;

    private Logger log = LogManager.getLogger(IntellectualPropertyWebServiceImpl.class);

    public ArrayList<OutputIntellectualPropertyData> sendOutputData(ArrayList<InputIntellectualPropertyData> inputIPropData) {
        log.info("Send data: " );
        return intellectualPropertyBo.getIntellectualProperty(inputIPropData);
    }
}
