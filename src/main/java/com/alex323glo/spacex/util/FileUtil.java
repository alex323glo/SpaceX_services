package com.alex323glo.spacex.util;

import java.io.*;

/**
 * Container for IO logic methods in different parts of app.
 *
 * @author alex323glo
 * @version 1.0.0
 */
public class FileUtil {

    public static final int DEFAULT_BYTE_FILE_SIZE = 1024 * 1024;   // 1 MB

    /**
     * Reads byte file.
     *
     * @param filePath path to target file.
     * @param numOfBytes number of bytes which will be read.
     * @return result byte array.
     *
     * @throws IOException when File System has some problems with reading target file.
     *
     * @see IOException
     * */
    public static byte[] readByteFile(String filePath, int numOfBytes) throws IOException {
        if (!fileIsValidToRead(filePath)) {
            return null;
        }

        FileInputStream fileInputStream = new FileInputStream(filePath);    // throws FileNotFoundException !

        byte[] bufferBytes = new byte[numOfBytes];
        fileInputStream.read(bufferBytes);          // throws IOException !

        return bufferBytes;
    }

    /**
     * Reads byte file.
     *
     * Similar to readByteFile(String, int) method except number of read bytes - this
     * method will try to read default number of bytes.
     *
     * @see FileUtil#readByteFile(String, int)
     * @see FileUtil#DEFAULT_BYTE_FILE_SIZE
     * @see IOException
     * */
    public static byte[] readByteFile(String filePath) throws IOException {
        return readByteFile(filePath, DEFAULT_BYTE_FILE_SIZE);
    }

    /**
     * Writes to byte file.
     *
     * @param filePath path to target file.
     * @param content byte array which will be written to file.
     * @return true, if operation was successful, and false, if it wasn't.
     *
     * @throws IOException when File System has some problems with writing to target file.
     *
     * @see IOException
     * */
    public static boolean writeByteFile(String filePath, byte[] content) throws IOException {
        if (content == null || content.length < 1) {
            throw new NullPointerException("content is null");
        }

        File file = new File(filePath);
        if (file.exists()) {
            if (!file.isFile()) {
                return false;
            }
        } else {
            file.createNewFile();
        }

        FileOutputStream fileOutputStream = new FileOutputStream(new File(filePath)); // throws FileNotFoundException !

        fileOutputStream.write(content);    // throws IOException !
        return true;
    }

    /**
     * Reads text file.
     *
     * @param filePath path to target file.
     * @return result String - concatenation of file's text rows.
     *
     * @throws IOException when File System has some problems with reading target file.
     *
     * @see IOException
     * */
    public static String readTextFile(String filePath) throws IOException {
        if (!fileIsValidToRead(filePath)) {
            return null;
        }

        BufferedReader bufferedReader =
                new BufferedReader(new FileReader(filePath));       // throws FileNotFoundException !

        StringBuilder stringBuilder = new StringBuilder();
        while (bufferedReader.ready()) {                        // throws IOException !
            stringBuilder.append(bufferedReader.readLine()).append("\n");   // throws IOException !
        }

        bufferedReader.close();             // throws IOException !
        return stringBuilder.toString();
    }

    /**
     * Writes to text file.
     *
     * @param filePath path to target file.
     * @param contentRows string array of rows, which will be written to file as separate lines.
     * @return true, if operation was successful, and false, if it wasn't.
     *
     * @throws IOException when File System has some problems with writing to target file.
     *
     * @see IOException
     * */
    public static boolean writeTextFile(String filePath, String[] contentRows) throws IOException {
        if (contentRows == null || contentRows.length < 1) {
            throw new NullPointerException("content is null");
        }

        if (!fileIsValidToWrite(filePath)) {
            return false;
        }

        BufferedWriter bufferedWriter =
                new BufferedWriter(new FileWriter(filePath));   // throws FileNotFoundException !

        for (String row: contentRows) {
            if (row == null) {
                continue;
            }
            bufferedWriter.write(row);
            bufferedWriter.newLine();
        }

        bufferedWriter.flush();
        bufferedWriter.close();
        return true;
    }

    /**
     * Private method, which checks if file could be used for reading.
     *
     * @param filePath path to target file.
     *
     * @return true, if file is valid, and false, if it is not.
     * */
    private static boolean fileIsValidToRead(String filePath) {
        File file = new File(filePath);
        return file.isFile() && file.exists();
    }

    /**
     * Private method, which checks if file could be used for writing.
     *
     * @param filePath path to target file.
     *
     * @return true, if file is valid, and false, if it is not.
     * */
    private static boolean fileIsValidToWrite(String filePath) {
        File file = new File(filePath);
        if (file.exists()) {
            return file.isFile();
        } else {
            return true;
        }
    }

}
