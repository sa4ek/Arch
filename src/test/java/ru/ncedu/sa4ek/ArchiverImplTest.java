package ru.ncedu.sa4ek;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;

public class ArchiverImplTest {
    String dirToZip = "src/test/resources/ToZip/vk_friends-1.0.0/";
    String outputZipFile = "src/test/resources/result/ToZip/1.zip";

    String zipFile = "src/test/resources/ToUnzip/1.zip";
    String outputPath = "src/test/resources/result/ToUnzip";

    Archiver archiver;

    @Before
    public void setUp(){
        archiver = new ArchiverImpl();
    }

    @Test
    public void zipFile() throws IOException {
        String test = archiver.createArchive(dirToZip,outputZipFile);
        Assert.assertEquals("700 files added.", test);

        test = archiver.createArchive("1.zip",outputZipFile);
        Assert.assertEquals("Sorry, but file wasn't found", test);

    }

    @Test
    public void unZipFile(){
        String test = archiver.unpackArchive(zipFile,outputPath);
        Assert.assertEquals("8 files unpacked.", test);

        test = archiver.unpackArchive("jdom.zip",outputPath);
        Assert.assertEquals("Sorry couldn't find file", test);

    }

}