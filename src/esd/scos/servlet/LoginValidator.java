package esd.scos.servlet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;



public class LoginValidator extends HttpServlet {
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 7949203117726021945L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp){
		
		doPost(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
		
		resp.setContentType("application/json;charset=UTF-8");
		
		String acceptjson = "";
		
		try {
			
			//获取接收到的数据
			BufferedReader br = new BufferedReader(new InputStreamReader(req.getInputStream(), "UTF-8"));
			StringBuilder sb = new StringBuilder("");
			String temp;
			while((temp = br.readLine())!=null) {
				sb.append(temp);
			}
			br.close();
			acceptjson = sb.toString();
			
			System.out.println(acceptjson);
			
			JSONObject respjson = new JSONObject();
			
			//开始处理接收到的数据
			if(acceptjson != "") {
				JSONObject jsonObject = JSONObject.fromObject(acceptjson);
				respjson = judgeReq(jsonObject);
			}
			
			//发送返回的数据
			PrintWriter out = resp.getWriter();
			out.write(respjson.toString());
			out.flush();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Override
    public String getServletInfo() {
        return "LoginValidator";
    }
	
	/**
	 * 根据json来判断登录是否成功，并且返回应当返回发送的json
	 * @param jsonObject
	 * @return
	 */
	private JSONObject judgeReq(JSONObject jsonObject) {
		boolean isTrue = false;
		JSONObject resp = new JSONObject();
		String userName = jsonObject.getString("userName");
		String password = jsonObject.getString("password");
		boolean isRegister = jsonObject.getBoolean("isRegister");
		
		if(userName == null || password == null) {
			isTrue = false;
		}else {
			if(isRegister == true) {
				//TODO save the user info
				isTrue = true;
			}else {
				//TODO judge the user info
				isTrue = true;
			}
		}
		
		if(isTrue) {
			resp.put("RESULTCODE", 1);
		}else {
			resp.put("RESULTCODE", 0);
		}
		
		return resp;
	}

}
