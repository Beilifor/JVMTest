package org.itstack.jvm.classpath;

import java.io.File;
import java.io.IOException;

import org.itstack.jvm.classpath.impl.CompositeEntry;
import org.itstack.jvm.classpath.impl.DirEntry;
import org.itstack.jvm.classpath.impl.WildcardEntry;
import org.itstack.jvm.classpath.impl.ZipEntry;

public interface Entry {
    
    byte[] readClass(String className) throws IOException;

    static Entry create(String path) {

        //File.pathSeparator；路径分隔符(win\linux)
        if (path.contains(File.pathSeparator)) {
            return new CompositeEntry(path);
        }

        if (path.endsWith("*")) {
            return new WildcardEntry(path);
        }

        if (path.endsWith(".jar") || path.endsWith(".JAR") ||
                path.endsWith(".zip") || path.endsWith(".ZIP")) {
            return new ZipEntry(path);
        }

        return new DirEntry(path);
    }

}
