package de.cg.cgge.files;

import java.io.*;
import java.util.ArrayList;

public class GameFile {
    private File file;
    private FileContents fileContents = null; 
    private boolean fileContentsLoaded = false;

    /**
     * Creates a game file
     * WARNING: It is not loaded to memory. For that to happen, you should type [GameFile].loadToMemory()
     * @param path The relative path of the game file
     * @throws IOException Exception
     */
    public GameFile(String path) throws IOException {
        file = new File(path);
    }

    /**
     * 
     * @return Returns the java.io.File instance, the GameFile uses
     */
    public File getFile() {
        return this.file; 
    }

    /**
     * Loads the file to memory
     * @throws IOException Exception
     */
    public void loadToMemory() throws IOException {
        ArrayList<String> contents = new ArrayList<>();
        BufferedReader br = new BufferedReader(new FileReader(file)); 

        String store = ""; 
        while((store = br.readLine()) != null) {
            contents.add(store); 
        }

        br.close();

        if (!contents.isEmpty()) {
            fileContents = new FileContents(contents);
            fileContentsLoaded = true; 
        } else {
            System.err.println("Couldn't load contents. REASON: File is empty.");
        }
    }

    /**
     * 
     * @return Returns a java.de.cg.cgge.file.FileContents instance belonging to the GameFile
     */
    public FileContents getContents() {
        if(!fileContentsLoaded)
            throw new IllegalStateException("File needs to be loaded with loadToMemory()");

        return this.fileContents; 
    }

    /**
     * 
     * @param fc Set the file contents, the GameFile should have
     */
    public void setContents(FileContents fc) {
        this.fileContents = fc; 
    }

    /**
     * Saves the file to the disk at a different file path 
     * @param file The file, the GameFile should be stored to
     * @throws IOException Exception
     */
    public void save(File file) throws IOException {
        FileWriter writer = new FileWriter(file);
        String toWrite = ""; 
        for (String str : fileContents.get()) {
            toWrite = toWrite + str + "\n"; 
        } 

        writer.write(toWrite);
        writer.close();
    }

    /**
     * Saves the GameFile under the already existing path
     * @throws IOException Exception
     */
    public void save() throws IOException {
        save(this.file);
    }

    /**
     * Wipes the file from memory.
     * This does not delete the file from disk
     */
    public void deleteFromMemory() {
        fileContents = null; 
    }

    /**
     * 
     * @return Return a boolean based on the file contents being loaded to memory
     */
    public boolean isLoaded() {
        return (fileContents != null);
    }

}