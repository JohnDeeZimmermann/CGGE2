package de.cg.cgge.files;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import static de.cg.cgge.utils.StringConstants.COLON;
import static de.cg.cgge.utils.StringConstants.SEMICOLON;

public class FileContents {

    private String splitter = SEMICOLON;

    private ArrayList<String> contents;
    private Map<String, String> contentCache;

    /**
     * Creates a container for the contents, which makes it easy
     * @param contents The lines of the file
     */
    public FileContents(ArrayList<String> contents) {
        this.contents = contents;
        loadKeyWordsToCache();
    }

    /**
     * 
     * @return Returns every line of the file as a String array
     */
    public String[] get() {
        return this.contents.toArray(new String[contents.size()]); 
    }

    /**
     * 
     * @return Returns every line of the file as an ArrayList of type string
     */
    public ArrayList<String> getArrayList() {
        return this.contents;
    }

    /**
     * Sets the lines of the file
     * @param contents A string array
     */
    public void set(String[] contents) {
       this.contents = new ArrayList<String>(Arrays.asList(contents));
    }

    /**
     * Sets the lines of the file
     * @param contents An ArrayList of type String
     */
    public void set(ArrayList<String> contents) {
        this.contents = contents; 
    }

    /**
     * Prints every line of the file
     */
    public void print() {
        for (String str : contents) {
            System.out.println(str);
        }
    }

    /**
     * Edit a specific line
     * @param line The line index
     * @param newString The new string, that should be applied to the line
     */
    public void edit(int line, String newString) {
        contents.set(line, newString);
    }

    /**
     * Append a line to the current contents, and refresh the cache.
     * @param str The line to be appended
     */
    public void append(String str) {
        contents.add(str);
        appendToCache(str);
    }

    /**
     * Sets how the table columns should be split
     * @param splitter The splitter to be used
     */
    public void setTableSplitter(String splitter) {
        this.splitter = splitter; 
    }

    /**
     * Searches for row and column and returns that specific string
     * @param row Row
     * @param column Column
     * @return What is in the table at row and column
     */
    public String getFromTableSection(int row, int column) {
        return get()[row].split(splitter)[column];
    }

    /**
     * Returns an Array of columns, that are in an individual row
     * @param row Row
     * @return Array of columns
     */
    public String[] getColumnsFromTableRow(int row) {
        return get()[row].split(splitter);
    }

    /**
     * Searches for specific keyword in file contents.
     * When the file contains a line
     *  <i>a keyword: test test</i>
     * then getFromKeyword("a test") would return 'test test'
     * @param keyword The keyword, that should be looked out for
     * @return The result of the keyword
     */
    public String getFromKeyword(String keyword) {
        if (contentCache.containsKey(keyword))
            return contentCache.get(keyword);
        return null;
    }

    /**
     * Searches for specific keyword in file contents.
     * When the file contains a line
     *  <i>a keyword: 2</i>
     * then getIntFromKeyword("a keyword") would return the integer 2
     * Only does parsing from keyword contents to integer. So there is no error catching involved
     * @param keyword The keyword, that should be looked out for
     * @return The int result of the keyword
     */
    public int getIntFromKeyword(String keyword) {
        String kw = getFromKeyword(keyword);
        return Integer.parseInt(kw);
    }

    /**
     * Searches for specific keyword in file contents.
     * When the file contains a line
     *  <i>a keyword: 2.0</i>
     * then getDoubleFromKeyword("a keyword") would return the double 2.0
     * Only does parsing from keyword contents to double. So there is no error catching involved
     * @param keyword The keyword, that should be looked out for
     * @return The double result of the keyword
     */
    public double getDoubleFromKeyword(String keyword) {
        String kw = getFromKeyword(keyword);
        return Double.parseDouble(kw);
    }

    /**
     * Searches for specific keyword in file contents.
     * When the file contains a line
     *  <i>a keyword: true</i>
     * then getBoolFromKeyword("a keyword") would return the boolean 'true'
     * Only does parsing from keyword contents to boolean. So there is no error catching involved
     * @param keyword The keyword, that should be looked out for
     * @return The bool result of the keyword
     */
    public boolean getBoolFromKeyword(String keyword) {
        String kw = getFromKeyword(keyword);
        return Boolean.parseBoolean(kw);
    }


    /**
     * Searches for specific keyword in file contents.
     * When the file contains a line
     *  <i>a keyword: a;b;c</i>
     * then getArrayFromKeyword("a keyword", ";") would return the string array {"a","b","c"}
     * @param keyword The keyword, that should be looked out for
     * @param regexSeperator The regex that's used in the String.split(String) method.
     * @return The string array result of the keyword
     */
    public String[] getArrayFromKeyword(String keyword, String regexSeperator) {
        String kw = getFromKeyword(keyword);
        return kw.split(regexSeperator);
    }

    /**
     * Iterates over the whole list of contents and populates the key-value pairs of the content list separated by ':' into the contentCache hash map.
     */
    private void loadKeyWordsToCache(){
        try {
            for (String row : get()) {
                appendToCache(row);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * Adds the input string to the local cache.
     * @param str key - value string separated by ':' to be added in the cache.
     */
    private void appendToCache(String str){

        if (!str.contains(":")) return;

        if(contentCache == null){
            contentCache = new HashMap<>();
        }

        try{
            String[] columns = str.split(COLON);
            if (columns == null) return;
            contentCache.put(columns[0].trim(), columns[1].trim());
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}