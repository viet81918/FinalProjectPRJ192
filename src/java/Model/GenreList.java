/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GenreList implements Serializable {
    private Map<Genre, List<Novel>> genreNovelMap;

    public GenreList() {
        this.genreNovelMap = new HashMap<>();
    }

    public void addNovel(Genre genre, Novel novel) {
        if (!genreNovelMap.containsKey(genre)) {
            genreNovelMap.put(genre, new ArrayList<>());
        }
        genreNovelMap.get(genre).add(novel);
    }

    public Map<Genre, List<Novel>> getGenreNovelMap() {
        return genreNovelMap;
    }

    public GenreList(Map<Genre, List<Novel>> genreNovelMap) {
        this.genreNovelMap = genreNovelMap;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("GenreList{");
        sb.append("genreNovelMap={");
        for (Map.Entry<Genre, List<Novel>> entry : genreNovelMap.entrySet()) {
            Genre key = entry.getKey();
            List<Novel> novels = entry.getValue();
            sb.append(key.getTypeId()).append("=[");
            for (Novel novel : novels) {
                sb.append(novel.getNovelId()).append(", ");
            }
            if (!novels.isEmpty()) {
                sb.setLength(sb.length() - 2); // Remove the last comma and space
            }
            sb.append("], ");
        }
        if (!genreNovelMap.isEmpty()) {
            sb.setLength(sb.length() - 2); // Remove the last comma and space
        }
        sb.append("}}");
        return sb.toString();
    }
}
