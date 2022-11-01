package mds.streamingserver.model;

import java.io.File;

public class Movie
{
    File file;
    String image_name;
    String file_name;

    Movie(File file_1, String image_name_1, String file_name_1)
    {
        file=file_1 = null;
        image_name=image_name_1 = null;
        file_name=file_name_1 = null;
    }

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }

    public String getImage_name() {
        return image_name;
    }

    public void setImage_name(String image_name) {
        this.image_name = image_name;
    }

    public String getFile_name() {
        return file_name;
    }

    public void setFile_name(String file_name) {
        this.file_name = file_name;
    }
}
