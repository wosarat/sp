package codeforces.round160;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicInteger;

public class MagicTask {
    private final static Scanner in=new Scanner(System.in);
    private final PrintWriter out=new PrintWriter(new OutputStreamWriter(System.out, "windows-1251"),true);
    private final PrintWriter debug=new PrintWriter(System.err,true);
    
    public static void main(String[] args) throws UnsupportedEncodingException {
            new MagicTask().solve();
    }
    /**
     * 
     */
    public MagicTask() throws UnsupportedEncodingException{
        // TODO Auto-generated constructor stub
    }

    /**
     * 
     */
    private void solve() {
        String universalSpell = in.next();
        String mostPowerfullSpell=findMostPowerfullSpell(universalSpell);
        out.println(mostPowerfullSpell);
    }

    private static class PowerfullSpell implements Comparable<PowerfullSpell>{
        private final String spell;
        private final AtomicInteger power=new AtomicInteger(0);
        /**
         * 
         */
        public PowerfullSpell(String spell) {
            this.spell=spell;
        }
        @Override
        public int compareTo(PowerfullSpell o) {
            int result = power.get()-o.power.get();
            if(result==0){
                result = spell.length()-o.spell.length();
            }
            return result;
        }
        /**
         * @return the power
         */
        public AtomicInteger getPower() {
            return power;
        }
        /**
         * @return the spell
         */
        public String getSpell() {
            return spell;
        }
        
    }
    private String findMostPowerfullSpell(String universalSpell) {
        HashMap<String, PowerfullSpell> spells=new HashMap<String, MagicTask.PowerfullSpell>();
        for(int spellLength=2,maxLength=universalSpell.length()-1;spellLength<=maxLength;spellLength++){
            for(int beginIndex=0,max=(universalSpell.length()-spellLength);beginIndex<=max;beginIndex++){
                String spellString = universalSpell.substring(beginIndex,beginIndex+spellLength);
                PowerfullSpell spell=spells.get(spellString);
                if(spell==null){
                    spell=new PowerfullSpell(spellString);
                    spells.put(spellString, spell);
                }
                spell.getPower().incrementAndGet();
            }
        }
        return Collections.max(spells.values()).getSpell();
    }
    
}
