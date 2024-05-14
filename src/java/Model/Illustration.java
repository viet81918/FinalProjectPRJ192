/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import java.io.Serializable;

/**
 *
 * @author LENOVO
 */
public class Illustration implements Serializable {
    private String illustrationId;
    private String illutrastionLink;

    public Illustration() {
    }

    public Illustration(String illustrationId, String illutrastionLink) {
        this.illustrationId = illustrationId;
        this.illutrastionLink = illutrastionLink;
    }

    public String getIllustrationId() {
        return illustrationId;
    }

    public void setIllustrationId(String illustrationId) {
        this.illustrationId = illustrationId;
    }

    public String getIllutrastionLink() {
        return illutrastionLink;
    }
  public String getIlluName() {
    String commonPath = "E:\\FPT UNI\\HoaiNTT40-SP24-PRJ301-Web-based Java Applications\\Final_Project_PRJ301\\web\\Illustration\\";
    String chapterPath = illutrastionLink.substring(commonPath.length());
    return chapterPath;
}

    public void setIllutrastionLink(String illutrastionLink) {
        this.illutrastionLink = illutrastionLink;
    }

    @Override
    public String toString() {
        return "Illustration{" + "illustrationId=" + illustrationId + ", illutrastionLink=" + illutrastionLink + '}';
    }

    
    
    
}
