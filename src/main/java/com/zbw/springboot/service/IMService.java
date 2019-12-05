package com.zbw.springboot.service;


import com.alibaba.fastjson.JSON;
import com.zbw.springboot.pojo.Customer;
import com.zbw.springboot.util.CheckSumBuilder;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import java.util.*;

@Component("imService")
public class IMService {
	private final static Logger logger = Logger.getLogger(IMService.class);

	// 创建网易云账号接口
	private static final String create_accid_url = "https://api.netease.im/nimserver/user/create.action";
	
	// 更新网易云token接口
	private static final String update_token_url = "https://api.netease.im/nimserver/user/update.action";
		
	//更新网易云用户名片
	private static final String update_uinfo_url="https://api.netease.im/nimserver/user/updateUinfo.action";
	
	// 获取用户名片信息
	private static final String get_uinfo_url="https://api.netease.im/nimserver/user/getUinfos.action";
	
	// 黑名单操作
	private static final String black_list_url="https://api.netease.im/nimserver/user/setSpecialRelation.action";
	
	//创建群
	private static final String create_team_url="https://api.netease.im/nimserver/team/create.action";
	
	//加人入群
	private static final String add_team_url="https://api.netease.im/nimserver/team/add.action";
	
	//主动退群
	private static final String leave_team_url="https://api.netease.im/nimserver/team/leave.action";
	
	//群消息提醒开关
	private static final String muteTeam_team_url="https://api.netease.im/nimserver/team/muteTeam.action";
	
	// 发送消息
	private static final String send_msg_url = "https://api.netease.im/nimserver/msg/sendMsg.action";
	
	// 批量发送消息
	private static final String send_batch_msg_url = "https://api.netease.im/nimserver/msg/sendBatchMsg.action";
	
	// 移交群主
	private static final String change_owner_team_url = "https://api.netease.im/nimserver/team/changeOwner.action";
	
	// 更新群信息
	private static final String update_team_url = "https://api.netease.im/nimserver/team/update.action";
	
	//查询群信息
	private static final String query_team_url="https://api.netease.im/nimserver/team/query.action";
	
	// 删除聊天群
	private static final String remove_team_url = "https://api.netease.im/nimserver/team/remove.action";
	
	// 踢人出群
	private static final String kick_url = "https://api.netease.im/nimserver/team/kick.action";
	
	
	
	public static void main(String[] args) {

		Customer c=new Customer();
		c.setCustomer_id((long) 1573157311);
		c.setSurname("郑");
		c.setName("丽丽");
		c.setPhoto("http://yuejian-app.oss-cn-shenzhen.aliyuncs.com/zupu/201710201923222322.png");
		c.setSex(1);
		c.setAge(47);
        String token="65db42ec3364b734ae169f8812954c06";
        long customerId=1573157311;
		IMService im = new IMService();
		im.updateToken(customerId,token);
	}

	public void getAccount(long... customer_ids){
		String jsonStr=JSON.toJSONString(customer_ids);
		List<NameValuePair> nvps = new ArrayList<NameValuePair>();
		nvps.add(new BasicNameValuePair("accids", jsonStr));
		logger.error(">>>>>>>>>>>>>>>>>>>>"+JSON.toJSON(nvps));
		String response = httpPost(get_uinfo_url, nvps);
		logger.error("#############################"+response);
		System.out.println("返回结果"+response);
	}
	
	/**
	 * 创建网易云账号
	 * @param customer
	 * @return
	 */
	public void createAccount(Customer customer){
		List<NameValuePair> nvps = new ArrayList<NameValuePair>();
		nvps.add(new BasicNameValuePair("accid", customer.getCustomer_id().toString()));
		nvps.add(new BasicNameValuePair("name", customer.getSurname()+customer.getName()));
		nvps.add(new BasicNameValuePair("icon", customer.getPhoto()));
		logger.error(">>>>>>>>>>>>>>>>>>>>"+JSON.toJSON(nvps));
		String response = httpPost(create_accid_url, nvps);
		logger.error("#############################"+response);
	}
	
