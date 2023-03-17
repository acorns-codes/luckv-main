package com.luckv.demo.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URLEncoder;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.luckv.demo.dto.Video;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@RestController
@RequiredArgsConstructor
public class VideoController {
	
	ResourceLoader resourceLoader;

	@Value("${file.dir}") //application.properties에 있는 속성 그대로 가져오기
    private String fileDir;

    @GetMapping("/videoUpload")
    public String newFile(){
        return "upload-form";
    }

    @PostMapping("/videoUpload")
    public String saveFile(@RequestParam int ano,
                           @RequestParam MultipartFile file,
                           HttpServletRequest request) throws IOException {
        log.info("request={}",request);
        log.info("itemName={}",ano);
        log.info("multipartFile={}",file);

        if(!file.isEmpty()){
            String fullPath = fileDir + file.getOriginalFilename(); 
            //스프링이 제공하는 기능
            log.info("파일 저장 fullPath={}",fullPath);
            file.transferTo(new File(fullPath));
        }

        return "upload-form";
    }
    
    
    
    
    @GetMapping("/download")
    public void download(HttpServletResponse response) throws IOException {

      String path = "C:/Users/ACORNS/Desktop/dddd/";
      
      byte[] fileByte = FileUtils.readFileToByteArray(new File(path));

      response.setContentType("application/octet-stream");
      response.setHeader("Content-Disposition", "attachment; fileName=\"" + URLEncoder.encode("cms_api.xlsx", "UTF-8")+"\";");
      response.setHeader("Content-Transfer-Encoding", "binary");

      response.getOutputStream().write(fileByte);
      response.getOutputStream().flush();
      response.getOutputStream().close();
    }
}
