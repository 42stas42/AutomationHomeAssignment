package Utilities;

import InfraObjects.NewsObject;
import io.qameta.allure.Allure;
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
        Allure.step("Starting export of " + newsObjectsList.size() + " news articles.");

        // Sort: date ascending, then type alphabetically
        Allure.step("Sorting news articles by absolute date and then type.");
        newsObjectsList.sort(
                Comparator.comparing(NewsObject::getAbsoluteConvertedDate)
                        .thenComparing(NewsObject::getType, String.CASE_INSENSITIVE_ORDER)
        );

        // Target file path
        String targetFilePath = Paths.get("target", "latest_news.txt").toString();
        Allure.step("Target file path: " + targetFilePath);

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(targetFilePath))) {
            for (NewsObject news : newsObjectsList) {
                Allure.step("Writing article: \"" + news.getTitle() + "\" from " + news.getOrigin());
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
            Allure.step("Export completed successfully. File saved at: " + targetFilePath);
            System.out.println("Export completed: " + targetFilePath);
        } catch (IOException e) {
            Allure.step("Error writing news file: " + e.getMessage());
            System.err.println("Error writing news file: " + e.getMessage());
        }
    }
}
