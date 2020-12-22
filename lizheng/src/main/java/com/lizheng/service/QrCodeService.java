package com.lizheng.service;

import com.google.zxing.WriterException;

import java.awt.image.BufferedImage;

public interface QrCodeService {


    BufferedImage getQrCodeImage(String content) throws WriterException;



}
