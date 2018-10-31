package esd.scos.servlet;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import esd.scos.util.FoodItem;



public class FoodUpdateServiceXML extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7895125223746192497L;
	
	
	public static long totalLength = 0;
	public static long times = 0;
	public static long totalTime = 0;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException{
		
		resp.setContentType("text/html;charset=UTF-8");
		req.setCharacterEncoding("UTF-8");
		
		Document document = DocumentHelper.createDocument();
		Element food = DocumentHelper.createElement("food");
		document.add(food);
		
		Random random = new Random();
		long start = System.currentTimeMillis();
		//获得菜品
		int type = random.nextInt(4)+1;
		food.addElement("type").addText(""+type);
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
		food.addElement("pos").addText(""+pos);
		
		//变更的价格
		int price = random.nextInt(40)+20;
		food.addElement("price").addText(""+price);
		
		//变更的库存量
		int storage = random.nextInt(30)+10;
		food.addElement("storage").addText(""+storage);
		
		String xml = document.asXML();
		long stop = System.currentTimeMillis();
		
		
		System.out.println("======================================================================");
		System.out.println(xml);
		times += 1;
		totalLength += xml.length();
		System.out.println("----------------------------------------------------------------------");
		System.out.println("第"+times+"次，长度为："+xml.length()+"，总长度为："+totalLength);
		totalTime += stop - start;
		System.out.println("第"+times+"次，耗时为："+(stop - start)+"，总耗时为："+totalTime);
		System.out.println("======================================================================");
		
		resp.setContentType("text/xml; charset=UTF-8"); //设置返回值的类型
		resp.getOutputStream().write(xml.getBytes("UTF-8"));
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		doGet(req, resp);
	}
}
