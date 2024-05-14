/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Objects;

/**
 *
 * @author LENOVO
 */
public class Genre  implements Serializable {
    private String typeId;
    private String typeOfGenre;

    public Genre() {
    }

    public Genre(String typeId, String typeOfGenre) {
        this.typeId = typeId;
        this.typeOfGenre = typeOfGenre;
    }
    public Genre( String typeOfGenre , ArrayList<Genre> genres) {
        this.typeId = generateGenreId(typeOfGenre,genres);
        this.typeOfGenre = typeOfGenre;
    }
    public String getTypeId() {
        return typeId;
    }

    public void setTypeId(String typeId) {
        this.typeId = typeId;
    }

    public String getTypeOfGenre() {
        return typeOfGenre;
    }

    public void setTypeOfGenre(String typeOfGenre) {
        this.typeOfGenre = typeOfGenre;
    }
    public static String generateGenreId(String genreName, ArrayList<Genre> genres) {
    // Get the first character of each word in the genre name
    String potentialGenreId = "NG"+genres.size()+1;

    // Check if the potential genre ID already exists in the list of genres
    if (!genreIdExists(genreName, genres)) {
        return potentialGenreId + genres.size()+1; // If not, return the potential genre ID
    }
    else {
        return null;
    }
}

// Check if the genre ID already exists in the list of genres
private static boolean genreIdExists(String genreName, ArrayList<Genre> genres) {
    for (Genre genre : genres) {
        if (genre.getTypeOfGenre().equalsIgnoreCase(genreName)) {
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
    Genre other = (Genre) obj;
    return Objects.equals(this.typeId, other.getTypeId());
}

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 41 * hash + Objects.hashCode(this.typeId);
        hash = 41 * hash + Objects.hashCode(this.typeOfGenre);
        return hash;
    }


// Implement similar methods for Format and Theme

    @Override
    public String toString() {
        return "Genre{" + "typeId=" + typeId + ", typeOfGenre=" + typeOfGenre + '}';
    }
    
}
