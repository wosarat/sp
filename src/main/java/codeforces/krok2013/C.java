package codeforces.krok2013;

import java.io.BufferedOutputStream;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class C {
    private final Scanner in=new Scanner(System.in, StandardCharsets.UTF_8);
    private final PrintWriter out=new PrintWriter(new BufferedOutputStream(System.out),true);
    @SuppressWarnings("unused")
    private final PrintWriter debug=new PrintWriter(System.err,true);
    private final Set<Character> chars= new HashSet<>();
    public static void main(String[] args) {
        new C().solve();
    }
    /**
     * 
     */
    private static class IpParts{
        int part3;
        int part4;
        @Override
        public String toString() {
            return part3+"."+part4;
        }
        public IpParts(int part3, int part4) {
            super();
            this.part3 = part3;
            this.part4 = part4;
        }
        
    }
    private void solve() {
        int n = in.nextInt();
        for(int i=0;i<n;i++){
            chars.add((char) (in.nextInt()+'0'));
        }
        
        
        StringBuilder sb=new StringBuilder(100);
        for(int part1=0;part1<256;part1++){
            for(int part2=0;part2<256;part2++){
                String part1part2=""+part1+""+part2;
                sb.setLength(0);
                String s1=sb.append(part1part2).reverse().toString();
                String s2=s1.substring(1);
                for(char c:chars){
                    String sss=c+s1;
                    if(check2(sss)){
                        continue;
                    }
                    for(IpParts part:findIpParts(sss)){
                        out.println(part1+"."+part2+"."+part.toString());
                    }
                }

                if(check2(part1part2)){
                    continue;
                }

                for(IpParts part:findIpParts(s1)){
                    out.println(part1+"."+part2+"."+part.toString());
                }
                for(IpParts part:findIpParts(s2)){
                    out.println(part1+"."+part2+"."+part.toString());
                }

            }

        }
        out.flush();
    }
    
    private boolean check2(String str) {
        Set<Character> chars2 = new HashSet<>();
        for(char c:str.toCharArray()){
            chars2.add(c);
        }
        return !chars.equals(chars2);
    }

    private List<IpParts> findIpParts(String s1) {
        List<IpParts> result= new ArrayList<>();
        for(int i=1;i<=s1.length()-1;i++){
            int part3 = Integer.parseInt(s1.substring(0, i));
            if(part3>255){
                continue;
            }
            int part4 = Integer.parseInt(s1.substring(i));
            if(part4>255){
                continue;
            }
            result.add(new IpParts(part3, part4));
        }
        
        return result;
    }

}