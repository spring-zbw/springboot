package com.zbw.springboot.pojo;

/**
 * Created by 郑博文 on 2019/10/11.
 */
public class CustomerCnt {
    private Long id;

    private Long customer_id;

    private Integer fans_cnt;

    private Integer thumb_cnt;

    private Integer attention_cnt;

    private Integer invite_cnt;

    public Integer getInvite_cnt() {
        return invite_cnt;
    }

    public void setInvite_cnt(Integer invite_cnt) {
        this.invite_cnt = invite_cnt;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(Long customer_id) {
        this.customer_id = customer_id;
    }

    public Integer getFans_cnt() {
        return fans_cnt;
    }

    public void setFans_cnt(Integer fans_cnt) {
        this.fans_cnt = fans_cnt;
    }

    public Integer getThumb_cnt() {
        return thumb_cnt;
    }

    public void setThumb_cnt(Integer thumb_cnt) {
        this.thumb_cnt = thumb_cnt;
    }

    public Integer getAttention_cnt() {
        return attention_cnt;
    }

    public void setAttention_cnt(Integer attention_cnt) {
        this.attention_cnt = attention_cnt;
    }
}
