package com.zbw.springboot.dao;

import com.zbw.springboot.pojo.CustomerCnt;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 *
 * @author 郑博文
 * @date 2019/10/11
 */
@Repository
public interface TimerStatisticDao {

    /**
     * 查询所有的关注数
     * @return
     */
    List<CustomerCnt> findCustomerCntAll();

    /**
     * 查询总数
     * @param customer_id
     * @return
     */
    int getMutualFans(@Param("customer_id") Integer customer_id);
}
