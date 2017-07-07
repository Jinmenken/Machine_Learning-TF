/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package NaiveBayes;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Objects;

/**
 *
 * @author Alumnos
 */
public class NaiveBayes {
   String nom_dataset;
   ArrayList<String> attributes;
   ArrayList<ArrayList<String>> attValues;
   ArrayList<ArrayList<String>> trainData;
   int percent;
   
   public NaiveBayes(ArrayList<String> atributos, ArrayList<ArrayList<String>> valores, ArrayList<ArrayList<String>> data, int percent){
       this.attributes = atributos;
       this.attValues = valores;
       this.trainData = data;
       this.percent = percent;
   }
   
   public int contar(int indexAtt1, int indexVal1, int indexVal2){
       int num = 0;
       
       int tot_train = trainData.size()*percent/100;
       String valor1 = attValues.get(indexAtt1).get(indexVal1);
       String valor2 = attValues.get(attributes.size()-1).get(indexVal2);
       for(int i = 0; i < tot_train; i++){
           if(trainData.get(i).get(indexAtt1).equals(valor1) && trainData.get(i).get(attributes.size()-1).equals(valor2))
               num++;
       }
       
       return num;
   }
   
   public ArrayList<Double[][]> hallarProbabilidades(){
       ArrayList<Double[][]> probabilidades = new ArrayList<Double[][]>();
       
       int tot_train = trainData.size()*percent/100;
       int alpha = 1;
       
       Double[][] pClase = new Double[attValues.get(attributes.size()-1).size()][1];
       
       int[] count = new int[attValues.get(attributes.size()-1).size()];
       for(int i = 0; i < attValues.get(attributes.size()-1).size(); i++)
           count[i] = 0;
       
       for(int i = 0; i < attValues.get(attributes.size()-1).size(); i++){
           for(int j = 0; j < tot_train; j++){
               if(trainData.get(j).get(attributes.size()-1).equals(attValues.get(attributes.size()-1).get(i)))
                   count[i]++;
           }
       }
       
       Double card = (double)attValues.get(attributes.size()-1).size();
       
       for(int i = 0; i < attValues.get(attributes.size()-1).size(); i++)
           pClase[i][0] = (count[i]+alpha)/(tot_train+card);
       
       Double total = (double)tot_train;
       for(int i = 0; i < attributes.size()-1; i++){ //atributos
           Double[][] prob = new Double[attValues.get(i).size()][attValues.get(attributes.size()-1).size()];
           for(int j = 0; j < attValues.get(i).size(); j++){ //valores de atributos
               Double cardinalidad = (double)attValues.get(i).size();
               for(int k = 0; k < attValues.get(attributes.size()-1).size(); k++){ //valores de clase
                   /*
                   System.out.print(contar(i,j,k));
                   System.out.print(":");
                   System.out.print(cardinalidad);
                   System.out.print(":");
                   System.out.print(total);
                   System.out.print(":");
                   System.out.print(pClase[k][0]);*/
                   prob[j][k] = ((contar(i,j,k)/total)+alpha)/(pClase[k][0]+cardinalidad);
                   /*
                   System.out.print("->");
                   System.out.println(prob[j][k]);*/
               }
           }
           probabilidades.add(prob);
       }
       probabilidades.add(pClase);
       
       return probabilidades;
   }
   
   public String clasificar(ArrayList<String> consulta){
       String clase = "";
       ArrayList<Double> probabilidades = new ArrayList<Double>();
       
       ArrayList<Double[][]> factores = hallarProbabilidades();
       /*
       for(int i = 0; i < factores.size()-1; i++){
           System.out.println(attributes.get(i));
           for(int j = 0; j < attValues.get(i).size(); j++){
               for(int k = 0; k < attValues.get(attributes.size()-1).size(); k++){
                   System.out.print(factores.get(i)[j][k]);
                   System.out.print(" ");
               }
               System.out.println();
           }
           System.out.println();
           System.out.println();
       }
       System.out.println(attributes.get(attributes.size()-1));
       for(int i = 0; i < attValues.get(attributes.size()-1).size(); i++){
            System.out.print(factores.get(factores.size()-1)[i][0]);
            System.out.print(" ");
       }
       System.out.println();
       System.out.println();
       System.out.println();*/
       
       for(int i= 0; i < attValues.get(attributes.size()-1).size(); i++){
           Double probabilidad = factores.get(factores.size()-1)[i][0];
           for(int j = 0; j < consulta.size()-1; j++){
               int indexVal = attValues.get(j).indexOf(consulta.get(j));
               
               probabilidad *= factores.get(j)[indexVal][i];
           }
           probabilidades.add(probabilidad);
       }
       
       int index;
       if(probabilidades.get(0) > probabilidades.get(1))
           index = 0;
       else
           index = 1;
       
       /*
       ArrayList<Double> probAux = probabilidades;
       Collections.sort(probAux);
       
       int index = 0;
       for(int i=0; i<probabilidades.size(); i++){
            if(Objects.equals(probabilidades.get(i), probAux.get(0))){
                index = i;
            }
            System.out.print(probabilidades.get(i));
            System.out.print(":::");
       }
       */
       
       clase = attValues.get(attributes.size()-1).get(index);
       
       /*
       System.out.print(probabilidades.get(0));
       System.out.print(":::");
       System.out.print(probabilidades.get(1));
       System.out.print(":::");
       System.out.print(clase);
       System.out.println();
       */
       
       return clase;
   }
   
   public int[][] test(){
       
       int matrixSize = attValues.get(attValues.size()-1).size();
       int[][] confMatrix = new int[matrixSize][matrixSize];
       
       for(int i=0; i<matrixSize; i++)
           for(int j=0; j<matrixSize; j++)
               confMatrix[i][j] = 0;
       
       int tot_train = trainData.size()*percent/100;
       
       //Clasificacion de las instancias de prueba y creacion de la matriz de confusion
       for(int i=tot_train; i<trainData.size(); i++){
           String clase = clasificar(trainData.get(i));
           String realClass = trainData.get(i).get(attributes.size()-1);
           
           /*
           System.out.print(i+175);
           System.out.print("->");
           System.out.print(clase);
           System.out.print("::::");
           System.out.println(realClass);
           */
           
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
