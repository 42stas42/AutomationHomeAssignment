package Utilities;

import InfraObjects.NewsObject;
import io.qameta.allure.Step;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Paths;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;
import java.util.List;

public class NewsExporter {

    @Step("Export News Articles to file")
    public static void exportNews(List<NewsObject> newsObjectsList) {
        // Sort: date ascending, then type alphabetically
        newsObjectsList.sort(
            Comparator.comparing(NewsObject::getAbsoluteConvertedDate)
                      .thenComparing(NewsObject::getType, String.CASE_INSENSITIVE_ORDER)
        );

        // Target file path
        String targetFilePath = Paths.get("target", "latest_news.txt").toString();

        // Date format pattern
        //DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(targetFilePath))) {
            for (NewsObject news : newsObjectsList) {
                writer.write("Origin: " + news.getOrigin());
                writer.newLine();
                writer.write("Date: " + news.getDate());
                writer.newLine();
                writer.write("Type: " + news.getType());
                writer.newLine();
                writer.write("Title: " + news.getTitle());
                writer.newLine();
                writer.write("URL: " + news.getNewsUrl());
                writer.newLine();
                writer.newLine(); // Blank line between news entries
            }
            System.out.println("Export completed: " + targetFilePath);
        } catch (IOException e) {
            System.err.println("Error writing news file: " + e.getMessage());
        }
    }
}