	/**
	 * 更新token
	 * @param customerId
	 * @param token
	 * @return
	 */
	public void updateToken(long customerId, String token){
		List<NameValuePair> nvps = new ArrayList<NameValuePair>();
		nvps.add(new BasicNameValuePair("accid", customerId+""));
		logger.error(">>>>>>>>>>>>>>>>>>>>"+JSON.toJSON(nvps));
		String response = httpPost(update_token_url, nvps);
		logger.error("#############################"+response);
	}
	
	public void updateUinfo(Customer c){
		List<NameValuePair> nvps = new ArrayList<NameValuePair>();
		nvps.add(new BasicNameValuePair("accid", c.getCustomer_id()+""));
		nvps.add(new BasicNameValuePair("name", ""+c.getSurname()+c.getName()));
		nvps.add(new BasicNameValuePair("icon", ""+c.getPhoto()));
		nvps.add(new BasicNameValuePair("gender",c.getSex()==null?"1":"2"));
		
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("age", c.getAge());
		map.put("family_area",(c.getFamily_area()!=null?c.getFamily_area():""));
		map.put("is_family_master", c.getIs_family_master());
		map.put("master_area_name", c.getMaster_area_name());
		map.put("is_kf", c.getIs_kf());
		String jsonStr=JSON.toJSONString(map);
		nvps.add(new BasicNameValuePair("ex",jsonStr));
		logger.error(">>>>>>>>>>>>>>>>>>>>"+JSON.toJSON(nvps));
		String response = httpPost(update_uinfo_url, nvps);
		logger.error("#############################"+response);
	}
	
	/**
	 * 加入黑名单
	 * @param customerId 操作者
	 * @param opCustomerId 被拉黑者
	 */
	public void blacklist(long customerId, long opCustomerId){
		List<NameValuePair> nvps = new ArrayList<NameValuePair>();
		nvps.add(new BasicNameValuePair("accid", customerId+""));
		nvps.add(new BasicNameValuePair("targetAcc", opCustomerId+""));
		nvps.add(new BasicNameValuePair("relationType", "1"));
		nvps.add(new BasicNameValuePair("value", "1"));
		logger.error(">>>>>>>>>>>>>>>>>>>>"+JSON.toJSON(nvps));
		String response = httpPost(black_list_url, nvps);
		logger.error("#############################"+response);
	}
	
	/**
	 * 取消黑名单
	 * @param customerId
	 * @param opCustomerId
	 */
	public void unBlacklist(long customerId, long opCustomerId){
		List<NameValuePair> nvps = new ArrayList<NameValuePair>();
		nvps.add(new BasicNameValuePair("accid", customerId+""));
		nvps.add(new BasicNameValuePair("targetAcc", opCustomerId+""));
		nvps.add(new BasicNameValuePair("relationType", "1"));
		nvps.add(new BasicNameValuePair("value", "0"));
		logger.error(">>>>>>>>>>>>>>>>>>>>"+JSON.toJSON(nvps));
		String response = httpPost(black_list_url, nvps);
		logger.error("#############################"+response);
	}
	
	/**
	 * 创建网易云聊天群
	 * @param tname 群名
	 * @param ownerId 群主id
	 * @param memberIds 群成员(200以内)
	 * @return
	 */
	public String createTeam(String tname, long ownerId, long... memberIds){
		String members = JSON.toJSON(memberIds).toString();
		
		List<NameValuePair> nvps = new ArrayList<NameValuePair>();
		nvps.add(new BasicNameValuePair("tname",tname));
		nvps.add(new BasicNameValuePair("owner", ownerId+""));
		nvps.add(new BasicNameValuePair("members", members));
		nvps.add(new BasicNameValuePair("msg", "msg"));
		nvps.add(new BasicNameValuePair("magree", "0"));
		nvps.add(new BasicNameValuePair("joinmode", "0"));
		nvps.add(new BasicNameValuePair("invitemode", "1"));
		nvps.add(new BasicNameValuePair("beinvitemode", "1"));
		nvps.add(new BasicNameValuePair("uptinfomode", "1"));
		logger.error(">>>>>>>>>>>>>>>>>>>>"+JSON.toJSON(nvps));
		String response = httpPost(create_team_url, nvps);
		logger.error("#############################"+response);
		return JSON.parseObject(response).getString("tid");
	}
	
