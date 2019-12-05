package com.zbw.springboot.pojo;

import java.math.BigDecimal;
import java.util.Date;

public class Customer {
    
	private Long customer_id;

    private Integer customer_status;

    private String area_code;
    
    private String mobile;

    private String qq;

    private String weixin;

    private String unionid;

    private String weixin_bound;

    private String surname;

    private String name;

    private String third_nick_name;

    private Integer sex;

    private Date birthday;

    private String photo;

    private String third_photo;

    private String province;

    private String city;

    private String area;

    private Date register_date;

    private String password;

    private Date last_login_time;

    private String token;
    
    private String market_token;

    private Integer is_online;

    private Integer is_idcard_certified;

    private Integer is_business_license_certified;

    private Date frozen_time;

    private Long frozen_id;

    private String bg_url;

    private Double video_price;

    private Integer service_status;

    private String origin_place;

    private String home;

    private String industry;

    private String job;

    private String interest;

    private String specialty;

    private String signature;
    
    private Long family_id;
    
    private Long is_family_master;
    
    private Integer age;
    
    private String invite_code;

    private Integer residue;

    private Long referrer_id;
    
    private String family_area;
    
    private String area_name;
    
    private Long chat_group_tid;
    
    private String master_area_name;
    
    private Integer rank; // 注册成功后返回的第几位同姓宗亲
    
    private BigDecimal perfect_ratio;
    
    private Integer  is_kf;
    
    private Integer is_super;
    
    private String qrcode_img;

    private String company_name;

    private String company_link;
    
    private String super_area;
    
    private String company_address;
    
    private Integer clan_hall_footprint_switch;
    
    private Integer vip_type;

    private Date vip_expiration_time;
    
    private Long referrer_id2;

    private Long referrer_id3;
    
    private Integer is_default_photo;
    
    private String other_job;
    
    private String email;

    private Long inheritor_id;

    private Integer is_hot_card;

    private String referral_mobile;

    private String last_login_city;

    private String referrer_link;

    public Integer getIs_hot_card() {
        return is_hot_card;
    }

    public void setIs_hot_card(Integer is_hot_card) {
        this.is_hot_card = is_hot_card;
    }

    public Long getInheritor_id() {
        return inheritor_id;
    }

    public void setInheritor_id(Long inheritor_id) {
        this.inheritor_id = inheritor_id;
    }

    public Long getReferrer_id2() {
		return referrer_id2;
	}

	public void setReferrer_id2(Long referrer_id2) {
		this.referrer_id2 = referrer_id2;
	}

	public Long getReferrer_id3() {
		return referrer_id3;
	}

	public void setReferrer_id3(Long referrer_id3) {
		this.referrer_id3 = referrer_id3;
	}

	public Integer getVip_type() {
		return vip_type;
	}

	public void setVip_type(Integer vip_type) {
		this.vip_type = vip_type;
	}

	public Date getVip_expiration_time() {
		return vip_expiration_time;
	}

	public void setVip_expiration_time(Date vip_expiration_time) {
		this.vip_expiration_time = vip_expiration_time;
	}

	public Integer getClan_hall_footprint_switch() {
		return clan_hall_footprint_switch;
	}

	public void setClan_hall_footprint_switch(Integer clan_hall_footprint_switch) {
		this.clan_hall_footprint_switch = clan_hall_footprint_switch;
	}
    public Integer getResidue() {
        return residue;
    }

    public void setResidue(Integer residue) {
        this.residue = residue;
    }
	public String getSuper_area() {
		return super_area;
	}

	public void setSuper_area(String super_area) {
		this.super_area = super_area;
	}

	public Integer getIs_super() {
		return is_super;
	}

	public void setIs_super(Integer is_super) {
		this.is_super = is_super;
	}

	public String getArea_code() {
		return area_code;
	}

	public void setArea_code(String area_code) {
		this.area_code = area_code;
	}

	public BigDecimal getPerfect_ratio() {
		return perfect_ratio;
	}

	public void setPerfect_ratio(BigDecimal perfect_ratio) {
		this.perfect_ratio = perfect_ratio;
	}

	public Integer getRank() {
		return rank;
	}

	public void setRank(Integer rank) {
		this.rank = rank;
	}

	public String getMaster_area_name() {
		return master_area_name;
	}

	public void setMaster_area_name(String master_area_name) {
		this.master_area_name = master_area_name;
	}

	public String getMarket_token() {
		return market_token;
	}

	public void setMarket_token(String market_token) {
		this.market_token = market_token;
	}

	public Long getChat_group_tid() {
		return chat_group_tid;
	}

	public void setChat_group_tid(Long chat_group_tid) {
		this.chat_group_tid = chat_group_tid;
	}

	public Long getReferrer_id() {
		return referrer_id;
	}

	public void setReferrer_id(Long referrer_id) {
		this.referrer_id = referrer_id;
	}

	public String getFamily_area() {
		return family_area;
	}

	public void setFamily_area(String family_area) {
		this.family_area = family_area;
	}
	
   

	public Long getFamily_id() {
		return family_id;
	}

	public void setFamily_id(Long family_id) {
		this.family_id = family_id;
	}
	
	public Long getIs_family_master() {
		return is_family_master;
	}

	public void setIs_family_master(Long is_family_master) {
		this.is_family_master = is_family_master;
	}

	public Long getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(Long customer_id) {
        this.customer_id = customer_id;
    }

    public Integer getCustomer_status() {
        return customer_status;
    }

    public void setCustomer_status(Integer customer_status) {
        this.customer_status = customer_status;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile == null ? null : mobile.trim();
    }

