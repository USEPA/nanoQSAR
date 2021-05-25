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
from Write_to_CSV import write_to_csv

def main():
    # input_Imputed_Values = "..\\data\\Imputed_Numerical_Columns.csv"
    input_Imputed_Values = "..\\data\\Multivariate_Imputed_Numerical_Columns.csv" 
    output_xtrain = "data\\X_Train.csv"
    output_ytrain = "data\\y_Train.csv"
    output_xtest = "data\\X_Test.csv"
    output_ytest = "data\\y_Test.csv"
    
    # Read X and Y matrices from CSV files.
    df = pd.read_csv(input_Imputed_Values, na_values = "NULL", skip_blank_lines = False, 
                     keep_default_na = True, na_filter = False, low_memory = False,
                     encoding = 'utf-8-sig')
    
    # Extract X and Y matrices
    dfX, dfY = extract_X_Y_matrices(df)
    
    # Keep features from dfX
    train_features = list(dfX.columns)
    
    # Split X and Y matrices into training and testing data sets.
    dfX_train, dfX_test, dfy_train, dfy_test = split_X_y_training_testing(dfX, dfY)
    
    # Write train and test matrices to CSV files.
    write_to_csv(dfX_train, output_xtrain)
    write_to_csv(dfX_test, output_xtest)
    write_to_csv(dfy_train, output_ytrain)
    write_to_csv(dfy_test, output_ytest)
    
    # Transform X and Y matrices to arrays
    X_train, y_train, X_test, y_test = transform_dataframes_to_arrays(dfX_train, dfX_test, dfy_train, dfy_test)
    
    # Split X and Y matrices into training and testing data sets.
    # X_train, X_test, y_train, y_test = split_X_y_training_testing(X_array, y_array)
    
    # Perform a Random Forest regression
    perform_RandomForest_regression(train_features, X_train, y_train, X_test, y_test)

if __name__ == "__main__":
    main()
        
    