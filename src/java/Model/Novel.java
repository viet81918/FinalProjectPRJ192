/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author LENOVO
 */
public class Novel implements Serializable {

    private String novelId;
    private String novelLanguage;
    private String novelName;
    private int novelLength;
    private int numberOfChapters;
    private float profit;
    private float licenseProfit;
    private int numberRead;
    private int numberWatch;
    private int numberFollow;
    private int rating;
    private Date publicationYear;
    private String publicationStatus;
    private String description;

    // Constructors
    public Novel() {
    }

    public Novel(String novelId, String novelLanguage, String novelName, int novelLength, int numberOfChapters,
            float profit, float licenseProfit, int numberRead, int numberWatch, int numberFollow, int rating,
            Date publicationYear, String publicationStatus, String description) {
        this.novelId = novelId;
        this.novelLanguage = novelLanguage;
        this.novelName = novelName;
        this.novelLength = novelLength;
        this.numberOfChapters = numberOfChapters;
        this.profit = profit;
        this.licenseProfit = licenseProfit;
        this.numberRead = numberRead;
        this.numberWatch = numberWatch;
        this.numberFollow = numberFollow;
        this.rating = rating;
        this.publicationYear = publicationYear;
        this.publicationStatus = publicationStatus;
        this.description = description;
    }
   public Novel(String novelLanguage, String novelName
          , float licenseProfit, 
              Date publicationYear, String publicationStatus, String description,
             ArrayList<Novel> novels) {
    this.novelId = generateNovelId(novelName, novels); // Generate unique novel ID
    this.novelLanguage = novelLanguage;
    this.novelName = novelName;
    this.novelLength = 0;
    this.numberOfChapters = 0;
    this.profit = 0;
    this.licenseProfit = licenseProfit;
    this.numberRead = 0;
    this.numberWatch = 0;
    this.numberFollow = 0;
    this.rating = 0;
    this.publicationYear = publicationYear;
    this.publicationStatus = publicationStatus;
    this.description = description;
}

    public String getNovelId() {
        return novelId;
    }

    public void setNovelId(String novelId) {
        this.novelId = novelId;
    }

    public String getNovelLanguage() {
        return novelLanguage;
    }

    public void setNovelLanguage(String novelLanguage) {
        this.novelLanguage = novelLanguage;
    }

    public String getNovelName() {
        return novelName;
    }

    public void setNovelName(String novelName) {
        this.novelName = novelName;
    }

    public int getNovelLength() {
        return novelLength;
    }

    public void setNovelLength(int novelLength) {
        this.novelLength = novelLength;
    }

    public int getNumberOfChapters() {
        return numberOfChapters;
    }

    public void setNumberOfChapters(int numberOfChapters) {
        this.numberOfChapters = numberOfChapters;
    }

    public float getProfit() {
        return profit;
    }

    public void setProfit(float profit) {
        this.profit = profit;
    }

    public float getLicenseProfit() {
        return licenseProfit;
    }

    public void setLicenseProfit(float licenseProfit) {
        this.licenseProfit = licenseProfit;
    }

    public int getNumberRead() {
        return numberRead;
    }

    public void setNumberRead(int numberRead) {
        this.numberRead = numberRead;
    }

    public int getNumberWatch() {
        return numberWatch;
    }

    public void setNumberWatch(int numberWatch) {
        this.numberWatch = numberWatch;
    }

    public int getNumberFollow() {
        return numberFollow;
    }

    public void setNumberFollow(int numberFollow) {
        this.numberFollow = numberFollow;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public Date getPublicationYear() {
        return publicationYear;
    }

    public void setPublicationYear(Date publicationYear) {
        this.publicationYear = publicationYear;
    }

    public String getPublicationStatus() {
        return publicationStatus;
    }

    public void setPublicationStatus(String publicationStatus) {
        this.publicationStatus = publicationStatus;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
 public static String generateNovelId(String novelName, ArrayList<Novel> novels) {
    // Get the first character of each word in the novel name
    StringBuilder novelIdBuilder = new StringBuilder();
    for (String word : novelName.split(" ")) {
        novelIdBuilder.append(word.charAt(0));
    }
     int counter = 1;
    String potentialNovelId = novelIdBuilder.toString().toUpperCase();
    // Check if the potential novel ID already exists in the list of novels
    if (!novelNameExists(potentialNovelId, novels)) {
        return potentialNovelId+ counter; // If not, return the potential novel ID
    }else{

    // If the potential novel ID already exists, add additional characters from the novel name
   
    String uniqueNovelId = potentialNovelId;
    while (novelNameExists(uniqueNovelId, novels)) {
           counter++;
        uniqueNovelId = potentialNovelId  + counter;
       
    }

    return uniqueNovelId;}
}

    // Check if the novel ID already exists in the list of novels
    private static boolean novelNameExists(String novelName, ArrayList<Novel> novels) {
        for (Novel novel : novels) {
            if (novel.getNovelName().equalsIgnoreCase(novelName)) {
                return true;
            }
        }
        return false;
    }
    @Override
    public String toString() {
        return "Novel{" + "novelId=" + novelId + ", novelLanguage=" + novelLanguage + ", novelName=" + novelName + ", novelLength=" + novelLength + ", numberOfChapters=" + numberOfChapters + ", profit=" + profit + ", licenseProfit=" + licenseProfit + ", numberRead=" + numberRead + ", numberWatch=" + numberWatch + ", numberFollow=" + numberFollow + ", rating=" + rating + ", publicationYear=" + publicationYear + ", publicationStatus=" + publicationStatus + ", description=" + description + '}';
    }
    
}
