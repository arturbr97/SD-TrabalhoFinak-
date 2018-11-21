package br.com.projetofinal.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FileManager {

    private final String fileName;
    private int lines;

    public FileManager(String fileName) {
        this.fileName = fileName;
    }

    public int countLines() {
        this.readData();
        return this.lines;
    }

    public List<String[]> simpleReadData() {
        BufferedReader br = null;
        FileReader fr = null;
        List<String[]> list = new ArrayList();

        try {

            fr = new FileReader(this.fileName);
            br = new BufferedReader(fr);

            String sCurrentLine;

            while ((sCurrentLine = br.readLine()) != null) {
                list.add(sCurrentLine.split(","));
                this.lines++;
            }

            return list;

        } catch (IOException e) {

            e.printStackTrace();

        } finally {

            try {

                if (br != null) {
                    br.close();
                }

                if (fr != null) {
                    fr.close();
                }

            } catch (IOException ex) {

                ex.printStackTrace();

            }

        }
        return null;
    }

    public String readData() {
        BufferedReader br = null;
        FileReader fr = null;
        StringBuilder builder = null;

        try {

            fr = new FileReader(this.fileName);
            br = new BufferedReader(fr);
            builder = new StringBuilder();

            String sCurrentLine;

            while ((sCurrentLine = br.readLine()) != null) {
                builder.append(sCurrentLine);
                this.lines++;
            }

            return builder.toString().replace(".", " ").replace(",", " ");

        } catch (IOException e) {

            e.printStackTrace();

        } finally {

            try {

                if (br != null) {
                    br.close();
                }

                if (fr != null) {
                    fr.close();
                }

            } catch (IOException ex) {

                ex.printStackTrace();

            }

        }
        return null;
    }

    public void write(String text) {
        BufferedWriter bw = null;
        FileWriter fw = null;

        try {

            fw = new FileWriter(this.fileName);
            bw = new BufferedWriter(fw);
            bw.write(text);

        } catch (IOException e) {

            e.printStackTrace();

        } finally {

            try {

                if (bw != null) {
                    bw.close();
                }

                if (fw != null) {
                    fw.close();
                }

            } catch (IOException ex) {

                ex.printStackTrace();

            }

        }
    }

    public void append(String text) {
        BufferedWriter bw = null;
        FileWriter fw = null;

        try {

            fw = new FileWriter(this.fileName, true);
            bw = new BufferedWriter(fw);
            bw.append(text);
            bw.append("\n");
            bw.flush();

        } catch (IOException e) {

            e.printStackTrace();

        } finally {

            try {

                if (bw != null) {
                    bw.close();
                }

                if (fw != null) {
                    fw.close();
                }

            } catch (IOException ex) {

                ex.printStackTrace();

            }

        }
    }

    public void simpleWriteData(List<String[]> nodes) {
        StringBuilder builder = new StringBuilder();
        for (String[] node : nodes) {
            builder.append(String.valueOf(node[0]).concat(","));
            builder.append(node[1].concat(","));
            builder.append(node[2].concat(","));
            builder.append(String.valueOf(node[3]).concat(","));
            builder.append(String.valueOf(node[4]));
            builder.append("\n");
        }
        
        this.write(builder.toString());
    }

}
