/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package poo.ra04;

import java.util.Random;

/**
 *
 * @author renan
 */
public class RA04 {

    static long iteracoes = 0;
    static long trocas = 0;

    public static void main(String[] args) {

        int numDeElementos = 10000;
        int[] elementosOriginal = new int[numDeElementos];
        int[] elementos = new int[numDeElementos];

        // Random randomSeed = new Random();
        Random random = new Random(1);

        for (int i = 0; i < numDeElementos; i++) {
            int n = random.nextInt(numDeElementos);
            elementosOriginal[i] = n;
            elementos[i] = n;
        }

        int avgN = 15; // NUMERO DE RODADAS PARA A MEDIA
        int soma = 0;
        long avgTime = 0;

        long startTime;
        long finishTime;
        long timeElapsed;

        for (int i = 0; i < avgN; i++) {

            for (int j = 0; j < numDeElementos; j++) {
                elementos[j] = elementosOriginal[j];
            }

            startTime = System.nanoTime();
            BubbleSort(elementos, numDeElementos);
            finishTime = System.nanoTime();
            timeElapsed = finishTime - startTime;

            soma += timeElapsed;

            //System.out.println("BubbleSort: " + timeElapsed + " ns");
        }
        avgTime = soma / avgN;

        System.out.println("BubbleSort Media (" + avgN + "): " + avgTime + " ns");
        System.out.println(avgTime / 1000000 + " ms");
        System.out.println("BubbleSort Trocas: " + trocas / avgN);
        System.out.println("BubbleSort Iteracoes: " + iteracoes / avgN);

        soma = 0;
        trocas = 0;
        iteracoes = 0;

        for (int i = 0; i < avgN; i++) {

            for (int j = 0; j < numDeElementos; j++) {
                elementos[j] = elementosOriginal[j];
            }

            startTime = System.nanoTime();
            MergeSort(elementos, 0, numDeElementos);
            finishTime = System.nanoTime();
            timeElapsed = finishTime - startTime;

            soma += timeElapsed;

            //System.out.println("MergeSort: " + timeElapsed + " ns");
        }
        avgTime = soma / avgN;

        System.out.println("MergeSort Media (" + avgN + "): " + avgTime + " ns");
        System.out.println(avgTime / 1000000 + " ms");
        System.out.println("MergeSort Trocas: " + trocas / avgN);
        System.out.println("MergeSort Iteracoes: " + iteracoes / avgN);

        soma = 0;
        trocas = 0;
        iteracoes = 0;

        for (int i = 0; i < avgN; i++) {

            for (int j = 0; j < numDeElementos; j++) {
                elementos[j] = elementosOriginal[j];
            }

            startTime = System.nanoTime();
            QuickSort(elementos, 0, numDeElementos - 1);
            finishTime = System.nanoTime();
            timeElapsed = finishTime - startTime;

            soma += timeElapsed;

            //System.out.println("QuickSort: " + timeElapsed + " ns");
        }
        avgTime = soma / avgN;

        System.out.println("QuickSort Media (" + avgN + "): " + avgTime + " ns");
        System.out.println(avgTime / 1000000 + " ms");
        System.out.println("QuickSort Trocas: " + trocas / avgN);
        System.out.println("QuickSort Iteracoes: " + iteracoes / avgN);

    }

    public static void BubbleSort(int[] elementos, int tamanho) {

        for (int i = 0; i < tamanho; i++) {
            for (int j = 1; j < tamanho; j++) {
                if (elementos[j - 1] > elementos[j]) {
                    int temp = elementos[j - 1];
                    elementos[j - 1] = elementos[j];
                    elementos[j] = temp;
                    trocas++;
                }
                iteracoes++;
            }
        }
    }

    public static void MergeSort(int[] elementos, int inicio, int fim) {
        int meio = 0;
        if (fim - inicio > 1) {
            meio = (fim + inicio) / 2;
            MergeSort(elementos, inicio, meio);
            MergeSort(elementos, meio, fim);
            Merge(elementos, inicio, meio, fim);
        }
    }

    public static void Merge(int[] elementos, int inicio, int meio, int fim) {
        int[] esquerda = new int[meio - inicio];
        int[] direita = new int[fim - meio];

        int j = 0;
        for (int i = inicio; i < meio; i++) {
            esquerda[j] = elementos[i];
            j++;
        }

        j = 0;
        for (int i = meio; i < fim; i++) {
            direita[j] = elementos[i];
            j++;
        }

        int iEsq = 0;
        int iDir = 0;
        for (int i = inicio; i < fim; i++) {
            if (iEsq >= meio - inicio) {
                iDir++;
            } else if (iDir >= fim - meio) {
                elementos[i] = esquerda[iEsq];
                iEsq++;
                trocas++;
            } else if (esquerda[iEsq] < direita[iDir]) {
                elementos[i] = esquerda[iEsq];
                iEsq++;
                trocas++;
            } else {
                elementos[i] = direita[iDir];
                iDir++;
                trocas++;
            }
            iteracoes++;
        }
    }

    public static void QuickSort(int[] elementos, int inicio, int fim) {

        if (inicio < fim) {
            int pivo = Particiona(elementos, inicio, fim);
            QuickSort(elementos, inicio, pivo - 1);
            QuickSort(elementos, pivo + 1, fim);
        }
    }

    public static int Particiona(int[] elementos, int inicio, int fim) {
        int pivo = elementos[fim];
        int inferior = inicio;
        for (int i = inicio; i < fim; i++) {
            if (elementos[i] <= pivo) {
                int temp = elementos[inferior];
                elementos[inferior] = elementos[i];
                elementos[i] = temp;
                inferior++;

                trocas++;
            }
            iteracoes++;
        }
        int temp = elementos[inferior];
        elementos[inferior] = elementos[fim];
        elementos[fim] = temp;

        trocas++;

        return inferior;
    }
}
