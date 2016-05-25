package com.swtec.sw.utils;

import java.math.BigDecimal;
import java.util.Random;

import org.apache.commons.lang3.StringUtils;

public class MyStringUtil extends StringUtils {

	/**
	 * 将对象转成string
	 * @param obj
	 * @return
	 */
	public static String obj2Str(Object obj){
		if(null == obj){
			return null;
		}
		return obj.toString();
	}
	
	/**
	 * 将字符串转成long
	 */
	public static Long str2Long(String str) {
		if (StringUtils.isEmpty(str)) {
			return null;
		}
		return Long.parseLong(str);
	}

	/**
	 * 将字符串转换Integer类型
	 */
	public static Integer str2Integer(String str) {
		if (StringUtils.isEmpty(str)) {
			return null;
		}
		return Integer.valueOf(str);
	}
	
	/**
	 * 将Integer转字符串类型
	 */
	public static String Integer2str(Integer interger) {
		if (null == interger) {
			return null;
		}
		return interger.toString();
	}

	/**
	 * 将字符串转出double类型
	 */
	public static Double str2Double(String str) {
		if (StringUtils.isEmpty(str)) {
			return null;
		}
		return Double.valueOf(str);
	}

	
	/**
	 * 将字符串转成float类型
	 */
	public static Float toFloat(String str) {
		if (StringUtils.isEmpty(str)) {
			return null;
		}
		return Float.valueOf(str);
	}
	
	/**
	 * 将Interge转成short类型
	 */
	public static Short interger2Short(Integer i){
		if(null == i){
			return null;
		}
		return Short.valueOf(i.toString());
	}
	
	/**
	 * 将String转成short类型
	 */
	public static Short str2Short(String str){
		if(StringUtils.isEmpty(str)){
			return null;
		}
		return Short.valueOf(str);
	}

	/**
	 *将string转成 BigDecimal类型
	 * @return
	 */
	public static BigDecimal str2BigDecimal(String str){
		if(StringUtils.isEmpty(str)){
			return null;
		}
		return BigDecimal.valueOf(str2Double(str));
	}
	
	/**
	 *将BigDecimal转成 string类型
	 * @return
	 */
	public static String bigDecimal2Str(BigDecimal bigD){
		if(null == bigD){
			return null;
		}
		return bigD.toString();
	}
	
	/**
	 *将double转成 BigDecimal类型
	 * @return
	 */
	public static BigDecimal double2BigDecimal(Double double1){
		if(null == double1){
			return null;
		}
		return BigDecimal.valueOf(double1);
	}
	
	/**
	 *将BigDecimal转成 Integer类型
	 * @return
	 */
	public static Integer bigDecimal2Integer(BigDecimal bigDecimal){
		if(null == bigDecimal){
			return null;
		}
		String bigD = bigDecimal2Str(bigDecimal);
		return Integer.parseInt(bigD);
	}
	
	/**
	 * 截取字符串最后几位
	 */
	public static String getStrLast(String str,int num){
		int strLen = str.length();
		if(MyStringUtil.isEmpty(str) || strLen <num){
			return "";
		}
		if(num > 0){
			return str.substring(strLen-num, strLen);
		}
		return "";
	}
	
	/**
	 * 数字string不满m位的前面补0
	 * @return string
	 */
	public static String getNumStrByLen(String strnum,int m){
		int numStrLen = strnum.length();
		StringBuffer sb = new StringBuffer();
		if(m > numStrLen){
			for(int i=0;i<(m-numStrLen);i++){
				sb.append("0");
			}
			sb.append(strnum);
			return sb.toString();
		}
		return strnum;
	}
	
	/**
	 * 会员卡 16位最后一位数字的验证
	 * */
	public static String createLuhnBit(String cardNo){
		int[] CI = new int[]{2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1};
	    int i,d,result;
	    int chk_dig=0;
	    int length = cardNo.length();
	    for ( i=0; i<length; i++ )
	    {
	        d = Integer.parseInt(cardNo.substring(length - i - 1, length - i));
	        result = d * CI[i];
	        chk_dig += result/10 + result%10;
	    }
	    chk_dig = 10 - chk_dig%10;
	    chk_dig = (chk_dig==10) ? 0 : chk_dig;
	    return cardNo+chk_dig;
    }
	
	/**
	 * String类型补0  右边和左边补0
	 * @param str
	 * @param strLength
	 * @return
	 */
	 public static String addZeroForNum(String str, int strLength) {
	     int strLen = str.length();
	     StringBuffer sb = null;
	     while (strLen < strLength) {
	           sb = new StringBuffer();
	         //sb.append("0").append(str);// 左(前)补0
	        sb.append(str).append("0");//右(后)补0
	           str = sb.toString();
	           strLen = str.length();
	     }
	     return str;
	 }
		/**
		 * String类型补*  右边和左边补0
		 * @param str
		 * @param strLength
		 * @return
		 */
		 public static String addEndName(String str, int strLength) {
		     int strLen = str.length();
		     StringBuffer sb = null;
		     while (strLen < strLength) {
		           sb = new StringBuffer();
		         //sb.append("0").append(str);// 左(前)补0
		        sb.append(str).append("*");//右(后)补0
		           str = sb.toString();
		           strLen = str.length();
		     }
		     return str;
		 }
		
	 /**
	  * String类型补0  右边和左边补0
	  * @param str
	  * @param strLength
	  * @return
	  */
	 public static String addEndForNum(String str, int strLength) {
	     int strLen = str.length();
	     StringBuffer sb = null;
	     while (strLen < strLength) {
	           sb = new StringBuffer();
	         sb.append("0").append(str);// 左(前)补0
	           str = sb.toString();
	           strLen = str.length();
	     }
	     return str;
	 }

	 /**
	  * 去空格
	  */
	 public static String trimStr(String str){
		 if(!StringUtils.isEmpty(str)){
			 return "";
		 }
		 return StringUtils.trim(str);
	 }

	 //根据传的位数随机数字
	    public static String random(int n) {
	        if (n < 1 || n > 10) {
	            throw new IllegalArgumentException("cannot random " + n + " bit number");
	        }
	        Random ran = new Random();
	        if (n == 1) {
	            return String.valueOf(ran.nextInt(10));
	        }
	        int bitField = 0;
	        char[] chs = new char[n];
	        for (int i = 0; i < n; i++) {
	            while(true) {
	                int k = ran.nextInt(10);
	                if( (bitField & (1 << k)) == 0) {
	                    bitField |= 1 << k;
	                    chs[i] = (char)(k + '0');
	                    break;
	                }
	            }
	        }
	        return new String(chs);
	    }
	    /**  
	    * 提供精确的乘法运算。  
	    * @param v1 被乘数  
	    * @param v2 乘数  
	    * @return 两个参数的积  
	    */  
	    public static double mul(double v1,double v2){   
	    BigDecimal b1 = new BigDecimal(Double.toString(v1));   
	    BigDecimal b2 = new BigDecimal(Double.toString(v2));   
	    return b1.multiply(b2).doubleValue();   
	    } 

}
