'''
Created on Apr 1, 2021

@author: Wmelende
'''

import pandas as pd
import numpy as np


# SKLearn explicitly requires this experimental feature
from sklearn.experimental import enable_iterative_imputer

# Now we can import normally from sklearn.impute
from sklearn.impute import IterativeImputer
from sklearn.linear_model import BayesianRidge
from pandas.tests.test_nanops import skipna
from sklearn.tree._classes import DecisionTreeRegressor, ExtraTreeRegressor

# from sklearn.tree import DecisionTreeRegressor
# from sklearn.ensemble import ExtraTreesRegressor
# from sklearn.neighbors import KNeighborsRegressor

def iteratively_impute_numerical_columns(df):    
    # Extract column names
    column_names = list(df.columns)
    
    # Define list with parameter columns.
    param_columns = ['OuterDiameterValue', 'SurfaceAreaValue', 'HydrodynamicDiameterValue', 
                    'particle concentration parameter_value','duration incubation parameter_value', 
                    'duration aging parameter_value', 'duration irradiation parameter_value',  
                    'irradiance parameter_value', 'irradiation power parameter_value',
                    'Purity', 'ChargeAvg', 'duration exposure parameter_value', 'temperature parameter_value',
                    'number of cells parameter_value', 'concentration carbon dioxide parameter_value'] 
    
    # Store copper and zinc concentrations in a separate list.
    param_conc_columns = ['concentration zinc parameter_value', 'concentration copper parameter_value']
    
    # Extract columns with additive_value
    subs_value = "additive_value"
    additive_columns  = [icol for icol in column_names if subs_value in icol]
    additive_columns.remove("fetal bovine serum additive_value")
    
    # Extract columns with contaminant_value 
    subs_value = "contaminant_value"
    contaminant_columns  = [icol for icol in column_names if subs_value in icol]
    
    categorical_columns = []
    # Extract encoded categorical data
    cat_patterns = ['CoreComposition', 'ShellComposition', 'CoatingComposition', 'Shape', 'SurfaceChargeType',
                    'particle concentration parameter_nonnum', 'cell type parameter_nonnum',
                    'subject parameter_nonnum', 'light parameter_nonnum']
    for pattern in cat_patterns:
        subs_value = pattern
        encoded_columns  = [icol for icol in column_names if subs_value in icol]
        categorical_columns = categorical_columns + encoded_columns
    
    # Add all columns to be used in the iterative imputation algorithm to a single list.
    total_cols = categorical_columns + param_columns + param_conc_columns + additive_columns + contaminant_columns
    
    # Extract results columns and store them in a separate DataFrame
    subs_value = "result_value"
    result_columns  = [icol for icol in column_names if subs_value in icol]
    result_columns.remove("viability result_value")
    #df_results = df[result_columns].copy()
    total_cols = total_cols + result_columns
    
    subs_viability = "viability"
    viability_columns  = [icol for icol in column_names if subs_viability in icol]
    df_viability = df[viability_columns].copy()
    
    # Make a copy of the DataFrame with the chosen columns.
    df_temp = df[total_cols].copy()
    
    # Impute additives and parameter-concentrations missing values with zeros.
    total_cols_zero = additive_columns + contaminant_columns + param_conc_columns
    df_temp[total_cols_zero] = df_temp[total_cols_zero].fillna(value = 0.0)
    
    # Extract minimum and maximum values of features and store them in separate lists.
    minimum_values = list(df_temp.min(axis = 0, skipna = True))
    maximum_values = list(df_temp.max(axis = 0, skipna = True))
    
    # Obtain length of lists
    length = len(maximum_values)
    
    # Loop over each element in lists and determine whether maximum value > minimum value.
    for i in range(length):
        if (maximum_values[i] <= minimum_values[i]):
            maximum_values[i] = 1.1 * maximum_values[i]
    
    # Define iterative imputer
    # Estimators available are:
    # 1) BayesianRidge: regularized linear regression  --> this is the default used by SKLearn
    # 2) DecisionTreeRegressor: non-linear regression
    # 3) ExtraTreesRegressor: similar to missForest in R (missForest is very popular with R users)
    # 4) KNeighborsRegressor: comparable to other KNN imputation approaches
    imp = IterativeImputer(estimator = DecisionTreeRegressor(),
                           max_iter = 100, 
                           random_state = 0, 
                           missing_values = np.nan, 
                           initial_strategy = 'mean',
                           verbose = 0,
                           min_value = minimum_values,
                           max_value = maximum_values)
    
    df_imp = imp.fit_transform(df_temp)
    
    # Create DataFrame with the imputed missing data.
    df_imputed = pd.DataFrame(data = df_imp, columns = total_cols)
    
    # Combine imputed DataFrame with results DataFrame
    df_combined = pd.concat([df_imputed, df_viability], axis = 1)
    
    return df_combined

