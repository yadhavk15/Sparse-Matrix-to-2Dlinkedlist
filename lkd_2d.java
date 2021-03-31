import java.io.FileNotFoundException;
import java.io.File;
import java.util.Formatter;
import java.util.Scanner;

class Node {
    private int data;
    private Node next;
    private Node down;

    Node() {
        this.next = null;
        this.down = null;
    }

    public Node(int data) {
        this.data = data;
    }

    public int getData() {
        return data;
    }

    public void setData(int data) {
        this.data = data;
    }

    public Node getNext() {
        return next;
    }

    public void setNext(Node next) {
        this.next = next;
    }

    public Node getDown() {
        return down;
    }

    public void setDown(Node down) {
        this.down = down;
    }
}

class lkd_2d {

    private Node head;

    public lkd_2d() {
        head = null;
    }

    public Node getHead() {
        return head;
    }

    public void matrix_to_2dlkdlist(int[][] arr) {

        int rows = arr.length;
        int columns = arr[0].length;

        Node[] node_array = new Node[rows];// stores the head of linked lists of each row
        Node previous = new Node();
        Node next, down, up;

        for (int i = 0; i < rows; i++) { // create a linked list for each row by traversing matrix
            node_array[i] = null; // initialize the head of each row
            for (int j = 0; j < columns; j++) {
                next = new Node(arr[i][j]);
                if (head == null) // matrix [0][0] elements becomes head of 2D linked list
                    head = next;
                if (node_array[i] == null)
                    node_array[i] = next; // if first node in array list is null
                else
                    previous.setNext(next);

                previous = next;
            }
        }

        for (int i = 0; i < rows - 1; i++) { /// traverse the head of linked lists of each row and the element down it
            up = node_array[i];
            down = node_array[i + 1];
            while (up != null && down != null) { // set the down node for each element in that linked list
                up.setDown(down);
                up = up.getNext();
                down = down.getNext();
            }
        }
    }

    void display() { // display 2dlinkedlsit
        Node nextup, nextdown = head;
        while (nextdown != null) {
            nextup = nextdown;
            while (nextup != null) {
                System.out.print(nextup.getData() + " ");
                nextup = nextup.getNext();
            }
            System.out.println();
            nextdown = nextdown.getDown();
        }
        System.out.println();
    }

    void edit(int row, int column, int data) {
        Node next = head;
        int flag = 0;
        while (next != null) { // traverse 1st row of linked list and compare column and row entered
            if (next.getData() == row && next.getDown().getData() == column) {
                next.getDown().getDown().setData(data);
                flag = 1;
            }
            next = next.getNext();
        }
        if (flag == 0) {
            System.out.println("Error in editing, recheck if row and column are well entered");
        } else {
            System.out.println("Data Modified\n");
        }
    }

    void delete(int row, int column) {
        Node next = head;
        Node previous = head;
        int flag = 0;
        if (next.getData() == row && next.getDown().getData() == column) {
            head = next.getNext();
            flag = 1;
        }
        while (next != null) { // traverse 1st row of linked list and compare column and row entered
            if (next.getData() == row && next.getDown().getData() == column) {
                if (next.getNext() != null) { // change previous reference to next next reference to delete
                    previous.setNext(next.getNext());
                    previous.getDown().setNext(next.getDown().getNext());
                    previous.getDown().getDown().setNext(next.getDown().getDown().getNext());
                    flag = 1;
                } else { // if it is the last in the link list that we are deleting
                    previous.setNext(null);
                    previous.getDown().setNext(null);
                    previous.getDown().getDown().setNext(null);
                    flag = 1;

                }
            }
            previous = next;
            next = next.getNext();
        }
        System.out.println();
        if (flag == 0) {
            System.out.println("Error in deleting,recheck if row and column are well entered");
        } else {
            System.out.println("Data deleted\n");
        }
    }

    private static Scanner infile;
    private static Formatter outfile;

    void write_2dlinkedlist_TripletMatrix() {

        try {
            outfile = new Formatter("sparseTriplet.txt");
            Node nextup, nextdown = head;
            while (nextdown != null) {
                nextup = nextdown;
                while (nextup != null) {
                    outfile.format("%d ", nextup.getData());
                    nextup = nextup.getNext();
                }
                outfile.format("\n");
                nextdown = nextdown.getDown();
            }
            outfile.close();
        } catch (FileNotFoundException fnfe) {
            System.out.println("Error Creating File");
            System.exit(1);
        }
    }

    void write_2dlinkedlist_SparseMatrix(int row, int column) {
        try {
            outfile = new Formatter("sparseMatrix.txt");
            Node next = head;
            for (int z = 0; z < row; z++) {
                for (int w = 0; w < column; w++) {
                    if (next == null) {
                        outfile.format("%d ", 0);
                        continue;
                    }
                    if (next.getData() == z && next.getDown().getData() == w && next != null) {
                        outfile.format("%d ", next.getDown().getDown().getData());
                        next = next.getNext();
                    } else {
                        outfile.format("%d ", 0);
                    }
                }
                outfile.format("\n");
            }
            outfile.close();
        } catch (FileNotFoundException fnfe) {
            System.out.println("Error Creating File");
            System.exit(1);
        }
    }

