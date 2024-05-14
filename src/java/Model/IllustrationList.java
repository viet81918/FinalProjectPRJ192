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

public class IllustrationList implements Serializable {
    private Map<Novel, List<Illustration>> novelIllustrationMap;

    public IllustrationList() {
        this.novelIllustrationMap = new HashMap<>();
    }

    public void addIllustration(Novel novel, Illustration illustration) {
        if (!novelIllustrationMap.containsKey(novel)) {
            novelIllustrationMap.put(novel, new ArrayList<>());
        }
        novelIllustrationMap.get(novel).add(illustration);
    }

    public  Map<Novel, List<Illustration>> getIllustrationsForNovel( ) {
        return  novelIllustrationMap ;
    }
     public List<Illustration> getIllustrationsForNovel(Novel novel) {
    // Retrieve and return the list of chapters for the given novel
    return novelIllustrationMap.get(novel);
}
 public int getNumberOfIllustrationsForNovel(Novel novel) {
        List<Illustration> illustrations = novelIllustrationMap.get(novel);
        return illustrations != null ? illustrations.size() : 0;
    }
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("IllustrationList{\n");
        for (Map.Entry<Novel, List<Illustration>> entry : novelIllustrationMap.entrySet()) {
            Novel novel = entry.getKey();
            List<Illustration> illustrations = entry.getValue();
            sb.append("\t").append("Novel: ").append(novel.getNovelName()).append("\n");
            sb.append("\t").append("Illustrations: ").append(illustrations).append("\n");
        }
        sb.append("}");
        return sb.toString();
    }
}