    public String getQq() {
        return qq;
    }

    public void setQq(String qq) {
        this.qq = qq == null ? null : qq.trim();
    }

    public String getWeixin() {
        return weixin;
    }

    public void setWeixin(String weixin) {
        this.weixin = weixin == null ? null : weixin.trim();
    }

    public String getUnionid() {
        return unionid;
    }

    public void setUnionid(String unionid) {
        this.unionid = unionid == null ? null : unionid.trim();
    }

    public String getWeixin_bound() {
        return weixin_bound;
    }

    public void setWeixin_bound(String weixin_bound) {
        this.weixin_bound = weixin_bound == null ? null : weixin_bound.trim();
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname == null ? null : surname.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getThird_nick_name() {
        return third_nick_name;
    }

    public void setThird_nick_name(String third_nick_name) {
        this.third_nick_name = third_nick_name == null ? null : third_nick_name.trim();
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo == null ? null : photo.trim();
    }

    public String getThird_photo() {
        return third_photo;
    }

    public void setThird_photo(String third_photo) {
        this.third_photo = third_photo == null ? null : third_photo.trim();
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province == null ? null : province.trim();
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city == null ? null : city.trim();
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area == null ? null : area.trim();
    }

    public Date getRegister_date() {
        return register_date;
    }

    public void setRegister_date(Date register_date) {
        this.register_date = register_date;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public Date getLast_login_time() {
        return last_login_time;
    }

    public void setLast_login_time(Date last_login_time) {
        this.last_login_time = last_login_time;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token == null ? null : token.trim();
    }

    public Integer getIs_online() {
        return is_online;
    }

    public void setIs_online(Integer is_online) {
        this.is_online = is_online;
    }

    public Integer getIs_idcard_certified() {
        return is_idcard_certified;
    }

    public void setIs_idcard_certified(Integer is_idcard_certified) {
        this.is_idcard_certified = is_idcard_certified;
    }

    public Integer getIs_business_license_certified() {
        return is_business_license_certified;
    }

    public void setIs_business_license_certified(Integer is_business_license_certified) {
        this.is_business_license_certified = is_business_license_certified;
    }

    public Date getFrozen_time() {
        return frozen_time;
    }

    public void setFrozen_time(Date frozen_time) {
        this.frozen_time = frozen_time;
    }

    public Long getFrozen_id() {
        return frozen_id;
    }

    public void setFrozen_id(Long frozen_id) {
        this.frozen_id = frozen_id;
    }

    public Double getVideo_price() {
        return video_price;
    }

    public void setVideo_price(Double video_price) {
        this.video_price = video_price;
    }

    public Integer getService_status() {
        return service_status;
    }

    public void setService_status(Integer service_status) {
        this.service_status = service_status;
    }

    public String getOrigin_place() {
        return origin_place;
    }

    public void setOrigin_place(String origin_place) {
        this.origin_place = origin_place == null ? null : origin_place.trim();
    }

    public String getHome() {
        return home;
    }

    public void setHome(String home) {
        this.home = home == null ? null : home.trim();
    }

    public String getIndustry() {
        return industry;
    }

    public void setIndustry(String industry) {
        this.industry = industry == null ? null : industry.trim();
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job == null ? null : job.trim();
    }

    public String getInterest() {
        return interest;
    }

    public void setInterest(String interest) {
        this.interest = interest == null ? null : interest.trim();
    }

    public String getSpecialty() {
        return specialty;
    }

    public void setSpecialty(String specialty) {
        this.specialty = specialty == null ? null : specialty.trim();
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature == null ? null : signature.trim();
    }

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public String getInvite_code() {
		return invite_code;
	}

	public void setInvite_code(String invite_code) {
		this.invite_code = invite_code;
	}

	public String getBg_url() {
		return bg_url;
	}

	public void setBg_url(String bg_url) {
		this.bg_url = bg_url;
	}

	public String getArea_name() {
		return area_name;
	}

	public void setArea_name(String area_name) {
		this.area_name = area_name;
	}

	public Integer getIs_kf() {
		return is_kf;
	}

	public void setIs_kf(Integer is_kf) {
		this.is_kf = is_kf;
	}

	public String getQrcode_img() {
		return qrcode_img;
	}

	public void setQrcode_img(String qrcode_img) {
		this.qrcode_img = qrcode_img;
	}

	public String getCompany_name() {
		return company_name;
	}

	public void setCompany_name(String company_name) {
		this.company_name = company_name;
	}

	public String getCompany_link() {
		return company_link;
	}

	public void setCompany_link(String company_link) {
		this.company_link = company_link;
	}

	public String getCompany_address() {
		return company_address;
	}

	public void setCompany_address(String company_address) {
		this.company_address = company_address;
	}

	public Integer getIs_default_photo() {
		return is_default_photo;
	}

	public void setIs_default_photo(Integer is_default_photo) {
		this.is_default_photo = is_default_photo;
	}

	public String getOther_job() {
		return other_job;
	}

	public void setOther_job(String other_job) {
		this.other_job = other_job;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

    public String getReferral_mobile() {
        return referral_mobile;
    }

    public void setReferral_mobile(String referral_mobile) {
        this.referral_mobile = referral_mobile;
    }

    public String getLast_login_city() {
        return last_login_city;
    }

    public void setLast_login_city(String last_login_city) {
        this.last_login_city = last_login_city;
    }

    public String getReferrer_link() {
        return referrer_link;
    }

    public void setReferrer_link(String referrer_link) {
        this.referrer_link = referrer_link;
    }
}