package by.ipo.service;

import by.ipo.bo.InputIntellectualPropertyData;
import by.ipo.bo.OutputIntellectualPropertyData;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import java.util.ArrayList;
@WebService
@SOAPBinding(style = SOAPBinding.Style.DOCUMENT)
public interface IntellectualPropertyWebService {
    @WebMethod
    ArrayList<OutputIntellectualPropertyData> sendOutputData(ArrayList<InputIntellectualPropertyData> inputIPropData);
}
