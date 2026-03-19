import java.io.*;
import java.util.*;

public class Main {

    // Predefined skill list
    static List<String> skills = Arrays.asList(
            "java", "spring", "python", "sql",
            "machine learning", "html", "css",
            "javascript", "react", "node"
    );

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        System.out.println("===== AI Resume Analyzer =====");
        System.out.print("Enter resume file name (example: resume.txt): ");

        String fileName = scanner.nextLine();

        String resumeText = readFile(fileName);

        if (resumeText == null) {
            System.out.println("File not found!");
            return;
        }

        resumeText = resumeText.toLowerCase();

        List<String> detectedSkills = detectSkills(resumeText);
        int score = calculateScore(resumeText, detectedSkills);
        String jobRole = recommendJob(detectedSkills);

        System.out.println("\n===== ANALYSIS RESULT =====");
        System.out.println("Detected Skills: " + detectedSkills);
        System.out.println("Resume Score: " + score + "/100");
        System.out.println("Recommended Job Role: " + jobRole);

        giveSuggestions(detectedSkills);

        scanner.close();
    }

    // Read resume text file
    public static String readFile(String fileName) {
        StringBuilder content = new StringBuilder();
        try {
            BufferedReader br = new BufferedReader(new FileReader(fileName));
            String line;
            while ((line = br.readLine()) != null) {
                content.append(line).append(" ");
            }
            br.close();
        } catch (IOException e) {
            return null;
        }
        return content.toString();
    }

    // Detect skills
    public static List<String> detectSkills(String text) {
        List<String> foundSkills = new ArrayList<>();

        for (String skill : skills) {
            if (text.contains(skill)) {
                foundSkills.add(skill);
            }
        }
        return foundSkills;
    }

    // Calculate resume score
    public static int calculateScore(String text, List<String> detectedSkills) {

        int score = 0;

        // +10 per skill
        score += detectedSkills.size() * 10;

        if (text.contains("experience"))
            score += 20;

        if (text.contains("project"))
            score += 15;

        if (text.contains("certification"))
            score += 10;

        if (score > 100)
            score = 100;

        return score;
    }

    // Recommend job role
    public static String recommendJob(List<String> skills) {

        if (skills.contains("java") && skills.contains("spring"))
            return "Backend Developer";

        if (skills.contains("python") && skills.contains("machine learning"))
            return "Data Analyst / ML Engineer";

        if (skills.contains("html") && skills.contains("css") && skills.contains("javascript"))
            return "Frontend Developer";

        if (skills.contains("react") || skills.contains("node"))
            return "Full Stack Developer";

        return "General Software Developer";
    }

    // Suggestions
    public static void giveSuggestions(List<String> skills) {

        System.out.println("\n===== SUGGESTIONS =====");

        if (!skills.contains("sql"))
            System.out.println("- Consider learning SQL.");

        if (!skills.contains("project"))
            System.out.println("- Add more project details.");

        if (!skills.contains("certification"))
            System.out.println("- Add certifications to improve score.");

        if (skills.size() < 3)
            System.out.println("- Add more technical skills.");
    }
}