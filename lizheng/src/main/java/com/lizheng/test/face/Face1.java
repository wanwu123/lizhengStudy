package com.lizheng.test.face;

import lombok.Data;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.PictureData;
import org.junit.Test;

import java.io.*;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Face1 {

    @Test
    public void test1(){
        sout(5);
    }

    public void sout(int i){
        String a = " ";
        for (int j = 1; j <= i; j++) {
            StringBuilder stringBuilder = new StringBuilder();
            for (int k = 0; k < i-j; k++) {
                stringBuilder.append(a);
            }
            for (int k = 0; k < 2 * j - 1; k++) {
                stringBuilder.append("*");
            }
            System.out.println(stringBuilder.toString());
        }
    }

    @Test
    public void test(){
        int[] nums1 = new int[]{1,2,3,0,0,0};
        int[] nums2 = new int[]{2,5,6};
        System.out.println(Arrays.toString( merge(nums1,3,nums2,3)));

    }

    public int[] merge(int[] nums1, int m, int[] nums2, int n) {
        int mergeIndex = 0;
        for (int i = 0;i < n;i++){
            for (int j = mergeIndex;j<5;j++){
                if(nums1[j] < nums2[i]){
                    int temp = nums1[j];
                    nums1[j] = nums2[i];
                    nums1[j+1] =temp;
                    mergeIndex = j;
                }
            }
        }
        return nums1;
    }

    /**
     * a = [3, 2, 0, 1 , 7, 5, 9, 4, -6]
     * result = 5
     *
     *  print:
     *  "a[0] + a[1] = 5"
     *  a[2] + a[5] = 5
     *  a[3] + a[7] = 5
     *
     * function sum(arr, target)
     */
    @Test
    public void test3(){
        int[] arr = new int[]{3, 2, 0, 1 , 7, 5, 9, 4, -6};
        sum(arr,5);
    }

    public void sum(int[] arr,int target){
        for (int i = 0; i < arr.length-1; i++) {
            int tempVal = arr[i];
            for (int j = i+1; j < arr.length; j++) {
                int tempVal2 = arr[j];
                if (tempVal+tempVal2 == target){
                    System.out.println(String.format("index1=%d,index2=%d",i,j));
                }
            }
        }
    }

    public void sum2(int[] arr,int target){
        HashMap<Integer, Integer> map = new HashMap();
        for (int i = 0; i < arr.length; i++) {
            map.put(i,arr[i]);
        }
    }


    /**
     *             a  index
     *         /      \
     *        b        c
     *     /     \      /
     *    e     f      h
     *            \
     *              g
     * closestParent(e, f) = b
     * closestParent(f, h) = a
     * closeseParent(f, g) = f
     */
    @Test
    public void test5(){

    }

    public Node closeseParent(Node f,Node g){


        return null;
    }

    public static void main(String[] args) throws Exception{
        HSSFWorkbook wb = new HSSFWorkbook(new FileInputStream(new File("C:\\Users\\86131\\Documents\\WeChat Files\\wxid_0ksku33fl40a22\\FileStorage\\File\\2020-10\\三公司一队.xls")));

        HashMap<String, Integer> objectObjectHashMap = new HashMap<>();
        int i = 1;
        HSSFSheet sheetAt = wb.getSheetAt(3);
        List<HSSFPictureData> ls = sheetAt.getWorkbook().getAllPictures();
        Map<String, PictureData> pictures1 = getPictures1(sheetAt);
        /*for(HSSFPictureData pd : ls){
            int a = 1;
            HSSFRow row = sheetAt.getRow(++i);
            System.out.println(i);
            HSSFCell cell = row.getCell(2);
            String rawValue = cell.getStringCellValue();
            rawValue = rawValue.replaceAll("\n","");
            Integer integer = objectObjectHashMap.get(rawValue);
            if (integer == null){
                objectObjectHashMap.put(rawValue,a);
            }else {
                objectObjectHashMap.put(rawValue,integer+1);
                rawValue = rawValue+"("+(integer+1)+")";
            }
            byte[] data = pd.getData();
            FileOutputStream fileOutputStream = new FileOutputStream("C:\\d\\"+rawValue+".jpg");
            fileOutputStream.write(data);
            fileOutputStream.flush();
            fileOutputStream.close();
        }*/
        while (true){
            int a = 1;
            HSSFRow row = sheetAt.getRow(++i);
            HSSFSheet sheet = row.getSheet();
            List<HSSFPictureData> allPictures = sheet.getWorkbook().getAllPictures();
            System.out.println(i);
            HSSFCell cell = row.getCell(2);
            HSSFCell cell1 = row.getCell(1);
            String rawValue = cell.getStringCellValue();
            rawValue = rawValue.replaceAll("\n", "");
            Integer integer = objectObjectHashMap.get(rawValue);
            if (integer == null) {
                objectObjectHashMap.put(rawValue, a);
            } else {
                objectObjectHashMap.put(rawValue, integer + 1);
                rawValue = rawValue + "(" + (integer + 1) + ")";
            }

            FileOutputStream fileOutputStream = new FileOutputStream("C:\\d\\" + rawValue + ".jpg");
           // fileOutputStream.write(data);
            fileOutputStream.flush();
            fileOutputStream.close();

        }

    }

    /**
     * 获取图片和位置 (xls)
     * @param sheet
     * @return
     * @throws IOException
     */
    public static Map<String, PictureData> getPictures1 (HSSFSheet sheet) throws IOException {
        Map<String, PictureData> map = new HashMap<String, PictureData>();
        List<HSSFShape> list = sheet.getDrawingPatriarch().getChildren();
        for (HSSFShape shape : list) {
            if (shape instanceof HSSFPicture) {
                HSSFPicture picture = (HSSFPicture) shape;
                HSSFClientAnchor cAnchor = (HSSFClientAnchor) picture.getAnchor();
                PictureData pdata = picture.getPictureData();
                String key = cAnchor.getRow1() + "-" + cAnchor.getCol1(); // 行号-列号
                map.put(key, pdata);
            }
        }
        return map;
    }
}
@Data
class Node{
    private Node LeftNode;
    private Node RightNode;
}
