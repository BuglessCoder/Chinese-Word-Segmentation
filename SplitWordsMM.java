package application;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class SplitWordsMM extends Application {
	//DICT为词典，MAX_LENGTH为词典中的最大词长
		private static final HashSet<String> DICT = new HashSet<>();  
		//private static final List<String> DIC = new ArrayList<>(); HashSet的contains方法查找速度比ArrayList更快
		private static int MAX_LENGTH; 
		
		private TextField t1 = new TextField();
		private TextField t2 = new TextField();
		
		@Override
		public void start(Stage primaryStage) throws Exception {
			// TODO 自动生成的方法存根
			GridPane g = new GridPane();
			g.add(new Label("Please input one sentence:"),0,0);
			g.add(t1,1,0);
		    g.add(new Label("The result after spliting words:"),0,1);
		    g.add(t2,1,1);
		    
		    Button b1 = new Button("Spilt Words");
		    b1.setOnAction(e -> {
				 String word = t1.getText();			 	 
				 List<String> list = maximumMatching(word);
				 String string = "";
				 for(String s : list){
					 string += s + " ";
				 }
				 t2.setText(string); 	                               
			 });
			 g.add(b1,1,2);	
			 
			 Scene scene = new Scene(g,400,80);
		     primaryStage.setScene(scene);
		     primaryStage.setTitle("Chinese Split Words");
		     primaryStage.show();		
		} 
		
		static{  
			String line = null;
	        try {  
	        	int max = 1;
	        	BufferedReader reader = new BufferedReader(new FileReader("/Users/lidawei/Downloads/dic.txt"));
	        	while((line = reader.readLine()) != null){
		        	DICT.add(line);  	     	      
		            if(line.length()>max){  
		                max=line.length();  
		            }  
	        	}	             
	            MAX_LENGTH = max;   
	        } catch (IOException ex) {  
	            System.err.println("词典装载失败:"+ex.getMessage());  
	        }  
	          
	    }
		public static void main(String[] args) {
			 Application.launch(args);
		}
		
		//正向最大匹配算法（MM算法），最长词优先匹配
		public static List<String> maximumMatching(String text){		
			//result存储分词后的结果
			List<String> result = new ArrayList<>();
			while(text.length() > 0){		
		        int len = MAX_LENGTH; 	        
		        //如果待切分字符串的长度小于词典中的最大词长，则令最大词长等于待切分字符串的长度
		        if(text.length() < len){
		            len = text.length();  
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
