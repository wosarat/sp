/*********************************
 * PROPRIETARY/CONFIDENTIAL.  Use of this product is subject to license terms.
 * Copyright (c) 2013 NVision Group, Inc. All rights reserved.
 *
 * Generator.java 14.06.2013 21:23:04
 *********************************/


/**
 * @author starasov
 *
 */
public class Generator {

    /**
     * 
     */
    public Generator() {
        // TODO Auto-generated constructor stub
    }
    public static void main(String[] args) {
        for(int i=2;i<100000;i++){
            System.out.print("heavymetal");
            if(i%100==0){
                System.out.flush();
            }
        }
        System.out.println();
    }

}
