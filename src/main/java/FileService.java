import java.io.File;
import java.util.List;

public interface FileService {

    void moveFiles(List<File> listaArquivos, String path);

    void moveFile(File arquivo, String path);

    List<File> getFiles(String path, String ext);

}
