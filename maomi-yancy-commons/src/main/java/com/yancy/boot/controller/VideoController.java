package com.yancy.boot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@RestController
public class VideoController {





    @PostMapping(value = "/index/uploadVidoe")
    @ResponseBody
    public Map<String,String> savaVideo(@RequestParam("file") MultipartFile file) throws IOException {


      Map<String,String> resultMap=new HashMap<>();
      //获取文件后缀
      String fileExt = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf(".")+1)
              .toLowerCase();
      //重构文件名称
        String pikId = UUID.randomUUID().toString().replaceAll("-","");
        String newVideoName=pikId+"."+fileExt;
        //保存视频
        String savePaths="E:/java/video/";
        File file1Save = new File(savePaths,newVideoName);
        file.transferTo(file1Save);
        resultMap.put("resCode","1");
        resultMap.put("webShowPath","http:127.0.0.1:8080/"+newVideoName);
         return resultMap;
    }
}
