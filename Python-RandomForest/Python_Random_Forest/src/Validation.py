'''
Created on Jul 26, 2022

@author: PHARTEN
'''

import pandas as pd
from sklearn.model_selection import train_test_split

from Extract_X_and_Y_Matrices import extract_X_Y_matrices
from Transform_DataFrames_to_Arrays import transform_dataframes_to_arrays

from sklearn.ensemble import RandomForestRegressor
from sklearn.model_selection import cross_val_score, cross_val_predict

def main():
    desired_result = "viability"
    #desired_result = "expression levels"

    # input_Imputed_Values = "..\\data\\Imputed_Numerical_Columns.csv"
    input_Imputed_Values = "..\\data\\Multivariate_Imputed_Numerical_Columns.csv" 
    
    # Read X and Y matrices from CSV files.
    df = pd.read_csv(input_Imputed_Values, na_values = "NULL", skip_blank_lines = False, 
                     keep_default_na = True, na_filter = False, low_memory = False,
                     encoding = 'utf-8-sig')
    
    # Extract X and Y matrices
    dfX, dfY = extract_X_Y_matrices(desired_result, df)
    
    # Keep features from dfX
    #train_features = list(dfX.columns)
    
    # Transform X and y DataFrames to arrays.
    X = dfX.to_numpy()
    y = dfY.to_numpy()
    
    #print(y)
    
    # sklearn RandomForestRegressor
    rfa = RandomForestRegressor(n_estimators=100, n_jobs=1, random_state=0, min_samples_split=4, max_samples=0.8, max_features=0.8)
    
    #rfa.fit(X, y)
    
    #diff = y - rfa.predict(X)
    
    #print(diff.mean(), diff.std())
    
    # Train Random Forest regressor
    scores = cross_val_score(rfa, X, y, cv=5, verbose=2)
    
    print(scores)
    
    print("%0.2f accuracy with a standard deviation of %0.2f" % (scores.mean(), scores.std()))
    

if __name__ == "__main__":
    main()
