package com.bjpowernode.fdfs.controller;

import com.bjpowernode.fdfs.model.Creditor;
import com.bjpowernode.fdfs.service.CreditorService;
import com.bjpowernode.fdfs.util.FastDfsUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

/**
 * ClassName:CreditorsController
 * Package:com.bjpowernode.fdfs.controller
 * Description:
 *
 * @Date:2018/10/13 16:24
 * @Author:hiwangxiaodong@hotmail.com
 */
@Controller
public class CreditorsController {
    @Autowired
    private CreditorService creditorService;
    @Value("${contract_url_prefix}")
    String contractUrlPrefix;

    @GetMapping("/")
    public String list(Model model){
        List<Creditor> list = creditorService.findAll();

        model.addAttribute("userList", list);

        return "creditors";
    }
    @GetMapping("/goUpload/{id}")
    public String goUpload(@PathVariable Integer id, Model model){
        Creditor creditor = creditorService.findOne(id);
        model.addAttribute("creditor", creditor);
        return "upload";
    }
    @PostMapping("/upload")
    public @ResponseBody  String upload(Integer id, MultipartFile multipartFile){
        if (multipartFile == null) {
            return "<script>window.parent.uploadOK(1);</script>";
        }
        Integer resultCode = null;
        try {
            byte[] bytes = multipartFile.getBytes();
            String oldFileName = multipartFile.getOriginalFilename();
            long fileSize = multipartFile.getSize();
            String contentType = multipartFile.getContentType();

            String extFileName = "";
            if (oldFileName.contains(".")){
                extFileName = oldFileName.substring(oldFileName.lastIndexOf(".")+1);
            }


            String[] result = FastDfsUtil.upload(bytes, extFileName);

            Creditor creditor = new Creditor();
            creditor.setId(id);
            creditor.setFileSize(fileSize);
            creditor.setFileType(contentType);
            creditor.setOldFileName(oldFileName);
            creditor.setRemoteFileName(result[1]);
            creditor.setGroupName(result[0]);
            creditor.setContractUrl(contractUrlPrefix + result[0] + "/" + result[1]);
            Integer count = creditorService.modify(creditor);
            if (count > 0){
                resultCode = 0;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return "<script>window.parent.uploadOK('"+resultCode+"');</script>";
    }
    @GetMapping("/download/{id}")
    public ResponseEntity<byte[]> download(@PathVariable Integer id){
        Creditor creditor = creditorService.findOne(id);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "attchement;filename=" + creditor.getOldFileName());
        headers.add("Content-Type", creditor.getFileType());
        headers.add("Content-Length", creditor.getFileSize().toString());

        byte[] bytes = FastDfsUtil.download(creditor.getGroupName(), creditor.getRemoteFileName());

        ResponseEntity<byte[]> entity = new ResponseEntity<>(bytes, headers, HttpStatus.OK);


        return entity;
    }

    @DeleteMapping("/delete/{id}")
    public  String  delete(@PathVariable Integer id) {
        creditorService.deleteRemoteFile(id);
        return "redirect:/";
    }

}
