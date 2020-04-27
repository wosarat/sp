/*********************************
 * PROPRIETARY/CONFIDENTIAL.  Use of this product is subject to license terms.
 * Copyright (c) 2013 NVision Group, Inc. All rights reserved.
 *
 * MagicTask2.java 01.03.2013 16:18:50
 *********************************/
package codeforces.round160;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;

/**
 * @author starasov
 *
 */
public class MagicTask2 {


    private class Spell {

        String word;
        int power; //the number of occurrences
        int length;

        public int getPower() {
            return power;
        }

        public void setPower(int power) {
            this.power = power;
        }

        public int getLength() {
            return length;
        }

        public void setLength(int length) {
            this.length = length;
        }


        public String getWord() {
            return word;
        }

        public void setWord(String word) {
            this.word = word;
        }


        public Spell(String s) {
            this.word = s;
            this.length = (null != s) ? s.length() : 0;
            this.power = 1;
        }

        public void addPower(int p) {
            this.power = this.power + p;
        }

        public String toString() {
            return word + " (" + power + ")";
        }
    }

    public static void main(String[] args) {

        MagicTask2 mf = new MagicTask2();
        mf.printInfo("one of the most powerful spell is " + mf.findPowerSpell("ddxxyxxyxxxddd"));
    }

    public void printInfo(Object o) {
        System.out.println(o.toString());
    }

    public String findPowerSpell(String s) {


        if (s.length() == 1) {
            return s;
        }

        HashMap<String, Spell> magicBook = new HashMap<String, Spell>();
        ArrayList<Spell> dictionary;
        StringBuilder sb;

        for (int j = 0; j < s.length() - 1; j++) {
            sb = new StringBuilder(s.substring(j));
            String word;

            while (sb.length() > 1) {

                // System.out.println("sb:" + sb);
                word = sb.toString();

                if (magicBook.containsKey(word)) {

                    magicBook.get(word).addPower(1);
                } else {
                    magicBook.put(word, new Spell(word));
                }

                sb.deleteCharAt(sb.length() - 1);
            }
        }

        dictionary = new ArrayList<Spell>(magicBook.values());
        /* printInfo(dictionary);
      Collections.sort(dictionary, new SpellsComparator());
      printInfo(dictionary + "/" + Collections.max(dictionary, new SpellsComparator()));
        */
        return Collections.max(dictionary, new SpellsComparator()).getWord();
    }


    class SpellsComparator implements Comparator<Spell> {
        // Implement the compare() method so that
        // the more powerful spell has great  strength (at first) and then length
        public int compare(Spell s1, Spell s2) {

            int c = 0;
            if (s1.power > s2.power) {
                c = 1;
            } else if (s1.power == s2.power) {
                c = s1.getLength() - s2.getLength();
            } else {
                c = -1;
            }

            return c;

        }

    }

}
