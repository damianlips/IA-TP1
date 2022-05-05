package etapa_tres;

import java.awt.*;
import java.util.*;

import javax.swing.*;
import javax.swing.JLabel;

public class graficador {

	public static ImageIcon procesar(ImageIcon ii) {
		return new ImageIcon(ii.getImage().getScaledInstance(100,100, Image.SCALE_DEFAULT));
	}
	public static void main(String[] args) {
		
//	        JFrame frame = new JFrame("Image display");
//	        JPanel panel = new JPanel(new GridLayout(5,9));
//	        
//	        //C:\Users\Fede\Desktop
//	       // imagen = new ImageIcon("C:/oso.jpg");
//	        ArrayList<JLabel> labels;
//	        ArrayList<ImageIcon> imagen = new ArrayList<ImageIcon>();
//	      //  ImageIcon imagen1 = new ImageIcon("C:\\Users\\Fede\\Desktop\\imagen\\raccon.jpg"); // cargar la imagen
//	     //   JLabel lb1 = new JLabel( imagen1 ); // imagen dentro de un Label
//	        ImageIcon imagen1 = new ImageIcon(new ImageIcon("C:\\Users\\Fede\\Desktop\\imagen\\raccon.jpg").getImage().getScaledInstance(110, 130, Image.SCALE_DEFAULT));
//	        ImageIcon imagen2 = new ImageIcon(new ImageIcon("C:\\Users\\Fede\\Desktop\\imagen\\oso.jpg").getImage().getScaledInstance(110, 130, Image.SCALE_DEFAULT));
//	        ImageIcon imagen3 = new ImageIcon(new ImageIcon("C:\\Users\\Fede\\Desktop\\imagen\\owl.jpg").getImage().getScaledInstance(110, 130, Image.SCALE_DEFAULT));
//	       
//	        imagen.add(imagen1); imagen.add(imagen2); imagen.add(imagen3);
//	        for(int i = 0; i<45; i++){
//	        	JLabel labelaux = new JLabel(imagen.get((int)(Math.random()*3)%3));
////	        	labels.add(labelaux);
//	        	panel.add(labelaux);
//	        }
////	        panel.add(lb1);
////	        panel.add(lb2);
////	        panel.add(lb3);
//
//	        frame.add(panel);
//	        frame.setSize(1100,700);
//	        frame.setVisible(true);
	        
	        
	        
		   JFrame frame = new JFrame("Image display");
	        JPanel panel = new JPanel(new GridLayout(5,9));
	        
	        ArrayList<JLabel> labels;
	        ArrayList<ImageIcon> imagen = new ArrayList<ImageIcon>();
	        
//	        ImageIcon grass1 = new ImageIcon(new ImageIcon("C:\\Users\\Fede\\Desktop\\imagen\\pvz\\grass1.jpg").getImage().getScaledInstance(110, 130, Image.SCALE_DEFAULT));
//	        ImageIcon grass2 = new ImageIcon(new ImageIcon("C:\\Users\\Fede\\Desktop\\imagen\\pvz\\grass2.jpg").getImage().getScaledInstance(110, 130, Image.SCALE_DEFAULT));
//	        ImageIcon grass3 = new ImageIcon(new ImageIcon("C:\\Users\\Fede\\Desktop\\imagen\\pvz\\grass3.jpg").getImage().getScaledInstance(110, 130, Image.SCALE_DEFAULT));
//	        ImageIcon plant = new ImageIcon(new ImageIcon("C:\\Users\\Fede\\Desktop\\imagen\\pvz\\plant.jpg").getImage().getScaledInstance(110, 130, Image.SCALE_DEFAULT));
//	        ImageIcon plantk = new ImageIcon(new ImageIcon("C:\\Users\\Fede\\Desktop\\imagen\\pvz\\plantk.jpg").getImage().getScaledInstance(110, 100, Image.SCALE_DEFAULT));
//	        ImageIcon sun = new ImageIcon(new ImageIcon("C:\\Users\\Fede\\Desktop\\imagen\\pvz\\sun.jpg").getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT));
//	        ImageIcon sunx = new ImageIcon(new ImageIcon("C:\\Users\\Fede\\Desktop\\imagen\\pvz\\sunx.jpg").getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT));
//	        ImageIcon sun2 = new ImageIcon(new ImageIcon("C:\\Users\\Fede\\Desktop\\imagen\\pvz\\sun2.jpg").getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT));
//	        ImageIcon sun2x = new ImageIcon(new ImageIcon("C:\\Users\\Fede\\Desktop\\imagen\\pvz\\sun2x.jpg").getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT));
//	    
	        ImageIcon grass1 = procesar(new ImageIcon("imagen\\pvz\\grass1.jpg"));
	        ImageIcon grass2 = procesar(new ImageIcon("imagen\\pvz\\grass2.jpg"));
	        ImageIcon grass3 = procesar(new ImageIcon("imagen\\pvz\\grass3.jpg"));
	        ImageIcon plant = procesar(new ImageIcon("imagen\\pvz\\plant.jpg"));
	        ImageIcon plantk = procesar(new ImageIcon("imagen\\pvz\\plantk.jpg"));
//	        ImageIcon sun = procesar(new ImageIcon("C:\\Users\\Fede\\Desktop\\imagen\\pvz\\sun.jpg"));
//	        ImageIcon sunx = procesar(new ImageIcon("C:\\Users\\Fede\\Desktop\\imagen\\pvz\\sunx.jpg"));
//	        ImageIcon sun2 = procesar(new ImageIcon("C:\\Users\\Fede\\Desktop\\imagen\\pvz\\sun2.jpg"));
//	        ImageIcon sun2x = procesar(new ImageIcon("C:\\Users\\Fede\\Desktop\\imagen\\pvz\\sun2x.jpg"));
	        ImageIcon sun1 = procesar(new ImageIcon("imagen\\pvz\\sun1.jpg"));
	        ImageIcon sun2 = procesar(new ImageIcon("imagen\\pvz\\sun2.jpg"));
	        ImageIcon sun3 = procesar(new ImageIcon("imagen\\pvz\\sun3.jpg"));
	        ImageIcon sun4 = procesar(new ImageIcon("imagen\\pvz\\sun4.jpg"));
	        ImageIcon sun5 = procesar(new ImageIcon("imagen\\pvz\\sun5.jpg"));
	        ImageIcon sunx = procesar(new ImageIcon("imagen\\pvz\\sunx.jpg"));
	        imagen.add(grass1); imagen.add(grass2); imagen.add(grass3);
	        imagen.add(plant); imagen.add(plantk);
//	        imagen.add(sun); imagen.add(sunx);
//	        imagen.add(sun2); imagen.add(sun2x);
	        imagen.add(sun1); imagen.add(sun2); imagen.add(sun3);
	        imagen.add(sun4); imagen.add(sun5); imagen.add(sunx);
	        Boolean used = false;
	        int row = 0, crow=0;
	        for(int i = 0; i<45; i++){
	        	row%=9;
	        	if(row==0) crow = 0;
	        	row++;
	        	int val;
	        	while(true){
	        		val = (int)(Math.random()*11)%11;
	        		if(val > 4) {
	        			if(row > 5) continue;
//	        			else crow++;
	        		}
	        		if(used) {
	        			if(val == 3 || val == 4) continue;
	        			else break;
	        		}
	        		else {
	        			if(val==3 || val == 4) used=true;
	        			break;
	        		}
	        	}
	        	JLabel labelaux = new JLabel(imagen.get(val));
	        	panel.add(labelaux);
	        }
//	        panel.add(lb1);
//	        panel.add(lb2);
//	        panel.add(lb3);

	        frame.add(panel);
	        frame.setSize(900,550);
	        frame.setVisible(true);
	        
	        
	        
	        

	}

}
