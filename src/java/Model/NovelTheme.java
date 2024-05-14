/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NovelTheme {
    private Map<Novel, List<Theme>> novelThemeMap; // Map<Novel, List<Theme>>

    public NovelTheme() {
        this.novelThemeMap = new HashMap<>();
    }

    public void addTheme(Novel novel, Theme theme) {
        if (!novelThemeMap.containsKey(novel)) {
            novelThemeMap.put(novel, new ArrayList<>());
        }
        novelThemeMap.get(novel).add(theme);
    }

    public Map<Novel, List<Theme>> getNovelThemeMap() {
        return novelThemeMap;
    }
 @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("ThemeList{\n");
        for (Map.Entry<Novel, List<Theme>> entry : novelThemeMap.entrySet()) {
            Novel novel = entry.getKey();
            List<Theme> themes = entry.getValue();
            sb.append("\t").append("Novel: ").append(novel.getNovelId()).append("\n");
            sb.append("\t").append("Themes: ");
            if (themes.isEmpty()) {
                sb.append("No themes\n");
            } else {
                sb.append(themes).append("\n");
            }
        }
        sb.append("}");
        return sb.toString();
    }
}