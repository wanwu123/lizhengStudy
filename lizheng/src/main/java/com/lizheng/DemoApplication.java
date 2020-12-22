package com.lizheng;

import com.lizheng.annotation.EnableLizheng;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

@SpringBootApplication
@EnableConfigurationProperties
@EnableScheduling
@EnableTransactionManagement
//@EnableLizheng
public class DemoApplication {

    public static void main(String[] args)
    {
        SpringApplication.run(DemoApplication.class, args);
    }

    public int[] searchRange(int[] nums, int target) {
        int left = 0, right = nums.length - 1;
        int mid, i, j;
        while (left <= right) {
            mid = (left + right) / 2;
            if (nums[mid] == target) {
                i = mid;
                j = mid;
                while (i >= 0 && nums[i] == target){
                    i--;
                }
                while (j < nums.length && nums[j] == target){
                    j++;
                }
                return new int[]{i + 1, j - 1};
            }
            if (nums[mid] < target) {
                left = mid + 1;
            } else{
                right = mid - 1;
            }
        }
        return new int[]{-1, -1};
    }


}

