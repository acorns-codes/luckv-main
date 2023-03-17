package com.luckv.demo.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.luckv.demo.dto.Video;
import com.luckv.demo.response.DefaultRes;
import com.luckv.demo.response.ResponseMessage;
import com.luckv.demo.response.StatusCode;
import com.luckv.demo.service.VideoService;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@RestController
@RequiredArgsConstructor
public class VideoController {
	
	private final VideoService videoService;
	ResourceLoader resourceLoader;

	@Value("${file.dir}") //application.properties에 있는 속성 그대로 가져오기
    private String fileDir;


	// 동영상파일 업로드
    @PostMapping("/videoUpload")
    public ResponseEntity videoUpload( Video video,
                           @RequestParam MultipartFile file,
                           HttpServletRequest request) throws IOException {
        log.info("request={}",request);
        log.info("itemName={}", video.getAno());
        log.info("multipartFile={}",file);
        	
        String originalFileName = file.getOriginalFilename(); // 파일 원본이름 알아오기
        
        
        
        // 파일 이름 중복되지않게 	변경
        UUID uuid = UUID.randomUUID(); 
        String savedFileName = "";       
        savedFileName = uuid.toString() + "_" + originalFileName;
        
        video.setVideoFile(savedFileName);
        
        if(!file.isEmpty()){
            String fullPath = fileDir + savedFileName; // 파일생성
            
            //스프링이 제공하는 기능
            log.info("파일 저장 fullPath={}",fullPath);
            file.transferTo(new File(fullPath)); // 파일 서버로 전송
            
            boolean b = videoService.videoUpload(video);
            
            if(b) {
                return new ResponseEntity(DefaultRes.res(StatusCode.OK, ResponseMessage.CREATED_VIDEO, b), HttpStatus.OK);
            }
        }      
        return  new ResponseEntity(DefaultRes.res(StatusCode.BAD_REQUEST, ResponseMessage.NOT_CREATED_VIDEO), HttpStatus.OK);
       }
    
    
    
    
  
    
    
    
    // 동영상 다운로드 
    @GetMapping("/videoDownload/{ano}")
    public ResponseEntity<Video> fileDownload(@PathVariable int ano,
                             HttpServletResponse response) throws IOException {
        
    	    	
    	Video video = videoService.videoDownload(ano);
        File f = new File(fileDir, video.getVideoFile());

        
        
        // file 다운로드 설정
        response.setContentType("application/download");
        response.setContentLength((int)f.length());
        response.setHeader("Content-disposition", "attachment;filename=\"" + video.getVideoFile()+ ".mp4" + "\"");
        // response 객체를 통해서 서버로부터 파일 다운로드
        OutputStream os = response.getOutputStream();
        
        // 파일 입력 객체 생성
        FileInputStream fis = new FileInputStream(f);
        FileCopyUtils.copy(fis, os);
        fis.close();
        os.close();
        

        if(video != null ) {
            return new ResponseEntity(DefaultRes.res(StatusCode.OK, ResponseMessage.DOWNLOAD_VIDEO), HttpStatus.OK);
        }
        return  new ResponseEntity(DefaultRes.res(StatusCode.BAD_REQUEST, ResponseMessage.NOT_DOWNLOAD_VIDEO), HttpStatus.OK);
       
    }
    
    
    
    
//    
//    
//    @GetMapping("/download")
//    public void download(HttpServletResponse response) throws IOException {
//
//      String path = "C:/Users/ACORNS/Desktop/dddd/";
//      
//      byte[] fileByte = FileUtils.readFileToByteArray(new File(path));
//
//      response.setContentType("application/octet-stream");
//      response.setHeader("Content-Disposition", "attachment; fileName=\"" + URLEncoder.encode("cms_api.xlsx", "UTF-8")+"\";");
//      response.setHeader("Content-Transfer-Encoding", "binary");
//
//      response.getOutputStream().write(fileByte);
//      response.getOutputStream().flush();
//      response.getOutputStream().close();
//    }
}
