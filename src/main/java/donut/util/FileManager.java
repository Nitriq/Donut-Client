package donut.util;

import com.google.gson.Gson;

import java.io.*;
import java.nio.charset.StandardCharsets;

public class FileManager {

    private static Gson gson = new Gson();

    private static File Root_Dir = new File("Donut_Client");
    private static File Mods_Dir = new File(Root_Dir, "Mods");

    public static void init(){
        if(!Root_Dir.exists()){
            Root_Dir.mkdirs();
        }
        if(!Mods_Dir.exists()){
            Mods_Dir.mkdirs();
        }
    }

    public static Gson getGson(){
        return gson;
    }

    public static File getModsDirectory(){
        return Mods_Dir;
    }

    public static boolean writeJsonToFile(File file, Object obj){

            try {
                if(!file.exists()) {
                    file.createNewFile();
                }

                FileOutputStream outputStream = new FileOutputStream(file);
                outputStream.write(gson.toJson(obj).getBytes());
                outputStream.flush();
                outputStream.close();
                return true;
            }
            catch (IOException e){
                e.printStackTrace();
                return false;
            }

    }

    public static <T extends Object> T readfromJson(File file, Class<T> c){
        try{

            FileInputStream inputStream = new FileInputStream(file);
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

            StringBuilder stringBuilder = new StringBuilder();
            String line;
            while((line = bufferedReader.readLine()) != null){
                stringBuilder.append(line);
            }
            bufferedReader.close();
            inputStreamReader.close();
            inputStream.close();

            return gson.fromJson(stringBuilder.toString(), c);

        }catch (IOException e){
            e.printStackTrace();
            return null;
        }
    }

}
