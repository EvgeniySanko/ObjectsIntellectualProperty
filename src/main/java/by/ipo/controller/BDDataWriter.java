package by.ipo.controller;

import by.ipo.bo.BDDataRewriter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

@Controller
public class BDDataWriter {

    @Autowired
    BDDataRewriter rewriter;

    private Logger log = LogManager.getLogger(BDDataWriter.class);

    @Bean
    public CommonsMultipartResolver multipartResolver(){
        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver();
        multipartResolver.setMaxUploadSize(10000000);
        return multipartResolver;
    }

    @GetMapping("/bddatarewriter")
    public String datawriter(){
        return "bddatarewriter";
    }

    @GetMapping("/rewrite")
    public String rewrite(){
        return "rewrite";
    }

    @RequestMapping(value = "/bddatarewriter/rewrite", method = RequestMethod.POST)
    public void rewrite(@RequestParam("file")MultipartFile multipartFile){
        rewriter.rewrite(multipartFile);
    }
}
