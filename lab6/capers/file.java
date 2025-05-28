package capers;

import java.io.File;
import java.io.IOException;

public class file {
    public static void main(String[] args) {
        File f = new File("dummy.txt");
        try {
            f.createNewFile();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Utils.writeContents(f,"hello, world");
    }
}
