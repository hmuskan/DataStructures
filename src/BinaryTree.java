import java.util.*;
import java.util.stream.Collectors;

public class BinaryTree {
    private class Node {
        int value;
        Node left, right;
        Node(int value) {
            this.value = value;
            left = right = null;
        }
    }
    private Node root;

    BinaryTree() {
        root = null;
    }

    public void add(int val) {
        root = add(root, val);
    }

    private Node add(Node current, int value) {
        if(current == null) {
            return new Node(value);
        }

        if(value < current.value) {
            current.left = add(current.left, value);
        } else if(value > current.value) {
            current.right = add(current.right, value);
        } else {
            return current;
        }

        return current;
    }
    public void preOrderRecursive() {
        preOrderRecursive(root);
    }

    private void preOrderRecursive(Node node) {
        if(node != null) {
            System.out.print(node.value + " ");

            if(node.left != null) {
                preOrderRecursive(node.left);
            }

            if(node.right != null) {
                preOrderRecursive(node.right);
            }
        }
    }

    public void preOrderIterative() {
        if(root != null) {
            Stack<Node> stack = new Stack<>();
            stack.push(root);
            Node temp;
            while(!stack.isEmpty()) {
                temp = stack.pop();
                System.out.print(temp.value + " ");
                if(temp.right != null) {
                    stack.push(temp.right);
                }
                if(temp.left != null) {
                    stack.push(temp.left);
                }
            }
        }
    }

    public void inOrderRecursive() {
        inOrderRecursive(root);
    }

    private void inOrderRecursive(Node node) {
        if(node != null) {
            if(node.left != null) {
                inOrderRecursive(node.left);
            }

            System.out.print(node.value + " ");

            if(node.right != null) {
                inOrderRecursive(node.right);
            }
        }
    }

    public void inOrderIterative() {
        if(root != null) {
            Stack<Node> stack = new Stack<>();
            Node curr = root;
            while(curr != null || !stack.isEmpty()) {
                while(curr != null) {
                    stack.push(curr);
                    curr = curr.left;
                }

                curr = stack.pop();
                System.out.print(curr.value + " ");
                curr = curr.right;
            }
        }
    }

    public void postOrderRecursive() {
        postOrderRecursive(root);
    }

    private void postOrderRecursive(Node node) {
        if(node != null) {
            if(node.left != null) {
                postOrderRecursive(node.left);
            }

            if(node.right != null) {
                postOrderRecursive(node.right);
            }

            System.out.print(node.value + " ");
        }
    }

    public void postOrderIterative() {
        if(root == null) {
            return;
        }

        Stack<Node> s1, s2;
        s1 = new Stack<>();
        s2 = new Stack<>();
        s1.push(root);
        Node temp;
        while(!s1.isEmpty()) {
            temp = s1.pop();
            s2.push(temp);

            if(temp.left != null) {
                s1.push(temp.left);
            }
            if(temp.right != null) {
                s1.push(temp.right);
            }
        }

        while(!s2.isEmpty()) {
            temp = s2.pop();
            System.out.print(temp.value + " ");
        }
    }

    public List<Double> averageOfLevels() {
        Queue<Node> queue = new LinkedList<>();
        ArrayList<Double> res = new ArrayList<>();
        queue.add(root);
        while(!queue.isEmpty()) {
            Queue<Node> neighbours = new LinkedList<>();
            int count = 0, sum = 0;
            while (!queue.isEmpty()) {
                Node node = queue.remove();
                sum += node.value;
                count++;
                if(node.left != null)
                    neighbours.add(node.left);
                if(node.right != null)
                    neighbours.add(node.right);
            }
            queue = neighbours;
            res.add(sum * 1.0 / count);
        }

        return res;
    }

    List<Integer> nums = new ArrayList();
    public Node balanceBST(Node root) {
        inorder(root);
        return construct(0, nums.size() - 1);
    }

    public void inorder(Node node) {
        if(node != null) {
            if(node.left != null)
                inorder(node.left);

            nums.add(node.value);

            if(node.right != null)
                inorder(node.right);
        }
    }

    public Node construct(int start, int end) {
        if(start > end) {
            return null;
        }

        int mid = start + (end - start) / 2;
        Node node = new Node(nums.get(mid));
        node.left = construct(start, mid - 1);
        node.right = construct(mid + 1, end);
        return node;
    }