	/**
	 * 创建网易云家族群聊
	 * @param tname 群名
	 * @param ownerId 群主id
	 * @return
	 */
	public String createTeam(String tname, long ownerId) {
		List<NameValuePair> nvps = new ArrayList<NameValuePair>();
		nvps.add(new BasicNameValuePair("tname",tname));
		nvps.add(new BasicNameValuePair("owner", ownerId+""));
		nvps.add(new BasicNameValuePair("members", "[]"));
		nvps.add(new BasicNameValuePair("msg", "msg"));
		nvps.add(new BasicNameValuePair("magree", "0"));
		nvps.add(new BasicNameValuePair("joinmode", "0"));
		nvps.add(new BasicNameValuePair("invitemode", "1"));
		nvps.add(new BasicNameValuePair("beinvitemode", "1"));
		nvps.add(new BasicNameValuePair("uptinfomode", "1"));
		logger.error(">>>>>>>>>>>>>>>>>>>>"+JSON.toJSON(nvps));
		String response = httpPost(create_team_url, nvps);
		logger.error("#############################"+response);
		return JSON.parseObject(response).getString("tid");
	}
	
	/**
	 * 创建网易云群聊
	 * @param tname 群名
	 * @param ownerId 群主id
	 * @param team_type 0:家族群聊, 1:自建群聊, 2:宗亲会广场
	 * @return
	 */
	public String createTeam(String tname, long ownerId, int team_type) {
		Map<String, Object> customMap = new HashMap<String, Object>(){
			{
				put("type", team_type);
			}
		};
		String custom = JSON.toJSON(customMap).toString();
		
		List<NameValuePair> nvps = new ArrayList<NameValuePair>();
		nvps.add(new BasicNameValuePair("tname",tname));
		nvps.add(new BasicNameValuePair("owner", ownerId+""));
		nvps.add(new BasicNameValuePair("members", "[]"));
		nvps.add(new BasicNameValuePair("msg", "msg"));
		nvps.add(new BasicNameValuePair("magree", "0"));
		nvps.add(new BasicNameValuePair("joinmode", "0"));
		nvps.add(new BasicNameValuePair("custom", custom));
		nvps.add(new BasicNameValuePair("invitemode", "1"));
		nvps.add(new BasicNameValuePair("beinvitemode", "1"));
		nvps.add(new BasicNameValuePair("uptinfomode", "1"));
		logger.error(">>>>>>>>>>>>>>>>>>>>"+JSON.toJSON(nvps));
		String response = httpPost(create_team_url, nvps);
		logger.error("#############################"+response);
		return JSON.parseObject(response).getString("tid");
	}
	
	/**
	 * 创建网易云群聊
	 * @param tname 群名
	 * @param ownerId 群主id
	 * @param team_type 0:家族群聊, 1:自建群聊, 2:宗亲会广场
	 * @param customMap 自定义聊天群数据
	 * @return
	 */
	/*public String createTeam(String tname, long ownerId, int team_type, Map<String, Object> customMap) {
		String custom = JSON.toJSON(customMap).toString();
		
		List<NameValuePair> nvps = new ArrayList<NameValuePair>();
		nvps.add(new BasicNameValuePair("tname",tname));
		nvps.add(new BasicNameValuePair("owner", ownerId+""));
		nvps.add(new BasicNameValuePair("members", "[]"));
		nvps.add(new BasicNameValuePair("msg", "msg"));
		nvps.add(new BasicNameValuePair("magree", "0"));
		nvps.add(new BasicNameValuePair("joinmode", "0"));
		nvps.add(new BasicNameValuePair("custom", custom));
		nvps.add(new BasicNameValuePair("invitemode", "1"));
		nvps.add(new BasicNameValuePair("beinvitemode", "1"));
		nvps.add(new BasicNameValuePair("uptinfomode", "1"));
		logger.error(">>>>>>>>>>>>>>>>>>>>"+JSON.toJSON(nvps));
		String response = httpPost(create_team_url, nvps);
		logger.error("#############################"+response);
		return JSON.parseObject(response).getString("tid");
	}*/
	
