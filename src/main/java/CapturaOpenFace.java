import java.io.File;
import java.io.PrintWriter;
import java.util.List;

public class CapturaOpenFace {

    public final static String videos_path = "D:\\Git\\Download_Youtube_VID\\videos";

    public final static String openFace_path = "D:\\Git\\TadasBaltrusaitis\\OpenFace_2.0.5_win_x64";


    public static void main(String[] args) {

        FileServiceImpl fs = new FileServiceImpl();
        List<File> files = fs.getFiles(videos_path, ".mp4");
        files.forEach(video -> {
            getdData(getParams(video.getName()));
            fs.moveFile(video, videos_path);
            List<File> filesTxt = fs.getFiles(videos_path, "_of_details.txt");
            fs.moveFiles(filesTxt, videos_path);
        });


    }

    private static String getParams(String videoName) {
        return "" + openFace_path +
                //"\\FaceLandmarkVidMulti.exe" +
                "\\FeatureExtraction.exe" +
                " -f \"" + videos_path + "\\" + videoName + "\"" +
                " -out_dir " + videos_path + //salva no mesmo path para processar postumamente
                " -aus" +
                //" -verbose" +
                //" -vis-aus "+
                //" -tracked "+
                " ";

    }

    private static void getdData(String params) {

        String[] command =
                {
                        "cmd"
                };

        Process p;
        try {
            p = Runtime.getRuntime().exec(command);
            new Thread(new SyncBuffer(p.getErrorStream(), System.err)).start();
            new Thread(new SyncBuffer(p.getInputStream(), System.out)).start();
            PrintWriter stdin = new PrintWriter(p.getOutputStream());
            stdin.println(params);
            stdin.close();
            p.waitFor();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
