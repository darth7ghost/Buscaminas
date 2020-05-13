/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package todo;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.Timer;


public class Juego {
    
    //Variables
    JFrame ventana;
    JPanel panelPresentacion;
    JLabel fondoPresentacion;
    JTextField numMinas;
    JTextField numFC;
    JButton jugar;
    int filas;
    int columnas;
    int minas;
    
    
    //Juego
    JPanel panelJuego;
    JLabel fondoJuego;
    JLabel marcadoTiempo;
    JLabel marcadorBanderas;
    JLabel matriz[][];
    Timer tiempo;
    int mat[][];
    int auxmat[][];
    Random aleatorio;
    int min;
    int seg;
    int contBanderas;
    int contrestante;
    
     
    
    //Constructor
    public Juego(){
        
        //ventana
        ventana = new JFrame("Buscaminas");
        ventana.setSize(650,500);
        ventana.setLocationRelativeTo(null);
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ventana.setLayout(null);
        
        //Jpanel presentacion
        panelPresentacion = new JPanel();
        panelPresentacion.setSize(ventana.getWidth(),ventana.getHeight());
        panelPresentacion.setLocation(0,0);
        panelPresentacion.setLayout(null);
        panelPresentacion.setVisible(true);
        panelPresentacion.setBackground(Color.red);
        
        //imagen de fondo de la presentación
        fondoPresentacion = new JLabel();
        fondoPresentacion.setIcon(new ImageIcon("Imagenes/presentacion.png"));
        fondoPresentacion.setBounds(0, 0, panelPresentacion.getWidth(), panelPresentacion.getHeight());
        fondoPresentacion.setVisible(true);
        panelPresentacion.add(fondoPresentacion,0);
        
        
        //Caja numero de filas y columnas
        
        numFC = new JTextField("Ingrese el tamaño de la pista");
        numFC.setSize(200,30);
        numFC.setLocation(panelPresentacion.getWidth()-numFC.getWidth()-225,120);
        numFC.setVisible(true);
        panelPresentacion.add(numFC,0);
        
        
        //Caja numero de minas
        
        numMinas = new JTextField("Ingrese el número de minas");
        numMinas.setSize(200,30);
        numMinas.setLocation(panelPresentacion.getWidth()-numFC.getWidth()-225,170);
        numMinas.setVisible(true);
        panelPresentacion.add(numMinas,0);
        
        
        //Botón Jugar
        
        jugar = new JButton("¡VAMOS A JUGAR!");
        jugar.setSize(200,30);
        jugar.setLocation(panelPresentacion.getWidth()-numFC.getWidth()-225,220);
        jugar.setVisible(true);
        jugar.setBackground(Color.WHITE);
        panelPresentacion.add(jugar,0);
        
        
       
        
        
        //Panel juego
        
        panelJuego = new JPanel();
        panelJuego.setSize(ventana.getWidth(),ventana.getHeight());
        panelJuego.setLocation(0,0);
        panelJuego.setLayout(null);
        panelJuego.setVisible(true);
        //panelPresentacion.setBackground(Color.red);
        
        //imagen de fondo del juego
        fondoJuego = new JLabel();
        fondoJuego.setIcon(new ImageIcon("Imagenes/fondojuego.jpg"));
        fondoJuego.setBounds(0, 0, panelJuego.getWidth(), panelJuego.getHeight());
        fondoJuego.setVisible(true);
        panelJuego.add(fondoJuego,0);
        
        aleatorio = new Random();
               
        
        min = 0;
        seg = 0;
        marcadoTiempo = new JLabel("Tiempo: "+min+":"+seg);
        marcadoTiempo.setSize(80, 30);
        marcadoTiempo.setVisible(true);
        marcadoTiempo.setForeground(Color.WHITE);
        panelJuego.add(marcadoTiempo,0);
        
        tiempo= new Timer (1000, new ActionListener ()
        {
            public void actionPerformed(ActionEvent e)
            {
                
                seg++;
                if (seg == 60) {
                    seg = 0;
                    min++;
                }
                
                marcadoTiempo.setText("Tiempo: "+min+":"+seg);
                
            }});
        
        
            
        
        
        marcadorBanderas = new JLabel("Banderas: "+contBanderas);
        marcadorBanderas.setSize(80, 30);
        marcadorBanderas.setVisible(true);
        marcadorBanderas.setForeground(Color.WHITE);
        panelJuego.add(marcadorBanderas,0);
                
        
        
        //Evento del botón jugar
        
        jugar.addMouseListener(new MouseAdapter (){
            
            
            @Override
            public void mousePressed(MouseEvent e){
                
                //Inicio de la validación de los campos
                System.out.println("Presione jugar");
                filas = Integer.parseInt(numFC.getText());
                columnas = Integer.parseInt(numFC.getText());
                minas = Integer.parseInt(numMinas.getText());
               
                
                //cambiando páneles
                panelPresentacion.setVisible(false);
                panelJuego.setVisible(true);
                ventana.add(panelJuego);
                
                mat = new int[filas][columnas];
                auxmat = new int[filas][columnas];
                matriz = new JLabel[filas][columnas];
                 for (int i = 0; i < filas; i++){
                        for (int j = 0; j < columnas; j++){
                                matriz[i][j] = new JLabel();

                        }
                    }
                tiempo.start();
                contBanderas = minas;
                marcadorBanderas.setText("Banderas: "+contBanderas);
                
                
                //agregar matriz de imágenes
                inicializarMatriz();
                
                for (int i = 0; i < filas; i++){
                    for (int j = 0; j < columnas; j++){
                        matriz[i][j].addMouseListener(new MouseAdapter (){
            
                            //evento del mouse para cada imagen de la matriz
                            @Override
                            public void mousePressed(MouseEvent e){
                
                             
                               for (int k = 0; k < filas; k++){
                                   for (int l = 0; l < columnas; l++){
                                        if (e.getSource() == matriz[k][l]){
                                                    
                                                if(e.getButton()== MouseEvent.BUTTON1){
                                            
                                            
                                            System.out.println(k+" "+l);

                                                    //escoger un número del tablero
                                                    if(mat[k][l] != -2 && mat[k][l] != 0 && auxmat[k][l] != -3){
                                                        auxmat[k][l] = mat[k][l];
                                                        matriz[k][l].setIcon(new ImageIcon("imagenes/"+auxmat[k][l]+".png"));

                                                    }
                                                    //escoger una mina
                                                    if (mat[k][l] ==-2){


                                                        //Mostrando las minas
                                                        for(int m = 0; m < filas; m++){
                                                            for(int n = 0; n < columnas; n++){
                                                                if (mat[m][n] == -2){
                                                                    auxmat[m][n] = mat[m][n];
                                                                   matriz[m][n].setIcon(new ImageIcon("imagenes/"+auxmat[m][n]+".png"));


                                                                }
                                                            
                                                    }
                                                
                                                
                                                }
                                                //Partida perdida
                                                JOptionPane.showMessageDialog(ventana, "PERDISTE");
                                                
                                                //reiniciar luego de perder
                                            for(int m = 0; m < filas; m++)
                                                for (int n= 0; n < columnas; n++)
                                                    matriz[m][n].setVisible(false);
                                                        seg = 0;
                                                        min = 0;
                                                        contBanderas = minas;
                                                        numVecinos();
                                            ventana.setSize(650,500);
                                            panelPresentacion.setSize(ventana.getWidth (),ventana.getHeight());
                                            fondoPresentacion.setSize(ventana.getWidth (),ventana.getHeight());
                                            panelJuego.setVisible(false);
                                            panelPresentacion.setVisible(true);
                                            ventana.add(panelPresentacion);
                                            }
                                            
                                            //Se eligió un espacio en blanco
                                            if (mat[k][l] == 0 && auxmat[k][l] != -3){
                                                recursiva(k, l);
                                                numVecinos();
                                          
                                            
                                            }
                                            //Validar partida ganada
                                            contrestante = 0;
                                            for(int m = 0; m < filas; m++){
                                                for (int n= 0; n < columnas; n++){
                                                    if (auxmat[m][n] == -1 || auxmat[m][n] == -2)
                                                        contrestante++;
                                                }
                                            }
                                            if(contrestante == minas){
                                            JOptionPane.showMessageDialog(ventana, "¡HAS GANADO LA PARTIDA!");
                                            
                                            //Reiniciar el juego
                                            
                                            //reiniciarJuego();
                                            for(int m = 0; m < filas; m++)
                                                for (int n= 0; n < columnas; n++)
                                                    matriz[m][n].setVisible(false);
                                                        seg = 0;
                                                        min = 0;
                                                        contBanderas = minas;
                                            numVecinos();
                                            ventana.setSize(650,500);
                                            panelPresentacion.setSize(ventana.getWidth (),ventana.getHeight());
                                            fondoPresentacion.setSize(ventana.getWidth (),ventana.getHeight());
                                            panelJuego.setVisible(false);
                                            panelPresentacion.setVisible(true);
                                            ventana.add(panelPresentacion);
                                            }
                                            
                                            
                                                }else if(e.getButton()==MouseEvent.BUTTON3){
                                                    if(auxmat[k][l]==-1 && contBanderas > 0){
                                                        contBanderas--;
                                                        auxmat[k][l] = -3;
                                                        matriz[k][l].setIcon(new ImageIcon("imagenes/"+auxmat[k][l]+".png"));
                                                        marcadorBanderas.setText("Banderas :"+contBanderas);

                                                        
                                                    }
                                                    else if(auxmat[k][l]==-3){
                                                        auxmat[k][l] = -1;
                                                        contBanderas++;
                                                        matriz[k][l].setIcon(new ImageIcon("imagenes/"+auxmat[k][l]+".png"));
                                                        marcadorBanderas.setText("Banderas :"+contBanderas);
                                                        
                                                    
                                                    }
                                                
                                                }
                                            
                                            
                                        }
                               }
                               } 
                                
                                
                                
            }});                    
                                
                    }
                }
                
                
                
                
                        
            
                    
                 //final de validación de campos   
                
                
                }
            
        } );
        
        ventana.add(panelPresentacion);
        ventana.setVisible(true);
        
        
    }
           