	/**
	 * 解散群
	 * @param tid
	 * @param ownerId
	 * @return
	 */
	public String removeTeam(long tid, long ownerId) {
		List<NameValuePair> nvps = new ArrayList<NameValuePair>();
		nvps.add(new BasicNameValuePair("tid",tid+""));
		nvps.add(new BasicNameValuePair("owner", ownerId+""));
		logger.error(">>>>>>>>>>>>>>>>>>>>"+JSON.toJSON(nvps));
		String response = httpPost(remove_team_url, nvps);
		logger.error("#############################"+response);
		return JSON.parseObject(response).getString("code");
	}
	
	/**
	 * 加入聊天群
	 * @param tid
	 * @param ownerId 群主accid
	 * @param memberId 进群成员accid
	 */
	public String addTeam(String tid, long ownerId, Long... memberIds){
		String members = JSON.toJSON(memberIds).toString();
		
		List<NameValuePair> nvps = new ArrayList<NameValuePair>();
		nvps.add(new BasicNameValuePair("tid",tid));
		nvps.add(new BasicNameValuePair("owner", ownerId+""));
		nvps.add(new BasicNameValuePair("members", members));
		nvps.add(new BasicNameValuePair("msg", "msg"));
		nvps.add(new BasicNameValuePair("magree", "0"));
		nvps.add(new BasicNameValuePair("joinmode", "0"));
		logger.error(">>>>>>>>>>>>>>>>>>>>"+JSON.toJSON(nvps));
		String response = httpPost(add_team_url, nvps);
		logger.error("#############################"+response);
		return JSON.parseObject(response).getString("code");
	}
	
	/**
	 * 离开群
	 * @param tid
	 * @param accid
	 */
	public String leaveTeam(String tid, long accid){
		List<NameValuePair> nvps = new ArrayList<NameValuePair>();
		nvps.add(new BasicNameValuePair("tid", tid));
		nvps.add(new BasicNameValuePair("accid", accid+""));
		logger.error(">>>>>>>>>>>>>>>>>>>>"+JSON.toJSON(nvps));
		String response = httpPost(leave_team_url, nvps);
		logger.error("#############################"+response);
		return JSON.parseObject(response).getString("code");
	}
	
	/**
	 * 群消息提醒开关
	 * @param tid
	 * @param accid
	 * @param ope 0：关闭，1：打开
	 */
	public void muteTeam(String tid, long accid, int ope){
		ope = ope == 0 ? 1 : 2;
		List<NameValuePair> nvps = new ArrayList<NameValuePair>();
		nvps.add(new BasicNameValuePair("tid", tid));
		nvps.add(new BasicNameValuePair("accid", accid+""));
		nvps.add(new BasicNameValuePair("ope", ope+""));
		logger.error(">>>>>>>>>>>>>>>>>>>>"+JSON.toJSON(nvps));
		String response = httpPost(muteTeam_team_url, nvps);
		logger.error("#############################"+response);
	}
	
