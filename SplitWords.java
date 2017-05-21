package advancedJava;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class SplitWords {
	
	//DICT为词典，MAX_LENGTH为词典中的最大词长
	private static final HashSet<String> DICT = new HashSet<>();  
	//private static final List<String> DIC = new ArrayList<>(); HashSet的contains方法查找速度比ArrayList更快
    private static final int MAX_LENGTH; 
    
    //加载词典
    static{
    	int max = 1;
    	DICT.add("汉语");
    	DICT.add("世界");
    	DICT.add("语言");
    	DICT.add("缺少");
    	DICT.add("部分");
    	DICT.add("中华");
    	DICT.add("中华民族");
    	DICT.add("文化");
    	DICT.add("瑰宝");
    	DICT.add("文化瑰宝");
    	for(String word : DICT){
    		if(word.length()>max){
    			max = word.length();
    		}
    	}
    	MAX_LENGTH = max;
    }
    
	public static void main(String[] args) {
		System.out.println(maximumMatching("汉语是世界语言库中不可缺少的一部分也是中华民族的文化瑰宝"));
	}
	
	//正向最大匹配算法（MM算法），最长词优先匹配
	public static List<String> maximumMatching(String text){		
		//result存储分词后的结果
		List<String> result = new ArrayList<>();
		while(text.length() > 0){		
	        int len = MAX_LENGTH; 
	        
	        //如果待切分字符串的长度小于词典中的最大词长，则令最大词长等于待切分字符串的长度
	        if(text.length() < len){
	            len=text.length();  
	        } 
	        
	        //取指定的最大长度的文本去词典里面匹配  
	        String tryWord = text.substring(0, 0+len); 
	        while(!DICT.contains(tryWord)){  	        	
	        	//如果长度已经减为一且在词典中仍未找到匹配，则按长度为一切分  
	            if(tryWord.length() == 1){  
	                break;  
	            }         	
	        	//如果匹配不到，则长度减一继续匹配  
	            tryWord = tryWord.substring(0, tryWord.length()-1);               	            
	        }
	        result.add(tryWord);	        
	        //从待切分字符串中去除已经分词完的部分
	        text = text.substring(tryWord.length());  
	    }  
	    return result;  	      
	} 
}

