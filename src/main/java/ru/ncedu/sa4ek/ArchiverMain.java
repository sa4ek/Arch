package ru.ncedu.sa4ek;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by sa4ek on 24.12.14.
 */
public class ArchiverMain {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String command;
        while(true){
            command = reader.readLine();
            if(command.equals("exit")) System.exit(0);
            else if(command.equals("zip")){
                ArchiverImpl archiver = new ArchiverImpl();
                System.out.println("Please, enter path to file or directory.");
                String files = reader.readLine();
                System.out.println("Please, enter name and path to output zip-file.");
                String output = reader.readLine();
                System.out.println("Done: " +
                        archiver.createArchive(files,output));
            }
            else if(command.equals("unzip")){
                ArchiverImpl archiver = new ArchiverImpl();
                System.out.println("Please, enter zip-file name");
                String zipfile = reader.readLine();
                System.out.println("Please, enter path to unzip");
                String path = reader.readLine();
                System.out.println("Done: " +
                archiver.unpackArchive(zipfile, path));
            }
            else {
                System.out.println("Usage: zip/unzip/exit");
            }
        }
    }
}