    public int maxDepth() {
        return maxDepth(root);
    }

    private int maxDepth(Node node) {
        if(node == null)
            return 0;
        int leftDepth = maxDepth(node.left);
        int rightDepth = maxDepth(node.right);
        int maxDepth = Math.max(leftDepth, rightDepth) + 1;
        return maxDepth;
    }

    public int minDepth() {
        return minDepth(root);
    }
    private int minDepth(Node node) {
        if(node == null)
            return 0;
        int leftDepth = minDepth(node.left);
        int rightDepth = minDepth(node.right);
        //int minDepth = Math.min(leftDepth, rightDepth) + 1;
        if(leftDepth == 0 && rightDepth != 0)
            return 1 + rightDepth;
        else if(rightDepth == 0 && leftDepth != 0)
            return 1 + leftDepth;
        else
            return 1 + Math.min(leftDepth, rightDepth);
    }

    public static List<String> watchedVideosByFriends(List<List<String>> watchedVideos, int[][] friends, int id, int level) {
        Queue<Integer> queue = new LinkedList();
        HashSet<Integer> visited = new HashSet();
        queue.add(id);
        visited.add(id);
        int currLevel = 0;
        while(!queue.isEmpty() && currLevel != level) {
            int size = queue.size();
            int currSize = 0;
            while(currSize < size) {
                int n = queue.remove();
                currSize++;
                for(int i : friends[n]) {
                    if(!visited.contains(i)) {
                        queue.add(i);
                        visited.add(i);
                    }
                }
            }
            currLevel++;
        }

        Map<String, Integer> map = new TreeMap();
        while(!queue.isEmpty()) {
            int fid = queue.remove();
            for(String l : watchedVideos.get(fid)) {
                    map.put(l, map.getOrDefault(l, 0) + 1);

            }
        }

        return sortByValue(map);

    }
    static List<String> sortByValue(Map<String, Integer> map) {
        List<Map.Entry<String, Integer>> list = new LinkedList(map.entrySet());
        Collections.sort(list, Comparator.comparing(Map.Entry::getValue));
        List<String> res = new ArrayList<>();
        for(Map.Entry<String, Integer> entry: list) {
            res.add(entry.getKey());
        }

        return res;
    }


    public List<List<Integer>> zigzagLevelOrder(Node root) {
        if(root == null) {
            List<List<Integer>> blank = new ArrayList();
            return blank;
        }
        Queue<Node> queue = new LinkedList();
        List<List<Integer>> traversal = new ArrayList();
        int toggle = 1;
        queue.add(root);
        while(!queue.isEmpty()) {
            List<Integer> leveln = new ArrayList();
            int size = queue.size();
            int currSize = 0;
            while(currSize < size) {
                Node node = queue.remove();
                currSize++;
                if(toggle == -1) {
                    leveln.add(0, node.value);
                } else {
                    leveln.add(node.value);
                }
                if(node.left != null) {
                    queue.add(node.left);
                }

                if(node.right != null) {
                    queue.add(node.right);
                }

            }
            toggle *= -1;
            traversal.add(leveln);
        }
        return traversal;
    }
    
    public static void main(String[] args) {
        BinaryTree tree = new BinaryTree();
        tree.add(6);
        tree.add(4);
        tree.add(8);
        tree.add(3);
        tree.add(5);
        tree.add(7);
        tree.add(9);
        System.out.println(tree.zigzagLevelOrder(tree.root));

        /*String[][] arr = {
                {"A","B"},{"C"},{"B","C"},{"D"}
        };
        List<List<String>> watchedVideos = Arrays.stream(arr)
                .map(Arrays::asList)
                .collect(Collectors.toList());
        
        int[][] friends = {{1,2},{0,3},{0,3},{1,2}};

        System.out.println(watchedVideosByFriends(watchedVideos, friends, 0, 1));*/

        /*tree.preOrderRecursive();
        System.out.println();
        tree.preOrderIterative();
        System.out.println();
        tree.inOrderRecursive();
        System.out.println();
        tree.inOrderIterative();
        System.out.println();
        tree.postOrderRecursive();
        System.out.println();
        tree.postOrderIterative();*/
        //System.out.println(tree.averageOfLevels());


    }
}


