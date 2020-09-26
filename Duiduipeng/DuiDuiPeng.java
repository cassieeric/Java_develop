import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JTextField;
import javax.swing.Timer;


public class DuiDuiPeng extends JFrame {

	private JPanel panel01 = new JPanel();
	private JButton btn_start = new JButton("开始游戏");
	private JLabel label01 = new JLabel("分数");
	private JTextField text_score = new JTextField(10); 
	private JLabel label02 = new JLabel("时间");
	private JProgressBar progress = new JProgressBar();
	private JButton btn_exit = new JButton("退出");
	
	private JPanel panel02 = new JPanel();
	private JButton button[][] = new JButton[8][8];
	private int animal[][] = new int[8][8];//0猫 1牛 2鸡 3狐狸 4青蛙 5猴子 6熊猫
    private ImageIcon icon[] = new ImageIcon[]{
    	new ImageIcon("image//cat.png"),
    	new ImageIcon("image//cattle.png"),
    	new ImageIcon("image//chicken.png"),
    	new ImageIcon("image//fox.png"),
    	new ImageIcon("image//frog.png"),
    	new ImageIcon("image//monkey.png"),
    	new ImageIcon("image//panda.png"),
    	
    };//7种动物的图片
    
    private MyListener my = new MyListener();
    private Timer timer;
    private int score=0;//总分
    private int jindu=0;
    
    private int x1,y1;//第一次点击按钮的坐标位置
    private int x2,y2;//第二次点击按钮的坐标位置
    private final int EMPTY=-1;//为空的标记
    private boolean isDoubleClicked=false;//是否点击两次
    
