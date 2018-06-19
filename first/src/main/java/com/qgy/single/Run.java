package com.qgy.single;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: qgy
 * @Date: 2018/3/10 17:09
 * @Description:
 */
public class Run
{
    public static void main(String[] args) {


                System.out.println(Thread.currentThread().getName());

                for(int i=0; i<10; i++){

                    new Thread("" + i){

                        public void run(){

                            System.out.println("Thread: " + getName() + "running");

                        }

                    }.start();

                }

            }

}


