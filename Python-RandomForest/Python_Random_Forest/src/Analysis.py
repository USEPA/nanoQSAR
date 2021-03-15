'''
This module contains the definition of the main function of the  
program.  

Created on Oct 29, 2020

@author: Wilson Melendez
'''

import pandas as pd

from Extract_X_and_Y_Matrices import extract_X_Y_matrices
from Transform_DataFrames_to_Arrays import transform_dataframes_to_arrays
from Split_XY_Training_Testing_Matrices import split_X_y_training_testing
from RandomForest_Regression import perform_RandomForest_regression

def main():
    input_Imputed_Values = "..\\data\\Imputed_Numerical_Columns.csv"
    
        # Read X and Y matrices from CSV files.
    df = pd.read_csv(input_Imputed_Values, na_values = "NULL", skip_blank_lines = False, 
                     keep_default_na = True, na_filter = False, low_memory = False,
                     encoding = 'utf-8-sig')
    
    # Extract X and Y matrices
    dfX, dfY = extract_X_Y_matrices(df)
    
    # Keep features from dfX
    train_features = list(dfX.columns)
    
    # Transform X and Y matrices to arrays
    X_array, y_array = transform_dataframes_to_arrays(dfX, dfY)
    
    # Split X and Y matrices into training and testing data sets.
    X_train, X_test, y_train, y_test = split_X_y_training_testing(X_array, y_array)
    
    # Perform a Random Forest regression
    perform_RandomForest_regression(train_features, X_train, y_train, X_test, y_test)

if __name__ == "__main__":
    main()
        
    