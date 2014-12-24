package ru.ncedu.sa4ek;

import java.io.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

/**
 * Created by sa4ek on 24.12.14.
 */
public class ArchiverImpl implements Archiver {
    byte[] readBuffer = new byte[2048];

    @Override
    public boolean createArchive(String pathToZip, String outputFileName) {
        try {
            ZipOutputStream zos = new
                    ZipOutputStream(new FileOutputStream(outputFileName));
            File f = new File(pathToZip);
            if (f.isDirectory())
                zipDir(pathToZip, zos);
            else {
                zipFile(f, zos);
            }
            zos.close();
            return true;
        } catch (FileNotFoundException e) {
            return false;
        } catch (IOException e) {
            return false;
        }
    }

    @Override
    public boolean unpackArchive(String zipFileName, String outputPath)  {
        File destDir = new File(outputPath);
        if (!destDir.exists()) {
            destDir.mkdir();
        }
        try {
            ZipInputStream zipIn = new ZipInputStream(new FileInputStream(zipFileName));
            ZipEntry entry = zipIn.getNextEntry();
            while (entry != null) {
                String filePath = outputPath + File.separator + entry.getName();
                if (!entry.isDirectory()) {
                    BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(filePath));
                    int read;
                    while ((read = zipIn.read(readBuffer)) != -1) {
                        bos.write(readBuffer, 0, read);
                    }
                    bos.close();
                } else {
                    File dir = new File(filePath);
                    dir.mkdir();
                }
                zipIn.closeEntry();
                entry = zipIn.getNextEntry();
            }
            zipIn.close();
        }catch (IOException e){
            return false;
        }
        return true;
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