	/**
	 * 透传消息至聊天群内
	 * @param customer_id 发起消息者id
	 * @param t_id 聊天群id
	 * @param body_data body 内自定义数据结构
	 */
	public void passthroughToChatGroup(long customer_id, long t_id, Map<String, Object> body_data, Map<String, Object> options){
		List<NameValuePair> nvps = new ArrayList<NameValuePair>();
		nvps.add(new BasicNameValuePair("from", customer_id+""));
		nvps.add(new BasicNameValuePair("ope", "1"));
		nvps.add(new BasicNameValuePair("to", t_id+""));
		nvps.add(new BasicNameValuePair("type", "100"));
		
		if(options == null){
			options = new HashMap<String, Object>();
		}
		options.put("push", false); // 透传，不触发消息通知提示
		
		String option = JSON.toJSONString(options);
		nvps.add(new BasicNameValuePair("option", option));
		
		Map<String, Object> body = new HashMap<String, Object>();
		body.put("type", 6);
		body.put("data", body_data);
		String jsonStr=JSON.toJSONString(body);
		
		nvps.add(new BasicNameValuePair("body", jsonStr));
		logger.error(">>>>>>>>>>>>>>>>>>>>"+JSON.toJSON(nvps));
		String response = httpPost(send_msg_url, nvps);
		logger.error("#############################"+response);
	}
		
	/**
	 * 批量发送点对点消息
	 * @param fromAccid 发消息者id
	 * @param toAccids 多个接收者id 【注意:最多500人】
	 * @param body_data
	 */
	public void sendToCustomers(long fromAccid, List<Long> toAccids, Map<String, Object> body_data){
		this.sendToCustomers(fromAccid, toAccids, body_data, 8, "");
	}
	
	public void sendToCustomers(long fromAccid, List<Long> toAccids, Map<String, Object> body_data, int type, String pushcontent){
		if(toAccids.size() > 500){
			logger.error(fromAccid+" 用户批量发出消息, 由于代码没处理接受者500人一批次发送消息, 抛弃今次推送。 接收人数:"+toAccids.size());
			return;
		}
		
		String jsonStr = JSON.toJSONString(toAccids);
		List<NameValuePair> nvps = new ArrayList<NameValuePair>();
		nvps.add(new BasicNameValuePair("fromAccid", fromAccid+""));
		nvps.add(new BasicNameValuePair("toAccids", jsonStr));
		nvps.add(new BasicNameValuePair("type", "100"));
		
		Map<String, Object> body = new HashMap<String, Object>();
		body.put("type", type);
		body.put("data", body_data);
		String bodyStr = JSON.toJSONString(body);
		
		nvps.add(new BasicNameValuePair("body", bodyStr));
		if(pushcontent != null && pushcontent.trim().length() > 0){
			nvps.add(new BasicNameValuePair("pushcontent", pushcontent));
		}
		logger.error(">>>>>>>>>>>>>>>>>>>>"+JSON.toJSON(nvps));
		String response = httpPost(send_batch_msg_url, nvps);
		logger.error("#############################"+response);
		
		String code = JSON.parseObject(response).getString("code");
		if(code.equals("416")){
			// 该接口一分钟调用超过120次会返回416状态码，并屏蔽一段时间
			logger.error("批量发送消息接口超出网易云每分钟调用120次规定, 现在改为循环一对一发送消息");
			/*for (long toId : toAccids) {
				nvps = new ArrayList<NameValuePair>();
				nvps.add(new BasicNameValuePair("from", fromAccid+""));
				nvps.add(new BasicNameValuePair("ope", "0"));
				nvps.add(new BasicNameValuePair("to", toId+""));
				nvps.add(new BasicNameValuePair("type", "100"));
				nvps.add(new BasicNameValuePair("body", bodyStr));
				nvps.add(new BasicNameValuePair("pushcontent", "宗亲会加入邀请"));
				response = httpPost(send_msg_url, nvps);
				logger.error("#############################"+response);
			}*/
		}
	}
	
