package com.example.przemek.calculator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class Symbols {

    private HashMap<Character, String> symbolsMap;
    private List <String> operationSymbolList;
    private List <String> symbolList = new ArrayList<String>();

    public Symbols(){
        initSymbolMap();
        operationSymbolList = Arrays.asList("+ - * /".split(" "));
        initSymbolList();
    }

    private void initSymbolMap(){
        symbolsMap = new HashMap<>();
        symbolsMap.put('+', "plus");
        symbolsMap.put('-', "minus");
        symbolsMap.put('*', "multiply");
        symbolsMap.put('/', "divide");
        symbolsMap.put('.', "dot");
    }

    private void initSymbolList(){
        Iterator it = symbolsMap.entrySet().iterator();
        while(it.hasNext()){
            Map.Entry pair = (Map.Entry)it.next();
            final Character operationSymbol = (Character) pair.getKey();
            symbolList.add(String.valueOf(operationSymbol));
        }
    }

    public HashMap<Character, String> getSymbolMap(){
        return symbolsMap;
    }

    public List<String> getOperationSymbolList(){
        return operationSymbolList;
    }

    public List<String> getSymbolList(){
        return symbolList;
    }

    public boolean isSymbol(char c){
        for(String s : symbolList){
            if(s.equals(String.valueOf(c))){
                return true;
            }
        }
        return false;
    }

    public boolean isOperationSymbol(Character c){
        for(String operationSymbol : operationSymbolList){
            if(operationSymbol.equals(String.valueOf(c))){
                return true;
            }
        }
        return false;
    }

}
