package com.lizheng.util;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

public class dom4j {
    public static void main(String[] args) throws Exception {
        String xml = "<?xml version=\"1.0\" encoding=\"utf-8\"?>" +
                "<soap:Envelope xmlns:soap=\"http://www.w3.org/2003/05/soap-envelope\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\">" +
                "<soap:Body>" +
                "<InsertSBaseExecutorMSForPeopleResponse xmlns=\"http://tempuri.org/\">" +
                "<InsertSMSForPeopleResult>success</InsertSMSForPeopleResult>" +
                "</InsertSMSForPeopleResponse>" +
                "</soap:Body>" +
                "</soap:Envelope>";
        Document document = DocumentHelper.parseText(xml);
        Element rootElement = document.getRootElement();
        Element element = rootElement.element("Body").element("InsertSMSForPeopleResponse").element("InsertSMSForPeopleResult");
        System.out.println(element.getData());
    }
}