	/**
	 * 批量发送点对点消息(邀请进宗亲会)
	 * @param fromAccid 发消息者id
	 * @param toAccids 多个接收者id 【注意:最多500人】
	 * @param body_data
	 *//*
	public void sendToClanAssociationInviteMsg(long fromAccid, List<Long> toAccids, Map<String, Object> body_data){
		if(toAccids.size() > 500){
			logger.error(fromAccid+" 用户批量发出消息, 由于代码没处理接受者500人一批次发送消息, 抛弃今次推送。 接收人数:"+toAccids.size());
			return;
		}
		
		String jsonStr = JSON.toJSONString(toAccids);
		List<NameValuePair> nvps = new ArrayList<NameValuePair>();
		nvps.add(new BasicNameValuePair("fromAccid", fromAccid+""));
		nvps.add(new BasicNameValuePair("toAccids", jsonStr));
		nvps.add(new BasicNameValuePair("type", "100"));
		nvps.add(new BasicNameValuePair("pushcontent", "宗亲会加入邀请"));
		
		Map<String, Object> body = new HashMap<String, Object>();
		body.put("type", 9);
		body.put("data", body_data);
		String bodyStr = JSON.toJSONString(body);
		
		nvps.add(new BasicNameValuePair("body", bodyStr));
		logger.error(">>>>>>>>>>>>>>>>>>>>"+JSON.toJSON(nvps));
		String response = httpPost(send_batch_msg_url, nvps);
		logger.error("#############################"+response);
		
		String code = JSON.parseObject(response).getString("code");
		if(code.equals("416")){
			// 该接口一分钟调用超过120次会返回416状态码，并屏蔽一段时间
			logger.error("批量发送消息接口超出网易云每分钟调用120次规定, 现在改为循环一对一发送消息");
			for (long toId : toAccids) {
				nvps = new ArrayList<NameValuePair>();
				nvps.add(new BasicNameValuePair("from", fromAccid+""));
				nvps.add(new BasicNameValuePair("ope", "0"));
				nvps.add(new BasicNameValuePair("to", toId+""));
				nvps.add(new BasicNameValuePair("type", "100"));
				nvps.add(new BasicNameValuePair("body", bodyStr));
				nvps.add(new BasicNameValuePair("pushcontent", "宗亲会加入邀请"));
				response = httpPost(send_msg_url, nvps);
				logger.error("#############################"+response);
			}
		}
	}*/
	
	/**
	 * 批量发送点对点消息(宗亲会收款)
	 * @param fromAccid 发消息者id
	 * @param toAccids 多个接收者id 【注意:最多500人】
	 * @param body_data
	 */
	/*public void sendToClanAssociationPayInviteMsg(long fromAccid, List<Long> toAccids, Map<String, Object> body_data){
		if(toAccids.size() > 500){
			logger.error(fromAccid+" 用户批量发出消息, 由于代码没处理接受者500人一批次发送消息, 抛弃今次推送。 接收人数:"+toAccids.size());
			return;
		}
		
		String jsonStr = JSON.toJSONString(toAccids);
		List<NameValuePair> nvps = new ArrayList<NameValuePair>();
		nvps.add(new BasicNameValuePair("fromAccid", fromAccid+""));
		nvps.add(new BasicNameValuePair("toAccids", jsonStr));
		nvps.add(new BasicNameValuePair("type", "100"));
		nvps.add(new BasicNameValuePair("pushcontent", "发起收款"));
		
		Map<String, Object> body = new HashMap<String, Object>();
		body.put("type", 10);
		body.put("data", body_data);
		String bodyStr = JSON.toJSONString(body);
		
		nvps.add(new BasicNameValuePair("body", bodyStr));
		logger.error(">>>>>>>>>>>>>>>>>>>>"+JSON.toJSON(nvps));
		String response = httpPost(send_batch_msg_url, nvps);
		logger.error("#############################"+response);
		
		String code = JSON.parseObject(response).getString("code");
		if(code.equals("416")){
			// 该接口一分钟调用超过120次会返回416状态码，并屏蔽一段时间
			logger.error("批量发送消息接口超出网易云每分钟调用120次规定, 现在改为循环一对一发送消息");
			for (long toId : toAccids) {
				nvps = new ArrayList<NameValuePair>();
				nvps.add(new BasicNameValuePair("from", fromAccid+""));
				nvps.add(new BasicNameValuePair("ope", "0"));
				nvps.add(new BasicNameValuePair("to", toId+""));
				nvps.add(new BasicNameValuePair("type", "100"));
				nvps.add(new BasicNameValuePair("body", bodyStr));
				nvps.add(new BasicNameValuePair("pushcontent", "宗亲会加入邀请"));
				response = httpPost(send_msg_url, nvps);
				logger.error("#############################"+response);
			}
		}
	}*/
	
