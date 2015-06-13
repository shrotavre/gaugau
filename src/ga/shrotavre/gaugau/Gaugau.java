package ga.shrotavre.gaugau;

import java.util.ArrayList;

/**
 * Created by Shrotavre on 6/12/2015.
 */
public class Gaugau {
    private ArrayList<LinearEquation> linearEquationArrayList;
    private ArrayList<Float> resultArrayList;

    public Gaugau() {
        this.linearEquationArrayList = new ArrayList<>();
        this.resultArrayList = new ArrayList<>();
    }

    // Initialization methods
    public void addLinearEquation(LinearEquation linearEquation) {
        this.linearEquationArrayList.add(linearEquation);
    }

    public void removeLinearEquation(int linearEquationIndex) {
        this.linearEquationArrayList.remove(linearEquationIndex);
    }

    public void removeLinearEquation(LinearEquation linearEquation) {
        this.linearEquationArrayList.remove(linearEquation);
    }

    // Elmination process
    public final void process(boolean roundResult) throws Exception {
        this.process();
        if (roundResult) {
            for (int i = 0; i < resultArrayList.size(); i++) {
                resultArrayList.set(i, (float) Math.round(resultArrayList.get(i)));
            }
        }
    }

    public final void process() throws Exception {
        int linearEquationCount, variableCount;

        evalLinearEquations();

        // Value validating
        if (linearEquationArrayList.size() < 2) {
            throw new ArithmeticException("Linear equations provided is not enough to find solution");
        }

        // Elimination logics
        linearEquationCount = linearEquationArrayList.size();
        variableCount = linearEquationArrayList.get(0).getVariableCount();

        for (int i = 0; i < linearEquationArrayList.size(); i++) {
            float[] currentVector = linearEquationArrayList.get(i).toArray();
            if (currentVector.length - 1 < variableCount) {
                variableCount = currentVector.length;
            }
        }

        // Creating input array
        ArrayList<float[]> inputList = new ArrayList<>();
        for (int i = 0; i < linearEquationCount; i++) {
            float[] newArray = new float[variableCount + 1];

            for (int j = 0; j < variableCount; j++) {
                newArray[j] = linearEquationArrayList.get(i).getVariable(j);
            }
            newArray[variableCount] = linearEquationArrayList.get(i).getConstant();
            inputList.add(newArray);
        }

        // Inputting array
        // Declare processArray to be of size (n+1)x(m+1) and do not use index 0
        float[][] processArray = new float[linearEquationCount + 1][variableCount + 2];
        for (int i = 1; i <= linearEquationCount; i++) {
            float[] currentVector = inputList.get(i - 1);
            for (int j = 1; j <= variableCount + 1; j++) {
                processArray[i][j] = currentVector[j - 1];
            }
        }

//        printMatrix(processArray);

        System.out.println();

        // perform Gauss-Jordan Elimination algorithm
        int i = 1;
        int j = 1;

        while (i <= linearEquationCount && j <= variableCount) {

            //look for a non-zero entry in col j at or below row i
            int k = i;
            while (k <= linearEquationCount && processArray[k][j] == 0) k++;
            // if such an entry is found at row k
            if (k <= linearEquationCount) {

                //  if k is not i, then swap row i with row k
                if (k != i) {
                    swap(processArray, i, k, j);
//                    printMatrix(processArray);
                }

                // if ProcessingArray[i][j] is not 1, then divide row i by ProcessingArray[i][j]
                if (processArray[i][j] != 1) {
                    divide(processArray, i, j);
//                    printMatrix(processArray);
                }

                // eliminate all other non-zero entries from col j by subtracting from each
                // row (other than i) an appropriate multiple of row i
                eliminate(processArray, i, j);
//                printMatrix(processArray);
                i++;
            }
            j++;
        }

        for (int k = 1; k <= variableCount; k++) {
            float nilaiCurrentTerm = processArray[k][variableCount + 1];
            resultArrayList.add(nilaiCurrentTerm);
        }
    }

    private void evalLinearEquations() {
        // Validate variable counts
        int variableCount = linearEquationArrayList.get(0).getVariableCount();
        for (int i = 0; i < linearEquationArrayList.size(); i++) {
            LinearEquation currentLinearEquation = linearEquationArrayList.get(i);
            variableCount = variableCount < currentLinearEquation.getVariableCount() ? currentLinearEquation.getVariableCount() : variableCount;
        }
    }

    // Core function
    private final void swap(float[][] A, int i, int k, int j) {
        int m = A[0].length - 1;
        float temp;
        for (int q = j; q <= m; q++) {
            temp = A[i][q];
            A[i][q] = A[k][q];
            A[k][q] = temp;
        }
    }

    private final void divide(float[][] A, int i, int j) {
        int m = A[0].length - 1;
        for (int q = j + 1; q <= m; q++) A[i][q] /= A[i][j];
        A[i][j] = 1;
    }

    private final void eliminate(float[][] A, int i, int j) {
        int n = A.length - 1;
        int m = A[0].length - 1;
        for (int p = 1; p <= n; p++) {
            if (p != i && A[p][j] != 0) {
                for (int q = j + 1; q <= m; q++) {
                    A[p][q] -= A[p][j] * A[i][q];
                }
                A[p][j] = 0;
            }
        }
    }

    private void printMatrix(float[][] A) {
        int n = A.length - 1;
        int m = A[0].length - 1;
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                System.out.print(A[i][j] + "  ");
            }
            System.out.println();
        }
        System.out.println();
    }

    // Helpers
    public ArrayList<Float> getResult() {
        return resultArrayList;
    }

    public Object[] getResultArray() {
        return resultArrayList.toArray();
    }

    public float getVariableValue(int variableIndex) {
        return resultArrayList.get(variableIndex);
    }
}