    public void inicializarMatriz(){
    
        int f;
        int c;
        for (int i = 0; i <filas; i++){
            for (int j = 0; j < columnas; j++){
                mat[i][j]=0;
                auxmat[i][j]=-1;
            }
        }
        for (int i = 0; i < minas; i++){
            
            do{
                
            f = aleatorio.nextInt(filas);
            c = aleatorio.nextInt(columnas);
            
            //-2 es el nombre del archivo de imagen de minas
            }while(mat[f][c]==-2);
                mat[f][c]=-2;
         }

            
        for (int i = 0; i <filas; i++){
            for (int j = 0; j < columnas; j++){
                if(mat[i][j] == -2){

                    //Hacia arriba  
                    if(i>0 && mat[i-1][j] != -2){
                        mat[i-1][j]++;
                    }    
                    //Hacia abajo    
                    if(i<filas-1 && mat[i+1][j] != -2){
                        mat[i+1][j]++;
                    }    
                    //Hacia la izquierda   
                    if(j>0 && mat[i][j-1] != -2){
                        mat[i][j-1]++;    
                    }
                    //Hacia la derecha  
                    if(j<columnas-1 && mat[i][j+1] != -2){
                        mat[i][j+1]++;
                    }
                    
                    //esquina superior izquierda
                    
                    if(i>0 && j>0 && mat[i-1][j-1] != -2){
                        mat[i-1][j-1]++;
                    }
                    
                    //esquina inferior izquierda
                    
                    if(i<filas-1 && j>0 && mat[i+1][j-1] != -2){
                        mat[i+1][j-1]++;
                    }
                    
                    //esquina superior derecha
                    
                    if(i>0 && j<columnas-1 && mat[i-1][j+1] != -2){
                        mat[i-1][j+1]++;
                    }
                    
                    //esquina inferior derecha
                    
                    if(i<filas-1 && j<columnas-1 && mat[i+1][j+1]!=-2){
                        mat[i+1][j+1]++;
                    }
                    
                    
                    
                    
                    
                }
            }
        }
        
        
        ventana.setSize(100+(columnas*25), 150+(filas*25));
        panelJuego.setSize(100+(columnas*25), 150+(filas*25));
        marcadoTiempo.setLocation(ventana.getWidth()-marcadoTiempo.getWidth()-80, 30);
        marcadorBanderas.setLocation(10, 30);
        
        
        
        for (int i = 0; i <filas; i++){
        for (int j = 0; j < columnas; j++){
            
            
            
            matriz[i][j].setSize(25, 25);
            matriz[i][j].setLocation(j, j);
            matriz[i][j].setLocation(50+(j*25), 75+(i*25));
            matriz[i][j].setIcon(new ImageIcon("imagenes/"+auxmat[i][j]+".png"));
            matriz[i][j].setVisible(true);
            panelJuego.add(matriz[i][j],0);
            
        }
        System.out.println("");
        }
        
    }
    
