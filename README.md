# FriendshipNetwrok
Social Network Graph (Java Console App)

This is a console-based Java application that simulates a **social network** using a **graph** data structure. Users can add people, create friendships, and find the shortest connection (degree of separation) between two people using **Breadth-First Search (BFS)**.

---

## 🚀 Features

- ✅ Add users to the social network
- ✅ Create mutual friendships
- ✅ Find the shortest connection path between two users
- ✅ View the entire user base with their friendships
- ✅ Simple console menu using `Scanner`

---

## 🧠 Data Structures Used

- `HashMap<String, List<String>>` – represents the graph (user → list of friends)
- `Queue` and `Set` – used for BFS traversal

---

## 💻 How to Run

### 🛠 Requirements
- Java 8 or higher installed

### 🧾 Compile and Run

```bash
javac SocialNetworkGraph.java
java SocialNetworkGraph
