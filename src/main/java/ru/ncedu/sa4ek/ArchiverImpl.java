package ru.ncedu.sa4ek;

import java.io.*;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

/**
 * Created by sa4ek on 24.12.14.
 */
public class ArchiverImpl implements Archiver {
    byte[] readBuffer = new byte[2048];
    int fileCounter = 0;

    @Override
    public String createArchive(String pathToZip, String outputFileName) {
        try {
            ZipOutputStream zos = new ZipOutputStream(new FileOutputStream(outputFileName));
            File f = new File(pathToZip);
            if (f.isDirectory()) {
                zipDir(pathToZip, zos);
            }
            else {
                zipFile(f, zos);
            }
            zos.close();
            return fileCounter + " files added.";
        } catch (IOException e){
            return "Sorry, but file wasn't found";
        }
    }

    @Override
    public String unpackArchive(String zipFileName, String outputPath)  {
        try {
            ZipFile zipFile = new ZipFile(zipFileName);
            Enumeration enu = zipFile.entries();
            while (enu.hasMoreElements()) {
                ZipEntry zipEntry = (ZipEntry) enu.nextElement();
                String name = zipEntry.getName();

                File file = new File(outputPath + File.separator + name);
                if (name.endsWith("/")) {
                    file.mkdirs();
                    continue;
                }
                File parent = file.getParentFile();
                if (parent != null) {
                    parent.mkdirs();
                }
                writeFile(zipEntry,zipFile,file);
                fileCounter++;
            }
            zipFile.close();
            return  fileCounter + " files unpacked.";
        } catch (IOException e) {
            return "Sorry couldn't find file";
        }
    }

    private void writeFile(ZipEntry zipEntry, ZipFile zipFile, File file) throws IOException {
        InputStream is = zipFile.getInputStream(zipEntry);
        FileOutputStream fos = new FileOutputStream(file);
        byte[] bytes = new byte[1024];
        int length;
        while ((length = is.read(bytes)) >= 0) {
            fos.write(bytes, 0, length);
        }
        is.close();
        fos.close();
    }

    private void zipDir(String dir2zip, ZipOutputStream zos) throws IOException {
        File zipDir = new File(dir2zip);
        String[] dirList = zipDir.list();
        for (String s: dirList) {
            File f = new File(zipDir, s);
            if (f.isDirectory()) {
                String filePath = f.getPath();
                zipDir(filePath, zos);
                continue;
            }
            zipFile(f, zos);
        }
    }

    private void zipFile(File f, ZipOutputStream zos) throws IOException {
        int bytesIn;
        fileCounter++;
        FileInputStream fis;
        fis = new FileInputStream(f);
        ZipEntry anEntry = new ZipEntry(f.getPath());
        zos.putNextEntry(anEntry);
        while ((bytesIn = fis.read(readBuffer)) != -1) {
            zos.write(readBuffer, 0, bytesIn);
        }
        fis.close();
    }
}
