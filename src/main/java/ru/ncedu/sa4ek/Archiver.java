package ru.ncedu.sa4ek;

/**
 * Created by sa4ek on 24.12.14.
 */
public interface Archiver {
    /***
     * This method create archive
     * @param pathToZip name of file or directory to Zip
     * @param outputFileName name of output Zip-file
     * @return success or error
     */
    public boolean createArchive(String pathToZip, String outputFileName);

    /***
     *  This method unpacks archive
     * @param zipFileName name of Zip-file
     * @param outputPath path to UnZip
     * @return success or error
     */
    public boolean unpackArchive(String zipFileName, String outputPath);
}
