package com.hakim.datauploder.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.logging.Level;

public final class FileUtility {
    private FileUtility() {

    }

    public static void createFile(String path) throws IOException {
        Files.createFile(Path.of(path));
    }

    public static void deleteFile(String path) throws IOException {
        Files.delete(Path.of(path));
    }

    public static void createDirectory(String path) throws IOException {
        Files.createDirectories(Path.of(path).getParent());
    }

    public static void copy(String from, String to) throws IOException {

        Path fromPath = Path.of(from);
        if (!Files.exists(fromPath)) {
            throw new FileNotFoundException();
        }

        Path pathTo = Path.of(to);
        if (!Files.exists(pathTo)) {
            Files.createFile(pathTo);
        }

        Files.copy(fromPath, pathTo, StandardCopyOption.REPLACE_EXISTING);
    }

    public static void fastCopy(String from, String to) throws IOException {
        if (!Files.exists(Path.of(from))) {
            throw new FileNotFoundException();
        }

        Path pathTo = Path.of(to);
        if (!Files.exists(pathTo)) {
            Files.createFile(pathTo);
        }

        try (FileChannel sourceChannel = new FileInputStream(from).getChannel();
             FileChannel sinkResource = new FileOutputStream(to).getChannel()) {

            sourceChannel.transferTo(0, sourceChannel.size(), sinkResource);
        } catch (Exception e) {
            DataUploaderLogger.log(Level.INFO, e.getMessage());
        }
    }

    public static String read(String fullPath) throws IOException {
        return Files.readString(Path.of(fullPath));
    }

    public static void write(String file, byte[] bytes) throws IOException {

        Path path = Path.of(file);
        if (!Files.exists(path)) {
            Files.createFile(path);
        }

        try (FileOutputStream fio = new FileOutputStream(file)) {
            fio.write(bytes);
            fio.flush();
        } catch (Exception ex) {
            DataUploaderLogger.log(Level.INFO, ex.getMessage());
        }
    }

    public static void write(String file, String data) throws IOException {

        Path path = Path.of(file);
        if (!Files.exists(path)) {
            Files.createFile(path);
        }
        Files.write(path, data.getBytes());
    }

    public static void fastWrite(String file, String data) throws IOException {

        Path path = Path.of(file);
        if (!Files.exists(path)) {
            Files.createFile(path);
        }

        try (FileChannel channel = new FileOutputStream(file).getChannel()) {
            ByteBuffer buffer = ByteBuffer.wrap(data.getBytes(StandardCharsets.UTF_8));

            channel.write(buffer);
        } catch (Exception ex) {
            DataUploaderLogger.log(Level.INFO, ex.getMessage());
        }
    }
}
