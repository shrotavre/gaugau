package ga.shrotavre.gaugau;

import java.util.ArrayList;

/**
 * Created by Shrotavre on 6/12/2015.
 */
public class LinearEquation {
    private int variableCount;
    private float constantValue;
    private float[] variableCoefficient;

    public LinearEquation(int variableCount) {
        this.variableCount = variableCount;
        this.variableCoefficient = new float[variableCount];
    }

    public LinearEquation(float[] variableCoefficientArray, float constantValue) {
        this.variableCount = variableCoefficientArray.length;
        this.variableCoefficient = new float[variableCount];

        this.setCoefficients(variableCoefficientArray, constantValue);
    }

    public void setCoefficients(float[] variableCoefficientArray, float constantValue) {
        for (int i = 0; i < variableCoefficientArray.length; i++) {
            variableCoefficient[i] = variableCoefficientArray[i];
        }
        this.constantValue = constantValue;
    }

    public void setCoefficients(int variableIndex, float coefficientValue) {
        this.variableCoefficient[variableIndex] = coefficientValue;
    }

    public void setConstantValue(float constantValue) {
        this.constantValue = constantValue;
    }

    public float[] toArray() {
        float[] arrayResult = new float[variableCount + 1];

        arrayResult[variableCount] = constantValue;
        for (int i = 0; i < variableCount; i++) {
            arrayResult[i] = variableCoefficient[i];
        }

        return arrayResult;
    }

    // Helpers
    public int getVariableCount() {
        return variableCount;
    }

    public float getConstant() {
        return constantValue;
    }

    public float getVariable(int index) {
        return variableCoefficient[index];
    }

    // Overriden Methods
    @Override
    public String toString() {
        String outputString = "";
        for (int i = 0; i < variableCoefficient.length; i++) {
            String variableName = Character.toString((char) (97 + i));
            float currentCoefficient = variableCoefficient[i];

            // Sign switching
            if (outputString.length() > 0) {
                if (currentCoefficient >= 0) {
                    outputString += (" + ");
                } else {
                    outputString += (" - ");
                }
                currentCoefficient = Math.abs(currentCoefficient);
            }

            // Writing
            outputString += currentCoefficient + variableName;
        }
        outputString += (" = ") + constantValue;
        return outputString;
    }
}
