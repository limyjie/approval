/**
 * Author: lin
 * Date: 2019/5/1 13:08
 */
package com.prd.approval.validation;

/**
 *<p></p>
 *
 */
public class Test {

    private static int race = 0;
    private static final int THREADS_COUNT = 20;

    private static void increase(){
        race ++;
    }

    public static void main(String[] args) {
        Thread[] threads = new Thread[THREADS_COUNT];

        for(int i =0 ;i<THREADS_COUNT;i++){
            threads[i] = new Thread(()->{
                for(int j = 0;j<1000;j++){
                    increase();
                }
            });
            threads[i].start();
        }

        while (Thread.activeCount()>1){
            /*
            Thread.activeCount()
             */
            System.out.println(Thread.activeCount());
            Thread.yield();
        }
        System.out.println(race);
    }

}
