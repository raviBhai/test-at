package com.fileSystem;

import java.util.ArrayList;
import java.util.List;

public class FileSystem {

    Path root = null;

    public FileSystem() {
        root = new Path("/");
    }

    public boolean createPath(String path, int value) {

        if (path.startsWith("/")) {
            path = path.substring(1);
        }
        Path current = root;
        String[] childPaths = path.split("/");
        for (int i = 0; i < childPaths.length; i++) {

            List<Path> paths = current.paths;
            for (Path eachPath : paths) {
                if (eachPath.name.equals(childPaths[i])) {
                    current = eachPath;
                    break;
                }
            }
            if (!childPaths[i].equals(current.name)) {
                if (i != childPaths.length - 1) {
                    return false;
                } else {
                    Path newPath = new Path(childPaths[i]);
                    current.addChildPath(newPath);
                    current = newPath;
                }
            } else {
                if (i == childPaths.length - 1) {   //existing path
                    return false;
                }
            }

        }
        current.value = value;
        return true;

    }

    public int get(String path) {
        if (path.startsWith("/")) {
            path = path.substring(1);
        }
        Path current = root;
        String[] childPaths = path.split("/");
        for (String childPath : childPaths) {
            List<Path> paths = current.paths;
            for (Path eachPath : paths) {
                if (eachPath.name.equals(childPath)) {
                    current = eachPath;
                    break;
                }
            }
            if (!current.name.equals(childPath)) {
                return -1;
            }
        }
        return current.value;
    }
}

class Path {
    String name;
    List<Path> paths = new ArrayList<>();
    int value = -1;

    Path(String name) {
        this.name = name;
    }

    void addChildPath(Path path) {
        this.paths.add(path);
    }
}

/**
 * Your FileSystem object will be instantiated and called as such:
 * FileSystem obj = new FileSystem();
 * boolean param_1 = obj.createPath(path,value);
 * int param_2 = obj.get(path);
 */
class Client {
    public static void main(String[] args) {
        FileSystem obj = new FileSystem();
        obj.createPath("/a", 1);
        System.out.println(obj.get("/a"));

    }
}