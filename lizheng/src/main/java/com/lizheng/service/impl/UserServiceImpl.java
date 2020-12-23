package com.lizheng.service.impl;

import com.lizheng.bean.po.UserExcelVolotile;
import com.lizheng.bean.res.UserListRes;
import com.lizheng.exception.BizException;
import com.lizheng.mapper.db2.UserInfoExtMapper;
import com.lizheng.service.QrCodeService;
import com.lizheng.service.UserService;
import com.lizheng.threadpool.UserThreadPool;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.CellStyle;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.stream.Stream;


@Slf4j
@Service

public class UserServiceImpl implements UserService {

    private static final int THREAD_SIZE = 8;

    @Resource
    private UserThreadPool userThreadPool;

    @Resource
    private UserInfoExtMapper userInfoExtMapper;

    @Resource
    private QrCodeService qrCodeService;


    @Override
    @Transactional(rollbackFor = Exception.class)
    public void up(long id) {
        userInfoExtMapper.update(id);
    }

    @Override
    public void exportAllExcel(HttpServletResponse response) {
        List<UserListRes> userListRes = userInfoExtMapper.selectList();
        if (CollectionUtils.isEmpty(userListRes)){
            throw new RuntimeException("该条件无数据");
        }
        writeRiderListExcel(response, userListRes);
    }