    //fin inicializar matriz
    public void recursiva(int i, int j){
        auxmat[i][j] = mat[i][j];
        mat[i][j] = 9;
        
        
        //derecha
        
        
        if(j < columnas - 1 && mat[i][j+1] == 0 && auxmat[i][j+1] != -3){
            recursiva(i, j+1);
            
            }
        
        else if(j < columnas-1 && mat[i][j+1] != 0 && mat[i][j+1] !=-2 && mat[i][j+1] != 9 && auxmat[i][j+1] != -3 ) {
                auxmat[i][j+1] = mat[i][j+1];    
        
        }
        
        //Izquierda
        if(j > 0 && mat[i][j-1] == 0 && auxmat[i][j-1] != -3){
            recursiva(i, j-1);
            
            }
        
        else if(j > 0  && mat[i][j-1] != 0 && mat[i][j-1] !=-2  && mat[i][j-1] != 9 && auxmat[i][j-1] != -3 ) {
                auxmat[i][j-1] = mat[i][j-1];    
        
        }
        
        
        //Arriba
        if(i > 0 && mat[i-1][j] == 0 && auxmat[i-1][j] != -3){
            recursiva(i-1,j);
            
            }
        
        else if(i > 0  && mat[i-1][j] != 0 && mat[i-1][j] !=-2  && mat[i-1][j] != 9 && auxmat[i-1][j] != -3 ) {
                auxmat[i-1][j] = mat[i-1][j];    
        
        }
        
        
        
        //Abajo
        if(i < filas-1 && mat[i+1][j] == 0  && auxmat[i+1][j] != -3){
            recursiva(i+1,j);
            
            }
        
        else if(i < filas-1  && mat[i+1][j] != 0 && mat[i+1][j] !=-2 && mat[i+1][j] != 9 && auxmat[i+1][j] != -3 ){
                auxmat[i+1][j] = mat[i+1][j];    
        
        }
        
            matriz[i][j].setIcon(new ImageIcon("imagenes/"+auxmat[i][j]+".png"));
        
    }
    
    public void numVecinos(){
        for(int i = 0; i < filas; i++){
            for(int j = 0; j < columnas; j++){
                System.out.print(auxmat[i][j]+" ");
                matriz[i][j].setIcon(new ImageIcon("imagenes/"+auxmat[i][j]+".png"));
                
            }
                System.out.println("");
        
        
        }
        
        
        
    
    }
    
        
    
    
    }

    

