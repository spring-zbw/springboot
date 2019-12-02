package com.zbw.springboot.util;

/**
 * Created by 郑博文 on 2019/12/2.
 */
public class Page {

    private Integer pageIndex=1;

    private Integer pageItemCount=10;

    private Integer start = 0;

    private int count = 2000;

    public Page() {
        // TODO Auto-generated constructor stub
    }

    /**
     * @param pageIndex 第几页
     * @param pageItemCount 每页显示多少条
     * @param count 总共多少条记录
     */
    public Page(int pageIndex, int pageItemCount, int count) {
        this.pageIndex = pageIndex;
        this.pageItemCount = pageItemCount;
        this.count = count;
    }

    public Integer getStart() {
        this.start = (this.pageIndex - 1) * this.pageItemCount;
        return this.start;
    }

    public Integer getPageIndex() {
        return pageIndex;
    }

    public void setPageIndex(Integer pageIndex) {
        this.pageIndex = pageIndex;
    }

    public Integer getPageItemCount() {
        return pageItemCount;
    }

    public void setPageItemCount(Integer pageItemCount) {
        this.pageItemCount = pageItemCount;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
