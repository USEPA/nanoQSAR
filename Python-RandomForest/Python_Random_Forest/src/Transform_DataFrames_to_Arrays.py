'''
Created on Mar 4, 2021

@author: Wmelende
'''

def transform_dataframes_to_arrays(dfX, dfY):
    # Transform X DataFrame to array.
    X_train = dfX.iloc[:,:].values

    # Transform Y DataFrame to array.
    Y_train = dfY['viability result_value'].values
    
    return X_train, Y_train