    public static void display_matrix(int array[][]) {
        int row = array.length;
        int column = array[0].length;
        for (int z = 0; z < row; z++) {
            for (int w = 0; w < column; w++) {
                System.out.print(array[z][w] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    public static int[][] generate_TripletMatrix(int array[][]) {
        int rows = array.length;
        int columns = array[0].length;
        int size = 0;
        for (int row = 0; row < rows; row++) {
            for (int column = 0; column < columns; column++) {
                if (array[row][column] != 0) {
                    size++;
                }
            }
        }
        int[][] resultMatrix = new int[3][size];
        int k = 0;
        for (int row = 0; row < rows; row++)
            for (int column = 0; column < columns; column++)
                if (array[row][column] != 0) {
                    resultMatrix[0][k] = row;
                    resultMatrix[1][k] = column;
                    resultMatrix[2][k] = array[row][column];
                    k++;
                }
        return resultMatrix;
    }

    public static void write_matrix(int array[][], String filename) {
        int row = array.length;
        int column = array[0].length;
        try {
            outfile = new Formatter(filename);
            for (int i = 0; i < row; i++) {
                for (int j = 0; j < column; j++) {
                    outfile.format("%d ", array[i][j]);
                }
                outfile.format("\n");
            }
            outfile.close();
        } catch (FileNotFoundException fnfe) {
            System.out.println("Error Creating File");
            System.exit(1);
        }
    }

    public static int[][] read_matrix(String filename) {
        int rows = 0;
        int columns = 0;
        String buffer = "";
        String[] line;
        try {
            infile = new Scanner(new File(filename));
            while (infile.hasNextLine()) {
                rows++; //each line represents a row
                buffer = infile.nextLine();
            }
            line = buffer.trim().split(" ");
            columns = line.length; // to know the no of columns in the matrix
            infile.close();
        } catch (FileNotFoundException fnfe) {
            System.out.println("Error Reading File");
            System.exit(1);
        }
        int[][] filematrix = new int[rows][columns];
        try {
            infile = new Scanner(new File(filename)); //re reads file into a 2d array
            for (int i = 0; i < rows; ++i) {
                for (int j = 0; j < columns; ++j) {
                    if (infile.hasNextInt()) {
                        filematrix[i][j] = infile.nextInt();
                    }
                }
            }
            infile.close();
        } catch (FileNotFoundException fnfe) {
            System.out.println("Error Reading File");
            System.exit(1);
        }
        return filematrix;
    }

    public static void main(String[] args) {

        int filematrix[][] = read_matrix("sparseMatrix.txt"); //read sparse matrix from file
        System.out.println("Sparse Matrix read from file:\n");
        display_matrix(filematrix);

        int resultMatrix[][] = generate_TripletMatrix(filematrix); //generates triplet matrix
        System.out.println("Triplet Matrix generated:\n");
        display_matrix(resultMatrix);
        write_matrix(resultMatrix, "sparseTriplet.txt"); //write triplet matrix to file

        lkd_2d triplet_2dlinkedlist = new lkd_2d();
        int read_tripletMatrix[][] = read_matrix("sparseTriplet.txt"); //read triplet matrix from file

        triplet_2dlinkedlist.matrix_to_2dlkdlist(read_tripletMatrix); //converts triplet matrix to 2d linked list
        System.out.println("Triplet Matrix Linked List:\n");
        triplet_2dlinkedlist.display();

        Scanner scan = new Scanner(System.in);
        int choice = 0;
        int row, column, data;
        while (choice == 0) {
            System.out.println("Enter 1 to edit");
            System.out.println("Enter 2 to delete");
            System.out.println("Enter 3 to exit");
            choice = scan.nextInt();
            switch (choice) {
                case 1:
                    System.out.println("Enter row");
                    row = scan.nextInt();
                    System.out.println("Enter column");
                    column = scan.nextInt();
                    System.out.println("Enter new data\n");
                    data = scan.nextInt();
                    triplet_2dlinkedlist.edit(row, column, data); //edit Triplet matrix 
                    triplet_2dlinkedlist.display();
                    triplet_2dlinkedlist.write_2dlinkedlist_TripletMatrix(); //update triplet matrix file
                    triplet_2dlinkedlist.write_2dlinkedlist_SparseMatrix(filematrix.length, filematrix[0].length); //update sparse matrix file
                    break;
                case 2:
                    System.out.println("Enter row");
                    row = scan.nextInt();
                    System.out.println("Enter column\n");
                    column = scan.nextInt();
                    triplet_2dlinkedlist.delete(row, column);
                    triplet_2dlinkedlist.display();
                    triplet_2dlinkedlist.write_2dlinkedlist_TripletMatrix();
                    triplet_2dlinkedlist.write_2dlinkedlist_SparseMatrix(filematrix.length, filematrix[0].length);
                    break;
                case 3:
                    break;
                default:
                    System.out.println("Wrong input, Re enter choice ");
                    break;
            }
            if (choice == 3) {
                break;
            } else {
                choice = 0;
            }
        }
        scan.close();
    }
}