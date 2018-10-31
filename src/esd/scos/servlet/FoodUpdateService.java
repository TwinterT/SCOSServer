package esd.scos.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import esd.scos.util.FoodItem;
import net.sf.json.JSONObject;

public class FoodUpdateService extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 9111437632282507886L;
	
	public static long totalLength = 0;
	public static long times = 0;
	public static long totalTime = 0;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)  {
		
		resp.setContentType("application/json;charset=UTF-8");
		
		PrintWriter printWriter;
		try {
			printWriter = resp.getWriter();
			JSONObject respJson = createRandomMessage();
			System.out.println("-------------------------------------------------------");
			System.out.println(respJson.toString());
			System.out.println("-------------------------------------------------------");
			totalLength += respJson.toString().length();
	
			System.out.println("第"+times+"次，长度为："+respJson.toString().length()+"，总长度为："+totalLength);
			System.out.println("=======================================================");
			
			printWriter.write(respJson.toString());
			printWriter.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp){
		doGet(req, resp);
	}
	
	
	/**
	 * 产生数据json文件
	 * @return
	 */
	private JSONObject createRandomMessage() {
		JSONObject jsonObject = new JSONObject();
		Random random = new Random();
		
		
		long start = System.currentTimeMillis();
		//获得菜品
		int type = random.nextInt(4)+1;
		//要变更的位置
		int pos;
		switch (type) {
		case FoodItem.HOT_FOOD:
			pos = random.nextInt(FoodItem.hot_food_name.length);
			break;
		case FoodItem.COLD_FOOD:
			pos = random.nextInt(FoodItem.cold_food_name.length);
			break;
		case FoodItem.SEE_FOOD:
			pos = random.nextInt(FoodItem.see_food_name.length);
			break;
		default:
			pos = random.nextInt(FoodItem.drink_name.length);
			break;
		}
		
		//变更的价格
		int price = random.nextInt(40)+20;
		
		//变更的库存量
		int storage = random.nextInt(30)+10;
		
		//生成数据
		jsonObject.put("type",type);
		jsonObject.put("pos", pos);
		jsonObject.put("price", price);
		jsonObject.put("storage", storage);
		
		long stop = System.currentTimeMillis();
		System.out.println("=======================================================");
		totalTime += stop - start;
		times++;
		System.out.println("第"+times+"次，耗时为："+(stop - start)+"，总耗时为："+totalTime);
		System.out.println("-------------------------------------------------------");
		
		return jsonObject;
	}

}
