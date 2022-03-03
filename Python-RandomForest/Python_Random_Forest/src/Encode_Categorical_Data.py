'''
Created on Feb 11, 2021

@author: Wmelende
'''

import pandas as pd
from sklearn.preprocessing import OneHotEncoder
from sklearn.impute import SimpleImputer
from Write_to_CSV import write_to_csv

def encode_categorical_columns(df):
    output_expandedCategoricalData = "data\\inVitro_expanded_categorical.data.csv"
    
    # Create list of columns to be encoded.
    columns_encode = ['CoreComposition', 'ShellComposition', 'CoatingComposition', 'Shape', 'SurfaceChargeType',
                      'particle concentration parameter_nonnum', 'cell type parameter_nonnum',
                      'subject parameter_nonnum', 'light parameter_nonnum',
                      'biochemical name parameter_nonnum', 'subpathway parameter_nonnum']
    
    # Replace None with empty string to avoid an error with One-Hot Encoder.
    # for icol in columns_encode:
    #     df[icol].replace({None:""}, inplace = True)
    
    # Create DataFrame with categorical data
    df_cat = df[columns_encode]
    original_headers_cat = list(df_cat.columns)
    
    # Create DataFrame with numerical data
    df_num = df.drop(columns_encode, inplace = False, axis = 1)
    
    # Create imputation transformer for completing missing values.
    imp_missing = SimpleImputer(missing_values = None, strategy = 'constant', fill_value = 'missing')
    
    # Impute the categorical data.
    df_imp = imp_missing.fit_transform(df_cat)
    # Encode the categorical data using the One-Hot Encoder
    encoder = OneHotEncoder(handle_unknown='ignore')
    encoder.fit(df_imp)
    trans_X_cat = encoder.transform(df_imp).toarray()
    #newHeaders = encoder.get_feature_names(original_headers_cat) #python3.8
    newHeaders = encoder.get_feature_names_out(original_headers_cat) #python3.9
    # df1 = pd.get_dummies(df_cat)
    
    # Create DataFrame with the transformed categorical data.
    df1 = pd.DataFrame(data = trans_X_cat, columns = newHeaders)
    
    # Combine the One-Hot encoded categorical data with the numerical data.
    dfnew = pd.concat([df1, df_num], axis = 1)
    
    # Write results to CSV file.
    write_to_csv(dfnew, output_expandedCategoricalData)
    
    return dfnew
