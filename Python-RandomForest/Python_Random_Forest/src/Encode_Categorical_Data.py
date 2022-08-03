'''
Created on Feb 11, 2021

@author: Wmelende & Paul Harten
'''

import pandas as pd
from sklearn.preprocessing import OneHotEncoder, OrdinalEncoder
from sklearn.impute import SimpleImputer
from Write_to_CSV import write_to_csv
from pickle import FALSE

def encode_categorical_columns(df):
    output_expandedCategoricalData = "data\\inVitro_expanded_categorical.data.csv"
    
    # Create list of columns to be encoded.
    columns_encode = ['CoreComposition', 'ShellComposition', 'CoatingComposition', 'Shape', 'SurfaceChargeType',
                      'particle concentration parameter_nonnum', 'cell type parameter_nonnum',
                      'subject parameter_nonnum', 'light parameter_nonnum']
                      
    columns_multicode = ['biochemical name parameter_nonnum', 'subpathway parameter_nonnum']
    
    #multi=False
    multi=True
    
    # Replace None with empty string to avoid an error with One-Hot Encoder.
    # for icol in columns_encode:
    #     df[icol].replace({None:""}, inplace = True)
    column_names = list(df.columns)
    columns_encode = [icol for icol in columns_encode if icol in column_names]
    columns_multicode = [icol for icol in columns_multicode if icol in column_names]
            
    # Create DataFrame with categorical data
    df_cat = df[columns_encode]
    df_multicat = df[columns_multicode]
    original_headers_cat = list(df_cat.columns)
    original_headers_multicat = list(df_multicat.columns)
    
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
    
    # Create DataFrame with the transformed categorical data.
    df1 = pd.DataFrame(data = trans_X_cat, columns = newHeaders)
    
    if (len(original_headers_multicat)==0):
        dfnew = pd.concat([df1, df_num], axis = 1)
    else:
        df_num = df_num.drop(columns_multicode, inplace = False, axis = 1)
        df_multi = imp_missing.fit_transform(df_multicat)
        if (multi==False):
            # Encode the categorical data using the One-Hot Encoder
            multicoder = OneHotEncoder(handle_unknown='ignore')
            multicoder.fit(df_multi)
            trans_X_multicat = multicoder.transform(df_multi).toarray()
            #print(trans_X_multicat)
            newHeaders_multi = multicoder.get_feature_names_out(original_headers_multicat)
            #print(newHeaders_multi)
            # Create DataFrame with the transformed categorical data.
            df2 = pd.DataFrame(data = trans_X_multicat, columns = newHeaders_multi)
            # Combine the One-Hot encoded categorical data with the numerical data.
            dfnew = pd.concat([df1, df2, df_num], axis = 1)
        else:
            # Encode the categorical data using the OrdinalEncoder
            multicoder = OrdinalEncoder(handle_unknown='use_encoded_value', unknown_value=-1)
            print(multicoder.get_params().keys())
            multicoder.fit(df_multi)
            print(multicoder.n_features_in_)
            print(multicoder.categories_)
            trans_X_multicat = multicoder.transform(df_multi)
            print(trans_X_multicat)
            #newHeaders_multi = original_headers_multicat
            newHeaders_multi = multicoder.get_feature_names_out(original_headers_multicat)
            print(newHeaders_multi)
            # Create DataFrame with the transformed categorical data.
            df2 = pd.DataFrame(data = trans_X_multicat, columns = newHeaders_multi)
            # Combine the One-Hot encoded categorical data with the numerical data.
            dfnew = pd.concat([df1, df2, df_num], axis = 1)
    
    # Write results to CSV file.
    write_to_csv(dfnew, output_expandedCategoricalData)
    
    return dfnew
