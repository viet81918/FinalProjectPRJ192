/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author LENOVO
 */
public class Chapter implements Serializable {
    private String chapterId;
    private String linkContent;
    private String content;
    private Date creationDate;
    // Constructors, getters, and setters
    public Chapter() {
    }

    public Chapter(String chapterId, String linkContent, Date creationDate ) {
        this.chapterId = chapterId;
        this.linkContent = linkContent;
        this.content = writeToTextFile(linkContent);
        this.creationDate = creationDate;
    }

    public String getChapterId() {
        return chapterId;
    }

    public void setChapterId(String chapterId) {
        this.chapterId = chapterId;
    }

    public String getLinkContent() {
        return linkContent;
    }
  public String getChapterName() {
    String commonPath = "E:\\FPT UNI\\HoaiNTT40-SP24-PRJ301-Web-based Java Applications\\Final_Project_PRJ301\\web\\Novel\\";
    String chapterPath = linkContent.substring(commonPath.length());
    return chapterPath;
}

    
    public void setLinkContent(String linkContent) {
        this.linkContent = linkContent;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
 public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }
    @Override
    public String toString() {
        return "Chapter{" +
                "chapterId='" + chapterId + '\'' +
                ", linkContent='" + linkContent + '\'' +
                ", content='" + content + '\'' +
                '}';
    }

    // Method to write chapter content to a text file
   private String writeToTextFile(String filePath) {
    try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
        StringBuilder contentBuilder = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            contentBuilder.append(line).append("\n");
        }
        String content = contentBuilder.toString().trim();
        System.out.println("Chapter content has been read from " + filePath);
        return content;
    } catch (IOException e) {
        System.err.println("Error reading from file: " + e.getMessage());
        return null;
    }
}

}