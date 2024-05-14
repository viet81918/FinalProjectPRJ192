package Model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ChapterList implements Serializable {
    private Map<Novel, List<Chapter>> novelChapterMap; // Map<Novel, List<Chapter>>
    
    public ChapterList() {
        this.novelChapterMap = new HashMap<>();
    }
    public Chapter getChapter(Novel novel, int chapterNumber) {
    // Retrieve the list of chapters for the given novel
    List<Chapter> chapters = novelChapterMap.get(novel);
    
    if (chapters != null && chapterNumber >= 0 && chapterNumber < chapters.size()) {
        // Return the chapter at the specified index (chapter number)
        return chapters.get(chapterNumber);
    } else {
        // Return null if the novel or chapter number is invalid
        return null;
    }
}
    public void addChapter(Novel novel, Chapter chapter) {
        if (!novelChapterMap.containsKey(novel)) {
            novelChapterMap.put(novel, new ArrayList<>());
        }
        novelChapterMap.get(novel).add(chapter);
    }

    public Map<Novel, List<Chapter>> getChaptersForNovel() {
        return novelChapterMap;
    }
   public List<Chapter> getChaptersForNovel(Novel novel) {
    // Retrieve and return the list of chapters for the given novel
    return novelChapterMap.get(novel);
}

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("ChapterList{");
        sb.append("novelChapterMap={");
        for (Map.Entry<Novel, List<Chapter>> entry : novelChapterMap.entrySet()) {
            Novel novel = entry.getKey();
            List<Chapter> chapters = entry.getValue();
            sb.append(novel.getNovelName()).append("=[");
            for (Chapter chapter : chapters) {
                sb.append(chapter.getContent()).append(", ");
            }
            if (!chapters.isEmpty()) {
                sb.setLength(sb.length() - 2); // Remove the last comma and space
            }
            sb.append("], ");
        }
        if (!novelChapterMap.isEmpty()) {
            sb.setLength(sb.length() - 2); // Remove the last comma and space
        }
        sb.append("}}");
        return sb.toString();
    }
}
    