package com.luckv.demo.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileUrlResource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.io.support.ResourceRegion;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpRange;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.MediaTypeFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.luckv.demo.service.VideoService;
import com.luckv.demo.vo.Video;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class VideoController {
	
	private final VideoService videoService;
	ResourceLoader resourceLoader;

	@Value("${file.dir}") //application.properties에 있는 속성 그대로 가져오기
    private String fileDir;


	// 동영상파일 업로드
    @PostMapping("/video/upload")
    public Map<String,Object> videoUpload(
                           @RequestParam MultipartFile file,
                           HttpServletRequest request) throws IOException {
        
    	Map<String,Object> obj = new HashMap<>();
        String originalFileName = file.getOriginalFilename(); // 파일 원본이름 알아오기
        
        
        
        // 파일 이름 중복되지않게 	변경
        UUID uuid = UUID.randomUUID(); 
        String savedFileName = "";       
        savedFileName = uuid.toString() + "_" + originalFileName;
        
        Video video = new Video();
        video.setVideoFile(savedFileName);
        
        
        
        if(!file.isEmpty()){
            String fullPath = fileDir + savedFileName; // 파일생성
            
            //스프링이 제공하는 기능
            file.transferTo(new File(fullPath)); // 파일 서버로 전송
            
            
            if(!videoService.videoUpload(video)) {
	        	obj.put("res","OK");
	        	obj.put("msg","NOT_CREATED_VIDEO");
	        	return obj;
	        }
           
	        obj.put("res","OK");
        	obj.put("msg","SUCCESS");
        }
        return obj;
           
       }
    
    // 동영상 다운로드 
    @GetMapping("/video/download/{ano}")
    public Map<String,Object> fileDownload(@PathVariable int ano,
                             HttpServletResponse response) throws IOException {
        
    	Map<String,Object> obj = new HashMap<>();      	
    	Video video = videoService.videoDownload(ano);
    	if(video == null) {
    		obj.put("res","OK");
    		obj.put("msg","NOT_DOWNLOAD_VIDEO");
    		return obj;
    	}
    	
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
        
        obj.put("res","OK");
    	obj.put("msg","SUCCESS");
    	return obj;

    }
    
    
    // 동영상 스트리밍
    @GetMapping("/video/play")
    public ResponseEntity<List<ResourceRegion>> video(int ano, @RequestHeader HttpHeaders httpHeaders) throws IOException {
    	Video video = videoService.videoPlay(ano);
    	
        FileUrlResource resource = new FileUrlResource(fileDir + video.getVideoFile());

        List<ResourceRegion> resourceRegions = HttpRange.toResourceRegions(httpHeaders.getRange(), resource);

        return ResponseEntity.status(HttpStatus.PARTIAL_CONTENT)
                .contentType(MediaTypeFactory.getMediaType(resource).orElse(MediaType.APPLICATION_OCTET_STREAM))
                .body(resourceRegions);
    }
   
}