	/**
	 * @param args
	 */
	DuiDuiPeng(){
		text_score.setText("0");
		text_score.setEditable(false);
		progress.setMinimum(0);
		progress.setMaximum(100);
		progress.setStringPainted(true);
		
		panel01.add(btn_start);
		panel01.add(label01);
		panel01.add(text_score);
		panel01.add(label02);
		panel01.add(progress);
		panel01.add(btn_exit);
		this.setLayout(new BorderLayout());
		this.add(panel01,BorderLayout.NORTH);
		
		panel02.setLayout(new GridLayout(8,8,2,2));
		for(int i=0;i<8;i++){//行
			for(int j=0;j<8;j++){//列
				int temp = (int)(Math.random()*7);//0-6
				button[i][j] = new JButton(icon[temp]);
				if((i+j)%2==0){
					button[i][j].setBackground(new Color(255,222,173));
				}else{
					button[i][j].setBackground(new Color(255,246,143));
				}
			    animal[i][j]=temp;
				panel02.add(button[i][j]);
				button[i][j].setEnabled(false);
				
				button[i][j].addActionListener(my);
			}
		}
		this.add(panel02,BorderLayout.CENTER);
		
		panel01.setOpaque(false);
		panel02.setOpaque(false);
		this.getContentPane().setBackground(Color.orange);
		
		btn_start.addActionListener(my);
		btn_exit.addActionListener(my);
		
		timer=new Timer(1000,new TimerListener());
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
        DuiDuiPeng  d = new DuiDuiPeng();
        d.setTitle("对对碰游戏");
        d.setSize(700, 550);
        d.setVisible(true);
        d.setResizable(false);
		d.setLocationRelativeTo(null);
	}
    private  class MyListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			if(e.getSource()==btn_start){//开始游戏
				btn_start.setEnabled(false);
				timer.start();
				jindu=0;
				progress.setValue(jindu);
				score=0;
				text_score.setText(""+score);
				for(int i=0;i<8;i++){
					for(int j=0;j<8;j++){
						button[i][j].setEnabled(true);
					}
				}
				do{initAllAnimals();
				}while(searchAllAnimals(1));
		    }
			if(e.getSource()==btn_exit){//退出按钮
				timer.stop();
				for(int i=0;i<8;i++){
					for(int j=0;j<8;j++){
						button[i][j].setEnabled(false);
					}
				}
				dispose();
			}
			for(int i=0;i<8;i++){
				for(int j=0;j<8;j++){
					if(e.getSource()==button[i][j]){//动物按钮
						System.out.println((i+1)+"行"+(j+1)+"列");
						swapAnimal(i,j);//交换图片
						
					}
				}
			}
		}	
    }
    private class TimerListener implements ActionListener{
    	@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			jindu++;
			progress.setValue(jindu);
			if(jindu==100){
				timer.stop();
				for(int i=0;i<8;i++){
					for(int j=0;j<8;j++){
						button[i][j].setEnabled(false);
					}
				}
				btn_start.setEnabled(true);
			}
		}
    }
    
    public void initAllAnimals(){//初始化动物矩阵
    	for(int i=0;i<8;i++){
    		for(int j=0;j<8;j++){
    			int temp=(int)(Math.random()*7);//0-6
    			button[i][j].setIcon(icon[temp]);
    			animal[i][j]=temp;
    		}
    	}
    }
    
    public boolean isThreeLinked(int y,int x){//y行 x列
    	//查询是否有三个或以上相同连接的图形，是返回true,不是返回false
    	int temp;
    	//判断水平方向上是否有三个或以上相同连接的图形
    	int linked=1;
    	if(x+1<8){
    		temp=x+1;
    		while(temp<8 && animal[y][x]==animal[y][temp]){
    			linked++;
    			temp++;
    		}
    	}
   	   if(x-1>=0){
   		   temp=x-1;
   		   while(temp>=0 && animal[y][x]==animal[y][temp]){
   			   linked++;
   			   temp--;
   		   }
   	   }
   	   if(linked>=3){
   		   return true;
   	   }
      
   	   //判断垂直方向上是否有三个或以上相同连接的图形
    	
   	   linked=1;
   	   if(y+1<8){
   		   temp=y+1;
   		   while(temp<8 && animal[y][x]==animal[temp][x]){
   			   linked++;
   			   temp++;
   		   }
   	   }
   	   if(y-1>=0){
   		   temp=y-1;
   		   while(temp>=0 && animal[y][x]==animal[temp][x]){
   			   linked++;
   			   temp--;
   		   }
   	   }
   	   if(linked>=3){
   		   return true;
   	   }
   	   return false;
    }
    
    public void removeThreeLined(int y,int x){//把三个或以上相同连接图形设为EMPTY
    	if(animal[y][x]==EMPTY){
    		return;
    	}
    	int num=0;
    	//判断水平方向上是否有三个或以上相同连接的图形
    	int temp=0;
    	int linked=1;
    	if(x+1<8){
    		temp=x+1;
    		while(temp<8 && animal[y][x]==animal[y][temp]){
    			linked++;
    			temp++;
    		}
    	}
   	   if(x-1>=0){
   		   temp=x-1;
   		   while(temp>=0 && animal[y][x]==animal[y][temp]){
   			   linked++;
   			   temp--;
   		   }
   	   }
   	//把水平方向上有三个或以上相同连接的图形设为EMPTY
   	   if(linked>=3){
   		   num=num+linked;
   		   temp=x+1;
   		   while(temp<8 &&animal[y][x]==animal[y][temp]){
   			   animal[y][temp]=EMPTY;
   			   temp++;
   		   }
   		   temp=x-1;
   		   while(temp>=0 && animal[y][x]==animal[y][temp]){
   			   animal[y][temp]=EMPTY;
   			   temp--;
   		   }
   		   animal[y][x]=EMPTY;
   	   }
   	 //判断垂直方向上是否有三个或以上相同连接的图形
   	   temp=0;
   	   linked=1;
   	   if(y+1<8){
		   temp=y+1;
		   while(temp<8 && animal[y][x]==animal[temp][x]){
			   linked++;
			   temp++;
		   }
	   }
	   if(y-1>=0){
		   temp=y-1;
		   while(temp>=0 && animal[y][x]==animal[temp][x]){
			   linked++;
			   temp--;
		   }
	   }
	 //把垂直方向上有三个或以上相同连接的图形设为EMPTY
	   if(linked>=3){
		   num=num+linked;
		   temp=y+1;
		   while(temp<8 &&animal[y][x]==animal[temp][x]){
			   animal[temp][x]=EMPTY;
			   temp++;
		   }
		   temp=y-1;
		   while(temp>=0 && animal[y][x]==animal[temp][x]){
			   animal[temp][x]=EMPTY;
			   temp--;
		   }
		   animal[y][x]=EMPTY;
	   }
	   score=score+num*10;
	   text_score.setText(""+score);
    }
    
    public boolean searchAllAnimals(int flag){
    	//1为查询连接  2为去除连接
    	for(int i=0;i<8;i++){
    		for(int j=0;j<8;j++){
    			if(flag==1){
    				if(isThreeLinked(i,j)){//查询是否有三个或以上连接
    					return true;
    				}
    			}else if(flag==2){
    				removeThreeLined(i,j);
    			}
    		}
    	}
    	return false;
    }
    
    public void downAnimal(){//动物往下移动
    	int temp;
    	for(int y=7;y>=0;y--){
    		for(int x=0;x<8;x++){
    			if(animal[y][x]==EMPTY){
    				//找到一个空的位置
    				for(int k=y-1;k>=0;k--){
    					if(animal[k][x]!=EMPTY){//找到上面一个非空的
    						temp=animal[k][x];
    						animal[k][x]=animal[y][x];
    						animal[y][x]=temp;
    						break;
    					}
    				}
    				
    			}
    		}
    	}
    }
    public void showAllAnimals(){//重新显示所有图形
    	for(int i=0;i<8;i++){
    		for(int j=0;j<8;j++){
    			button[i][j].setIcon(icon[animal[i][j]]);
    		}
    	}
    }
    public void updateAnimal(){//为空的重新生成随机图形
    	for(int i=0;i<8;i++){
    		for(int j=0;j<8;j++){
    			if(animal[i][j]==EMPTY){
    				animal[i][j]=(int)(Math.random()*7);
    			}
    		}
    	}
    	
    }
    public void swapAnimal(int y,int x){//y为行,x为列
    	if((x>=0 && x<8)&&(y>=0 && y<8)){
    		if(!isDoubleClicked){
    			//第一次单击
    			x1=x;
    			y1=y;
    			isDoubleClicked=true;
    			System.out.println("第一次单击的坐标=("+(y1+1)+","+(x1+1)+")");
    		}else{
    			x2=x;
    			y2=y;
    			isDoubleClicked=false;
    			System.out.println("第一次单击的坐标=("+(y2+1)+","+(x2+1)+")");
    			
    			if((Math.abs(x2-x1)==1 &&(y1==y2))
    					||(x1==x2)&&(Math.abs(y2-y1)==1)){//相邻两个按钮
    				int temp;
    				temp=animal[y2][x2];//交换
    				animal[y2][x2]=animal[y1][x1];
    				animal[y1][x1]=temp;
    				
    				if(isThreeLinked(y1,x1)|| isThreeLinked(y2,x2)){//有三个或以上连接
    					if(isThreeLinked(y1,x1)){
    						removeThreeLined(y1,x1);
    					}
    					if(isThreeLinked(y2,x2)){
    						removeThreeLined(y2,x2);
    					}
    					downAnimal();//动物往下移动
    					updateAnimal();//为空的重新生成随机图形
    					showAllAnimals();//重新显示所有图形
    					while(searchAllAnimals(1)){
    						searchAllAnimals(2);//去除连接
    						downAnimal();
    						updateAnimal();
    						showAllAnimals();
    					}
    				}else{//没有三个相邻的
    					temp=animal[y2][x2];//交换
        				animal[y2][x2]=animal[y1][x1];
        				animal[y1][x1]=temp;
    				}
    			}
    		}
    	}
    }
}
