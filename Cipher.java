package ca.yorku.eecs.kryptonote;


public class Cipher {

    private String key;
    public static final String ALPHABET = " ABCDEFGHIJKLMNOPQRSTUVWXYZ";

    public Cipher(String k){

        this.key = k;
    }

    private String makePad(String note){
        String pad;
        for(pad = this.key;pad.length() < note.length();pad += this.key);

        /** or
         * String pad = this.key;
         * for (; pad.length() < note. length();)
         * { pad += this.key;
         * }
         */

        return pad;
    }
    public String encrypt(String note){ //THIS IS A TEST
        String pad = makePad(note);
        String result = "";
        for(int i = 0; i < note.length(); i++){
            String c = note.substring(i,i+1);   //extracts the character at i position
                                                //for example the word 'site', its length is 4. the code would be substring(0,1), it means
                                                //you extract a letter that starts inclusive at 0 but ends exclusive at 1.This means you only
                                                //extract the letter 's'.

            int position = ALPHABET.indexOf(c); //determine position in ALPHABET
            int shift = Integer.parseInt(pad.substring(i,i+1)); //determine shift amount based on the corresponding digit in the pad
            int newPosition = (position+shift) % ALPHABET.length();
            result += ALPHABET.substring(newPosition,newPosition+1);
        }
        return result;
    }

    public String decrypt(String note){         //UJLWAKVDBBWITV
        String pad = makePad(note);
        String decryptResult = "";
        for(int i = 0; i < note.length();i++){
            String c = note.substring(i,i+1);   //extracts the character at i position
                                                         //same word 'site', its length is 4. The code would be substring(3,4), it means
            int position = ALPHABET.indexOf(c);
            int shift = Integer.parseInt(pad.substring(i,i+1)); //determine shift amount based on the corresponding digit in the pad
            int newPosition = (position-shift) % ALPHABET.length(); //only difference in the code; shifts position by subtracting
            decryptResult += ALPHABET.substring(newPosition,newPosition+1);
        }
        return decryptResult;
    }

}
