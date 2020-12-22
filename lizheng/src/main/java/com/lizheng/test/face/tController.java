package com.lizheng.test.face;


public class tController {
    //    @Test
//    public void test1(){
//        int[] arr = {0,0,3,0,6,3,0,2};
//        int index = arr.length-1;
//        for (int i = arr.length-1; i > 0; i--) {
//            if (arr[i] != 0){
//                int temp = arr[index];
//                arr[index--] = arr[i];
//                arr[i] = temp;
//            }
//        }
//        for (int i : arr) {
//            System.out.print(i+" ");
//        }
//    }

    /**
     * 现有需求，所有的对象在存储时需要先对各字段进行加密(可指定不加密的字段)，再存储到数据库中。如何做。
     * Person: id(String)，sex(String),age(int),identity(List<String>),cars(Car[]),cards(Map<String,Card>)
     *
     * Car: id(String)，sex(String),type(String),label(List<String>)
     *
     *
     * man1:
     * 	id:"no1001"
     * 	sex:"男",
     * 	age:18,
     * 	identity: ["教师","儿子","父亲"],
     * 	cars:[
     * 		car1,
     * 		car2,
     * 	]
     * 	cards:{
     * 		"card1_id":{card1},
     * 		"card2_id":{card2}
     *        }
     *
     * car1:
     * 	id:"no01",
     * 	type:"奔驰"
     * 	label:["高端","大气","上档次"]
     * car1:
     * 	id:"no02",
     * 	type:"宝马"
     * 	label:["低调","奢华","有内涵"]
     */

}
