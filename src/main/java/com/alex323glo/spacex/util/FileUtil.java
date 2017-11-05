package com.alex323glo.spacex.util;

// TODO add doc.

import java.io.*;

/**
 * Created by alex323glo on 05.11.17.
 */
public class FileUtil {

    public static final int DEFAULT_BYTE_FILE_SIZE = 1024 * 1024 * 1024;

    public static byte[] readByteFile(String filePath, int numOfBytes) throws IOException {
//        if (!fileIsValidToRead(filePath)) {
//            return null;
//        }

        FileInputStream fileInputStream = new FileInputStream(filePath);    // throws FileNotFoundException !

        byte[] bufferBytes = new byte[numOfBytes];
        fileInputStream.read(bufferBytes);          // throws IOException !

        return bufferBytes;
    }

    public static byte[] readByteFile(String filePath) throws IOException {
        return readByteFile(filePath, DEFAULT_BYTE_FILE_SIZE);
    }

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

    private static boolean fileIsValidToRead(String filePath) {
        File file = new File(filePath);
        return file.isFile() && file.exists();
    }

    private static boolean fileIsValidToWrite(String filePath) {
        File file = new File(filePath);
        if (file.exists()) {
            return file.isFile();
        } else {
            return true;
        }
    }

}
