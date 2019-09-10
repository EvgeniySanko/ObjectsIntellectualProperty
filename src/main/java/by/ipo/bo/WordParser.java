package by.ipo.bo;

import by.ipo.entity.GoodsCode;
import by.ipo.entity.Reestr;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.POIXMLDocumentPart;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.openxml4j.opc.PackagePart;
import org.apache.poi.openxml4j.opc.PackagePartName;
import org.apache.poi.xwpf.usermodel.*;
import org.springframework.web.multipart.MultipartFile;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.xml.sax.InputSource;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathFactory;

import java.io.StringReader;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import java.util.*;

public class WordParser {

    public static List<Reestr> parse(MultipartFile multipartFile) {
        Logger log = LogManager.getLogger(WordParser.class);
        try {
            XWPFDocument xdoc = new XWPFDocument(OPCPackage.open(multipartFile.getInputStream()));
            Iterator bodyElementIterator = xdoc.getBodyElementsIterator();
            List<Reestr> reestrs = null;

            while (bodyElementIterator.hasNext()) {
                IBodyElement element = (IBodyElement) bodyElementIterator.next();

                if ("TABLE".equalsIgnoreCase(element.getElementType().name())) {
                    List<XWPFTable> tableList = element.getBody().getTables();
                    reestrs = new ArrayList<Reestr>();
                    for (XWPFTable table : tableList) {
                        System.out.println("Total Number of Rows of Table:" + table.getNumberOfRows());
                        if (table.getNumberOfRows()==1)
                            continue;
                        for (int i = 2; i < table.getRows().size(); i++) {
                            Reestr reestr = new Reestr();

                            for (int j = 0; j < table.getRow(i).getTableCells().size(); j++) {

                                List<XWPFPictureData> list = new ArrayList<XWPFPictureData>();
                                switch (j) {
                                    case 0: reestr.setId(Integer.valueOf(table.getRow(i).getCell(j).getText()));
                                        break;
                                    case 1: reestr.setRegNumber(table.getRow(i).getCell(j).getText());
                                        break;
                                    case 2: if(!"".equals(table.getRow(i).getCell(j).getText()))
                                        reestr.setLableText(table.getRow(i).getCell(j).getText());
                                    else {
                                        StringBuilder stringBuilder = new StringBuilder();

                                        for(XWPFParagraph paragraph: table.getRow(i).getCell(j).getParagraphs()){
                                            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
                                            DocumentBuilder builder = factory.newDocumentBuilder();
                                            InputSource is = new InputSource(new StringReader(paragraph.getCTP().toString()));
                                            Document doc = builder.parse(is);
                                            XPathFactory xPathfactory = XPathFactory.newInstance();
                                            XPath xpath = xPathfactory.newXPath();
                                            XPathExpression expr = xpath.compile("//xml-fragment/r/pict/shape/imagedata");
                                            Node nn = (Node) expr.evaluate(doc, XPathConstants.NODE);

                                            String idP = null;
                                            if(nn==null){
                                                expr = xpath.compile("//xml-fragment/r/drawing/inline/graphic/graphicData/pic/blipFill/blip");
                                                nn = (Node) expr.evaluate(doc, XPathConstants.NODE);
                                                idP = nn.getAttributes().getNamedItem("r:embed").getNodeValue();
                                            } else
                                                idP = nn.getAttributes().getNamedItem("r:id").getNodeValue();
                                            POIXMLDocumentPart poixmlDocumentPart = xdoc.getRelationById(idP);
                                            PackagePart packagePart = poixmlDocumentPart.getPackagePart();
                                            PackagePartName packagePartName = packagePart.getPartName();
                                            stringBuilder.append(packagePartName.getURI());
                                        }
                                        list = xdoc.getAllPictures();
                                        for (XWPFPictureData pictureData : list){
                                            if (pictureData.getFileName().contains(stringBuilder.substring(stringBuilder.indexOf("image")))){
                                                reestr.setLablePicture(pictureData.getData());
                                                break;
                                            }
                                        }
                                    }
                                        break;
                                    case 3: reestr.setDocNumber(table.getRow(i).getCell(j).getText());
                                        break;
                                    case 4: reestr.setName(table.getRow(i).getCell(j).getText());
                                        break;
                                    case 5: {
                                        List<GoodsCode> goodsCodes = new ArrayList<GoodsCode>();

                                        StringBuilder stringBuilder = new StringBuilder();
                                        stringBuilder.append(table.getRow(i).getCell(j).getText());
                                        stringBuilder.delete(0,stringBuilder.indexOf("/")+1);

                                        String s = stringBuilder.toString();
                                        String[] strings = s.split(",");

                                        for(String s1:strings){
                                            GoodsCode goodCode = new GoodsCode();
                                            goodCode.setTnvedCode(s1.trim());
                                            goodCode.setReestrId(reestr.getId());
                                            goodsCodes.add(goodCode);
                                        }
                                        reestr.goodsCodes = goodsCodes;
                                    }
                                    break;
                                    case 6: reestr.setFullName(table.getRow(i).getCell(j).getText());
                                        break;
                                    case 7: reestr.setDovFullName(table.getRow(i).getCell(j).getText());
                                        break;
                                    case 8: {
                                        String string = table.getRow(i).getCell(j).getText();
                                        DateFormat format = new SimpleDateFormat("dd.MM.yyyy");
                                        Date date = format.parse(string);
                                        reestr.setDateOff(date);
                                    }
                                    break;
                                    case 9: reestr.setNote(table.getRow(i).getCell(j).getText());
                                        break;
                                }
                            }
                            reestrs.add(reestr);
                        }
                    }
                }
            }
            return reestrs;
        } catch (Exception ex) {
            log.error("Can't parse file");
            log.error(ex);
            ex.printStackTrace();
            return null;
        }
    }
}

