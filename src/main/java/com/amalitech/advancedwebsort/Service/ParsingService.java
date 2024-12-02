package com.amalitech.advancedwebsort.Service;


import com.amalitech.advancedwebsort.Requests.SortRequest;
import org.springframework.stereotype.Component;

@Component
public class ParsingService {
    public int [] IntParser(SortRequest sortRequest){
         String input = sortRequest.values();
         if (input==null || input.isEmpty()) {
              throw  new IllegalArgumentException(" values cannot be  empty");
         }
          String[]stringArray= input.split(",");
        final int arraySize = stringArray.length;
        int index= 0;
        final int[] arrayPassed =  new int[arraySize];
        for(String v :stringArray){
            try{
                arrayPassed[index++]=  Integer.parseInt(v.trim());
            }
             catch (NumberFormatException e){
                 throw  new NumberFormatException("invalid character passed:"+v);
             }
            }


        return  arrayPassed;
    }

    public float [] floatParser(SortRequest sortRequest){
        String input = sortRequest.values();
        if (input==null || input.isEmpty()) {
            throw  new IllegalArgumentException(" values cannot be  empty");
        }
        String[]stringArray= input.split(",");
        final int arraySize = stringArray.length;
        int index= 0;
        final float [] arrayPassed =  new float [arraySize];
        for(String v :stringArray){
            try{
                arrayPassed[index++]=  Float.parseFloat(v.trim());
            }
            catch (NumberFormatException e){
                throw  new NumberFormatException("invalid character passed:"+v);
            }
        }


        return  arrayPassed;
    }


}
