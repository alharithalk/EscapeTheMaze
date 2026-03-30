import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Stack;

public class Main {

            static class Position {
                int row;
                int col;

                Position(int row, int col) {
                    this.row = row;
                    this.col = col;
                }
            }

            public static void main(String[] args) {
                if (args.length != 1) {
                    System.out.println("Usage: java Main <maze.txt>");
                    return;
                }

                try {
                    // 1) قراءة الملف
                    List<String> lines = Files.readAllLines(Paths.get(args[0]));

                    if (lines.isEmpty()) {
                        System.out.println("Error: File is empty.");
                        return;
                    }

                    int rows = lines.size();
                    int cols = lines.get(0).length();

                    // 2) إنشاء المتاهة
                    char[][] maze = new char[rows][cols];

                    // لتخزين البداية
                    Position start = null;

                    // 3) تعبئة المصفوفة والبحث عن @
                    for (int i = 0; i < rows; i++) {
                        if (lines.get(i).length() != cols) {
                            System.out.println("Error: Maze is malformed.");
                            return;
                        }

                        for (int j = 0; j < cols; j++) {
                            maze[i][j] = lines.get(i).charAt(j);

                            if (maze[i][j] == '@') {
                                start = new Position(i, j);
                            }
                        }
                    }

                    if (start == null) {
                        System.out.println("Error: Start point '@' not found.");
                        return;
                    }

                    // 4) visited
                    boolean[][] visited = new boolean[rows][cols];

                    // 5) stack
                    Stack<Position> stack = new Stack<>();
                    stack.push(start);
                    visited[start.row][start.col] = true;

                    // اتجاهات الحركة: أعلى، أسفل، يسار، يمين
                    int[] dr = {-1, 1, 0, 0};
                    int[] dc = {0, 0, -1, 1};

                    boolean found = false;

                    while (!stack.isEmpty()) {
                        Position current = stack.peek();

                        // إذا وصلنا إلى E
                        if (maze[current.row][current.col] == 'E') {
                            found = true;
                            break;
                        }

                        boolean moved = false;

                        // تجربة الجهات الأربع
                        for (int i = 0; i < 4; i++) {
                            int newRow = current.row + dr[i];
                            int newCol = current.col + dc[i];

                            // التحقق من الحدود
                            if (newRow >= 0 && newRow < rows && newCol >= 0 && newCol < cols) {

                                // إذا كانت الخلية طريق أو نهاية ولم تتم زيارتها
                                if (!visited[newRow][newCol] &&
                                        (maze[newRow][newCol] == '0' || maze[newRow][newCol] == 'E')) {

                                    stack.push(new Position(newRow, newCol));
                                    visited[newRow][newCol] = true;
                                    moved = true;
                                    break;
                                }
                            }
                        }

                        // إذا لم يتحرك، نرجع للخلف
                        if (!moved) {
                            stack.pop();
                        }
                    }

                    // 6) النتيجة
                    if (found) {
                        System.out.println("Maze Solved!");
                        System.out.println("Path:");

                        for (Position p : stack) {
                            System.out.println("(" + p.row + ", " + p.col + ")");
                        }
                    } else {
                        System.out.println("No path found.");
                    }

                } catch (Exception e) {
                    System.out.println("Error reading file: " + e.getMessage());
                }
            }
        }



