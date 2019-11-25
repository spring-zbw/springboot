package com.zbw.springboot.dao;

import com.zbw.springboot.pojo.CustomerCnt;
import com.zbw.springboot.pojo.ExportUser;
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

    /**
     * 根据条件查询
     * @param type
     * @param endDate
     * @param startDate
     * @return
     */
    List<ExportUser> getExportUser(@Param("type") Integer type, @Param("endDate") String endDate, @Param("startDate") String startDate);
}
