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

public class AuthorList implements Serializable {

    private Map<Author, List<Novel>> authorNovelMap; // Map<Author, List<Novel>>

    public AuthorList() {
        this.authorNovelMap = new HashMap<>();
    }

    public void addNovel(Author author, Novel novel) {
        if (!authorNovelMap.containsKey(author)) {
            authorNovelMap.put(author, new ArrayList<>());
        }
        authorNovelMap.get(author).add(novel);
    }

    public Map<Author, List<Novel>> getAuthorNovelMap() {
        return authorNovelMap;
    }

    public AuthorList(Map<Author, List<Novel>> authorNovelMap) {
        this.authorNovelMap = authorNovelMap;
    }
   

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("AuthorList{");
        sb.append("authorNovelMap={");
        for (Map.Entry<Author, List<Novel>> entry : authorNovelMap.entrySet()) {
            Author author = entry.getKey();
            List<Novel> novels = entry.getValue();
            sb.append(author.getAuthorId()).append("=[");
            for (Novel novel : novels) {
                sb.append(novel.getNovelId()).append(", ");
            }
            if (!novels.isEmpty()) {
                sb.setLength(sb.length() - 2); // Remove the last comma and space
            }
            sb.append("], ");
        }
        if (!authorNovelMap.isEmpty()) {
            sb.setLength(sb.length() - 2); // Remove the last comma and space
        }
        sb.append("}}");
        return sb.toString();
    }

}
