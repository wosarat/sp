/*********************************
 * PROPRIETARY/CONFIDENTIAL.  Use of this product is subject to license terms.
 * Copyright (c) 2014 NVision Group, Inc. All rights reserved.
 *
 * C1.java 22.09.2014 17:41:22
 *********************************/
package stepic2014;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class C4 {

    public static void main(String[] args) {
        @SuppressWarnings("resource")
        Scanner scanner = new Scanner(System.in);
        long n = scanner.nextLong();
        int m = scanner.nextInt();
        System.out.println(solve(n,m));
    }

    private static long solve(long n,int m) {
        List<Long> period = findPeriod(m);
        log("Period size: %s", period.size());
        return period.get((int) (n%period.size()));
    }
    private static List<Long> findPeriod(int m) {
        List<Long> reminders=new ArrayList<Long>();
        reminders.add(0l);
        reminders.add(1l);
        long fa=0;
        long fb=1;
        long fResult;
        do{
            fResult=(fa+fb)%m;

            fa=fb;
            fb=fResult;
            if(fa==0 && fb==1){
                reminders.remove(reminders.size()-1);
                break;
            }
            reminders.add(fResult);
        }while(true);
        return reminders;
    }
    private static void log(String format, Object ... args){
        System.err.format(format, args);
        System.err.println();
    }

}
