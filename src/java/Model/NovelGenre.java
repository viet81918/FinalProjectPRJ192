package Model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NovelGenre {
    private Map<Novel, List<Genre>> novelGenreMap; // Map<Novel, List<Genre>>

    public NovelGenre() {
        this.novelGenreMap = new HashMap<>();
    }

    public void addGenre(Novel novel, Genre genre) {
        if (!novelGenreMap.containsKey(novel)) {
            novelGenreMap.put(novel, new ArrayList<>());
        }
        novelGenreMap.get(novel).add(genre);
    }

    public Map<Novel, List<Genre>> getNovelGenreMap() {
        return novelGenreMap;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("GenreList{\n");
        for (Map.Entry<Novel, List<Genre>> entry : novelGenreMap.entrySet()) {
            Novel novel = entry.getKey();
            List<Genre> genres = entry.getValue();
            sb.append("\t").append("Novel ID: ").append(novel.getNovelId()).append("\n");
            sb.append("\t").append("Genres: ");
            if (genres.isEmpty()) {
                sb.append("No genres\n");
            } else {
                for (Genre genre : genres) {
                    sb.append(genre.getTypeId()).append(", ");
                }
                sb.setLength(sb.length() - 2); // Remove the last comma and space
                sb.append("\n");
            }
        }
        sb.append("}");
        return sb.toString();
    }
}
