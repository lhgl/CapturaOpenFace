import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.Arrays;
import java.util.List;

public class FileServiceImpl implements FileService {


    @Override
    public void moveFiles(List<File> listaArquivos, String path) {
        listaArquivos.forEach(arquivo -> {
            this.moveFile(arquivo, path);
        });
    }

    @Override
    public void moveFile(File arquivo, String path) {
        String fileName = arquivo.getPath().toString().replace(path, "");
        Path from = FileSystems.getDefault().getPath(path + fileName);
        Path target = FileSystems.getDefault().getPath(path + "\\processados" + fileName);

        try {
            Files.move(from, target, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<File> getFiles(String path, String ext) {
        File dir = new File(path);
        List<File> files = Arrays.asList(dir.listFiles((d, name) -> name.endsWith(ext)));
        return files;
    }

}
