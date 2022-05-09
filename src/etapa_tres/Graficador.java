package etapa_tres;

import java.awt.*;
import java.util.*;

import javax.swing.*;
import javax.swing.JLabel;

import etapa_dos.Girasol;
import etapa_dos.Zombie;

public class Graficador {

	
	 JFrame frame = new JFrame("Image display");
//     JPanel panel = new JPanel(new GridLayout(5,9));
      
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
	   
	}

	public JPanel graficar() {
		 
        
//		   JFrame frame = new JFrame("Image display");
	        JPanel panel = new JPanel(new GridLayout(5,9));
//	        
	        ArrayList<JLabel> labels;
	        ArrayList<ImageIcon> imagen = new ArrayList<ImageIcon>();

	        ImageIcon grass1 = procesar(new ImageIcon("imagen\\pvz\\grass1.jpg"));
	        ImageIcon grass2 = procesar(new ImageIcon("imagen\\pvz\\grass2.jpg"));
	        ImageIcon grass3 = procesar(new ImageIcon("imagen\\pvz\\grass3.jpg"));
	        ImageIcon plant = procesar(new ImageIcon("imagen\\pvz\\plant.jpg"));
	        ImageIcon plantk = procesar(new ImageIcon("imagen\\pvz\\plantk.jpg"));
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
	        
	        return panel;
	}
	
	public JPanel graficar(String[][] mapa) {
		 
        
//		   JFrame frame = new JFrame("Image display");
	        JPanel panel = new JPanel(new GridLayout(5,9));
//	        
	        ArrayList<JLabel> labels;
	        ArrayList<ImageIcon> imagen = new ArrayList<ImageIcon>();

	        ImageIcon grass1 = procesar(new ImageIcon("imagen\\pvz\\grass1.jpg"));
	        ImageIcon grass2 = procesar(new ImageIcon("imagen\\pvz\\grass2.jpg"));
	        ImageIcon grass3 = procesar(new ImageIcon("imagen\\pvz\\grass3.jpg"));
	        ImageIcon plant = procesar(new ImageIcon("imagen\\pvz\\plant.jpg"));
	        ImageIcon plantk = procesar(new ImageIcon("imagen\\pvz\\plantk.jpg"));
	        ImageIcon sun0 = procesar(new ImageIcon("imagen\\pvz\\sun0.jpg"));
	        ImageIcon sun1 = procesar(new ImageIcon("imagen\\pvz\\sun1.jpg"));
	        ImageIcon sun2 = procesar(new ImageIcon("imagen\\pvz\\sun2.jpg"));
	        ImageIcon sun3 = procesar(new ImageIcon("imagen\\pvz\\sun3.jpg"));
	        ImageIcon sun4 = procesar(new ImageIcon("imagen\\pvz\\sun4.jpg"));
	        ImageIcon sun5 = procesar(new ImageIcon("imagen\\pvz\\sun5.jpg"));
	        ImageIcon sunx = procesar(new ImageIcon("imagen\\pvz\\sunx.jpg"));
	        ImageIcon zombie1 = procesar(new ImageIcon("imagen\\pvz\\zombie1.jpg"));
	        ImageIcon zombie2 = procesar(new ImageIcon("imagen\\pvz\\zombie2.jpg"));
	        ImageIcon zombie3 = procesar(new ImageIcon("imagen\\pvz\\zombie3.jpg"));
	        ImageIcon zombie4 = procesar(new ImageIcon("imagen\\pvz\\zombie4.jpg"));
	        ImageIcon zombie5 = procesar(new ImageIcon("imagen\\pvz\\zombie5.jpg"));
	        
	        for(int i=0; i<5; i++) {
				for(int j=0; j<9; j++) {
					JLabel labelAux = new JLabel(grass1);
					if(mapa[i][j].charAt(0) == 'Z') {
						int val = mapa[i][j].charAt(1)-'0';
						if(val==1) labelAux = new JLabel(zombie1);
						else if(val==2) labelAux = new JLabel(zombie2);
						else if(val==3) labelAux = new JLabel(zombie3);
						else if(val==4) labelAux = new JLabel(zombie4);
						else if(val==5) labelAux = new JLabel(zombie5);
					}
					else if(mapa[i][j].charAt(0) == 'G') {
						int val = 0;
						for(int k = 1; k<mapa[i][j].length(); k++) {
							val*=10;
							val+=(mapa[i][j].charAt(k)-'0');
						}
						// FALTA EL CERO
						if(val==0) labelAux = new JLabel(sun0);
						else if(val==1) labelAux = new JLabel(sun1);
						else if(val==2) labelAux = new JLabel(sun2);
						else if(val==3) labelAux = new JLabel(sun3);
						else if(val==4) labelAux = new JLabel(sun4);
						else if(val==5) labelAux = new JLabel(sun5);
						else if(val>=6) labelAux = new JLabel(sunx);
					}
					else if(mapa[i][j].charAt(0) == 'P') labelAux = new JLabel(plant);
					panel.add(labelAux);
				}
			}
//	        panel.add(lb1);
//	        panel.add(lb2);
//	        panel.add(lb3);
	        
	        return panel;
	}
	
}
