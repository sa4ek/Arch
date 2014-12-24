package ru.ncedu.sa4ek;

import org.junit.Assert;
import org.junit.Test;

public class ArchiverImplTest {
    String dirToZip = "/home/sa4ek/MavenWorkSpace/Arch/src/test/resources/ToZip/vk_friends-1.0.0/";
    String outputZipFile = "/home/sa4ek/MavenWorkSpace/Arch/src/test/resources/result/ToZip/1.zip";

    String zipFile = "/home/sa4ek/MavenWorkSpace/Arch/src/test/resources/ToUnzip/1.zip";
    String outputPath = "/home/sa4ek/MavenWorkSpace/Arch/src/test/resources/result/ToUnzip";
    @Test
    public void zipFile(){
        ArchiverImpl archiver = new ArchiverImpl();
        Boolean test;
        test = archiver.createArchive(dirToZip,outputZipFile);
        Assert.assertEquals(true, test);

        test = archiver.createArchive("1.zip",outputZipFile);
        Assert.assertEquals(false, test);
    }

    @Test
    public void unZipFile(){
        ArchiverImpl archiver = new ArchiverImpl();
        Boolean test;
        test = archiver.unpackArchive(zipFile,outputPath);
        Assert.assertEquals(true, test);

        test = archiver.unpackArchive("jdom.zip",outputPath);
        Assert.assertEquals(false, test);
    }

}