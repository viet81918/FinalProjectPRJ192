/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Objects;

public class Author implements Serializable {
    private String authorId;
    private String authorName;
    private String authorDescription;

    public Author() {}

    public Author(String authorId, String authorName, String authorDescription) {
        this.authorId = authorId;
        this.authorName = authorName;
        this.authorDescription = authorDescription;
    }
    public Author(String authorName, String authorDescription, ArrayList<Author> authors ) {
        this.authorId = generateAuthorId(authorName,authors);
        this.authorName = authorName;
        this.authorDescription = authorDescription;
    }

    public String getAuthorId() {
        return authorId;
    }

    public void setAuthorId(String authorId) {
        this.authorId = authorId;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String name) {
        this.authorName = name;
    }

    public String getAuthorDescription() {
        return authorDescription;
    }

    public void setAuthorDescription(String authorDescription) {
        this.authorDescription = authorDescription;
    }
public static String generateAuthorId(String authorName, ArrayList<Author> authors) {
    // Get the first character of each word in the novel name
    StringBuilder novelIdBuilder = new StringBuilder();
    for (String word : authorName.split(" ")) {
        novelIdBuilder.append(word.charAt(0));
    }
    String potentialAuthorId = novelIdBuilder.toString().toUpperCase();

    // Check if the potential novel ID already exists in the list of novels
    if (!authorIdExists(potentialAuthorId, authors)) {
        return potentialAuthorId+"1"; // If not, return the potential novel ID
    }

    // If the potential novel ID already exists, add additional characters from the novel name
    int counter = 1;
    String uniqueNovelId = potentialAuthorId;
    while (authorIdExists(uniqueNovelId, authors)) {
           counter++;
        uniqueNovelId = potentialAuthorId + counter;
       
    }

    return uniqueNovelId;
}

    // Check if the novel ID already exists in the list of novels
    private static boolean authorIdExists(String authorId, ArrayList<Author> authors) {
        for (Author author : authors) {
            if (author.getAuthorId().equalsIgnoreCase(authorId)) {
                return true;
            }
        }
        return false;
    }
    @Override
public boolean equals(Object obj) {
    if (this == obj) {
        return true;
    }
    if (obj == null || getClass() != obj.getClass()) {
        return false;
    }
    Author other = (Author) obj;
    return Objects.equals(this.authorId, other.getAuthorId());
}

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 29 * hash + Objects.hashCode(this.authorId);
        hash = 29 * hash + Objects.hashCode(this.authorName);
        hash = 29 * hash + Objects.hashCode(this.authorDescription);
        return hash;
    }

 

    @Override
    public String toString() {
        return "Author{" + "authorId=" + authorId + ", name=" + authorName + ", description=" + authorDescription + '}';
    }

   
}
