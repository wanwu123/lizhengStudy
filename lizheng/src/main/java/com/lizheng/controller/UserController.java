package com.lizheng.controller;


import com.lizheng.service.UserService;
import org.apache.commons.io.IOUtils;
import org.apache.http.entity.ContentType;
import org.apache.poi.hssf.usermodel.HSSFPictureData;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFPictureData;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.Test;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.List;
import java.util.UUID;


@RestController
@RequestMapping("/api/rider")
public class UserController {

    @Resource
    private UserService userService;

    @GetMapping(value = "/exportAllExcel")
    public void exportExcel(HttpServletResponse response) {
        userService.exportAllExcel(response);
    }


    @Test
    public void test() throws IOException {
        XSSFWorkbook wb = new XSSFWorkbook(new FileInputStream(new File("C:\\Users\\86131\\Documents\\WeChat Files\\wxid_0ksku33fl40a22\\FileStorage\\File\\2020-10\\三公司一队.xlsx")));
        List<XSSFPictureData> ls = wb.getAllPictures();
        for(XSSFPictureData pd : ls){
            byte[] data = pd.getData();
            InputStream inputStream = new ByteArrayInputStream(data);
            MultipartFile file = new MockMultipartFile(ContentType.APPLICATION_OCTET_STREAM.toString(), inputStream);
        }

    }


    public static void main(String[] args) {
        System.out.println(UUID.randomUUID());
    }

}
