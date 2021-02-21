'''
Created on Feb 11, 2021

@author: Wmelende
'''

import pandas as pd
from sklearn.preprocessing import LabelEncoder
from sklearn.preprocessing import OneHotEncoder
from sklearn.compose import ColumnTransformer

from Write_to_CSV import write_to_csv

def encode_categorical_columns(df):
    output_expandedCategoricalData = "data\\inVitro_expanded_categorical.data.csv"
    # Replace empty string with None.
    
    # Create list of columns to be encoded.
    columns_encode = ['CoreComposition', 'ShellComposition', 'CoatingComposition', 'Shape', 'SurfaceChargeType',
                      'particle concentration parameter_nonnum', 'cell type parameter_nonnum',
                      'subject parameter_nonnum', 'light parameter_nonnum']
    
    for icol in columns_encode:
        df[icol].replace({None:""}, inplace = True)
       
    t = [('cat', OneHotEncoder(dtype='int'), columns_encode)]
    
    ct = ColumnTransformer(transformers = t, remainder = 'passthrough')
    
    train_X = ct.fit_transform(df)
    
    df1 = pd.DataFrame(data = train_X, columns = ct.get_feature_names())
    
    write_to_csv(df1, output_expandedCategoricalData)