	/**
	 * 批量“透传”点对点消息
	 * @param fromAccid 发消息者id
	 * @param toAccids 多个接收者id 【注意:最多500人】
	 * @param body_data
	 */
	public void passthroughToCustomers(long fromAccid, List<Long> toAccids, Map<String, Object> body_data){
		if(toAccids.size() > 500){
			logger.error(fromAccid+" 用户批量发出消息, 由于代码没处理接受者500人一批次发送消息, 抛弃今次推送。 接收人数:"+toAccids.size());
			return;
		}
		
		String jsonStr = JSON.toJSONString(toAccids);
		List<NameValuePair> nvps = new ArrayList<NameValuePair>();
		nvps.add(new BasicNameValuePair("fromAccid", fromAccid+""));
		nvps.add(new BasicNameValuePair("toAccids", jsonStr));
		nvps.add(new BasicNameValuePair("type", "100"));
		nvps.add(new BasicNameValuePair("option", "{'push':false}")); // 透传，不触发消息通知提示
		
		Map<String, Object> body = new HashMap<String, Object>();
		body.put("type", 6);
		body.put("data", body_data);
		String bodyStr = JSON.toJSONString(body);
		
		nvps.add(new BasicNameValuePair("body", bodyStr));
		logger.error(">>>>>>>>>>>>>>>>>>>>"+JSON.toJSON(nvps));
		String response = httpPost(send_batch_msg_url, nvps);
		logger.error("#############################"+response);
		
		String code = JSON.parseObject(response).getString("code");
		if(code.equals("416")){
			// 该接口一分钟调用超过120次会返回416状态码，并屏蔽一段时间
			logger.error("批量发送消息接口超出网易云每分钟调用120次规定, 现在改为循环一对一发送消息");
			for (long toId : toAccids) {
				nvps = new ArrayList<NameValuePair>();
				nvps.add(new BasicNameValuePair("from", fromAccid+""));
				nvps.add(new BasicNameValuePair("ope", "0"));
				nvps.add(new BasicNameValuePair("to", toId+""));
				nvps.add(new BasicNameValuePair("type", "100"));
				nvps.add(new BasicNameValuePair("option", "{'push':false}")); // 透传，不触发消息通知提示
				nvps.add(new BasicNameValuePair("body", bodyStr));
				response = httpPost(send_msg_url, nvps);
				logger.error("#############################"+response);
				try {
					Thread.sleep(30);
				} catch (Exception e) {}
			}
		}
	}
	
	/**
	 * 移交群主
	 * @param tid 网易云群id
	 * @param old_owner_id 旧群主id
	 * @param new_owner_id 新群主id
	 */
	public String changeOwner(String tid, long old_owner_id, long new_owner_id){
		List<NameValuePair> nvps = new ArrayList<NameValuePair>();
		nvps.add(new BasicNameValuePair("tid", tid));
		nvps.add(new BasicNameValuePair("owner", old_owner_id+""));
		nvps.add(new BasicNameValuePair("newowner", new_owner_id+""));
		nvps.add(new BasicNameValuePair("leave", "2")); // 旧群主解除群主后成为普通成员
		String response = httpPost(change_owner_team_url, nvps);
		logger.info("#############################"+response);
		return JSON.parseObject(response).getString("code");
	}

	/**
	 * 更新群名称
	 * @param tid
	 * @param owner_id
	 * @param group_name
	 * @return
	 */
	public String updateTeamName(long tid, long owner_id, String group_name){
		List<NameValuePair> nvps = new ArrayList<NameValuePair>();
		nvps.add(new BasicNameValuePair("tid", tid+""));
		nvps.add(new BasicNameValuePair("owner", owner_id+""));
		nvps.add(new BasicNameValuePair("tname", group_name));
		String response = httpPost(update_team_url, nvps);
		logger.info("#############################"+response);
		return JSON.parseObject(response).getString("code");
	}
	
