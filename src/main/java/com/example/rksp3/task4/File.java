package com.example.rksp3.task4;

public class File {
    enum FileType {
        XML, JSON, XLS
    }

    private FileType type;
    private int size; // Размер файла

    public File(FileType type, int size) {
        this.type = type;
        this.size = size;
    }

    public FileType getType() {
        return type;
    }

    public int getSize() {
        return size;
    }

    @Override
    public String toString() {
        return "File{" +
                "type=" + type +
                ", size=" + size +
                '}';
    }
}

