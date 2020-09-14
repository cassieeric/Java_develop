import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class StoryBook {
	JFrame jf = new JFrame();
    JPanel jp = new JPanel();
    JPanel jp1 = new JPanel();
    JMenuBar mainMenu = new JMenuBar();
    JTextArea jt = new JTextArea(6,40);
    JLabel jl = new JLabel();
    JButton jb = new JButton("上一页");
    JButton jb1 = new JButton("下一页");
    JMenu fontsize = new JMenu("字号");
    JMenuItem twelf = new JMenuItem("12");
    JMenuItem fiveteen = new JMenuItem("15");
    JMenuItem eighteen = new JMenuItem("18");
    JMenuItem twenty = new JMenuItem("20");
    JMenu font = new JMenu("字体");
    JMenuItem song = new JMenuItem("宋体");
    JMenuItem hei = new JMenuItem("黑体");
    JMenuItem kai = new JMenuItem("楷体");
    JMenu fontstyle = new JMenu("字形");
    JMenuItem chang = new JMenuItem("常规");
    JMenuItem jia = new JMenuItem("加粗");
    JMenuItem qing = new JMenuItem("倾斜");
    JMenu color = new JMenu("颜色");
    JMenuItem red = new JMenuItem("红色");
    JMenuItem green = new JMenuItem("绿色");
    JMenuItem blue = new JMenuItem("蓝色");
    JMenu intall = new JMenu("设置");
    JMenuItem swap = new JMenuItem("焕壁纸");
    Box horizontal = Box.createHorizontalBox();
    public void init() {
    	mainMenu.add(fontsize);
    	mainMenu.add(font);
    	mainMenu.add(fontstyle);
    	mainMenu.add(color);
    	mainMenu.add(intall);
    	
    	fontsize.add(twelf);
    	fontsize.add(fiveteen);
    	fontsize.add(eighteen);
    	fontsize.add(twenty);
    	
    	font.add(song);
    	font.add(hei);
    	font.add(kai);
    	
    	fontstyle.add(chang);
    	fontstyle.add(jia);
    	fontstyle.add(qing);
    	
    	color.add(red);
    	color.add(green);
    	color.add(blue);
    	
    	
    	intall.add(swap);
    	
    	jf.setLayout(new FlowLayout());
    	jp.add(jb);
    	jp.add(jb1);
    	
    	jf.add(jp);
    	jf.add(jp1);
    	jf.setJMenuBar(mainMenu);
    	jf.add(jt);
    	jf.pack();
    	jf.setVisible(true);
    }
    public static void main(String[] args) {
             new StoryBook().init();
    }
}
