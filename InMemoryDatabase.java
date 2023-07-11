package ir.utux;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
import java.util.regex.Pattern;

public class InMemoryDatabase {
    private static final String DEFAULT_DB_NAME = "default";
    private static final int DEFAULT_LIMIT = 10;
    private static final int DEFAULT_PAGE = 1;

    private static final Map<String, Map<String, String>> databases = new HashMap<>();
    private static String currentDbName = DEFAULT_DB_NAME;

    public static void main(String[] args) {
        databases.put(DEFAULT_DB_NAME, new HashMap<>());
        Scanner scanner = new Scanner(System.in);

        while (true) {
            String command = scanner.nextLine();
            String[] parts = command.split(" ");
            String action = parts[0];

            switch (action) {
                case "set" -> set(parts[1], parts[2]);
                case "get" -> System.out.println(get(parts[1]));
                case "del" -> System.out.println(del(parts[1]));
                case "keys" -> {
                    int limit = DEFAULT_LIMIT;
                    int page = DEFAULT_PAGE;
                    if (parts.length > 2) {
                        for (int i = 2; i < parts.length; i += 2) {
                            if (parts[i].equals("limit")) {
                                limit = Integer.parseInt(parts[i + 1]);
                            } else if (parts[i].equals("page")) {
                                page = Integer.parseInt(parts[i + 1]);
                            }
                        }
                    }
                    System.out.println(keys(parts[1], limit, page));
                }
                case "use" -> use(parts[1]);
                case "list" -> System.out.println(list());
                case "import" -> importDb(parts[1], parts[2]);
                case "export" -> exportDb(parts[1], parts[2]);
                case "exit" -> System.exit(0);
                default -> System.out.println("Invalid command");
            }
        }
    }

    private static void set(String key, String value) {
        databases.get(currentDbName).put(key, value);
        System.out.println("ok");
    }

    private static String get(String key) {
        return databases.get(currentDbName).getOrDefault(key, "null");
    }

    private static boolean del(String key) {
        return databases.get(currentDbName).remove(key) != null;
    }

    private static List<String> keys(String regex, int limit, int page) {
        Pattern pattern = Pattern.compile(regex);
        List<String> matchedKeys = new ArrayList<>();
        for (String key : databases.get(currentDbName).keySet()) {
            if (pattern.matcher(key).matches()) {
                matchedKeys.add(key);
            }
        }
        int start = Math.min((page - 1) * limit, matchedKeys.size());
        int end = Math.min(start + limit, matchedKeys.size());
        return matchedKeys.subList(start, end);
    }

    private static void use(String dbName) {
        databases.putIfAbsent(dbName, new HashMap<>());
        currentDbName = dbName;
        System.out.println("db switched");
    }

    private static Set<String> list() {
        return databases.keySet();
    }

    private static void importDb(String path, String dbName) {
        try (FileReader reader = new FileReader(path)) {
            Map<String, String> db = new Gson().fromJson(reader, new TypeToken<Map<String, String>>() {
            }.getType());
            databases.put(dbName, db);
            System.out.println("OK");
        } catch (IOException e) {
            System.out.println("Error reading file");
        }
    }

    private static void exportDb(String path, String dbName) {
        try (FileWriter writer = new FileWriter(path)) {
            writer.write(new Gson().toJson(databases.get(dbName)));
            System.out.println("OK");
        } catch (IOException e) {
            System.out.println("Error writing file");
        }
    }


}


