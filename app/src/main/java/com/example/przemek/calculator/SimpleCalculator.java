package com.example.przemek.calculator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by Przemek on 06.03.2018.
 */

public class SimpleCalculator {

    private HashMap<Character, String> symbolsMap = new HashMap<>();
    private List <String> operationSymbolsList = Arrays.asList("+ - * /".split(" "));

    SimpleCalculator(){
        initSymbolsMap();
    }

    private void initSymbolsMap(){
        symbolsMap.put('+', "plus");
        symbolsMap.put('-', "minus");
        symbolsMap.put('*', "multiply");
        symbolsMap.put('/', "divide");
        symbolsMap.put('.', "dot");
    }

    public HashMap <Character, String> getSymbolsMap(){
        return symbolsMap;
    }

    public List<String> getOperationSymbolsList(){
        return operationSymbolsList;
    }

    public boolean isSymbol(char c){
        Iterator it = symbolsMap.entrySet().iterator();
        while(it.hasNext()){
            Map.Entry pair = (Map.Entry)it.next();
            final Character operationSymbol = (Character) pair.getKey();
            if(operationSymbol.equals(c)){
                return true;
            }
        }
        return false;
    }

    public boolean isOperationSymbol(Character c){
        for(String operationSymbol : operationSymbolsList){
            if(operationSymbol.equals(String.valueOf(c))){
                return true;
            }
        }
        return false;
    }

}