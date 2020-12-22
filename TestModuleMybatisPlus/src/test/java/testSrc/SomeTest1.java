package testSrc;

import java.text.SimpleDateFormat;
import java.util.Date;


public class SomeTest1 {

	public static void main_1(String[] args) {
		
		String tmpStr = "中移在线服务有限公司\\数字服务园区分公司（淮安）\\淮安建运一体化团队";
	
		if(tmpStr.contains("\\"))
		{
			String[] strArr = tmpStr.split("\\\\");
			
			System.out.println("tmpStr 被分割成了" + strArr.length + "块!");
			System.out.println("截取需要的字符串:" + strArr[strArr.length-1] + "!");
		}
		else
		{
			System.out.println("tmpStr不包含\\");
		}
		
		String tmpStr2 = tmpStr.replace("\\", "-");
		
		System.out.println("tmpStr2:" + tmpStr2);
		
		System.out.println(nowDateToStamp("yyyyMMddHHmmss"));
	
	}
	
	public static void main(String[] args)
	{
		for(int i = 0;i < 10;i ++)
		{
			System.out.println(String.valueOf(getRandomValue()));
		}
		
		
	}
	
    public static String nowDateToStamp(String format) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
        String date = simpleDateFormat.format(new Date());
        return date;
    }
	
    public static int getRandomValue()
    {
    	int randomNum = (int) (System.currentTimeMillis()%Integer.MAX_VALUE - 10000000);
    	final double d = Math.random();
    	final int randomNum2 = (int)(d*10000000);
    	
    	
    	return randomNum + randomNum2;
    }
}