	/**
	 * 更新群公告
	 * @param tid
	 * @param owner_id
	 * @param announcement
	 * @return
	 */
	public String updateAnnouncement(long tid, long owner_id, String announcement){
		List<NameValuePair> nvps = new ArrayList<NameValuePair>();
		nvps.add(new BasicNameValuePair("tid", tid+""));
		nvps.add(new BasicNameValuePair("owner", owner_id+""));
		nvps.add(new BasicNameValuePair("announcement", announcement));
		String response = httpPost(update_team_url, nvps);
		logger.info("#############################"+response);
		return JSON.parseObject(response).getString("code");
	}
	
	/**
	 * 更新群头像
	 * @param tid
	 * @param owner_id
	 * @param icon_url
	 * @return
	 */
	public String updateGroupIcon(long tid, long owner_id, String icon_url){
		List<NameValuePair> nvps = new ArrayList<NameValuePair>();
		nvps.add(new BasicNameValuePair("tid", tid+""));
		nvps.add(new BasicNameValuePair("owner", owner_id+""));
		nvps.add(new BasicNameValuePair("icon", icon_url));
		String response = httpPost(update_team_url, nvps);
		logger.info("#############################"+response);
		return JSON.parseObject(response).getString("code");
	}
	
	/**
	 * 查询群信息
	 * @param tid
	 * @param ope		1表示带上群成员列表，0表示不带群成员列表，只返回群信息
	 * @param 
	 * @return
	 */
	public String queryTeam(long tid, int ope){
		List<NameValuePair> nvps = new ArrayList<NameValuePair>();
		nvps.add(new BasicNameValuePair("tids", "["+tid+"]"));
		nvps.add(new BasicNameValuePair("ope", ope+""));
		String response = httpPost(query_team_url, nvps);
		logger.info("#############################"+response);
		return JSON.parseObject(response).getString("code");
	}
	
	/**
	 * 群聊踢人
	 * @param tid 网易云群id
	 * @param owner_id 群主id
	 * @param member_id 被踢人id
	 * @return
	 */
	public String kick(long tid, long owner_id, long member_id){
		List<NameValuePair> nvps = new ArrayList<NameValuePair>();
		nvps.add(new BasicNameValuePair("tid", tid+""));
		nvps.add(new BasicNameValuePair("owner", owner_id+""));
		nvps.add(new BasicNameValuePair("member", member_id+""));
		String response = httpPost(kick_url, nvps);
		logger.info("#############################"+response);
		return JSON.parseObject(response).getString("code");
	}

	public static String httpPost(String url, List<NameValuePair> pairs){
		HttpClient client = HttpClients.createDefault();
		HttpPost httpPost = new HttpPost(url);

		String curTime = String.valueOf((new Date()).getTime() / 1000L);
		String checkSum = CheckSumBuilder.getCheckSum(
				"", "123456" , curTime);//参考 计算CheckSum的java代码

		// 设置请求的header开发者平台分配的appkey
		httpPost.addHeader("AppKey", "");
		//随机数（最大长度128个字符）
		httpPost.addHeader("Nonce", "123456");
		//当前UTC时间戳，从1970年1月1日0点0 分0 秒开始到现在的秒数(String)
		httpPost.addHeader("CurTime", curTime);
		//SHA1(AppSecret + Nonce + CurTime)，三个参数拼接的字符串，进行SHA1哈希计算，转化成16进制字符(String，小写)
		httpPost.addHeader("CheckSum", checkSum);
		httpPost.addHeader("Content-Type", "application/x-www-form-urlencoded;charset=utf-8");
		try {
			httpPost.setEntity(new UrlEncodedFormEntity(pairs, "utf-8"));
			HttpResponse response = client.execute(httpPost);
			if(response.getStatusLine().getStatusCode() == 200){
				return EntityUtils.toString(response.getEntity(), "utf-8");
			} else {
				return null;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
