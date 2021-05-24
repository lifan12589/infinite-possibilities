//package com.infinitePossibilities.utils;
//
//import java.util.HashSet;
//import java.util.Set;
//
//import net.sourceforge.pinyin4j.PinyinHelper;
//import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
//import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
//import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
//import net.sourceforge.pinyin4j.format.HanyuPinyinVCharType;
//import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;
//import wfc.service.log.Log;
//
//public class pinYinUtils {
//
//	public static int wordLength = 4;
//	    /**
//	     * 将字符串中的中文转化为拼音,其他字符不变
//	     *
//	     * @param inputString
//	     * @return
//	     */
//	    public static String getPingYin(String inputString) {
//	        HanyuPinyinOutputFormat format = new HanyuPinyinOutputFormat();
//	        format.setCaseType(HanyuPinyinCaseType.LOWERCASE);
//	        format.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
//	        format.setVCharType(HanyuPinyinVCharType.WITH_V);
//
//	        char[] input = inputString.trim().toCharArray();
//	        String output = "";
//
//	        try {
//	            for (int i = 0; i < input.length; i++) {
//	                if (Character.toString(input[i]).matches("[\\u4E00-\\u9FA5]+")) {
//	                    String[] temp = PinyinHelper.toHanyuPinyinStringArray(input[i], format);
//	                    output += temp[0];
//	                } else
//	                    output += Character.toString(input[i]);
//	            }
//	        } catch (BadHanyuPinyinOutputFormatCombination e) {
//	            e.printStackTrace();
//	        }
//	        return output;
//	    }
//
//	    /**
//	     * 获取汉字串拼音首字母，英文字符不变
//	     * @param chinese 汉字串
//	     * @return 汉语拼音首字母
//	     */
//	    public static String getFirstSpell(String chinese) {
//	            StringBuffer pybf = new StringBuffer();
//	            char[] arr = chinese.toCharArray();
//	            HanyuPinyinOutputFormat defaultFormat = new HanyuPinyinOutputFormat();
//	            defaultFormat.setCaseType(HanyuPinyinCaseType.LOWERCASE);
//	            defaultFormat.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
//	            for (int i = 0; i < arr.length; i++) {
//	                    if (arr[i] > 128) {
//	                            try {
//	                                    String[] temp = PinyinHelper.toHanyuPinyinStringArray(arr[i], defaultFormat);
//	                                    if (temp != null) {
//	                                            pybf.append(temp[0].charAt(0));
//	                                    }
//	                            } catch (BadHanyuPinyinOutputFormatCombination e) {
//	                                    e.printStackTrace();
//	                            }
//	                    } else {
//	                            pybf.append(arr[i]);
//	                    }
//	            }
//	            return pybf.toString().replaceAll("\\W", "").trim();
//	    }
//
//
//
//	    /**
//		 * 获取拼音集合
//		 *
//		 * @param src
//		 * @return Set<String>
//		 */
//		public static Set<String> getPinyin(String src) {
//			if (src != null && !src.trim().equalsIgnoreCase("")) {
//				char[] srcChar;
//				srcChar = src.toCharArray();
//				// 汉语拼音格式输出类
//				HanyuPinyinOutputFormat hanYuPinOutputFormat = new HanyuPinyinOutputFormat();
//
//				// 输出设置，大小写，音标方式等
//				hanYuPinOutputFormat.setCaseType(HanyuPinyinCaseType.LOWERCASE);
//				hanYuPinOutputFormat.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
//				hanYuPinOutputFormat.setVCharType(HanyuPinyinVCharType.WITH_V);
//				// 因为多音字问题，导致在Exchange方法中排序组合后内存溢出，所以根据使用习惯或者查询喜欢，只取前几个文字，解决内存溢出问题
//				if (src.length() <= wordLength) {
//					wordLength = src.length();
//				}
//				String[][] temp = new String[wordLength][];
//				for (int i = 0; i < wordLength; i++) {
//					char c = srcChar[i];
//					// 是中文或者a-z或者A-Z转换拼音(我的需求，是保留中文或者a-z或者A-Z)
//					if (String.valueOf(c).matches("[\\u4E00-\\u9FA5]+")) {
//						try {
//							temp[i] = PinyinHelper.toHanyuPinyinStringArray(
//									srcChar[i], hanYuPinOutputFormat);
//						} catch (BadHanyuPinyinOutputFormatCombination e) {
//							e.printStackTrace();
//						}
//
//					} else if (((int) c >= 65 && (int) c <= 90)
//							|| ((int) c >= 97 && (int) c <= 122)) {
//						temp[i] = new String[] { String.valueOf(srcChar[i]) };
//					} else {
//						temp[i] = new String[] { "" };
//					}
//				}
//				String[] pingyinArray = Exchange(temp);
//				Set<String> pinyinSet = new HashSet<String>();
//				for (int i = 0; i < pingyinArray.length; i++) {
//					pinyinSet.add(pingyinArray[i]);
//				}
//				return pinyinSet;
//			}
//			Log.info("###############暂时注释");
//			return new HashSet<String>();
//		}
//
//
//
//	public static void main(String[] args) {
//
//		Set<String> str = getPinyin("万达11测试cc");
//		System.out.println(str);
//	}
//
//
//	/**
//	 * 递归
//	 *
//	 * @param strJaggedArray
//	 * @return
//	 */
//	public static String[] Exchange(String[][] strJaggedArray) {
//		String[][] temp = DoExchange(strJaggedArray);
//		return temp[0];
//	}
//
//	/**
//	 * 递归
//	 *
//	 * @param strJaggedArray
//	 * @return
//	 */
//	private static String[][] DoExchange(String[][] strJaggedArray) {
//		int len = strJaggedArray.length;
//		if (len >= 2) {
//			int len1 = strJaggedArray[0].length;
//			int len2 = strJaggedArray[1].length;
//			int newlen = len1 * len2;
//			String[] temp = new String[newlen];
//			int Index = 0;
//			for (int i = 0; i < len1; i++) {
//				for (int j = 0; j < len2; j++) {
//					temp[Index] = strJaggedArray[0][i] + strJaggedArray[1][j];
//					Index++;
//				}
//			}
//			String[][] newArray = new String[len - 1][];
//			for (int i = 2; i < len; i++) {
//				newArray[i - 1] = strJaggedArray[i];
//			}
//			newArray[0] = temp;
//			return DoExchange(newArray);
//		} else {
//			return strJaggedArray;
//		}
//	}
//
//
//}
