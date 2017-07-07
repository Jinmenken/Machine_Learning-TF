/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package KNN;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import static java.lang.Math.pow;
import java.util.*;

/**
 *
 * @author Valky
 */
public class KNN {

   ArrayList<String> attributes;
   ArrayList<ArrayList<String>> attValues;
   ArrayList<ArrayList<String>> trainData;
   int k;
   int percent;
   String distancia;

   public KNN(ArrayList<String> atributos, ArrayList<ArrayList<String>> valores, ArrayList<ArrayList<String>> data, int k, int percent, String distancia){
       this.attributes = atributos;
       this.attValues = valores;
       this.trainData = data;
       this.k = k;
       this.percent = percent;
       this.distancia = distancia;
   }

   public ArrayList<Double> calcDistancias(ArrayList<String> consulta){
       ArrayList<Double> result = new ArrayList<Double>();
       
       int tot_train = trainData.size()*percent/100;
       for(int i=0; i<tot_train; i++){
           double dist = 0;
           for(int j=0; j<trainData.get(i).size()-1; j++){
               String attValTrainData = trainData.get(i).get(j);
               int indexAVTD = attValues.get(j).indexOf(attValTrainData);
               String attValConsult = consulta.get(j);
               int indexAVC = attValues.get(j).indexOf(attValConsult);
               dist += pow(indexAVTD-indexAVC,2);
           }
           result.add(dist);
       }

       return result;
   }

   public int[] nearestNeighbors(ArrayList<Double> distancias){
       int[] nn = new int[k];
       double[] lowestValues = new double[k];

       Arrays.fill(lowestValues, Double.MAX_VALUE);

       for(int i=0;i<distancias.size();i++){
           if(distancias.get(i) < lowestValues[k-1]){
               lowestValues[k-1] = distancias.get(i);
               Arrays.sort(lowestValues);
           }
       }

       for(int i=0;i<lowestValues.length;i++){
           for(int j=0;j<distancias.size();j++){
               if(lowestValues[i] == distancias.get(j)){
                   nn[i] = j;
               }
           }
       }

       return nn;
   }

   public String clasificar(int[] nn){
       int cantClase[] = new int[2];

       Arrays.fill(cantClase, 0);

       for(int i=0;i<nn.length;i++){
           String clase = trainData.get(nn[i]).get(trainData.get(nn[i]).size()-1);
           
           for(int j=0; j<2; j++){
               if(attValues.get(attributes.size()-1).get(j).equals(clase))
                   cantClase[j]++;
           }
       }

       if(cantClase[0] < cantClase[1])
           return attValues.get(attributes.size()-1).get(1);
       else
           return attValues.get(attributes.size()-1).get(0);
   }

   public String clasificador(ArrayList<String> consulta){
       
       String clase = "";
       
       if(distancia.equals("Hamming")){
           ArrayList<Double> dist = calcDistanciasHamming(consulta);
           int[] nn = nearestNeighbors(dist);
           clase = clasificar(nn);
       }
       if(distancia.equals("Euclidiana")){
           ArrayList<Double> dist = calcDistancias(consulta);
           int[] nn = nearestNeighbors(dist);
           clase = clasificar(nn);
       }
       return clase;
   }
   
   
   public int HammingDistance(String str1, String str2) {
       int a = 0;
       String sequenceX = str1.toLowerCase();
       String sequenceY = str2.toLowerCase();
       
       /*
       if (sequenceX.length() != sequenceY.length()) {
          return -1; //input strings should be of equal length
       }
       */

       for (int i = 0; i < str1.length(); i++) {
           if (str1.charAt(i) != str2.charAt(i)) {
               a++;
           }
       }
       return a;
   }
   
   public ArrayList<Double> calcDistanciasHamming(ArrayList<String> consulta){
       ArrayList<Double> result = new ArrayList<Double>();
       
       int tot_train = trainData.size()*percent/100;
       for(int i=0; i<tot_train; i++){
           double dist = 0;
           for(int j=0; j<trainData.get(i).size()-1; j++){
               int hammingd = HammingDistance(trainData.get(i).get(j), consulta.get(j));
               dist += hammingd;
           }
           result.add(dist);
       }

       return result;
   }
   
   public int[][] test(){
       
       int matrixSize = attValues.get(attValues.size()-1).size();
       int[][] confMatrix = new int[matrixSize][matrixSize];
       
       for(int i=0; i<matrixSize; i++)
           for(int j=0; j<matrixSize; j++)
               confMatrix[i][j] = 0;
       
       int tot_train = trainData.size()*percent/100;
       
       //Clasifiacacion de las instancias de prueba y creacion de la matriz de confusion
       for(int i=tot_train; i<trainData.size(); i++){
           String clase = clasificador(trainData.get(i));
           String realClass = trainData.get(i).get(attributes.size()-1);
           
           int indexPred = attValues.get(attributes.size()-1).indexOf(clase);
           int indexReal = attValues.get(attributes.size()-1).indexOf(realClass);
           
           confMatrix[indexReal][indexPred]++;
       }
       
       return confMatrix;
       
       /*
       int vp = confMatrix[0][0];
       int fn = confMatrix[0][1];
       int fp = confMatrix[1][0];
       int vn = confMatrix[1][1];
       
       
       //System.out.println(vp);
       //System.out.println(fn);
       //System.out.println(fp);
       //System.out.println(vn);
       
       //precision, exhaustividad y f-measure
       double precision = (double)vp / (vp + fp);
       double exhaustividad = (double)vp / (vp + fn);
       double f1 = (precision*exhaustividad / (precision + exhaustividad))*2;
       
       System.out.print("Numero de instancias clasificadas: ");
       System.out.println(trainData.size()-tot_train);
       System.out.print("precision: ");
       System.out.println(precision);
       System.out.print("exhaustividad: ");
       System.out.println(exhaustividad);
       System.out.print("f1: ");
       System.out.println(f1);
       
       System.out.println();
       
       //Matriz de confusion
       
       System.out.print("  ");
       
       for(int i=0; i<matrixSize; i++){
           System.out.print(attValues.get(attValues.size()-1).get(i));
           System.out.print(" ");
       }
       
       System.out.println();
       
       for(int i=0; i<matrixSize; i++){
           System.out.print(attValues.get(attValues.size()-1).get(i));
           System.out.print(" ");
           for(int j=0; j<matrixSize; j++){
               System.out.print(confMatrix[i][j]);
               System.out.print(" ");
           }
           System.out.println();
       }
       */
   }

}