    private void writeRiderListExcel(HttpServletResponse response, List<UserListRes> userListRes) {
        ByteArrayOutputStream byteArrayOut = null;
        try {
            HSSFWorkbook workbook = new HSSFWorkbook();
            //创建
            HSSFSheet sheet = workbook.createSheet("xxxx");
            HSSFPatriarch drawingPatriarch = sheet.createDrawingPatriarch();
            //样式
            HSSFCellStyle cellStyle = workbook.createCellStyle();
            //自动换行
            cellStyle.setWrapText(true);
            //创建设置宽度
            sheet.setColumnWidth(1,2000);
            sheet.setColumnWidth(2,4000);
            //创建一行
            HSSFRow row = sheet.createRow(0);
            //设置每列名字
            row.createCell(0).setCellValue("序号");
            row.createCell(1).setCellValue("xxx");
            row.createCell(2).setCellValue("二维码");
            int size = userListRes.size();
            if (size < 100){
                simpleWriteExcel(userListRes,cellStyle,drawingPatriarch,sheet,workbook);
            }else {
                long start = System.currentTimeMillis();
                CountDownLatch countDownLatch = new CountDownLatch(THREAD_SIZE);
                int subSize = userListRes.size() / THREAD_SIZE;
                List<Map<Integer,List<UserListRes>>> lists = new ArrayList<>();
                for (int i = 0; i < THREAD_SIZE; i++) {
                    Map<Integer,List<UserListRes>> mapTemp = new HashMap();
                    int offset = i * subSize;
                    List<UserListRes> riderListSub;
                    if (i == 7){
                        riderListSub = userListRes.subList(offset, size);
                    }else {
                        riderListSub = userListRes.subList(offset, offset + subSize);
                    }
                    mapTemp.put(offset,riderListSub);
                    lists.add(mapTemp);
                }
                UserExcelVolotile userExcelVolotile = new UserExcelVolotile();
                for (Map<Integer, List<UserListRes>> list : lists) {
                    userThreadPool.exec(()->{
                        log.info(Thread.currentThread().getName()+"-----------------start");
                        threadWriteExcel(list,cellStyle,drawingPatriarch,sheet,workbook,countDownLatch, userExcelVolotile);
                        log.info(Thread.currentThread().getName()+"-----------------end");
                    });
                }
                countDownLatch.await(600,TimeUnit.SECONDS);
                long end = System.currentTimeMillis();
                log.error("thread excel执行time={}",end-start);
                if (!userExcelVolotile.isFalg()){
                    throw new BizException("有线程异常中断导出异常");
                }
                if (countDownLatch.getCount() != 0){
                    log.error("导出异常，自动释放countDownLatch");
                    throw new BizException("导出异常");
                }
            }
            SimpleDateFormat sd = new SimpleDateFormat("yyyyMMddHHmmss");
            //设置文件名称
            response.setCharacterEncoding("UTF-8");
            response.setHeader("content-Type", "application/vnd.ms-excel");
            response.setHeader("Content-Disposition", "attachment;filename="
                    .concat(String.valueOf(URLEncoder.encode("员工信息管理-"+sd.format(new Date()) + ".xls"
                            //设置文件名称
                            , "UTF-8"))));
            workbook.write(response.getOutputStream());

        } catch (Exception e) {
            log.error("rider excel error e={}|emsg={}",e,e.getMessage());
            throw new BizException("导出异常");
        }finally {
            if (byteArrayOut!=null){
                try {
                    byteArrayOut.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    private void threadWriteExcel(Map<Integer, List<UserListRes>> integerListMap, CellStyle cellStyle,
                                  HSSFPatriarch drawingPatriarch, HSSFSheet sheet, HSSFWorkbook workbook, CountDownLatch countDownLatch, UserExcelVolotile userExcelVolotile) {
        try {
            for (Map.Entry<Integer, List<UserListRes>> integerListEntry : integerListMap.entrySet()) {
                Integer key = integerListEntry.getKey();
                List<UserListRes> value = integerListEntry.getValue();
                for (int i = 1; i <= value.size(); i++) {
                    if (!userExcelVolotile.isFalg()){
                        break;
                    }
                    threadWriteRow(value, cellStyle, drawingPatriarch, sheet,workbook,i,key+i);
                }
            }
        }catch (Exception e){
            userExcelVolotile.setFalg(false);
            log.error(Thread.currentThread().getName()+"Excel业务异常|e={}",e);
        }finally {
            countDownLatch.countDown();
        }

    }

    private void simpleWriteExcel(List<UserListRes> userListRes, CellStyle cellStyle,
                                  HSSFPatriarch drawingPatriarch, HSSFSheet sheet, HSSFWorkbook workbook) {
        for (int i = 1; i <= userListRes.size(); i++) {
            writeRow(userListRes, cellStyle, drawingPatriarch, sheet,workbook, i , i);
        }
    }

    private void threadWriteRow(List<UserListRes> userListRes, CellStyle cellStyle,
                                HSSFPatriarch drawingPatriarch, HSSFSheet sheet, HSSFWorkbook workbook, int i, int key) throws Exception{
        ByteArrayOutputStream byteArrayOut = null;
        try {
            UserListRes userListResTemp = userListRes.get(i - 1);
            HSSFRow tempRow = writeRow(key, sheet);
            int temp = 0;
            HSSFCell cell1 = tempRow.createCell(temp++);
            cell1.setCellStyle(cellStyle);
            cell1.setCellValue(key);
            String qrCode = userListResTemp.getQrCode();
            if (StringUtils.isBlank(qrCode)){
                return;
            }
            BufferedImage qrCodeImage = qrCodeService.getQrCodeImage(qrCode);
            byteArrayOut = new ByteArrayOutputStream();
            ImageIO.write(qrCodeImage, "jpg", byteArrayOut);
            byte[] data = byteArrayOut.toByteArray();
            HSSFClientAnchor anchor = new HSSFClientAnchor(0, 0, 0, 0, (short)temp++, key , (short) temp++, key+1 );
            threadCreatePicture(drawingPatriarch,anchor, workbook,data);
            tempRow.setHeight((short)2000);
        }finally {
            if (byteArrayOut!=null){
                try {
                    byteArrayOut.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void writeRow(List<UserListRes> userListRes, CellStyle cellStyle, HSSFPatriarch drawingPatriarch, HSSFSheet sheet, HSSFWorkbook workbook, int i, int key) {
        ByteArrayOutputStream byteArrayOut = null;
        try {
            UserListRes userListResTemp = userListRes.get(i - 1);
            HSSFRow tempRow = sheet.createRow(key);
            int temp = 0;
            HSSFCell cell1 = tempRow.createCell(temp++);
            cell1.setCellStyle(cellStyle);
            cell1.setCellValue(key);
            HSSFCell cell2 = tempRow.createCell(temp++);
            cell2.setCellStyle(cellStyle);
            cell2.setCellValue(userListResTemp.getRiderName());
            String qrCode = userListResTemp.getQrCode();
            if (StringUtils.isBlank(qrCode)){
                return;
            }
            BufferedImage qrCodeImage = qrCodeService.getQrCodeImage(qrCode);
            byteArrayOut = new ByteArrayOutputStream();
            ImageIO.write(qrCodeImage, "jpg", byteArrayOut);
            byte[] data = byteArrayOut.toByteArray();
            HSSFClientAnchor anchor = new HSSFClientAnchor(0, 0, 0, 0, (short)temp++, key , (short) temp++, key+1 );
            drawingPatriarch.createPicture(anchor, workbook.addPicture(data, HSSFWorkbook.PICTURE_TYPE_JPEG));
            tempRow.setHeight((short)2000);
        }catch (Exception e){
            throw new BizException("导出异常");
        }finally {
            if (byteArrayOut!=null){
                try {
                    byteArrayOut.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }



    public synchronized HSSFRow writeRow(Integer key,HSSFSheet sheet){
        return sheet.createRow(key);
    }

    public synchronized void threadCreatePicture(HSSFPatriarch drawingPatriarch, HSSFClientAnchor anchor, HSSFWorkbook workbook, byte[] data) {
        drawingPatriarch.createPicture(anchor, workbook.addPicture(data, HSSFWorkbook.PICTURE_TYPE_JPEG));
    }

}
