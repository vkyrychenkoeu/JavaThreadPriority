/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package priorytety;

import java.util.Random;
import java.util.Vector;
import java.util.concurrent.*;

	public class Priorytety extends Thread {

	
	private int odliczanie = 5;
    private volatile double d; //zmienna, która jest przechowywana w pamięci 
    //głównej komputera, nie w pamięci podręcznej procesora. Pozwala zapobiec
    //problemom ze spójnością danych
   
     private final int priorytet; //priorytet jest stałą, bo po stworzeniu wątku 
   
    // już się nie zmienia
    
    //Konstruktor
    
    public Priorytety(int priorytet){
        this.priorytet = priorytet;
    }
   
    
    //Standardowa metoda do wypisywania na ekranie łańcucha znakowego
    @Override
    public String toString(){
       return  Thread.currentThread() + ": " + odliczanie;
    	
    }
    
    public static void bubble_srt(int array[]) { 
        int n = array.length;
        int k;
        for (int m = n; m >= 0; m--) {
            for (int i = 0; i < n - 1; i++) {
                k = i + 1;
                if (array[i] > array[k]) {
                    swapNumbers(i, k, array);
                }
            }
           
        }
    }
    
    private static void swapNumbers(int i, int j, int[] array) {
    	  
        int temp;
        temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }
      
      public static void printNumbers(int[] input) {
          
        for (int i = 0; i < input.length; i++) {
        
            System.out.print(input[i] + " ");
        }
    
    }
    
    
    @Override
    public void run(){
    
        Thread.currentThread().setPriority(priorytet);
    	    	  
        while (true){
            //Długa i skomplikowana ooperacja matematyczna
            for (int i=1; i < 100000; i++){
                d += (Math.PI + Math.E)/(double)i;
                if (i % 1000 == 0)
                    Thread.yield();//Tu oddajemy kontrolę nad procesorem innym 
                //wątkom (bo to jest długa operacja i trzeba się co jakiś czas 
                //zatrzymywać)
            }
            System.out.println(this);
            if (--odliczanie == 0)
                return;
        }
    }
      
    

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args){
    	 
        Thread.currentThread().setPriority(MAX_PRIORITY);
        
          Vector<Integer> vector = new Vector<>();
          Random random = new Random();
           for (int i = 0; i < 10; i++) {
               vector.add(random.nextInt(100));
          
           }
                                                                                                                                                                                                                                                                                                                                                                                                                                
           int[] array = vector.stream().mapToInt(i->i).toArray();
         
          	
          	
          	
           Thread wazne = new Thread();
           Thread niewazne = new Thread();
       
              // Default 5
              System.out.println("Ważny wątek priority : " + wazne.MAX_PRIORITY); 
             
              // Default 5
              System.out.println("Nie ważny wątek priority : " + niewazne.MIN_PRIORITY); 
             
            
              
              
              new Thread(() -> {
                  
                  wazne.run();
                  wazne.setPriority(10);
                  bubble_srt(array);
                  
                  printNumbers(array);
                 
                  Thread.currentThread().getPriority();
                  System.out.println("Currently Executing Thread Priority: " + wazne.getPriority());
                 
                  
                }).start();
              
             
             
              new Thread(() -> {
                 
                  niewazne.run();
                  niewazne.setPriority(1);
                  bubble_srt(array);
                  
                  printNumbers(array);
                 
                  Thread.currentThread().getPriority();
              
                  System.out.println("Currently Executing Thread Priority: " + niewazne.getPriority() + "\n");
                  
                }).start();
              
           
        
    	ExecutorService exec = Executors.newCachedThreadPool();
        for (int i = MIN_PRIORITY; i <= MAX_PRIORITY; i++) {        	
        	
        Thread.currentThread().setPriority(i);
        exec.execute(new Priorytety(Thread.currentThread().getPriority()));
        
        }
        
        
        exec.shutdown();
        
      
        	
    }
}

		
/* 
Zadanie 1: zmodyfikować program tak, aby generował tyle wątków do wykonywania 
tego zadania obliczeniowego, ile jest priorytetów. Zaobserwować kolejność 
wykonania zadań.

Zadanie 2: wykorzystać priorytety do stworzenia dwoch klas wątków 
(ważniejszych i mniej waznych), które będą odpowiedzialne za sortowanie 
wdługiego wektora losowo wybranych liczb całkowitych (trzeba go sobie stworzyć).
Przetestować wplyw priorytetow na kolejność wykonania tego zadania dla 
poszczegolnych watkow.
*/