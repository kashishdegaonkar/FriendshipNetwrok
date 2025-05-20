import java.util.*;

public class SocialNetworkGraph {
    private final Map<String, List<String>> network = new HashMap<>();
    private final Scanner scanner = new Scanner(System.in);

    // Add a user
    public void addUser(String user) {
        if (!network.containsKey(user)) {
            network.put(user, new ArrayList<>());
            System.out.println("User '" + user + "' added.");
        } else {
            System.out.println("User '" + user + "' already exists.");
        }
    }

    // Add a friendship (undirected)
    public void addFriendship(String user1, String user2) {
        if (!network.containsKey(user1) || !network.containsKey(user2)) {
            System.out.println("Both users must exist to create a friendship.");
            return;
        }

        if (!network.get(user1).contains(user2)) {
            network.get(user1).add(user2);
            network.get(user2).add(user1);
            System.out.println("Friendship created between " + user1 + " and " + user2 + ".");
        } else {
            System.out.println("They are already friends.");
        }
    }

    // Find shortest path using BFS
    public List<String> shortestPath(String start, String end) {
        if (!network.containsKey(start) || !network.containsKey(end)) {
            return Collections.emptyList();
        }

        Queue<List<String>> queue = new LinkedList<>();
        Set<String> visited = new HashSet<>();

        queue.offer(new ArrayList<>(List.of(start)));

        while (!queue.isEmpty()) {
            List<String> path = queue.poll();
            String lastNode = path.get(path.size() - 1);

            if (lastNode.equals(end)) {
                return path;
            }

            if (!visited.contains(lastNode)) {
                visited.add(lastNode);
                for (String neighbor : network.getOrDefault(lastNode, new ArrayList<>())) {
                    List<String> newPath = new ArrayList<>(path);
                    newPath.add(neighbor);
                    queue.offer(newPath);
                }
            }
        }

        return Collections.emptyList();
    }

    // Menu loop
    public void run() {
        while (true) {
            System.out.println("\n--- Social Network Graph ---");
            System.out.println("1. Add User");
            System.out.println("2. Add Friendship");
            System.out.println("3. Find Shortest Path");
            System.out.println("4. Show All Users");
            System.out.println("5. Exit");
            System.out.print("Choose an option: ");
            
            int choice;
            try {
                choice = Integer.parseInt(scanner.nextLine().trim());
            } catch (Exception e) {
                System.out.println("Invalid input. Please enter a number.");
                continue;
            }

            switch (choice) {
                case 1 -> {
                    System.out.print("Enter username: ");
                    String user = scanner.nextLine().trim();
                    addUser(user);
                }
                case 2 -> {
                    System.out.print("Enter first user's name: ");
                    String user1 = scanner.nextLine().trim();
                    System.out.print("Enter second user's name: ");
                    String user2 = scanner.nextLine().trim();
                    addFriendship(user1, user2);
                }
                case 3 -> {
                    System.out.print("Enter starting user: ");
                    String start = scanner.nextLine().trim();
                    System.out.print("Enter target user: ");
                    String end = scanner.nextLine().trim();
                    List<String> path = shortestPath(start, end);
                    if (path.isEmpty()) {
                        System.out.println("No connection found.");
                    } else {
                        System.out.println("Shortest Path: " + path);
                    }
                }
                case 4 -> {
                    System.out.println("All Users:");
                    for (String user : network.keySet()) {
                        System.out.println("- " + user + " (friends: " + network.get(user) + ")");
                    }
                }
                case 5 -> {
                    System.out.println("Goodbye!");
                    return;
                }
                default -> System.out.println("Invalid option. Try again.");
            }
        }
    }

    // Main
    public static void main(String[] args) {
        SocialNetworkGraph app = new SocialNetworkGraph();
        app.run();
    }
}
