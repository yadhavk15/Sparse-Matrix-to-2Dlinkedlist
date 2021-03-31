# Sparse-Matrix-to-2Dlinkedlist
A java program to edit a sparse matrix found in a file using a 2d linked list as datastructure.

# Problem Definition
Write a program which reads a file named sparseMatrix.txt whereby each line contains       the values in the matrix separated by a ‘tab’. The file contains as many lines as there are  rows and each row contains as many values as there are columns in the matrix.

1. Your program should read the file, constitute the matrix, display it on the screen and         represent the matrix using the triplet representation and store it in a file named                 sparseTriplet.txt”.

3. The second part of the program should read the sparseTriplet.txt file and represent the  data in the form of a 2-D linked list, display it on the screen (For simplicity, you may wish    to make the last node of each row/column point to an asterix, ‘*’ ).

5. Once the sparse matrix is stored as a 2-D linked list, it should allow insertion and removal  of values. After insertion and removal of each value, the program should save the new sparse matrix by overwriting sparseMatrix.txt and sparseTriplet.txt.


# Solution Definition

## Node
Each node in the 2d linked list contains a value for a specific element in the matrix, a reference(next)  to the next node in the first row, and a reference(down) to the node down it.

## matrix_to_2dlkdlist(int[][] arr):
- It takes as an argument a 2d array which represents a matrix then converts it into a 2d linked list. 
- Firstly it creates a node for each first element of each row and stores it into a node array, then it populates the list of each first element with the other elements in that row.
- Secondly, it assigns the down reference of each node accordingly.

## edit(int row, int column, int data):
- Take as an argument the row and column of the data we want to change in the matrix and the new data.
- Traverses the first row of the triplet matrix 2d linked list and compares its value to the row value and compares its down node value to the column value until the required one is reached.
- Then change the down down node value to the new data.

## delete(int row, int column):
- Takes as argument the row and column of the data we want to delete
- Traverses the first row of the triplet matrix 2d linked list and compares its value to the row value and compares its down node value to the column value until the required one is reached.
- Then deletes that value by changing the next and down node references accordingly.

## write_2dlinkedlist_TripletMatrix(): 
- Traverses the 2d triplet matrix linked list and writes the elements to the sparseMatrix.txt file.

## write_2dlinkedlist_SparseMatrix(int row, int column): 
- Takes as argument the number of  row and columns in the sparse matrix
- Traverses the 2d triplet matrix linked list and converts it to a Sparse matrix while writing it to the sparseMatrix.txt file.
 
## write_matrix(int array[][], String filename): 
- Takes as argument a 2d array and the filename
- Writes the matrix to that file

## int[][] read_matrix(String filename): 
- Takes as argument the filename
- Read the matrix in that file 
- Returns it as a 2d array

## Int[][] generate_TripletMatrix(int array[][]):
- Takes as argument a 2d array read from the sparseMatrix.txt file and converts it to a Triplet Matrix and returns it as a 2d array.

## write_matrix(int array[][], String filename):
- Takes as argument the filename ad a 2d array
- Writes the matrix to the file
